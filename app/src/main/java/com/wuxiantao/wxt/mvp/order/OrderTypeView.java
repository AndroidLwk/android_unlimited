package com.wuxiantao.wxt.mvp.order;

import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderTypeView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-26 上午10:48
 * Description:${DESCRIPTION}
 */
public interface OrderTypeView extends MvpView {

    void getOrderTypeSuccess(OrderTypeBean bean);
    void getOrderTypeFailure(String failure);
}
