package com.wuxiantao.wxt.mvp.banner;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BannerMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午3:13
 * Description:${DESCRIPTION}
 */
public interface BannerMvpPresenter<V extends BannerView> extends MvpPresenter<V> {


    void gainBanner(int type);
}
