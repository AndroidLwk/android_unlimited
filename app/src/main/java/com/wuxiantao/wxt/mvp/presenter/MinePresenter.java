package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MyMoneyCashBean;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.model.MyDepositModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class MinePresenter extends BasePresenter<MineContract.IMineView> {

    private MineContract.IMineView view;
    private MyDepositModel model = new MyDepositModel();

    public void myMoneyCash(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MyMoneyCashBean> observer = new BaseObserver<MyMoneyCashBean>(view) {
            @Override
            public void onSuccess(MyMoneyCashBean bean) {
                view.showMyMoneyCash(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                 view.onFailure(errorMsg);
            }
        };
         model.myMoneyCash(observer,token);
    }
}
