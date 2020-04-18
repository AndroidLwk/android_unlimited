package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.CommodityInfoBean;
import com.wuxiantao.wxt.mvp.contract.CommodityInfoContract;
import com.wuxiantao.wxt.mvp.model.CommodityInfoModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午5:03
 * Description:${DESCRIPTION}
 */
public class CommodityInfoPresenter extends BasePresenter<CommodityInfoContract.ICommodityInfoView> implements CommodityInfoContract.ICommodityInfoPresenter {

    private CommodityInfoContract.ICommodityInfoView view;
    private CommodityInfoModel model = new CommodityInfoModel();

    @Override
    public void obtainInfo(int good_id) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CommodityInfoBean> observer = new BaseObserver<CommodityInfoBean>(view) {
            @Override
            public void onSuccess(CommodityInfoBean commodityInfoBean) {
                 view.obtainInfoSuccess(commodityInfoBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainInfoFailure(errorMsg);
            }
        };
        model.obtainCommodityInfo(observer,good_id);
    }



}
