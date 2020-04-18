package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.PhoneLoginBean;

public interface TestContract  {
    interface ITestView extends LoginContract.ILoginView {
        void showUi(PhoneLoginBean response);
        void error_login(String msg);
    }

    interface ITestPrensenter {
        void getData();
    }

}
