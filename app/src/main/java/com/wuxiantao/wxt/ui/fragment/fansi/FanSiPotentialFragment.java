package com.wuxiantao.wxt.ui.fragment.fansi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.FanSiPotentialRecViewAdapter;
import com.wuxiantao.wxt.bean.FanSiPotentialBean;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiPotentialContract;
import com.wuxiantao.wxt.mvp.fansi.p.FanSiPotentialPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioButton;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioGroup;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.ActivationFriendPopupWindow;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.FANSI_TYPE_POTENTIAL;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午5:10
 * Description:${DESCRIPTION} 潜在粉丝
 */
@ContentView(R.layout.fragment_fansi_potential)
public class FanSiPotentialFragment extends MvpFragment<FanSiPotentialPresenter, FansiPotentialContract.IFansiView> implements FansiPotentialContract.IFansiView {
    @ViewInject(R.id.fansi_potential_rl)
    SmartRefreshLayout fansi_potential_rl;
    @ViewInject(R.id.fansi_potential_classic_header)
    ClassicsHeader fansi_potential_classic_header;
    @ViewInject(R.id.fansi_potential_rv)
    RecyclerView fansi_potential_rv;
    @ViewInject(R.id.fansi_potential_rg)
    SoftRadioGroup fansi_potential_rg;
    @ViewInject(R.id.fansi_potential_time_rb)
    SoftRadioButton fansi_potential_time_rb;

    private FanSiPotentialRecViewAdapter adapter;
    private FanSiPotentialBean datas;
    private Map<String,Object> parameters = new HashMap<>();
    private LoadingDialog loadingDialog;
    private int page = 1;

    @Override
    protected FanSiPotentialPresenter CreatePresenter() {
        return new FanSiPotentialPresenter();
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog.Build(getContext()).build();
        parameters.put(TOKEN,getAppToken());
        parameters.put("page",page);
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("create_time",0);
        parameters.put("type",FANSI_TYPE_POTENTIAL);
        mPresenter.obtainFansi(parameters);

        initRefreshLoad();
        fansi_potential_rg.setOnCheckedChangeListener((group, checkedId, orientation) -> {
            parameters.put("create_time",orientation ? 0 : 1);
            mPresenter.obtainFansi(parameters);
        });
    }


    private void initRefreshLoad(){
        fansi_potential_rl.setRefreshHeader(fansi_potential_classic_header);
        fansi_potential_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        fansi_potential_rl.setOnRefreshListener(refreshlayout ->{
            page = 1;
            refreshlayout.resetNoMoreData();
            parameters.put("page",page);
            mPresenter.obtainFansi(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        fansi_potential_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.obtainFansi(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void obtainFansSuccess(FanSiPotentialBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(FanSiPotentialBean bean){
        if (adapter == null){
            adapter = new FanSiPotentialRecViewAdapter(getContext(),bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,20);
            fansi_potential_rv.setLayoutManager(manager);
            fansi_potential_rv.addItemDecoration(itemDecoration);
            fansi_potential_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(new FanSiPotentialRecViewAdapter.OnItemClickListener() {
                @Override
                public void onActivation(int uid,String img,String name) {
                    showActivationWindow(uid,img,name);
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

    private void showActivationWindow(int uid,String img,String name){
        new ActivationFriendPopupWindow.Build(getContext())
                .setCircleImageResource(img)
                .setNickName(name)
                .setOnCopyListener(TextViewUtils::copy)
                .builder()
                .showPopupWindow();
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
