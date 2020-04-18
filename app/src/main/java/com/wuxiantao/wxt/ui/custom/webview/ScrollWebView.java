package com.wuxiantao.wxt.ui.custom.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.wuxiantao.wxt.utils.LogUtils;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/11/29 0029 17:54 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class ScrollWebView extends WebView {

    public ScrollWebView(Context context) {
        this(context, null);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView();
        initWebSetting();
    }

    private void initWebView() {
        //添加js监听 这样html就能调用客户端
        this.addJavascriptInterface(this, "Android");
        //是否可以后退
        this.canGoBack();
        //是否可以前进
        this.canGoForward();
        this.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSetting() {
        WebSettings webSettings = this.getSettings();
        //允许使用js
        webSettings.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //将图片调整到适合webView的大小 useWideViewPort
        webSettings.setUseWideViewPort(true);
        //setLayoutAlgorithm
        //将webView的横向竖向的scrollBar都禁用掉，将不再与ScrollView冲突，解决了大面积空白的问题
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置WebView中加载页面字体变焦百分比，默认100
        webSettings.setTextZoom(100);
        //userAgent
        webSettings.setUserAgent(webSettings.getUserAgentString());
        //缩放操作
        //支持缩放，默认为true。是下面那个的前提
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);

        webSettings.setAllowContentAccess(true);
        webSettings.setLoadWithOverviewMode(true);

        //缓存
        //是否启用缓存模式
        webSettings.setAppCacheEnabled(true);
        //设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置最大缓存值
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        //是否开启数据库缓存
        webSettings.setDatabaseEnabled(true);
        //是否开启DOM缓存
        webSettings.setDomStorageEnabled(true);

        //设置是否开启定位功能，默认true
        webSettings.setGeolocationEnabled(false);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);

        this.setInitialScale(300);

        this.setVerticalScrollBarEnabled(false);
        this.setVerticalScrollbarOverlay(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setHorizontalScrollbarOverlay(false);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // webview的高度
        float webcontent = getContentHeight() * getScale();
        // 当前webview的高度
        float webnow = getHeight() + getScrollY();
        if (Math.abs(webcontent - webnow) < 1) {
            // 已经处于底端
            if (listener != null) {
                listener.onPageEnd(l, t, oldl, oldt);
            }
        } else if (getScrollY() == 0) {
            //已经处于顶端
            if (listener != null) {
                listener.onPageTop(l, t, oldl, oldt);
            }
        } else {
            //正在滑动
            if (listener != null) {
                listener.onScrollChanged(l, t, oldl, oldt);
            }
        }
    }

    //给js发送一个字符串
    @JavascriptInterface
    public String setParameter(){
        return "Android与JavaScript交互";
    }


    //获取js返回的数据
    @JavascriptInterface
    public void getJSReBack(String result){
        LogUtils.loge("js返回的数据:" + result);
    }

    //js数据返回
    @JavascriptInterface
    public void webLoadingComplete(){
        if (loadingListener != null){
            loadingListener.onWebLoadingComplete();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //按下
            if (listener != null) {
                listener.onTouchScreen();
            }
        }
        return super.onTouchEvent(event);
    }

    private OnScrollChangeListener listener;

    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        this.listener = l;
    }

    //激活 js 调用，设置 webView 活跃状态
    @SuppressLint("SetJavaScriptEnabled")
    public void webViewOnResume() {
        this.onResume();
        this.getSettings().setJavaScriptEnabled(true);
    }

    //退出界面暂停 webView的活跃，并且关闭 JS 支持
    public void webViewOnPause() {
        this.onPause();
        this.getSettings().setLightTouchEnabled(false);
    }

    //关闭界面时，销毁webview
    public void webViewOnDestroy() {
        this.clearCache(true);
        this.clearHistory();
        this.clearFormData();
        this.destroy();
    }


    public interface OnScrollChangeListener {
        void onPageEnd(int l, int t, int oldl, int oldt);

        void onPageTop(int l, int t, int oldl, int oldt);

        void onScrollChanged(int l, int t, int oldl, int oldt);

        void onTouchScreen();
    }

    private OnWebLoadingListener loadingListener;

    public void setOnWebLoadingListener(OnWebLoadingListener listener){
        this.loadingListener = listener;
    }

    public interface OnWebLoadingListener{
        void onWebLoadingComplete();
    }
}
