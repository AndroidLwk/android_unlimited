package com.wuxiantao.wxt.ui.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.TaoBaoFeaturedMenuBean;
import com.wuxiantao.wxt.adapter.recview.SelfEmployedAdapter;
import com.wuxiantao.wxt.adapter.recview.TaoBaoFeaturedMenuRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.TaoBaoFeaturedVerRecViewAdapter;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.mvp.contract.TaoBaoFeaturedContract;
import com.wuxiantao.wxt.mvp.presenter.TaoBaoFeaturedPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.CommodityInfoActivity;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.activity.MerchandiseDetailsActivity;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.activity.my.MyInvitationCodeActivity;
import com.wuxiantao.wxt.ui.activity.my.MyMemberActivity;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.scrollview.CustomScrollView;
import com.wuxiantao.wxt.ui.popupwindow.TradingHallPopupWindow;
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
import java.util.List;
import java.util.Objects;

import static com.wuxiantao.wxt.config.Constant.GOOD_ID;
import static com.wuxiantao.wxt.config.Constant.GOOD_TYPE;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.SELF_EMPLOYED;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_FEATURED_REC_VIEW;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午7:37
 * Description:${DESCRIPTION} 淘宝优选->子fragment 淘宝精选
 */
@ContentView(R.layout.fragment_tao_bao_featured)
public class TaoBaoFeaturedFragment extends MvpFragment<TaoBaoFeaturedPresenter, TaoBaoFeaturedContract.IFeaturedView> implements TaoBaoFeaturedContract.IFeaturedView {
    @ViewInject(R.id.fragment_taobao_featured_rl)
    SmartRefreshLayout mSmartRefreshLayout;
    @ViewInject(R.id.taobao_featured_grid_sl)
    CustomScrollView taobao_featured_grid_sl;
    @ViewInject(R.id.fragment_taobao_featured_classic_header)
    ClassicsHeader mClassicsHeader;
    @ViewInject(R.id.taobao_featured_grid_rv)
    RecyclerView taobao_featured_grid_rv;
    @ViewInject(R.id.self_employed_ver_rv)
    RecyclerView self_employed_ver_rv;
    @ViewInject(R.id.taobao_featured_ver_rv)
    RecyclerView taobao_featured_ver_rv;
    @ViewInject(R.id.fragment_tao_bao_featured_banner)
    Banner mBanner;
    @ViewInject(R.id.tv_tao_bao_featured_layout)
    TextView tv_tao_bao_featured_layout;

    private int page = 1;
    private TaoBaoFeaturedVerRecViewAdapter adapter;
    private SelfEmployedAdapter employedAdapter;
    private List<String> bannerListImg = new ArrayList<>();
    private TaoBaoHomeBean datas;

    @Override
    protected TaoBaoFeaturedPresenter CreatePresenter() {
        return new TaoBaoFeaturedPresenter();
    }

    @Override
    public void initView() {
        //注册
        EventBus.getDefault().register(this);
        initRefreshLoad();
        boolean isReview = getSPBoolean(IS_REVIEW);
        if (isReview) {
            mBanner.setVisibility(View.GONE);
            tv_tao_bao_featured_layout.setVisibility(View.GONE);
        } else {
            mBanner.setVisibility(View.VISIBLE);
            tv_tao_bao_featured_layout.setVisibility(View.VISIBLE);
            mPresenter.gainBanner(0);
            initGridLayout();
        }
    }

    private void initRefreshLoad() {
        mPresenter.getSelfEmployed();
        mPresenter.getTaoBaoHome(page);
        mSmartRefreshLayout.setRefreshHeader(mClassicsHeader);
        mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(
                Objects.requireNonNull(getContext())).setSpinnerStyle(SpinnerStyle.Scale));
        mSmartRefreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getSelfEmployed();
            mPresenter.getTaoBaoHome(page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (datas != null && datas.getMore() == 1) {
                mPresenter.getSelfEmployed();
                mPresenter.getTaoBaoHome(++page);
                refreshLayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    //九宫个菜单布局
    private void initGridLayout() {
        TaoBaoFeaturedMenuRecViewAdapter adapter = new TaoBaoFeaturedMenuRecViewAdapter(getContext(), initList());
        int spanCount = 5;
        GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(spanCount, 20, true);
        taobao_featured_grid_rv.setLayoutManager(manager);
        taobao_featured_grid_rv.addItemDecoration(decoration);
        taobao_featured_grid_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(this::setItemClick);
    }

    private void setItemClick(int position) {
        switch (position) {
            //推广赚钱
            case 0:
                $startActivity(ShareThemActivity.class);
                break;
            //淘宝精选
            case 1:
                break;
            //会员中心
            case 2:
                $startActivity(MyMemberActivity.class);
                break;
            //邀请码
            case 3:
                $startActivity(MyInvitationCodeActivity.class);
                break;
            //交易大厅
            case 4:
                new TradingHallPopupWindow.Build(getContext())
                        .setWindowAnimStyle(R.style.custom_dialog)
                        .builder().showPopupWindow();
                break;
        }
    }

    private List<TaoBaoFeaturedMenuBean> initList() {
        List<TaoBaoFeaturedMenuBean> list = new ArrayList<>();
        String[] name = getResources().getStringArray(R.array.main_taobao_featured_title);
        TypedArray ta = getResources().obtainTypedArray(R.array.main_taobao_featured_icon);
        //获取图片资源
        int[] icons = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            icons[i] = ta.getResourceId(i, 0);
        }
        ta.recycle();

        for (int i = 0; i < name.length; i++) {
            TaoBaoFeaturedMenuBean bean = new TaoBaoFeaturedMenuBean();
            bean.setIcon(icons[i]);
            bean.setName(name[i]);
            list.add(bean);
        }
        return list;
    }

    //获取banner图成功
    @Override
    public void gainBannerSuccess(BannerBean bean) {
        initBanner(bean.getList());
    }

    private void initBanner(List<BannerBean.ListBean> listBeans) {
        if (bannerListImg.size() > 0) {
            bannerListImg.clear();
        }
        for (BannerBean.ListBean bean : listBeans) {
            bannerListImg.add(bean.getImg());
        }
        mBanner.setVisibility(View.VISIBLE);
        //设置图片集合
        mBanner.setImages(bannerListImg);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader(350, 200));
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
        mBanner.setOnBannerListener(position -> {
            $startActivity(H5GameActivity.class);
//            String url = listBeans.get(position).getUrl();
//            Bundle bundle = new Bundle();
//            if (!isEmpty(url)){
//                bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_MAIN_BANNER);
//                bundle.putString(MAIN_BANNER_URL,url);
//                $startActivity(ProtocolActivity.class,bundle);
//            }else {
//                bundle.putInt(SHIFT_ID,2);
//                $startActivity(MenuActivity.class,bundle);
//            }
        });
        mBanner.start();
    }

    //获取淘宝商品
    @Override
    public void getTaoBaoHomeSuccess(TaoBaoHomeBean bean) {
        this.datas = bean;
        initTaoBaoLayout(bean);
    }

    //垂直列表
    private void initTaoBaoLayout(TaoBaoHomeBean bean) {
        if (adapter == null) {
            adapter = new TaoBaoFeaturedVerRecViewAdapter(getContext(), bean.getResult_list());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0, 0);
            taobao_featured_ver_rv.setLayoutManager(manager);
            taobao_featured_ver_rv.addItemDecoration(itemDecoration);
            taobao_featured_ver_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(id -> {
                Bundle bundle = new Bundle();
                bundle.putLong(TAO_BAO_ID, id);
                $startActivity(MerchandiseDetailsActivity.class, bundle);
            });
        } else {
            adapter.addList(bean.getResult_list(), page);
        }
    }

    //获取自营商品
    @Override
    public void getSelfEmployedSuccess(SelfEmployedBean bean) {
        initSelfLayout(bean);
    }

    //垂直列表
    private void initSelfLayout(SelfEmployedBean bean) {
        if (employedAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0, 0);
            employedAdapter = new SelfEmployedAdapter(getContext(), bean.getList());
            self_employed_ver_rv.setLayoutManager(manager);
            self_employed_ver_rv.addItemDecoration(itemDecoration);
            self_employed_ver_rv.setAdapter(employedAdapter);
            employedAdapter.setOnBaseViewClickListener(id -> {
                Bundle bundle = new Bundle();
                bundle.putInt(GOOD_TYPE, SELF_EMPLOYED);
                bundle.putInt(GOOD_ID, id);
                $startActivity(CommodityInfoActivity.class, bundle);
            });
        } else {
            employedAdapter.updataList(bean.getList());
        }
    }

    @Override
    public void gainBannerFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void onDestroy() {
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
        employedAdapter = null;
    }

    @Override
    public void getTaoBaoHomeFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void getSelfEmployedFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == TAO_BAO_FEATURED_REC_VIEW) {
            taobao_featured_grid_sl.fullScroll(View.FOCUS_UP);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注册
        EventBus.getDefault().unregister(this);
    }
}
