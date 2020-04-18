package com.wuxiantao.wxt.mvp.fansi.p;

import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.mvp.fansi.m.BaseFansiModel;
import com.wuxiantao.wxt.mvp.fansi.view.FansiView;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:34
 * Description:${DESCRIPTION}
 */
public class FansiPresenter<V extends FansiView> extends BasePresenter<V> {

    private V view;

    protected void obtainFansiDetail(BaseFansiModel model,int uid) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<FansiDetailBean> observer = new BaseObserver<FansiDetailBean>() {
            @Override
            public void onSuccess(FansiDetailBean bean) {
                view.obtainFansiDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainFansiDetailFailure(errorMsg);
            }
        };
        model.obtainFansiDetail(observer,uid);
    }


}
