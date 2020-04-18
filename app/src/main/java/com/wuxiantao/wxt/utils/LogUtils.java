package com.wuxiantao.wxt.utils;

import android.util.Log;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.config.Constant;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/11/23 0023 10:28 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class LogUtils {

    //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
    //  把4*1024的MAX字节打印长度改为2001字符数
    public  static void  loge(String str){
        boolean isDebugLog = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(Constant.IS_DEBUG,false);
        if (isDebugLog){
            if (str == null){
                Log.e("LogUtils","打印的字符串 == null");
                return;
            }
            int max_str_length= 2001;
            //大于4000时
            while (str.length( )> max_str_length){
                Log.e("LogUtils", str.substring(0,max_str_length) );
                str = str.substring(max_str_length);
            }
            //剩余部分
            Log.e("LogUtils", str );
        }
    }

    public  static void  loge(String api,String str){
        boolean isDebugLog = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(Constant.IS_DEBUG,true);
        if (isDebugLog){
            if (str == null){
                Log.e("请求接口地址:" + api,"打印的字符串 == null");
                return;
            }
            int max_str_length= 2001;
            //大于4000时
            while (str.length( )> max_str_length){
                Log.e("请求接口地址:" + api, str.substring(0,max_str_length) );
                str = str.substring(max_str_length);
            }
            //剩余部分
            Log.e("请求接口地址:" + api, str );
        }
    }

}
