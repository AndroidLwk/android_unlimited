package com.wuxiantao.wxt.net.interceptor;

import com.wuxiantao.wxt.utils.LogUtils;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LoggerInterceptor
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午8:28
 * Description:${DESCRIPTION} 日志打印拦截器
 */
public class LoggerInterceptor implements Interceptor {

    private static final String TAG = "Android-Request";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.loge(TAG, "request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        LogUtils.loge(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LogUtils.loge(TAG, "response body:" + content);
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }
}
