package com.wuxiantao.wxt.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.SearchResultRecViewAdapter;
import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.TaoBaoFeaturedSubContract;
import com.wuxiantao.wxt.mvp.presenter.TaoBaoFeaturedSubPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.MerchandiseDetailsActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioButton;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioGroup;
import com.wuxiantao.wxt.ui.custom.scroller.TopSmoothScroller;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.CATE_ID;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_CLOSE;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_OPEN;
import static com.wuxiantao.wxt.config.Constant.MAIN_TAO_BAO_REC_VIEW;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午7:37
 * Description:${DESCRIPTION} 淘宝优选->子fragment
 */
@ContentView(R.layout.fragment_taobao_featured_sub)
public class TaoBaoFeaturedSubFragment extends MvpFragment<TaoBaoFeaturedSubPresenter, TaoBaoFeaturedSubContract.ITaoBaoSubView> implements TaoBaoFeaturedSubContract.ITaoBaoSubView {
    @ViewInject(R.id.fragment_tao_bao_featured_sub_rl)
    SmartRefreshLayout mSmartRefreshLayout;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_classic_header)
    ClassicsHeader mClassicsHeader;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_dl)
    DrawerLayout mDrawerLayout;
    @ViewInject(R.id.fragment_tao_bao_featured_sub__rb)
    SoftRadioGroup mSoftRadioGroup;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_comprehensive_rb)
    SoftRadioButton comprehensive_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_volume_rb)
    SoftRadioButton volume_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_coupon_rb)
    SoftRadioButton coupon_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_filter_rb)
    SoftRadioButton filter_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_rv)
    RecyclerView mRecyclerView;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_layout)
    RelativeLayout mRelativeLayout;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_right_rg)
    RadioGroup mRadioGroup;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_right_tianmao)
    RadioButton right_tianmao_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_unlimited)
    RadioButton right_unlimited_rb;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_input_lowest)
    EditText input_lowest;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_input_highest)
    EditText input_highest;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_right_reset)
    StateButton right_reset;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_right_confirm)
    StateButton right_confirm;

    private int page = 1;
    private SearchResultRecViewAdapter adapter;
    private Map<String,Object> parameters = new HashMap<>();
    private SearchResultBean datas;
    private LinearLayoutManager manager;
    private TopSmoothScroller mScroller;

    @Override
    protected TaoBaoFeaturedSubPresenter CreatePresenter() {
        return new TaoBaoFeaturedSubPresenter();
    }

    @Override
    public void initView() {
        //注册
        EventBus.getDefault().register(this);
        mScroller = new TopSmoothScroller(getContext());
        Bundle bundle = getArguments();
        if (bundle != null){
            String cateID = bundle.getString(CATE_ID);
            if (cateID != null){
                parameters.put(TOKEN,getAppToken());
                parameters.put("pagesize",PAGE_SIZE);
                parameters.put("page",page);
                parameters.put("cat",cateID);
                mPresenter.onSearch(parameters);
            }
        }
        initRefreshLoad();
        initFilterRb();
        setOnClikListener(right_reset,right_confirm);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                EventBus.getDefault().post(new MessageEvent(MAIN_TAO_BAO_OPEN));
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                EventBus.getDefault().post(new MessageEvent(MAIN_TAO_BAO_CLOSE));
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void initFilterRb(){
        mSoftRadioGroup.setOnCheckedChangeListener((group, checkedId, orientation) -> {
            switch (checkedId){
                //综合
                case R.id.fragment_tao_bao_featured_sub_comprehensive_rb:
                    parameters.put("sort",0);
                    break;
                //销量
                case R.id.fragment_tao_bao_featured_sub_volume_rb:
                    parameters.put("sort",orientation ? 1 : 2);
                    break;
                //券后价
                case R.id.fragment_tao_bao_featured_sub_coupon_rb:
                    parameters.put("sort",orientation ? 3 : 4);
                    break;
                // 筛选
                case R.id.fragment_tao_bao_featured_sub_filter_rb:
                    //右边菜单栏  如果打开 就关闭
                    if (mDrawerLayout.isDrawerOpen(Gravity.END)){
                        mDrawerLayout.closeDrawer(Gravity.END);
                    }else {
                        //如果关闭就打开
                        mDrawerLayout.openDrawer(Gravity.END);
                    }
                    break;
            }
            mPresenter.onSearch(parameters);
        });
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                //天猫
                case R.id.fragment_tao_bao_featured_sub_right_tianmao:

                    break;
                //不限制
                case R.id.fragment_tao_bao_featured_sub_unlimited:

                    break;
            }
        });
    }


    private void initRefreshLoad(){
        mSmartRefreshLayout.setRefreshHeader(mClassicsHeader);
        mSmartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mSmartRefreshLayout.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            parameters.put("page",page);
            mPresenter.onSearch(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.onSearch(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    public void onSearchSuccess(SearchResultBean bean) {
        this.datas = bean;
        initLayout(bean);
    }

    @Override
    public void onSearchFailure(String failure) {
        ToastUtils.error(failure);
    }

    private void initLayout(SearchResultBean bean){
        if (adapter == null){
            manager = new LinearLayoutManager(getContext());
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(20,0);
            adapter = new SearchResultRecViewAdapter(getContext(),bean.getResult_list());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(decoration);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnSearchResultItemClickListener(id -> {
                Bundle bundle = new Bundle();
                bundle.putLong(TAO_BAO_ID,id);
                $startActivity(MerchandiseDetailsActivity.class,bundle);
            });
        }else {
            adapter.addList(bean.getResult_list(),page);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
    }

    @Override
    protected void widgetClick(int id) {
        switch (id){
            //重置
            case R.id.fragment_tao_bao_featured_sub_right_reset:
                input_lowest.setText("");
                input_highest.setText("");
                if (!right_unlimited_rb.isChecked()){
                    right_unlimited_rb.setChecked(true);
                }
                parameters.remove("is_tmall");
                parameters.remove("start_price");
                parameters.remove("end_price");
                mPresenter.onSearch(parameters);
                break;
            //确定
            case R.id.fragment_tao_bao_featured_sub_right_confirm:
                parameters.put("is_tmall",right_unlimited_rb.isChecked() ? 0 : 1);
                parameters.put("start_price",getEdtText(input_lowest));
                parameters.put("end_price",getEdtText(input_highest));
                mDrawerLayout.closeDrawer(Gravity.END);
                mPresenter.onSearch(parameters);
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        if (event.getType() == MAIN_TAO_BAO_REC_VIEW){
            mScroller.setTargetPosition(0);
            manager.startSmoothScroll(mScroller);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //右边菜单栏  如果打开 就关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.END)){
            EventBus.getDefault().post(new MessageEvent(MAIN_TAO_BAO_CLOSE));
            mDrawerLayout.closeDrawer(Gravity.END);
        }
        //注册
        EventBus.getDefault().unregister(this);
    }


}
