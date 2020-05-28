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
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.kf5.sdk.system.init.KF5SDKInitializer;
import com.kf5.sdk.system.utils.SPUtils;
import com.ssm.androidkeystoresign.AndroidKeyStoreRSAUtils;
import com.ssm.sp.SPSecuredUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.wuxiantao.wxt.ad.TTAdManagerHolder;
import com.wuxiantao.wxt.config.Api;
import com.wuxiantao.wxt.config.Constant;
import com.wuxiantao.wxt.handler.CrashHandler;
import com.wuxiantao.wxt.ui.activity.MenuActivity;
import com.wuxiantao.wxt.utils.AppUtils;
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
        initUm();

        CrashHandler.newInstance().initCrashHandler(getInstance());
        registerActivityLifecycleCallbacks(this);
        initYunKf();
        initBugly();
        if (AppUtils.isVisiableView()) {
            initTTAdSdk();
        }
    }

    /**
     * 初始化友盟SDK
     */
    private void initUm() {
        /**
         * 参数一：当前上下文context；
         * 参数二：应用申请的Appkey（需替换）；
         * 参数三：渠道名称；
         * 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
         * 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
         */
        // 打开统计SDK调试模式
        //UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, Api.UM_APPKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, Api.UM_MESSAGE_SECRECT);
        // 支持在子进程中统计自定义事件
        UMConfigure.setProcessEvent(true);
        // 选用AUTO页面采集模式[统计]
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        //程序退出时，用于保存统计数据的API
        MobclickAgent.onKillProcess(this);
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("", "注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("", "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
        //自定义通知栏打开动作(自定义消息)
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //自定义消息回调
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //自定义通知栏样式


                        // 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }
        };

        mPushAgent.setMessageHandler(messageHandler);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }

    /**
     * shapre本地储存初始化加密
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initRSAKeyPair() {
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
    private void initBugly() {
        // 获取当前包名
//        String packageName = this.getPackageName();
//        // 获取当前进程名
//        String processName = getProcessName(Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
//        strategy.setUploadProcess(TextUtils.isEmpty(processName) || processName.equals(packageName));
//        //Bugly会在启动20s后联网同步数据
//        strategy.setAppReportDelay(10000);
        // 初始化Bugly
        //CrashReport.initCrashReport(this,BUGLY_ID,isDebugLog,strategy);
        Bugly.init(getApplicationContext(), BUGLY_ID, true);
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MenuActivity.class);
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
    private void initYunKf() {
        KF5SDKInitializer.init(getApplicationContext());
        SPUtils.saveHelpAddress(YUN_KF_ADDRESS);
        SPUtils.saveAppID(YUN_KF_APP_ID);
    }

    //初始化穿山甲SDK
    //穿山甲SDK初始化
    //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
    private void initTTAdSdk() {
        TTAdSdk.init(this, ttAdConfig);
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
    private String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
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
     *
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
     *
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
