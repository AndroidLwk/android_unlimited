package com.wuxiantao.wxt.mvp.fansi.p;

import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.FansiIndirectBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiIndirectContract;
import com.wuxiantao.wxt.mvp.fansi.m.FanSiIndirectModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:26
 * Description:${DESCRIPTION} 间接粉丝
 */
public class FanSiIndirectPresenter extends FansiPresenter<FansiIndirectContract.IFansiView> implements FansiIndirectContract.IFansiPresenter {

    private FansiIndirectContract.IFansiView view;
    private FanSiIndirectModel model = new FanSiIndirectModel();

    @Override
    public void obtainFansi(Map<String, Object> parameters) {
        if (view == null){
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
        model.obtainInDirectlyFansi(observer,parameters);
    }


    @Override
    public void obtainFansiDetail(int uid) {
        super.obtainFansiDetail(model,uid);
    }
}
