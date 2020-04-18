package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_RESET_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ResetPassWordActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 上午9:18
 * Description:${DESCRIPTION} 重置登陆密码 1.输入手机号
 */
@ContentView(R.layout.activity_reset_pass_word)
public class ResetPassWordActivity extends BaseActivity {
    @ViewInject(R.id.reset_pw_input)
    MaterialEditText reset_pw_input;
    @ViewInject(R.id.reset_pw_vercode)
    StateButton reset_pw_vercode;

    @Override
    public void initView() {
        setOnClikListener(reset_pw_vercode);
        reset_pw_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reset_pw_vercode.setEnabled(s.length() > 0);
            }
        });
        reset_pw_input.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL){
                int len = getEdtText(reset_pw_input).length();
                reset_pw_vercode.setEnabled(len > 0);
            }
            return false;
        });
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.reset_pw_vercode) {
            String number = getEdtText(reset_pw_input);
            if (NumberFormatUtils.isVerificationNo(number)){
                Bundle bundle = new Bundle();
                bundle.putInt(VERCODE_TYPE,VERCODE_RESET_PASS_WORD);
                bundle.putString(NUMBER,number);
                $startActivity(ResetPassWordInputActivity.class,bundle);
            }else {
                showNumberError();
            }
        }
    }

    private void showNumberError(){
        showDialog(getString(R.string.number_error),(dialog, which) ->reset_pw_input.setText(""));
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).build();
    }
}
