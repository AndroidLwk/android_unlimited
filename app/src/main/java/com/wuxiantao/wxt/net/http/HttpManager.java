package com.wuxiantao.wxt.net.http;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.config.Api;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.net.gson.GsonManager;
import com.wuxiantao.wxt.net.gson.LenientGsonConverterFactory;
import com.wuxiantao.wxt.net.interceptor.HttpCommonInterceptor;
import com.wuxiantao.wxt.net.interceptor.HttpReqeustInterceptor;
import com.wuxiantao.wxt.net.interceptor.HttpResponseInterceptor;
import com.wuxiantao.wxt.net.ssl.SSLSocketManager;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.wuxiantao.wxt.config.Constant.IS_DEBUG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HttpManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午4:09
 * Description:${DESCRIPTION} 网络请求
 */
public class HttpManager {

    //okhttp请求的客户端
    private OkHttpClient.Builder mClient;

    //Retrofit请求Builder
    private Retrofit.Builder builder;

    //instance
    private static volatile HttpManager instance;

    //初始化
    private HttpManager(){
        initOkHttp();
        initRetrofit();
    }

    //单例
    public static HttpManager newInstance(){
        if (instance == null){
            synchronized (HttpManager.class){
                if (instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }


    //初始化OkHttpClient
    private void initOkHttp(){
        mClient = new OkHttpClient.Builder();
        // 设置连接超时时间
        //https://blog.csdn.net/cs_lwb/article/details/82016997
        //https://www.jianshu.com/p/cb2c375c9105
        //链接超时
        int mConnectTimeout = 5000;
        mClient.connectTimeout(mConnectTimeout, TimeUnit.SECONDS);
        // 设置读取数据超时时间
        //读超时
        int mReadTimeOut = 5000;
        mClient.readTimeout(mReadTimeOut, TimeUnit.SECONDS);
        // 设置写入超时时间
        //写超时
        int mWriteTimeOut = 5000;
        mClient.writeTimeout(mWriteTimeOut,TimeUnit.SECONDS);
        // 设置进行连接失败重试
        mClient.retryOnConnectionFailure(true);
        boolean isDebug = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_DEBUG,true);
        if (isDebug){
            //打印拦截器日志
//            mClient.addNetworkInterceptor(new LoggerInterceptor());
            mClient.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        // 支持HTTPS
        mClient.connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT,ConnectionSpec.MODERN_TLS));
        //添加ssl安全验证
        TrustManager[] mTrustManagers =  SSLSocketManager.createTrustManagers();
        if (mTrustManagers != null){
            SSLSocketFactory mSSLSocketFactory = SSLSocketManager.createSSLSocketFactory(mTrustManagers);
            if (mSSLSocketFactory != null){
                mClient.sslSocketFactory(mSSLSocketFactory, (X509TrustManager) mTrustManagers[0]);
            }
        }
    }

    //初始化Retrofit
    private void initRetrofit(){
        builder = new Retrofit.Builder();
        builder.baseUrl(Api.BASE_URL);
        //Gson转换
        builder.addConverterFactory(getConverterFactory());
//        builder.addConverterFactory(GsonConverterFactory.create());
        //RxJava转换
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    private Converter.Factory getConverterFactory(){
        return LenientGsonConverterFactory.create(GsonManager.newInstance().getGson());
    }

    //获取对应的Service
    public <T> T createService(Class<T> service){
        mClient.addInterceptor(new HttpCommonInterceptor.Builder().addHeaderParams("User-Agent","Android").build());
        return builder.client(mClient.build()).build().create(service);
    }

    //获取对应的上传Service(带响应进度)
    public <T> T createReqeustService(Class<T> service,ReqProgressCallBack callBack){
        mClient.addInterceptor(new HttpReqeustInterceptor(callBack));
        return builder.client(mClient.build()).build().create(service);
    }

    //获取对应的下载Service(带响应进度)
    public <T> T createResponseService(Class<T> service,ReqProgressCallBack callBack){
        mClient.addInterceptor(new HttpResponseInterceptor(callBack));
        return builder.client(mClient.build()).build().create(service);
    }
}
