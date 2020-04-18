package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.TaoBaoOrderDetailBean;
import com.wuxiantao.wxt.bean.YouXuanOrderDetailBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderDetailsContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:28
 * Description:${DESCRIPTION}
 */
public interface OrderDetailsContract {

    interface IOrderDetailsView extends MvpView{
        void getYouXuanOrderDetailSuccess(YouXuanOrderDetailBean bean);
        void getYouXuanOrderDetailFailure(String failure);
        void getTaoBaoOrderDetailSuccess(TaoBaoOrderDetailBean bean);
        void getTaoBaoOrderDetailFailure(String failure);
    }

    interface IOrderDetailsPresenter extends MvpPresenter<IOrderDetailsView>{
        void getYouXuanOrderDetail(String token,int order_id);
        void getTaoBaoOrderDetail(String token,int order_id);
    }
}
