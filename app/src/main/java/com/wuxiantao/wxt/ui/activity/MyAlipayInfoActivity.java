package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.ALI_CODE;
import static com.wuxiantao.wxt.config.Constant.ALI_NAME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_ALIPAY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyAlipayInfoActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-14 下午4:42
 * Description:${DESCRIPTION}我的支付宝信息
 */
@ContentView(R.layout.activity_myalipay_info)
public class MyAlipayInfoActivity extends BaseActivity {
    @ViewInject(R.id.myalipay_info_rl)
    SmartRefreshLayout myalipay_info_rl;
    @ViewInject(R.id.myalipay_info_account)
    TextView myalipay_info_account;
    @ViewInject(R.id.myalipay_info_name)
    TextView myalipay_info_name;
    @ViewInject(R.id.myalipay_info_change)
    StateButton myalipay_info_change;

    private String alicode;
    private String aliname;

    @Override
    public void initView() {
        myalipay_info_rl.setEnableRefresh(false);
        myalipay_info_rl.setEnableLoadMore(false);
        Bundle bundle = getBundle();
        if (bundle != null) {
            alicode = bundle.getString(ALI_CODE);
            aliname = bundle.getString(ALI_NAME);
            if (!isEmpty(alicode) && !isEmpty(aliname)) {
                myalipay_info_account.setText(NumberFormatUtils.phoneNumberSub(alicode));
                myalipay_info_name.setText(aliname);
                setOnClikListener(myalipay_info_change);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Bundle bundle = new Bundle();
            bundle.putString(ALI_CODE,alicode);
            bundle.putString(ALI_NAME,aliname);
            getResult(RESULT_CODE_BINDING_ALIPAY, bundle);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.myalipay_info_change) {
            Bundle bundle = new Bundle();
            bundle.putString(ALI_CODE, alicode);
            bundle.putString(ALI_NAME, aliname);
            $startActivityForResult(BinddingAlipayActivity.class, bundle, REQUEST_CODE_BINDING_ALIPAY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_BINDING_ALIPAY && resultCode == RESULT_CODE_BINDING_ALIPAY) {
            alicode = data.getStringExtra(ALI_CODE);
            aliname = data.getStringExtra(ALI_NAME);
            if (!isEmpty(alicode) && !isEmpty(aliname)) {
                myalipay_info_account.setText(NumberFormatUtils.phoneNumberSub(alicode));
                myalipay_info_name.setText(aliname);
            }
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString(ALI_CODE, alicode);
                    bundle.putString(ALI_NAME, aliname);
                    getResult(RESULT_CODE_BINDING_ALIPAY, bundle);
                })
                .setMiddleTitleText(getResources().getString(R.string.alypay)).build();
    }
}
