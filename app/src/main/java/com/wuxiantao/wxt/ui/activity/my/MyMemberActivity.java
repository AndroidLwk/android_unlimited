package com.wuxiantao.wxt.ui.activity.my;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MymemberAdapter;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.MyMemberContract;
import com.wuxiantao.wxt.mvp.presenter.MyMemberPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.popupwindow.OrderPayModePopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.DensityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

@ContentView(R.layout.activity_mymember)
public class MyMemberActivity extends MvpActivity<MyMemberPresenter, MyMemberContract> implements MyMemberContract {
    @ViewInject(R.id.base_title_left_img)
    ImageView base_title_left_img;
    @ViewInject(R.id.base_title_left_tv)
    TextView base_title_left_tv;
    @ViewInject(R.id.base_title_left_layout)
    RelativeLayout base_title_left_layout;
    @ViewInject(R.id.base_title_center_tv)
    TextView base_title_center_tv;
    @ViewInject(R.id.base_title_center_img)
    ImageView base_title_center_img;
    @ViewInject(R.id.base_title_right_img)
    ImageView base_title_right_img;
    @ViewInject(R.id.base_title_right_tv)
    TextView base_title_right_tv;
    @ViewInject(R.id.base_title_right_layout)
    RelativeLayout base_title_right_layout;
    @ViewInject(R.id.base_title_layout)
    RelativeLayout base_title_layout;
    @ViewInject(R.id.base_title)
    LinearLayout base_title;
    @ViewInject(R.id.iv_mine_header)
    ImageView iv_mine_header;
    @ViewInject(R.id.iv_headerName)
    TextView iv_headerName;
    @ViewInject(R.id.bt_openMember)
    Button bt_openMember;
    @ViewInject(R.id.rt_personInfo)
    RelativeLayout rt_personInfo;
    @ViewInject(R.id.tv_member_title)
    TextView tv_member_title;
    @ViewInject(R.id.tv_memberType)
    TextView tv_memberType;
    @ViewInject(R.id.tv_vipStatus)
    TextView tv_vipStatus;
    @ViewInject(R.id.tv_member_explain)
    TextView tv_member_explain;
    @ViewInject(R.id.rv_equity)
    NestRecyclerView rv_equity;
    List<VipStatusInfoBean.BannerBean> mData = new ArrayList<>();
    private MymemberAdapter mAdapter;

    @Override
    protected void initView() {
        mPresenter.getVipStatusInfo(getAppToken());
        setOnClikListener(bt_openMember);
        PayListener.getInstance().addListener(this);
        int spanCount = 4;
        GridLayoutManager manager = new GridLayoutManager(this, spanCount);
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(spanCount, DensityUtils.dip2px(20), true);
        rv_equity.setLayoutManager(manager);
        rv_equity.addItemDecoration(decoration);
        mAdapter = new MymemberAdapter(this, mData);
        rv_equity.setAdapter(mAdapter);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.drawable.back_mymember)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.mymember_text6)).build();
    }

    @Override
    protected void widgetClick(int id) {
        if (id == R.id.bt_openMember) {//开通会员
            if (info == null) {
                return;
            }
            showPayModeWindow();//开通会员/续费会员
        }
    }

    //选择支付方式
    private void showPayModeWindow() {
        new OrderPayModePopupWindow.Build(this)
                .setOnItemClickListener(payType -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put(TOKEN, getAppToken());
                    map.put("goods_id", 9);
                    map.put("type", payType);
                    mPresenter.onOrderCreate(map, payType);
                })
                .setOrderPayMoney(info.getVip_price() + "")
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .builder()
                .showPopupWindow();
    }

    @Override
    protected MyMemberPresenter CreatePresenter() {
        return new MyMemberPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private VipStatusInfoBean info;

    @Override
    public void showMymemberInfo(VipStatusInfoBean info) {
        this.info = info;
        mData.clear();
        mData.addAll(info.getBanner());
        mAdapter.notifyDataSetChanged();
        String head_img = info.getHeadimg();
        if (!isEmpty(head_img)) {
            GlideImgManager.loadCircleImg(this, head_img, iv_mine_header);
        } else {
            GlideImgManager.loadCircleImg(this, R.drawable.ic_person_outline_black_24dp, iv_mine_header);
        }
        iv_headerName.setText(info.getNickname());
        //0不是会员 1年会会员 2月会会员 -1会员已过期
        tv_memberType.setVisibility(info.getVip() != 0 ? View.VISIBLE : View.INVISIBLE);
        tv_vipStatus.setVisibility(info.getVip() != 0 ? View.VISIBLE : View.INVISIBLE);
        int vipStatus = info.getVip();
        String time = getString(R.string.member_center_end_time, DateUtils.timestampToTime(info.getVip_end_time()));
        switch (vipStatus) {
            case -1:
                time = getString(R.string.ordinary_member) + getString(R.string.member_center_end_time, DateUtils.timestampToTime(info.getVip_end_time()));
                break;
            case 0:
                time = "";
                break;
            case 1:
                time = getString(R.string.year_member) + getString(R.string.member_center_end_time, DateUtils.timestampToTime(info.getVip_end_time()));
                break;
            case 2:
                time = getString(R.string.month_member) + getString(R.string.member_center_end_time, DateUtils.timestampToTime(info.getVip_end_time()));
                break;
        }
        tv_memberType.setText(time);
        bt_openMember.setText(info.getVip() != 0 ? "续费会员" : "开通会员");
        tv_member_explain.setText(info.getQuanyi());
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    //订单号
    private String order_sn;

    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {//微信支付
        order_sn = infoBean.getOrder_id();
        PayManager.getInstance(this).pay(PAY_TYPE_WX, infoBean);
    }

    @Override
    public void onAliPay(String order_id, String res) { //支付宝支付
        order_sn = order_id;
        PayManager.getInstance(this).pay(PAY_TYPE_ALI, res);
    }

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //支付成功
    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.getVipStatusInfo(getAppToken()));
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //请求服务器查询订单
    @Override
    public void onPaySuccess() {
        if (!isEmpty(order_sn)) {
            mPresenter.checkOrderStatus(getAppToken(), order_sn);
        }
    }

    @Override
    public void onDestroy() {
        PayListener.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));

    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));

    }
}
