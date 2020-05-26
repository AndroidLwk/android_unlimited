package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.BalanceDetailsRecViewAdapter;
import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.mvp.contract.BalanceDetailContract;
import com.wuxiantao.wxt.mvp.presenter.BalanceDetailPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--13:52
 * Description: 余额明细
 * Author: lht
 */
@ContentView(R.layout.activity_balance_details)
public class BalanceDetailsActivity extends MvpActivity<BalanceDetailPresenter, BalanceDetailContract.IDetailView> implements BalanceDetailContract.IDetailView {
    @ViewInject(R.id.img_balance_details_back)
    ImageView img_balance_details_back;
    @ViewInject(R.id.rv_balance_details_list)
    RecyclerView rv_balance_details_list;
    @ViewInject(R.id.srl_balance_details)
    SmartRefreshLayout srl_balance_details;
    @ViewInject(R.id.ch_balance_details_header)
    ClassicsHeader ch_balance_details_header;
    @ViewInject(R.id.tv_detail)
    TextView tv_detail;


    private BalanceDetailsRecViewAdapter adapter;
    ArrayList<BalanceDetailBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    private int type;

    @Override
    protected BalanceDetailPresenter CreatePresenter() {
        return new BalanceDetailPresenter();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 1:
                tv_detail.setText("青铜英雄卡分红明细");
                break;
            case 2:
                tv_detail.setText("黄金英雄卡分红明细");
                break;
            case 3:
                tv_detail.setText("整套英雄卡分红明细");
                break;
            case 4:
                tv_detail.setText("游戏分红明细");
                break;
        }
        setStatusBar();
        StatusBarUtil.setStatusBarColor(BalanceDetailsActivity.this, getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(BalanceDetailsActivity.this, true);
        initPage();
        img_balance_details_back.setOnClickListener(v -> finish());
        mPresenter.obtainBalanceDetails(getAppToken(), page, type);
    }

    private void initPage() {
        adapter = new BalanceDetailsRecViewAdapter(mContext, list);
        rv_balance_details_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_balance_details_list.setAdapter(adapter);

        srl_balance_details.setRefreshHeader(ch_balance_details_header);
        srl_balance_details.setRefreshFooter(new BallPulseFooter(
                Objects.requireNonNull(mContext)).setSpinnerStyle(SpinnerStyle.Scale));
        srl_balance_details.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            list.clear();
            mPresenter.obtainBalanceDetails(getAppToken(), page, type);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void obtainBalanceDetailSuccess(BalanceDetailBean bean) {
        List<BalanceDetailBean.ListBean> mlist = bean.getList();
        for (int i = 0; i < mlist.size(); i++) {
            list.add(mlist.get(i));
        }
        adapter.updataList(list);
    }

    @Override
    public void obtainBalanceDetailFailure(String failure) {

    }
}
