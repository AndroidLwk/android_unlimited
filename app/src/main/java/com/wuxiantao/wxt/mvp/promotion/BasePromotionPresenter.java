package com.wuxiantao.wxt.mvp.promotion;

import com.wuxiantao.wxt.bean.PromotionLanguageBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePromotionPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午1:51
 * Description:${DESCRIPTION}
 */
public class BasePromotionPresenter<V extends PromotionView> extends BasePresenter<V> {

    private V v;
    private PromotionModel model = new PromotionModel();

    protected void getPromotionLanguage(int type){
        if (v == null){
            v = getMvpView();
        }
        BaseObserver<PromotionLanguageBean> observer = new BaseObserver<PromotionLanguageBean>() {
            @Override
            public void onSuccess(PromotionLanguageBean bean) {
                v.getLanguageSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                v.getLanguageFailure(errorMsg);
            }
        };
        model.getPromotionLanguage(observer,type);
    }
}
