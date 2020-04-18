package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.mvp.contract.TaoBaoOrderListContract;
import com.wuxiantao.wxt.mvp.model.TaoBaoOrderListModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:46
 * Description:${DESCRIPTION}
 */
public class TaoBaoOrderListPresenter extends BasePresenter<TaoBaoOrderListContract.IOrderListView> implements TaoBaoOrderListContract.IOrderListPresenter {

    private TaoBaoOrderListContract.IOrderListView view;
    private TaoBaoOrderListModel model = new TaoBaoOrderListModel();

    @Override
    public void getTaoBaoOrderList(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TaoBaoOrderListBean> observer = new BaseObserver<TaoBaoOrderListBean>() {
            @Override
            public void onSuccess(TaoBaoOrderListBean baoOrderListBean) {
                   view.getTaoBaoOrderListSuccess(baoOrderListBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                 view.getTaoBaoOrderListFailure(errorMsg);
            }
        };
        model.getTaoBaoOrderList(observer,parameters);
    }
}
