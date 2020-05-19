package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.KuorongInfoBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.BackpackExpansionContract;
import com.wuxiantao.wxt.mvp.presenter.BackpackExpansionPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.popupwindow.OrderPayModePopupWindow;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;

@ContentView(R.layout.activity_backpackexpansion)
public class BackpackExpansionActivity extends MvpActivity<BackpackExpansionPresenter, BackpackExpansionContract> implements BackpackExpansionContract {
    @ViewInject(R.id.cntoolbar_title)
    CNToolbar cntoolbar_title;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_content)
    TextView tv_content;
    @ViewInject(R.id.statebutton_confirm)
    StateButton statebutton_confirm;
    @ViewInject(R.id.tv_one)
    TextView tv_one;
    @ViewInject(R.id.tv_two)
    TextView tv_two;
    @ViewInject(R.id.tv_three)
    TextView tv_three;
    @ViewInject(R.id.tv_four)
    TextView tv_four;
    @ViewInject(R.id.tv_five)
    TextView tv_five;

    @Override
    protected void initView() {
        setStatusBar();
        mPresenter.kuorongInfo(getAppToken());
        setOnClikListener(statebutton_confirm);
        PayListener.getInstance().addListener(this);
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
    }

    @Override
    protected void onDestroy() {
        PayListener.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    protected BackpackExpansionPresenter CreatePresenter() {
        return new BackpackExpansionPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void widgetClick(int id) {
        if (id == R.id.statebutton_confirm) {
            if (Double.parseDouble(info.getMoney()) >= info.getPrice()) {
                showLoading();
                mPresenter.addbox_balance(getAppToken(), "3");
                return;
            }
            new OrderPayModePopupWindow.Build(this)
                    .setOnItemClickListener(payType -> {
                        if (payType == 1) {//支付宝
                            showLoading();
                            mPresenter.addbox_alipay(getAppToken(), "1");
                        } else {
                            showLoading();
                            mPresenter.addbox_wx(getAppToken(), "2");
                        }
                    })
                    .setOrderPayMoney(info.getPrice() + "")
                    .setPopupWindowAnimStyle(R.style.custom_dialog)
                    .builder()
                    .showPopupWindow();
        }
    }

    @Override
    public void addbox_balanceSuccess(String msg) {
        showOnlyConfirmDialog(msg);
        noTifyrefreshData();
    }

    @Override
    public void addbox_balanceFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    private KuorongInfoBean info;//扩容信息

    @Override
    public void showKuorongInfo(KuorongInfoBean info) {
        this.info = info;
        tv_one.setText("用户余额：￥" + info.getMoney());
        tv_two.setText("总容量：" + info.getNow());
        tv_three.setText("已使用：" + info.getTotal());
        tv_four.setText("暂存:" + info.getStash());
        tv_five.setText("扩容价格：" + info.getPrice());
        tv_title.setText(info.getIntro().get(0).getTitle());
        tv_content.setText(info.getIntro().get(0).getContent());
        statebutton_confirm.setText(info.getPrice() + "元扩容");
    }

    @Override
    public void showKuorongInfo_failure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    //订单号
    private String order_sn;

    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        //微信支付
        order_sn = infoBean.getOrder_id();
        PayManager.getInstance(this).pay(PAY_TYPE_WX, infoBean);
    }

    @Override
    public void onAliPay(String order_id, String res) {
        //支付宝支付
        order_sn = order_id;
        PayManager.getInstance(this).pay(PAY_TYPE_ALI, res);
    }

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);

    }

    @Override
    public void onOrderPaySuccess(String msg) {
        noTifyrefreshData();
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onPaySuccess() {//请求服务器查询订单
        if (!isEmpty(order_sn)) {
            mPresenter.checkOrderStatus(getAppToken(), order_sn);
        }
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));
    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));
    }

    /**
     * 通知刷新列表
     */
    public void noTifyrefreshData() {

    }
}
