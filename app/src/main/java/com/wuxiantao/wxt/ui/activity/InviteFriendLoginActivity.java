package com.wuxiantao.wxt.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.DepositRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.DescriptionRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.RankingRecViewAdapter;
import com.wuxiantao.wxt.bean.DepositDayBean;
import com.wuxiantao.wxt.bean.ToDayDepositBean;
import com.wuxiantao.wxt.bean.TodayShareDayBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.InviteFriendLoginContract;
import com.wuxiantao.wxt.mvp.presenter.InviteFriendLoginPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteFriendLoginActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 上午8:47
 * Description:${DESCRIPTION} 邀请好友登陆
 */
@ContentView(R.layout.activity_invite_friend_login)
public class InviteFriendLoginActivity extends MvpActivity<InviteFriendLoginPresenter, InviteFriendLoginContract.IInviteFriendView> implements InviteFriendLoginContract.IInviteFriendView {
    @ViewInject(R.id.invite_friend_login_back)
    RelativeLayout invite_friend_login_back;
    @ViewInject(R.id.invite_friend_login_head_img)
    ImageView invite_friend_login_head_img;
    @ViewInject(R.id.invite_friend_login_total_deposit)
    TextView invite_friend_login_total_deposit;
    @ViewInject(R.id.invite_friend_login_deposit_rv)
    RecyclerView invite_friend_login_deposit_rv;
    @ViewInject(R.id.invite_friend_login_share)
    StateButton invite_friend_login_share;
    @ViewInject(R.id.invite_friend_login_ranking_rv)
    RecyclerView invite_friend_login_ranking_rv;
    @ViewInject(R.id.invite_friend_login_description_rv)
    RecyclerView invite_friend_login_description_rv;

    private LoadingDialog loadingDialog;

    @Override
    protected InviteFriendLoginPresenter CreatePresenter() {
        return new InviteFriendLoginPresenter();
    }

    @Override
    public void initView() {
        setStatusBar();
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.depositDay(getAppToken());
        mPresenter.todayShare(getAppToken());
        GlideImgManager.loadRoundImg(this,getUserInfo(USER_HEAD_IMG),invite_friend_login_head_img,5.0f);
        setOnClikListener(invite_friend_login_back,invite_friend_login_share);
    }


    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.invite_friend_login_back:
                finish();
                break;
            case R.id.invite_friend_login_share:
                $startActivity(ShareThemActivity.class);
                break;
        }
    }


    @Override
    public void depositDaySuccess(DepositDayBean bean) {
        invite_friend_login_total_deposit.setText(String.valueOf(bean.getDeposit_all()));
        List<ToDayDepositBean> list = new ArrayList<>();
        list.add(new ToDayDepositBean(getString(R.string.yesterday_deposit),String.valueOf(bean.getYesterday())));
        list.add(new ToDayDepositBean(getString(R.string.today_deposit),String.valueOf(bean.getToday())));
        list.add(new ToDayDepositBean(getString(R.string.day_before_deposit),String.valueOf(bean.getBeforeyesterday())));
        initDepositLayout(list);

        boolean isReview = getSPBoolean(IS_REVIEW);
        if (isReview){
            invite_friend_login_description_rv.setVisibility(View.GONE);
        }else {
            invite_friend_login_description_rv.setVisibility(View.VISIBLE);
            initDescriptionLayout(bean);
        }

    }

    //今日 昨日 前日 红包
    private void initDepositLayout(List<ToDayDepositBean> list){
        DepositRecViewAdapter adapter = new DepositRecViewAdapter(this,list);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(3,20,true);
        invite_friend_login_deposit_rv.setLayoutManager(manager);
        invite_friend_login_deposit_rv.addItemDecoration(decoration);
        invite_friend_login_deposit_rv.setAdapter(adapter);
    }

    //分享规则副文本解析
    private void initDescriptionLayout(DepositDayBean bean){
        List<String> list = new ArrayList<>();
        list.add(getString(R.string._2000));
        list.add(getString(R.string.zero));
        list.add(getString(R.string._365));
        list.add(getString(R.string.find));
        list.add(getString(R.string.activi));
        list.add(getString(R.string.find1));
        SpaceItemDecoration decoration = new SpaceItemDecoration(0,20);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        DescriptionRecViewAdapter adapter = new DescriptionRecViewAdapter(this,list);
        invite_friend_login_description_rv.setLayoutManager(manager);
        invite_friend_login_description_rv.addItemDecoration(decoration);
        invite_friend_login_description_rv.setAdapter(adapter);
    }


    @Override
    public void depositDayFailure(String failure) {
        ToastUtils.error(failure);
    }

    //今日邀请奖励排行榜
    @Override
    public void todayShareSuccess(TodayShareDayBean bean) {
        initRankingLayout(bean);
    }


    private void initRankingLayout(TodayShareDayBean bean){
        RankingRecViewAdapter adapter = new RankingRecViewAdapter(this,bean.getList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0,20);
        invite_friend_login_ranking_rv.setLayoutManager(manager);
        invite_friend_login_ranking_rv.addItemDecoration(decoration);
        invite_friend_login_ranking_rv.setAdapter(adapter);
    }

    @Override
    public void todayShareFailure(String failure) {
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
