package com.wuxiantao.wxt.mvp.share;

import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareBgView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:34
 * Description:${DESCRIPTION}
 */
public interface ShareBgView  extends MvpView {

    void getShareBgSuccess(ShareBackGroundBean bean);
    void getShareBgFailure(String failure);
}
