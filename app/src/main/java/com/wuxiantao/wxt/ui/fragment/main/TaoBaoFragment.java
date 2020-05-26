package com.wuxiantao.wxt.ui.fragment.main;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.viewpager.TaoBaoSubViewPagerAdapter;
import com.wuxiantao.wxt.bean.TaoBaoSortBean;
import com.wuxiantao.wxt.bean.TaoBaoSubBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.TaoBaoSortContract;
import com.wuxiantao.wxt.mvp.presenter.TaoBaoSortPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.HelpCenterActivity;
import com.wuxiantao.wxt.ui.activity.SearchActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;
import com.wuxiantao.wxt.ui.popupwindow.NoticePopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.newUserGiftPopupWindow;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.IS_STARTED_LOADING;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_CLOSE;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_OPEN;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_REC_VIEW;
import static com.wuxiantao.wxt.config.Constant.NOTICE_FIRST;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_FEATURED_REC_VIEW;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 首页
 */
@ContentView(R.layout.fragment_tao_bao)
public class TaoBaoFragment extends MvpFragment<TaoBaoSortPresenter, TaoBaoSortContract.ITaoBaoSortView> implements TaoBaoSortContract.ITaoBaoSortView {
    @ViewInject(R.id.main_taobao_topping)
    ImageView main_taobao_topping;
    @ViewInject(R.id.fragment_tao_bao_key)
    SimpleMarqueeView<String> marqueeView;
    @ViewInject(R.id.fragment_tao_bao_title)
    AppBarLayout fragment_tao_bao_title;
    @ViewInject(R.id.fragment_tao_bao_search)
    RelativeLayout fragment_tao_bao_search;
    @ViewInject(R.id.fragment_tao_bao_save_money)
    LinearLayout fragment_tao_bao_save_money;
    @ViewInject(R.id.main_taobao_indicator)
    VPindicator mIndicator;
    @ViewInject(R.id.main_taobao_viewpager)
    LazyViewPager mViewPager;
    @ViewInject(R.id.sbt_search)
    StateButton sbt_search;

    private List<TaoBaoSubBean> list = new ArrayList<>();

    @Override
    protected TaoBaoSortPresenter CreatePresenter() {
        return new TaoBaoSortPresenter();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void initView() {
        mPresenter.notice(getAppToken());
        //注册
        EventBus.getDefault().register(this);
//        if (getUserStatus(NEW_AWARD_STATUS) == 0 && getSPBoolean(IS_STARTED_LOADING)) {
//            showNewUserWindow();
//        }
        mPresenter.getTaoBaoSort();
        setOnClikListener(sbt_search, fragment_tao_bao_search, fragment_tao_bao_save_money, main_taobao_topping);
        //initMvText();

        boolean isReview = getSPBoolean(IS_REVIEW);
        fragment_tao_bao_save_money.setVisibility(isReview ? View.GONE : View.VISIBLE);
    }

    //跑马灯
    private void initMvText() {
        //https://github.com/gongwen/MarqueeViewLibrary
        List<String> list = new ArrayList<>();
        list.add(getString(R.string.paste_taobao_title));
        list.add(getString(R.string.paste_taobao_title));
        SimpleMF<String> mSimpleMF = new SimpleMF<>(getContext());
        mSimpleMF.setData(list);
        marqueeView.setMarqueeFactory(mSimpleMF);
        marqueeView.startFlipping();
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            //搜索
            case R.id.sbt_search:
            case R.id.fragment_tao_bao_search:
                $startActivity(SearchActivity.class);
                break;
            //省钱教程
            case R.id.fragment_tao_bao_save_money:
                $startActivity(HelpCenterActivity.class);
                break;
            //置顶
            case R.id.main_taobao_topping:
                if (mViewPager.getCurrentItem() == 0) {
                    EventBus.getDefault().post(new MessageEvent(TAO_BAO_FEATURED_REC_VIEW));
                } else {
                    EventBus.getDefault().post(new MessageEvent(MAIN_TAO_BAO_REC_VIEW));
                }
                fragment_tao_bao_title.setExpanded(true);
                break;
        }
    }


    private void showNewUserWindow() {
        String head_img = getUserInfo(USER_HEAD_IMG);
        new newUserGiftPopupWindow.Build(getContext())
                .setOnViewClickListener(() -> {
                    put(IS_STARTED_LOADING, false);
                    mPresenter.receiveRedBag(getAppToken());
                })
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setUserIcon(head_img)
                .builder()
                .showPopupWindow();
    }

    //设置指示器颜色
    private void setTabPagerIndicator() {
        // 设置模式，一定要先设置模式
        mIndicator.setIndicatorMode(VPindicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);
        // 设置分割线的颜色
//        my_coupon_indicator.setDividerColor(Color.parseColor("#00bbcf"));
        //设置
        mIndicator.setDividerPadding(getResources().getDimensionPixelSize(R.dimen.dp_10));
        // 设置底部导航线的颜色
        mIndicator.setIndicatorColor(Color.parseColor("#FF615B"));
        // 设置tab标题选中的颜色
        mIndicator.setTextColorSelected(Color.parseColor("#FF615B"));
        // 设置tab标题未被选中的颜色
        mIndicator.setTextColor(Color.parseColor("#FF111111"));
        // 设置字体大小
        mIndicator.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_15));
    }

    //获取淘宝顶部标题
    @Override
    public void getTaoBaoSortSuccess(List<TaoBaoSortBean> beans) {
        //添加首页
        list.add(new TaoBaoSubBean(getString(R.string.tao_bao_featured_sub), getString(R.string._zero)));
        for (TaoBaoSortBean bean : beans) {
            list.add(new TaoBaoSubBean(bean.getCate_title(), bean.getCate_id()));
        }
//        mFragmenList.add(new TaoBaoFeaturedFragment());

        FragmentManager fm = getChildFragmentManager();
//        String[] titleArray = getResources().getStringArray(R.array.main_taobao_viewpager_title);
        TaoBaoSubViewPagerAdapter adapter = new TaoBaoSubViewPagerAdapter(fm, list);
        // 设置adapter
        mViewPager.setAdapter(adapter);
        // 绑定indicator
        mIndicator.setViewPager(mViewPager);
        setTabPagerIndicator();
    }

    @Override
    public void getTaoBaoSortFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void receiveRedBagSuccess(String msg) {
        ToastUtils.success(msg);
    }

    @Override
    public void receiveRedBagFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void noticeFailure(String failure) {

    }

    @Override
    public void noticeSuccess(String content) {
        if (TextUtils.isEmpty(content) || getUserInfo(NOTICE_FIRST).equals("1")) {
            return;
        }
        new NoticePopupWindow.Build(getContext())
                .setWindowAnimStyle(R.style.custom_dialog)
                .setContent(content)
                .builder().showPopupWindow();
        saveUserInfo(NOTICE_FIRST, "1");//0：第一次 1：不是第一次
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == MAIN_TAO_BAO_OPEN) {
            main_taobao_topping.setVisibility(View.GONE);
            fragment_tao_bao_title.setExpanded(false);
        } else if (event.getType() == MAIN_TAO_BAO_CLOSE) {
            main_taobao_topping.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注册
        EventBus.getDefault().unregister(this);
    }

}
