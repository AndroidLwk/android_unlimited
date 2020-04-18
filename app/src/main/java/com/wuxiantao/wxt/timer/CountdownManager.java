package com.wuxiantao.wxt.timer;

import android.os.CountDownTimer;
import android.widget.TextView;

import static com.wuxiantao.wxt.config.Constant.COUNT_DOWN_INTERVAL;
import static com.wuxiantao.wxt.config.Constant.MILLIS_IN_FUTURE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CountdownManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-18 上午10:14
 * Description:${DESCRIPTION}
 */
public abstract class CountdownManager extends CountDownTimer {

    private TextView view;

    public CountdownManager(TextView view){
        this(MILLIS_IN_FUTURE,view);
    }

    public CountdownManager(long millisInFuture,TextView view){
        this(millisInFuture,COUNT_DOWN_INTERVAL,view);
    }

    public CountdownManager(long millisInFuture,long countDownInterval,TextView view){
        super(millisInFuture, countDownInterval);
        this.view = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        view.setText(String.valueOf(millisUntilFinished / 1000));
        onPregress(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        onTimeOver();
    }

    public abstract void onTimeOver();

    public void onPregress(long millisUntilFinished){

    }
}
