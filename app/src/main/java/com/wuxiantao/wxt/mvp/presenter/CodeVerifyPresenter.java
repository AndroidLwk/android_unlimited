package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.ModifyPersonalBean;
import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.contract.CodeVerifyContract;
import com.wuxiantao.wxt.mvp.login.BaseLoginPresenter;
import com.wuxiantao.wxt.mvp.model.CodeVerifyModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class CodeVerifyPresenter extends BaseLoginPresenter<CodeVerifyContract.IVerifyView> implements CodeVerifyContract.IVerifyPresenter {

    private CodeVerifyContract.IVerifyView view;

    private CodeVerifyModel model = new CodeVerifyModel();

    @Override
    public void phoneRegister(String mobile, String code, String password) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<PhoneLoginBean> observer = new BaseObserver<PhoneLoginBean>(view) {
            @Override
            public void onSuccess(PhoneLoginBean bean) {
                 view.registerSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.registerFailure(errorMsg);
            }
        };
        model.phoneRegister(observer,mobile,code,password);
    }

    @Override
    public void resetPassWord(String mobile, String code,String newPassWord) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List list) {
                view.resetPassWordSuccess(RESOURCES.getString(R.string.reset_pw_success));
            }
            @Override
            public void onFailure(String errorMsg) {
                view.resetPassWordFailure(errorMsg);
            }
        };
        model.resetPassWord(observer,mobile,code,newPassWord);
    }

    @Override
    public void bindingNumber(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ModifyPersonalBean> observer = new BaseObserver<ModifyPersonalBean>(view) {
            @Override
            public void onSuccess(ModifyPersonalBean list) {
                view.bindingNumberSuccess("绑定成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.bindingNumberFailure(errorMsg);
            }
        };
        model.bindingNumber(observer,parameters);
    }


}
