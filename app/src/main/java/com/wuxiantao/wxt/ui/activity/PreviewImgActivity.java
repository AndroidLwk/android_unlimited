package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.viewpager.PreviewImgViewPagerAdapter;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_LIST;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_POSITION;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PreviewImgActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-24 下午7:44
 * Description:${DESCRIPTION} 图片预览
 */
@ContentView(R.layout.activity_preview_img)
public class PreviewImgActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @ViewInject(R.id.preview_img_back)
    ImageView preview_img_back;
    @ViewInject(R.id.preview_img_current_count)
    TextView preview_img_current_count;
    @ViewInject(R.id.preview_img_total_count)
    TextView preview_img_total_count;
    @ViewInject(R.id.preview_img_vp)
    ViewPager preview_img_vp;

    private ArrayList<String> imgList;
    private int position = 0;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#373b3e"));
        Bundle bundle = getBundle();
        if (bundle != null) {
            imgList = bundle.getStringArrayList(PREVIEW_IMG_LIST);
            position = bundle.getInt(PREVIEW_IMG_POSITION);
        }
        if (imgList != null && !imgList.isEmpty()) {
            preview_img_current_count.setText(String.valueOf(position + 1));
            preview_img_total_count.setText(String.valueOf(imgList.size()));
            preview_img_vp.setAdapter(new PreviewImgViewPagerAdapter(this, imgList));
            preview_img_vp.setCurrentItem(position);
        }
        preview_img_vp.addOnPageChangeListener(this);
        setOnClikListener(preview_img_back);
    }


    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.preview_img_back) {
            finish();
        }
    }


    @Override
    public void onPageSelected(int position) {
        this.position = position;
        preview_img_current_count.setText(String.valueOf(position + 1));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
