package com.wuxiantao.wxt.ui.fragment.fansi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.FanSiIndirectlyRecViewAdapter;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.bean.FansiIndirectBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiIndirectContract;
import com.wuxiantao.wxt.mvp.fansi.p.FanSiIndirectPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioButton;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioGroup;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.FanSiInfoPopupWindow;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.FANSI_TYPE_INDIRECT;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午5:10
 * Description:${DESCRIPTION} 间接粉丝
 */
@ContentView(R.layout.fragment_fansi_indirect)
public class FanSiIndirectFragment extends MvpFragment<FanSiIndirectPresenter, FansiIndirectContract.IFansiView> implements FansiIndirectContract.IFansiView{
    @ViewInject(R.id.fansi_indirectly_rl)
    SmartRefreshLayout fansi_indirectly_rl;
    @ViewInject(R.id.fansi_indirectly_classic_header)
    ClassicsHeader fansi_indirectly_classic_header;
    @ViewInject(R.id.fansi_indirectly_rv)
    RecyclerView fansi_indirectly_rv;

    @ViewInject(R.id.fansi_indirectly_rg)
    SoftRadioGroup fansi_indirectly_rg;
    @ViewInject(R.id.fansi_indirectly_time_rb)
    SoftRadioButton fansi_indirectly_time_rb;
    @ViewInject(R.id.fansi_indirectly_deposit_rb)
    SoftRadioButton fansi_indirectly_deposit_rb;

    private FanSiIndirectlyRecViewAdapter adapter;
    private int page = 1;
    private FansiIndirectBean datas;
    private Map<String,Object> parameters = new HashMap<>();
    private LoadingDialog loadingDialog;

    @Override
    protected FanSiIndirectPresenter CreatePresenter() {
        return new FanSiIndirectPresenter();
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog.Build(getContext()).build();
        parameters.put(TOKEN,getAppToken());
        parameters.put("page",page);
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("create_time",0);
        parameters.put("cun",0);
        parameters.put("type",FANSI_TYPE_INDIRECT);
        mPresenter.obtainFansi(parameters);
        initRefreshLoad();
        fansi_indirectly_rg.setOnCheckedChangeListener((group, checkedId, orientation) -> {
            switch (checkedId){
                case R.id.fansi_indirectly_time_rb:
                    parameters.put("create_time",orientation ? 0 : 1);
                    break;
                case R.id.fansi_indirectly_deposit_rb:
                    parameters.put("cun",orientation ? 0 : 1);
                    break;
            }
            mPresenter.obtainFansi(parameters);
        });
    }


    private void initRefreshLoad(){
        fansi_indirectly_rl.setRefreshHeader(fansi_indirectly_classic_header);
        fansi_indirectly_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        fansi_indirectly_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.obtainFansi(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        fansi_indirectly_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.obtainFansi(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    public void obtainFansSuccess(FansiIndirectBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(FansiIndirectBean bean){
        if (adapter == null){
            adapter = new FanSiIndirectlyRecViewAdapter(getContext(),bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,20);
            fansi_indirectly_rv.setLayoutManager(manager);
            fansi_indirectly_rv.addItemDecoration(itemDecoration);
            fansi_indirectly_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(new FanSiIndirectlyRecViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int uid) {
                    mPresenter.obtainFansiDetail(uid);
                }

                @Override
                public void onInviteFriend() {
                    $startActivity(ShareThemActivity.class);
                }
            });
        }else {
            adapter.addList(bean.getList(),page);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        adapter = null;
        page = 1;
    }

    @Override
    public void obtainFansFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void obtainFansiDetailSuccess(FansiDetailBean bean) {
        showFansiInfoWindow(bean);
    }

    private void showFansiInfoWindow(FansiDetailBean bean){
        new FanSiInfoPopupWindow.Build(getContext())
                .setWindowDatas(bean)
                .setOnCopyClickListener(new FanSiInfoPopupWindow.OnCopyClickListener() {
                    @Override
                    public void onCopyWeChatId(String wechat) {
                        TextViewUtils.copy(wechat);
                    }

                    @Override
                    public void onCopyInviteId(String inviteId) {
                        TextViewUtils.copy(inviteId);
                    }
                })
                .setPopupWindowAnimStyle(R.style.popupwindow_anim)
                .setPopupWindowWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .builder().showPopupWindow(Gravity.CENTER);
    }

    @Override
    public void obtainFansiDetailFailure(String failure) {
        ToastUtils.error(failure);
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
