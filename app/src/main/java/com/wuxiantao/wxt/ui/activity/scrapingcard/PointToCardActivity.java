package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.mvp.contract.PointToCardContract;
import com.wuxiantao.wxt.mvp.presenter.PointToCardPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.popupwindow.ScrapingCardSuccessPopupWindow;
import com.wuxiantao.wxt.ui.title.CNToolbar;
import com.wuxiantao.wxt.utils.LogUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

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
    @ViewInject(R.id.konfettiView_play)
    KonfettiView konfettiView_play;

    @Override
    protected void initView() {
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        cntoolbar_title.setOnRightButtonClickListener(() -> {
        });
        setOnClikListener(iv_openCard);
        startPlay();

    }

    private void startPlay() {
        konfettiView_play.setVisibility(View.VISIBLE);
        konfettiView_play.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, konfettiView_play.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

    //显示卡牌开启五个之后对话框
    private void showSuccessWindow(String carname, int id) {
        new ScrapingCardSuccessPopupWindow.Build(PointToCardActivity.this)
                .setWindowData(carname, id)
                .setWindowAnimStyle(R.style.custom_dialog)
                .setOnClickListener(() -> {//获取成功
                            showOnlyConfirmDialog("领取成功！！！");
                        }
                )
                .builder().showPopupWindow();
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.iv_openCard:
                iv_openCard.setEnabled(false);
                iv_countDown.setVisibility(View.VISIBLE);
                addObserverx(6, new CountDownCallBack() {
                    @Override
                    public void onNext(Long time) {
                        LogUtils.loge("time:" + time);
                        if (time == 1) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_one);
                        } else if (time == 2) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_two);
                        } else if (time == 3) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_three);
                        } else if (time == 4) {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_four);
                        } else {
                            iv_countDown.setImageResource(R.drawable.pointtocard_countdown_five);
                        }
                        ToastUtils.showToast("又来了" + time);
                    }

                    @Override
                    public void onComplete() {
                        ToastUtils.showToast("5秒完成=了哦!!!");
                        showSuccessWindow("曹操青铜碎片", 2);
                        iv_openCard.setEnabled(true);
                        iv_countDown.setVisibility(View.GONE);
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
}
