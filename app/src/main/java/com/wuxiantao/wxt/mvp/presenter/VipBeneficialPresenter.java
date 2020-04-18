package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.VipBeneficialContract;
import com.wuxiantao.wxt.mvp.model.VipBeneficialModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VipBeneficialPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 上午11:33
 * Description:${DESCRIPTION}
 */
public class VipBeneficialPresenter extends BasePresenter<VipBeneficialContract.IVipBeneficialView> implements VipBeneficialContract.IVipBeneficialPresenter {

    private VipBeneficialContract.IVipBeneficialView view;
    private VipBeneficialModel model = new VipBeneficialModel();

    @Override
    public void receiveMemberActive(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List list) {
                view.receiveSuccess(RESOURCES.getString(R.string.participate_activities));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.receiveFailure(errorMsg);
            }
        };
        model.receiveMemberActive(observer,token);
    }


}
