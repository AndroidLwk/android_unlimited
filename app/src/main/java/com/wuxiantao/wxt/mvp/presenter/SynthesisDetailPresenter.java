package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.CompositeDragonBean;
import com.wuxiantao.wxt.bean.CompositeScrapBean;
import com.wuxiantao.wxt.bean.DragonInfoBean;
import com.wuxiantao.wxt.mvp.contract.SynthesisDetailContract;
import com.wuxiantao.wxt.mvp.model.SynthesisDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SynthesisDetailPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:28
 * Description:${DESCRIPTION}
 */
public class SynthesisDetailPresenter extends BasePresenter<SynthesisDetailContract.ISynthesisDetailView> implements SynthesisDetailContract.ISynthesisDetailPresenter {

    private SynthesisDetailContract.ISynthesisDetailView view;
    private SynthesisDetailModel model = new SynthesisDetailModel();

    @Override
    public void getDragonInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<DragonInfoBean> observer = new BaseObserver<DragonInfoBean>() {
            @Override
            public void onSuccess(DragonInfoBean bean) {
                view.getDragonInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getDragonInfoFailure(errorMsg);
            }
        };
        model.getDragonInfo(observer,token);
    }

    @Override
    public void onCompositeScrap(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CompositeScrapBean> observer = new BaseObserver<CompositeScrapBean>(view) {
            @Override
            public void onSuccess(CompositeScrapBean bean) {
                view.onCompositeScrapSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onCompositeScrapFailure(errorMsg);
            }
        };
        model.onCompositeScrap(observer,token);
    }

    @Override
    public void onCompositeDragon(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CompositeDragonBean> observer = new BaseObserver<CompositeDragonBean>(view) {
            @Override
            public void onSuccess(CompositeDragonBean bean) {
                view.onCompositeDragonSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onCompositeDragonFailure(errorMsg);
            }
        };
        model.onCompositeDragon(observer,token);
    }
}
