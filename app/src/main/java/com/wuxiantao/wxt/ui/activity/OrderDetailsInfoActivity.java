package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.TaoBaoOrderDetailBean;
import com.wuxiantao.wxt.bean.YouXuanOrderDetailBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.OrderDetailsContract;
import com.wuxiantao.wxt.mvp.presenter.OrderDetailPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.MathUtils;
import com.wuxiantao.wxt.utils.RegexUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.ORDER_ID;
import static com.wuxiantao.wxt.config.Constant.ORDER_TYPE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderDetailsInfoActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午12:57
 * Description:${DESCRIPTION} 订单详情
 */
@ContentView(R.layout.activity_order_details_info)
public class OrderDetailsInfoActivity extends MvpActivity<OrderDetailPresenter, OrderDetailsContract.IOrderDetailsView> implements OrderDetailsContract.IOrderDetailsView {
    @ViewInject(R.id.order_details_info_nodata_layout)
    LinearLayout order_details_info_nodata_layout;
    @ViewInject(R.id.order_details_info_nodata_tv)
    TextView order_details_info_nodata_tv;
    @ViewInject(R.id.order_details_info_layout)
    LinearLayout order_details_info_layout;
    @ViewInject(R.id.order_details_info_img)
    ImageView order_details_info_img;
    @ViewInject(R.id.order_details_title)
    TaoBaoTagTextView order_details_title;
    @ViewInject(R.id.order_details_money)
    TextView order_details_money;
    @ViewInject(R.id.order_details_deposit_type)
    TextView order_details_deposit_type;
    @ViewInject(R.id.order_details_deposit)
    TextView order_details_deposit;
    @ViewInject(R.id.order_details_platform)
    TextView order_details_platform;
    @ViewInject(R.id.order_details_name)
    TextView order_details_name;
    @ViewInject(R.id.order_details_time)
    TextView order_details_time;
    @ViewInject(R.id.order_details_order_no)
    TextView order_details_order_no;
    @ViewInject(R.id.order_details_copy)
    TextView order_details_copy;
    @ViewInject(R.id.order_details_info_pay)
    StateButton order_details_info_pay;

    private LoadingDialog loadingDialog;

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle bundle = getBundle();
        if (bundle != null){
            int orderType = bundle.getInt(ORDER_TYPE);
            int id = bundle.getInt(ORDER_ID);
            loadingDialog = new LoadingDialog.Build(this).build();
            switch (orderType){
                //淘宝订单详情
                case 0:
                    mPresenter.getTaoBaoOrderDetail(getAppToken(),id);
                    break;
                //自营
                case 1:
                    mPresenter.getYouXuanOrderDetail(getAppToken(),id);
                    break;
            }
            setOnClikListener(order_details_copy,order_details_info_pay);
        }
    }

    @Override
    protected void widgetClick(int id) {
        switch (id){
            case R.id.order_details_copy:
                TextViewUtils.copy(getTextViewText(order_details_order_no));
                break;
            case R.id.order_details_info_pay:

                break;
        }
    }

    @Override
    protected OrderDetailPresenter CreatePresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void getYouXuanOrderDetailSuccess(YouXuanOrderDetailBean bean) {
        order_details_info_nodata_layout.setVisibility(View.GONE);
        order_details_info_layout.setVisibility(View.VISIBLE);
        String title = bean.getGoods().getGoods_title();
        order_details_title.setContentAndTag(title, TaoBaoTagTextView.TYPE_YOUXUAN);
        String imgs = bean.getGoods().getGoods_image();
        GlideImgManager.loadRoundImg(this, RegexUtils.imgUrlSeparate(imgs),order_details_info_img,5.0f);
        order_details_money.setText(bean.getGoods().getPrice());
        String rebate = MathUtils.formatCurrency(String.valueOf(bean.getRate_money()),2);
        int vip_level = bean.getVip_level();
        int orderStatus = bean.getStatus();
        if (vip_level > 0){
            order_details_deposit_type.setText(getString(R.string.expected_member_day));
        }else {
            //订单状态:0 1验证中,2验证成功 ,3已失效
            switch (orderStatus){
                case 0:
                case 1:
                    order_details_deposit_type.setText(getString(R.string.expected_commission_yuan));
                    break;
                case 2:
                    order_details_deposit_type.setText(getString(R.string.seek_commission_yuan));
                    break;
                case 3:
                    order_details_deposit_type.setText(getString(R.string.lost_commission_yuan));
                    break;
            }
        }
        order_details_deposit.setText(rebate);
        order_details_name.setText(getString(R.string.help_center_optimal_platform));
        order_details_time.setText(bean.getCreate_at());
        order_details_order_no.setText(bean.getOrder_no());
        order_details_platform.setText(getString(R.string.youxuan_order_info));
        //0 未支付   1.已支付
        int isPay = bean.getIs_pay();
//        order_details_info_pay.setVisibility(bean.getIs_pay() == 0 ? View.VISIBLE : View.GONE);
    }



    @Override
    public void getTaoBaoOrderDetailSuccess(TaoBaoOrderDetailBean bean) {
        order_details_info_nodata_layout.setVisibility(View.GONE);
        order_details_info_layout.setVisibility(View.VISIBLE);
        String title = bean.getItem_title();
        String type = bean.getOrder_type();
        switch (type){
            case "天猫":
                order_details_title.setContentAndTag(title,TaoBaoTagTextView.TYPE_TIANMAO);
                break;
            case "淘宝":
                order_details_title.setContentAndTag(title,TaoBaoTagTextView.TYPE_TAOBAO);
                break;
            case "优选":
                order_details_title.setContentAndTag(title,TaoBaoTagTextView.TYPE_YOUXUAN);
                break;
        }
        order_details_platform.setText(type.contains(getString(R.string.taobao)) ?
                getString(R.string.tao_bao_order_info) : getString(R.string.tianmao_order_info));
        String imgs = bean.getPict_url();
        if (imgs.startsWith("//")){
            imgs = "http:" + imgs;
        }
        GlideImgManager.loadRoundImg(this,imgs,order_details_info_img,5.0f);
        order_details_money.setText(bean.getPrice());
        String rebate = MathUtils.formatCurrency(String.valueOf(bean.getRebate()),2);
        order_details_deposit.setText(rebate);
        order_details_name.setText(bean.getSeller_shop_title());
        order_details_time.setText(bean.getCreate_time());
        order_details_order_no.setText(bean.getTrade_id());
        //订单状态:0 12验证中,3 14验证成功 ,13已失效
        int orderStatus = bean.getTk_status();
        switch (orderStatus){
            case 0:
            case 12:
                order_details_deposit_type.setText(getString(R.string.expected_commission_yuan));
                break;
            case 3:
            case 14:
                order_details_deposit_type.setText(getString(R.string.seek_commission_yuan));
                break;
            case 13:
                order_details_deposit_type.setText(getString(R.string.lost_commission_yuan));
                break;
        }
//        order_details_info_pay.setVisibility(orderStatus == 0 || orderStatus == 12 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void getYouXuanOrderDetailFailure(String failure) {
        if (failure.equals(getString(R.string.no_data_order_info))){
            order_details_info_nodata_layout.setVisibility(View.VISIBLE);
            order_details_info_nodata_tv.setText(failure);
        }
    }


    @Override
    public void getTaoBaoOrderDetailFailure(String failure) {
        if (failure.equals(getString(R.string.no_data_order_info))){
            order_details_info_nodata_layout.setVisibility(View.VISIBLE);
            order_details_info_nodata_tv.setText(failure);
        }
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
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).setMiddleTitleText(R.string.order_details_title).build();
    }
}
