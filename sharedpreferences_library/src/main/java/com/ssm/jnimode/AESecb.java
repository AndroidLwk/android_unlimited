package com.ssm.jnimode;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AESecb
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-5 下午1:21
 * Description:${DESCRIPTION}
 */
public class AESecb {
    static {
        System.loadLibrary("aesecb");
    }

    public native String getKey();
    public native String getIvParameter();

}
