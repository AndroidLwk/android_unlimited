package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.ResetPassWordContract;
import com.wuxiantao.wxt.mvp.presenter.ResetPassWordPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.NEW_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_RESET_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ResetPassWordActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 上午9:18
 * Description:${DESCRIPTION} 重置登陆密码 2.输入新密码
 */
@ContentView(R.layout.activity_reset_pass_word_input)
public class ResetPassWordInputActivity extends MvpActivity<ResetPassWordPresenter, ResetPassWordContract.IResetView> implements ResetPassWordContract.IResetView {

    @ViewInject(R.id.reset__pass_word__input)
    MaterialEditText reset__pass_word__input;
    @ViewInject(R.id.reset__pass_word_get_code)
    StateButton reset__pass_word_get_code;

    private String number;
    private int type;
    private LoadingDialog loadingDialog;
    private String newPassWord;

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle bundle = getBundle();
        if (bundle != null){
            type = bundle.getInt(VERCODE_TYPE);
            number = bundle.getString(NUMBER);
            setOnClikListener(reset__pass_word_get_code);
            reset__pass_word__input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int len = s.length();
                    reset__pass_word_get_code.setEnabled(len > 0);
                }
            });
            reset__pass_word__input.setOnEditorActionListener((v, actionId, event) -> {
                if (event.getAction() == KeyEvent.KEYCODE_DEL){
                    int len = getEdtText(reset__pass_word__input).length();
                    reset__pass_word_get_code.setEnabled(len > 0);
                }
                return false;
            });
        }
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.reset__pass_word_get_code) {
            newPassWord = getEdtText(reset__pass_word__input);
            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.sms_loading).build();
            mPresenter.obtainCode(number,4);
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).build();
    }

    @Override
    protected ResetPassWordPresenter CreatePresenter() {
        return new ResetPassWordPresenter();
    }

    @Override
    public void obtainSuccess(String msg) {
        if (!isEmpty(number) && !isEmpty(newPassWord) && type == VERCODE_RESET_PASS_WORD){
            Bundle bundle = new Bundle();
            bundle.putInt(VERCODE_TYPE,type);
            bundle.putString(NUMBER,number);
            bundle.putString(NEW_PASS_WORD,newPassWord);
            $startActivity(VerificationCodeActivity.class,bundle,true);
        }
    }

    @Override
    public void obtainFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> { });
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
