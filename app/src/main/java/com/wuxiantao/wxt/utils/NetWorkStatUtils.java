package com.wuxiantao.wxt.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.wuxiantao.wxt.app.BaseApplication;

/**
 *  当前网络状态检查
 * Created by Administrator on 2018/7/20 0020.
 */

public class NetWorkStatUtils {

    private static volatile NetWorkStatUtils instance;

    private Context  context;

    private NetWorkStatUtils(){
        this.context= BaseApplication.getAppContext();
    }

    public static NetWorkStatUtils getInstance(){
        if (instance==null){
            synchronized (NetWorkStatUtils.class){
                if (instance==null){
                    return instance=new NetWorkStatUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 判断当前是否有网络链接
     * @return
     */
    public boolean  isNetworkConnected(){
        switch (getAPNType()){
            //没有网络
            case 0:
                return false;
            //wifi网络
            case 1:
                if (isWifiAvailable()){
                    return true;
                }
                // 3G网络
            case 2:
                if (isMobileConnected()){
                    return true;
                }
                // 2G网络
            case 3:
                if (isMobileConnected()){
                    return true;
                }
        }
        if (isNetworkconnected()){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断是否有网络连接
     * @return
     */
    private  boolean isNetworkconnected() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }



    /**
     * 判断WIFI网络是否可用
     * @return
     */
    public   boolean isWifiAvailable() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断当前是否是wifi网络
     * @return
     */
    public boolean isWificonnected(){
        if (getAPNType() == 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断MOBILE网络是否可用
     * @return
     */
    private  boolean isMobileConnected() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息
     * @return
     */
    private  int getConnectedType() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }




    /**
     * 获取当前的网络状态 ：没有网络0：WIFI网络1：3G网络2：2G网络3
     * @return
     */
    private     int  getAPNType() {
        int netType = 0;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager mTelephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    && !mTelephony.isNetworkRoaming()) {
                netType = 2;// 3G
            } else {
                netType = 3;// 2G
            }
        }
        return netType;
    }

}
