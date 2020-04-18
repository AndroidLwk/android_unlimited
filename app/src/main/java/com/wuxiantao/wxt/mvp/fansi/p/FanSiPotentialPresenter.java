package com.wuxiantao.wxt.mvp.fansi.p;

import com.wuxiantao.wxt.bean.FanSiPotentialBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiPotentialContract;
import com.wuxiantao.wxt.mvp.fansi.m.FanSiPotentialModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:26
 * Description:${DESCRIPTION} 潜在粉丝
 */
public class FanSiPotentialPresenter extends FansiPresenter<FansiPotentialContract.IFansiView> implements FansiPotentialContract.IFansiPresenter {

    private FansiPotentialContract.IFansiView view;
    private FanSiPotentialModel model = new FanSiPotentialModel();

    @Override
    public void obtainFansi(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<FanSiPotentialBean> observer = new BaseObserver<FanSiPotentialBean>(view) {
            @Override
            public void onSuccess(FanSiPotentialBean bean) {
                view.obtainFansSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainFansFailure(errorMsg);
            }
        };
        model.obtainFansi(observer,parameters);
    }


    @Override
    public void obtainFansiDetail(int uid) {
        super.obtainFansiDetail(model,uid);
    }
}
