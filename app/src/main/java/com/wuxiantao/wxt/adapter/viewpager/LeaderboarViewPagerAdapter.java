package com.wuxiantao.wxt.adapter.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wuxiantao.wxt.adapter.base.BasePagerAdater;
import com.wuxiantao.wxt.ui.fragment.LeaderboardFragment;

import static com.wuxiantao.wxt.config.Constant.REVENUE_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubViewPagerAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午5:06
 * Description:${DESCRIPTION}
 */
public class LeaderboarViewPagerAdapter extends BasePagerAdater {

    //排行榜类型 1今日收益榜 2总收益排行
    private int revenueType = 1;

    public LeaderboarViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm, titles);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment mFragment = new LeaderboardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(REVENUE_TYPE,position == 0 ? 1 : 2);
        mFragment.setArguments(bundle);
        return mFragment;
    }



}
