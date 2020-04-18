package com.wuxiantao.wxt.callback;

import com.wuxiantao.wxt.bean.AlibcLoginBean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaobaoLoginCallback
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-19 下午3:17
 * Description:${DESCRIPTION} 淘宝登陆回调接口
 */
public interface TaobaoLoginCallback {

    void onLoginSuccess(AlibcLoginBean bean);
    void onLoginFailure(int errorCode,String errorMsg);
}
