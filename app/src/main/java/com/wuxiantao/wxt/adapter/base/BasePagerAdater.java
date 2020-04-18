package com.wuxiantao.wxt.adapter.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePagerAdater
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-14 下午7:08
 * Description:${DESCRIPTION}
 */
public abstract class BasePagerAdater  extends FragmentPagerAdapter {

    private List<String>  list;
    private String[] titles;

    public BasePagerAdater(FragmentManager fm,List<String>  list) {
        super(fm);
        this.list = list;
    }

    public BasePagerAdater(FragmentManager fm,String[] titles) {
        super(fm);
        this.titles = titles;
    }

    //返回选项卡的文本 ，，，添加选项卡
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (list == null || list.isEmpty()){
            return titles[position];
        }
        return list.get(position);
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
        return list == null ? (titles == null ? 0 : titles.length) : list.size();
    }
}
