package com.wuxiantao.wxt.ui.custom.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;
import com.wuxiantao.wxt.ui.custom.webview.base.BaseWebViewClient;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import java.lang.ref.WeakReference;

import static com.wuxiantao.wxt.config.Api.H5_WE_CHAT_PAY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GameWebViewClient
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-7 下午6:08
 * Description:${DESCRIPTION}
 */
public  class GameWebViewClient  extends BaseWebViewClient {

    public OnInterceptUrlListener listener;
    private LoadingDialog loadingDialog;

    public GameWebViewClient(ProgressBar progressBar,Context context) {
        super(progressBar);
        WeakReference<Context> reference = new WeakReference<>(context);
        loadingDialog = new LoadingDialog.Build(reference.get()).build();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (loadingDialog != null){
            loadingDialog.showLoadingDialog();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (loadingDialog != null){
            loadingDialog.dismissLoadingDialog();
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (loadingDialog != null){
            loadingDialog.dismissLoadingDialog();
        }
    }

    @Override
    public boolean onInterceptUrl(WebView webView, String url) {
        if (url.contains("srvid")){
            String[] array = url.split("&");
            try {
                String srvid = array[1];
                if (listener != null){
                    srvid = srvid.substring(srvid.indexOf('=')).replaceAll("=","").trim();
                    listener.onSaveSrvid(srvid);
                }
            }catch (IndexOutOfBoundsException e){
                listener.onError("区id获取失败");
                e.printStackTrace();
            }
        }
        if (url.contains(H5_WE_CHAT_PAY)) {
            String[] array = url.split("&");
            int type = NumberFormatUtils.StrFindInteger(array[0]);
            int goods_id = NumberFormatUtils.StrFindInteger(array[1]);
            switch (type) {
                //支付宝支付
                case 1:
                    //微信支付
                case 2:
                    if (listener != null) {
                        listener.onCreateOrderPay(type, goods_id);
                    }
                    break;
                //微信分享
                case 3:
                    if (listener != null) {
                        listener.onJumpShare();
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    public void setOnInterceptUrlListener(OnInterceptUrlListener listener) {
        this.listener = listener;
    }


    public interface OnInterceptUrlListener {
        void onCreateOrderPay(int type, int goods_id);
        void onError(String error);
        void onSaveSrvid(String id);
        void onJumpShare();
    }

}
