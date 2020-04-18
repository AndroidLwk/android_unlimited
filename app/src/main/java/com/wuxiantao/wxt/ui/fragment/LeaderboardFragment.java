package com.wuxiantao.wxt.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.LeaderboarRecViewAdapter;
import com.wuxiantao.wxt.bean.LeaderboardBean;
import com.wuxiantao.wxt.mvp.contract.BulletinRecordingContract;
import com.wuxiantao.wxt.mvp.presenter.BulletinRecordingPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.REVENUE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DepositLeaderFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 下午2:10
 * Description:${DESCRIPTION} 今日收益榜/累计收益榜
 */
@ContentView(R.layout.fragment_interest_leader)
public class LeaderboardFragment extends MvpFragment<BulletinRecordingPresenter, BulletinRecordingContract.IRecordingView> implements BulletinRecordingContract.IRecordingView {
    @ViewInject(R.id.interest_leader_rl)
    SmartRefreshLayout interest_leader_rl;
    @ViewInject(R.id.interest_leader_classic_header)
    ClassicsHeader interest_leader_classic_header;
    @ViewInject(R.id.interest_leader_rv)
    RecyclerView interest_leader_rv;

    private LeaderboarRecViewAdapter adapter;
    private LoadingDialog loadingDialog;
    private LeaderboardBean datas;
    private int page = 1;
    //收益类型:1今日排行 2 总收益排行
    private int revenueType = 1;

    @Override
    protected BulletinRecordingPresenter CreatePresenter() {
        return new BulletinRecordingPresenter();
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        if (bundle != null){
            revenueType = bundle.getInt(REVENUE_TYPE);
        }
        loadingDialog = new LoadingDialog.Build(getContext()).build();
        mPresenter.obtainRecording(getAppToken(),page,revenueType);
        initRefreshLoad();
    }

    private void initRefreshLoad(){
        interest_leader_rl.setRefreshHeader(interest_leader_classic_header);
        interest_leader_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        interest_leader_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.obtainRecording(getAppToken(),page,revenueType);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        interest_leader_rl.setOnLoadMoreListener(refreshLayout -> {
            if (datas.getMore() == 1){
                mPresenter.obtainRecording(getAppToken(),++page,revenueType);
                refreshLayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    //垂直列表
    private void initVerLayout(LeaderboardBean bean){
        if (adapter == null){
            adapter = new LeaderboarRecViewAdapter(getContext(),bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,0);
            interest_leader_rv.setLayoutManager(manager);
            interest_leader_rv.addItemDecoration(itemDecoration);
            interest_leader_rv.setAdapter(adapter);
            adapter.setOnBaseViewClickListener(userid -> {

            });
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    @Override
    public void obtainRecordingSuccess(LeaderboardBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    @Override
    public void obtainRecordingFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
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
