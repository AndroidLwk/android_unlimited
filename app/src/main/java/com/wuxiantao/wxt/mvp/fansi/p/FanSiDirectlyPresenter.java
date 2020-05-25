package com.wuxiantao.wxt.mvp.fansi.p;

import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiDirectlyContract;
import com.wuxiantao.wxt.mvp.fansi.m.FanSiDirectlyModel;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:26
 * Description:${DESCRIPTION} 好友列表
 */
public class FanSiDirectlyPresenter extends BasePresenter<FansiDirectlyContract.IFansiView> {

    private FansiDirectlyContract.IFansiView view;
    private FanSiDirectlyModel model = new FanSiDirectlyModel();

    /**
     * 好友列表
     *
     * @param parameters
     */
    public void obtainFansi(Map<String, Object> parameters) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<FansiDirectlyBean> observer = new BaseObserver<FansiDirectlyBean>(view) {
            @Override
            public void onSuccess(FansiDirectlyBean bean) {
                view.obtainFansSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainFansFailure(errorMsg);
            }
        };
        model.obtainDirectlyFansi(observer, parameters);
    }
}
