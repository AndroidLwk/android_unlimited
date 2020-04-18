package com.wuxiantao.wxt.pay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.wuxiantao.wxt.config.Constant;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 10:37 8-19
 * Description: ${DESCRIPTION}  支付处理代码(UI线程)
 * Author: Administrator Shiming-Shi
 */

public class AliPayHandler extends Handler {


    public AliPayHandler(){

    }


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //支付宝支付
       if (msg.what== Constant.ALIPAY_FLAG){
           Map<String,String> result = (Map<String, String>) msg.obj;
           PayResult  payResult = new PayResult(result);
           //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
           // 同步返回需要验证的信息
           String resultInfo = payResult.getResult();
           String resultStatus = payResult.getResultStatus();
           // 判断resultStatus 为9000则代表支付成功
           if (TextUtils.equals(resultStatus, "9000")) {
               // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//               setDialog("支付成功");
               PayListener.getInstance().addSuccess();
           } else if(TextUtils.equals(resultStatus, "6001")){
               // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//               setDialog("取消支付");
               PayListener.getInstance().addCancel();
           }else {
//               setDialog("支付失败");
               PayListener.getInstance().addError();
           }
        }
    }

}
