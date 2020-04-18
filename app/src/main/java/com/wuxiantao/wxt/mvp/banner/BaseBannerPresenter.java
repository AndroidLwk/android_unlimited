package com.wuxiantao.wxt.mvp.banner;

import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseBannerPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午3:16
 * Description:${DESCRIPTION}
 */
public class BaseBannerPresenter<V extends BannerView> extends BasePresenter<V> {

    private V mView;

    public void gainBanner(BannerModel model,int type) {
        if (mView == null){
            mView = getMvpView();
        }
        BaseObserver<BannerBean> observer = new BaseObserver<BannerBean>(mView) {
            @Override
            public void onSuccess(BannerBean response) {
                mView.gainBannerSuccess(response);
            }

            @Override
            public void onFailure(String errorMsg) {
                mView.gainBannerFailure(errorMsg);
            }
        };
        model.gainBanner(observer,type);
    }


}
