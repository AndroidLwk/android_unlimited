package com.wuxiantao.wxt.mvp.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.bean.EnrollBonusBean;
import com.wuxiantao.wxt.bean.GameMessageBean;
import com.wuxiantao.wxt.bean.IncomeHallBean;
import com.wuxiantao.wxt.bean.IncreaseCountBean;
import com.wuxiantao.wxt.bean.MyGameInfoBean;
import com.wuxiantao.wxt.bean.OpenDragonBean;
import com.wuxiantao.wxt.bean.StartExperienceBean;
import com.wuxiantao.wxt.bean.VideoDoubleBean;
import com.wuxiantao.wxt.config.Api;
import com.wuxiantao.wxt.mvp.contract.IncomeHallContract;
import com.wuxiantao.wxt.mvp.model.IncomeHallModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IncomeHallPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午3:23
 * Description:${DESCRIPTION}
 */
public class IncomeHallPresenter extends BasePresenter<IncomeHallContract.IIncomeHallView> implements IncomeHallContract.IIncomeHallPresenter {

    private IncomeHallContract.IIncomeHallView view;
    private IncomeHallModel model = new IncomeHallModel();

    @Override
    public void getIncomeHallInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<IncomeHallBean> observer = new BaseObserver<IncomeHallBean>() {
            @Override
            public void onSuccess(IncomeHallBean bean) {
                view.getIncomeHallInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getIncomeHallInfoFailure(errorMsg);
            }
        };
        model.getIncomeHallInfo(observer, token);
    }


    @Override
    public void getDragonStatusInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<DragonStatusInfoBean> observer = new BaseObserver<DragonStatusInfoBean>() {
            @Override
            public void onSuccess(DragonStatusInfoBean bean) {
                view.getDragonStatusInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getDragonStatusInfoFailure(errorMsg);
            }
        };
        model.getDragonStatusInfo(observer, token);
    }

    @Override
    public void onOpenDragon(String token, int card_id) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<OpenDragonBean> observer = new BaseObserver<OpenDragonBean>(view) {
            @Override
            public void onSuccess(OpenDragonBean bean) {
                view.onOpenDragonSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOpenDragonFailure(errorMsg);
            }
        };
        model.onOpenDragon(observer, token, card_id);
    }

    @Override
    public void onGameMessage(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<GameMessageBean> observer = new BaseObserver<GameMessageBean>(view) {
            @Override
            public void onSuccess(GameMessageBean bean) {
                view.onGameMessageSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onGameMessageFailure(errorMsg);
            }
        };
        model.onGameMessage(observer, token);
    }

    @Override
    public void onStartExperience(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<StartExperienceBean> observer = new BaseObserver<StartExperienceBean>(view) {
            @Override
            public void onSuccess(StartExperienceBean bean) {
                view.onStartExperienceSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onStartExperienceFailure(errorMsg);
            }
        };
        model.onStartExperience(observer, token);
    }

    @Override
    public void onChangeAreaInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<AreaChangeInfoBean> observer = new BaseObserver<AreaChangeInfoBean>(view) {
            @Override
            public void onSuccess(AreaChangeInfoBean bean) {
                view.onChangeAreaInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onChangeAreaInfoFailure(errorMsg);
            }
        };
        model.onChangeAreaInfo(observer, token);
    }

    @Override
    public void onBindingArea(String token, int id) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List<String>> observer = new BaseObserver<List<String>>(view) {
            @Override
            public void onSuccess(List<String> msg) {
                view.onBindingAreaSuccess(msg);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onBindingAreaFailure(errorMsg);
            }
        };
        model.onBindingArea(observer, token, id);
    }

    @Override
    public void onIncreaseCount(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<IncreaseCountBean> observer = new BaseObserver<IncreaseCountBean>(view) {
            @Override
            public void onSuccess(IncreaseCountBean bean) {
                view.onIncreaseCountSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onIncreaseCountFailure(errorMsg);
            }
        };
        model.onIncreaseCount(observer, token);
    }

    @Override
    public void onVideoDouble(String token, int dragon_id, String num) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<VideoDoubleBean> observer = new BaseObserver<VideoDoubleBean>() {
            @Override
            public void onSuccess(VideoDoubleBean msg) {
                view.onVideoDoubleSuccess(msg);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onVideoDoubleFailure(errorMsg);
            }
        };
        model.onVideoDouble(observer, token, dragon_id, num);
    }
    /**
     * 斩妖之旅
     *
     * @param token
     */
    public void getMyGameInfo(String token,Activity activity) {
        if (view == null) {
            view = getMvpView();
        }
//        BaseObserver<MyGameInfoBean> observer = new BaseObserver<MyGameInfoBean>() {
//            @Override
//            public void onSuccess(MyGameInfoBean bean) {
//                view.onGetMyGameInfoSuccess(bean);
//            }
//
//            @Override
//            public void onFailure(String errorMsg) {
//                view.onGetMyGameInfoFailure(errorMsg);
//            }
//        };
//        model.onGetMyGameInfo(observer, token);
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("token", token);//传递键值对参数
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(Api.BASE_URL + Api.MY_GAME_INFO)
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
                        MyGameInfoBean info = new Gson().fromJson(responseString, MyGameInfoBean.class);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.onGetMyGameInfoSuccess(info);
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.onGetMyGameInfoFailure(activity.getString(R.string.game_text8));
                    }
                });
            }
        });
    }

    /**
     * 分红
     *
     * @param token
     * @param type
     */
    @Override
    public void enrollBonus(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<EnrollBonusBean> observer = new BaseObserver<EnrollBonusBean>() {
            @Override
            public void onSuccess(EnrollBonusBean bean) {
                view.enrollBonusSuccess("分红成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.enrollBonusFailure(errorMsg);
            }
        };
        model.enrollBonus(observer, token, type);
    }

}
