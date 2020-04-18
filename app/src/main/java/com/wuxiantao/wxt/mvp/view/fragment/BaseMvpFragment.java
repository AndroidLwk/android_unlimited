package com.wuxiantao.wxt.mvp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ssm.sp.SPSecuredUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.count_down.CountDownManager;
import com.wuxiantao.wxt.db.DbManagement;
import com.wuxiantao.wxt.mvp.delegate.FragmentDelegate;
import com.wuxiantao.wxt.mvp.delegate.FragmentDelegateImp;
import com.wuxiantao.wxt.mvp.delegate.FragmentMvpDelegateCallback;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import org.xutils.x;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/9 0009 23:23 8-19
 * Description: ${DESCRIPTION} 创建一个基类BaseMvpFragment，这是一个抽象类，所有使用Mvp的Fragment可以继承此类
 * Author: Administrator Shiming-Shi
 */
public abstract class BaseMvpFragment<P extends MvpPresenter,V extends MvpView> extends RxFragment implements FragmentMvpDelegateCallback<P,V>,MvpView{

    private FragmentDelegate mFragmentDelegate;

    protected P mPresenter;

    protected  Activity mActivity;

    //数据库管理
    protected DbManagement mDbManagement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentDelegate = new FragmentDelegateImp<>(this);
        mFragmentDelegate.onCreateView();
        mActivity = getActivity();
        mDbManagement = DbManagement.newInstance();
        return x.view().inject(this,inflater,container);
    }

    protected void addObserver(long diff, CountDownCallBack callBack){
        CountDownManager mCountDownManager = new CountDownManager();
        mCountDownManager.cancelCallback();
        getLifecycle().addObserver(mCountDownManager);
        mCountDownManager.startCountDown(diff).setCallBack(callBack);
    }

    protected void increaseObserver(long diff, CountDownCallBack callBack){
        CountDownManager mCountDownManager = new CountDownManager();
        mCountDownManager.cancelCallback();
        getLifecycle().addObserver(mCountDownManager);
        mCountDownManager.start(diff).setCallBack(callBack);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initView();
    }


    /**
     * 隐藏软键
     */
    protected  void  hideKey(EditText edit){
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    protected void showInputMethod(Activity act) {
        if (act.getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager)act.getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(act.getCurrentFocus().getWindowToken(),0);
        }
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
     * 获取文本输入框内容文本
     * @param editText
     * @return
     */
    protected String  getEdtText(EditText editText){
        return editText.getText().toString().trim();
    }

    protected void callPhone(String number){
        if (!isEmpty(number)){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + number);
            intent.setData(data);
            startActivity(intent);
        }
    }

    protected void sendSmsMessage(String number){
        if (!isEmpty(number)){
            Uri smsToUri = Uri.parse("smsto:"+ number);
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            intent.putExtra("sms_body","");
            startActivity(intent);
        }
    }


    //这个方法由MvpInternalDelegate 调用 BaseDelegateCallback 来创建Presenter
    @Override
    public P createPresenter() {
        mPresenter = CreatePresenter();
        return  mPresenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


    protected void put(String key,Object value){
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(key,value);
    }

    protected String getSPString(String key){
        return (String) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, "");
    }

    protected boolean getSPBoolean(String key){
        return (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, false);
    }

    protected int getSPInt(String key){
        return (int) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(key, 0);
    }



    protected String getAppToken(){
        return (String) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(TOKEN,"");
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onPause() {
        super.onPause();
        mFragmentDelegate.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        mFragmentDelegate.onResume();
    }


    @Override
    public void onStop() {
        super.onStop();
        mFragmentDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentDelegate.onDestroy();
    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    //初始化控件
    protected   void initView(){}

    //标题设置
    protected void  setTitle(){}

    //点击事件
    protected  void widgetClick(int id){}
}
