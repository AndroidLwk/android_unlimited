package com.wuxiantao.wxt.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.webview.ExampleWebViewClient;
import com.wuxiantao.wxt.ui.custom.webview.ScrollWebView;
import com.wuxiantao.wxt.ui.custom.webview.base.BaseWebChromeClient;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Api.PRIVACY_POLICY;
import static com.wuxiantao.wxt.config.Api.USER_AGREEMENT;
import static com.wuxiantao.wxt.config.Constant.APP_URL;
import static com.wuxiantao.wxt.config.Constant.MAIN_BANNER_URL;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_CONTENT_TYPE;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_MAIN_BANNER;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_PRIVACY_POLICY;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_UPDATE_APP;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_URL;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_USER_AGREEMENT;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_URL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProtocolActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-22 上午10:15
 * Description:${DESCRIPTION} 用户协议/隐私政策/首页banner链接
 */
@ContentView(R.layout.activity_protocol)
public class ProtocolActivity extends BaseActivity {
    @ViewInject(R.id.protocol_rl)
    SmartRefreshLayout protocol_rl;
    @ViewInject(R.id.protocol_back)
    ImageView protocol_back;
    @ViewInject(R.id.protocol_title)
    TextView protocol_title;
    @ViewInject(R.id.protocol_progressbar)
    ProgressBar protocol_progressbar;
    @ViewInject(R.id.protocol_webview)
    ScrollWebView protocol_webview;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        protocol_rl.setEnableRefresh(false);
        protocol_rl.setEnableLoadMore(false);
        Bundle bundle = getBundle();
        if (bundle != null){
            int type = bundle.getInt(WEB_VIEW_CONTENT_TYPE);
            switch (type){
                //隐私政策
                case WEB_VIEW_TYPE_PRIVACY_POLICY:
                    load(PRIVACY_POLICY);
                    break;
                //用户协议
                case WEB_VIEW_TYPE_USER_AGREEMENT:
                    load(USER_AGREEMENT);
                    break;
                //升级app
                case WEB_VIEW_TYPE_UPDATE_APP:
                    String appUrl = bundle.getString(APP_URL);
                    if (!isEmpty(appUrl)){
                        load(appUrl);
                    }
                    break;
                //首页banner链接
                case WEB_VIEW_TYPE_MAIN_BANNER:
                    String webUrl = bundle.getString(MAIN_BANNER_URL);
                    if (!isEmpty(webUrl)){
                        load(webUrl);
                    }
                    break;
                //url连接跳转
                case WEB_VIEW_TYPE_URL:
                    String url = bundle.getString(WEB_VIEW_URL);
                    if (!isEmpty(url)){
                        load(url);
                    }
                    break;

            }
        }
        setOnClikListener(protocol_back);
    }



    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.protocol_back){
            finish();
        }
    }

    @SuppressLint("JavascriptInterface")
    private void load(String url){
        if (!isEmpty(url)){
            setWebClient();
            protocol_webview.loadUrl(url);
        }
    }

    private void setWebClient(){
        protocol_webview.setWebChromeClient(new BaseWebChromeClient(protocol_title,protocol_progressbar));
        protocol_webview.setWebViewClient(new ExampleWebViewClient(protocol_progressbar));
    }

    //点击返回键，返回上一个页面，而不是退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && protocol_webview.canGoBack()) {
            // 返回前一个页面
            protocol_webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        protocol_webview.webViewOnPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        protocol_webview.webViewOnResume();
    }

    @Override
    protected void onDestroy() {
        protocol_webview.webViewOnDestroy();
        super.onDestroy();
    }

}
