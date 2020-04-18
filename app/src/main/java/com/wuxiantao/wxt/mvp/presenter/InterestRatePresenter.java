package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.InterestRateBean;
import com.wuxiantao.wxt.mvp.contract.InterestRateContract;
import com.wuxiantao.wxt.mvp.model.InterestRateModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InterestRatePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-9 上午10:30
 * Description:${DESCRIPTION}
 */
public class InterestRatePresenter extends BasePresenter<InterestRateContract.IRateView> implements InterestRateContract.IRatePresenter {

    private InterestRateModel model = new InterestRateModel();
    private InterestRateContract.IRateView view;


    @Override
    public void obtainRate(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<InterestRateBean> observer = new BaseObserver<InterestRateBean>(view) {
            @Override
            public void onSuccess(InterestRateBean bean) {
                view.obtainRateSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainRateFailure(errorMsg);
            }
        };
        model.obtainRate(observer,token);
    }
}
