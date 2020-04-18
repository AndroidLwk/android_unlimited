package com.wuxiantao.wxt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.config.Constant;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VersionUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午12:46
 * Description:${DESCRIPTION}
 */
public class VersionUtils {

    @SuppressLint("StaticFieldLeak")
    private static volatile VersionUtils instance;

    private VersionUtils(){
        mContext = BaseApplication.getAppContext();
    }

    private Context mContext;

    public static VersionUtils newInstance(){
        if (instance == null){
            synchronized (VersionUtils.class){
                if (instance == null){
                    instance = new VersionUtils();
                }
            }
        }
        return instance;
    }



    /**
     * 获取当前版本号
     * @return
     */
    public int getCurrentVersionCode() {
        return Objects.requireNonNull(getCurrentVersion()).versionCode;
    }

    /**
     * 获取当前版本名
     * @return
     */
    public String getCurrentVersionName() {
        return Objects.requireNonNull(getCurrentVersion()).versionName;
    }


    /**
     * 获取当前包名
     * @return
     */
    public String getCurrentPackageName() {
        return Objects.requireNonNull(getCurrentVersion()).packageName;
    }

    /**
     * 获取当前版本的软件信息
     * @return
     */
    private PackageInfo getCurrentVersion() {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 与当前版本好对比，是否需要升级
     * @return
     */
    public boolean isNeedUpdate(CurrentVersionBean info) {
        //获得最新  versionCode versionName
        String newVersion = info.getVersion_andorid();
        String curVersion = Objects.requireNonNull(getCurrentVersion()).versionName;
        //newVersion 大于 curVersion
        return compareVersion(newVersion,curVersion) == 1;
    }

    //0代表相等，1代表version1大于version2，-1代表version1小于version2
    private int compareVersion(String newVersion,String curVersion){
        if (newVersion.equals(curVersion)) {
            return 0;
        }
        String[] version1Array = newVersion.split("\\.");
        String[] version2Array = curVersion.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        }else {
            return diff > 0 ? 1 : -1;
        }
    }


    /**
     * 下载完成安装apk
     * @param f apk文件
     */
    public void installApk(File f){
        setPermission();
        if (f.getName().endsWith(".apk")){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            String type = "application/vnd.android.package-archive";
            // 适配7.0安装
            if (Build.VERSION.SDK_INT > 24){
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数 3共享的文件
                Uri uri = FileProvider.getUriForFile(mContext,"com.wuxiantao.wxt.fileprovider",f);
                intent.addFlags((Intent.FLAG_GRANT_READ_URI_PERMISSION));
                intent.setDataAndType(uri,type);
            }else {
                intent.setDataAndType(Uri.fromFile(f),type);
            }
            mContext.startActivity(intent);
        }
    }

    /**
     * 提升读写权限
     * @throws //否则在安装的时候会出现apk解析失败的页面
     */
    private  void setPermission()  {
        String command = "chmod " + "777" + " " + Constant.APK_PATH;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
