package com.wuxiantao.wxt.net.base;

import com.google.gson.annotations.SerializedName;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseResponse
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午6:16
 * Description:${DESCRIPTION} 网络请求结果 基类
 */
public class BaseResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T data;

    public boolean isSuccess(){
        return code == 200;
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
