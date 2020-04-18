package com.wuxiantao.wxt.mvp.vercode;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
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
public class BaseObtainCodePresenter<V extends ObtainCodeView> extends BasePresenter<V> {

    private V view;
    private ObtainCodeModel model = new ObtainCodeModel();
    
    public void obtainCode(String mobile,int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List s) {
                view.obtainSuccess(RESOURCES.getString(R.string.sms_success));
            }
            @Override
            public void onFailure(String errorMsg) {
                view.obtainFailure(errorMsg);
            }
        };
        model.obtainCode(observer,mobile,type);
    }





}
