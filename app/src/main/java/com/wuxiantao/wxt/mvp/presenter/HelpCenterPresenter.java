package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.HelpCenterBean;
import com.wuxiantao.wxt.mvp.contract.HelpCenterContract;
import com.wuxiantao.wxt.mvp.model.HelpCenterModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class HelpCenterPresenter extends BasePresenter<HelpCenterContract.IHelpCenterView> implements HelpCenterContract.IHelpCenterPresenter {

    private HelpCenterContract.IHelpCenterView view;
    private HelpCenterModel model = new HelpCenterModel();

    @Override
    public void onGetImgList(String token,int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List<HelpCenterBean>> observer = new BaseObserver<List<HelpCenterBean>>(view) {
            @Override
            public void onSuccess(List<HelpCenterBean> list) {
                view.onGetImgListSuccess(list);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onGetImgListFailure(errorMsg);
            }
        };
        model.onGetImgList(observer);
    }
}
