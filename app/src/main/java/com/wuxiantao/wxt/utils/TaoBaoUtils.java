package com.wuxiantao.wxt.utils;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.applink.adapter.AlibcFailModeType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlibcLoginBean;
import com.wuxiantao.wxt.callback.TaobaoLoginCallback;
import com.wuxiantao.wxt.callback.TaobaoLogoutCallback;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.IS_TAO_BAO_AUTH;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午11:48
 * Description:${DESCRIPTION}
 */
public class TaoBaoUtils {

    private static volatile TaoBaoUtils instance;

    //天猫超市Supermarket
    public static final String TMALL_SUPERMARKET = "https://chaoshi.m.tmall.com";
    //天猫国际International
    public static final String TMALL_INTERNATIONAL = "https://pages.tmall.com/wow/jinkou/act/zhiying";
    //聚划算Poly cost-effective
    public static final String POLY_COST_EFFECTIVE = "https://jhs.m.taobao.com/m/index.htm";
    //足迹Footprint
    public static final String TAOBAO_FOOT_PRINT = "https://www.taobao.com/markets/footmark/tbfoot";
    //我的淘宝
    public static final String TAOBAO_MAIN_PAGE = "https://m.taobao.com";
    //淘抢购 Panic buying
    public static final String TAOBAO_PANIC_BUYING = "https://qiang.taobao.com";
    //淘宝订单
    public static final String TAOBAO_ORDER = "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm";

    private TaoBaoUtils() {

    }

    public static TaoBaoUtils newInstance() {
        if (instance == null) {
            synchronized (TaoBaoUtils.class) {
                if (instance == null) {
                    instance = new TaoBaoUtils();
                }
            }
        }
        return instance;
    }

    //授权阿里登陆
    public void authorAliLogin(TaobaoLoginCallback callback) {
        AlibcLogin mAlibcLogin = AlibcLogin.getInstance();
        mAlibcLogin.showLogin(new AlibcLoginCallback() {
            /**
             * 参数说明：
             * @param loginResult  3--登出成功
             * @param openId 用户id
             * @param userNick 用户昵称
             */
            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {
                AlibcLoginBean bean = new AlibcLoginBean();
                bean.setUserid(mAlibcLogin.getSession().userid);
                bean.setNick(mAlibcLogin.getSession().nick);
                bean.setAvatarUrl(mAlibcLogin.getSession().avatarUrl);
                bean.setOpenId(mAlibcLogin.getSession().openId);
                bean.setOpenSid(mAlibcLogin.getSession().openSid);
                bean.setTopAccessToken(mAlibcLogin.getSession().topAccessToken);
                bean.setTopAuthCode(mAlibcLogin.getSession().topAuthCode);
                bean.setTopExpireTime(mAlibcLogin.getSession().topExpireTime);
                bean.setSsoToken(mAlibcLogin.getSession().ssoToken);
                if (callback != null) {
                    callback.onLoginSuccess(bean);
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                if (callback != null) {
                    callback.onLoginFailure(code,errorMsg);
                }
            }
        });
    }

    //退出登录
    public void authorAliLoginOut(TaobaoLogoutCallback callback){
        AlibcLogin mAlibcLogin = AlibcLogin.getInstance();
        mAlibcLogin.logout(new AlibcLoginCallback() {
            /**
             * 参数说明：
             * @param loginResult  3--登出成功
             * @param openId 用户id
             * @param userNick 用户昵称
             */
            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {
                if (callback != null){
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                if (callback != null){
                    callback.onFailure(code,errorMsg);
                }
            }
        });
    }

    //打开淘宝界面
    public void openAliHomeApp(Activity act,String url) {
        //判断是否授权登陆
        boolean isAuth = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_TAO_BAO_AUTH,true);
        if (isAuth){
            //设置页面打开方式
            AlibcShowParams showParams = new AlibcShowParams();
            //展示参数配置
            //Native表示唤端，Auto表示不做设置
            showParams.setOpenType(OpenType.Native);
            //表示唤端类型：taobao---唤起淘宝客户端；tmall---唤起天猫客户端
            showParams.setClientType("taobao");
            //唤端返回的scheme
            showParams.setBackUrl("");
            //唤端失败模式:
            //AlibcNativeFailModeNONE：不做处理；
            //AlibcNativeFailModeJumpBROWER：跳转浏览器；
            //AlibcNativeFailModeJumpDOWNLOAD：跳转下载页；
            //AlibcNativeFailModeJumpH5：应用内webview打开）
            showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpH5);
            show(act,url,showParams);
        }else {
            //跳转授权界面
            Intent intent = new Intent(act, MyInformationActivity.class);
            act.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            act.startActivity(intent);
        }
    }


    /**
     *  打开电商组件
     * @param activity 必填
     * @param url  页面类型,必填，不可为null
     * @param showParams   show参数
     * @param //taokeParams 淘客参数
     * @param //trackParam yhhpass参数
     * @param //callback 交易流程的回调，必填，不允许为null；
     * @return 0标识跳转到手淘打开了,1标识用h5打开,-1标识出错
     */
    private void show(Activity activity,String url, AlibcShowParams showParams){
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");
        AlibcTrade.openByUrl(activity, "",url,null,new WebViewClient(),new WebChromeClient(), showParams, null, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
            }
        });
    }

}
