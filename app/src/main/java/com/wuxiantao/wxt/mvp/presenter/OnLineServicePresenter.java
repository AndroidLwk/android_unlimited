package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.OnLineServiceBean;
import com.wuxiantao.wxt.mvp.contract.OnLineServiceContract;
import com.wuxiantao.wxt.mvp.model.OnLineServiceModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.download.BaseDownloadPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineServicePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-21 下午1:55
 * Description:${DESCRIPTION}
 */
public class OnLineServicePresenter extends BaseDownloadPresenter<OnLineServiceContract.IOnLineServiceView> implements OnLineServiceContract.IOnLineServicePresenter {

    private OnLineServiceContract.IOnLineServiceView view;
    private OnLineServiceModel model = new OnLineServiceModel();

    @Override
    public void getOnLineServiceInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<OnLineServiceBean> observer = new BaseObserver<OnLineServiceBean>() {
            @Override
            public void onSuccess(OnLineServiceBean bean) {
                view.getOnLineServiceInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getOnLineServiceInfoFailure(errorMsg);
            }
        };
        model.getOnLineServiceInfo(observer,token);
    }

    @Override
    public void onDownloadFile(String url) {
        super.onDownloadFile(model,url);
    }
}
