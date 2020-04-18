package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.SelfOrderListBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayMvpPresenter;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:38
 * Description:${DESCRIPTION}
 */
public interface SelfOrderListContract {

    interface IOrderListView extends OrderPayView {
        void getSelfOrderListSuccess(SelfOrderListBean bean);
        void getSelfOrderListFailure(String failure);
    }

    interface IOrderListPresenter extends OrderPayMvpPresenter<IOrderListView> {
        void getSelfOrderList(Map<String, Object> parameters);
    }

}
