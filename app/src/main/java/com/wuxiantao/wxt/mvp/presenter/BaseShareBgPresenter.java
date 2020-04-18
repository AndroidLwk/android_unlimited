package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.mvp.model.ShareBgModel;
import com.wuxiantao.wxt.mvp.share.ShareBgView;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseShareBgPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:39
 * Description:${DESCRIPTION}
 */
public class BaseShareBgPresenter<V extends ShareBgView> extends BasePresenter<V> {

    private V view;

    protected void getShareBg(ShareBgModel model, int page, int pageSize){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ShareBackGroundBean> observer = new BaseObserver<ShareBackGroundBean>() {
            @Override
            public void onSuccess(ShareBackGroundBean bean) {
                view.getShareBgSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getShareBgFailure(errorMsg);
            }
        };
        model.getShareBackGround(observer,page,pageSize);
    }
}
