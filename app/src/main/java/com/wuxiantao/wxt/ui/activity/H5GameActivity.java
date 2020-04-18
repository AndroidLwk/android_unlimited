package com.wuxiantao.wxt.ui.activity;

import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.H5GameContract;
import com.wuxiantao.wxt.mvp.presenter.H5GamePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.pay.PayResultListener;
import com.wuxiantao.wxt.ui.custom.webview.GameWebViewClient;
import com.wuxiantao.wxt.ui.custom.webview.ScrollWebView;
import com.wuxiantao.wxt.ui.custom.webview.base.BaseWebChromeClient;
import com.wuxiantao.wxt.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.GAME_ACCOUNT;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:H5GameActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-12-29 上午10:42
 * Description:${DESCRIPTION} h5游戏
 */
@ContentView(R.layout.activity_h5_game)
public class H5GameActivity extends MvpActivity<H5GamePresenter, H5GameContract.IH5GameView> implements H5GameContract.IH5GameView, PayResultListener {

    @ViewInject(R.id.h5_game_progressbar)
    ProgressBar h5_game_progressbar;
    @ViewInject(R.id.h5_game_web_view)
    ScrollWebView h5_game_web_view;

    @Override
    protected H5GamePresenter CreatePresenter() {
        return new H5GamePresenter();
    }

    @Override
    protected void initView() {
        //去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        PayListener.getInstance().addListener(this);
        mPresenter.onGetLoadingImg();
        startLoadGame();
    }


    private void startLoadGame(){
        setWebClient();
        String account = getUserInfo(GAME_ACCOUNT);
        if (!isEmpty(account)){
            String url = getString(R.string.game_url_link,account);
            h5_game_web_view.loadUrl(url);
        }
    }

    private void setWebClient(){
        h5_game_web_view.setWebChromeClient(new BaseWebChromeClient(h5_game_progressbar));
        GameWebViewClient client = new GameWebViewClient(h5_game_progressbar,this);
        h5_game_web_view.setWebViewClient(client);
        client.setOnInterceptUrlListener(new GameWebViewClient.OnInterceptUrlListener() {
            //1支付宝支付 2微信支付
            @Override
            public void onCreateOrderPay(int type, int goods_id) {
                String account = getUserInfo(GAME_ACCOUNT);
                String srvid = getUserInfo("srvid");
                Map<String,Object> map = new HashMap<>();
                map.put("token",account);
                map.put("type",type);
                map.put("goods_id",goods_id);
                map.put("srvid",srvid);
                mPresenter.onOrderCreate(map,type == 1 ? -1 : -2);
            }

            @Override
            public void onError(String error) {
                showOnlyConfirmDialog(error);
            }

            @Override
            public void onSaveSrvid(String id) {
                LogUtils.loge("id=================>" + id);
               saveUserInfo("srvid",id);
            }

            //跳转分享
            @Override
            public void onJumpShare() {
                $startActivity(ShareThemActivity.class);
            }
        });
    }


    //游戏载入图
    @Override
    public void onGetLoadingImgSuccess(String url) {

    }

    //订单号
    private String order_id;

    //生成微信支付订单
    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        this.order_id = infoBean.getOrder_id();
        PayManager.getInstance(H5GameActivity.this).pay(PAY_TYPE_WX,infoBean);
    }

    //生成支付宝支付订单
    @Override
    public void onAliPay(String order_id, String res) {
        this.order_id = order_id;
        PayManager.getInstance(H5GameActivity.this).pay(PAY_TYPE_ALI,res);
    }

    //支付成功 查询服务器支付状态
    @Override
    public void onPaySuccess() {
        if (!isEmpty(order_id)){
            mPresenter.checkOrderStatus(getAppToken(),order_id);
        }else {
            showOnlyConfirmDialog(getString(R.string.pay_success));
        }
    }

    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(getString(R.string.pay_success));
    }

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onGetLoadingImgFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> finish());
    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));
    }

    @Override
    protected void onPause() {
        super.onPause();
        h5_game_web_view.webViewOnPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        h5_game_web_view.webViewOnResume();
    }

    @Override
    protected void onDestroy() {
        h5_game_web_view.webViewOnDestroy();
        PayListener.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    //点击返回键，返回上一个页面，而不是退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && h5_game_web_view.canGoBack()) {
            // 返回前一个页面
            h5_game_web_view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
