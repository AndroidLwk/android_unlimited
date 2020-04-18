package com.wuxiantao.wxt.pay;

import android.app.Activity;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.config.Constant;

import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 18:46 8-19
 * Description: ${DESCRIPTION} 封装支付的工具类，以后在使用的过程中直接采用工具类即可
 * Author: Administrator Shiming-Shi
 */

public class PayManager {

    private static volatile PayManager instance;

    private Activity activity;

    private AliPayHandler mHandler;

    private PayManager(Activity activity) {
        this.activity = activity;
        mHandler = new AliPayHandler();
    }

    public static PayManager getInstance(Activity activity) {
        if (instance == null) {
            synchronized (PayManager.class) {
                if (instance == null) {
                    instance = new PayManager(activity);
                }
            }
        }
        return instance;
    }


    public <T> void pay(int payType, T t) {
        switch (payType) {
            //微信支付
            case PAY_TYPE_WX:
                toWXPay(t);
                break;
            //支付宝支付
            case PAY_TYPE_ALI:
                toAliPay(t);
                break;
        }
    }


    /**
     * 发起微信支付
     *
     * @param t
     * @param <T>
     */
    private <T> void toWXPay(T t) {
        if (t instanceof WeChatPayBean) {
            WeChatPayBean bean = (WeChatPayBean) t;
            PayReq req = new PayReq();
            //应用ID
            req.appId = Constant.WEIXIN_ID;
            //商户号
            req.partnerId = bean.getPartnerid();
            //预支付交易会话ID
            req.prepayId = bean.getPrepayid();
            //扩展字段 固定值Sign=WXPay
            req.packageValue = bean.getPackageValue();
            //随机字符串，不长于32位
            req.nonceStr = bean.getNoncestr();
            //时间戳
            req.timeStamp = String.valueOf(bean.getTimestamp());
            //签名
            req.sign = bean.getSign();

            // 调用接口发起支付
            BaseApplication.getInstance().api.sendReq(req);
        }
    }


    /**
     * 发起支付宝支付
     *
     * @param t
     * @param <T>
     */
    private <T> void toAliPay(T t) {
        String orderInfo = (String) t;
        AlipayThread alipayThread = new AlipayThread(activity, mHandler, orderInfo);
        new Thread(alipayThread).start();
    }


}
