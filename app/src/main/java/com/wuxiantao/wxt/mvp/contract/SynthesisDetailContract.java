package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.CompositeDragonBean;
import com.wuxiantao.wxt.bean.CompositeScrapBean;
import com.wuxiantao.wxt.bean.DragonInfoBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SynthesisDetailContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:27
 * Description:${DESCRIPTION}
 */
public interface SynthesisDetailContract {

    interface ISynthesisDetailView extends MvpView{
        void getDragonInfoSuccess(DragonInfoBean bean);
        void getDragonInfoFailure(String failure);
        void onCompositeScrapSuccess(CompositeScrapBean bean);
        void onCompositeScrapFailure(String failure);
        void onCompositeDragonSuccess(CompositeDragonBean bean);
        void onCompositeDragonFailure(String failure);
    }

    interface ISynthesisDetailPresenter extends MvpPresenter<ISynthesisDetailView>{
        void getDragonInfo(String token);
        void onCompositeScrap(String token);
        void onCompositeDragon(String token);
    }
}
