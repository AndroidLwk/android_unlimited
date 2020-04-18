package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.BalanceBean;
import com.wuxiantao.wxt.mvp.contract.BalanceContract;
import com.wuxiantao.wxt.mvp.model.BalanceModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class BalancePresenter extends BasePresenter<BalanceContract.IBalanceView> implements BalanceContract.IBalancePresenter {

    private BalanceContract.IBalanceView view;
    private BalanceModel model = new BalanceModel();

    @Override
    public void obtainBalance(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<BalanceBean> observer = new BaseObserver<BalanceBean>(view) {
            @Override
            public void onSuccess(BalanceBean bean) {
                 view.obtainBalanceSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainBalanceFailure(errorMsg);
            }
        };
        model.obtainBalance(observer,token);
    }
}
