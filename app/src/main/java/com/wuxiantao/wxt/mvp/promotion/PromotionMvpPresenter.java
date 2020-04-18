package com.wuxiantao.wxt.mvp.promotion;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PromotionMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午1:47
 * Description:${DESCRIPTION}
 */
public interface PromotionMvpPresenter<V extends PromotionView> extends MvpPresenter<V> {

    void getPromotionLanguage(int type);
}
