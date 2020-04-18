package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;
import com.wuxiantao.wxt.mvp.banner.BannerMvpPresenter;
import com.wuxiantao.wxt.mvp.banner.BannerView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午8:03
 * Description:${DESCRIPTION}
 */
public interface TaoBaoFeaturedContract {

    interface IFeaturedView extends BannerView {
        void getTaoBaoHomeSuccess(TaoBaoHomeBean bean);
        void getTaoBaoHomeFailure(String failure);
        void getSelfEmployedSuccess(SelfEmployedBean bean);
        void getSelfEmployedFailure(String failure);
    }

    interface IFeaturedPresenter extends BannerMvpPresenter<IFeaturedView> {
        void getTaoBaoHome(int page);
        void getSelfEmployed();
    }


}
