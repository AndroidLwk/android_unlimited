package com.wuxiantao.wxt.ui.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.BaseExampleBean;
import com.wuxiantao.wxt.adapter.bean.MineMenuBean;
import com.wuxiantao.wxt.adapter.bean.RecentOrderBean;
import com.wuxiantao.wxt.adapter.recview.MineMenuVerRecViewAdapter;
import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.bean.TaobaoLatelyOrderBean;
import com.wuxiantao.wxt.bean.YouXuanLatelyOrderBean;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.presenter.MinePresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.AllOrderActivity;
import com.wuxiantao.wxt.ui.activity.HelpCenterActivity;
import com.wuxiantao.wxt.ui.activity.MineBalanceActivity;
import com.wuxiantao.wxt.ui.activity.MineFanSiActivity;
import com.wuxiantao.wxt.ui.activity.MoreIncomeActivity;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;
import com.wuxiantao.wxt.ui.activity.OrderDetailsInfoActivity;
import com.wuxiantao.wxt.ui.activity.ProfitRecordingActivity;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.progress.ArcProgressBar;
import com.wuxiantao.wxt.ui.popupwindow.ImagePopupWindow;
import com.wuxiantao.wxt.ui.title.CNToolbar;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.MathUtils;
import com.wuxiantao.wxt.utils.RegexUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.IS_SHOW_SAMALL_ICON;
import static com.wuxiantao.wxt.config.Constant.ORDER_ID;
import static com.wuxiantao.wxt.config.Constant.ORDER_TYPE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TYPE_NORMAL;
import static com.wuxiantao.wxt.config.Constant.TYPE_ORDER_INFO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 个人中心
 */
@ContentView(R.layout.fragment_my_deposit)
public class MyDepositFragment extends MvpFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {
    @ViewInject(R.id.my_deposit_title)
    CNToolbar my_deposit_title;
    @ViewInject(R.id.fragment_mine_cp)
    ArcProgressBar fragment_mine_cp;
    @ViewInject(R.id.fragment_mine_appbar)
    AppBarLayout fragment_mine_appbar;
    @ViewInject(R.id.mine_rl)
    SmartRefreshLayout mine_rl;
    @ViewInject(R.id.mine_classic_header)
    ClassicsHeader mine_classic_header;
    @ViewInject(R.id.mine_expected_interest)
    LinearLayout mine_expected_interest;
    @ViewInject(R.id.fragment_mine_rv)
    RecyclerView mRecyclerView;
    @ViewInject(R.id.mine_available_money)
    LinearLayout mine_available_money;
    @ViewInject(R.id.mine_interest_rate)
    LinearLayout mine_interest_rate;
    @ViewInject(R.id.fragment_mine_production)
    StateButton fragment_mine_production;

    @ViewInject(R.id.mine_yall)
    TextView mine_yall;
    @ViewInject(R.id.mine_aninter_rate)
    TextView mine_aninter_rate;
    @ViewInject(R.id.mine_deposit_amount)
    TextView mine_deposit_amount;
    @ViewInject(R.id.mine_amount)
    TextView mine_amount;

    private MineMenuVerRecViewAdapter adapter;
    private Animation mAnimation;
    //官方url
    private String official_url;

    @Override
    protected MinePresenter CreatePresenter() {
        return new MinePresenter();
    }

    @Override
    public void initView() {
        boolean isReview =  isUserAuthorization(IS_REVIEW);

        fragment_mine_appbar.setVisibility(isReview ? View.GONE : View.VISIBLE);
        if (!isEmpty(getAppToken())){
            mPresenter.obtainMyDeposit(getAppToken());
            mPresenter.getOrderType(getAppToken());
        }else {
            initTabaoLayout(null);
        }
        initRefreshLoad();
        my_deposit_title.setOnRightButtonClickListener(() -> {
            if (!isEmpty(official_url)){
                showOfficialWindow(official_url);
            }
        });
    }


    private void showOfficialWindow(String url){
        new ImagePopupWindow.Build(getContext())
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setImageUrl(url)
                .builder().showPopupWindow();

    }

    private void initRefreshLoad(){
        mine_classic_header.setBackgroundResource(R.drawable.base_title_background);
        mine_rl.setRefreshHeader(mine_classic_header);
        mine_rl.setEnableLoadMore(false);
        mine_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            fragment_mine_cp.setProgress(0);
            zoomOutAnimation();
            if (!isEmpty(getAppToken())){
                mPresenter.obtainMyDeposit(getAppToken());
            }
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        setOnClikListener(mine_available_money,
                mine_interest_rate,fragment_mine_production,fragment_mine_cp);
    }


    @Override
    protected void widgetClick(int id) {
        switch (id){
            //我的余额
            case R.id.mine_interest_rate:
            case R.id.mine_available_money:
                $startActivity(MineBalanceActivity.class);
                break;
            //查看更多收益
            case R.id.fragment_mine_production:
                $startActivity(MoreIncomeActivity.class);
                break;
            //收益记录
            case R.id.fragment_mine_cp:
                $startActivity(ProfitRecordingActivity.class);
                break;
        }
    }

    //我的存款信息获取成功
    @Override
    public void obtainMyDepositSuccess(MyDepositBean bean) {
        String defaultValue = getString(R.string.zero);
        //冻结佣金
        String yall = String.valueOf(bean.getList().getTao_ferrze());
        //红包收益
        String aninter_rate = String.valueOf(bean.getList().getRed_profit());
        //佣金收益
        String deposit_amount = String.valueOf(bean.getList().getYongjin());
        //可用提现
        String amount = BigDecimalUtils.roundStr(bean.getList().getAmount());
        //今日收益
        String todaym = String.valueOf(bean.getList().getTotal());

        //激励每日分红还需多少等级
        String dividend = bean.getList().getFenhong();
        int cha = bean.getList().getCha();
        String text = getString(R.string.current_deposit,dividend,cha);
        int color = Color.parseColor("#FF0000");
        TextViewUtils.setTextViewKeyWordHighlight(mine_yall, text, color, new Pair<>(4, (dividend.length()) + 4 + 1));
        mine_aninter_rate.setText(isEmpty(aninter_rate) || aninter_rate.equals(getString(R.string._zero))  ? defaultValue : MathUtils.formatCurrency(aninter_rate,DECIMAL_BIT));

        //当前等级
        mine_deposit_amount.setText(getString(R.string.level,bean.getList().getLevel()));
        //官方url
        official_url = bean.getList().getQun_img();
        mine_amount.setText(isEmpty(amount) || amount.equals(getString(R.string._zero))  ? defaultValue : MathUtils.formatCurrency(amount,DECIMAL_BIT));
        float progress = isEmpty(aninter_rate) || aninter_rate.equals(getString(R.string._zero)) ? 0 : Float.valueOf(aninter_rate) * 100;
        fragment_mine_cp.setProgress(progress);
        fragment_mine_cp.setSecondText(isEmpty(todaym) ? defaultValue : MathUtils.formatCurrency(todaym,3));
        spreadAnimation();
    }

    //获取我的订单类型
    @Override
    public void getOrderTypeSuccess(OrderTypeBean bean) {
        mPresenter.getLatelyOrder(bean.getType(),getAppToken(),bean.getOrder_no());
    }

    @Override
    public void getOrderTypeFailure(String failure) {
        if (failure.contains(getString(R.string.no_order))){
            initYouXuanLayout(null);
        }else {
            showOnlyConfirmDialog(failure);
        }
    }


    //淘宝订单列表
    @Override
    public void getTaobaoLatelyOrderSuccess(TaobaoLatelyOrderBean bean) {
        initTabaoLayout(bean);
    }

    //自营订单列表
    @Override
    public void getYouXuanLatelyOrderSuccess(YouXuanLatelyOrderBean bean) {
        initYouXuanLayout(bean);
    }

    private void initTabaoLayout(TaobaoLatelyOrderBean bean){
        if (adapter == null){
            adapter = new MineMenuVerRecViewAdapter(getContext(),initTaoBaoList(bean));
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(20,0);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(itemDecoration);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MineMenuVerRecViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    startActivity(bean,position);
                }

                @Override
                public void onViewOrderInfo(int id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ORDER_TYPE,0);
                    bundle.putInt(ORDER_ID,id);
                    $startActivity(OrderDetailsInfoActivity.class,bundle);
                }
            });
        }else {
            adapter.updateList(initTaoBaoList(bean));
        }
    }

    private void initYouXuanLayout(YouXuanLatelyOrderBean bean){
        if (adapter == null){
            adapter = new MineMenuVerRecViewAdapter(getContext(),initYouXuanList(bean));
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(20,0);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(itemDecoration);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new MineMenuVerRecViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    startActivity(bean,position);
                }

                @Override
                public void onViewOrderInfo(int id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ORDER_TYPE,1);
                    bundle.putInt(ORDER_ID,id);
                    $startActivity(OrderDetailsInfoActivity.class,bundle);
                }
            });
        }else {
            adapter.updateList(initYouXuanList(bean));
        }
    }

    private void startActivity(Object obj,int position){
        if (obj != null){
            switch (position){
                case 0:
                    $startActivity(MyInformationActivity.class);
                    break;
                case 1:
                case 2:
                    $startActivity(AllOrderActivity.class);
                    break;
                case 3:
                    $startActivity(MineFanSiActivity.class,true);
                    break;
                case 4:

                    $startActivity(ShareThemActivity.class);
                    break;
                case 5:
                    $startActivity(HelpCenterActivity.class);
                    break;
            }
        }else {
            switch (position){
                case 0:
                    $startActivity(MyInformationActivity.class);
                    break;
                case 1:
                    $startActivity(AllOrderActivity.class);
                    break;
                case 2:
                    $startActivity(MineFanSiActivity.class,true);
                    break;
                case 3:
                    $startActivity(ShareThemActivity.class);
                    break;
                case 4:
                    $startActivity(HelpCenterActivity.class);
                    break;
            }
        }
    }

    private List<BaseExampleBean> initTaoBaoList(TaobaoLatelyOrderBean bean){
        List<BaseExampleBean> list = new ArrayList<>();

        MineMenuBean menuBean1 = createMineMenuBean(R.mipmap.mine_icon_personal_center,R.string.person_center);
        MineMenuBean menuBean3 = createMineMenuBean(R.mipmap.mine_icon_order,R.string.recent_order);
        MineMenuBean menuBean4 = createMineMenuBean(R.mipmap.fansi_icon,R.string.my_fans);
        MineMenuBean menuBean5 = createMineMenuBean(R.mipmap.mine_icon_invite_fds,R.string.invite_friend_);
        MineMenuBean menuBean6 = createMineMenuBean(R.mipmap.mine_icon_help_center,R.string.help_center);

        list.add(menuBean1);
        list.add(menuBean3);

        initTaoBaoLatelyOrderList(list,bean);

        initListFooter(list,menuBean4,menuBean5,menuBean6);
        return list;
    }

    private List<BaseExampleBean> initYouXuanList(YouXuanLatelyOrderBean bean){
        List<BaseExampleBean> list = new ArrayList<>();

        MineMenuBean menuBean1 = createMineMenuBean(R.mipmap.mine_icon_personal_center,R.string.person_center);
        MineMenuBean menuBean3 = createMineMenuBean(R.mipmap.mine_icon_order,R.string.recent_order);
        MineMenuBean menuBean4 = createMineMenuBean(R.mipmap.fansi_icon,R.string.my_fans);
        MineMenuBean menuBean5 = createMineMenuBean(R.mipmap.mine_icon_invite_fds,R.string.invite_friend_);
        MineMenuBean menuBean6 = createMineMenuBean(R.mipmap.mine_icon_help_center,R.string.help_center);

        list.add(menuBean1);
        list.add(menuBean3);

        initYouXuanLatelyOrderList(list,bean);

        initListFooter(list,menuBean4,menuBean5,menuBean6);
        return list;
    }

    private MineMenuBean createMineMenuBean(int icon, @StringRes int title){
        MineMenuBean bean = new MineMenuBean(icon,getString(title),isUserAuthorization(IS_SHOW_SAMALL_ICON));
        bean.setType(TYPE_NORMAL);
        return bean;
    }


    private void initListFooter(List<BaseExampleBean> list,MineMenuBean ...menuBean){
        list.addAll(Arrays.asList(menuBean));
    }

    private void initTaoBaoLatelyOrderList(List<BaseExampleBean> list,TaobaoLatelyOrderBean bean){
        if (bean != null){
            String url = bean.getPict_url();
            if (url.startsWith("//")){
                url = "http:" + url;
            }
            String title = bean.getItem_title();
            String money = bean.getCommission_rate();
            int goods_id = bean.getId();
            String order_type = bean.getOrder_type();
            int orderType = -1;
            if (order_type.contains(getString(R.string.taobao))){
                orderType = RecentOrderBean.RECENT_ORDER_TAOBAO;
            }else if (order_type.contains(getString(R.string.tianmao))){
                orderType = RecentOrderBean.RECENT_ORDER_TMALL;
            }
            RecentOrderBean recentOrderBean = new RecentOrderBean();
            recentOrderBean.setUrl(url);
            recentOrderBean.setTitle(title);
            recentOrderBean.setMoney(money);
            recentOrderBean.setId(goods_id);
            recentOrderBean.setOrderType(orderType);
            recentOrderBean.setRate_money(String.valueOf(BigDecimalUtils.round(bean.getRebate(),2)));
            recentOrderBean.setType(TYPE_ORDER_INFO);
            recentOrderBean.setOrderStatus(bean.getTk_status());
            list.add(recentOrderBean);
        }
    }

    private void initYouXuanLatelyOrderList(List<BaseExampleBean> list,YouXuanLatelyOrderBean bean){
        if (bean != null){
            String imgs = bean.getGoods().getGoods_image();
            String title = bean.getGoods().getGoods_title();
            int orderType = RecentOrderBean.RECENT_ORDER_YOUXUAN;
            RecentOrderBean recentOrderBean = new RecentOrderBean();
            recentOrderBean.setUrl(RegexUtils.imgUrlSeparate(imgs));
            recentOrderBean.setTitle(title);
            recentOrderBean.setMoney(BigDecimalUtils.round(bean.getMoney(),2));
            recentOrderBean.setId(bean.getId());
            recentOrderBean.setOrderType(orderType);
            recentOrderBean.setVip_level(bean.getVip_level());
            recentOrderBean.setRate_money(BigDecimalUtils.round(bean.getRate_money(),2));
            recentOrderBean.setType(TYPE_ORDER_INFO);
            recentOrderBean.setOrderStatus(bean.getStatus());
            list.add(recentOrderBean);
        }
    }



    @Override
    public void obtainMyDepositFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void getTaobaoLatelyOrderFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    //放大动画
    private void spreadAnimation(){
        mAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.spread_anim);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mine_expected_interest.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mine_expected_interest.startAnimation(mAnimation);
    }

    //缩小动画
    private void zoomOutAnimation(){
        mAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out_anim);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mine_expected_interest.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mine_expected_interest.startAnimation(mAnimation);
    }

    private void clearAnimation(){
        if (mAnimation != null){
            mAnimation.cancel();
            mAnimation.reset();
            mAnimation = null;
            mine_expected_interest.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearAnimation();
    }

}
