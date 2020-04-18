package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.share.ShareBgView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareBgMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:36
 * Description:${DESCRIPTION}
 */
public interface ShareBgMvpPresenter<V extends ShareBgView> extends MvpPresenter<V>  {

    void getShareBg(int page,int pageSize);
}
