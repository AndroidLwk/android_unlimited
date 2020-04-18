package com.wuxiantao.wxt.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/1/2 0002 18:12 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class SystemUtils {

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";


    //判断当前的手机系统是不是MIUI系统
    public static boolean isMIUI(){
        try {
            BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE,null) != null ||
                    prop.getProperty(KEY_MIUI_VERSION_NAME,null) != null ||
                    prop.getProperty(KEY_MIUI_INTERNAL_STORAGE,null) != null;
        }catch (Exception e){
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取当前手机系统语言。
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }


    /**
     * 获取手机厂商
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }


    /**
     *
     * @param context 一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */


    /**
     * 判断应用是否正在运行
     * @param context 上下文
     * @param packageName 包名
     * @return 1 正在运行中  0 未运行 -1应用未安装
     */
    public static int isAppAlive(Context context,String packageName){
        int uid = getPackageUid(context,packageName);
        if (uid > 0){
            boolean rstA = isAppRunning(context,packageName);
            boolean rstB = isProcessRunning(context,uid);
            if (rstA || rstB){
                //指定包名的程序正在运行中
                return 1;
            }else {
                //指定包名的程序未在运行中
                return 0;
            }
        }else {
            //应用未安装
            return -1;
        }
    }


    /**
     * 判断当前应用是在前台还是后台
     * @param context 上下文
     * @return true前台  false后台
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 判断某一 uid 的程序是否有正在运行的进程，即是否存活
     * Created by cafeting on 2017/2/4.
     * @param context     上下文
     * @param uid 已安装应用的 uid
     * @return true 表示正在运行，false 表示没有运行
     */
    private static boolean isProcessRunning(Context context, int uid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() > 0) {
            for (ActivityManager.RunningServiceInfo appProcess : runningServiceInfos){
                if (uid == appProcess.uid) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取已安装应用的 uid，-1 表示未安装此应用或程序异常
     * @param context 上下问
     * @param packageName 报名
     * @return
     */
    private static int getPackageUid(Context context, String packageName) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }


    /**
     * 方法描述：判断某一应用是否正在运行
     * Created by cafeting on 2017/2/4.
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false 表示没有运行
     */
    private static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 跳转到WIFI设置
     * @param context
     */
    public static void intentWifiSetting(Context context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            // 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
            context.startActivity(new Intent(
                    Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(
                    Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * WIFI网络开关
     */
    public static void toggleWiFi(Context context, boolean enabled) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(enabled);
    }

    /**
     * 移动网络开关
     */
    public static void toggleMobileData(Context context, boolean enabled) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        Class<?> conMgrClass = null; // ConnectivityManager类
        Field iConMgrField = null; // ConnectivityManager类中的字段
        Object iConMgr = null; // IConnectivityManager类的引用
        Class<?> iConMgrClass = null; // IConnectivityManager类
        Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法
        try {
            // 取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            // 取得ConnectivityManager类中的对象mService
            iConMgrField = conMgrClass.getDeclaredField("mService");
            // 设置mService可访问
            iConMgrField.setAccessible(true);
            // 取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            // 取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());
            // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
            setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
                    "setMobileDataEnabled", Boolean.TYPE);
            // 设置setMobileDataEnabled方法可访问
            setMobileDataEnabledMethod.setAccessible(true);
            // 调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConMgr, enabled);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * GPS开关 当前若关则打开 当前若开则关闭
     */
    public static void toggleGPS(Context context) {
        Intent gpsIntent = new Intent();
        gpsIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        gpsIntent.addCategory("android.intent.category.ALTERNATIVE");
        gpsIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, gpsIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调节系统音量
     * @param context
     */
    public static void holdSystemAudio(Context context) {
        AudioManager audiomanage = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取系统最大音量
        // int maxVolume = audiomanage.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量
        // int currentVolume = audiomanage.getStreamVolume(AudioManager.STREAM_RING);
        // 设置音量
        // audiomanage.setStreamVolume(AudioManager.STREAM_SYSTEM, currentVolume, AudioManager.FLAG_PLAY_SOUND);

        // 调节音量
        // ADJUST_RAISE 增大音量，与音量键功能相同
        // ADJUST_LOWER 降低音量
        audiomanage.adjustStreamVolume(AudioManager.STREAM_SYSTEM,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

    }

    /**
     * 设置亮度（每30递增）
     */
    public static void setBrightness(Activity activity) {
        ContentResolver resolver = activity.getContentResolver();
        Uri uri = Settings.System
                .getUriFor("screen_brightness");
        int nowScreenBri = getScreenBrightness(activity);
        nowScreenBri = nowScreenBri <= 225 ? nowScreenBri + 30 : 30;
        System.out.println("nowScreenBri==" + nowScreenBri);
        Settings.System.putInt(resolver, "screen_brightness",
                nowScreenBri);
        resolver.notifyChange(uri, null);
    }

    /**
     * 获取屏幕的亮度
     * @param activity
     * @return
     */
    public static int getScreenBrightness(Activity activity) {
        int nowBrightnessValue = 0;
        ContentResolver resolver = activity.getContentResolver();
        try {
            nowBrightnessValue = Settings.System.getInt(
                    resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }

    /**
     * 跳转到系统设置
     * @param context
     */
    public static void intentSetting(Context context) {
        String pkg = "com.android.settings";
        String cls = "com.android.settings.Settings";

        ComponentName component = new ComponentName(pkg, cls);
        Intent intent = new Intent();
        intent.setComponent(component);

        context.startActivity(intent);
    }

    /**
     * 获取文件夹下所有文件
     * @param path
     * @return
     */
    public static ArrayList<File> getFilesArray(String path){
        File file = new File(path);
        File files[] = file.listFiles();
        ArrayList<File> listFile = new ArrayList<File>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    listFile.add(files[i]);
                }
                if (files[i].isDirectory()) {
                    listFile.addAll(getFilesArray(files[i].toString()));
                }
            }
        }
        return listFile;
    }


    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     * @param videoPath 视频的路径
     * @param width 指定输出视频缩略图的宽度
     * @param height 指定输出视频缩略图的高度度
     * @param kind 参照MediaStore.Images.Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *            其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height,int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        //System.out.println("w"+bitmap.getWidth());
        //System.out.println("h"+bitmap.getHeight());
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }


    /**
     * 打开视频文件
     * @param context
     * @param file 视频文件
     */
    public static void intentVideo(Context context, File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = "video/*";
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, type);
        context.startActivity(intent);
    }




    static class BuildProperties {

        private final Properties properties;

        public BuildProperties() throws IOException {
            this.properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        }

        public boolean containsKey(Object key) {
            return properties.containsKey(key);
        }

        public boolean containsValue(Object value) {
            return properties.containsValue(value);
        }

        public Set<Map.Entry<Object, Object>> entrySet() {
            return properties.entrySet();
        }

        public String getProperty(final String name) {
            return properties.getProperty(name);
        }

        public String getProperty(final String name, final String defaultValue) {
            return properties.getProperty(name, defaultValue);
        }

        public boolean isEmpty() {
            return properties.isEmpty();
        }

        public Enumeration<Object> keys() {
            return properties.keys();
        }

        public Set<Object> keySet() {
            return properties.keySet();
        }

        public int size() {
            return properties.size();
        }

        public Collection<Object> values() {
            return properties.values();
        }

        public static BuildProperties newInstance() throws IOException {
            return new BuildProperties();
        }
    }











}
