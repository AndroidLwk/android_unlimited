package com.wuxiantao.wxt.pay;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 18:38 8-19
 * Description: ${DESCRIPTION}  封装个接口，用于返回支付状态的
 * Author: Administrator Shiming-Shi
 */

public interface PayResultListener {

     void onPaySuccess();

     void onPayError();

     void onPayCancel();
}
