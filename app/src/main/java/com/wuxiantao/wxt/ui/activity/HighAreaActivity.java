package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.HighAreaRecViewAdapter;
import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.HighAreaContract;
import com.wuxiantao.wxt.mvp.presenter.HighAreaPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.scroller.TopSmoothScroller;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.COMMODITY_SPACE;
import static com.wuxiantao.wxt.config.Constant.GOOD_ID;
import static com.wuxiantao.wxt.config.Constant.GOOD_TYPE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.UPDATE_HIGH_AREA;
import static com.wuxiantao.wxt.config.Constant.ZERO_BUY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午1:41
 * Description:${DESCRIPTION} 高佣商品/0元购
 */
@ContentView(R.layout.fragment_high_area)
public class HighAreaActivity extends MvpActivity<HighAreaPresenter, HighAreaContract.IHighAreaView> implements HighAreaContract.IHighAreaView{
    @ViewInject(R.id.high_area_toolbar)
    CNToolbar high_area_toolbar;
    @ViewInject(R.id.fragment_high_area_appbar)
    AppBarLayout high_area_appbar;
    @ViewInject(R.id.fragment_high_area_rl)
    SmartRefreshLayout fragment_high_area_rl;
    @ViewInject(R.id.fragment_high_area_classic_header)
    ClassicsHeader fragment_high_area_classic_header;

    @ViewInject(R.id.fragment_high_area_rv)
    RecyclerView fragment_high_area_rv;
    @ViewInject(R.id.fragment_high_area_topping)
    ImageView fragment_high_area_topping;

    private HighAreaRecViewAdapter adapter;
    private int page = 1;
    private HighAreaListBean datas;

    @Override
    protected HighAreaPresenter CreatePresenter() {
        return new HighAreaPresenter();
    }

    @Override
    public void initView() {
        setStatusBar();
        //注册
        EventBus.getDefault().register(this);
        mScroller = new TopSmoothScroller(this);
        mPresenter.getHighAreaList(getAppToken(),page);
        initRefreshLoad();
        setOnClikListener(fragment_high_area_topping);
    }


    private void initRefreshLoad(){
        fragment_high_area_classic_header.setBackgroundResource(R.drawable.base_title_background);
        fragment_high_area_rl.setRefreshHeader(fragment_high_area_classic_header);
        fragment_high_area_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        fragment_high_area_rl.setOnRefreshListener(r ->{
            r.resetNoMoreData();
            page = 1;
            mPresenter.getHighAreaList(getAppToken(),page);
            r.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        fragment_high_area_rl.setOnLoadMoreListener(r -> {
            if (datas != null && datas.getMore() == 1){
                mPresenter.getHighAreaList(getAppToken(),++page);
                r.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                r.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    protected void widgetClick(int id) {
        //置顶
        high_area_appbar.setExpanded(true);
        mScroller.setTargetPosition(0);
        manager.startSmoothScroll(mScroller);
    }

    @Override
    public void getHighAreaListSuccess(HighAreaListBean bean) {
        this.datas = bean;
        initLayout(bean);
    }

    @Override
    public void getHighAreaListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    private LinearLayoutManager manager;
    private TopSmoothScroller mScroller;

    private void initLayout(HighAreaListBean bean){
        if (adapter == null){
            manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(20,20);
            int status = bean.getStatus();
            adapter = new HighAreaRecViewAdapter(this,bean.getGoodslist(),status);
            fragment_high_area_rv.setLayoutManager(manager);
            fragment_high_area_rv.addItemDecoration(decoration);
            fragment_high_area_rv.setAdapter(adapter);
            adapter.setOnItemClickListener((type, goodsId, s) -> {
                //1.自营商品  2.0元购商品
                Bundle bundle = new Bundle();
                bundle.putInt(GOOD_TYPE,ZERO_BUY);
                bundle.putInt(GOOD_ID,goodsId);
                bundle.putString(COMMODITY_SPACE,s);
                $startActivity(CommodityInfoActivity.class,bundle);
            });
        }else {
            adapter.addList(bean.getGoodslist(),page);
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void setTitle() {
        high_area_toolbar.setOnLeftButtonClickListener(HighAreaActivity.this::finish);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        if (event.getType() == UPDATE_HIGH_AREA){
            mPresenter.getHighAreaList(getAppToken(),page);
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
