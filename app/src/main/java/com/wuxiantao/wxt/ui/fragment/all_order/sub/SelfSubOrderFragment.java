package com.wuxiantao.wxt.ui.fragment.all_order.sub;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.SelfOrderListRecViewAdapter;
import com.wuxiantao.wxt.bean.SelfOrderListBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.SelfOrderListContract;
import com.wuxiantao.wxt.mvp.presenter.SelfOrderListPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.activity.OrderDetailsInfoActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.OrderPayModePopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ITEM_V_SPACE;
import static com.wuxiantao.wxt.config.Constant.ORDER_ID;
import static com.wuxiantao.wxt.config.Constant.ORDER_STATUS;
import static com.wuxiantao.wxt.config.Constant.ORDER_TYPE;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubOrderFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午4:05
 * Description:${DESCRIPTION} 自营商品----->全部订单
 */
@ContentView(R.layout.fragment_self_order_all)
public class SelfSubOrderFragment extends MvpFragment<SelfOrderListPresenter, SelfOrderListContract.IOrderListView> implements SelfOrderListContract.IOrderListView {

    @ViewInject(R.id.self_order_all_sub_rl)
    SmartRefreshLayout order_all_rl;
    @ViewInject(R.id.self_order_all_sub_classics_header)
    ClassicsHeader order_all_classics_header;
    @ViewInject(R.id.self_order_all_sub_rv)
    RecyclerView order_all_rv;

    private SelfOrderListRecViewAdapter adapter;
    private Map<String,Object> parameters = new HashMap<>();
    private int page = 1;
    private SelfOrderListBean datas;
    private String order_id;
    private int status;

    @Override
    protected SelfOrderListPresenter CreatePresenter() {
        return new SelfOrderListPresenter();
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
            parameters.put("status",status);
        }
        parameters.put(TOKEN,getAppToken());
        parameters.put("page",page);
        parameters.put("pagesize",PAGE_SIZE);
        mPresenter.getSelfOrderList(parameters);
        initRefreshLoadmore();
        PayListener.getInstance().addListener(this);
    }


    private void initRefreshLoadmore(){
        order_all_rl.setRefreshHeader(order_all_classics_header);
        order_all_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        order_all_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            parameters.put("page",page);
            mPresenter.getSelfOrderList(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        order_all_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.getSelfOrderList(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void getSelfOrderListSuccess(SelfOrderListBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    @Override
    public void getSelfOrderListFailure(String failure) {
        showOnlyConfirmDialog(failure);
        initVerLayout(null);
    }

    //垂直列表
    private void initVerLayout(SelfOrderListBean bean){
        if (adapter == null){
            adapter = new SelfOrderListRecViewAdapter(getContext(),bean.getList(),status);
            order_all_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(new SelfOrderListRecViewAdapter.OnItemClickListener() {
                @Override
                public void onQueryOrderDetail(int id) {
                    isQueryDetail = true;
                    Bundle bundle = new Bundle();
                    bundle.putInt(ORDER_TYPE,1);
                    bundle.putInt(ORDER_ID,id);
                    $startActivity(OrderDetailsInfoActivity.class,bundle);
                }

                @Override
                public void onOrderPay(String order_no,String money) {
                    createOrderInfo(order_no,money);
                }
            });
        }else {
            adapter.addList(bean == null ? null : bean.getList(),page);
        }
    }


    //创建订单信息
    private void createOrderInfo(String order_no,String money){
        new OrderPayModePopupWindow.Build(getContext())
                .setOnItemClickListener(payType -> {
                    Map<String,Object> map = new HashMap<>();
                    map.put(TOKEN,getAppToken());
                    map.put("order_no",order_no);
                    map.put("type",payType);
                    mPresenter.onOrderCreate(map,payType);
                })
                .setOrderPayMoney(money)
                .builder()
                .showPopupWindow();
    }


    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        this.order_id = infoBean.getOrder_id();
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_WX,infoBean);
        isWeChatpay = true;
    }

    @Override
    public void onAliPay(String order_id, String res) {
        this.order_id = order_id;
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_ALI,res);
        isAlipay = true;
    }

    private  boolean isAlipay;
    private  boolean isWeChatpay;
    private  boolean isQueryDetail;

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.getSelfOrderList(parameters));
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onPaySuccess() {
        if (!isEmpty(order_id)){
            mPresenter.checkOrderStatus(getAppToken(),order_id);
        }
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));
    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
        if (!isAlipay && !isWeChatpay && !isQueryDetail){
            PayListener.getInstance().removeListener(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PayListener.getInstance().removeListener(this);
    }
}
