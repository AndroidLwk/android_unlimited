package com.wuxiantao.wxt.mvp.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.StartStrapingBean;
import com.wuxiantao.wxt.mvp.contract.PointToCardContract;
import com.wuxiantao.wxt.mvp.model.PointToCardModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class PointToCardPresenter extends BasePresenter<PointToCardContract> {
    private PointToCardContract view;
    private PointToCardModel model = new PointToCardModel();

    /**
     * 开始刮卡
     *
     * @param token
     */
//    public void startStraping(String token) {
//        if (view == null) {
//            view = getMvpView();
//        }
//
//        model.startStraping(observer, token);
//    }
    private StartStrapingBean info;
    private Activity mActivity;

    /**
     * 开始刮卡
     */
    public void startStraping(String token) {
        if (view == null) {
            view = getMvpView();
        }
        mActivity = (Activity) view;
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("token", token);//传递键值对参数
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://chaoren.haowusong.com/api/Scratchcard/startStraping")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = null;
                    try {
                        responseString = response.body().string();
                    } catch (IOException e) {
                        return;
                    }
                    try {
                        info = new Gson().fromJson(responseString, StartStrapingBean.class);
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.startStrapSuccess(info);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.onFailure("网络错误");
                    }
                });
            }
        });
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
