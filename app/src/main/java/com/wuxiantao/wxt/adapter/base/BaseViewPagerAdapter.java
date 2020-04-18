package com.wuxiantao.wxt.adapter.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/24 0024 15:14 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi ViewPagerAdapter
 */

public class BaseViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titles;

    private List<Fragment> mFragments;

    public BaseViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments,String [] arrays) {
        super(fm);
        this.mFragments = mFragments;
        if (arrays == null){
            return;
        }
        this.titles = Arrays.asList(arrays);
    }

    public BaseViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments,List<String> list) {
        super(fm);
        this.mFragments = mFragments;
        if (list == null){
            return;
        }
        this.titles = list;
    }

    public BaseViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return titles == null ? (mFragments == null ? 0 : mFragments.size()) : titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
