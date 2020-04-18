package com.wuxiantao.wxt.ui.activity;

import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.viewpager.RecordingViewPagerAdapter;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RecordingActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午3:29
 * Description:${DESCRIPTION} 记录
 */
@ContentView(R.layout.activity_recording)
public class RecordingActivity extends BaseActivity {
    @ViewInject(R.id.recording_back)
    RelativeLayout recording_back;
    @ViewInject(R.id.recording_rg)
    RadioGroup recording_rg;
    @ViewInject(R.id.recording_duration)
    RadioButton recording_duration;
    @ViewInject(R.id.recording_upper_limit)
    RadioButton recording_upper_limit;
    @ViewInject(R.id.recording_vp)
    LazyViewPager recording_vp;

    @Override
    public void initView() {
        setOnClikListener(recording_back,recording_duration,recording_upper_limit);
        FragmentManager fm = getSupportFragmentManager();
        String[] arrays = getResources().getStringArray(R.array.recording_title);
        RecordingViewPagerAdapter adapter = new RecordingViewPagerAdapter(fm,Arrays.asList(arrays));
        recording_vp.setAdapter(adapter);
        recording_vp.setCurrentItem(0);
        recording_rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.recording_duration:
                    recording_vp.setCurrentItem(0);
                    break;
                case R.id.recording_upper_limit:
                    recording_vp.setCurrentItem(1);
                    break;
            }
        });
        recording_vp.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        recording_duration.setChecked(true);
                        break;
                    case 1:
                        recording_upper_limit.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.recording_back){
            finish();
        }
    }












}
