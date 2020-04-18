package com.wuxiantao.wxt.ui.fragment.all_order;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.viewpager.TaoBaoOrderSubViewPagerAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.base.BaseFragment;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_TAO_BAO_AUTH;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-20 下午3:02
 * Description:${DESCRIPTION} 淘宝商品订单
 */
@ContentView(R.layout.fragment_taobao_order)
public class TaoBaoOrderFragment extends BaseFragment {
    @ViewInject(R.id.fragment_taobao_order_indicator)
    VPindicator mIndicator;
    @ViewInject(R.id.fragment_taobao_order_vp)
    LazyViewPager mViewPager;

    @Override
    public void initView() {
        String[] titleArray = getResources().getStringArray(R.array.order_status_title);
        List<String> list = Arrays.asList(titleArray);
        FragmentManager fm = getChildFragmentManager();
        // 设置adapter
        mViewPager.setAdapter(new TaoBaoOrderSubViewPagerAdapter(fm, list));
        // 绑定indicator
        mIndicator.setViewPager(mViewPager);
        setTabPagerIndicator();
        boolean isAuth = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_TAO_BAO_AUTH,true);
        if (!isAuth){
            $startActivity(MyInformationActivity.class);
        }
    }


    //设置指示器颜色
    private void setTabPagerIndicator(){
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
}
