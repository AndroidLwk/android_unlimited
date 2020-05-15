package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.mvp.contract.MyInvitationContract;
import com.wuxiantao.wxt.mvp.model.MyInvitationCodeModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

public class MyInvitationPresenter extends BasePresenter<MyInvitationContract> {
    private MyInvitationCodeModel model = new MyInvitationCodeModel();
    private MyInvitationContract view;

    /**
     * 分享二维码图片
     */
    public void getSharePic(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<SharePicBean> observer = new BaseObserver<SharePicBean>() {
            @Override
            public void onSuccess(SharePicBean bean) {
                view.showShareCode(bean);

            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getSharePic(observer, token);
    }
}
