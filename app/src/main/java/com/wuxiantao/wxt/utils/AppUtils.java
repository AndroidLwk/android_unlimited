package com.wuxiantao.wxt.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.wuxiantao.wxt.app.BaseApplication;

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
}
