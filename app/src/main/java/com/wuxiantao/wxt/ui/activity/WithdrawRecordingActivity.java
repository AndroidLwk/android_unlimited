package com.wuxiantao.wxt.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.WithdrawRecordingRecViewAdapter;
import com.wuxiantao.wxt.bean.WithdrawRecordingBean;
import com.wuxiantao.wxt.mvp.contract.WithdrawRecordingContract;
import com.wuxiantao.wxt.mvp.presenter.WithdrawRecordingPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawRecordingActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午6:17
 * Description:${DESCRIPTION}提现记录
 */
@ContentView(R.layout.activity_withdraw_recording)
public class WithdrawRecordingActivity extends MvpActivity<WithdrawRecordingPresenter, WithdrawRecordingContract.IRecordingView> implements WithdrawRecordingContract.IRecordingView {

    @ViewInject(R.id.withdraw_recording_rl)
    SmartRefreshLayout withdraw_recording_rl;
    @ViewInject(R.id.withdraw_recording_classic_header)
    ClassicsHeader withdraw_recording_classic_header;
    @ViewInject(R.id.withdraw_recording_rv)
    RecyclerView withdraw_recording_rv;

    private WithdrawRecordingRecViewAdapter adapter;
    private int page = 1;
    private LoadingDialog loadingDialog;
    private WithdrawRecordingBean datas;

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
        mPresenter.obtainRecording(getAppToken(),page);
        initRefreshLoadMore();
    }

    private void initRefreshLoadMore(){
        withdraw_recording_rl.setRefreshHeader(withdraw_recording_classic_header);
        withdraw_recording_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        withdraw_recording_rl.setOnRefreshListener(refreshlayout ->{
            page = 1;
            refreshlayout.resetNoMoreData();
            mPresenter.obtainRecording(getAppToken(),page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        withdraw_recording_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas.getMore() == 1){
                mPresenter.obtainRecording(getAppToken(),++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.withdraw_recording).build();
    }

    @Override
    protected WithdrawRecordingPresenter CreatePresenter() {
        return new WithdrawRecordingPresenter();
    }

    @Override
    public void obtainRecordingSuccess(WithdrawRecordingBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(WithdrawRecordingBean bean){
        if (adapter == null){
            adapter = new WithdrawRecordingRecViewAdapter(this,bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(this);
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,0);
            withdraw_recording_rv.setLayoutManager(manager);
            withdraw_recording_rv.addItemDecoration(itemDecoration);
            withdraw_recording_rv.setAdapter(adapter);
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    @Override
    public void obtainRecordingFailure(String failure) {
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
