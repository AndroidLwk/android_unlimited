package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:38
 * Description:${DESCRIPTION}
 */
public interface TaoBaoOrderListContract {

    interface IOrderListView extends MvpView{
        void getTaoBaoOrderListSuccess(TaoBaoOrderListBean bean);
        void getTaoBaoOrderListFailure(String failure);
    }

    interface IOrderListPresenter extends MvpPresenter<IOrderListView>{
        void getTaoBaoOrderList(Map<String,Object> parameters);
    }

}
