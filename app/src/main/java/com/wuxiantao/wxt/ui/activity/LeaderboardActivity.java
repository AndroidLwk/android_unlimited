package com.wuxiantao.wxt.ui.activity;

import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.viewpager.LeaderboarViewPagerAdapter;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineBalanceActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 下午4:57
 * Description:${DESCRIPTION} 排行榜 今日收益榜/累计收益榜
 */
@ContentView(R.layout.activity_leaderboard)
public class LeaderboardActivity extends BaseActivity {
    @ViewInject(R.id.leadderboard_rg)
    RadioGroup leadderboard_rg;

    //昨日收益榜
    @ViewInject(R.id.leadderboard_interest)
    RadioButton leadderboard_interest;

    //累计收益榜
    @ViewInject(R.id.leadderboard_deposit)
    RadioButton leadderboard_deposit;

    @ViewInject(R.id.leader_board_vp)
    LazyViewPager leader_board_vp;

    @Override
    public void initView() {
        FragmentManager fm = getSupportFragmentManager();
        String[] arrays = getResources().getStringArray(R.array.leader_board_title);
        LeaderboarViewPagerAdapter adapter = new LeaderboarViewPagerAdapter(fm,arrays);
        leader_board_vp.setAdapter(adapter);
        leader_board_vp.setCurrentItem(0);
        leadderboard_rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.leadderboard_interest:
                    leader_board_vp.setCurrentItem(0);
                    break;
                case R.id.leadderboard_deposit:
                    leader_board_vp.setCurrentItem(1);
                    break;
            }
        });
        leader_board_vp.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        leadderboard_interest.setChecked(true);
                        break;
                    case 1:
                        leadderboard_deposit.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).setMiddleTitleText(R.string.leaderboard).build();
    }
}
