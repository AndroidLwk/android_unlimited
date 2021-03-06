package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.FansiIndirectBean;
import com.wuxiantao.wxt.mvp.contract.MineFansiContract;
import com.wuxiantao.wxt.mvp.model.MineFansiModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class MineFansiPresenter extends BasePresenter<MineFansiContract.IFansiView> implements MineFansiContract.IFansiPresenter {

    private MineFansiContract.IFansiView view;
    private MineFansiModel model = new MineFansiModel();

    @Override
    public void obtainFansiHeadInfo(String token) {
//        if (view == null){
//            view = getMvpView();
//        }
//        BaseObserver<MyFansiHeadInfoBean> observer = new BaseObserver<MyFansiHeadInfoBean>(view) {
//            @Override
//            public void onSuccess(MyFansiHeadInfoBean bean) {
//                view.obtainFansiHeadInfoSuccess(bean);
//            }
//
//            @Override
//            public void onFailure(String errorMsg) {
//                //view.obtainFansiHeadInfoFailure(errorMsg);
//            }
//        };
//        model.obtainFansiHeadInfo(observer,token);

    }

    @Override
    public void obtainDirectlyFansi(Map<String, Object> parameters){
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
            }
        };
        model.obtainDirectlyFansi(observer,parameters);
    }

}
