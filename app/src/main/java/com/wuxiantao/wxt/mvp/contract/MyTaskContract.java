package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.TaskInfoBean;
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
public interface MyTaskContract {

    interface ITaskView extends MvpView{
        void getTaskInfoSuccess(TaskInfoBean bean);
        void getTaskInfoFailure(String failure);
        void checkInSuccess(String msg);
        void checkInFailure(String failure);
        void onAdsDevoteSuccess(String msg);
        void onAdsDevoteFailure(String failure);
    }

    interface ITaskPresenter extends MvpPresenter<ITaskView>{
        void getTaskInfo(String token);
        void checkIn(String token);
        void onAdsDevote(String token);
    }
}
