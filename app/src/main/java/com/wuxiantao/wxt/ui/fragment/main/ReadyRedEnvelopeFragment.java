package com.wuxiantao.wxt.ui.fragment.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MainFriendBean;
import com.wuxiantao.wxt.adapter.bean.SupermanMenuBean;
import com.wuxiantao.wxt.adapter.recview.MainFriendRcvViewAdapter;
import com.wuxiantao.wxt.adapter.recview.SupermanRecViewAdapter;
import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.MakeGoldBean;
import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.bean.UnfastenRedbagBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.DepositContract;
import com.wuxiantao.wxt.mvp.presenter.DepositPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.BalanceWithdrawActivity;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.activity.HelpCenterActivity;
import com.wuxiantao.wxt.ui.activity.InviteFriendLoginActivity;
import com.wuxiantao.wxt.ui.activity.MenuActivity;
import com.wuxiantao.wxt.ui.activity.MineFanSiActivity;
import com.wuxiantao.wxt.ui.activity.ProtocolActivity;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.AboutGoldPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OpenRedEnvelopePopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.RedBagWithdrawalPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.RedEnvelopePopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.wuxiantao.wxt.adapter.bean.MainFriendBean.TYPE_FRIEND;
import static com.wuxiantao.wxt.adapter.bean.MainFriendBean.TYPE_INVITE;
import static com.wuxiantao.wxt.config.Constant.IS_SHARE_SUCCESS;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_SHARE;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_SHARE;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_RED_BAG_LIST;
import static com.wuxiantao.wxt.config.Constant.UPDATE_RED_BAG_MAIN_INFO;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_CONTENT_TYPE;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_URL;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_URL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 现金红包
 */
@ContentView(R.layout.fragment_deposit_super_man)
public class ReadyRedEnvelopeFragment extends MvpFragment<DepositPresenter, DepositContract.IDepositView> implements DepositContract.IDepositView {
    @ViewInject(R.id.deposit_super_man_rl)
    SmartRefreshLayout deposit_super_man_rl;
    @ViewInject(R.id.deposit_super_man_classic_header)
    ClassicsHeader deposit_super_man_classic_header;

    @ViewInject(R.id.fragment_deposit_friends_bulletin)
    TextView fragment_deposit_friends_bulletin;
    @ViewInject(R.id.fragment_deposit_friends_rule)
    TextView fragment_deposit_friends_rule;
    @ViewInject(R.id.fragment_deposit_friends_count_down_time)
    TextView count_down_time;
    @ViewInject(R.id.fragment_deposit_friends_thousandth_img)
    ImageView thousandth_img;
    @ViewInject(R.id.fragment_deposit_friends_bag_open_img)
    ImageView bag_open_img;
    @ViewInject(R.id.fragment_deposit_friends_bag_money)
    TextView fragment_deposit_friends_bag_money;
    @ViewInject(R.id.fragment_deposit_lottery)
    StateButton fragment_deposit_lottery;
    @ViewInject(R.id.fragment_deposit_gold_key)
    TextView fragment_deposit_gold_key;
    @ViewInject(R.id.fragment_deposit_super_man_shantou_icon)
    ImageView shantou_icon;
    @ViewInject(R.id.fragment_deposit_super_man_gold_status)
    TextView fragment_deposit_super_man_gold_status;
    @ViewInject(R.id.fragment_deposit_super_man_vip)
    TextView fragment_deposit_super_man_vip;
    @ViewInject(R.id.fragment_deposit_super_man_pb)
    ProgressBar fragment_deposit_super_man_pb;
    @ViewInject(R.id.fragment_deposit_super_man_about_key)
    ImageView fragment_deposit_super_man_about_key;
    @ViewInject(R.id.fragment_deposit_super_man_double)
    TextView fragment_deposit_super_man_double;
    @ViewInject(R.id.fragment_deposit_super_man_invite)
    ImageView fragment_deposit_super_man_invite;

    @ViewInject(R.id.fragment_exclusive_invited_friends_rv)
    RecyclerView friends_rv;
    @ViewInject(R.id.fragment_exclusive_friends_work_num)
    TextView friends_work_num;
    @ViewInject(R.id.fragment_exclusive_friends_query_all)
    TextView fragment_exclusive_friends_query_all;

    @ViewInject(R.id.fragment_deposit_super_man_make)
    StateButton fragment_deposit_super_man_make;

    @ViewInject(R.id.deposit_super_man_rv)
    RecyclerView deposit_super_man_rv;

    private int status = -1;
    private Animation mAnimation;
    private Animation openAnimation;
    private Map<String, Object> map = new HashMap<>();
    private MainFriendRcvViewAdapter friendRcvViewAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected DepositPresenter CreatePresenter() {
        return new DepositPresenter();
    }

    @Override
    public void initView() {
        map.put(TOKEN, getAppToken());
        map.put("type", "1");
        map.put("page", "1");
        map.put("pagesize", "5");
        //注册EventBus
        EventBus.getDefault().register(this);
        mPresenter.getRedBagInfo(getAppToken());
        mPresenter.onGetFriendList(map);
        deposit_super_man_classic_header.setBackgroundResource(R.drawable.base_title_background);
        deposit_super_man_rl.setRefreshHeader(deposit_super_man_classic_header);
        deposit_super_man_rl.setEnableLoadMore(false);
        deposit_super_man_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            mPresenter.onGetFriendList(map);
            mPresenter.getRedBagInfo(getAppToken());
            EventBus.getDefault().post(new MessageEvent(UPDATE_RED_BAG_LIST));
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        setOnClikListener(fragment_deposit_friends_bulletin, fragment_deposit_friends_rule, bag_open_img, fragment_deposit_lottery,
                fragment_deposit_super_man_about_key, fragment_deposit_super_man_invite, fragment_exclusive_friends_query_all,
                fragment_deposit_super_man_make);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
        openAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.open_red_bag);
    }


    @Subscribe
    @Override
    protected void widgetClick(int id) {
        switch (id) {
            //邀请排行榜
            case R.id.fragment_deposit_friends_bulletin:
//                $startActivity(LeaderboardActivity.class);
                $startActivity(InviteFriendLoginActivity.class);
                break;
            //规则
            case R.id.fragment_deposit_friends_rule:
//                $startActivity(RuleActivity.class);
                $startActivity(HelpCenterActivity.class);
                break;
            //开启红包
            case R.id.fragment_deposit_friends_bag_open_img:
                if (status == 0) {
                    showOpenRedEnvelopeWindow();
                } else {
                    showOnlyConfirmDialog(R.string.tips, R.string.red_not_can_open);
                }
                break;
            //提现
            case R.id.fragment_deposit_lottery:
                $startActivity(BalanceWithdrawActivity.class);
                break;
            //金币打造
            case R.id.fragment_deposit_super_man_make:
                showRewardVideoAd(2);
                break;
            //关于金币
            case R.id.fragment_deposit_super_man_about_key:
                showAboutGoldWindow();
                break;
            //邀请好友
            case R.id.fragment_deposit_super_man_invite:
                $startActivity(ShareThemActivity.class);
                break;
            //查看全部好友
            case R.id.fragment_exclusive_friends_query_all:
                $startActivity(MineFanSiActivity.class);
//                $startActivity(MyRedBagFriendActivity.class);
                break;
        }
    }

    //播放激励视频
    private void showRewardVideoAd(int type) {
        // type 1:开启红包 2:打造金币
        //开启红包
        if (type == 1) {
            AdUtils.initRewardVideoAd(getActivity(), () -> mPresenter.openRedBag(getAppToken()));
        }
        //双倍打造
        else if (type == 2 && make_status == 2) {
            AdUtils.initRewardVideoAd(getActivity(), () -> mPresenter.onMakeGold(getAppToken(), make_status));
        }
        //单倍打造
        else if (type == 2 && make_status == 1) {
            mPresenter.onMakeGold(getAppToken(), make_status);
        }
    }


    //关于金币
    private void showAboutGoldWindow() {
        new AboutGoldPopupWindow.Build(getContext())
                .setWindowAnimStyle(R.style.custom_dialog)
                .builder().showPopupWindow();
    }


    //开启红包对话框
    private void showOpenRedEnvelopeWindow() {
        new OpenRedEnvelopePopupWindow.Build(getContext())
                .setWindowAnimStyle(R.style.custom_dialog)
                .setGoldBalance(String.valueOf(store))
                .setOnClickListener(() -> showRewardVideoAd(1))
                .builder().showPopupWindow();

    }


    //金币打造状态
    private int make_status = 1;
    //金币打造速度
    private int make_per = 0;
    //当前金币数量
    private int store = 0;
    //最大值
    private int max = 1800;

    //红包信息获取成功
    @Override
    public void getRedBagInfoSuccess(RedBagInfoBean bean) {
        stopAllTime();
        //红包余额
        String deposit = bean.getList().getDeposit_amount();
        //我的余额
        String amount = BigDecimalUtils.roundStr(bean.getList().getAmount(), 8);
        //是否是vip
        boolean isVip = bean.getList().getIs_vip() == 1;
        //翻倍状态:0不能翻倍 1可以翻倍
        int can_double = bean.getList().getDoubleStatus();
        //累计打造数量
        int make_total = bean.getList().getMake_total();
        //每秒打造数量
        make_per = bean.getList().getMake_per();
        //打造开始时间
        long make_start_time = bean.getList().getMake_starttime();
        //打造结束时间
        long make_end_time = bean.getList().getMake_endtime();
        //开红包时间
        long send_time = Long.valueOf(bean.getList().getSend_time());
        //弹窗金额,大于0时弹出提示框
        double money = bean.getList().getMoney();
        if (money > 0){
            showShareWindow(String.valueOf(money));
        }
        //金币总数量
        store = bean.getList().getStore();
        //当前时间
        long current_time = System.currentTimeMillis() / 1000;
        //总共时间
        long total_time = make_end_time - make_start_time + 2;
        max = (int) total_time;
        //开启红包倒计时
        if (send_time - current_time > 0) {
            status = -1;
            openAnimation.cancel();
            bag_open_img.clearAnimation();
            count_down_time.setVisibility(View.VISIBLE);
            thousandth_img.setVisibility(View.GONE);
            bag_open_img.setBackgroundResource(R.mipmap.close_red_pag);
            startOpenRedBagTimer(send_time - current_time);
        } else {
            //红包倒计时已结束
            status = 0;
            bag_open_img.startAnimation(openAnimation);
            count_down_time.setVisibility(View.GONE);
            thousandth_img.setVisibility(View.VISIBLE);
            bag_open_img.setBackgroundResource(R.mipmap.open_red_pag);
        }
        //已经开始的时间
        long start_ = current_time - make_start_time;
        if (start_ > 0) {
            store += make_per * (current_time - make_start_time);
            total_time -= start_;
        }

        setRedBagAnim(make_per);

        //开启金币打造倒计时
        if (total_time > 0) {
            //点击翻倍
            if (can_double == 1) {
                fragment_deposit_super_man_make.setText(getString(R.string.click_make_double));
                fragment_deposit_super_man_make.setEnabled(true);
                make_status = 2;
            } else {
                //翻倍成功
                fragment_deposit_super_man_make.setText(getString(R.string.make_double_success));
                fragment_deposit_super_man_make.setEnabled(false);
            }
            startGoldMake(total_time, make_start_time);
        } else {
            //点击打造
            make_status = 1;
            fragment_deposit_super_man_make.setText(getString(R.string.click_make));
            fragment_deposit_super_man_make.setEnabled(true);
        }
        String status_wait = getString(R.string.gold_wait_make, make_per);
        int color = Color.parseColor("#FF5857");
        TextViewUtils.setTextViewKeyWordHighlight(fragment_deposit_super_man_gold_status,
                status_wait, color, new Pair<>(5, status_wait.length()));
        fragment_deposit_super_man_vip.setVisibility(isVip && total_time > 0 ? View.VISIBLE : View.GONE);
        fragment_deposit_gold_key.setText(BigDecimalUtils.round(amount,5));
        NumberFormat format = NumberFormat.getInstance();
        fragment_deposit_friends_bag_money.setText(format.format(new BigDecimal(deposit)));
        fragment_deposit_super_man_double.setText(getString(R.string.build_speed_, store));

        initMenuLayout(bean.getList().getNew_url());
    }

    //设置红包动画
    private void setRedBagAnim(int make_per) {
        int value = 5;
        if (make_per > 0) {
            int speed = make_per > value ? value : make_per;
            mAnimation.setDuration(3000 / speed);
            shantou_icon.startAnimation(mAnimation);
        } else {
            shantou_icon.clearAnimation();
            mAnimation.cancel();
        }
    }


    private void initMenuLayout(String new_url) {
        SupermanRecViewAdapter adapter = new SupermanRecViewAdapter(getContext(), initList(), new_url);
        RecViewItemDecoration decoration = new RecViewItemDecoration(getContext(), 1);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(1);
        deposit_super_man_rv.setLayoutManager(manager);
        deposit_super_man_rv.addItemDecoration(decoration);
        deposit_super_man_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new SupermanRecViewAdapter.OnItemClickListener() {
            //去玩游戏
            @Override
            public void onPlayGame() {
                $startActivity(H5GameActivity.class);
            }

            //去邀请好友
            @Override
            public void onInviteFriend() {
                goShare();
            }

            //去开通会员
            @Override
            public void onOpenMember() {
                $startActivity(MenuActivity.class, SHIFT_ID, 1);
            }

            //去购物
            @Override
            public void onShopping() {
                $startActivity(MenuActivity.class, SHIFT_ID, 0);
            }

            //去体验
            @Override
            public void onExperience() {
                if (!isEmpty(new_url)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(WEB_VIEW_CONTENT_TYPE, WEB_VIEW_TYPE_URL);
                    bundle.putString(WEB_VIEW_URL, new_url);
                    $startActivity(ProtocolActivity.class, bundle);
                }
            }
        });
    }

    private List<SupermanMenuBean> initList() {
        List<SupermanMenuBean> list = new ArrayList<>();
        //获取图片资源
        TypedArray ta = getResources().obtainTypedArray(R.array.main_red_menu_icon);
        int[] icons = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            icons[i] = ta.getResourceId(i, 0);
        }
        ta.recycle();
        String[] title = getResources().getStringArray(R.array.main_red_menu_title);
        String[] content = getResources().getStringArray(R.array.main_red_menu_content);
        String[] text = getResources().getStringArray(R.array.main_red_menu_text);
        for (int i = 0; i < icons.length; i++) {
            SupermanMenuBean bean = new SupermanMenuBean();
            bean.setIcon(icons[i]);
            bean.setTitle(title[i]);
            bean.setContent(content[i]);
            bean.setText(text[i]);
            list.add(bean);
        }
        return list;
    }

    private List<CountDownTimer> red_timers = new ArrayList<>();
    private List<Disposable> gold_timers = new ArrayList<>();

    //开启红包倒计时
    private void startOpenRedBagTimer(long diff) {
        //millisInFuture：设置倒计时的总时间（毫秒）
        //countDownInterval：设置每次减去的时间（毫秒）
        CountDownTimer redBagCountDownTimer = new CountDownTimer(diff * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = DateUtils.timeParse(millisUntilFinished / 1000);
                count_down_time.setText(getString(R.string.count_down, time));
            }

            @Override
            public void onFinish() {
                updateData();
            }
        };
        redBagCountDownTimer.start();
        red_timers.add(redBagCountDownTimer);
    }

    //开启金币打造
    private void startGoldMake(long diff,long start_time) {
        //设置金币打造的总数量
        fragment_deposit_super_man_pb.setMax(max);
        Disposable mDisposable = Flowable.intervalRange(0, diff, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    long current_time = System.currentTimeMillis() / 1000;
                    int progress = (int) (current_time - start_time);
                    fragment_deposit_super_man_pb.setProgress(progress);
                    store += make_per;
                    fragment_deposit_super_man_double.setText(getString(R.string.build_speed_, store));
                })
                .doOnComplete(this::updateData)
                .subscribe();
        gold_timers.add(mDisposable);
    }

    //停止红包倒计时
    private void stopOpenRedBagTimer() {
        if (!red_timers.isEmpty()) {
            for (CountDownTimer timer : red_timers) {
                if (timer != null) {
                    timer.cancel();
                }
            }
            red_timers.clear();
        }
    }

    //停止金币打造
    private void stopGoldMake() {
        fragment_deposit_super_man_pb.setProgress(0);
        if (!gold_timers.isEmpty()) {
            for (Disposable mDisposable : gold_timers) {
                if (mDisposable != null && !mDisposable.isDisposed()) {
                    mDisposable.dispose();
                }
            }
            gold_timers.clear();
        }
    }

    //停止所有倒计时
    private void stopAllTime() {
        stopOpenRedBagTimer();
        stopGoldMake();
    }

    //红包开启成功
    @Override
    public void openRedBagSuccess(UnfastenRedbagBean bean) {
        showOpenRedBagSuccessWindow(BigDecimalUtils.round(String.valueOf(bean.getGetred()), 8));
    }

    //显示红包开启成功后的对话框
    private void showOpenRedBagSuccessWindow(String money) {
        new RedBagWithdrawalPopupWindow
                .Build(getContext())
                .setMoneyText(money)
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setOnPopupClickListener(isShare -> {
                    if (isShare) {
                       goShare();
                    }
                    updateData();
                })
                .builder()
                .showPopupWindow();
    }

    //打造金币
    @Override
    public void onMakeGoldSuccess(MakeGoldBean bean) {
        mPresenter.getRedBagInfo(getAppToken());
    }


    //好友列表获取成功
    @Override
    public void onGetFriendListSuccess(FriendListBean bean) {
        friends_work_num.setText(getString(R.string.invite_add_money, bean.getAll_amount()));
        initFriendListLayout(bean.getList());
    }


    private void initFriendListLayout(List<FriendListBean.ListBean> listBeans) {
        if (friendRcvViewAdapter == null) {
            friendRcvViewAdapter = new MainFriendRcvViewAdapter(getContext(), initFriendList(listBeans));
            GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(5, 0, true);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
            friends_rv.setLayoutManager(manager);
            friends_rv.addItemDecoration(decoration);
            friends_rv.setAdapter(friendRcvViewAdapter);
            friendRcvViewAdapter.setOnItemClickListener(() -> goShare());
        } else {
            friendRcvViewAdapter.updateList(initFriendList(listBeans));
        }
    }

    private List<MainFriendBean> list = new ArrayList<>();

    private List<MainFriendBean> initFriendList(List<FriendListBean.ListBean> listBeans) {
        list.clear();
        int max = 5;
        if (listBeans == null || listBeans.isEmpty()) {
            for (int i = 0; i < max; i++) {
                MainFriendBean inviteBean = new MainFriendBean();
                inviteBean.setType(TYPE_INVITE);
                list.add(inviteBean);
            }
        } else {
            for (FriendListBean.ListBean listBean : listBeans) {
                MainFriendBean friendBean = new MainFriendBean();
                friendBean.setUrl(listBean.getHeadimg());
                friendBean.setName(listBean.getNickname());
                friendBean.setType(TYPE_FRIEND);
                list.add(friendBean);
            }
            int size = list.size();
            if (size < max) {
                int diff = max - size;
                for (int i = 0; i < diff; i++) {
                    MainFriendBean inviteBean = new MainFriendBean();
                    inviteBean.setType(TYPE_INVITE);
                    list.add(inviteBean);
                }
            }
        }
        return list;
    }

    @Override
    public void getRedBagInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void onMakeGoldFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void onGetFriendListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void openRedBagFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        //分享后返回
        if (requestCode == REQUEST_CODE_SHARE && resultCode == RESULT_CODE_SHARE) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                boolean isShare = bundle.getBoolean(IS_SHARE_SUCCESS);
                if (isShare){
                    updateData();
                }
            }
        }
    }

    //分享得现金对话框
    private void showShareWindow(String money) {
        String head_img = getUserInfo(USER_HEAD_IMG);
        new RedEnvelopePopupWindow.Build(getContext())
                .setMoneyText(money)
                .setHeadImg(head_img)
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setOnPopupClickListener(this::updateData)
                .builder().showPopupWindow();
    }

    //刷新数据
    private void updateData() {
        mPresenter.getRedBagInfo(getAppToken());
        mPresenter.onGetFriendList(map);
//        EventBus.getDefault().post(new MessageEvent(UPDATE_RED_BAG_LIST));
    }

    //刷新数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == UPDATE_RED_BAG_MAIN_INFO) {
            mPresenter.getRedBagInfo(getAppToken());
        }
    }

    private void goShare(){
        $startActivityForResult(ShareThemActivity.class, REQUEST_CODE_SHARE);
    }

    @Override
    public void onDestroy() {
        stopAllTime();
        //注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}
