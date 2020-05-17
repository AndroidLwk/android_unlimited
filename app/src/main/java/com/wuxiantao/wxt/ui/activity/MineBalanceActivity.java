package com.wuxiantao.wxt.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.BalanceBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.BalanceContract;
import com.wuxiantao.wxt.mvp.contract.PointToCardContract;
import com.wuxiantao.wxt.mvp.presenter.BalancePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.PointToCardActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;
import static com.wuxiantao.wxt.config.Constant.IS_ATTENTION_PUBLIC;
import static com.wuxiantao.wxt.config.Constant.UPDATE_MINE_BALANCE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineBalanceActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 下午4:57
 * Description:${DESCRIPTION} 我的余额
 */
@ContentView(R.layout.activity_mine_balance)
public class MineBalanceActivity extends MvpActivity<BalancePresenter, BalanceContract.IBalanceView> implements BalanceContract.IBalanceView {
    @ViewInject(R.id.mine_balance_back)
    ImageView mine_balance_back;
    @ViewInject(R.id.tv_balance_top_up)
    LinearLayout tv_balance_top_up;
    @ViewInject(R.id.tv_balance_withdraw)
    LinearLayout tv_balance_withdraw;
    @ViewInject(R.id.tv_balance_money)
    TextView tv_balance_money;
    @ViewInject(R.id.tv_balance_cash)
    TextView tv_balance_cash;
    @ViewInject(R.id.tv_balance_gogk)
    TextView tv_balance_gogk;
    @ViewInject(R.id.mine_balance_details)
    TextView mine_balance_details;

    private LoadingDialog loadingDialog;
    private BalanceBean datas;

    @Override
    public void initView() {
        setStatusBar();
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.white)); 设置状态栏颜色
//        StatusBarUtil.setStatusBarDarkTheme(this, true);  设置状态栏图标是否深色切换
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
        mPresenter.obtainBalance(getAppToken());
//        mPresenter.obtainBalance("o1voQ1XGQBGDT1F6UjC4xnLbFavc");
        setOnClikListener(mine_balance_back,tv_balance_top_up,tv_balance_withdraw,tv_balance_gogk,mine_balance_details);
    }


    @Override
    public void widgetClick(int viewId) {
        Bundle bundle = null;
        if (datas != null) {
            bundle = new Bundle();
        }
        switch (viewId) {
            case R.id.mine_balance_back:
                finish();
                break;
            case R.id.tv_balance_top_up:
                $startActivity(PayActivity.class);
                break;
            case R.id.tv_balance_withdraw:
                $startActivity(BalanceWithdrawActivity.class);
                break;
            case R.id.tv_balance_gogk:
                $startActivity(PointToCardActivity.class);
                break;
            case R.id.mine_balance_details: //余额明细
//                $startActivity(PointToCardActivity.class);
                break;
        }
    }



    @Override
    protected BalancePresenter CreatePresenter() {
        return new BalancePresenter();
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void obtainBalanceSuccess(BalanceBean bean) {
        this.datas = bean;
        try {
            //余额
            Double money = Double.valueOf(bean.getMoney());
            tv_balance_money.setText("￥" +money+"元");
            //累计提现金额
            Double cash = Double.valueOf(bean.getCash());
            tv_balance_cash.setText("已累计提现￥"+cash+"元");
        } catch (NumberFormatException e) {
            tv_balance_money.setText("￥" +R.string.zero+"元");
            tv_balance_cash.setText("已累计提现￥"+R.string.zero+"元");
        }
//        try {
//            //佣金余额
//            double amount = Double.valueOf(bean.getAmount());
//            //红包余额
//            double volumes = Double.valueOf(bean.getVolumes());
//            //总和
//            double total = bean.getTotal();
//            mine_balance_tv.setText(total <= 0 ? getString(R.string.zero) : BigDecimalUtils.round(String.valueOf(total), DECIMAL_BIT));
//            mine_balance_withdraw_red_bag.setText(getString(R.string.withdraw_red_bag, getString(R.string.red_bg), volumes));
//            mine_balance_withdraw_commission.setText(getString(R.string.withdraw_red_bag, getString(R.string.commission), amount));
//        } catch (NumberFormatException e) {
//            mine_balance_tv.setText(R.string.zero);
//            mine_balance_withdraw_red_bag.setEnabled(false);
//            mine_balance_withdraw_commission.setEnabled(false);
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        if (event.getType() == UPDATE_MINE_BALANCE){
            mPresenter.obtainBalance(getAppToken());
        }
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public void obtainBalanceFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }
}
