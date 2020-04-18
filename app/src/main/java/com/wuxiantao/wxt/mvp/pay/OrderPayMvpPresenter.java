package com.wuxiantao.wxt.mvp.pay;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderPayMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:30
 * Description:${DESCRIPTION}
 */
public interface OrderPayMvpPresenter<V extends OrderPayView> extends MvpPresenter<V> {

    void onOrderCreate(Map<String,Object> parameters,int payType);
    void checkOrderStatus(String token,String order_no);
}
