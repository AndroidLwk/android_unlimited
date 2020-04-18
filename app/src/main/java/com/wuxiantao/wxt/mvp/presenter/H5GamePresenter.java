package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.H5GameContract;
import com.wuxiantao.wxt.mvp.model.H5GameModel;
import com.wuxiantao.wxt.mvp.pay.BaseOrderPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:H5GamePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-12-29 上午10:43
 * Description:${DESCRIPTION}
 */
public class H5GamePresenter extends BaseOrderPresenter<H5GameContract.IH5GameView> implements H5GameContract.IH5GamePresenter {

    private H5GameContract.IH5GameView view;
    private H5GameModel model = new H5GameModel();


    @Override
    public void onGetLoadingImg() {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<String> observer = new BaseObserver<String>() {
            @Override
            public void onSuccess(String s) {
                view.onGetLoadingImgSuccess(s);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onGetLoadingImgFailure(errorMsg);
            }
        };
        model.onGetLoadingImg(observer);
    }


    @Override
    public void onOrderCreate(Map<String, Object> parameters, int payType) {
        super.onOrderCreate(model,parameters,payType);
    }

    @Override
    public void checkOrderStatus(String token, String order_no) {
        super.checkOrderStatus(model,token,order_no);
    }
}
