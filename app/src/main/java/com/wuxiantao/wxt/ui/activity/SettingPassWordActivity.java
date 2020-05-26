package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.contract.SettingPassWordContract;
import com.wuxiantao.wxt.mvp.presenter.SettingPassWordPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.GO_LOGIN_TIME;
import static com.wuxiantao.wxt.config.Constant.IS_SETTING_PW;
import static com.wuxiantao.wxt.config.Constant.NEW_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_SET_LOGIN_PW;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SettingPassWordActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-25 下午2:49
 * Description:${DESCRIPTION} 设置登陆密码
 */
@ContentView(R.layout.activity_setting_pass_word)
public class SettingPassWordActivity extends MvpActivity<SettingPassWordPresenter, SettingPassWordContract.ISettingView> implements View.OnKeyListener, TextWatcher,SettingPassWordContract.ISettingView {
    @ViewInject(R.id.setting_pass_word_type)
    TextView setting_pass_word_type;
    @ViewInject(R.id.setting_pass_word_old_input)
    MaterialEditText setting_pass_word_old_input;
    @ViewInject(R.id.setting_pass_word_new_input)
    MaterialEditText setting_pass_word_new_input;
    @ViewInject(R.id.setting_pass_word_confirm_setting)
    StateButton setting_pass_word_confirm_setting;
    @ViewInject(R.id.setting_pass_word_forget)
    TextView setting_pass_word_forget;
    //是否是设置密码
    private boolean isSettingPassWord;
    private LoadingDialog loadingDialog;
    private String number;

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle bundle = getBundle();
        if (bundle != null){
            isSettingPassWord = bundle.getBoolean(IS_SETTING_PW);
            number = bundle.getString(NUMBER);
            setting_pass_word_forget.setVisibility(isSettingPassWord ? View.GONE : View.VISIBLE);
            setting_pass_word_type.setText(isSettingPassWord ? R.string.setting_pw : R.string.setting_pw1);
            setting_pass_word_confirm_setting.setText(isSettingPassWord ? R.string.confirm_setting : R.string.confirm_update);
            setting_pass_word_old_input.setHint(isSettingPassWord ? R.string.input_pw : R.string.input_old_pw);
            setting_pass_word_new_input.setVisibility(isSettingPassWord ? View.GONE : View.VISIBLE);
            setting_pass_word_old_input.setOnKeyListener(this);
            setting_pass_word_new_input.setOnKeyListener(this);
            setting_pass_word_old_input.addTextChangedListener(this);
            setting_pass_word_new_input.addTextChangedListener(this);
            setOnClikListener(setting_pass_word_confirm_setting,setting_pass_word_forget);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        int oldPwLen = getEdtText(setting_pass_word_old_input).length();
        int newPwLen = getEdtText(setting_pass_word_new_input).length();
        setting_pass_word_confirm_setting.setEnabled(isSettingPassWord ? oldPwLen > 0 : oldPwLen > 0 && newPwLen > 0);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL){
            if (isSettingPassWord){
                int pwLen = getEdtText(setting_pass_word_old_input).length();
                setting_pass_word_confirm_setting.setEnabled(pwLen > 0);
            }else {
                int oldPwLen = getEdtText(setting_pass_word_old_input).length();
                int newPwLen = getEdtText(setting_pass_word_new_input).length();
                setting_pass_word_confirm_setting.setEnabled(oldPwLen > 0 && newPwLen > 0);
            }
        }
        return false;
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            //设置/修改密码
            case R.id.setting_pass_word_confirm_setting:
                loadingDialog = new LoadingDialog.Build(this)
                        .setLoadingText(isSettingPassWord ? getString(R.string.setting_set_pw) : getString(R.string.set_update_pw)).build();
                if (isSettingPassWord){
                    mPresenter.setUserLoginPassWord(getAppToken(),
                            "",getEdtText(setting_pass_word_old_input));
                }else {
                    mPresenter.setUserLoginPassWord(getAppToken(),
                            getEdtText(setting_pass_word_old_input),getEdtText(setting_pass_word_new_input));
                }
                break;
             //忘记密码
            case R.id.setting_pass_word_forget:
                $startActivity(ResetPassWordActivity.class);
                break;
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).build();
    }

    @Override
    protected SettingPassWordPresenter CreatePresenter() {
        return new SettingPassWordPresenter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void setPassWordsSuccess(String msg) {
        ToastUtils.success(msg);
        Bundle bundle = new Bundle();
        if (isSettingPassWord){
            //设置密码
            bundle.putString(NEW_PASS_WORD,getEdtText(setting_pass_word_old_input));
            getResult(RESULT_CODE_SET_LOGIN_PW,bundle);
        }else {
            //修改密码 成功跳转登陆界面
            showGoLoginDialog();
        }
    }

    private void showGoLoginDialog(){
        if (isEmpty(number)){
            return;
        }
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).remove(TOKEN);
        showTimeDialog(getString(R.string.pw_reset_success), getString(R.string.will), getString(R.string.goto_login), (dialog, which) -> {
            Bundle bundle = new Bundle();
            bundle.putString(NUMBER,number);
            $startActivity(AdminLoginActivity.class,bundle,true);
            finish();
        },GO_LOGIN_TIME);
    }

    @Override
    protected void onTimeEnd() {
        super.onTimeEnd();
        Bundle bundle = new Bundle();
        bundle.putString(NUMBER,number);
        $startActivity(AdminLoginActivity.class,bundle,true);
    }

    @Override
    public void setPassWordsFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> {
            setting_pass_word_old_input.setText("");
            setting_pass_word_new_input.setText("");
        });
    }

    @Override
    public void setPayPasswordSuccess() {

    }


    @Override
    public void setPayPasswordFailure(String failure) {

    }

    @Override
    public void isPayPasswordSuccess(int msg) {

    }

    @Override
    public void isPayPasswordFailure(String failure) {

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
