package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.wuxiantao.wxt.utils.WeChatUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_CONTENT_TYPE;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_PRIVACY_POLICY;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_USER_AGREEMENT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WeChatLoginActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 上午11:00
 * Description:${DESCRIPTION} 微信登陆
 */
@ContentView(R.layout.activity_wechat_login)
public class WeChatLoginActivity extends BaseActivity {
    @ViewInject(R.id.wechat_login_rl)
    SmartRefreshLayout wechat_login_rl;
    @ViewInject(R.id.wechat_login)
    StateButton wechat_login;
    @ViewInject(R.id.wechat_login_other)
    TextView wechat_login_other;
    @ViewInject(R.id.wechat_login_server)
    TextView wechat_login_server;
    @ViewInject(R.id.wechat_login_privacy)
    TextView wechat_login_privacy;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        wechat_login_rl.setEnableRefresh(false);
        wechat_login_rl.setEnableLoadMore(false);
        setOnClikListener(wechat_login,wechat_login_other,wechat_login_server,wechat_login_privacy);
    }


    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.wechat_login:
                WeChatUtils.sendWeChatLoginRequest();
                break;
            case R.id.wechat_login_other:
                $startActivity(VerCodeLoginActivity.class);
                break;
            //用户协议
            case R.id.wechat_login_server:
                Bundle bundle = new Bundle();
                bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_USER_AGREEMENT);
                $startActivity(ProtocolActivity.class,bundle);
                break;
            //隐私政策
            case R.id.wechat_login_privacy:
                Bundle b = new Bundle();
                b.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_PRIVACY_POLICY);
                $startActivity(ProtocolActivity.class,b);
                break;
        }
    }


    // 第一次按下返回键的事件
    private long firstPressedTime;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            BaseApplication.getInstance().exitApp();
            super.onBackPressed();
        } else {
            ToastUtils.info(getResources().getString(R.string.drop_out_app));
            firstPressedTime = System.currentTimeMillis();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放百川相应的资源引用
        AlibcTradeSDK.destory();
    }
}
