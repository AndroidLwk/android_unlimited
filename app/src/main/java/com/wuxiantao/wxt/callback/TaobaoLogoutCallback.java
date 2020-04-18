package com.wuxiantao.wxt.callback;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaobaoLogoutCallback
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-1 下午12:58
 * Description:${DESCRIPTION} 淘宝退出登陆回调接口
 */
public interface TaobaoLogoutCallback {

    void onSuccess();
    void onFailure(int code,String msg);
}
