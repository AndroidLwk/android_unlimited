package com.wuxiantao.wxt.ui.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.contract.CodeVerifyContract;
import com.wuxiantao.wxt.mvp.presenter.CodeVerifyPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.timer.CountdownManager;
import com.wuxiantao.wxt.ui.custom.layout.VerCodeBorderLayout;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.IMUtils;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.GAME_ACCOUNT;
import static com.wuxiantao.wxt.config.Constant.GO_LOGIN_TIME;
import static com.wuxiantao.wxt.config.Constant.NEW_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;
import static com.wuxiantao.wxt.config.Constant.VERCODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_LOGIN;
import static com.wuxiantao.wxt.config.Constant.VERCODE_REGISTERED;
import static com.wuxiantao.wxt.config.Constant.VERCODE_RESET_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VerificationCodeActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午8:51  验证码验证
 * Description:${DESCRIPTION} 更改用户信息 更换手机号/绑定手机号/更换支付宝/手机号登陆/重置登陆密码
 */
@ContentView(R.layout.activity_verification_code)
public class VerificationCodeActivity extends MvpActivity<CodeVerifyPresenter, CodeVerifyContract.IVerifyView> implements CodeVerifyContract.IVerifyView {
    @ViewInject(R.id.verification_code_rl)
    SmartRefreshLayout verification_code_rl;
    @ViewInject(R.id.ver_code_edt)
    VerCodeBorderLayout ver_code_edt;
    @ViewInject(R.id.ver_code_number)
    TextView ver_code_number;
    @ViewInject(R.id.ver_code_count_down)
    TextView count_down_tv;
    @ViewInject(R.id.ver_code_count_second)
    TextView ver_code_count_second;

    private CountdownManager mTimer;
    private LoadingDialog loadingDialog;
    private int type = -1;
    private String number;
    private String newPassWord;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initView() {
        Bundle bundle = getBundle();
        if (bundle != null){
            type = bundle.getInt(VERCODE_TYPE);
            number = bundle.getString(NUMBER);
            newPassWord = bundle.getString(NEW_PASS_WORD);
            initTitle(type);
            if (!isEmpty(number)){
                ver_code_number.setText(NumberFormatUtils.phoneNumberSub(number));
                ver_code_edt.setOnCompleteListener(content ->{
                    switch (type){
                        //验证码登陆
                        case VERCODE_LOGIN:
                            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.login_loading).build();
                            mPresenter.login(number,content,1);
                            break;
                        //验证码注册
                        case VERCODE_REGISTERED:
                            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.register_loading).build();
                            mPresenter.phoneRegister(number,content,"");
                            break;
                        //绑定手机号
                        case VERCODE_BINDING_NUMBER:
                        //更换手机号
                        case VERCODE_CHANGE_NUMBER:
                            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.binding_loading).build();
                            Map<String,Object> map = new HashMap<>();
                            map.put(TOKEN,getAppToken());
                            map.put("phone",number);
                            map.put("code",content);
                            mPresenter.bindingNumber(map);
                            break;
                        //重置密码
                        case VERCODE_RESET_PASS_WORD:
                            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.reset_pw_loading).build();
                            mPresenter.resetPassWord(number,content,newPassWord);
                            break;
                    }
                });
                startCountDown();
            }
        }
        verification_code_rl.setEnableRefresh(false);
        verification_code_rl.setEnableLoadMore(false);
        setOnClikListener(count_down_tv);
    }

    //重新发送验证码
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void widgetClick(int id) {
        if (id == R.id.ver_code_count_down){
            ver_code_edt.clear();
            ver_code_count_second.setVisibility(View.VISIBLE);
            startCountDown();
        }
    }

    //登陆成功
    @Override
    public void loginSuccess(PhoneLoginBean response) {
        saveUserInfo(APP_USER_ID,response.getId());
        saveUserInfo(USER_HEAD_IMG,response.getHeadimg());
        saveUserInfo(NICK_NAME,response.getNickname());
        saveUserInfo(TOKEN,response.getUnionid());
        saveUserInfo(NUMBER,number);
        saveUserInfo(GAME_ACCOUNT,response.getAccountname());
        //登录im客服系统
        IMUtils.createUser(response.getId(),this);
        $startActivity(MenuActivity.class,SHIFT_ID,0,true);
    }

    //登陆失败
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void loginFailure(String failure) {
        showVerCodeErrorDialog(failure, (dialog, which) -> ver_code_edt.clear());
    }

    //注册成功
    @Override
    public void registerSuccess(PhoneLoginBean bean) {
        saveUserInfo(APP_USER_ID,bean.getId());
        saveUserInfo(USER_HEAD_IMG,bean.getHeadimg());
        saveUserInfo(NICK_NAME,bean.getNickname());
        saveUserInfo(TOKEN,bean.getUnionid());
        saveUserInfo(NUMBER,number);
        saveUserInfo(GAME_ACCOUNT,bean.getAccountname());
        //登录im客服系统
        IMUtils.createUser(bean.getId(),this);
        $startActivity(MenuActivity.class,SHIFT_ID,0);
    }

    //注册失败
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void registerFailure(String failure) {
        showVerCodeErrorDialog(failure, (dialog, which) -> ver_code_edt.clear());
    }

    //重置密码成功
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void resetPassWordSuccess(String success) {
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).remove(TOKEN);
        showTimeDialog(getString(R.string.pw_reset_success), getString(R.string.will), getString(R.string.goto_login), (dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(NUMBER,number);
            $startActivity(AdminLoginActivity.class,bundle,true);
        },GO_LOGIN_TIME);
    }

    @Override
    protected void onTimeEnd() {
        super.onTimeEnd();
        Bundle bundle = new Bundle();
        bundle.putString(NUMBER,number);
        $startActivity(AdminLoginActivity.class,bundle,true);
    }

    //重置密码失败
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void resetPassWordFailure(String failure) {
        showVerCodeErrorDialog(failure, (dialog, which) -> ver_code_edt.clear());
    }

    //绑定手机号成功
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void bindingNumberSuccess(String message) {
        if (!isEmpty(number)){
            showOnlyConfirmDialog(message, (dialog, which) -> {
                Bundle bundle = new Bundle();
                bundle.putString(NUMBER,number);
                switch (type){
                    case VERCODE_BINDING_NUMBER:
                        getResult(RESULT_CODE_BINDING_NUMBER,bundle);
                        break;
                    case VERCODE_CHANGE_NUMBER:
                        getResult(RESULT_CODE_CHANGE_NUMBER,bundle);
                        break;
                }
            });
        }
    }


    //绑定手机号失败
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void bindingNumberFailure(String failure) {
        showVerCodeErrorDialog(failure, (dialog, which) -> ver_code_edt.clear());
    }

    //开始倒计时
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startCountDown(){
        mTimer = new CountdownManager(count_down_tv) {
            @Override
            public void onTimeOver() {
                count_down_tv.setText(getString(R.string.re_send));
                ver_code_count_second.setVisibility(View.GONE);
            }
        };
        mTimer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showVerCodeErrorDialog(String msg, DialogInterface.OnClickListener listener){
        showDialog(msg, listener);
    }


    private void initTitle(int type){
        switch (type){
            //验证码登陆
            case VERCODE_LOGIN:
                initTitle(getString(R.string.ver_code_login));
                break;
            //验证码注册
            case VERCODE_REGISTERED:
                initTitle(getString(R.string.ver_code_reg));
                break;
            //绑定手机号
            case VERCODE_BINDING_NUMBER:
                initTitle(getString(R.string.banding_number));
                break;
            //更换手机号
            case VERCODE_CHANGE_NUMBER:
                initTitle(getString(R.string.change_number));
                break;
            //重置密码
            case VERCODE_RESET_PASS_WORD:
                initTitle(getString(R.string.reset_pw));
                break;
            default:
                initTitle(getString(R.string.ver_code_ver));
                break;
        }
    }

    private void initTitle(String title){
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(title).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    protected CodeVerifyPresenter CreatePresenter() {
        return new CodeVerifyPresenter();
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
