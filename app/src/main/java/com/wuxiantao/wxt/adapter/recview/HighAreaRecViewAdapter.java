package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.ui.custom.textview.BuyZeroTagTextView;
import com.wuxiantao.wxt.utils.RegexUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.ZERO_BUY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:42
 * Description:${DESCRIPTION} 高用专区
 */
public class HighAreaRecViewAdapter extends RcvBaseAdapter<HighAreaListBean.GoodslistBean> {

    //为0元购商品，1为高拥商品
    private int status;

    public HighAreaRecViewAdapter(Context context, List<HighAreaListBean.GoodslistBean> list, int status) {
        super(context, list);
        this.status = status == 0 ? ZERO_BUY : 1;
    }


    @Override
    protected void convert(BaseViewHolder holder, HighAreaListBean.GoodslistBean bean, int position) {
        holder.setRoundImageResource(R.id.item_high_area_list_img,
                RegexUtils.imgUrlSeparate(bean.getGoods_image()), R.drawable.no_banner,10.0f);
        String title = bean.getGoods_title();
        String sale_num = bean.getSale_num();
        if (status == ZERO_BUY){
            //0元购
            holder.setBuyZeroTagText(R.id.item_high_area_list_title,title,BuyZeroTagTextView.TYPE_BUY_ZERO);
            holder.setText(R.id.item_high_area_list_return,
                    mContext.getString(R.string.first_order_regex, mContext.getString(R.string.first_order),
                            Double.valueOf(bean.getPrice())));
            holder.setVisibility(R.id.item_high_area_list_original_price, View.VISIBLE);
            holder.setTextDeleteLine(R.id.item_high_area_list_price,mContext.getString(R.string.rmb,bean.getPrice()));
            holder.setTextColor(R.id.item_high_area_list_price, Color.GRAY);
            holder.setText(R.id.item_high_area_list_peoples,mContext.getString(R.string.already_regx,
                    mContext.getString(R.string.already),sale_num,mContext.getString(R.string.free)));
            holder.setViewBackGroundResources(R.id.item_high_area_list_buy,R.mipmap.zero_buy);
        }else {
            //高佣
            holder.setBuyZeroTagText(R.id.item_high_area_list_title,title,BuyZeroTagTextView.TYPE_HIGH_COMMISSION);
            holder.setText(R.id.item_high_area_list_return,
                    mContext.getString(R.string.first_order_regex, mContext.getString(R.string.buy_order),bean.getRebate()));
            holder.setVisibility(R.id.item_high_area_list_original_price, View.GONE);
            holder.setText(R.id.item_high_area_list_price,mContext.getString(R.string.rmb,bean.getPrice()));
            holder.setTextColor(R.id.item_high_area_list_price, "#FC4D43");
            holder.setText(R.id.item_high_area_list_peoples,mContext.getString(R.string.already_regx,
                    mContext.getString(R.string.already),sale_num,mContext.getString(R.string.buy_hight_area)));
            holder.setViewBackGroundResources(R.id.item_high_area_list_buy,R.mipmap.go_buy);
        }
        holder.setViewOnClickListener(R.id.item_high_area_list_buy, v -> {
            if (listener != null){
                listener.onItemClick(status,bean.getId(),bean.getSpecs());
            }
        });
        holder.setViewOnClickListener(R.id.item_high_area_list_layout, v -> {
            if (listener != null){
                listener.onItemClick(status,bean.getId(),bean.getSpecs());
            }
        });
    }



    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_high_area_list;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int status,int goodsId,String spce);
    }

}
