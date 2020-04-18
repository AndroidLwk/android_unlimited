package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.DividedDragonDetailBean;
import com.wuxiantao.wxt.bean.DividedDragonListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:45
 * Description:${DESCRIPTION}
 */
public interface DividendContract {

    interface IDividendView extends MvpView{
        void getDragonDetailSuccess(DividedDragonDetailBean bean);
        void getDragonDetailFailure(String failure);
        void getDragonListSuccess(DividedDragonListBean listBean);
        void getDragonListFailure(String failure);
    }

    interface IDividendPresenter extends MvpPresenter<IDividendView>{
        void getDragonDetail(String token);
        void getDragonList(String token,int page);
    }
}
