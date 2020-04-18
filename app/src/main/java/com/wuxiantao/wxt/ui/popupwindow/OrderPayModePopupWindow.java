package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.OrderPayModeRecViewAdapter;
import com.wuxiantao.wxt.adapter.bean.OrderPayModeBean;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 订单支付类型选择窗口
 */
public class OrderPayModePopupWindow extends BasePopupWindow {


    public OrderPayModePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnItemClickListener listener;
        private int index = 1;

        public Build(Context context) {
            super(context, R.layout.popupwindow_order_pay_mode);
            setOnButtonListener(R.id.popup_order_pay_mode_close, R.id.popup_order_pay_mode_confirm);
            initLayout();
        }

        private void initLayout(){
            super.setRcvConfig(R.id.popup_order_pay_mode_rv,initManager(), initItemDecoration(), initAdapter());
        }

        private LinearLayoutManager initManager(){
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(1);
            return manager;
        }

        private RecyclerView.ItemDecoration initItemDecoration(){
            return new SpaceItemDecoration(0,0);
        }

        public Build setOrderPayMoney(String money){
            if (!isEmpty(money)){
                setText(getString(R.string.self_operated_pay,Double.valueOf(money)),R.id.popup_order_pay_mode_confirm);
            }
            return this;
        }

        private OrderPayModeRecViewAdapter initAdapter(){
            List<OrderPayModeBean> list = initList();
            OrderPayModeRecViewAdapter adapter = new OrderPayModeRecViewAdapter(mContext,list);
            adapter.setOnBaseViewClickListener(position -> {
                index = position == 0 ? 1 : 2;
                for (int i = 0;i < list.size();i++){
                    list.get(i).setPaySelected(false);
                }
                list.get(position).setPaySelected(true);
                adapter.updataList(list);
            });
            return adapter;
        }


        private List<OrderPayModeBean> initList(){
            List<OrderPayModeBean> list = new ArrayList<>();
            String[] names = mContext.getResources().getStringArray(R.array.withdraw_title);
            int[] icons = {R.mipmap.withdraw_alipay_icon,R.mipmap.withdraw_wechat_icon};
            for (int i = 0;i < names.length;i++){
                OrderPayModeBean bean = new OrderPayModeBean();
                bean.setPayIcon(icons[i]);
                bean.setPayName(names[i]);
                bean.setPaySelected(i == 0);
                list.add(bean);
            }
            return list;
        }


        public Build setOnItemClickListener(OnItemClickListener l){
            this.listener = l;
            return this;
        }

        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new OrderPayModePopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_order_pay_mode_close:

                    break;
                case R.id.popup_order_pay_mode_confirm:
                    if (listener != null){
                        //1为支付宝，2为微信
                        listener.onOrderPay(index);
                    }
                    break;
            }
        }
    }


    public interface OnItemClickListener{
        void onOrderPay(int payType);
    }
}
