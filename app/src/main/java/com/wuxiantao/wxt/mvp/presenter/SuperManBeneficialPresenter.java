package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.mvp.contract.SuperManBeneficialContract;
import com.wuxiantao.wxt.mvp.model.SuperManBeneficialModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperManBeneficialPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-23 上午10:59
 * Description:${DESCRIPTION}
 */
public class SuperManBeneficialPresenter extends BasePresenter<SuperManBeneficialContract.ISuperManBeneficialView> implements SuperManBeneficialContract.ISuperManBeneficialPresenter {

    private SuperManBeneficialModel model = new SuperManBeneficialModel();
    private SuperManBeneficialContract.ISuperManBeneficialView view;

    @Override
    public void getShoppingList(int page,int pagesize) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<SuperManBeneBean> observer = new BaseObserver<SuperManBeneBean>() {
            @Override
            public void onSuccess(SuperManBeneBean bean) {
                view.getShoppingListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getShoppingListFailure(errorMsg);
            }
        };
        model.getShoppingList(observer,page,pagesize);
    }
}
