package com.wuxiantao.wxt.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/9/7 0007 12:58 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class URLUtils {

    /**
     * 检查url是否合法
     * @param url
     * @return
     */
    public static boolean  isUrlLegal(String url){
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }




}
