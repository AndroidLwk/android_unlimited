package com.wuxiantao.wxt.mvp.order;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderTypeMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-26 上午10:49
 * Description:${DESCRIPTION}
 */
public interface OrderTypeMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void getOrderType(String token);
}
