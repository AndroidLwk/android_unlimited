package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.ShareAdBean;
import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

public interface MyInvitationContract extends MvpView {
    void showShareCode(SharePicBean info);
    void onFailure(String msg);


    void shareSucceed(ShareAdBean bean);
    void onShareFailure(String msg);
}
