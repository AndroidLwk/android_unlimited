package com.wuxiantao.wxt.utils;

import android.content.Context;
import android.util.TypedValue;

import com.wuxiantao.wxt.app.BaseApplication;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DensityUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-31 上午10:20
 * Description:${DESCRIPTION}像素转换工具
 */
public class DensityUtils {

    /**
     * dip转像素
     */
    public static int dip2px(float dpValue) {
        Context context = BaseApplication.getAppContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 像素转dip
     */
    public static int px2dip(float pxValue) {
        Context context = BaseApplication.getAppContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //dp转换成像素
    public static  int dp2px(int value) {
        Context context = BaseApplication.getAppContext();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,context.getResources().getDisplayMetrics());
    }

    //dp转换成像素
    public static  float dp2px(float value) {
        Context context = BaseApplication.getAppContext();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,context.getResources().getDisplayMetrics());
    }


}
