package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewPagerAdapter;
import com.wuxiantao.wxt.mvp.contract.HeroScrollContract;
import com.wuxiantao.wxt.mvp.presenter.HeroScrollPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.NoSlidingLazyViewPager;
import com.wuxiantao.wxt.ui.fragment.heroscroll.BronzeScrollFragment;
import com.wuxiantao.wxt.ui.fragment.heroscroll.GoldScrollFragment;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 英雄卷轴
 */
@ContentView(R.layout.activity_heroscroll)

public class HeroScrollActivity extends MvpActivity<HeroScrollPresenter, HeroScrollContract> {
    @ViewInject(R.id.cntoolbar_title)
    CNToolbar cntoolbar_title;
    @ViewInject(R.id.indicator_scrapcard_heroscroll)
    VPindicator indicator_scrapcard_heroscroll;
    @ViewInject(R.id.viewpager_scrapcard_heroscroll)
    NoSlidingLazyViewPager viewpager_scrapcard_heroscroll;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBar();
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        initFragment();
        initViewPager();
    }

    private void initViewPager() {
        String[] titleArray = getResources().getStringArray(R.array.hero_scroll_title);
        FragmentManager fm = getSupportFragmentManager();
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(fm, mFragments, titleArray);
        // 设置adapter
        viewpager_scrapcard_heroscroll.setAdapter(adapter);
        // 绑定indicator
        indicator_scrapcard_heroscroll.setViewPager(viewpager_scrapcard_heroscroll);
        setTabPagerIndicator();
    }

    //设置指示器颜色
    private void setTabPagerIndicator() {
        // 设置模式，一定要先设置模式
        indicator_scrapcard_heroscroll.setIndicatorMode(VPindicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        // 设置分割线的颜色
//        my_coupon_indicator.setDividerColor(Color.parseColor("#00bbcf"));
        //设置
        indicator_scrapcard_heroscroll.setDividerPadding(getResources().getDimensionPixelSize(R.dimen.dp_10));
        // 设置底部导航线的颜色
        indicator_scrapcard_heroscroll.setIndicatorColor(Color.parseColor("#FF615B"));
        // 设置tab标题选中的颜色
        indicator_scrapcard_heroscroll.setTextColorSelected(Color.parseColor("#FF615B"));
        // 设置tab标题未被选中的颜色
        indicator_scrapcard_heroscroll.setTextColor(Color.parseColor("#FF111111"));
        // 设置字体大小
        indicator_scrapcard_heroscroll.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_15));
    }

    private void initFragment() {
        mFragments.add(new BronzeScrollFragment());
        mFragments.add(new GoldScrollFragment());
    }

    @Override
    protected HeroScrollPresenter CreatePresenter() {
        return new HeroScrollPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
