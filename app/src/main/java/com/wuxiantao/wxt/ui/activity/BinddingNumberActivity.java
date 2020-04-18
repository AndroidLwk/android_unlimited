package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.BindingPhoneContract;
import com.wuxiantao.wxt.mvp.presenter.BindNumberPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.edittext.filter.MaxTextLengthFilter;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BinddingNumberActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午8:50
 * Description:${DESCRIPTION} 绑定手机号- >手机号输入
 */
@ContentView(R.layout.activity_banding_number_activity)
public class BinddingNumberActivity extends MvpActivity<BindNumberPresenter, BindingPhoneContract.IBindingView> implements BindingPhoneContract.IBindingView {
    @ViewInject(R.id.banding_number_rl)
    SmartRefreshLayout banding_number_rl;
    @ViewInject(R.id.banding_number_input_edt)
    EditText input_edt;
    @ViewInject(R.id.banding_number_input_next)
    StateButton next;
    @ViewInject(R.id.banding_number_input_clear)
    ImageView banding_number_input_clear;

    private int type = -1;
    private LoadingDialog loadingDialog;
    @Override
    protected BindNumberPresenter CreatePresenter() {
        return new BindNumberPresenter();
    }

    @Override
    public void initView() {
        Bundle bundle = getBundle();
        if (bundle != null){
            type = bundle.getInt(VERCODE_TYPE);
            switch (type){
                case VERCODE_BINDING_NUMBER:
                    initTitle(getString(R.string.banding_number));
                    break;
                case VERCODE_CHANGE_NUMBER:
                    initTitle(getString(R.string.change_number));
                    break;
            }
        }
        banding_number_rl.setEnableRefresh(false);
        banding_number_rl.setEnableLoadMore(false);
        setOnClikListener(next,banding_number_input_clear);
        input_edt.setFilters(new InputFilter[]{new MaxTextLengthFilter(11)});
        input_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                banding_number_input_clear.setVisibility(len > 0 ? View.VISIBLE : View.INVISIBLE);
                next.setEnabled(len > 0);
            }
        });
        input_edt.setOnKeyListener((v, keyCode, event) -> {
            int len = getEdtText(input_edt).length();
            banding_number_input_clear.setVisibility(len > 0 ? View.VISIBLE : View.INVISIBLE);
            next.setEnabled(len > 0);
            return false;
        });
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.banding_number_input_clear:
                input_edt.setText("");
                break;
            case R.id.banding_number_input_next:
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.sms_loading).build();
                String number = getEdtText(input_edt);
                if (!isEmpty(number)){
                    if (NumberFormatUtils.isVerificationNo(number)){
                        mPresenter.obtainCode(number,3);
                    }else {
                        showDialog(getString(R.string.number_format_error), (dialog, which) -> input_edt.setText(""));
                    }
                }
                break;
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null){
            return;
        }
        Bundle bundle = new Bundle();
        String number = data.getStringExtra(NUMBER);
        bundle.putString(NUMBER,number);
        if (requestCode == REQUEST_CODE_BINDING_NUMBER && resultCode == RESULT_CODE_BINDING_NUMBER){
            getResult(RESULT_CODE_BINDING_NUMBER,bundle);
        }
        else if (requestCode == REQUEST_CODE_CHANGE_NUMBER && resultCode == RESULT_CODE_CHANGE_NUMBER){
            getResult(RESULT_CODE_CHANGE_NUMBER,bundle);
        }
    }

    private void initTitle(String title){
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(title).build();
    }

    @Override
    public void obtainSuccess(String msg) {
        Bundle bundle = new Bundle();
        String number = getEdtText(input_edt);
        switch (type){
            case VERCODE_BINDING_NUMBER:
                bundle.putInt(VERCODE_TYPE,VERCODE_BINDING_NUMBER);
                bundle.putString(NUMBER,number);
                $startActivityForResult(VerificationCodeActivity.class,bundle,REQUEST_CODE_BINDING_NUMBER);
                break;
            case VERCODE_CHANGE_NUMBER:
                bundle.putInt(VERCODE_TYPE,VERCODE_CHANGE_NUMBER);
                bundle.putString(NUMBER,number);
                $startActivityForResult(VerificationCodeActivity.class,bundle,REQUEST_CODE_CHANGE_NUMBER);
                break;
        }
    }

    @Override
    public void obtainFailure(String failure) {
        showDialog(failure, (dialog, which) -> input_edt.setText(""));
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
