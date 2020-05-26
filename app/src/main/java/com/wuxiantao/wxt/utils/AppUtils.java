package com.wuxiantao.wxt.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.wuxiantao.wxt.app.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wuxiantao.wxt.config.Constant.VISIABLE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AppUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-13 下午3:11
 * Description:${DESCRIPTION}
 */
public class AppUtils {

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName() {
        try {
            Context context = BaseApplication.getAppContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static synchronized String getVersionName() {
        try {
            Context context = BaseApplication.getAppContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static synchronized int getVersionCode() {
        try {
            Context context = BaseApplication.getAppContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static synchronized String getPackageName() {
        try {
            Context context = BaseApplication.getAppContext();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取图标 bitmap
     */
    public static synchronized Bitmap getBitmap() {
        Context context = BaseApplication.getAppContext();
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        //xxx根据自己的情况获取drawable
        Drawable d = packageManager.getApplicationIcon(applicationInfo);
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }

    /**
     * 获取的设备信息[友盟集成测试]
     */
    public static String[] getTestDeviceInfo(Context context) {
        String[] deviceInfo = new String[2];
        try {
            if (context != null) {
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
            }
        } catch (Exception e) {
        }
        return deviceInfo;
    }

    /**
     * 规定的时间是否显示view
     */
    public static boolean isVisiableView() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String str1 = df.format(new Date());// new Date()为获取当前系统时间
        String str2 = VISIABLE_TIME;
        try {
            Date date1 = df.parse(str1);
            Date date2 = df.parse(str2);
            int i = date1.compareTo(date2);//0 相等 小于0 之前 大于0 之后
            if (i > 0 || i == 0) {//显示
                return true;
            } else {//不显示
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
