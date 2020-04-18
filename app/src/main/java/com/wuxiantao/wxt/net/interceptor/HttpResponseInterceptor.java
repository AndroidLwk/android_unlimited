package com.wuxiantao.wxt.net.interceptor;

import com.wuxiantao.wxt.net.body.ProgressResponseBody;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HttpResponseInterceptor
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午6:06
 * Description:${DESCRIPTION} 文件下载
 */
public class HttpResponseInterceptor implements Interceptor {

    private ReqProgressCallBack callBack;

    public HttpResponseInterceptor(ReqProgressCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        //包装响应体
        return response.newBuilder().body(new ProgressResponseBody(response.body(),callBack)).build();
    }






}
