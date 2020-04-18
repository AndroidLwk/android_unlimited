package com.wuxiantao.wxt.ui.fragment.all_order.sub;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.TaoBaoOrderListRecViewAdapter;
import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.mvp.contract.TaoBaoOrderListContract;
import com.wuxiantao.wxt.mvp.presenter.TaoBaoOrderListPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;
import com.wuxiantao.wxt.ui.activity.OrderDetailsInfoActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.wuxiantao.wxt.config.Constant.ITEM_V_SPACE;
import static com.wuxiantao.wxt.config.Constant.ORDER_ID;
import static com.wuxiantao.wxt.config.Constant.ORDER_STATUS;
import static com.wuxiantao.wxt.config.Constant.ORDER_TYPE;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubOrderFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午4:05
 * Description:${DESCRIPTION} 淘宝商品订单->全部订单/验证中/验证成功/已失效
 */
@ContentView(R.layout.fragment_taobao_order_all)
public class TaoBaoSubOrderFragment extends MvpFragment<TaoBaoOrderListPresenter, TaoBaoOrderListContract.IOrderListView> implements TaoBaoOrderListContract.IOrderListView {

    @ViewInject(R.id.taobao_order_all_sub_rl)
    SmartRefreshLayout order_all_rl;
    @ViewInject(R.id.taobao_order_all_sub_classics_header)
    ClassicsHeader order_all_classics_header;
    @ViewInject(R.id.taobao_order_all_sub_rv)
    RecyclerView order_all_rv;

    private TaoBaoOrderListRecViewAdapter adapter;
    private Map<String,Object> parameters = new HashMap<>();
    private int page = 1;
    private TaoBaoOrderListBean datas;
    private int status;

    @Override
    protected TaoBaoOrderListPresenter CreatePresenter() {
        return new TaoBaoOrderListPresenter();
    }

    @Override
    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,ITEM_V_SPACE);
        order_all_rv.setLayoutManager(manager);
        order_all_rv.addItemDecoration(itemDecoration);

        Bundle bundle = getArguments();
        if (bundle != null){
            status = bundle.getInt(ORDER_STATUS);
        }
        parameters.put(TOKEN,getAppToken());
        parameters.put("page",page);
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("status",status);
        //1.android 2.ios
        parameters.put("device",1);
        mPresenter.getTaoBaoOrderList(parameters);
        initRefreshLoadmore();
    }

    private void initRefreshLoadmore(){
        order_all_rl.setRefreshHeader(order_all_classics_header);
        order_all_rl.setRefreshFooter(new BallPulseFooter(Objects.requireNonNull(getContext())).setSpinnerStyle(SpinnerStyle.Scale));
        order_all_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            parameters.put("page",page);
            mPresenter.getTaoBaoOrderList(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        order_all_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.getTaoBaoOrderList(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void getTaoBaoOrderListSuccess(TaoBaoOrderListBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    @Override
    public void getTaoBaoOrderListFailure(String failure) {
        if (failure.contains(getString(R.string.tao_bao_auth_expired)) ||
                failure.contains(getString(R.string.tao_bao_auth_not))){
            showOnlyConfirmDialog(failure, (dialog, which) -> $startActivity(MyInformationActivity.class));
        }else {
            showOnlyConfirmDialog(failure);
        }
        initVerLayout(null);
    }

    //垂直列表
    private void initVerLayout(TaoBaoOrderListBean bean){
        if (adapter == null){
            adapter = new TaoBaoOrderListRecViewAdapter(getContext(),bean == null ? null : bean.getList(),status);
            order_all_rv.setAdapter(adapter);
            adapter.setOnBaseViewClickListener(id -> {
                Bundle bundle = new Bundle();
                bundle.putInt(ORDER_TYPE,0);
                bundle.putInt(ORDER_ID,id);
                $startActivity(OrderDetailsInfoActivity.class,bundle);
            });
        }else {
            adapter.addList(bean == null ? null : bean.getList(),page);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
