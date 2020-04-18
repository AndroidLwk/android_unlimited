package com.wuxiantao.wxt.mvp.user.p;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.mvp.user.m.ModifyModel;
import com.wuxiantao.wxt.mvp.user.v.ModifyView;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseModifyPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午3:06
 * Description:${DESCRIPTION}
 */
public class BaseModifyPresenter<V extends ModifyView> extends BasePresenter<V> {

    private ModifyView view;


    protected void modifyPersonal(ModifyModel model,Map<String, Object> parameters){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List s) {
                view.modifySuccess(RESOURCES.getString(R.string.modify_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.modifyFailure(errorMsg);
            }
        };
        model.modifyPersonal(observer,parameters);
    }


}
