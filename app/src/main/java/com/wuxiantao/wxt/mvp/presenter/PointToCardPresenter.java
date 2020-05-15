package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.StartStrapingBean;
import com.wuxiantao.wxt.mvp.contract.PointToCardContract;
import com.wuxiantao.wxt.mvp.model.PointToCardModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

public class PointToCardPresenter extends BasePresenter<PointToCardContract> {
    private PointToCardContract view;
    private PointToCardModel model = new PointToCardModel();

    /**
     * 开始刮卡
     *
     * @param token
     */
    public void startStraping(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<StartStrapingBean> observer = new BaseObserver<StartStrapingBean>() {
            @Override
            public void onSuccess(StartStrapingBean bean) {
                view.startStrapSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.startStraping(observer, token);
    }

    /**
     * 获取幸运值
     *
     * @param token
     * @param type
     */
    public void myLuckyInfo(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MyLuckyInfoBean> observer = new BaseObserver<MyLuckyInfoBean>() {
            @Override
            public void onSuccess(MyLuckyInfoBean bean) {
                view.myLuckyInfo(bean);

            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);

            }
        };
        model.myLuckyInfo(observer, token, type);
    }

    /**
     * 领取卡片
     *
     * @param token
     * @param type
     */
    public void getCard(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<CardInfoBean> observer = new BaseObserver<CardInfoBean>() {
            @Override
            public void onSuccess(CardInfoBean bean) {
                view.getCardSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getCard(observer, token, type);
    }
}
