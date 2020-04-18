package com.wuxiantao.wxt.ui.custom.webview;

import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebView;
import com.wuxiantao.wxt.ui.custom.webview.base.BaseWebViewClient;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ExampleWebViewClient
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-7 下午6:14
 * Description:${DESCRIPTION}
 */
public class ExampleWebViewClient extends BaseWebViewClient {

    public ExampleWebViewClient(ProgressBar progressBar) {
        super(progressBar);
    }


    @Override
    public boolean onInterceptUrl(WebView webView, String url) {
        return false;
    }
}
