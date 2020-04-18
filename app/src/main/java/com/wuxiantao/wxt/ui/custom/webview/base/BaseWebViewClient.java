package com.wuxiantao.wxt.ui.custom.webview.base;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;

import com.ssm.sp.SPSecuredUtils;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.LogUtils;

import static com.wuxiantao.wxt.config.Constant.IS_DEBUG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseWebViewClient
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-30 上午11:58
 * Description:${DESCRIPTION} WebViewClient主要帮助WebView处理各种通知、请求事件
 */
public abstract class BaseWebViewClient extends WebViewClient {

    private ProgressBar mProgressBar;


    public BaseWebViewClient(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    //页面开始加载
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        //获取Cookie
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(url);
        LogUtils.loge("cookie================>" + cookie);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    //WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，
    // 除非本地有当前 url 对应有缓存，否则就会加载。
    @Override
    public void onLoadResource(WebView webView, String url) {
        super.onLoadResource(webView, url);
    }

    //页面加载完成
    @Override
    public void onPageFinished(WebView view, String url) {
        mProgressBar.setVisibility(View.GONE);
        //开启调试
        boolean isDebug = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_DEBUG, false);
        if (isDebug) {
            String js = "var newscript = document.createElement(\"script\");";
            js += "newscript.src=\"https://cdn.bootcss.com/eruda/1.4.3/eruda.min.js\";";
            js += "document.body.appendChild(newscript);";
            view.loadUrl("javascript:" + js);
        }
    }


    //页面加载错误
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    //访问证书出错，handler.cancel()取消加载，handler.proceed()对错误也继续加载。
    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        //https忽略证书问题
        sslErrorHandler.proceed();
    }


    //回调拦截 url
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (onInterceptUrl(webView,url)){
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, url);
    }

    public abstract boolean onInterceptUrl(WebView webView, String url);

    //以拦截某一次的 request 来返回我们自己加载的数据
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
        return super.shouldInterceptRequest(webView, url);
    }



}
