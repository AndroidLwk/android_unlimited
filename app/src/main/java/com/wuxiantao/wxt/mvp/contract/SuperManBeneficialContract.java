package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperManBeneficialContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-23 上午10:55
 * Description:${DESCRIPTION}
 */
public interface SuperManBeneficialContract {

    interface ISuperManBeneficialView extends MvpView{
        void getShoppingListSuccess(SuperManBeneBean bean);
        void getShoppingListFailure(String failure);
    }

    interface ISuperManBeneficialPresenter extends MvpPresenter<ISuperManBeneficialView>{
        void getShoppingList(int page,int pagesize);
    }
}
