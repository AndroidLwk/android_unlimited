package com.wuxiantao.wxt.adapter.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wuxiantao.wxt.bean.TaoBaoSubBean;
import com.wuxiantao.wxt.ui.fragment.TaoBaoFeaturedFragment;
import com.wuxiantao.wxt.ui.fragment.TaoBaoFeaturedSubFragment;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.CATE_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubViewPagerAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午5:06
 * Description:${DESCRIPTION}
 */
public class TaoBaoSubViewPagerAdapter extends FragmentPagerAdapter {

    private List<TaoBaoSubBean> list;

    public TaoBaoSubViewPagerAdapter(FragmentManager fm, List<TaoBaoSubBean> list) {
        super(fm);
        this.list = list;
    }

    //返回选项卡的文本 ，，，添加选项卡
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        String cateId = list.get(position).getCateId();
        Fragment mFragment;
        if (cateId.equals("0")){
            mFragment = new TaoBaoFeaturedFragment();
        }else {
            mFragment = new TaoBaoFeaturedSubFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CATE_ID,cateId);
            mFragment.setArguments(bundle);
        }
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
