package com.wuxiantao.wxt.utils;

import com.ssm.sp.SPSecuredUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wuxiantao.wxt.app.BaseApplication;

import static com.wuxiantao.wxt.config.Constant.BINGDING_WECHAT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WeChatUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 下午12:28
 * Description:${DESCRIPTION}
 */
public class WeChatUtils {

    private static final String WECHAT_SCOPE = "snsapi_userinfo";
    private static final String WECHAT_STATE = "wechat_sdk_login";


    //发送微信绑定请求
    public static void sendWeChatLoginRequest(boolean b){
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(BINGDING_WECHAT,b);
        send(createSendAuthReq());
    }

    //发送微信登陆请求
    public static void sendWeChatLoginRequest(){
        sendWeChatLoginRequest(false);
    }


    private static void send(SendAuth.Req req){
        //向微信发送请求
        BaseApplication.getInstance().api.sendReq(req);
    }


    private static SendAuth.Req createSendAuthReq(){
        SendAuth.Req req = new SendAuth.Req();
        req.scope = WECHAT_SCOPE;
        req.state = WECHAT_STATE;
        return req;
    }

}
