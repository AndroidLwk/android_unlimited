package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewPagerAdapter;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.mvp.contract.MineFansiContract;
import com.wuxiantao.wxt.mvp.presenter.MineFansiPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiDirectlyFragment;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiIndirectFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineFanSiActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午4:24
 * Description:${DESCRIPTION}我的粉丝
 */
@ContentView(R.layout.activity_mine_fansi)
public class MineFanSiActivity extends MvpActivity<MineFansiPresenter, MineFansiContract.IFansiView> implements MineFansiContract.IFansiView {
    @ViewInject(R.id.mine_fansi_back)
    private RelativeLayout mine_fansi_back;
    @ViewInject(R.id.mine_fansi_title_img)
    private ImageView mine_fansi_title_img;
    @ViewInject(R.id.mine_fansi_title_text)
    private TextView mine_fansi_title_text;
    @ViewInject(R.id.mine_fansi_viewpager)
    private LazyViewPager mViewpager;
    @ViewInject(R.id.mine_fansi_indicator)
    private VPindicator mIndicator;
    private List<Fragment> mFragments = new ArrayList<>();
    private LoadingDialog loadingDialog;

    @Override
    protected MineFansiPresenter CreatePresenter() {
        return new MineFansiPresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(mine_fansi_back, mine_fansi_title_text, mine_fansi_title_img);
        setStatusBar();
        initFragment();
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.obtainFansiHeadInfo(getAppToken());
        initViewPager();
    }


    private void initViewPager() {
        String[] titleArray = getResources().getStringArray(R.array.mine_fansi_title);
        FragmentManager fm = getSupportFragmentManager();
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(fm, mFragments, titleArray);
        // 设置adapter
        mViewpager.setAdapter(adapter);
        // 绑定indicator
        mIndicator.setViewPager(mViewpager);
        setTabPagerIndicator();
    }

    //设置指示器颜色
    private void setTabPagerIndicator() {
        // 设置模式，一定要先设置模式
        mIndicator.setIndicatorMode(VPindicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
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

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //返回
            case R.id.mine_fansi_back:
                finish();
                break;
        }
    }
    private void initFragment() {
        mFragments.add(new FanSiDirectlyFragment());
        mFragments.add(new FanSiIndirectFragment());
    }

    private void showNoSuperiorDialog() {
        showOnlyConfirmDialog(R.string.prompt, R.string.voluntary_occupation);
    }

    @Override
    public void obtainFansiHeadInfoSuccess(MyFansiHeadInfoBean bean) {

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
