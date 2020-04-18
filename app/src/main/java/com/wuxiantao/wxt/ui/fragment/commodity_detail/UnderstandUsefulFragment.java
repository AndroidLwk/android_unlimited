package com.wuxiantao.wxt.ui.fragment.commodity_detail;

import android.annotation.SuppressLint;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseFragment;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GraphicDetailsFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午6:39
 * Description:${DESCRIPTION} 商品详情->了解VIP权益
 */
@SuppressLint("ValidFragment")
@ContentView(R.layout.fragment_unerstand_userful)
public class UnderstandUsefulFragment extends BaseFragment {
    @ViewInject(R.id.graphic_details_banner)
    Banner graphic_details_banner;

    @Override
    public void initView() {
        initBanner();
    }


    private void initBanner(){
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.super_man_b1);
        list.add(R.mipmap.super_man_b2);
        list.add(R.mipmap.super_man_b3);
        //设置图片集合
        graphic_details_banner.setImages(list);
        //设置banner样式
        graphic_details_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        graphic_details_banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        graphic_details_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        quality_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        graphic_details_banner.isAutoPlay(false);
        //设置轮播图片间隔时间（不设置默认为2000）
//        commodity_info_banner.setDelayTime(1500);
        //设置是否允许手动滑动轮播图
        graphic_details_banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        graphic_details_banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        quality_banner.start();
//        commodity_info_banner.setOnBannerListener();
        graphic_details_banner.start();
    }
}
