package com.wuxiantao.wxt.ui.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MineMenuBean;
import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.presenter.MinePresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.MoreIncomeActivity;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static com.wuxiantao.wxt.config.Constant.IS_SHOW_SAMALL_ICON;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TYPE_NORMAL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 个人中心
 */
@ContentView(R.layout.fragment_my_deposit)
public class MyDepositFragment extends MvpFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {
    @ViewInject(R.id.mine_classic_header)
    ClassicsHeader mine_classic_header;
    @ViewInject(R.id.mine_rl)
    SmartRefreshLayout mine_rl;
    @ViewInject(R.id.tv_mine_member)
    TextView tv_mine_member;
    @ViewInject(R.id.tv_mine_friend)
    TextView tv_mine_friend;
    @ViewInject(R.id.tv_mine_code)
    TextView tv_mine_code;
    @ViewInject(R.id.tv_mine_order)
    TextView tv_mine_order;
    @ViewInject(R.id.tv_mine_profit)
    TextView tv_mine_profit;
    @ViewInject(R.id.tv_mine_set)
    TextView tv_mine_set;
    @ViewInject(R.id.tv_mine_freedback)
    TextView tv_mine_freedback;
    @Override
    protected MinePresenter CreatePresenter() {
        return new MinePresenter();
    }
    @Override
    public void initView() {
        if (!isEmpty(getAppToken())) {
            mPresenter.obtainMyDeposit(getAppToken());
            mPresenter.getOrderType(getAppToken());
        }
        initRefreshLoad();
        setOnClikListener(tv_mine_member,
                tv_mine_friend, tv_mine_code, tv_mine_order, tv_mine_profit, tv_mine_freedback);
    }

    private void initRefreshLoad() {
        mine_classic_header.setBackgroundResource(R.drawable.base_title_background);
        mine_rl.setRefreshHeader(mine_classic_header);
        mine_rl.setEnableLoadMore(false);
        mine_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            if (!isEmpty(getAppToken())) {
                mPresenter.obtainMyDeposit(getAppToken());
            }
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });

    }


    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.tv_mine_member:
                break;
            case R.id.tv_mine_friend:
                break;
            case R.id.tv_mine_code:
                break;
            case R.id.tv_mine_order:
                break;
            case R.id.tv_mine_profit:
                $startActivity(MoreIncomeActivity.class);
                break;
            case R.id.tv_mine_set:
                break;
            case R.id.tv_mine_freedback:
                break;
        }
    }
    //我的存款信息获取成功
    @Override
    public void obtainMyDepositSuccess(MyDepositBean bean) {
        String defaultValue = getString(R.string.zero);
        //冻结佣金
        String yall = String.valueOf(bean.getList().getTao_ferrze());
        //红包收益
        String aninter_rate = String.valueOf(bean.getList().getRed_profit());
        //佣金收益
        String deposit_amount = String.valueOf(bean.getList().getYongjin());
        //可用提现
        String amount = BigDecimalUtils.roundStr(bean.getList().getAmount());
        //今日收益
        String todaym = String.valueOf(bean.getList().getTotal());

        //激励每日分红还需多少等级
        String dividend = bean.getList().getFenhong();
        int cha = bean.getList().getCha();
        String text = getString(R.string.current_deposit, dividend, cha);
        int color = Color.parseColor("#FF0000");
    }


    private MineMenuBean createMineMenuBean(int icon, @StringRes int title) {
        MineMenuBean bean = new MineMenuBean(icon, getString(title), isUserAuthorization(IS_SHOW_SAMALL_ICON));
        bean.setType(TYPE_NORMAL);
        return bean;
    }


    @Override
    public void obtainMyDepositFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        x.view().inject(this, rootView);
        return rootView;
    }

    @Event(value = {R.id.tv_mine_member, R.id.tv_mine_friend, R.id.tv_mine_code, R.id.tv_mine_order, R.id.tv_mine_profit, R.id.tv_mine_set, R.id.tv_mine_freedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_member:
                break;
            case R.id.tv_mine_friend:
                break;
            case R.id.tv_mine_code:
                break;
            case R.id.tv_mine_order:
                break;
            case R.id.tv_mine_profit:
                break;
            case R.id.tv_mine_set:
                break;
            case R.id.tv_mine_freedback:
                break;
        }
    }

    @Override
    public void getOrderTypeSuccess(OrderTypeBean bean) {

    }

    @Override
    public void getOrderTypeFailure(String failure) {

    }
}
