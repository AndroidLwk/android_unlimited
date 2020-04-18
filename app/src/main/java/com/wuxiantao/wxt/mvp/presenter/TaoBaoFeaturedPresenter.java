package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;
import com.wuxiantao.wxt.mvp.banner.BannerModel;
import com.wuxiantao.wxt.mvp.banner.BaseBannerPresenter;
import com.wuxiantao.wxt.mvp.contract.TaoBaoFeaturedContract;
import com.wuxiantao.wxt.mvp.model.TaoBaoFeaturedModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午8:04
 * Description:${DESCRIPTION}
 */
public class TaoBaoFeaturedPresenter extends BaseBannerPresenter<TaoBaoFeaturedContract.IFeaturedView> implements TaoBaoFeaturedContract.IFeaturedPresenter{

    private TaoBaoFeaturedContract.IFeaturedView view;
    private TaoBaoFeaturedModel model = new TaoBaoFeaturedModel();

    @Override
    public void getTaoBaoHome(int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TaoBaoHomeBean> observer = new BaseObserver<TaoBaoHomeBean>() {
            @Override
            public void onSuccess(TaoBaoHomeBean bean) {
                view.getTaoBaoHomeSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getTaoBaoHomeFailure(errorMsg);
            }
        };
        model.getTaoBaoHome(observer,page);
    }

    @Override
    public void getSelfEmployed() {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<SelfEmployedBean> observer = new BaseObserver<SelfEmployedBean>() {
            @Override
            public void onSuccess(SelfEmployedBean bean) {
                view.getSelfEmployedSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getSelfEmployedFailure(errorMsg);
            }
        };
        model.getSelfEmployed(observer);
    }


    @Override
    public void gainBanner(int type) {
        super.gainBanner(new BannerModel(),type);
    }
}
