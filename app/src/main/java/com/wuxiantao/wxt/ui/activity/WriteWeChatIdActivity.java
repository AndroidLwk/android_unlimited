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

import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_WECHAT_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.WECHAT_NO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SettingNickNameActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午8:33
 * Description:${DESCRIPTION} 填写微信号/修改微信号
 */
@ContentView(R.layout.activity_write_wechatid)
public class WriteWeChatIdActivity extends MvpActivity<SettingNamePresenter, SettingNickNameContract.ISettingView> implements SettingNickNameContract.ISettingView {
    @ViewInject(R.id.write_wechatid_rl)
    SmartRefreshLayout write_wechatid_rl;
    @ViewInject(R.id.write_wechatid_name_back)
    RelativeLayout write_wechatid_name_back;
    @ViewInject(R.id.write_wechatid_name_title)
    TextView write_wechatid_name_title;
    @ViewInject(R.id.write_wechatid_edt)
    MaterialEditText mEdtext;
    @ViewInject(R.id.write_wechatid_save)
    StateButton save;

    private String wechat_id;
    private LoadingDialog loadingDialog;

    @Override
    protected SettingNamePresenter CreatePresenter() {
        return new SettingNamePresenter();
    }

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        write_wechatid_rl.setEnableRefresh(false);
        write_wechatid_rl.setEnableLoadMore(false);
        Bundle bundle = getBundle();
        if (bundle != null){
            wechat_id = bundle.getString(WECHAT_NO);
            if (!isEmpty(wechat_id)){
                mEdtext.setText(wechat_id);
                mEdtext.setSelection(wechat_id.length());
                write_wechatid_name_title.setText(R.string.change_wechat_id);

            }else {
                write_wechatid_name_title.setText(R.string.setting_wechat_id);
            }
        }else {
            write_wechatid_name_title.setText(R.string.setting_wechat_id);
        }
        loadingDialog = new LoadingDialog.Build(this).build();
        setOnClikListener(save,write_wechatid_name_back);
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
            case R.id.write_wechatid_name_back:
                finish();
                break;
            case R.id.write_wechatid_save:
                wechat_id = getEdtText(mEdtext);
                Map<String,Object> map = new HashMap<>();
                map.put(TOKEN,getAppToken());
                map.put("wechat",wechat_id);
                mPresenter.modifyPersonal(map);
                break;
        }
    }


    @Override
    public void modifySuccess(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString(WECHAT_NO,wechat_id);
        getResult(RESULT_CODE_WECHAT_ID,bundle);
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
