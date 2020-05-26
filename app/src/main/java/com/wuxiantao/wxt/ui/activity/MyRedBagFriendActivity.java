package com.wuxiantao.wxt.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.RedBagFriendRecViewAdapter;
import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.PromotionLanguageBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.RedBagFriendContract;
import com.wuxiantao.wxt.mvp.presenter.RedBagFriendPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.InviteInterestPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OperatePromptPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.PromotionLanguagePopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.RedBagActivationPopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.BitmapUtils;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.WebPageUrlUtils;
import com.wuxiantao.wxt.wxapi.WXShare;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.FAST_CLICK_DELAY_TIME;
import static com.wuxiantao.wxt.config.Constant.FAST_CLICK_RED_BAG;
import static com.wuxiantao.wxt.config.Constant.FRIEND_TYPE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_RED_BAG_INFO_LIST;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyRedBagFriendActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午12:34
 * Description:${DESCRIPTION} 我的红包好友
 */
@ContentView(R.layout.activity_my_red_bag_friend)
public class MyRedBagFriendActivity extends MvpActivity<RedBagFriendPresenter, RedBagFriendContract.IRedBagFriendView> implements RedBagFriendContract.IRedBagFriendView {
    @ViewInject(R.id.my_red_bag_friend_rl)
    SmartRefreshLayout my_red_bag_friend_rl;
    @ViewInject(R.id.my_red_bag_friend_classic_header)
    ClassicsHeader my_red_bag_friend_classic_header;
    @ViewInject(R.id.my_red_bag_friend_back)
    ImageView my_red_bag_friend_back;
    @ViewInject(R.id.my_red_bag_friend_promotion_language)
    ImageView my_red_bag_friend_promotion_language;
    @ViewInject(R.id.my_red_bag_friend_promotion_poster)
    ImageView my_red_bag_friend_promotion_poster;
    @ViewInject(R.id.my_red_bag_friend_leaderboard)
    TextView my_red_bag_friend_leaderboard;
    @ViewInject(R.id.my_red_bag_friend_work_count)
    TextView my_red_bag_friend_work_count;
    @ViewInject(R.id.my_red_bag_friend_rest_count)
    TextView my_red_bag_friend_rest_count;
    @ViewInject(R.id.my_red_bag_friend_contribution_count)
    TextView my_red_bag_friend_contribution_count;
    @ViewInject(R.id.my_red_bag_friend_rv)
    RecyclerView my_red_bag_friend_rv;
    @ViewInject(R.id.my_red_bag_friend_invite)
    StateButton my_red_bag_friend_invite;

    private RedBagFriendRecViewAdapter adapter;
    private int page = 1;
    private FriendListBean bean;
    private Map<String, Object> parameters = new HashMap<>();
    private boolean isTimeLine;
    //是否是复制推广语
    private boolean isCopy;

    @Override
    protected RedBagFriendPresenter CreatePresenter() {
        return new RedBagFriendPresenter();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBar();
        Bundle bundle = getBundle();
        int type = 1;
        if (bundle != null) {
            type = bundle.getInt(FRIEND_TYPE);
        }
        initRefresh();
        parameters.put(TOKEN, getAppToken());
        parameters.put("page", page);
        parameters.put("pagesize", 11);
        parameters.put("type", type);
        mPresenter.getRedBagFriendList(parameters);
        setOnClikListener(my_red_bag_friend_back, my_red_bag_friend_invite, my_red_bag_friend_leaderboard,
                my_red_bag_friend_promotion_language, my_red_bag_friend_promotion_poster);
    }

    private void initRefresh() {
        my_red_bag_friend_classic_header.setBackgroundResource(R.drawable.base_title_background);
        my_red_bag_friend_rl.setRefreshHeader(my_red_bag_friend_classic_header);
        my_red_bag_friend_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        my_red_bag_friend_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            parameters.put("page", page);
            mPresenter.getRedBagFriendList(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        my_red_bag_friend_rl.setOnLoadMoreListener(refreshlayout -> {
            if (bean != null && bean.getMore() == 1) {
                parameters.put("page", ++page);
                mPresenter.getRedBagFriendList(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            //返回
            case R.id.my_red_bag_friend_back:
                finish();
                break;
            //邀请好友
            case R.id.my_red_bag_friend_invite:
                isCopy = false;
                $startActivity(ShareThemActivity.class);
                break;
            //邀请排行榜
            case R.id.my_red_bag_friend_leaderboard:
                $startActivity(InviteFriendLoginActivity.class);
                break;
            //推广语
            case R.id.my_red_bag_friend_promotion_language:
                isCopy = true;
                mPresenter.getPromotionLanguage(1);
                break;
            //推广海报
            case R.id.my_red_bag_friend_promotion_poster:
                $startActivity(ShareThemActivity.class);
                break;
        }
    }

    //好友邀请分享
    private void showInviteWindow() {
        new InviteInterestPopupWindow.Build(this)
                .setOnSharePlatformListener(new InviteInterestPopupWindow.Build.OnSharePlatformListener() {
                    @Override
                    public void onWeChatFriend() {
                        isTimeLine = false;
                        mPresenter.getPromotionLanguage(0);
                    }

                    @Override
                    public void onWeChatGroup() {
                        isTimeLine = true;
                        mPresenter.getPromotionLanguage(0);
                    }
                }).builder().showPopupWindow();
    }

    //好友列表获取成功
    @Override
    public void getRedBagFriendListSuccess(FriendListBean bean) {
        this.bean = bean;
//        int workfans = bean.getWorkfans();
//        int seleepfans = bean.getSeleepfans();
//        double contribution = bean.getAll_amount();
//        my_red_bag_friend_work_count.setText(getString(R.string.people_regex, workfans));
//        my_red_bag_friend_rest_count.setText(getString(R.string.people_regex, seleepfans));
//        my_red_bag_friend_contribution_count.setText(getString(R.string.yuan_regex, contribution));
        initLayout(bean);
    }

    @Override
    public void getRedBagFriendListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    private void initLayout(FriendListBean bean) {
        if (adapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(40, 20);
            adapter = new RedBagFriendRecViewAdapter(this, bean.getList());
            my_red_bag_friend_rv.setLayoutManager(manager);
            my_red_bag_friend_rv.addItemDecoration(decoration);
            my_red_bag_friend_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(this::showActivationWindow);
        } else {
            adapter.addList(bean.getList(), page);
        }
    }

    //好友激活成功
    @Override
    public void onActivateSuccess(String msg) {
        //刷新列表
        mPresenter.getRedBagFriendList(parameters);
        EventBus.getDefault().post(new MessageEvent(UPDATE_RED_BAG_INFO_LIST));
    }

    @Override
    public void onActivateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    //推广语获取成功
    @Override
    public void getLanguageSuccess(PromotionLanguageBean bean) {
        String title = bean.getTitle();
        String description = bean.getTuiguang();
        String url = WebPageUrlUtils.createShareUrl(getLocalUserId(), isCopy ? 1 : 0);
        Bitmap bitmap = BitmapUtils.drawableBitmapOnWhiteBg(getResources().getDrawable(R.drawable.share_img_bg));
        if (isCopy) {
            //复制推广语
            showPromLangPopupWindow(description, url);
        } else {
            //好友邀请
            WXShare.getInstance().shareWebPage(isTimeLine, url, title, description, bitmap);
        }
    }

    @Override
    public void getLanguageFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //显示激活打造钥匙Window
    private void showActivationWindow(int id, int act_num, String imgUrl) {
        new RedBagActivationPopupWindow
                .Build(this)
                .setWindowData(act_num, imgUrl, 1)
                .setOnPopupClickListener(type -> {
                    if (type == RedBagActivationPopupWindow.Build.BUILD_SPEED_TWO) {
                        if (DateUtils.isCanClick2()) {
                            showRewardVideoAd(id, type);
                        } else {
                            long lastTime = getUserInfoLong(FAST_CLICK_RED_BAG);
                            long currentTime = System.currentTimeMillis() / 1000;
                            long diff = lastTime + FAST_CLICK_DELAY_TIME - currentTime;
                            showDownTimeWindow(diff);
                        }
                    } else {
                        buildLotteryTicket(id, type);
                    }
                }).builder().showPopupWindow();
    }

    //显示限时对话框
    private void showDownTimeWindow(long time) {
        new OperatePromptPopupWindow.Build(this)
                .setCountDowntime(String.valueOf(time))
                .builder().showPopupWindow();
    }

    //播放激励视频
    private void showRewardVideoAd(int id, int type) {
        AdUtils.initRewardVideoAd(this, () -> buildLotteryTicket(id, type));
    }

    //打造抽奖券
    private void buildLotteryTicket(int id, int type) {
        if (type == RedBagActivationPopupWindow.Build.BUILD_SPEED_TWO) {
            long time = System.currentTimeMillis() / 1000;
            saveUserInfo(FAST_CLICK_RED_BAG, time);
        }
        Map<String, Object> map = new HashMap<>();
        map.put(TOKEN, getAppToken());
        map.put("type", 1);
        map.put("user_id", id);
        mPresenter.onActivateFriend(map, type);
    }


    //复制推广语
    private void showPromLangPopupWindow(String description, String url) {
        new PromotionLanguagePopupWindow.Build(this)
                .setWindowTextData(description, url)
                .setOnSharePromotionListener(TextViewUtils::copy)
                .builder().showPopupWindow();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}
