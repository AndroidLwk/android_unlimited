package com.wuxiantao.wxt.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kf5.sdk.system.utils.ToastUtil;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.BalanceContract;
import com.wuxiantao.wxt.mvp.contract.PayContract;
import com.wuxiantao.wxt.mvp.presenter.BalancePresenter;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.presenter.PayPresenter;
import com.wuxiantao.wxt.mvp.view.activity.BaseMvpActivity;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.pay.PayResultListener;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--11:42
 * Description: 充值页面
 * Author: lht
 */
@ContentView(R.layout.activity_pay)
public class PayActivity extends MvpActivity<PayPresenter, PayContract.IPayView> implements PayContract.IPayView, PayResultListener {
    @ViewInject(R.id.bimg_pay_back)
    ImageView bimg_pay_back;
    @ViewInject(R.id.et_pay_sum)
    EditText et_pay_sum;
    @ViewInject(R.id.bimg_pay_sum)
    ImageView bimg_pay_sum;
    @ViewInject(R.id.img_pay_check_wx)
    ImageView img_pay_check_wx;
    @ViewInject(R.id.bimg_pay_check_zfb)
    ImageView bimg_pay_check_zfb;
    @ViewInject(R.id.ll_pay_check_wx)
    LinearLayout ll_pay_check_wx;
    @ViewInject(R.id.ll_pay_check_zfb)
    LinearLayout ll_pay_check_zfb;
    @ViewInject(R.id.bll_pay_affirm)
    LinearLayout bll_pay_affirm;


    @Override
    protected void initView() {
        PayListener.getInstance().addListener(this);
        setStatusBar();
        initListener();
    }

    private void initListener() {
        bimg_pay_back.setOnClickListener(v -> {finish();});
        bimg_pay_sum.setOnClickListener(v -> {
            et_pay_sum.setText("");
            bimg_pay_sum.setVisibility(View.INVISIBLE);
        });
        et_pay_sum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bimg_pay_sum.setVisibility(View.VISIBLE);
            }
        });
        ll_pay_check_wx.setOnClickListener(v -> {
            payType=2;
            img_pay_check_wx.setImageDrawable(getResources().getDrawable(R.drawable.scrapcard_popwindow_b));
            bimg_pay_check_zfb.setImageDrawable(getResources().getDrawable(R.drawable.scrapcard_popwindow_a));
        });
        ll_pay_check_zfb.setOnClickListener(v -> {
            payType=1;
            bimg_pay_check_zfb.setImageDrawable(getResources().getDrawable(R.drawable.scrapcard_popwindow_b));
            img_pay_check_wx.setImageDrawable(getResources().getDrawable(R.drawable.scrapcard_popwindow_a));
        });
        bll_pay_affirm.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_pay_sum.getText())){
                ToastUtils.showToast("请输入充值金额！");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put(TOKEN, getAppToken());
                map.put("money", et_pay_sum.getText().toString());
                map.put("type", payType);
                if (payType==2){
                    mPresenter.onWechatPay(map);
                }else {
                    mPresenter.onAliPay(map);
                }
            }
        });
    }

    //支付方式  充值方式1支付宝2微信
    int payType=2;
    @Override
    protected PayPresenter CreatePresenter() {
        return new PayPresenter();
    }


    @Override
    public void getPayInfoSucceed() {

    }

    //订单号
    private String order_sn;
    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        order_sn = infoBean.getOrder_id();
        PayManager.getInstance(this).pay(PAY_TYPE_WX, infoBean);
    }

    @Override
    public void onAliPay(String order_id, String res) {
        order_sn = order_id;
        PayManager.getInstance(this).pay(PAY_TYPE_ALI, res);
    }

    //支付后查询订单成功
    @Override
    public void onOrderPaySuccess(String msg) {
        ToastUtils.showToast(msg);
        finish();
    }
    //支付后查询订单失败
    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOrderCreateFailure(String failure) {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    //请求服务器查询订单
    @Override
    public void onPaySuccess() {
        if (!isEmpty(order_sn)) {
            mPresenter.checkOrderStatus(getAppToken(), order_sn);
        }
    }

    @Override
    public void onDestroy() {
        PayListener.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));
    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));
    }
}
