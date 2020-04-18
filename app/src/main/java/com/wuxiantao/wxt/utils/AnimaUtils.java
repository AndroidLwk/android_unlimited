package com.wuxiantao.wxt.utils;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wuxiantao.wxt.R;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AnimaUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-10-23 下午5:54
 * Description:${DESCRIPTION}
 */
public class AnimaUtils {

    //显示动画
    public static void showView(Context context,@NonNull View view){
        WeakReference<Context> reference = new WeakReference<>(context);
        if (view.getVisibility() == View.GONE) {
            Animation mAnimation = AnimationUtils.loadAnimation(reference.get(), R.anim.in_top);
            mAnimation.setDuration(500);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(mAnimation);
        }
    }

     //隐藏动画
    public static void goneView(Context context,@NonNull View view){
        WeakReference<Context> reference = new WeakReference<>(context);
        if (view.getVisibility() == View.VISIBLE) {
            Animation mAnimation = AnimationUtils.loadAnimation(reference.get(), R.anim.in_bottom);
            mAnimation.setDuration(500);
            view.startAnimation(mAnimation);
            mAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }


    public static void startShakeByPropertyAnim(View view, Animator.AnimatorListener listener){
        startShakeByPropertyAnim(view,listener,0);
    }


    public static void startShakeByPropertyAnim(View view, Animator.AnimatorListener listener,int repeatCount){
        if (view == null){
            return;
        }
        //先变小后变大
        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1f),
                Keyframe.ofFloat(.4f, 1f),
                Keyframe.ofFloat(.5f, 1f),
                Keyframe.ofFloat(.6f, 1f),
                Keyframe.ofFloat(.7f, 1f),
                Keyframe.ofFloat(.8f, 1f),
                Keyframe.ofFloat(.9f, 1f),
                Keyframe.ofFloat(1f, 1f));
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1f),
                Keyframe.ofFloat(.4f, 1f),
                Keyframe.ofFloat(.5f, 1f),
                Keyframe.ofFloat(.6f, 1f),
                Keyframe.ofFloat(.7f, 1f),
                Keyframe.ofFloat(.8f, 1f),
                Keyframe.ofFloat(.9f, 1f),
                Keyframe.ofFloat(1f, 1f));
        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * 1f),
                Keyframe.ofFloat(.2f, -3f * 1f),
                Keyframe.ofFloat(.3f, 3f * 1f),
                Keyframe.ofFloat(.4f, -3f * 1f),
                Keyframe.ofFloat(.5f, 3f * 1f),
                Keyframe.ofFloat(.6f, -3f * 1f),
                Keyframe.ofFloat(.7f, 3f * 1f),
                Keyframe.ofFloat(.8f, -3f * 1f),
                Keyframe.ofFloat(.9f, 3f * 1f),
                Keyframe.ofFloat(1f, 0));
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,scaleXValuesHolder,
                scaleYValuesHolder,rotateValuesHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(repeatCount);
        //动画重复模式
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        if (listener != null){
            objectAnimator.addListener(listener);
        }
        objectAnimator.start();
    }
}
