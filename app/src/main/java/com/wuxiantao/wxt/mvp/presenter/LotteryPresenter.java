package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.LotteryBean;
import com.wuxiantao.wxt.bean.LotteryListBean;
import com.wuxiantao.wxt.mvp.contract.LotteryContract;
import com.wuxiantao.wxt.mvp.model.LotteryModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午12:56
 * Description:${DESCRIPTION}
 */
public class LotteryPresenter extends BasePresenter<LotteryContract.ILotteryView> implements LotteryContract.ILotteryPresenter{

    private LotteryContract.ILotteryView view;
    private LotteryModel model = new LotteryModel();

    @Override
    public void getLotteryList(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<LotteryListBean> observer = new BaseObserver<LotteryListBean>() {
            @Override
            public void onSuccess(LotteryListBean bean) {
                view.getLotteryListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getLotteryListFailure(errorMsg);
            }
        };
        model.getLotteryList(observer,token);
    }

    @Override
    public void onLottery(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<LotteryBean> observer = new BaseObserver<LotteryBean>() {
            @Override
            public void onSuccess(LotteryBean bean) {
                view.onLotterySuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onLotteryFailure(errorMsg);
            }
        };
        model.onLottery(observer,token);
    }
}
