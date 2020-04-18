package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.CommodityInfoBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午4:58
 * Description:${DESCRIPTION}
 */
public interface CommodityInfoContract {

    interface ICommodityInfoView extends MvpView{
        void obtainInfoSuccess(CommodityInfoBean msg);
        void obtainInfoFailure(String failure);
    }

    interface ICommodityInfoPresenter extends MvpPresenter<ICommodityInfoView>{
        void obtainInfo(int good_id);
    }

}
