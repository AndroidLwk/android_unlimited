package com.wuxiantao.wxt.ui.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ParticipateRecViewAdapter;
import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.mvp.contract.ParticipateContract;
import com.wuxiantao.wxt.mvp.presenter.ParticipatePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.PROGRESS_DURATION;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ParticipateActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 上午9:50
 * Description:${DESCRIPTION} 立即参加活动->免费获取VIP权益
 */
@ContentView(R.layout.activity_participate)
public class ParticipateActivity extends MvpActivity<ParticipatePresenter, ParticipateContract.IParticipateView> implements ParticipateContract.IParticipateView {
    @ViewInject(R.id.participate_sv)
    ScrollView participate_sv;
    @ViewInject(R.id.participate_rl)
    SmartRefreshLayout participate_rl;
    @ViewInject(R.id.participate_classics_header)
    ClassicsHeader participate_classics_header;
    @ViewInject(R.id.participate_doing_rule)
    RelativeLayout participate_doing_rule;
    @ViewInject(R.id.participate_time)
    TextView participate_time;
    @ViewInject(R.id.participate_invited_count)
    TextView participate_invited_count;
    @ViewInject(R.id.participate_invited_count_2)
    TextView participate_invited_count_2;
    @ViewInject(R.id.participate_progress_bar)
    ProgressBar participate_progress_bar;
    @ViewInject(R.id.participate_rv)
    RecyclerView participate_rv;
    @ViewInject(R.id.participate_invite_friend)
    StateButton participate_invite_friend;
    private LoadingDialog loadingDialog;
    private ParticipateRecViewAdapter adapter;
    private ValueAnimator mValueAnimator;

    @Override
    public void initView(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog.Build(this).build();
        participate_rl.setRefreshHeader(participate_classics_header);
        participate_rl.setEnableLoadMore(false);
        mPresenter.getFriendNum(getAppToken(),1);
        participate_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            stopAnimation();
            mPresenter.getFriendNum(getAppToken(),1);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        setOnClikListener(participate_doing_rule,participate_invite_friend);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            //活动规则
            case R.id.participate_doing_rule:
                //滚到到底部
                participate_sv.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            //立即邀请好友
            case R.id.participate_invite_friend:
                $startActivity(ShareThemActivity.class);
                break;
        }
    }

    @Override
    protected ParticipatePresenter CreatePresenter() {
        return new ParticipatePresenter();
    }

    @Override
    public void getFriendNumSuccess(InviteFriendNumBean bean) {
        String startTime = DateUtils.timestampToTime(bean.getStart_time());
        String endTime = DateUtils.timestampToTime(bean.getEnd_time());
        participate_time.setText(getString(R.string.participate_time,startTime,endTime));
        int num = bean.getFriend_num();
        participate_invited_count.setText(getString(R.string.good_friend,num));
        participate_invited_count_2.setText(String.valueOf(num));
        startAnimation(num);
        initLayout(bean);
    }

    private void initLayout(InviteFriendNumBean bean){
        if (adapter == null){
            LinearLayoutManager manager = new LinearLayoutManager(this);
            SpaceItemDecoration decoration = new SpaceItemDecoration(20,0);
            adapter = new ParticipateRecViewAdapter(this,bean.getUser());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            participate_rv.setLayoutManager(manager);
            participate_rv.addItemDecoration(decoration);
            participate_rv.setAdapter(adapter);
        }else {
            adapter.updataList(bean.getUser());
        }
    }


    //开始动画
    private void startAnimation(int progress) {
        mValueAnimator = new ValueAnimator().ofInt(0,progress).setDuration(PROGRESS_DURATION);
        mValueAnimator.addUpdateListener(animation ->
                participate_progress_bar.setProgress((int)animation.getAnimatedValue()));
        mValueAnimator.start();
    }

    //停止动画
    private void stopAnimation(){
        participate_progress_bar.setProgress(0);
        mValueAnimator = null;
        participate_progress_bar.clearAnimation();
    }


    @Override
    public void getFriendNumFailure(String failure) {
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

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back).setLeftTextOrImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setMiddleTitleText(getResources().getString(R.string.free_vip_quanyhi)).build();
    }
}
