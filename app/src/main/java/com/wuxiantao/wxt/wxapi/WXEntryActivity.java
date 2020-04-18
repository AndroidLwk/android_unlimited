package com.wuxiantao.wxt.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.WeChatLoginBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.WeChatLoginContract;
import com.wuxiantao.wxt.mvp.presenter.WeChatLoginPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.share.WeChatShareListener;
import com.wuxiantao.wxt.ui.activity.MenuActivity;
import com.wuxiantao.wxt.utils.IMUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.BINGDING_WECHAT;
import static com.wuxiantao.wxt.config.Constant.GAME_ACCOUNT;
import static com.wuxiantao.wxt.config.Constant.IS_BINDING_WECHAT;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_ERROR;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_SUCCESS;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * 微信登录/分享 的回调页面
 *   用来接收微信的响应信息
 * Created by Administrator on 2018/8/17 0017.
 */

public class WXEntryActivity extends MvpActivity<WeChatLoginPresenter, WeChatLoginContract.ILoginView> implements IWXAPIEventHandler,WeChatLoginContract.ILoginView{

    //Application
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api= BaseApplication.getInstance().api;
        api.handleIntent(getIntent(),this);
    }

    @Override
    protected WeChatLoginPresenter CreatePresenter() {
        return new WeChatLoginPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent,this);
        finish();
    }


    /**
     * 微信发送请求到第三方应用时，会回调到该方法
     * @param baseReq Req
     */
    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }


    /*
	 * 发送到微信请求的响应结果
     * （1）用户同意授权后得到微信返回的一个code，将code替换到请求地址GetCodeRequest里的CODE，同样替换APPID和SECRET
	 * （2）将新地址newGetCodeRequest通过HttpClient去请求，解析返回的JSON数据
	 * （3）通过解析JSON得到里面的openid （用于获取用户个人信息）还有 access_token
	 * （4）同样地，将openid和access_token替换到GetUnionIDRequest请求个人信息的地址里
	 * （5）将新地址newGetUnionIDRequest通过HttpClient去请求，解析返回的JSON数据
	 * （6）通过解析JSON得到该用户的个人信息，包括unionid */
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.getType()){
            //微信登录
            case 1:
                login(baseResp);
                break;
            //微信分享
            case 2:
                share(baseResp);
                break;
        }
        finish();
    }

    private void login(BaseResp baseResp){
        switch (baseResp.errCode){
            //成功
            case BaseResp.ErrCode.ERR_OK:
                SendAuth.Resp mResp = (SendAuth.Resp) baseResp;
                String code = mResp.code;
                boolean isBingding = isUserAuthorization(BINGDING_WECHAT);
                if (isBingding){
                    mPresenter.weChatBingding(getAppToken(),code);
                }else {
                    mPresenter.weChatLogin(code);
                }
                break;
            //取消
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.info(getString(R.string.user_cancel));
                break;
            //错误
            case BaseResp.ErrCode.ERR_AUTH_DENIED:

                break;
        }
    }

    private void share(BaseResp baseResp){
        switch (baseResp.errCode){
            //成功
            case BaseResp.ErrCode.ERR_OK:
                WeChatShareListener.getInstance().addSuccess();
                break;
            //取消
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                WeChatShareListener.getInstance().addCancel();
                break;
            //错误
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                WeChatShareListener.getInstance().addError();
                break;
        }
    }

    //微信登录成功
    @Override
    public void weChatLoginSuccess(WeChatLoginBean bean) {
        saveUserInfo(APP_USER_ID,bean.getId());
        saveUserInfo(USER_HEAD_IMG,bean.getHeadimg());
        saveUserInfo(NICK_NAME,bean.getNickname());
        saveUserInfo(TOKEN,bean.getUnionid());
        saveUserInfo(GAME_ACCOUNT,bean.getAccountname());
        IMUtils.createUser(bean.getId(),this);
        $startActivity(MenuActivity.class,SHIFT_ID,0);
    }

    @Override
    public void weChatLoginFailure(String failure) {
        ToastUtils.error(failure);
    }

    //微信绑定成功
    @Override
    public void weChatBindingSuccess(String msg) {
        EventBus.getDefault().post(new MessageEvent(UPDATE_WECHAT_BINGDING_SUCCESS));
        //是否是绑定微信
        saveUserInfo(BINGDING_WECHAT,false);
        //是否已绑定微信
        saveUserInfo(IS_BINDING_WECHAT,true);
    }

    @Override
    public void weChatBindingFailure(String failure) {
        EventBus.getDefault().post(new MessageEvent(UPDATE_WECHAT_BINGDING_ERROR,failure));
        saveUserInfo(BINGDING_WECHAT,true);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
