package com.wuxiantao.wxt.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.contract.LoginContract;
import com.wuxiantao.wxt.mvp.presenter.LoginPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.IMUtils;
import com.wuxiantao.wxt.utils.NumberFormatUtils;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.GAME_ACCOUNT;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AdminLoginActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-22 上午10:27
 * Description:${DESCRIPTION} 账号密码登陆
 */
@ContentView(R.layout.activity_admin_login)
public class AdminLoginActivity extends MvpActivity<LoginPresenter, LoginContract.ILoginView> implements View.OnKeyListener ,TextWatcher,LoginContract.ILoginView{
    @ViewInject(R.id.admin_login_close)
    ImageView admin_login_close;
    @ViewInject(R.id.admin_login_code)
    TextView admin_login_code;
    @ViewInject(R.id.admin_login_forget)
    TextView admin_login_forget;
    @ViewInject(R.id.admin_number_input)
    MaterialEditText admin_number_input;
    @ViewInject(R.id.admin_password_input)
    MaterialEditText admin_password_input;
    @ViewInject(R.id.admin_login_login)
    StateButton admin_login_login;

    private LoadingDialog loadingDialog;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        Bundle bundle = getBundle();
        if (bundle != null){
            String number = bundle.getString(NUMBER);
            if (!isEmpty(number)){
                admin_number_input.setText(number);
            }
        }
        setOnClikListener(admin_login_close,admin_login_code,admin_login_forget,admin_login_login);
        admin_number_input.setOnKeyListener(this);
        admin_password_input.setOnKeyListener(this);
        admin_number_input.addTextChangedListener(this);
        admin_password_input.addTextChangedListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL){
            int numberLen = getEdtText(admin_number_input).length();
            int pwLen = getEdtText(admin_password_input).length();
            admin_login_login.setEnabled(numberLen > 0 && pwLen > 0);
        }
        return false;
    }

    @Override
    public void afterTextChanged(Editable s) {
        int numberLen = admin_number_input.length();
        int pwLen = admin_password_input.length();
        admin_login_login.setEnabled(numberLen > 0 && pwLen > 0);
    }


    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.admin_login_login:
                login();
                break;
            case R.id.admin_login_close:
                finish();
                break;
            case R.id.admin_login_code:
                $startActivity(VerCodeLoginActivity.class,true);
                finish();
                break;
            case R.id.admin_login_forget:
                $startActivity(ResetPassWordActivity.class,true);
                break;
        }

    }


    private void login(){
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.login_loading).build();
        String number = getEdtText(admin_number_input);
        String pw = getEdtText(admin_password_input);
        if (NumberFormatUtils.isVerificationNo(number)){
            mPresenter.login(number,pw,0);
        }else {
            showDialog(getString(R.string.number_error),(dialog, which) ->admin_number_input.setText(""));
        }
    }

    //登录成功
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void loginSuccess(PhoneLoginBean response) {
        String phone = getEdtText(admin_number_input);
        saveUserInfo(APP_USER_ID, response.getId());
        saveUserInfo(USER_HEAD_IMG, response.getHeadimg());
        saveUserInfo(NICK_NAME, response.getNickname());
        saveUserInfo(TOKEN, response.getUnionid());
        saveUserInfo(NICK_NAME, response.getNickname());
        saveUserInfo(NUMBER,phone);
        saveUserInfo(GAME_ACCOUNT,response.getAccountname());
        //登录im客服系统
        IMUtils.createUser(response.getId(),this);
        $startActivity(MenuActivity.class,SHIFT_ID,0,true);
        Log.e("用户id:"+response.getId(),"token:"+response.getUnionid());
    }

    @Override
    public void loginFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    protected LoginPresenter CreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

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
