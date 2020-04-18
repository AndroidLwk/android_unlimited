package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomeContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:17
 * Description:${DESCRIPTION}
 */
public interface MyIncomeContract {

    interface IMyIncomeView extends MvpView{
        void getMyIncomeListSuccess(MyIncomeBean bean);
        void getMyIncomeListFailure(String failure);
    }

    interface IMyIncomePresenter extends MvpPresenter<IMyIncomeView>{
        void getMyIncomeList(String token,int page);
    }
}
