package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MerchandiseDetailBean;
import com.wuxiantao.wxt.mvp.contract.MerchandiseDetailContract;
import com.wuxiantao.wxt.mvp.model.MerchandiseDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MerchandiseDetailPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午9:36
 * Description:${DESCRIPTION}
 */
public class MerchandiseDetailPresenter extends BasePresenter<MerchandiseDetailContract.IDetailView> implements MerchandiseDetailContract.IDetailPresenter {

    private MerchandiseDetailContract.IDetailView view;
    private MerchandiseDetailModel model = new MerchandiseDetailModel();

    @Override
    public void getProductDetail(long id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<MerchandiseDetailBean> observer = new BaseObserver<MerchandiseDetailBean>(view) {
            @Override
            public void onSuccess(MerchandiseDetailBean bean) {
                view.getProductDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getProductDetailFailure(errorMsg);
            }
        };
        model.getProductDetail(observer,id);
    }

}
