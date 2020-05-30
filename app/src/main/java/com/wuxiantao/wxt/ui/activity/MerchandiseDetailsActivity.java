package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MerchandiseImgRecViewAdapter;
import com.wuxiantao.wxt.bean.MerchandiseDetailBean;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.mvp.contract.MerchandiseDetailContract;
import com.wuxiantao.wxt.mvp.presenter.MerchandiseDetailPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.scrollview.CustomScrollView;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.TaoBaoUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_ID;
import static com.wuxiantao.wxt.config.Constant.VIP_STATUS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MerchandiseDetailsActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 下午2:15
 * Description:${DESCRIPTION} 淘宝商品详情
 */
@ContentView(R.layout.activity_merchandise_details)
public class MerchandiseDetailsActivity extends MvpActivity<MerchandiseDetailPresenter, MerchandiseDetailContract.IDetailView> implements MerchandiseDetailContract.IDetailView{
    @ViewInject(R.id.merchandise_sv)
    CustomScrollView merchandise_sv;

    @ViewInject(R.id.merchandise_toolbar)
    Toolbar merchandise_toolbar;
    @ViewInject(R.id.merchandise_toolbar_tv)
    TextView merchandise_toolbar_tv;
    @ViewInject(R.id.merchandise_details_appbar)
    AppBarLayout merchandise_details_appbar;
    @ViewInject(R.id.collapsing_tool_bar)
    CollapsingToolbarLayout collapsing_tool_bar;

    @ViewInject(R.id.merchandise_topping)
    ImageView merchandise_topping;
    @ViewInject(R.id.merchandise_save_buy)
    RelativeLayout merchandise_save_buy;
    @ViewInject(R.id.merchandise_save_buy_hide)
    RelativeLayout merchandise_save_buy_hide;
    @ViewInject(R.id.merchandise_img_rv)
    RecyclerView merchandise_img_rv;
    @ViewInject(R.id.merchandise_banner)
    Banner merchandise_banner;
    @ViewInject(R.id.merchandise_monthly_sales)
    TextView merchandise_monthly_sales;
    @ViewInject(R.id.merchandise_current_price)
    TextView merchandise_current_price;
    @ViewInject(R.id.merchandise_original_price)
    TextView merchandise_original_price;
    @ViewInject(R.id.merchandise_details_commodity_reward_layout)
    LinearLayout merchandise_commodity_reward_layout;
    @ViewInject(R.id.merchandise_details_prize_tv)
    TextView merchandise_details_prize_tv;
    @ViewInject(R.id.merchandise_details_prize_money)
    TextView merchandise_details_prize_money;
    @ViewInject(R.id.merchandise_title)
    TaoBaoTagTextView merchandise_title;
    @ViewInject(R.id.merchandise_details_coupon_layout)
    LinearLayout merchandise_details_coupon_layout;
    @ViewInject(R.id.merchandise_details_coupon_info)
    TextView merchandise_details_coupon_info;
    @ViewInject(R.id.merchandise_details_period)
    TextView merchandise_details_period;
    @ViewInject(R.id.merchandise_save_money1)
    TextView merchandise_save_money1;
    @ViewInject(R.id.merchandise_save_money2)
    TextView merchandise_save_money2;

    private LoadingDialog loadingDialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView(Bundle savedInstanceState) {
        Map<String, Object> detail = new HashMap<String, Object>();
        detail.put("event_GoodsView", "popular");//自定义参数：音乐类型，值：流行
        MobclickAgent.onEventObject(this, "event_GoodsView", detail);
        setStatusBar();
        loadingDialog = new LoadingDialog.Build(this).build();
        Bundle bundle = getBundle();
        if (bundle != null) {
            long id = bundle.getLong(TAO_BAO_ID);
            mPresenter.getProductDetail(id);
        }
        setOnClikListener(merchandise_save_buy, merchandise_save_buy_hide,
                merchandise_topping,merchandise_commodity_reward_layout,
                merchandise_details_coupon_layout);
        merchandise_sv.setOnScrollListener(isBottom -> {
            merchandise_save_buy_hide.setVisibility(isBottom ? View.VISIBLE : View.INVISIBLE);
            merchandise_save_buy.setVisibility(isBottom ? View.INVISIBLE : View.VISIBLE);
            merchandise_topping.setVisibility(isBottom ? View.VISIBLE : View.GONE);

        });
        setToolBarReplaceActionBar();
    }


    // 用toolBar替换ActionBar
    private void setToolBarReplaceActionBar(){
        setSupportActionBar(merchandise_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        collapsing_tool_bar.setTitle("");
        //返回按钮监听
        merchandise_toolbar.setNavigationOnClickListener(v -> finish());
        merchandise_toolbar.setNavigationIcon(R.mipmap.back_icon);
        merchandise_details_appbar.addOnOffsetChangedListener((appBarLayout, offset) -> {
            int toolbarHeight = merchandise_details_appbar.getTotalScrollRange();
            int dy = Math.abs(offset);
            if (dy >= toolbarHeight){
                merchandise_toolbar_tv.setText(getString(R.string.commodity_info));
                merchandise_toolbar.setNavigationIcon(R.mipmap.base_title_back);
                merchandise_toolbar.setBackgroundResource(R.drawable.base_title_background);
            }else {
                merchandise_toolbar.setNavigationIcon(R.mipmap.back_icon);
                merchandise_toolbar.setBackgroundResource(0);
                merchandise_toolbar_tv.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        merchandise_sv.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    protected MerchandiseDetailPresenter CreatePresenter() {
        return new MerchandiseDetailPresenter();
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //省钱购买
            case R.id.merchandise_save_buy_hide:
            case R.id.merchandise_save_buy:
                if (datas != null) {
                    TaoBaoUtils.newInstance().openAliHomeApp(this, datas.getData().getCoupon_click_url());
                }
                break;
            //置顶
            case R.id.merchandise_topping:
                merchandise_details_appbar.setExpanded(true);
                merchandise_sv.fullScroll(ScrollView.FOCUS_UP);
                break;
            //跳转会员中心
            case R.id.merchandise_details_commodity_reward_layout:
                $startActivity(MenuActivity.class,SHIFT_ID,1);
                break;
            //优惠券
            case R.id.merchandise_details_coupon_layout:
                if (datas != null) {
                    TaoBaoUtils.newInstance().openAliHomeApp(this, datas.getData().getCoupon_click_url());
                }
                break;
        }
    }

    private MerchandiseDetailBean datas;

    @Override
    public void getProductDetailSuccess(MerchandiseDetailBean bean) {
        this.datas = bean;
        if (bean != null) {
            merchandise_current_price.setText(bean.getData().getZk_final_price());
            String volume = bean.getData().getVolume();
            merchandise_monthly_sales.setText(mContext.getString(R.string.month_volume,Integer.valueOf(volume)));
            String reserve_price = bean.getData().getReserve_price();
            String type = bean.getData().getUser_type();
            TextViewUtils.setTextViewDeleteLine(merchandise_original_price,
                    getString(R.string.commodity_price,type.equals("1") ? mContext
                            .getString(R.string.tianmao_price) : mContext.getString(R.string.taobao_price_),
                            Double.valueOf(reserve_price)));
            merchandise_title.setContentAndTag(bean.getData().getTitle(),
                    type.equals("1") ? TaoBaoTagTextView.TYPE_TIANMAO : TaoBaoTagTextView.TYPE_TAOBAO);
            //奖励
            String com_rebate = String.valueOf(bean.getData().getCom_rebate());
            String vip_rebate = String.valueOf(bean.getData().getVip_rebate());
            merchandise_details_prize_tv.setText(getString(R.string.gold_rex,BigDecimalUtils.round(com_rebate,2)));

            int color = Color.parseColor("#FF5857");
            String sources = getString(R.string.member_can,BigDecimalUtils.round(vip_rebate,2));
            TextViewUtils.setTextViewKeyWordHighlight(merchandise_details_prize_money,
                    sources, color,new Pair<>(4,sources.length()));
            //会员状态
            int vipStatus = getUserStatus(VIP_STATUS);
            if (vipStatus == 0){
                merchandise_save_money1.setText(com_rebate);
                merchandise_save_money2.setText(com_rebate);
            }else {
                merchandise_save_money1.setText(vip_rebate);
                merchandise_save_money2.setText(vip_rebate);
            }
            //优惠券
            String coupon_info = bean.getData().getCoupon_info();
            if (!isEmpty(coupon_info)){
                merchandise_details_coupon_layout.setVisibility(View.VISIBLE);
                merchandise_details_coupon_info.setText(coupon_info);
                merchandise_details_period.setText(bean.getData().getCoupon_start_time());
            }
            initBanner(bean.getData().getSmall_images());
            initRecView(bean.getData().getSmall_images());
        }
    }

    private void initBanner(ArrayList<String> list) {
        //设置图片集合
        merchandise_banner.setImages(list);
        //设置banner样式
        merchandise_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        merchandise_banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        merchandise_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        quality_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        merchandise_banner.isAutoPlay(false);
        //设置是否允许手动滑动轮播图
        merchandise_banner.setViewPagerIsScroll(true);
        //merchandise_banner（当banner模式中有指示器时）
        merchandise_banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        merchandise_banner.start();
        merchandise_banner.setOnBannerListener(position -> previewImg(list, position));
    }

    private void initRecView(ArrayList<String> list) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0, 10);
        MerchandiseImgRecViewAdapter adapter = new MerchandiseImgRecViewAdapter(this, list);
        merchandise_img_rv.setLayoutManager(manager);
        merchandise_img_rv.addItemDecoration(decoration);
        merchandise_img_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> previewImg(list, position));
    }

    @Override
    public void getProductDetailFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> finish());
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }


}
