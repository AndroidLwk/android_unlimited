package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.mvp.contract.MyIncomeContract;
import com.wuxiantao.wxt.mvp.model.MyIncomeModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:18
 * Description:${DESCRIPTION}
 */
public class MyIncomePresenter extends BasePresenter<MyIncomeContract.IMyIncomeView> implements MyIncomeContract.IMyIncomePresenter {

    private MyIncomeContract.IMyIncomeView view;
    private MyIncomeModel model = new MyIncomeModel();

    @Override
    public void getMyIncomeList(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<MyIncomeBean> observer = new BaseObserver<MyIncomeBean>() {
            @Override
            public void onSuccess(MyIncomeBean bean) {
                view.getMyIncomeListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getMyIncomeListFailure(errorMsg);
            }
        };
        model.getMyIncomeList(observer,token,page);
    }
}
