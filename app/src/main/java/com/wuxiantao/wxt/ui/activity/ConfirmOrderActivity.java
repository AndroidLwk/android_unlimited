package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.BalanceWithdrawRecViewAdapter;
import com.wuxiantao.wxt.bean.DefaultAddressBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.ConfirmOrderContract;
import com.wuxiantao.wxt.mvp.presenter.ConfirmOrderPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.RegexUtils;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ADDRESS_ADD_TYPE;
import static com.wuxiantao.wxt.config.Constant.ADDRESS_TYPE;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_IMG;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_PRICE;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_SELECT_NAME;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_SPACE;
import static com.wuxiantao.wxt.config.Constant.COMMODITY_TITLE;
import static com.wuxiantao.wxt.config.Constant.GOOD_ID;
import static com.wuxiantao.wxt.config.Constant.GOOD_TYPE;
import static com.wuxiantao.wxt.config.Constant.ORDER_REMARK;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_ADD_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_ORDER_REMARK;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_ORDER_REMARK;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_DEFAULT_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.UPDATE_HIGH_AREA;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ConfirmOrderActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午4:36
 * Description:${DESCRIPTION} 确认订单
 */
@ContentView(R.layout.activity_confirm_order)
public class ConfirmOrderActivity extends MvpActivity<ConfirmOrderPresenter, ConfirmOrderContract.IOrderView> implements ConfirmOrderContract.IOrderView {
    @ViewInject(R.id.commodity_info_rl)
    SmartRefreshLayout commodity_info_rl;
    @ViewInject(R.id.confirm_order_back)
    RelativeLayout confirm_order_back;
    @ViewInject(R.id.confirm_order_remark)
    TextView confirm_order_remark;
    @ViewInject(R.id.confirm_order_set_address)
    RelativeLayout confirm_order_set_address;
    @ViewInject(R.id.confirm_order_address_layout)
    LinearLayout confirm_order_address_layout;
    @ViewInject(R.id.confirm_order_person)
    TextView confirm_order_person;
    @ViewInject(R.id.confirm_order_number)
    TextView confirm_order_number;
    @ViewInject(R.id.confirm_order_address)
    TextView confirm_order_address;

    @ViewInject(R.id.confirm_order_img)
    ImageView confirm_order_img;
    @ViewInject(R.id.confirm_order_title)
    TextView confirm_order_title;
    @ViewInject(R.id.confirm_order_price)
    TextView confirm_order_price;
    @ViewInject(R.id.confirm_order_price_red)
    TextView confirm_order_price_red;
    @ViewInject(R.id.confirm_order_money)
    TextView confirm_order_money;

    @ViewInject(R.id.confirm_order_spce)
    TextView confirm_order_spce;
    @ViewInject(R.id.confirm_order_commit)
    Button confirm_order_commit;
    @ViewInject(R.id.confirm_order_pay_rv)
    RecyclerView confirm_order_pay_rv;

    private String order_id;
    private String addressId;
    private String selectName;
    private int payType = 1;
    private int good_id = 0;
    private LoadingDialog loadingDialog;
    private String remark;
    private int good_type;

    @Override
    protected ConfirmOrderPresenter CreatePresenter() {
        return new ConfirmOrderPresenter();
    }

    @Override
    public void initView() {
        //注册EventBus
        EventBus.getDefault().register(this);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        commodity_info_rl.setEnableRefresh(false);
        commodity_info_rl.setEnableLoadMore(false);

        //注册支付监听
        PayListener.getInstance().addListener(this);
        setOnClikListener(confirm_order_back,confirm_order_set_address,confirm_order_address_layout,
                confirm_order_commit,confirm_order_remark);
        initPayTypeLayout();

        Bundle bundle = getBundle();
        if (bundle != null){
            String price = bundle.getString(COMMODITY_PRICE);
            String title = bundle.getString(COMMODITY_TITLE);
            String img = bundle.getString(COMMODITY_IMG);
            String spce = bundle.getString(COMMODITY_SPACE);
            selectName = bundle.getString(COMMODITY_SELECT_NAME);
            good_type = bundle.getInt(GOOD_TYPE);
            good_id = bundle.getInt(GOOD_ID);
            confirm_order_money.setText(isEmpty(price) ? getString(R.string.zero) : price);
            confirm_order_price_red.setText(isEmpty(price) ? getString(R.string.zero) : price);
            confirm_order_price.setText(isEmpty(price) ? getString(R.string.zero) : price);
            confirm_order_title.setText(title);
            GlideImgManager.loadRoundImg(this, RegexUtils.imgUrlSeparate(img),confirm_order_img);
            confirm_order_spce.setText(spce);
        }
        mPresenter.getDefaultAddress(getAppToken());
    }

    private void initPayTypeLayout(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0,30);
        List<String> type = Arrays.asList(getResources().getStringArray(R.array.withdraw_title));
        List<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);
        BalanceWithdrawRecViewAdapter adapter = new BalanceWithdrawRecViewAdapter(this,type,booleans);
        confirm_order_pay_rv.setLayoutManager(manager);
        confirm_order_pay_rv.addItemDecoration(decoration);
        confirm_order_pay_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> {
            payType = position == 0 ? 1 : 2;
            adapter.updateChecdList(position);
        });
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.confirm_order_back:
                finish();
                break;
                //添加收货地址
            case R.id.confirm_order_set_address:
                //修改收货地址
            case R.id.confirm_order_address_layout:
                $startActivity(AddressManagementActivity.class);
                break;
                //确认订单
            case R.id.confirm_order_commit:
                if (isEmpty(addressId) || confirm_order_address_layout.getVisibility() == View.GONE){
                    showOnlyConfirmDialog(getString(R.string.plase_setting_address), (dialog, which) -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt(ADDRESS_TYPE,ADDRESS_ADD_TYPE);
                        $startActivityForResult(ReceiptAddressActivity.class,bundle,REQUEST_CODE_ADD_ADDRESS);
                    });
                    return;
                }
                if (isEmpty(selectName)){
                    return;
                }
                Map<String,Object> map = new HashMap<>();
                map.put(TOKEN,getAppToken());
                map.put("type",payType);
                map.put("goods_id",good_id);
                map.put("address_id",addressId);
                if (!isEmpty(remark)){
                    map.put("desc",remark);
                }
                map.put("selectName",selectName);
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.create_order_loading).build();
                mPresenter.onOrderCreate(map,payType);
                break;
            case R.id.confirm_order_remark:
                Bundle bundle = new Bundle();
                bundle.putString(ORDER_REMARK,remark);
                $startActivityForResult(OrderRemarkActivity.class,bundle,REQUEST_CODE_ORDER_REMARK);
                break;
        }
    }

    //刷新数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == UPDATE_DEFAULT_ADDRESS){
            mPresenter.getDefaultAddress(getAppToken());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //备注信息
        if (requestCode == REQUEST_CODE_ORDER_REMARK && resultCode == RESULT_CODE_ORDER_REMARK){
            if (data != null){
                remark = data.getStringExtra(ORDER_REMARK);
            }
        }
    }

    //获取默认收货地址
    @Override
    public void getDefaultAddressSuccess(DefaultAddressBean infoBean) {
        setDefaultAddress(infoBean);
    }

    private void setDefaultAddress(DefaultAddressBean bean){
        List<DefaultAddressBean.ListBean> list = bean.getList();
        if (list.isEmpty()){
            confirm_order_address_layout.setVisibility(View.GONE);
            confirm_order_set_address.setVisibility(View.VISIBLE);
        }else {
            DefaultAddressBean.ListBean listBean = list.get(0);
            addressId = String.valueOf(listBean.getId());
            String person = listBean.getUsername();
            String number = listBean.getPhone();
            String province = listBean.getProvince();
            String city = listBean.getCity();
            String area = listBean.getArea();
            String address = listBean.getAddress();
            if (!isEmpty(person,number,address,addressId)){
                confirm_order_person.setText(getString(R.string.person_, person));
                confirm_order_number.setText(number);
                confirm_order_address.setText(getString(R.string.s_address,province,city,area,address));
            }
            confirm_order_address_layout.setVisibility(View.VISIBLE);
            confirm_order_set_address.setVisibility(View.GONE);
        }
    }

    @Override
    public void getDeafaultAddressFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //订单生成成功 微信支付
    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        this.order_id = infoBean.getOrder_id();
        PayManager.getInstance(this).pay(PAY_TYPE_WX,infoBean);
    }

    //订单生成成功 支付宝支付
    @Override
    public void onAliPay(String order_id,String res) {
        this.order_id = order_id;
        PayManager.getInstance(this).pay(PAY_TYPE_ALI,res);
    }


    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //支付成功
    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> finish());
        //购买0元购商品 刷新高用商品
        if (good_type == 3){
            EventBus.getDefault().post(new MessageEvent(UPDATE_HIGH_AREA));
        }
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
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注册EventBus
        EventBus.getDefault().unregister(this);
        PayListener.getInstance().removeListener(this);
    }
}
