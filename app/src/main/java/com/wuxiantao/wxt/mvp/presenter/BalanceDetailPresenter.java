package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.mvp.contract.BalanceDetailContract;
import com.wuxiantao.wxt.mvp.model.BalanceDetailModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class BalanceDetailPresenter extends BasePresenter<BalanceDetailContract.IDetailView> implements BalanceDetailContract.IDetailPresenter {

    private BalanceDetailModel model = new BalanceDetailModel();
    private BalanceDetailContract.IDetailView view;


    @Override
    public void obtainBalanceDetails(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<BalanceDetailBean> observer = new BaseObserver<BalanceDetailBean>(view) {
            @Override
            public void onSuccess(BalanceDetailBean bean) {
                view.obtainBalanceDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainBalanceDetailFailure(errorMsg);
            }
        };
        model.obtainBalanceDetails(observer,token,page);
    }
}
