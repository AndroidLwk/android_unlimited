package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.mvp.contract.HighAreaContract;
import com.wuxiantao.wxt.mvp.model.HighAreaModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:47
 * Description:${DESCRIPTION}
 */
public class HighAreaPresenter extends BasePresenter<HighAreaContract.IHighAreaView> implements HighAreaContract.IHighAreaPresenter {

    private HighAreaContract.IHighAreaView view;
    private HighAreaModel model = new HighAreaModel();

    @Override
    public void getHighAreaList(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<HighAreaListBean> observer = new BaseObserver<HighAreaListBean>() {
            @Override
            public void onSuccess(HighAreaListBean bean) {
                 view.getHighAreaListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getHighAreaListFailure(errorMsg);
            }
        };
        model.getHighAreaList(observer,token,page);
    }
}
