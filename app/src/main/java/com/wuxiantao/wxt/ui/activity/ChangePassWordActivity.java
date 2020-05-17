package com.wuxiantao.wxt.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kf5.sdk.system.utils.ToastUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.SettingPassWordContract;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.presenter.SettingPassWordPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

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
    @ViewInject(R.id.change_password_old_input)
    MaterialEditText change_password_old_input;
    @ViewInject(R.id.change_password_new_input)
    MaterialEditText change_password_new_input;
    @ViewInject(R.id.change_password_submit)
    TextView change_password_submit;
    @ViewInject(R.id.change_password_title)
    TextView change_password_title;

    private int status = 0;
    private int type = 1;
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
        change_password_submit.setOnClickListener(v -> {
            if (status==1&&TextUtils.isEmpty(change_password_old_input.getText())){
                ToastUtils.showToast("修改密码必须填写原密码");
            }else if (TextUtils.isEmpty(change_password_new_input.getText())){
                ToastUtils.showToast("请输入新密码");
            }else {
                HashMap<String, Object> map = new HashMap<>();
//                map.put("token","o1voQ1XGQBGDT1F6UjC4xnLbFavc");
                map.put("token",getAppToken());
                map.put("type",type);
                map.put("old_password",change_password_old_input.getText().toString());
                map.put("pay_password",change_password_new_input.getText().toString());
                map.put("pay_password_again",change_password_new_input.getText().toString());
                mPresenter.setUserPayPassword(map);
            }
        });
        mPresenter.isSetPayPassword(getAppToken());
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
    //设置交易密码成功
    @Override
    public void setPayPasswordSuccess(String msg) {
        ToastUtils.showToast(msg);
        finish();
    }
    //设置交易密码失败
    @Override
    public void setPayPasswordFailure(String failure) {
        ToastUtils.showToast(failure);
    }

    //获取是否设置交易密码成功
    @Override
    public void isPayPasswordSuccess(int msg) {
        status=msg;
        if (msg==1){//已设置   修改
            type=2;
            change_password_title.setText("修改密码");
        }else { //未设置  设置
            type=1;
            change_password_title.setText("设置密码");
        }
    }
    //获取是否设置交易密码失败
    @Override
    public void isPayPasswordFailure(String failure) {
        ToastUtils.showToast(failure);
    }

}
