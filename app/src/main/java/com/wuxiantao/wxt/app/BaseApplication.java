package com.wuxiantao.wxt.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.kf5.sdk.system.init.KF5SDKInitializer;
import com.kf5.sdk.system.utils.SPUtils;
import com.ssm.androidkeystoresign.AndroidKeyStoreRSAUtils;
import com.ssm.sp.SPSecuredUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.wuxiantao.wxt.ad.TTAdManagerHolder;
import com.wuxiantao.wxt.config.Constant;
import com.wuxiantao.wxt.handler.CrashHandler;
import com.wuxiantao.wxt.utils.LogUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashSet;
import java.util.Set;

import static com.wuxiantao.wxt.config.Constant.BUGLY_ID;
import static com.wuxiantao.wxt.config.Constant.IS_DEBUG;
import static com.wuxiantao.wxt.config.Constant.TT_APP_ID;
import static com.wuxiantao.wxt.config.Constant.YUN_KF_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.YUN_KF_APP_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseApplication
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午3:01
 * Description:${DESCRIPTION}
 */
public class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    //是否开启日志打印
    protected boolean isDebugLog = false;
    private Set<Activity> allActivities;
    public IWXAPI api;
    private static Context appContext;
    protected static BaseApplication instance;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        initRSAKeyPair();

        ToastUtils.setContext(this);

        initAlibc();
        initXUtils();
        registerToWX();
        initX5Environment();


        CrashHandler.newInstance().initCrashHandler(getInstance());
        registerActivityLifecycleCallbacks(this);
        initYunKf();
        initBugly();
        initTTAdSdk();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initRSAKeyPair(){
        try {
            AndroidKeyStoreRSAUtils.generateRSAKeyPair(getApplicationContext());
        } catch (
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (
                NoSuchProviderException e) {
            e.printStackTrace();
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(IS_DEBUG, isDebugLog);
    }


    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }


    // 设置 SPApplication 字体不随系统字体设置改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res != null) {
            Configuration config = res.getConfiguration();
            if (config != null && config.fontScale != 1.0f) {
                config.fontScale = 1.0f;
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        }
        return res;
    }


    //初始化x5浏览器
    private void initX5Environment() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                String s1 = "腾讯x5浏览器初始化成功";
                String s2 = "腾讯x5浏览器初始化失败";
                LogUtils.loge(b ? s1 : s2);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


    //初始化XUtils
    private void initXUtils() {
        //初始化XUtils
        x.Ext.init(this);
        //是否输出日志
        x.Ext.setDebug(isDebugLog);
    }

    //注册微信
    private void registerToWX() {
        api = WXAPIFactory.createWXAPI(this, Constant.WEIXIN_ID, false);
        api.registerApp(Constant.WEIXIN_ID);
    }

    //初始化腾讯bugly
    private void initBugly(){
        // 获取当前包名
        String packageName = this.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setUploadProcess(TextUtils.isEmpty(processName) || processName.equals(packageName));
        //Bugly会在启动20s后联网同步数据
        strategy.setAppReportDelay(10000);
        // 初始化Bugly
        CrashReport.initCrashReport(this,BUGLY_ID,isDebugLog,strategy);
    }


    //初始化阿里百川
    private void initAlibc() {
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数
                LogUtils.loge("阿里百川初始化成功....");
                // 是否使用支付宝
                AlibcTradeSDK.setShouldUseAlipay(true);
                // 设置全局淘客参数，方便开发者用同一个淘客参数，不需要在show接口重复传入
//                AlibcTradeSDK.setTaokeParams(taokeParams)
            }

            @Override
            public void onFailure(int i, String s) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                LogUtils.loge("阿里百川初始化失败....");
            }
        });
    }

    //初始化云客服
    private void initYunKf(){
        KF5SDKInitializer.init(getApplicationContext());
        SPUtils.saveHelpAddress(YUN_KF_ADDRESS);
        SPUtils.saveAppID(YUN_KF_APP_ID);
    }

    //初始化穿山甲SDK
    //穿山甲SDK初始化
    //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
    private void initTTAdSdk(){
        TTAdSdk.init(this,ttAdConfig);
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdManagerHolder.init(this);
    }

    private TTAdConfig ttAdConfig = new TTAdConfig.Builder()
            .appId(TT_APP_ID)
            //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
//            .useTextureView(false)
            .appName("无限淘")
            .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
            //是否允许sdk展示通知栏提示
            .allowShowNotify(true)
            //是否在锁屏场景支持展示广告落地页
            .allowShowPageWhenScreenLock(true)
            //测试阶段打开，可以通过日志排查问题，上线时去除该调用
            .debug(isDebugLog)
            //允许直接下载的网络状态集合
            .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI, TTAdConstant.NETWORK_STATE_3G)
            //是否支持多进程，true支持
            .supportMultiProcess(true)
            //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
            .build();

    /**
     * 获取数据库的管理器
     * 通过管理器进行增删改查
     *
     * @return
     */
    public DbManager getDbManager() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        //数据库存储路径
//        File  file = new File("doukexin/userDb");
//        daoConfig.setDbDir(file);
        //数据库名字
        daoConfig.setDbName("user.db");
        //数据库版本
        daoConfig.setDbVersion(1);
        daoConfig.setDbOpenListener(db -> {
            // 开启WAL, 对写入加速提升巨大
        });
        //获取数据库单例
        return x.getDb(daoConfig);
    }

    //检查是否安装支付宝客户端
    public static boolean checkAliPayInstalled() {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(getInstance().getPackageManager());
        return componentName != null;
    }


    //获取当前进程名
    private String getProcessName(int pid){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // 在Activity启动时（onCreate()） 写入Activity实例到容器内
        addActivity(activity);
        LogUtils.loge("onActivityCreated==============>");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.loge("onActivityStarted==============>");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.loge("onActivityResumed==============>");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.loge("onActivityPaused==============>");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.loge("onActivityStopped==============>");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtils.loge("onActivitySaveInstanceState==============>");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.loge("onActivityDestroyed==============>");
        removeActivity(activity);
    }

    /**
     * 添加activity
     * @author android
     * @time 19-5-27 下午4:08
     */
    private void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }


    /**
     * 移除activity
     *
     * @author android
     * @time 19-5-27 下午4:08
     */
    private void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }


    /**
     * 退出app
     * @author android
     * @time 19-5-27 下午4:08
     */
    public void exitApp() {
        if (allActivities != null && !allActivities.isEmpty()) {
            for (Activity act : allActivities) {
                act.finish();
            }
            allActivities.clear();
            //  结束进程
             System.exit(0);
        }
    }


}
