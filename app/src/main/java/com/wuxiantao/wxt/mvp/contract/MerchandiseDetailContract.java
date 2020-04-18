package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.MerchandiseDetailBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MerchandiseDetailContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午9:34
 * Description:${DESCRIPTION}
 */
public interface MerchandiseDetailContract {

    interface IDetailView extends MvpView{
        void getProductDetailSuccess(MerchandiseDetailBean bean);
        void getProductDetailFailure(String failure);
    }

    interface IDetailPresenter extends MvpPresenter<IDetailView>{
        void getProductDetail(long id);
    }
}
