package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.ObtainCodeContract;
import com.wuxiantao.wxt.mvp.presenter.ObtainCodePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.NumberFormatUtils;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_LOGIN;
import static com.wuxiantao.wxt.config.Constant.VERCODE_REGISTERED;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VerCodeLoginActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-22 上午10:31
 * Description:${DESCRIPTION} 验证码登陆
 */
@ContentView(R.layout.activity_vercode_login)
public class VerCodeLoginActivity extends MvpActivity<ObtainCodePresenter, ObtainCodeContract.IObtainView> implements ObtainCodeContract.IObtainView {
    @ViewInject(R.id.vercode_login_pw)
    TextView vercode_login_pw;
    @ViewInject(R.id.vercode_login_close)
    ImageView vercode_login_close;
    @ViewInject(R.id.vercode_login_input)
    EditText vercode_login_input;
    @ViewInject(R.id.vercode_login_btn)
    StateButton vercode_login_btn;

    private String number;
    private boolean isRegistered;
    private LoadingDialog loadingDialog;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        setOnClikListener(vercode_login_pw,vercode_login_close,vercode_login_btn);
        vercode_login_input.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL){
                int len = getEdtText(vercode_login_input).length();
                vercode_login_btn.setEnabled(len > 0);
            }
            return false;
        });
        vercode_login_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                vercode_login_btn.setEnabled(len > 0);
            }
        });
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.vercode_login_pw:
                $startActivity(AdminLoginActivity.class,true);
                finish();
                break;
            case R.id.vercode_login_close:
                finish();
                break;
            case R.id.vercode_login_btn:
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.sms_loading).build();
                number = getEdtText(vercode_login_input);
                if (NumberFormatUtils.isVerificationNo(number)){
                    mPresenter.obtainCode(number,2);
                }else {
                    showNumberError();
                }
                break;
        }
    }

    @Override
    public void obtainSuccess(String s) {
        if (!isEmpty(number)){
            Bundle bundle = new Bundle();
            if (isRegistered){
                bundle.putInt(VERCODE_TYPE,VERCODE_REGISTERED);
            }else {
                bundle.putInt(VERCODE_TYPE,VERCODE_LOGIN);
            }
            bundle.putString(NUMBER,number);
            $startActivity(VerificationCodeActivity.class,bundle,true);
        }
    }

    @Override
    public void obtainFailure(String failure) {
        if (failure.contains(getString(R.string.not_reg))){
            showNoRegisteredWindow();
        }else {
            showDialog(failure, (dialog, which) -> {

            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRegistered = false;
    }

    private void showNoRegisteredWindow(){
        showDialog(getString(R.string.unregistered), getString(R.string.are_you_registered),getString(R.string.registered),
                "#FD3939","#C2C2C2", (dialog, which) -> {
                    isRegistered = true;
                    loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
                    mPresenter.obtainCode(number,1);
                });
    }

    private void showNumberError(){
        showDialog(getString(R.string.number_error),(dialog, which) ->vercode_login_input.setText(""));
    }

    @Override
    protected ObtainCodePresenter CreatePresenter() {
        return new ObtainCodePresenter();
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
