package com.wuxiantao.wxt.mvp.banner;

import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BannerView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午2:38
 * Description:${DESCRIPTION}
 */
public interface BannerView extends MvpView {

    void gainBannerSuccess(BannerBean bean);
    void gainBannerFailure(String failure);

}
