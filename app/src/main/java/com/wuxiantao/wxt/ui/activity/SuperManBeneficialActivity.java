package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.SuperManVipRecViewAdapter;
import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.mvp.contract.SuperManBeneficialContract;
import com.wuxiantao.wxt.mvp.presenter.SuperManBeneficialPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.LogUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.COMMODITY_SPACE;
import static com.wuxiantao.wxt.config.Constant.GOOD_ID;
import static com.wuxiantao.wxt.config.Constant.GOOD_TYPE;
import static com.wuxiantao.wxt.config.Constant.SELF_EMPLOYED;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperManBeneficialActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-21 下午12:20
 * Description:${DESCRIPTION} 超人VIP权益
 */
@ContentView(R.layout.activity_super_man_beneficial)
public class SuperManBeneficialActivity extends MvpActivity<SuperManBeneficialPresenter, SuperManBeneficialContract.ISuperManBeneficialView> implements SuperManBeneficialContract.ISuperManBeneficialView {

    @ViewInject(R.id.super_man_beneficial_banner)
    Banner mBanner;
    @ViewInject(R.id.super_man_beneficial_rv)
    RecyclerView super_man_beneficial_rv;

    private SuperManVipRecViewAdapter adapter;
    private int page = 1;
    @Override
    public void initView() {
        mPresenter.getShoppingList(page,5);
        initBanner();
    }


    @Override
    public void getShoppingListSuccess(SuperManBeneBean bean) {
        initLayout(bean);
    }




    private void initLayout(SuperManBeneBean bean){
        if (adapter == null){
            adapter = new SuperManVipRecViewAdapter(this,bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(this);
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,10);
            super_man_beneficial_rv.setLayoutManager(manager);
            super_man_beneficial_rv.addItemDecoration(itemDecoration);
            super_man_beneficial_rv.setAdapter(adapter);
            adapter.setOnItemClickListener((good_id, spec) -> {
                Bundle bundle = new Bundle();
                bundle.putInt(GOOD_TYPE,SELF_EMPLOYED);
                bundle.putInt(GOOD_ID,good_id);
                bundle.putString(COMMODITY_SPACE,spec);
                $startActivity(CommodityInfoActivity.class,bundle);
            });
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    private void initBanner(){
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.super_man_b1);
        list.add(R.mipmap.super_man_b2);
        list.add(R.mipmap.super_man_b3);
        //设置图片集合
        mBanner.setImages(list);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        quality_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(1500);
        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        quality_banner.start();
        mBanner.setOnBannerListener(position -> LogUtils.loge("点击位置:" + position));
    }



    @Override
    protected void onStart() {
        super.onStart();
        mBanner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    protected SuperManBeneficialPresenter CreatePresenter() {
        return new SuperManBeneficialPresenter();
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.super_man_quanyi)).build();
    }

    @Override
    public void getShoppingListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
