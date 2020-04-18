package com.wuxiantao.wxt.net.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HttpCommonInterceptor
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午6:23
 * Description:${DESCRIPTION} 拦截器 向请求头里添加公共参数
 */
public class HttpCommonInterceptor implements Interceptor {

    private Map<String,String> mHeaderParamsMap = new HashMap<>();

    public HttpCommonInterceptor(){}


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
        builder.method(oldRequest.method(),oldRequest.body());
        //添加公共参数到header中
        if (mHeaderParamsMap.size() > 0){
            for (Map.Entry<String,String> params : mHeaderParamsMap.entrySet()){
                builder.header(params.getKey(),params.getValue());
            }
        }
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }


    public static class Builder{
        HttpCommonInterceptor mInterceptor;
        public Builder(){
            mInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key,String value){
            mInterceptor.mHeaderParamsMap.put(key,value);
            return this;
        }

        public Builder addHeaderParams(String key,int value){
            mInterceptor.mHeaderParamsMap.put(key,String.valueOf(value));
            return this;
        }

        public Builder addHeaderParams(String key,float value){
            mInterceptor.mHeaderParamsMap.put(key,String.valueOf(value));
            return this;
        }

        public Builder addHeaderParams(String key,double value){
            mInterceptor.mHeaderParamsMap.put(key,String.valueOf(value));
            return this;
        }

        public Builder addHeaderParams(String key,long value){
            mInterceptor.mHeaderParamsMap.put(key,String.valueOf(value));
            return this;
        }

        public HttpCommonInterceptor build(){
            return mInterceptor;
        }
    }






}
