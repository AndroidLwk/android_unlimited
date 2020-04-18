package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.NetSwitchBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.mvp.contract.MenuContract;
import com.wuxiantao.wxt.mvp.model.InfomationModel;
import com.wuxiantao.wxt.mvp.version.BaseVersionPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MenuPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-13 上午10:55
 * Description:${DESCRIPTION}
 */
public class MenuPresenter extends BaseVersionPresenter<MenuContract.IMenuView> implements MenuContract.IMenuPresenter {

    private MenuContract.IMenuView view;
    private InfomationModel model = new InfomationModel();

    @Override
    public void onStartApp(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List<String>> observer = new BaseObserver<List<String>>() {
            @Override
            public void onSuccess(List<String> list) {
                view.onStartAppSuccess("");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onStartAppFailure(errorMsg);
            }
        };
        model.onStartApp(observer,token);
    }

    @Override
    public void onStopApp(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List<String>> observer = new BaseObserver<List<String>>() {
            @Override
            public void onSuccess(List<String> msg) {
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
    public void getSwitchType(String url) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<NetSwitchBean> observer = new BaseObserver<NetSwitchBean>() {
            @Override
            public void onSuccess(NetSwitchBean bean) {
                 view.getSwitchTypeSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getSwitchTypeFailure(errorMsg);
            }
        };
        model.getSwitchType(observer,url);
    }

    @Override
    public void getAppCurrentVersion() {
        super.getAppCurrentVersion(model);
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
