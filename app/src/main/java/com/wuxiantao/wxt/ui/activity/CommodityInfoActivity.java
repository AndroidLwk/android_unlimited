package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewPagerAdapter;
import com.wuxiantao.wxt.adapter.bean.LableSelecedInfo;
import com.wuxiantao.wxt.bean.CommodityInfoBean;
import com.wuxiantao.wxt.bean.SkuBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.mvp.contract.CommodityInfoContract;
import com.wuxiantao.wxt.mvp.presenter.CommodityInfoPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.fragment.commodity_detail.GraphicDetailsFragment;
import com.wuxiantao.wxt.ui.fragment.commodity_detail.UnderstandUsefulFragment;
import com.wuxiantao.wxt.ui.popupwindow.ArticleSpcePopupWindow;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.LogUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.wuxiantao.wxt.config.Constant.COMMODITY_DETAIL_TOPPING;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_IMG;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_PRICE;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_SELECT_NAME;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_SPACE;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_TITLE;
import static com.wuxiantao.wxt.config.Constant.GOOD_CONTENT;
import static com.wuxiantao.wxt.config.Constant.GOOD_ID;
import static com.wuxiantao.wxt.config.Constant.GOOD_TYPE;
import static com.wuxiantao.wxt.config.Constant.SELF_EMPLOYED;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.ZERO_BUY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午2:50
 * Description:${DESCRIPTION} 自营商品信息
 */
@ContentView(R.layout.activity_commodity_info)
public class CommodityInfoActivity extends MvpActivity<CommodityInfoPresenter, CommodityInfoContract.ICommodityInfoView> implements CommodityInfoContract.ICommodityInfoView {

    @ViewInject(R.id.commodity_info_toolbar)
    Toolbar commodity_info_toolbar;
    @ViewInject(R.id.commodity_info_toolbar_tv)
    TextView commodity_info_toolbar_tv;
    @ViewInject(R.id.commodity_info_appbar)
    AppBarLayout commodity_info_appbar;
    @ViewInject(R.id.commodity_info_ctl)
    CollapsingToolbarLayout commodity_info_ctl;

    @ViewInject(R.id.commodity_info_banner)
    Banner commodity_info_banner;
    @ViewInject(R.id.commodity_info_money)
    TextView commodity_info_money;
    @ViewInject(R.id.commodity_info_return_layout)
    LinearLayout commodity_info_return_layout;
    @ViewInject(R.id.commodity_info_return_money)
    TextView commodity_info_return_money;
    @ViewInject(R.id.commodity_info_reward_layout)
    LinearLayout commodity_info_reward_layout;
    @ViewInject(R.id.commodity_info_prize_tv)
    TextView commodity_info_prize_tv;
    @ViewInject(R.id.commodity_info_prize_money)
    TextView commodity_info_prize_money;
    @ViewInject(R.id.commodity_info_title)
    TaoBaoTagTextView commodity_info_title;
    @ViewInject(R.id.commodity_info_sort)
    TextView commodity_info_sort;
    @ViewInject(R.id.commodity_info_buy)
    Button commodity_info_buy;
    @ViewInject(R.id.commodity_info_spec)
    RelativeLayout commodity_info_spec;
    @ViewInject(R.id.commodity_info_spec_tv)
    TextView commodity_info_spec_tv;
    @ViewInject(R.id.commodity_info_spec_hint)
    TextView commodity_info_spec_hint;
    @ViewInject(R.id.commodity_info_tabLayout)
    TabLayout commodity_info_tabLayout;
    @ViewInject(R.id.commodity_info_viewpager)
    ViewPager commodity_info_viewpager;

    private List<Fragment> mFragments = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private ArrayList<String> bannerImgs = new ArrayList<>();
    private CommodityInfoBean bean;
    private List<SkuBean> skuBeans = new ArrayList<>();
    private int good_id = 0;
    private String defSpce;
    //商品类型 1.自营商品  2.0元购商品
    private int good_type = 0;

    @Override
    protected CommodityInfoPresenter CreatePresenter() {
        return new CommodityInfoPresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        //注册
        EventBus.getDefault().register(this);

        setStatusBar();
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.obtain_commodity_info_loading).build();
        Bundle bundle = getBundle();
        setToolBarReplaceActionBar();
        if (bundle != null) {
            good_type = bundle.getInt(GOOD_TYPE);
            good_id = bundle.getInt(GOOD_ID);
            defSpce = bundle.getString(COMMODITY_SPACE);
            mPresenter.obtainInfo(good_id);
        }
        setOnClikListener(commodity_info_buy,commodity_info_spec,commodity_info_reward_layout);
        commodity_info_tabLayout.setTabIndicatorFullWidth(false);
    }

    // 用toolBar替换ActionBar
    private void setToolBarReplaceActionBar(){
        setSupportActionBar(commodity_info_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        commodity_info_toolbar.setTitle("");
        //返回按钮监听
        commodity_info_toolbar.setNavigationOnClickListener(v -> finish());
        commodity_info_toolbar.setNavigationIcon(R.mipmap.back_icon);
        commodity_info_appbar.addOnOffsetChangedListener((appBarLayout, offset) -> {
            int toolbarHeight = commodity_info_appbar.getTotalScrollRange();
            int dy = Math.abs(offset);
            if (dy >= toolbarHeight){
                commodity_info_toolbar_tv.setText(getString(R.string.commodity_info));
                commodity_info_toolbar.setNavigationIcon(R.mipmap.base_title_back);
                commodity_info_toolbar.setBackgroundResource(R.drawable.base_title_background);
            }else {
                commodity_info_toolbar.setNavigationIcon(R.mipmap.back_icon);
                commodity_info_toolbar.setBackgroundResource(0);
                commodity_info_toolbar_tv.setText("");
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            //规格
            case R.id.commodity_info_spec:
                showSpecWindow();
                break;
            //立即购买
            case R.id.commodity_info_buy:
                String spce = commodity_info_spec_tv.getText().toString();
                if (!isEmpty(spce)){
                    startActivity(spce,skuToString());
                }else {
                    showSpecWindow();
                }
                break;
            //升级vip
            case R.id.commodity_info_reward_layout:
                $startActivity(MenuActivity.class,SHIFT_ID,1);
                break;
        }
    }

    //商品信息获取成功
    @Override
    public void obtainInfoSuccess(CommodityInfoBean bean) {
        this.bean = bean;
        switch (good_type){
            //自营
            case SELF_EMPLOYED:
                commodity_info_sort.setText(getString(R.string.self_order_commodity));
                commodity_info_return_layout.setVisibility(View.GONE);
                commodity_info_reward_layout.setVisibility(View.VISIBLE);
                String com_rebate = String.valueOf(bean.getInfo().getCom_rebate());
                commodity_info_prize_tv.setText(getString(R.string.gold_rex, BigDecimalUtils.round(com_rebate,2)));
                int color = Color.parseColor("#FF5857");
                String vip_rebate = String.valueOf(bean.getInfo().getVip_rebate());
                String sources = getString(R.string.member_can,BigDecimalUtils.round(vip_rebate,2));
                TextViewUtils.setTextViewKeyWordHighlight(commodity_info_prize_money,
                        sources, color,new Pair<>(4,sources.length()));
                break;
            //0元购
            case ZERO_BUY:
                commodity_info_sort.setText(getString(R.string.self_order_commodity));
                commodity_info_return_layout.setVisibility(View.VISIBLE);
                commodity_info_reward_layout.setVisibility(View.GONE);
                commodity_info_return_money.setText(getString(R.string.buy_m_order,BigDecimalUtils.round(bean.getInfo().getPrice())));
                break;
        }
        String imgs = bean.getInfo().getGoods_image();
        initBanner(!isEmpty(imgs) ? imgs : "");
        commodity_info_money.setText(bean.getInfo().getPrice());
        commodity_info_title.setContentAndTag(bean.getInfo().getGoods_title(), TaoBaoTagTextView.TYPE_YOUXUAN);
        if (!isEmpty(defSpce)){
            LogUtils.loge("defSpce====" + defSpce);
//            List<CommodityInfoSpesBean> spesBean = GsonManager.newInstance().jsonToArrayList(defSpce, CommodityInfoSpesBean.class);
//            for (CommodityInfoSpesBean spce : spesBean){
//                LogUtils.loge("spce.getName()====" + spce.getName());
//            }
        }
        initFragment(bean.getInfo().getGoods_content());
    }


    private void initFragment(String good_content){
        String[] stringArray = getResources().getStringArray(R.array.commodity_info_title);
        FragmentManager fm= getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString(GOOD_CONTENT,good_content);
        initFragment(new GraphicDetailsFragment(),bundle);
        initFragment(new UnderstandUsefulFragment(),null);
        BaseViewPagerAdapter mGiftPackageAdapter = new BaseViewPagerAdapter(fm,mFragments,stringArray);
        // 设置adapter
        commodity_info_viewpager.setAdapter(mGiftPackageAdapter);
        // 绑定indicator
//        commodity_info_indicator.setViewPager(commodity_info_viewpager);
        commodity_info_viewpager.setAdapter(mGiftPackageAdapter);
        commodity_info_tabLayout.setupWithViewPager(commodity_info_viewpager);
        commodity_info_tabLayout.setTabMode(TabLayout.MODE_FIXED);
        commodity_info_viewpager.setOffscreenPageLimit(0);
    }

    private void initFragment(Fragment fragment,Bundle bundle){
        if (fragment != null){
            if (bundle != null){
                fragment.setArguments(bundle);
            }
            if (!mFragments.contains(fragment)){
                mFragments.add(fragment);
            }
        }
    }


    private void initBanner(String imgs){
        String[] imgsArray = imgs.split("\\|");
        bannerImgs.addAll(Arrays.asList(imgsArray));
        //设置图片集合
        commodity_info_banner.setImages(bannerImgs);
        //设置banner样式
        commodity_info_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        commodity_info_banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        commodity_info_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        quality_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        commodity_info_banner.isAutoPlay(false);
        //设置是否允许手动滑动轮播图
        commodity_info_banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        commodity_info_banner.setIndicatorGravity(BannerConfig.RIGHT);
        commodity_info_banner.start();
        commodity_info_banner.setOnBannerListener(position -> previewImg(bannerImgs,position));
    }

    private StringBuffer sb = new StringBuffer();
    private Bundle bundle = new Bundle();

    //界面跳转
    private void startActivity(String space,String selectName){
        bundle.clear();
        bundle.putString(COMMODITY_PRICE,bean.getInfo().getPrice());
        bundle.putString(COMMODITY_TITLE,bean.getInfo().getGoods_title());
        bundle.putString(COMMODITY_IMG,bean.getInfo().getGoods_image());
        bundle.putString(COMMODITY_SPACE,space);
        bundle.putString(COMMODITY_SELECT_NAME,selectName);
        bundle.putInt(GOOD_TYPE,good_type);
        bundle.putInt(GOOD_ID,good_id);
        $startActivity(ConfirmOrderActivity.class,bundle);
    }

    private void showSpecWindow(){
        new ArticleSpcePopupWindow.Build(this)
                .setLablesViewList(bean)
                .setImageResource(bannerImgs.get(0))
                .setOnPopupClickListener(new ArticleSpcePopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void onImmediatelyBuy(List<LableSelecedInfo> spaces,String count) {
                        commodity_info_spec_hint.setVisibility(View.GONE);
                        sb.setLength(0);
                        sb.append(getString(R.string.sp));
                        for (int i = 0;i < spaces.size();i++){
                            SkuBean bean = new SkuBean(spaces.get(i).getName(),spaces.get(i).getLable());
                            skuBeans.add(bean);
                            sb.append(bean.getName());
                            sb.append(bean.getValue());
                            if (i != spaces.size() - 1){
                                sb.append(",");
                            }
                        }
                        sb.append("\n")
                                .append(getString(R.string.count_q))
                                .append(getString(R.string.commodity_count));
                        commodity_info_spec_tv.setText(sb.toString());
                        startActivity(sb.toString(),skuToString());
                    }

                    @Override
                    public void onClose(List<LableSelecedInfo> spaces, String count) {
                        commodity_info_spec_hint.setVisibility(View.GONE);
                        sb.setLength(0);
                        for (int i = 0;i < spaces.size();i++){
                            SkuBean bean = new SkuBean(spaces.get(i).getName(),spaces.get(i).getLable());
                            skuBeans.add(bean);
                            sb.append(bean.getName());
                            sb.append(bean.getValue());
                            if (i != spaces.size() - 1){
                                sb.append(",");
                            }
                        }
                        commodity_info_spec_tv.setText(sb.toString());
                    }
                })
                .builder()
                .showPopupWindow();
    }

    private String skuToString(){
        if (skuBeans.size() <= 0){
            return "";
        }
        if (sb.length() > 0){
            sb.setLength(0);
        }
        for (SkuBean bean : skuBeans){
            sb.append(bean.getName())
                    .append(":")
                    .append(bean.getValue())
                    .append(";");
        }
        return sb.toString().substring(0,sb.toString().length() - 1);
    }

    @Override
    public void obtainInfoFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        if (event.getType() == COMMODITY_DETAIL_TOPPING){
            commodity_info_appbar.setExpanded(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注册
        EventBus.getDefault().unregister(this);
    }
}
