package com.wuxiantao.wxt.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.pay.PayListener;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/27 0027 16:58 8-19
 * Description: ${DESCRIPTION} 微信支付回调界面
 * Author: Administrator Shiming-Shi
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = BaseApplication.getInstance().api;
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType();
        //微信支付
        if (type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                //成功
                case 0:
                    PayListener.getInstance().addSuccess();
                    break;
                //失败
                case -1:
                    PayListener.getInstance().addError();
                    break;
                //用户取消
                case -2:
                    PayListener.getInstance().addCancel();
                    break;
            }
        }
        finish();
    }


}
