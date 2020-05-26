package com.wuxiantao.wxt.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.bean.NetSwitchBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.broadcast.LoginBroadcastReceiver;
import com.wuxiantao.wxt.broadcast.NetWorkTimeOutReceiver;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.MenuContract;
import com.wuxiantao.wxt.mvp.presenter.MenuPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.radiobutton.SiteImgRadioButton;
import com.wuxiantao.wxt.ui.fragment.main.IncomeHallFragment;
import com.wuxiantao.wxt.ui.fragment.main.MyDepositFragment;
import com.wuxiantao.wxt.ui.fragment.main.ScrapingCardFragment;
import com.wuxiantao.wxt.ui.fragment.main.TaoBaoFragment;
import com.wuxiantao.wxt.ui.fragment.main.TaskHallFragment;
import com.wuxiantao.wxt.ui.popupwindow.VersionUpdatePopupWindow;
import com.wuxiantao.wxt.utils.AppUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Api.DKX_SWITCH;
import static com.wuxiantao.wxt.config.Constant.BUY_STATUS;
import static com.wuxiantao.wxt.config.Constant.FAIURE_POINTTOCARD_BACK;
import static com.wuxiantao.wxt.config.Constant.GAME_ACCOUNT;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.IS_SHOW_SAMALL_ICON;
import static com.wuxiantao.wxt.config.Constant.IS_TAO_BAO_AUTH;
import static com.wuxiantao.wxt.config.Constant.NEW_AWARD_STATUS;
import static com.wuxiantao.wxt.config.Constant.NICKNAME;
import static com.wuxiantao.wxt.config.Constant.RECEIVE_LOGIN;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;
import static com.wuxiantao.wxt.config.Constant.VIP_STATUS;
import static com.wuxiantao.wxt.config.Constant.WECHAT_NO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MenuActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午2:55
 * Description:${DESCRIPTION} 主菜单界面
 */
@ContentView(R.layout.activity_menu)
public class MenuActivity extends MvpActivity<MenuPresenter, MenuContract.IMenuView> implements RadioGroup.OnCheckedChangeListener, MenuContract.IMenuView {
    @ViewInject(R.id.main_menu_radiogroup)
    RadioGroup main_menu_radiogroup;
    @ViewInject(R.id.menu_tab_taobao)
    public SiteImgRadioButton menu_tab_taobao;
    @ViewInject(R.id.menu_tab_high_area)
    SiteImgRadioButton menu_tab_high_area;
    @ViewInject(R.id.menu_tab_red_envelope)
    SiteImgRadioButton menu_tab_red_envelope;
    @ViewInject(R.id.menu_tab_income_hall)
    public SiteImgRadioButton menu_tab_income_hall;
    @ViewInject(R.id.menu_tab_my_deposit)
    SiteImgRadioButton menu_tab_my_deposit;
    @ViewInject(R.id.menu_tab_high_area_checked_img)
    ImageView menu_tab_high_area_checked_img;
    private TaoBaoFragment mTaoBaoFragment;
    private TaskHallFragment mTaskHallFragment;
    private ScrapingCardFragment mScrapingCardFragment;
    private IncomeHallFragment mIncomeHallFragment;
    private MyDepositFragment mMyDepositFragment;

    private FragmentTransaction mTransaction;

    private static final int DEFAULT_PAGE = 0;
    private List<SiteImgRadioButton> mRBlist = new ArrayList<>();
    private boolean isExit = false;
    private String type = "0";
    private LoginBroadcastReceiver receiver;
    private NetWorkTimeOutReceiver netWorkReceiver;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isExit = false;
        }
    };

    private Bundle savedInstanceState;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void initView(Bundle savedInstanceState) {
        menu_tab_high_area.setVisibility(AppUtils.isVisiableView() ? View.VISIBLE : View.GONE);
        menu_tab_high_area_checked_img.setVisibility(AppUtils.isVisiableView() ? View.VISIBLE : View.GONE);
        this.savedInstanceState = savedInstanceState;
        EventBus.getDefault().register(this);
        setOnClikListener(menu_tab_high_area_checked_img);
        setStatusBar();
        boolean isReview = getSPBoolean(IS_REVIEW);
        menu_tab_red_envelope.setVisibility(isReview ? View.GONE : View.VISIBLE);
        menu_tab_income_hall.setVisibility(isReview ? View.GONE : View.VISIBLE);
        if (isEmpty(getAppToken())) {
            $startActivity(WeChatLoginActivity.class, true);
            finish();
        } else {
            mPresenter.getSwitchType(DKX_SWITCH);
        }
        //注册广播
        //登陆
        receiver = new LoginBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(RECEIVE_LOGIN);
        //网络
        netWorkReceiver = new NetWorkTimeOutReceiver();
        IntentFilter netFilter = new IntentFilter();
        netFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        netFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        netFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //注册
        registerReceiver(receiver, filter);
        registerReceiver(netWorkReceiver, netFilter);
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.menu_tab_high_area_checked_img://刮刮卡图片
                changeFragment(2, null);
                menu_tab_red_envelope.setChecked(true);
//                mScrapingCardFragment.refreshData();
                break;
        }
    }

    @Override
    protected MenuPresenter CreatePresenter() {
        return new MenuPresenter();
    }

    @Override
    public void getSwitchTypeSuccess(NetSwitchBean bean) {
        Log.e("getSwitchTypeSuccess", "df");
        this.type = bean.getType();
        mPresenter.onStartApp(getAppToken());
    }

    //启动app 成功
    @Override
    public void onStartAppSuccess(String msg) {
        Log.e("onStartAppSuccess", "df");
        mPresenter.getAppCurrentVersion();
    }

    //停止app
    @Override
    public void onStopAppSuccess(String msg) {

    }

    //版本信息获取成功
    @Override
    public void getAppCurrentVersionSuccess(CurrentVersionBean bean) {
//        boolean isNeedUpdate = VersionUtils.newInstance().isNeedUpdate(bean);
//        if (isNeedUpdate) {
//            showVersionUpdateWindow(bean.getSite_url());
//        }
        mPresenter.getPersonalInfo(getAppToken());
    }

    //显示升级对话框
    private void showVersionUpdateWindow(String url) {
        new VersionUpdatePopupWindow.Build(this)
                .setPopupWindowWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnPopupClickListener(() -> {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_UPDATE_APP);
//                    bundle.putString(APP_URL,url);
//                    $startActivity(ProtocolActivity.class,bundle);
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }).builder().showPopupWindow();
    }

    //获取用户信息
    @Override
    public void getPersonalInfoSuccess(PersonalInfoBean bean) {
        if (type.equals("0")) {
            int vipStatus = bean.getVip();
            int buyStatus = bean.getBuy_status();
            int newAwardStatus = bean.getNew_award_status();
            //淘宝是否授权
            boolean isTaoBao = bean.getIs_taobao() == 1;
            //是否绑定手机号
            boolean isBindingNumber = !isEmpty(bean.getPhone());
            //是否填写微信号
            boolean isWriteWeChat = !isEmpty(bean.getWechat());
            //缓存信息
            saveUserStatus(VIP_STATUS, vipStatus);
            saveUserStatus(BUY_STATUS, buyStatus);
            saveUserStatus(NEW_AWARD_STATUS, newAwardStatus);
            saveUserInfo(IS_TAO_BAO_AUTH, bean.getIs_taobao() == 1);
            saveUserInfo(IS_SHOW_SAMALL_ICON, !(isTaoBao && isBindingNumber && isWriteWeChat));
            saveUserInfo(WECHAT_NO, bean.getWechat());
            saveUserInfo(GAME_ACCOUNT, bean.getAccountname());
            saveUserInfo(USER_HEAD_IMG, bean.getHeadimg());
            saveUserInfo(NICKNAME, bean.getNickname());
            //isShowSmallIcon
            if (savedInstanceState == null) {
                initRadioButton();
                Bundle bundle = getBundle();
                if (bundle != null) {
                    // initRadioButton();
                    int id = bundle.getInt(SHIFT_ID);
                    changeFragment(id, null);
                    mRBlist.get(id).setChecked(true);
                } else {
                    changeFragment(DEFAULT_PAGE, null);
                }
            }
            main_menu_radiogroup.setOnCheckedChangeListener(MenuActivity.this);
        }
    }

    //初始化RadioButton
    private void initRadioButton() {
        mRBlist.clear();
        mRBlist.add(menu_tab_taobao);
        mRBlist.add(menu_tab_high_area);
        mRBlist.add(menu_tab_red_envelope);
        mRBlist.add(menu_tab_income_hall);
        mRBlist.add(menu_tab_my_deposit);
        mRBlist.get(DEFAULT_PAGE).setChecked(true);
    }

    //RadioGroup 监听
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            //淘宝优选
            case R.id.menu_tab_taobao:
                changeFragment(0, null);
                break;
            //游戏
            case R.id.menu_tab_high_area:
                changeFragment(1, null);
                break;
            //刮刮卡
            case R.id.menu_tab_red_envelope:
                changeFragment(2, null);
                mScrapingCardFragment.refreshData();
                break;
            //任务大厅
            case R.id.menu_tab_income_hall:
                changeFragment(3, null);
                mTaskHallFragment.refreshData();
                break;
            //我的
            case R.id.menu_tab_my_deposit:
                changeFragment(4, null);
                break;
        }
    }

    //切换页面
    public void changeFragment(int index, Bundle bundle) {
        FragmentManager mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        hideFragments();
        //   menu_tab_high_area_checked_img.setVisibility(index == 2 ? View.VISIBLE : View.GONE);
        switch (index) {
            //首页
            case 0:
                if (mTaoBaoFragment == null) {
                    mTaoBaoFragment = new TaoBaoFragment();
                    mTransaction.add(R.id.main_menu_frament, mTaoBaoFragment);
                } else {
                    mTransaction.show(mTaoBaoFragment);
                }
                if (bundle != null) {
                    mTaoBaoFragment.setArguments(bundle);
                }
                break;
            //游戏分红
            case 1:
                if (mIncomeHallFragment == null) {
                    mIncomeHallFragment = new IncomeHallFragment();
                    mTransaction.add(R.id.main_menu_frament, mIncomeHallFragment);
                } else {
                    mTransaction.show(mIncomeHallFragment);
                }
                if (bundle != null) {
                    mIncomeHallFragment.setArguments(bundle);
                }


                break;
            //刮刮卡
            case 2:
                if (mScrapingCardFragment == null) {
                    mScrapingCardFragment = new ScrapingCardFragment();
                    mTransaction.add(R.id.main_menu_frament, mScrapingCardFragment);
                } else {
                    mTransaction.show(mScrapingCardFragment);
                }
                if (bundle != null) {
                    mScrapingCardFragment.setArguments(bundle);
                }
                break;
            case 3://任务大厅
                if (mTaskHallFragment == null) {
                    mTaskHallFragment = new TaskHallFragment();
                    mTransaction.add(R.id.main_menu_frament, mTaskHallFragment);
                } else {
                    mTransaction.show(mTaskHallFragment);
                }
                if (bundle != null) {
                    mTaskHallFragment.setArguments(bundle);
                }
                break;
            //个人中心
            case 4:
                if (mMyDepositFragment == null) {
                    mMyDepositFragment = new MyDepositFragment();
                    mTransaction.add(R.id.main_menu_frament, mMyDepositFragment);
                } else {
                    mTransaction.show(mMyDepositFragment);
                }
                if (bundle != null) {
                    mMyDepositFragment.setArguments(bundle);
                }
                break;
        }
        mTransaction.commitAllowingStateLoss();
    }

    //隐藏所有fragment
    private void hideFragments() {
        hideFragments(mTaoBaoFragment);
        hideFragments(mTaskHallFragment);
        hideFragments(mScrapingCardFragment);
        hideFragments(mIncomeHallFragment);
        hideFragments(mMyDepositFragment);
    }


    //隐藏
    private void hideFragments(Fragment fragment) {
        if (fragment != null) {
            mTransaction.hide(fragment);
        }
    }

    //双击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                ToastUtils.info(getResources().getString(R.string.drop_out_app));
                //利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                //退出程序
                finish();
                BaseApplication.getInstance().exitApp();
            }
        }
        return false;
    }

    @Override
    public void getSwitchTypeFailure(String failure) {
        Log.e("getSwitchTypeFailure", "");
        mPresenter.getPersonalInfo(getAppToken());
    }

    @Override
    public void getAppCurrentVersionFailure(String failure) {
        mPresenter.getPersonalInfo(getAppToken());
    }

    @Override
    public void getPersonalInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onStopAppFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onStartAppFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        //释放百川相应的资源引用
        AlibcTradeSDK.destory();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        if (netWorkReceiver != null) {
            unregisterReceiver(netWorkReceiver);
        }
        if (!isEmpty(getAppToken())) {
            mPresenter.onStopApp(getAppToken());
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getMessage().equals(FAIURE_POINTTOCARD_BACK)) {
            changeFragment(3, null);
            menu_tab_income_hall.setChecked(true);

        }
    }
}
