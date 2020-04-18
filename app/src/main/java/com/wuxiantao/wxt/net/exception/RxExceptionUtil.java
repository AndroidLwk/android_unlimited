package com.wuxiantao.wxt.net.exception;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.net.base.ResultException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RxExceptionUtil
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午7:49
 * Description:${DESCRIPTION} 网络请求异常处理
 */
public class RxExceptionUtil {

    public static String exceptionHandler(Throwable e) {
        if (e instanceof UnknownHostException) {
            return BaseApplication.getAppContext().getString(R.string.network_unavailable);
        } else if (e instanceof ConnectException) {
            return BaseApplication.getAppContext().getString(R.string.connection_error);
        } else if (e instanceof SSLHandshakeException) {
            return BaseApplication.getAppContext().getString(R.string.safety_certificate);
        } else if (e instanceof SocketTimeoutException) {
            return BaseApplication.getAppContext().getString(R.string.network_timeout);
        } else if (e instanceof HttpException) {
            return convertStatusCode((HttpException) e);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            return BaseApplication.getAppContext().getString(R.string.parsing_error);
        }else if (e instanceof ResultException){
            return convertStatusCode((ResultException)e);
        }
        return BaseApplication.getAppContext().getString(R.string.unknow_error);
    }


    private static String convertStatusCode(HttpException e) {
        if (e.code() >= 500 && e.code() < 600) {
            return BaseApplication.getAppContext().getString(R.string.service_request_error);
        } else if (e.code() >= 400 && e.code() < 500) {
            return BaseApplication.getAppContext().getString(R.string.unable_to_process);
        } else if (e.code() >= 300 && e.code() < 400) {
            return BaseApplication.getAppContext().getString(R.string.redirect);
        }
        return e.message();
    }

    private static String convertStatusCode(ResultException e) {
        switch (e.getErrorCode()){
            case 1001:
            case 1002:
               return e.getErrorMessage();
        }
        return e.getErrorMessage();
    }
}
