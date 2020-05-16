package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewPagerAdapter;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.mvp.contract.MineFansiContract;
import com.wuxiantao.wxt.mvp.presenter.MineFansiPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiDirectlyFragment;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiIndirectFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.FANSI_TYPE_POTENTIAL;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineFanSiActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午4:24
 * Description:${DESCRIPTION}我的粉丝
 */
@ContentView(R.layout.activity_mine_fansi)
public class MineFanSiActivity extends MvpActivity<MineFansiPresenter, MineFansiContract.IFansiView> implements MineFansiContract.IFansiView {
    @ViewInject(R.id.mine_fansi_back)
    private RelativeLayout mine_fansi_back;
    @ViewInject(R.id.mine_fansi_title_img)
    private ImageView mine_fansi_title_img;
    @ViewInject(R.id.mine_fansi_title_text)
    private TextView mine_fansi_title_text;

    @ViewInject(R.id.tv_fansi_total)
    private TextView tv_fansi_total;
    @ViewInject(R.id.tv_fansi_total_zhitui)
    private TextView tv_fansi_total_zhitui;
    @ViewInject(R.id.tv_fansi_total_jianjie)
    private TextView tv_fansi_total_jianjie;
    @ViewInject(R.id.mPieChart)
    private PieChart mPieChart;

    @ViewInject(R.id.mine_fansi_viewpager)
    private LazyViewPager mViewpager;
    @ViewInject(R.id.mine_fansi_indicator)
    private VPindicator mIndicator;

    private List<Fragment> mFragments = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private Map<String, Object> parameters = new HashMap<>();

    private String[] Stars = new String[]{"", "", "", ""};
    private int[] number = new int[]{1, 1};
    @Override
    protected MineFansiPresenter CreatePresenter() {
        return new MineFansiPresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(mine_fansi_back, mine_fansi_title_text, mine_fansi_title_img);
        setStatusBar();
        initFragment();
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.obtainFansiHeadInfo(getAppToken());

        //        parameters.put(TOKEN,getAppToken());
        parameters.put(TOKEN,"o1voQ1Xik7iCxobahGFXoBpi1KS8");
        parameters.put("page","1");
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("type",FANSI_TYPE_POTENTIAL);
        mPresenter.obtainDirectlyFansi(parameters);
        initViewPager();
        initVie();
    }

    /**
     * 初始化图表
     */
    private void initVie() {
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < Stars.length; i++) {
            titles.add(Stars[i]);
        }
        ArrayList<PieEntry> entrys = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            entrys.add(new PieEntry(number[i], i));
        }
        PieDataSet dataset = new PieDataSet(entrys, "");
        dataset.setSelectionShift(10f);
        //颜色填充
        dataset.setColors(new int[]{getResources().getColor(R.color.orange),getResources().getColor(R.color.tomato)});
        //数据填充
        PieData piedata = new PieData(dataset);
        //设置饼图上显示数据的字体大小
//        piedata.setValueTextSize(10);
        mPieChart.setData(piedata);
        mPieChart.animateX(1400);
        mPieChart.setUsePercentValues(true);
        mPieChart.getLegend().setEnabled(false);
        mPieChart.setDescription(null);
        mPieChart.setRotationEnabled(true);
    }


    private void initViewPager() {
        String[] titleArray = getResources().getStringArray(R.array.mine_fansi_title);
        FragmentManager fm = getSupportFragmentManager();
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(fm, mFragments, titleArray);
        // 设置adapter
        mViewpager.setAdapter(adapter);
        // 绑定indicator
        mIndicator.setViewPager(mViewpager);
        setTabPagerIndicator();
    }

    //设置指示器颜色
    private void setTabPagerIndicator() {
        // 设置模式，一定要先设置模式
        mIndicator.setIndicatorMode(VPindicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        // 设置分割线的颜色
//        my_coupon_indicator.setDividerColor(Color.parseColor("#00bbcf"));
        //设置
        mIndicator.setDividerPadding(getResources().getDimensionPixelSize(R.dimen.dp_10));
        // 设置底部导航线的颜色
        mIndicator.setIndicatorColor(Color.parseColor("#FF615B"));
        // 设置tab标题选中的颜色
        mIndicator.setTextColorSelected(Color.parseColor("#FF615B"));
        // 设置tab标题未被选中的颜色
        mIndicator.setTextColor(Color.parseColor("#FF111111"));
        // 设置字体大小
        mIndicator.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_15));
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //返回
            case R.id.mine_fansi_back:
                finish();
                break;
        }
    }
    private void initFragment() {
        mFragments.add(new FanSiDirectlyFragment());
        mFragments.add(new FanSiIndirectFragment());
    }

    private void showNoSuperiorDialog() {
        showOnlyConfirmDialog(R.string.prompt, R.string.voluntary_occupation);
    }

    @Override
    public void obtainFansiHeadInfoSuccess(MyFansiHeadInfoBean bean) {

    }

    //获取
    @Override
    public void obtainFansSuccess(FansiDirectlyBean bean) {
        tv_fansi_total.setText("￥"+bean.getTotal()+"元");
        tv_fansi_total_zhitui.setText("￥"+bean.getTotal_zhitui()+"元");
        tv_fansi_total_jianjie.setText("￥"+bean.getTotal_jianjie()+"元");
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
