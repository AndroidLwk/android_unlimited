package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.pay.OrderPayMvpPresenter;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:H5GameContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-12-29 上午10:42
 * Description:${DESCRIPTION}
 */
public interface H5GameContract {

    interface IH5GameView extends OrderPayView {
        void onGetLoadingImgSuccess(String url);
        void onGetLoadingImgFailure(String failure);
    }

    interface IH5GamePresenter extends OrderPayMvpPresenter<IH5GameView> {
        void onGetLoadingImg();
    }


}
