package com.wuxiantao.wxt.mvp.pay;

import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.pay.PayResultListener;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderPayView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:29
 * Description:${DESCRIPTION}
 */
public interface OrderPayView extends MvpView, PayResultListener {

    void onWeChatPay(WeChatPayBean infoBean);
    void onAliPay(String order_id,String res);

    void onOrderCreateFailure(String failure);

    void onOrderPaySuccess(String msg);
    void onOrderPayFailure(String failure);
}
