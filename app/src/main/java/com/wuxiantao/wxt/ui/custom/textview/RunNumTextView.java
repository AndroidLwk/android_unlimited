package com.wuxiantao.wxt.ui.custom.textview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RunNumAnimation
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-12-19 下午1:00
 * Description:${DESCRIPTION}自定义 数字变换，滚动
 */
public class RunNumTextView extends AppCompatTextView {

    //动画时长
    private long duration = 2000;

    //数字
    private float number = 0.00f;

    //是否自动开始
    private boolean isAuto;

    //是否加单为
    private boolean isUnit;
    private Context context;

    private ValueAnimator mValueAnimator;

    public RunNumTextView(Context context) {
        this(context,null);
    }

    public RunNumTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RunNumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RunNumTextView);
        number = a.getFloat(R.styleable.RunNumTextView_tv_number,0.00f);
        isAuto = a.getBoolean(R.styleable.RunNumTextView_tv_is_auto,isAuto);
        a.recycle();
        if (isAuto){
            start();
        }
    }


    public void start() {
        if (number > 0){
            stop();
            //从0开始，number是你要传的数据
            mValueAnimator = ValueAnimator.ofFloat(0, number);
            mValueAnimator.setDuration(duration);
            mValueAnimator.addUpdateListener(animation -> {
                String value = mValueAnimator.getAnimatedValue().toString();
                String str = BigDecimalUtils.round(value,8);
                if (isUnit){
                    setText(context.getString(R.string.yuan_regex_f,str));
                }else {
                    setText(str);
                }
            });
            mValueAnimator.start();
        }
    }

    public boolean isStarted(){
        return mValueAnimator != null && mValueAnimator.isStarted();
    }


    public void setNumber(String number){
        try {
            this.number = BigDecimalUtils.roundFloatValue(number,8);
            start();
        }catch (Exception e){
            e.printStackTrace();
            this.setText(number);
        }
    }

    public void setNumber(String number,long duration){
        isUnit = true;
        try {
            this.duration = duration;
            this.number = BigDecimalUtils.roundFloatValue(number,8);
            start();
        }catch (Exception e){
            e.printStackTrace();
            this.setText(number);
        }
    }


    private void stop(){
        if (mValueAnimator != null && mValueAnimator.isRunning()){
            this.clearAnimation();
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
    }
}
