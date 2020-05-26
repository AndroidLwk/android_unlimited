package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ProfitRecordingRecViewAdpter;
import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.mvp.contract.ProfitRecordingContract;
import com.wuxiantao.wxt.mvp.presenter.ProfitRecordingPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.MathUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-25 下午4:35
 * Description:${DESCRIPTION} 收益记录
 */
@ContentView(R.layout.activity_profit_recording)
public class ProfitRecordingActivity extends MvpActivity<ProfitRecordingPresenter, ProfitRecordingContract.IRecordingView> implements ProfitRecordingContract.IRecordingView {

    @ViewInject(R.id.profit_recording_rl)
    SmartRefreshLayout profit_recording_rl;
    @ViewInject(R.id.profit_recording_classic_header)
    ClassicsHeader profit_recording_classic_header;
    @ViewInject(R.id.profit_recording_rv)
    RecyclerView profit_recording_rv;
    @ViewInject(R.id.profit_recording_chartview)
    LineChart profit_recording_chartview;
    @ViewInject(R.id.profit_recording_amount)
    TextView profit_recording_amount;

    private ProfitRecordingRecViewAdpter adpter;
    private LoadingDialog loadingDialog;
    private int page = 1;
    private ProfitRecordingBean datas;
    private List<Float> valueList = new ArrayList<>();

    @Override
    public void initView(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.getProfitRecording(getAppToken(), page);
        profit_recording_rl.setRefreshHeader(profit_recording_classic_header);
        profit_recording_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        profit_recording_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getProfitRecording(getAppToken(), page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        profit_recording_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1) {
                mPresenter.getProfitRecording(getAppToken(), ++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            } else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    protected ProfitRecordingPresenter CreatePresenter() {
        return new ProfitRecordingPresenter();
    }

    @Override
    public void getProfitRecordingSuccess(ProfitRecordingBean bean) {
        profit_recording_amount.setText(MathUtils.formatCurrency(String.valueOf(bean.getAmount()), 5));
        this.datas = bean;
        initVerListLayout(bean);
        initChartViewData(bean.getSeven());
    }

    private void initChartViewData(List<ProfitRecordingBean.SevenBean> beans) {
        //设置数据
        List<Entry> list = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        int xCount = beans.size();
        for (int i = 0; i < xCount; i++) {
            String date = beans.get(i).getDate().substring(5);
            double total = beans.get(i).getTotal();
            list.add(new Entry(i, (float) total));
            dateList.add(date);
        }
        if (!valueList.isEmpty()) {
            valueList.clear();
        }
        for (ProfitRecordingBean.SevenBean bean : beans) {
            valueList.add((float)bean.getTotal());
        }
        float max = Collections.max(valueList) * 2;
        setXYAxisStyle(list, dateList, xCount,max);
    }


    private void setXYAxisStyle(List<Entry> list, List<String> dateList, int xCount,float max) {
        //一个LineDataSet就是一条线
        LineDataSet set = new LineDataSet(list, getString(R.string.income_record));
        //设置曲线值的圆点是实心还是空心
        set.setDrawCircleHole(true);
        //设置线模式 默认折线
        set.setMode(LineDataSet.Mode.LINEAR);
        //线条宽度
        set.setLineWidth(1f);
        //线条颜色
        set.setColor(Color.parseColor("#1187D6"));
        //圆点颜色
        set.setCircleColor(Color.parseColor("#1187D6"));

        //格式化数据
        set.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return MathUtils.formatCurrency(String.valueOf(value), 3);
            }
        });

        LineData data = new LineData(set);
        //透明化图例
        Legend mLegend = profit_recording_chartview.getLegend();
        mLegend.setForm(Legend.LegendForm.NONE);
        mLegend.setTextColor(Color.WHITE);

        //得到X轴
        XAxis mXAxis = profit_recording_chartview.getXAxis();
        //设置X轴的位置（默认在上方）
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔（因为此图有缩放功能，X轴,Y轴可设置可缩放）
        mXAxis.setGranularity(1f);
        //设置X轴的刻度数量 第二个参数表示是否平均分配
        mXAxis.setLabelCount(xCount, true);
        //是否绘制轴线
        mXAxis.setDrawAxisLine(false);
        //设置x轴上每个点对应的线
        mXAxis.setDrawGridLines(true);
        //绘制标签  指x轴上的对应数值
        mXAxis.setDrawLabels(true);
        //设置x轴起点和终点label不超出屏幕
        mXAxis.setAvoidFirstLastClipping(true);
        //设置是否显示X轴表格
        mXAxis.setDrawGridLines(false);
        //设置X轴值为字符串
        mXAxis.setValueFormatter(new IndexAxisValueFormatter(dateList));

        //得到Y轴
        YAxis mLeftYAxis = profit_recording_chartview.getAxisLeft();
        YAxis mlRightYAxis = profit_recording_chartview.getAxisRight();
        mLeftYAxis.setAxisMinimum(0f);
        mLeftYAxis.setAxisMaximum(max);
        mlRightYAxis.setAxisMinimum(0f);
        mlRightYAxis.setAxisMaximum(max);
        //设置是否显示Y轴表格
        mlRightYAxis.setDrawGridLines(false);
        mlRightYAxis.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value);
            }
        });

        //隐藏描述
        Description mDescription = new Description();
        mDescription.setEnabled(false);
        profit_recording_chartview.setDescription(mDescription);

        //显示边界
        profit_recording_chartview.setDrawBorders(false);
        profit_recording_chartview.setPinchZoom(false);
        //设置是否可以触摸
        profit_recording_chartview.setTouchEnabled(true);
        //设置图表是否可缩放
        profit_recording_chartview.setScaleEnabled(false);
        //设置是否可以拖拽
        profit_recording_chartview.setDragEnabled(true);
        //设置是否显示表格
        profit_recording_chartview.setDrawGridBackground(false);
        //设置图表背景颜色
        profit_recording_chartview.setBackgroundColor(Color.WHITE);
        //设置x轴动画时间
        profit_recording_chartview.animateX(2000);
        //set data
        profit_recording_chartview.setData(data);
    }

    private void initVerListLayout(ProfitRecordingBean bean) {
        if (adpter == null) {
            adpter = new ProfitRecordingRecViewAdpter(this, bean.getSall());
            LinearLayoutManager manager = new LinearLayoutManager(this);
            RecViewItemDecoration itemDecoration = new RecViewItemDecoration(this, 1);
            manager.setOrientation(1);
            profit_recording_rv.setLayoutManager(manager);
            profit_recording_rv.addItemDecoration(itemDecoration);
            profit_recording_rv.setAdapter(adpter);
        } else {
            adpter.addList(bean.getSall(), page);
        }
    }

    @Override
    public void getProfitRecordingFailure(String failure) {
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


    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.profit_recording).build();
    }

}
