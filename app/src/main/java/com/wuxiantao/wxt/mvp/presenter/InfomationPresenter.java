package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.ModifyPersonalBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.StopAppBean;
import com.wuxiantao.wxt.bean.TaoBaoLoginBean;
import com.wuxiantao.wxt.mvp.contract.MyInfomationContract;
import com.wuxiantao.wxt.mvp.model.InfomationModel;
import com.wuxiantao.wxt.mvp.model.TaobaoLoginModel;
import com.wuxiantao.wxt.mvp.upload.UpLoadFilePresenter;
import com.wuxiantao.wxt.mvp.user.m.ModifyModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.io.File;
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
public class InfomationPresenter extends UpLoadFilePresenter<MyInfomationContract.IInfomationView> implements MyInfomationContract.IInfomationPresenter {

    private InfomationModel model = new InfomationModel();
    private MyInfomationContract.IInfomationView view;
    private ModifyModel modifyModel = new ModifyModel();
    private TaobaoLoginModel loginModel = new TaobaoLoginModel();

    @Override
    public void obtainPersonal(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<PersonalInfoBean> observer = new BaseObserver<PersonalInfoBean>(view) {
            @Override
            public void onSuccess(PersonalInfoBean bean) {
                if (bean != null){
                    view.obtainPersonalSuccess(bean);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                view.obtainPersonalFailure(errorMsg);
            }
        };
        model.obtainPersonal(observer,token);
    }

    @Override
    public void taoBaoLogin(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TaoBaoLoginBean> observer = new BaseObserver<TaoBaoLoginBean>() {
            @Override
            public void onSuccess(TaoBaoLoginBean bean) {
                view.taoBaoLoginSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.taoBaoLoginFailure(errorMsg);
            }
        };
        loginModel.taoBaoLogin(observer,parameters);
    }

    @Override
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

    //上传文件
    @Override
    public void upLoadFile(File file) {
        super.upLoadFile(file);
    }


    //修改用户资料
    @Override
    public void modifyPersonal(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ModifyPersonalBean> observer = new BaseObserver<ModifyPersonalBean>(view) {
            @Override
            public void onSuccess(ModifyPersonalBean s) {
                view.modifySuccess(RESOURCES.getString(R.string.modify_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.modifyFailure(errorMsg);
            }
        };
        modifyModel.modifyPersonal(observer,parameters);
    }
}
