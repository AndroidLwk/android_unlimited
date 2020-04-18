package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.TaskInfoBean;
import com.wuxiantao.wxt.mvp.contract.MyTaskContract;
import com.wuxiantao.wxt.mvp.model.MyTaskModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:46
 * Description:${DESCRIPTION}
 */
public class MyTaskPresenter extends BasePresenter<MyTaskContract.ITaskView> implements MyTaskContract.ITaskPresenter{

    private MyTaskContract.ITaskView view;
    private MyTaskModel model = new MyTaskModel();

    @Override
    public void getTaskInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<TaskInfoBean> observer = new BaseObserver<TaskInfoBean>() {
            @Override
            public void onSuccess(TaskInfoBean bean) {
                view.getTaskInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getTaskInfoFailure(errorMsg);
            }
        };
        model.getTaskInfo(observer,token);
    }


    @Override
    public void checkIn(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List list) {
                view.checkInSuccess(RESOURCES.getString(R.string.check_in_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.checkInFailure(errorMsg);
            }
        };
        model.checkIn(observer,token);
    }

    @Override
    public void onAdsDevote(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List<String>> observer = new BaseObserver<List<String>>() {
            @Override
            public void onSuccess(List<String> list) {
                view.onAdsDevoteSuccess(RESOURCES.getString(R.string.ads_devote));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onAdsDevoteFailure(errorMsg);
            }
        };
        model.onAdsDevote(observer,token);
    }

}
