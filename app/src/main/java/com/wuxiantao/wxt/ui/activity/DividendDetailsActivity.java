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
import com.wuxiantao.wxt.adapter.recview.DividendDetailsRecViewAdapter;
import com.wuxiantao.wxt.bean.DividedDragonDetailBean;
import com.wuxiantao.wxt.bean.DividedDragonListBean;
import com.wuxiantao.wxt.mvp.contract.DividendContract;
import com.wuxiantao.wxt.mvp.presenter.DividendPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.RedBagMachinePopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendDetailsActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:45
 * Description:${DESCRIPTION} 分红详情
 */
@ContentView(R.layout.activity_dividend_detail)
public class DividendDetailsActivity extends MvpActivity<DividendPresenter, DividendContract.IDividendView> implements DividendContract.IDividendView {
    @ViewInject(R.id.dividend_detail_rl)
    SmartRefreshLayout dividend_detail_rl;
    @ViewInject(R.id.dividend_detail_classic_header)
    ClassicsHeader dividend_detail_classic_header;
    @ViewInject(R.id.dividend_detail_prompt1)
    ImageView dividend_detail_prompt1;
    @ViewInject(R.id.dividend_detail_time1)
    TextView dividend_detail_time1;
    @ViewInject(R.id.dividend_detail_time2)
    TextView dividend_detail_time2;
    @ViewInject(R.id.dividend_detail_yesterday_earnings)
    TextView dividend_detail_yesterday_earnings;
    @ViewInject(R.id.dividend_detail_historical_income)
    TextView dividend_detail_historical_income;
    @ViewInject(R.id.dividend_detail_prompt2)
    ImageView dividend_detail_prompt2;
    @ViewInject(R.id.dividend_detail_yesterday_earnings_yuan)
    TextView dividend_detail_yesterday_earnings_yuan;
    @ViewInject(R.id.dividend_detail_total_number)
    TextView dividend_detail_total_number;
    @ViewInject(R.id.dividend_detail_output_today)
    TextView dividend_detail_output_today;
    @ViewInject(R.id.dividend_detail_waiting_for_output)
    TextView dividend_detail_waiting_for_output;
    @ViewInject(R.id.dividend_detail_rv)
    RecyclerView dividend_detail_rv;

    private DividendDetailsRecViewAdapter adapter;
    private int page = 1;
    private DividedDragonListBean datas;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRefreshLoadMore();
        mPresenter.getDragonDetail(getAppToken());
        setOnClikListener(dividend_detail_prompt1,dividend_detail_prompt2);
    }

    @Override
    protected void widgetClick(int id) {
        switch (id){
            //广告收益提示
            case R.id.dividend_detail_prompt1:
                showAdsIncomeWindow();
                break;
            //分红龙提示
            case R.id.dividend_detail_prompt2:
                showDividedDragonWindow();
                break;
        }
    }

    //广告收益提示
    private void showAdsIncomeWindow() {
        new RedBagMachinePopupWindow.Build(this)
                .setWindowTitle(getString(R.string.ads_income_description))
                .setWindowMessage(getString(R.string.ads_income_msg))
                .builder()
                .showPopupWindow();
    }

    //永久分红提示
    private void showDividedDragonWindow() {
        new RedBagMachinePopupWindow.Build(this)
                .setWindowTitle(getString(R.string.permanent_dividend))
                .setWindowMessage(getString(R.string.permanent_dividend_msg))
                .builder()
                .showPopupWindow();
    }


    private void initRefreshLoadMore(){
        mPresenter.getDragonList(getAppToken(),page);
        dividend_detail_rl.setRefreshHeader(dividend_detail_classic_header);
        dividend_detail_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        dividend_detail_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getDragonList(getAppToken(),page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        dividend_detail_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getList().size() > 0){
                mPresenter.getDragonList(getAppToken(),++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    //分红龙详情信息获取成功
    @Override
    public void getDragonDetailSuccess(DividedDragonDetailBean bean) {
        //昨日广告收益
        String ad_money_yesterday = BigDecimalUtils.roundStr(bean.getAd_money_yesterday());
        //历史广告收益
        String ad_money_historey = BigDecimalUtils.roundStr(bean.getAd_money_historey());
        //昨日收益
        String yesterday_pre = BigDecimalUtils.roundStr(bean.getYesterday_pre());
        //全网总数
        String total = BigDecimalUtils.roundStr(bean.getTotal());
        //今日产出
        String total_already = BigDecimalUtils.roundStr(bean.getTotal_already());
        //待产出
        String total_reset = BigDecimalUtils.roundStr(bean.getTotal_reset());
        //核算时间
        String time = bean.getTime();
        //结算周期
        dividend_detail_time1.setText(time);
        dividend_detail_time2.setText(time);
        dividend_detail_yesterday_earnings.setText(ad_money_yesterday);
        dividend_detail_historical_income.setText(ad_money_historey);
        dividend_detail_yesterday_earnings_yuan.setText(yesterday_pre);
        dividend_detail_total_number.setText(total);
        dividend_detail_output_today.setText(total_already);
        dividend_detail_waiting_for_output.setText(total_reset);
    }


    //分红龙详情列表
    @Override
    public void getDragonListSuccess(DividedDragonListBean listBean) {
        this.datas = listBean;
        initLayout(listBean);
    }



    private void initLayout(DividedDragonListBean bean){
        if (adapter == null){
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(0,0);
            adapter = new DividendDetailsRecViewAdapter(this,bean.getList());
            dividend_detail_rv.setLayoutManager(manager);
            dividend_detail_rv.addItemDecoration(decoration);
            dividend_detail_rv.setAdapter(adapter);
        }else {
            adapter.addList(bean.getList(),page);
        }
    }


    @Override
    protected void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.dividend_detail)
                .build();
    }

    @Override
    protected DividendPresenter CreatePresenter() {
        return new DividendPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void getDragonDetailFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void getDragonListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }
}
