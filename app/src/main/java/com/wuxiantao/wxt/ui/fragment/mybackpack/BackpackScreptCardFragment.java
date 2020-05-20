package com.wuxiantao.wxt.ui.fragment.mybackpack;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.kf5.sdk.im.ui.KF5ChatActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MyBackpackRecRecViewAdapter;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.presenter.MyBackpackPrewenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.activity.ChangePassWordActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.HeroScrollActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.PointToCardActivity;
import com.wuxiantao.wxt.ui.popupwindow.OrderPayModePopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.PackOperationPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.TransferScratchCardPopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_SETPAY_PASS;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * 刮刮卡fragment
 */

@ContentView(R.layout.fragment_backpack)
public class BackpackScreptCardFragment extends MvpFragment<MyBackpackPrewenter, MyBackpackContract> implements MyBackpackContract {
    @ViewInject(R.id.fragment_tao_bao_featured_sub_classic_header)
    ClassicsHeader fragment_tao_bao_featured_sub_classic_header;
    @ViewInject(R.id.rv_myBackpack)
    RecyclerView rv_myBackpack;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_rl)
    SmartRefreshLayout fragment_tao_bao_featured_sub_rl;
    private int page = 1;
    private MyBackpackRecRecViewAdapter mAdapter;
    private int pid;

    @Override
    protected void initView() {
        PayListener.getInstance().addListener(this);
        if (getArguments() != null) {
            pid = getArguments().getInt("pid");
        }
        fragment_tao_bao_featured_sub_rl.setEnableLoadMore(false);
        fragment_tao_bao_featured_sub_rl.setRefreshHeader(fragment_tao_bao_featured_sub_classic_header);
        fragment_tao_bao_featured_sub_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        fragment_tao_bao_featured_sub_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.myBox(getAppToken(), page, pid);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        rv_myBackpack.setLayoutManager(manager);
        mPresenter.myBox(getAppToken(), page, pid);
    }
    public void refreshData() {
        if (mPresenter != null) {
            mPresenter.myBox(getAppToken(), 1, pid);
        }
    }

    private MyBoxInfo.ListBean myBackpackBean;//点击事件的数据
    private String id, pass, num;//转赠用到的传参

    //操作弹框
    private void showOperationDialog(MyBoxInfo.ListBean myBackpackBean) {
        this.myBackpackBean = myBackpackBean;
        new PackOperationPopupWindow.Build(getContext())
                .setWindowAnimStyle(R.style.custom_dialog)
                .setButton(myBackpackBean)
                .setOnPopupClickListener(new PackOperationPopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void goOpenCard() {
                        $startActivity(PointToCardActivity.class);
                    }

                    @Override
                    public void cardTransfer() {
                        if (!getUserInfo(IS_SETPAY_PASS).equals("1")) {
                            showOnlyConfirmDialog("未设置交易密码，确定前往设置?", (dialog, which) -> $startActivity(ChangePassWordActivity.class));
                            return;
                        }
                        new TransferScratchCardPopupWindow.Build(getContext())
                                .setWindowAnimStyle(R.style.custom_dialog)
                                .setTitleText(myBackpackBean.getPid() == 226 ? getResources().getString(R.string.pointtocard_text15) : getResources().getString(R.string.pointtocard_text18))
                                .setOnItemClickListener((id, num, sxxh, pass) -> {
                                    if (id.equals("null")) {
                                        showOnlyConfirmDialog("转赠ID不能为空");
                                    } else if (num.equals("null")) {
                                        showOnlyConfirmDialog("转赠数量不能为空");
                                    } else if (pass.equals("null")) {
                                        showOnlyConfirmDialog("二级密码不能为空");
                                    } else {
                                        //调接口
                                        BackpackScreptCardFragment.this.id = id;
                                        BackpackScreptCardFragment.this.pass = pass;
                                        BackpackScreptCardFragment.this.num = num;
                                        showLoading();
                                        mPresenter.exchange(getAppToken(), myBackpackBean.getCard_id() + "", id, pass, num);
                                    }
                                })
                                .builder().showPopupWindow();
                    }

                    @Override
                    public void carUse() {
                        if (myBackpackBean.getPid() == 2) {//皮肤卡使用
                            $startActivity(KF5ChatActivity.class);
                            mPresenter.myBox(getAppToken(), page, pid);
                        } else {//现金卡使用
                            mPresenter.useCard(getAppToken(), myBackpackBean.getCard_id() + "", "1");
                        }
                    }

                    @Override
                    public void synthesis() {
                        getActivity().finish();
                        $startActivity(HeroScrollActivity.class);
                    }

                    @Override
                    public void discard() {
                        showDisCardDialog("确定销毁卡片？", (dialog, which) -> mPresenter.discard(getAppToken(), myBackpackBean.getCard_id() + "", "1"));
                    }
                })
                .builder().showPopupWindow();
    }

    private void showDisCardDialog(String title, DialogInterface.OnClickListener listener) {
        showDialog(title, listener);
    }

    private void initVerLayout(List<MyBoxInfo.ListBean> list) {
        if (mAdapter == null) {
            mAdapter = new MyBackpackRecRecViewAdapter(getContext(), list);
            mAdapter.setOnItemClickListener((myBackpackBean, position) -> {
                if (!TextUtils.isEmpty(myBackpackBean.getImg())) {
                    showOperationDialog(myBackpackBean);
                }

            });
            rv_myBackpack.setAdapter(mAdapter);
        } else {
            mAdapter.addList(list == null ? null : list, page);
        }
    }

    @Override
    public void onFailure(String msg) {
        if (msg.equals("余额不足!")) {
            showOnlyConfirmDialog("余额不足，确定用支付宝/微信支付？", (dialog, which) -> {
                new OrderPayModePopupWindow.Build(getContext())
                        .setOnItemClickListener(payType -> {
                            if (payType == 1) {//支付宝
                                showLoading();
                                mPresenter.exchange_alipay(getAppToken(), myBackpackBean.getCard_id() + "", id, pass, num);
                            } else {
                                showLoading();
                                mPresenter.exchange_wx(getAppToken(), myBackpackBean.getCard_id() + "", id, pass, num);
                            }
                        })
                        .setPopupWindowAnimStyle(R.style.custom_dialog)
                        .builder()
                        .showPopupWindow();
            });
            return;
        }
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void exchangeSuccess() {
        showOnlyConfirmDialog("转赠成功");
        mPresenter.myBox(getAppToken(), page, pid);
    }

    @Override
    public void useCardSuccess(String msg) {
        mPresenter.myBox(getAppToken(), page, pid);
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void discardSuccess(String msg) {
        mPresenter.myBox(getAppToken(), page, pid);
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void showMyBackPack(MyBoxInfo bean) {
        initVerLayout(mPresenter.getBoxAllData(bean.getList(), bean.getBox_volume()));
    }

    @Override
    public void showBoxType(List<BoxTypeBean> list) {

    }

    @Override
    public void isSetPayPasswordSuccess(IsSetPayPassword info) {

    }

    @Override
    protected MyBackpackPrewenter CreatePresenter() {
        return new MyBackpackPrewenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        //微信支付
        order_sn = infoBean.getOrder_id();
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_WX, infoBean);
    }

    @Override
    public void onAliPay(String order_id, String res) {
        //支付宝支付
        order_sn = order_id;
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_ALI, res);
    }

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.myBox(getAppToken(), page, pid));
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //订单号
    private String order_sn;

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
