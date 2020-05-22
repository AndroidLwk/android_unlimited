package com.wuxiantao.wxt.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.CardDetailsRecViewAdapter;
import com.wuxiantao.wxt.bean.ScratchCardDetailsBean;
import com.wuxiantao.wxt.mvp.contract.ScratchCardDetailContract;
import com.wuxiantao.wxt.mvp.presenter.ScratchCardDetailPresenter;
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
 * Date: 2020/5/17--17:19
 * Description: 刮刮卡明细
 * Author: lht
 */

@ContentView(R.layout.activity_scratch_card)
public class ScratchCardActivity extends MvpActivity<ScratchCardDetailPresenter, ScratchCardDetailContract.IDetailView> implements ScratchCardDetailContract.IDetailView {
    @ViewInject(R.id.img_balance_details_back)
    ImageView img_balance_details_back;
    @ViewInject(R.id.rv_balance_details_list)
    RecyclerView rv_balance_details_list;
    @ViewInject(R.id.srl_balance_details)
    SmartRefreshLayout srl_balance_details;
    @ViewInject(R.id.ch_balance_details_header)
    ClassicsHeader ch_balance_details_header;
    private CardDetailsRecViewAdapter adapter;
    ArrayList<ScratchCardDetailsBean.ListBean> list = new ArrayList<>();
    private int page = 1;

    @Override
    protected void initView() {
        setStatusBar();
        StatusBarUtil.setStatusBarColor(ScratchCardActivity.this, getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(ScratchCardActivity.this, true);
        initPage();
        img_balance_details_back.setOnClickListener(v -> finish());
        mPresenter.obtainCardDetails(getAppToken(), page);
    }

    private void initPage() {
        adapter = new CardDetailsRecViewAdapter(mContext, list);
        rv_balance_details_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_balance_details_list.setAdapter(adapter);

        srl_balance_details.setRefreshHeader(ch_balance_details_header);
        srl_balance_details.setRefreshFooter(new BallPulseFooter(
                Objects.requireNonNull(mContext)).setSpinnerStyle(SpinnerStyle.Scale));
        srl_balance_details.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            list.clear();
            mPresenter.obtainCardDetails(getAppToken(), page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });

        srl_balance_details.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && list.size() < datas.getCount()) {
                mPresenter.obtainCardDetails(getAppToken(), ++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected ScratchCardDetailPresenter CreatePresenter() {
        return new ScratchCardDetailPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private ScratchCardDetailsBean datas;

    @Override
    public void obtainCardDetailSuccess(ScratchCardDetailsBean bean) {
        this.datas = bean;
        List<ScratchCardDetailsBean.ListBean> mlist = bean.getList();
        for (int i = 0; i < mlist.size(); i++) {
            list.add(mlist.get(i));
        }
        adapter.updataList(list);
    }

    @Override
    public void obtainCardDetailFailure(String failure) {

    }
}
