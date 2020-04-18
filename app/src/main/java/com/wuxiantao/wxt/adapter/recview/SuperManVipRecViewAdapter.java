package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.utils.RegexUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperManVipRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-21 下午2:09
 * Description:${DESCRIPTION} 超人权益vip
 */
public class SuperManVipRecViewAdapter extends RcvBaseAdapter<SuperManBeneBean.ListBean> {


    public SuperManVipRecViewAdapter(Context context, List<SuperManBeneBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, SuperManBeneBean.ListBean bean, int position) {
        holder.setCircleImageResource(R.id.item_vip_sub_department_head_img, RegexUtils.imgUrlSeparate(bean.getGoods_image()));
        holder.setText(R.id.item_vip_sub_department_nick_name, bean.getGoods_title());
        //原价
        holder.setTextDeleteLine(R.id.item_vip_sub_department_original_price,mContext.getString(R.string.department_original_price,
                mContext.getString(R.string.original_price),Double.valueOf(bean.getPrice())));
        //到手价
        holder.setText(R.id.item_vip_sub_department_present_price,mContext.getString(R.string.department_original_price,
                mContext.getString(R.string.present_price),bean.getDaoshou()));
        //省
        holder.setText(R.id.item_vip_sub_department_present_sheng,mContext.getString(R.string.save_money_regx,bean.getRebate()));

        holder.setViewOnClickListener(R.id.item_super_man_vip_layout, v -> {
            if (listener != null){
                listener.onItemClick(bean.getId(),bean.getSpecs());
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_super_man_vip;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(int good_id,String spec);
    }
}
