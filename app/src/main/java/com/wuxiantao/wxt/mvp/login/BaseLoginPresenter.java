package com.wuxiantao.wxt.mvp.login;

import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.model.LoginModel;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseLoginPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-27 上午11:42
 * Description:${DESCRIPTION}
 */
public class BaseLoginPresenter<V extends LoginView> extends BasePresenter<V> {

    private V mView;
    private LoginModel mModel = new LoginModel();

    public void login(String mobile,String para,int type) {
        if (mView == null){
            mView = getMvpView();
        }
        BaseObserver<PhoneLoginBean> observer = new BaseObserver<PhoneLoginBean>(mView) {
            @Override
            public void onSuccess(PhoneLoginBean response) {
                mView.loginSuccess(response);
            }

            @Override
            public void onFailure(String errorMsg) {
                mView.loginFailure(errorMsg);
            }
        };
        mModel.login(observer,mobile,para,type);
    }



}
