package com.wuxiantao.wxt.net.interceptor;

import com.wuxiantao.wxt.net.body.ProgressRequestBody;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HttpResponseInterceptor
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午6:06
 * Description:${DESCRIPTION} 文件上传
 */
public class HttpReqeustInterceptor implements Interceptor {

    private ReqProgressCallBack callBack;

    public HttpReqeustInterceptor(ReqProgressCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //包装响应体
        request.newBuilder().method(request.method(),
                new ProgressRequestBody(request.body(),callBack)).build();
        return chain.proceed(request);
    }



}
