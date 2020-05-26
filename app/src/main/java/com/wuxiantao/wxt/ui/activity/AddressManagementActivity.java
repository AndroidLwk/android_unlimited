package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.AddressRecViewAdapter;
import com.wuxiantao.wxt.bean.AddressEditInfoBean;
import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.AddressContract;
import com.wuxiantao.wxt.mvp.presenter.AddressPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.ADDRESS_ADD_TYPE;
import static com.wuxiantao.wxt.config.Constant.ADDRESS_EDIT_TYPE;
import static com.wuxiantao.wxt.config.Constant.ADDRESS_TYPE;
import static com.wuxiantao.wxt.config.Constant.EDIT_ADDRESS_INFO;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_ADD_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_EDIT_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.UPDATE_DEFAULT_ADDRESS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressManagementActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-3 上午10:39
 * Description:${DESCRIPTION} 地址管理
 */
@ContentView(R.layout.activity_address_management)
public class AddressManagementActivity extends MvpActivity<AddressPresenter, AddressContract.IAddressView> implements AddressContract.IAddressView {
    @ViewInject(R.id.address_management_rl)
    SmartRefreshLayout address_management_rl;
    @ViewInject(R.id.address_management_classic_header)
    ClassicsHeader address_management_classic_header;
    @ViewInject(R.id.address_management_rv)
    RecyclerView address_management_rv;
    @ViewInject(R.id.address_management_back)
    ImageView address_management_back;
    @ViewInject(R.id.address_management_add)
    StateButton address_management_add;

    private AddressRecViewAdapter adapter;
    private int page = 1;
    private AddressListBean datas;
    private LoadingDialog loadingDialog;

    @Override
    protected AddressPresenter CreatePresenter() {
        return new AddressPresenter();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog.Build(this).build();
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        mPresenter.getAddressList(getAppToken(),page);
        initRefreshLoadmore();
        setOnClikListener(address_management_back,address_management_add);
    }

    private void initRefreshLoadmore() {
        address_management_rl.setRefreshHeader(address_management_classic_header);
        address_management_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        address_management_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getAddressList(getAppToken(),page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        address_management_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1) {
                mPresenter.getAddressList(getAppToken(),++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void getAddressListSuccess(AddressListBean bean) {
        this.datas = bean;
        initLayout(bean);
    }

    @Override
    public void getAddressListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    private void initLayout(AddressListBean bean) {
        if (adapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(0, 10);
            adapter = new AddressRecViewAdapter(this, bean.getList());
            address_management_rv.setLayoutManager(manager);
            address_management_rv.addItemDecoration(decoration);
            address_management_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(new AddressRecViewAdapter.OnItemClickListener() {
                //编辑地址
                @Override
                public void onEditAddress(AddressEditInfoBean infoBean) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ADDRESS_TYPE,ADDRESS_EDIT_TYPE);
                    bundle.putParcelable(EDIT_ADDRESS_INFO,infoBean);
                    $startActivityForResult(ReceiptAddressActivity.class,bundle,REQUEST_CODE_EDIT_ADDRESS);
                }

                //设默认地址
                @Override
                public void onDefaultAddress(String address_id) {
                    mPresenter.setDefaultAddress(getAppToken(),address_id);
                }

                //删除地址
                @Override
                public void onDeleteAddress(String address_id) {
                    mPresenter.deleteAddress(getAppToken(),address_id);
                }
            });
        } else {
            adapter.addList(bean.getList(),page);
        }
    }


    @Override
    public void setDefaultAddressSuccess(String msg) {
        mPresenter.getAddressList(getAppToken(),page);
        EventBus.getDefault().post(new MessageEvent(UPDATE_DEFAULT_ADDRESS));
    }

    @Override
    public void deleteAddressSuccess(String msg) {
        mPresenter.getAddressList(getAppToken(),page);
        EventBus.getDefault().post(new MessageEvent(UPDATE_DEFAULT_ADDRESS));
    }

    @Override
    public void setDefaultAddressFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void deleteAddressFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            case R.id.address_management_back:
                finish();
                break;
            case R.id.address_management_add:
                Bundle bundle = new Bundle();
                bundle.putInt(ADDRESS_TYPE,ADDRESS_ADD_TYPE);
                $startActivityForResult(ReceiptAddressActivity.class,bundle,REQUEST_CODE_ADD_ADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.getAddressList(getAppToken(),page);
        EventBus.getDefault().post(new MessageEvent(UPDATE_DEFAULT_ADDRESS));
    }



    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }
}
