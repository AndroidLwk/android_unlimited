package com.wuxiantao.wxt.utils;

import java.text.DecimalFormat;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FormatUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 上午9:42
 * Description:${DESCRIPTION}
 */
public class FormatUtils {

    //定义GB的计算常量
    private static final int GB = 1024 * 1024 *1024;
    //定义MB的计算常量
    private static final int MB = 1024 * 1024;
    //定义KB的计算常量
    private static final int KB = 1024;

    public static String bytes2kb(long bytes){
        //格式化小数
        DecimalFormat format = new DecimalFormat("###.0");
        if (bytes / GB >= 1){
            return format.format(bytes / GB) + "GB";
        }
        else if (bytes / MB >= 1){
            return format.format(bytes / MB) + "MB";
        }
        else if (bytes / KB >= 1){
            return format.format(bytes / KB) + "KB";
        }else {
            return  bytes + "B";
        }
    }
}
