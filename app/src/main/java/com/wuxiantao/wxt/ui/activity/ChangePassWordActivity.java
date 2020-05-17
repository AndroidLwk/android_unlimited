package com.wuxiantao.wxt.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.SettingPassWordContract;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.presenter.SettingPassWordPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--18:24
 * Description: 修改密码
 * Author: lht
 */
@ContentView(R.layout.activity_change_pass_word)
public class ChangePassWordActivity extends MvpActivity<SettingPassWordPresenter, SettingPassWordContract.ISettingView> implements TextWatcher,SettingPassWordContract.ISettingView  {
    @ViewInject(R.id.bimg_change_pwd_back)
    ImageView bimg_change_pwd_back;


    @Override
    protected SettingPassWordPresenter CreatePresenter() {
        return new SettingPassWordPresenter();
    }

    @Override
    protected void initView() {
        setStatusBar();
        initListener();
    }

    private void initListener() {
        bimg_change_pwd_back.setOnClickListener(v -> finish());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void setPassWordsSuccess(String msg) {

    }

    @Override
    public void setPassWordsFailure(String failure) {

    }

    @Override
    public void setPayPasswordSuccess(String msg) {

    }

    @Override
    public void setPayPasswordFailure(String failure) {

    }

}
