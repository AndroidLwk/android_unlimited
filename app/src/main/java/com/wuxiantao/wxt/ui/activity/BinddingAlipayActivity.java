package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.BindingAlipayContract;
import com.wuxiantao.wxt.mvp.presenter.BindingAlipayPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.NumberFormatUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ALI_CODE;
import static com.wuxiantao.wxt.config.Constant.ALI_NAME;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BinddingAlipayActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午9:24
 * Description:${DESCRIPTION} 绑定支付宝
 */
@ContentView(R.layout.activity_binding_alipay)
public class BinddingAlipayActivity extends MvpActivity<BindingAlipayPresenter, BindingAlipayContract.IBindingView> implements View.OnKeyListener, TextWatcher, BindingAlipayContract.IBindingView {
    @ViewInject(R.id.change_alipay_rl)
    SmartRefreshLayout change_alipay_rl;
    @ViewInject(R.id.binding_alipay_type)
    TextView binding_type;

    @ViewInject(R.id.binding_alipay_account_input)
    EditText binding_account_input;
    @ViewInject(R.id.binding_alipay_name_input)
    EditText binding_name_input;

    @ViewInject(R.id.binding_alipay_confrim_banding)
    StateButton binding_confrim_banding;

    private LoadingDialog loadingDialog;
    private String alicode;
    private String aliname;

    @Override
    public void initView() {
        change_alipay_rl.setEnableRefresh(false);
        change_alipay_rl.setEnableLoadMore(false);
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.binding_ing).build();
        Bundle bundle = getBundle();
        if (bundle != null) {
            alicode = bundle.getString(ALI_CODE);
            aliname = bundle.getString(ALI_NAME);
            if (!isEmpty(alicode) && !isEmpty(aliname)) {
                binding_account_input.setText(alicode);
                binding_name_input.setText(aliname);
                binding_account_input.setSelection(alicode.length());
                binding_type.setText(R.string.alipay_change);
            }
        } else {
            binding_type.setText(R.string.alipay_bangding);
        }
        binding_account_input.setOnKeyListener(this);
        binding_name_input.setOnKeyListener(this);
        binding_account_input.addTextChangedListener(this);
        binding_name_input.addTextChangedListener(this);
        setOnClikListener(binding_confrim_banding);
    }

    @Override
    public void afterTextChanged(Editable s) {
        int accountLen = binding_account_input.length();
        int nameLen = binding_name_input.length();
        binding_confrim_banding.setEnabled(accountLen > 0 && nameLen > 0);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            int accountLen = getEdtText(binding_account_input).length();
            int nameLen = getEdtText(binding_name_input).length();
            binding_confrim_banding.setEnabled(accountLen > 0 && nameLen > 0);
        }
        return false;
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.binding_alipay_confrim_banding) {
            alicode = getEdtText(binding_account_input);
            aliname = getEdtText(binding_name_input);
            if (NumberFormatUtils.isVerificationNo(alicode)) {
                Map<String, Object> maps = new HashMap<>();
                maps.put(TOKEN, getAppToken());
                maps.put("aliname", aliname);
                maps.put("alicode", alicode);
                mPresenter.modifyPersonal(maps);
            } else {
                showNumberError();
            }
        }
    }

    private void showNumberError() {
        showDialog(getString(R.string.number_error), (dialog, which) -> binding_account_input.setText(""));
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).build();
    }

    @Override
    protected BindingAlipayPresenter CreatePresenter() {
        return new BindingAlipayPresenter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void modifySuccess(String msg) {
        if (!isEmpty(alicode) && !isEmpty(aliname)) {
            Bundle bundle = new Bundle();
            bundle.putString(ALI_CODE, alicode);
            bundle.putString(ALI_NAME, aliname);
            getResult(RESULT_CODE_BINDING_ALIPAY, bundle);
            ToastUtils.showToast(msg);
        }
    }

    @Override
    public void modifyFailure(String failure) {
        showOnlyConfirmDialog(failure);
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
