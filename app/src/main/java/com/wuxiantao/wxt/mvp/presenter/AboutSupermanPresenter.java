package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.StopAppBean;
import com.wuxiantao.wxt.mvp.contract.AboutSupermanContract;
import com.wuxiantao.wxt.mvp.model.AboutSupermanModel;
import com.wuxiantao.wxt.mvp.model.InfomationModel;
import com.wuxiantao.wxt.mvp.version.BaseVersionPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutSupermanPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 上午10:52
 * Description:${DESCRIPTION}
 */
public class AboutSupermanPresenter extends BaseVersionPresenter<AboutSupermanContract.IAboutSupermanView> implements AboutSupermanContract.IAboutSupermanPresenter {
    private InfomationModel model = new InfomationModel();
    private AboutSupermanContract.IAboutSupermanView view;
    @Override
    public void getAppCurrentVersion() {
        super.getAppCurrentVersion(new AboutSupermanModel());
    }

    public void onStopApp(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<StopAppBean> observer = new BaseObserver<StopAppBean>() {
            @Override
            public void onSuccess(StopAppBean msg) {
                view.onStopAppSuccess("");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onStopAppFailure(errorMsg);
            }
        };
        model.onStopApp(observer,token);
    }

    @Override
    public void getPersonalInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<PersonalInfoBean> observer = new BaseObserver<PersonalInfoBean>() {
            @Override
            public void onSuccess(PersonalInfoBean bean) {
                if (bean != null){
                    view.getPersonalInfoSuccess(bean);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getPersonalInfoFailure(errorMsg);
            }
        };
        model.obtainPersonal(observer,token);
    }
}
