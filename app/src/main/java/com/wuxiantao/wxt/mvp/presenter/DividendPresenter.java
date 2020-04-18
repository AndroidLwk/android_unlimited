package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.DividedDragonDetailBean;
import com.wuxiantao.wxt.bean.DividedDragonListBean;
import com.wuxiantao.wxt.mvp.contract.DividendContract;
import com.wuxiantao.wxt.mvp.model.DividendModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:46
 * Description:${DESCRIPTION}
 */
public class DividendPresenter extends BasePresenter<DividendContract.IDividendView> implements DividendContract.IDividendPresenter{

    private DividendContract.IDividendView view;
    private DividendModel model = new DividendModel();

    @Override
    public void getDragonDetail(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<DividedDragonDetailBean> observer = new BaseObserver<DividedDragonDetailBean>() {
            @Override
            public void onSuccess(DividedDragonDetailBean bean) {
                view.getDragonDetailSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getDragonDetailFailure(errorMsg);
            }
        };
        model.getDividedDragonDetail(observer,token);
    }

    @Override
    public void getDragonList(String token, int page) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<DividedDragonListBean> observer = new BaseObserver<DividedDragonListBean>() {
            @Override
            public void onSuccess(DividedDragonListBean bean) {
                view.getDragonListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getDragonListFailure(errorMsg);
            }
        };
        model.getDividedDragonList(observer,token,page);
    }
}
