package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.contract.TestContract;
import com.wuxiantao.wxt.mvp.model.LoginModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

public class TestPrensenter extends LoginPresenter implements TestContract.ITestPrensenter  {
    private  TestContract.ITestView view;
    private LoginModel mModel = new LoginModel();
    @Override
    public void getData() {
        if (view == null){
            view = (TestContract.ITestView) getMvpView();
        }
        BaseObserver<PhoneLoginBean> observer = new BaseObserver<PhoneLoginBean>(view) {
            @Override
            public void onSuccess(PhoneLoginBean response) {
                view.loginSuccess(response);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.loginFailure(errorMsg);
            }
        };
        mModel.login(observer,"19130697963","123456",1);
    }
}
