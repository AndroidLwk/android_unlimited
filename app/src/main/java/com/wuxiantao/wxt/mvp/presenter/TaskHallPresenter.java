package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.banner.BaseBannerPresenter;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.model.ConfirmOrderModel;
import com.wuxiantao.wxt.mvp.model.QualityModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:QualityPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午3:44
 * Description:${DESCRIPTION}
 */
public class TaskHallPresenter extends BaseBannerPresenter<TaskHallContract.ITaskHallView>  {

    private TaskHallContract.ITaskHallView view;
    private QualityModel model = new QualityModel();
    private ConfirmOrderModel orderModel = new ConfirmOrderModel();

    public void checkOrderStatus(String token, String order_no) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List s) {
               // view.onOrderPaySuccess(RESOURCES.getString(R.string.pay_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                //view.onOrderPayFailure(errorMsg);
            }
        };
        orderModel.checkOrderStatus(observer,token,order_no);
    }
}
