package com.wuxiantao.wxt.ui.custom.webview.base;

import android.graphics.Bitmap;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseWebChromeClient
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-30 下午12:00
 * Description:${DESCRIPTION} WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
 */
public class BaseWebChromeClient extends WebChromeClient {

    private TextView   mTextView;
    private ProgressBar mProgressBar;

    public BaseWebChromeClient(TextView tv, ProgressBar progressBar){
        this.mTextView = tv;
        this.mProgressBar = progressBar;
    }

    public BaseWebChromeClient(ProgressBar progressBar){
        this.mProgressBar = progressBar;
    }


    //输出 Web 端日志
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return super.onConsoleMessage(consoleMessage);
    }

    //加载进度回调
    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        mProgressBar.setProgress(newProgress);
    }

    //Js 中调用 alert() 函数，产生的对话框
    @Override
    public boolean onJsAlert(WebView webView, String url, String message, JsResult jsResult) {
        return super.onJsAlert(webView, url, message, jsResult);
    }

    //处理 JS 中的 Confirm对话框
    @Override
    public boolean onJsConfirm(WebView webView, String url, String message, JsResult jsResult) {
        return super.onJsConfirm(webView, url, message, jsResult);
    }

    //处理 JS 中的 Prompt对话框
    @Override
    public boolean onJsPrompt(WebView webView, String url, String message, String defaultValue, JsPromptResult jsPromptResult) {
        return super.onJsPrompt(webView, url, message, defaultValue, jsPromptResult);
    }

    //获取网页标题
    @Override
    public void onReceivedTitle(WebView webView, String title) {
        super.onReceivedTitle(webView, title);
        if (mTextView != null){
            mTextView.setText(title);
        }
    }

    //获取网页icon
    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

}
