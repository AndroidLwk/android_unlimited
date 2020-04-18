package com.wuxiantao.wxt.net.converter;

import com.google.gson.Gson;
import com.wuxiantao.wxt.net.base.BaseResponse;
import com.wuxiantao.wxt.net.base.ResultException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LenientGsonResponseBodyConverter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-25 下午3:35
 * Description:${DESCRIPTION}
 */
public class LenientGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private Type type;

    public LenientGsonResponseBodyConverter(Gson gson,Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        BaseResponse response = gson.fromJson(json,BaseResponse.class);
        if (response.isSuccess()){
            return gson.fromJson(json,type);
        }else {
            throw new ResultException(response.getCode(),response.getMsg());
        }
    }

}
