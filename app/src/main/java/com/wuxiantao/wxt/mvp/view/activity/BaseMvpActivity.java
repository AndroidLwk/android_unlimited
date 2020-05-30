package com.wuxiantao.wxt.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ssm.sp.SPSecuredUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.count_down.CountDownManager;
import com.wuxiantao.wxt.db.DbManagement;
import com.wuxiantao.wxt.mvp.delegate.ActivityDelegateImp;
import com.wuxiantao.wxt.mvp.delegate.ActivityMvpDelegateCallback;
import com.wuxiantao.wxt.mvp.delegate.ActyvityDelegate;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import org.xutils.x;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/9 0009 23:23 8-19
 * Description: ${DESCRIPTION} 创建一个基类Activity，这是一个抽象类，所有使用Mvp的Activity可以继承此类
 * Author: Administrator Shiming-Shi
 */

public abstract class BaseMvpActivity<P extends MvpPresenter,V extends MvpView> extends RxAppCompatActivity implements ActivityMvpDelegateCallback<P,V>,MvpView{

    private ActyvityDelegate mActyvityDelegate;
    protected P mPresenter;
    protected Context mContext;
    protected DbManagement mDbManagement;

    // 设置 SPApplication 字体不随系统字体设置改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res != null) {
            Configuration config = res.getConfiguration();
            if (config != null && config.fontScale != 1.0f) {
                config.fontScale = 1.0f;
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        }
        return res;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        //PushAgent.getInstance(this).onAppStart();//友盟Push后台进行日活统计及多维度推送
//        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        //设置状态栏透明
//        StatusBarUtil.setTranslucentStatus(this);
//        StatusBarUtil.fullScreen(this);
        //设置默认不弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mActyvityDelegate = new ActivityDelegateImp<>(this);
        mActyvityDelegate.onCreate();
        mContext = this.getApplication();
        mDbManagement = DbManagement.newInstance();
        initView(savedInstanceState);
        setTitle();
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void addObserver(long diff, CountDownCallBack callBack){
        CountDownManager mCountDownManager = new CountDownManager();
        getLifecycle().addObserver(mCountDownManager);
        mCountDownManager.startCountDown(diff).setCallBack(callBack);
    }
    protected void addObserverx(long diff, CountDownCallBack callBack){
        CountDownManager mCountDownManager = new CountDownManager();
        getLifecycle().addObserver(mCountDownManager);
        mCountDownManager.startCountDownx(diff).setCallBack(callBack);
    }

    //设置状态栏完全透明
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }



    /**
     * 显示软键盘
     */
    protected void showInputMethod() {
        if (getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
        }
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 隐藏软键
     */
    protected  void  hideKey(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 判断非空
     * @param str
     * @return
     */
    protected boolean isEmpty(String str){
        return str == null || TextUtils.isEmpty(str);
    }

    /**
     * 判断非空
     * @param str
     * @return
     */
    protected boolean isEmpty(String ...str){
        if (str.length <= 0){
            return true;
        }
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    protected void loginOut(){}

    protected void getResult(int resultCode){
        Intent intent = getIntent();
        setResult(resultCode,intent);
        this.finish();
    }

    protected void getResult(int resultCode,Bundle bundle){
        Intent intent = getIntent();
        if (bundle != null){
            intent.putExtras(bundle);
            setResult(resultCode,intent);
        }else {
            getResult(resultCode);
        }
        this.finish();
    }

    protected  Bundle  getBundle(){
        return getIntent().getExtras();
    }

    protected  String   getStringExtras(String key){
        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString(key);
        return isEmpty(value) ? "null" : value;
    }

    protected  int   getIntegerExtras(String key){
        Bundle bundle = getIntent().getExtras();
        int value = bundle.getInt(key);
        return value;
    }


    protected void put(String key,Object value){
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(key,value);
    }

    protected String getSPString(String key){
        return (String) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, "");
    }

    protected long getSPLong(String key){
        return (long) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, 0L);
    }

    protected boolean getSPBoolean(String key){
        return (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, false);
    }

    protected int getSPInt(String key){
        return (int) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, 0);
    }

    /**
     * 获取文本输入框内容文本
     * @param editText editText
     * @return
     */
    protected String  getEdtText(EditText editText){
        //去除空格
        return editText.getText().toString().trim();
    }

    //标题设置
    protected void  setTitle(){

    }

    //这个方法由MvpInternalDelegate 调用 BaseDelegateCallback 来创建Presenter
    @Override
    public P createPresenter() {
        mPresenter = CreatePresenter();
        return mPresenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    //点击软键盘之外的空白处，隐藏软件盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            hideSoftInput();
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)){
            return true;
        }
        return onTouchEvent(ev);
    }


    @Override
    public void setPresenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mActyvityDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActyvityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActyvityDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActyvityDelegate.onDestroy();
        mPresenter.onDestroy();
    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    protected   void initView(Bundle savedInstanceState){}

    protected  void widgetClick(int id){}

    protected void onLongClick(int id){};
}
