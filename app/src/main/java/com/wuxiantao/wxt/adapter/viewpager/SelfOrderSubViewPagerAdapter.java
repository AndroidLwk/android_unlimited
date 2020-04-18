package com.wuxiantao.wxt.adapter.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wuxiantao.wxt.ui.fragment.all_order.sub.SelfSubOrderFragment;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.ORDER_STATUS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubViewPagerAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午5:06
 * Description:${DESCRIPTION}
 */
public class SelfOrderSubViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> list;

    public SelfOrderSubViewPagerAdapter(FragmentManager fm,List<String> list) {
        super(fm);
        this.list = list;
    }

    //返回选项卡的文本 ，，，添加选项卡
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        SelfSubOrderFragment mFragment = new SelfSubOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ORDER_STATUS,position);
        mFragment.setArguments(bundle);
        return mFragment;
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

    @Override
    public int getCount() {
        return list.size();
    }

}
