package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.SettingPassWordContract;
import com.wuxiantao.wxt.mvp.model.SettingPassWordModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class SettingPassWordPresenter extends BasePresenter<SettingPassWordContract.ISettingView> implements SettingPassWordContract.ISettingPresenter {

    private SettingPassWordContract.ISettingView view;
    private SettingPassWordModel model = new SettingPassWordModel();

    //设置用户密码
    @Override
    public void setUserLoginPassWord(String token,String password_old, String password_new) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List s) {
                view.setPassWordsSuccess(RESOURCES.getString(R.string.password_set_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.setPassWordsFailure(errorMsg);
            }
        };
        model.setUserLoginPassWord(token,observer,password_old,password_new);
    }


}
