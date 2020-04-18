package com.wuxiantao.wxt.mvp.version;

import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseVersionPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午1:20
 * Description:${DESCRIPTION}
 */
public class BaseVersionPresenter<V extends VersionView> extends BasePresenter<V> {

    private V view;

    protected void getAppCurrentVersion(VersionModel model){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<CurrentVersionBean> observer = new BaseObserver<CurrentVersionBean>() {
            @Override
            public void onSuccess(CurrentVersionBean bean) {
                view.getAppCurrentVersionSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getAppCurrentVersionFailure(errorMsg);
            }
        };
        model.getAppCurrentVersion(observer);
    }
}
