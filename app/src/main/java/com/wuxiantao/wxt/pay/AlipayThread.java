package com.wuxiantao.wxt.pay;

import android.app.Activity;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.wuxiantao.wxt.config.Constant;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 15:57 8-19
 * Description: ${DESCRIPTION} 支付宝支付
 * Author: Administrator Shiming-Shi
 */

public class AlipayThread implements Runnable{

    //支付订单信息  app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。
    String  orderInfo;

    /*用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，
      这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，直到唤起H5支付页面或者唤起外
      部的钱包付款页面loading才消失。（建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）*/
//    boolean isShowPayLoading;

    private Activity activity;

    private AliPayHandler mHandler;

    protected  AlipayThread(Activity activity,AliPayHandler mHandler,String  orderInfo){
            this.activity = activity;
            this.mHandler = mHandler;
            this.orderInfo = orderInfo;
    }

    @Override
    public void run() {
        apliPay();
    }



    private void apliPay(){
        //调用支付接口
        PayTask alipay = new PayTask(activity);
        //调起支付宝支付页面
        Map<String,String> result = alipay.payV2(orderInfo,true);
        //发送支付结果
        Message msg = Message.obtain();
        msg.what= Constant.ALIPAY_FLAG;
        msg.obj = result;
        mHandler.sendMessage(msg);
    }















}
