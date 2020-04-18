package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:46
 * Description:${DESCRIPTION}
 */
public interface HighAreaContract {

    interface IHighAreaView extends MvpView{
        void getHighAreaListSuccess(HighAreaListBean bean);
        void getHighAreaListFailure(String failure);
    }

    interface IHighAreaPresenter extends MvpPresenter<IHighAreaView>{
        void getHighAreaList(String token,int page);
    }
}
