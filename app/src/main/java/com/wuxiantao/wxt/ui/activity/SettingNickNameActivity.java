package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.SettingNickNameContract;
import com.wuxiantao.wxt.mvp.presenter.SettingNamePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.edittext.filter.MaxTextLengthFilter;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_UPDATE_NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SettingNickNameActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午8:33
 * Description:${DESCRIPTION} 设置昵称
 */
@ContentView(R.layout.activity_setting_nick_name)
public class SettingNickNameActivity extends MvpActivity<SettingNamePresenter, SettingNickNameContract.ISettingView> implements SettingNickNameContract.ISettingView {
    @ViewInject(R.id.setting_nick_name_rl)
    SmartRefreshLayout setting_nick_name_rl;
    @ViewInject(R.id.setting_nick_name_back)
    RelativeLayout setting_nick_name_back;
    @ViewInject(R.id.setting_nick_name_title)
    TextView setting_nick_name_title;
    @ViewInject(R.id.setting_nick_name_edt)
    MaterialEditText mEdtext;
    @ViewInject(R.id.setting_nick_name_save)
    StateButton save;

    private String nickName;
    private LoadingDialog loadingDialog;

    @Override
    protected SettingNamePresenter CreatePresenter() {
        return new SettingNamePresenter();
    }

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        setting_nick_name_rl.setEnableRefresh(false);
        setting_nick_name_rl.setEnableLoadMore(false);
        Bundle bundle = getBundle();
        if (bundle != null){
            nickName = bundle.getString(NICK_NAME);
            if (!isEmpty(nickName)){
                mEdtext.setText(nickName);
                mEdtext.setSelection(nickName.length());
                setting_nick_name_title.setText(R.string.change_nick_name);
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.setting_update_nickname).build();
            }else {
                setting_nick_name_title.setText(R.string.setting_nick_name);
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.setting_nickname).build();
            }
        }else {
            setting_nick_name_title.setText(R.string.setting_nick_name);
        }
        setOnClikListener(save,setting_nick_name_back);
        mEdtext.setFilters(new InputFilter[]{new MaxTextLengthFilter(16)});
        mEdtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                save.setEnabled(len > 0);
            }
        });
        mEdtext.setOnKeyListener((v, keyCode, event) -> {
            int len = getEdtText(mEdtext).length();
            save.setEnabled(len > 0);
            return false;
        });
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.setting_nick_name_back:
                finish();
                break;
            case R.id.setting_nick_name_save:
                nickName = getEdtText(mEdtext);
                Map<String,Object> map = new HashMap<>();
                map.put(TOKEN,getAppToken());
                map.put("nickname",nickName);
                mPresenter.modifyPersonal(map);
                break;
        }
    }


    @Override
    public void modifySuccess(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString(NICK_NAME,nickName);
        getResult(RESULT_CODE_UPDATE_NICK_NAME,bundle);
    }

    @Override
    public void modifyFailure(String failure) {
        ToastUtils.info(failure);
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
