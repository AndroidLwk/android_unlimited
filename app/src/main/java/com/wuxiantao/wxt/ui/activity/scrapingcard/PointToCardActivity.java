package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.plattysoft.leonids.ParticleSystem;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.StartStrapingBean;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.PointToCardContract;
import com.wuxiantao.wxt.mvp.presenter.PointToCardPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.popupwindow.ScrapingCardSuccessPopupWindow;
import com.wuxiantao.wxt.ui.title.CNToolbar;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.AudioUtils;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.FAIURE_POINTTOCARD_BACK;

/**
 * 点我刮卡
 */
@ContentView(R.layout.activity_pointtocard)
public class PointToCardActivity extends MvpActivity<PointToCardPresenter, PointToCardContract> implements PointToCardContract {
    @ViewInject(R.id.cntoolbar_title)
    CNToolbar cntoolbar_title;
    @ViewInject(R.id.tv_percent)
    TextView tv_percent;
    @ViewInject(R.id.progress_pointtocard_a)
    ProgressBar progress_pointtocard_a;
    @ViewInject(R.id.iv_openCard)
    ImageView iv_openCard;
    @ViewInject(R.id.iv_countDown)
    ImageView iv_countDown;
    @ViewInject(R.id.rt_pointCard)
    RelativeLayout rt_pointCard;
    private boolean isCountDowning;//是否正在计时

    private int isClick = 0;
    private List<TTNativeExpressAd> list_ad;//广告

    @Override
    protected void initView(Bundle savedInstanceState) {
        new Thread(() -> {
            list_ad = AdUtils.initAd(this);
        }).start();
        setStatusBar();
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        cntoolbar_title.setOnRightButtonClickListener(() -> $startActivity(MyBackpackActivity.class));
        setOnClikListener(iv_openCard, rt_pointCard);
        mPresenter.myLuckyInfo(getAppToken(), "1");
    }

    private void startPlay() {
        new ParticleSystem(PointToCardActivity.this, 1000, R.drawable.love, 4000)
                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 360)
                .setRotationSpeed(30)
                .setAcceleration(0, 90)
                .oneShot(iv_openCard, 200);
    }

    private Handler mHandler = new Handler();

    private void showSuccessWindow(StartStrapingBean bean) {
        if (bean.getCode() == 2002) {//去扩容
            showOnlyConfirmDialog("背包容量不足,您和以下卡片失之交臂.快去扩容吧!!", (dialog, which) -> $startActivity(BackpackExpansionActivity.class));
            return;
        }
        if (bean.getCode() == 200) {
            AudioUtils.modeIndicater(this, R.raw.didatwo);//播放音乐
            startPlay();
            mHandler.postDelayed(() -> {
                new ScrapingCardSuccessPopupWindow.Build(PointToCardActivity.this, list_ad)
                        .setWindowData(bean.getCode() == 200 ? bean.getData().getMsg() : bean.getMsg(), bean.getData().getCard_img())
                        .setWindowAnimStyle(R.style.custom_dialog)
                        .setOnClickListener((status) -> {//获取成功
                                    if (status) {
                                        AdUtils.initRewardVideoAd(this, () -> {
                                            mPresenter.getCard(getAppToken(), "video");
                                        });
                                    }
                                }
                        )
                        .builder().showPopupWindow();
            }, 4000);
        } else {//没的刮刮卡
            new ScrapingCardSuccessPopupWindow.Build(PointToCardActivity.this, list_ad)
                    .setWindowData(bean.getCode() == 200 ? bean.getData().getMsg() : bean.getMsg(), bean.getData().getCard_img())
                    .setWindowAnimStyle(R.style.custom_dialog)
                    .setOnClickListener((s) -> {
                                finish();
                                EventBus.getDefault().post(new MessageEvent(FAIURE_POINTTOCARD_BACK));
                            }
                    )
                    .builder().showPopupWindow();
        }
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.rt_pointCard:
                // mPresenter.myLuckyInfo(getAppToken(), "1");
                break;
            case R.id.iv_openCard:
                ++isClick;
                // mPresenter.myLuckyInfo(getAppToken(), "1");
                if (isCountDowning) {
                    return;
                }
                isCountDowning = true;//正在计时
                AudioUtils.modeIndicater(this, R.raw.dida);
                iv_countDown.setVisibility(View.VISIBLE);
                addObserverx(6, new CountDownCallBack() {
                    @Override
                    public void onNext(Long time) {
                        if (time == 2) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_one);
                        } else if (time == 3) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_two);
                        } else if (time == 4) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_three);
                        } else if (time == 5) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_four);
                        } else {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_five);
                        }
                    }

                    @Override
                    public void onComplete() {//计时完成
                        if (isClick > 0) {
                            //狂点
                            mPresenter.startStraping(getAppToken(), "yes");
                        } else {
                            //没狂点
                            mPresenter.startStraping(getAppToken(), "no");
                        }
                        isClick = 0;
                        iv_countDown.setVisibility(View.GONE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(4000);
                                    isCountDowning = false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
                break;

        }
    }

    @Override
    protected PointToCardPresenter CreatePresenter() {
        return new PointToCardPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void startStrapSuccess(StartStrapingBean bean) {
        mPresenter.myLuckyInfo(getAppToken(), "1");//每次刮刮卡成功后更新进度
        showSuccessWindow(bean);
    }

    @Override
    public void getCardSuccess(CardInfoBean bean) {
        showOnlyConfirmDialog(bean.getMsg());
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    private int progress;

    @Override
    public void myLuckyInfo(MyLuckyInfoBean info) {
        if (info.getIs_full() == 1) {//格子满了
            showDialog("背包格子已经满了，是否去扩容？", (dialog, which) -> {
                finish();
                $startActivity(BackpackExpansionActivity.class);
            });
        }
        if (Double.parseDouble(info.getLucky_value()) > 100) {
            progress = 100;
        } else {
            progress = (int) (Double.parseDouble(info.getLucky_value()));
        }
        progress_pointtocard_a.setProgress(progress);
        tv_percent.setText(progress + "%");
    }


}
