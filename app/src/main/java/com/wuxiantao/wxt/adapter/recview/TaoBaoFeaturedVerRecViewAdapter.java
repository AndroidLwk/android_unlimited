package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedMenuRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 下午3:08
 * Description:${DESCRIPTION} 淘宝精选-垂直列表
 */
public class TaoBaoFeaturedVerRecViewAdapter extends RcvBaseAdapter<TaoBaoHomeBean.ResultListBean> {


    public TaoBaoFeaturedVerRecViewAdapter(Context context, List<TaoBaoHomeBean.ResultListBean> list){
        super(context,list);
    }

    @Override
    public void convert(BaseViewHolder holder, TaoBaoHomeBean.ResultListBean bean, int position) {
        holder.setRoundImageResource(R.id.item_taobao_featured_img,bean.getPict_url(),R.drawable.no_banner,5.0f);
        int type = bean.getUser_type();
        String taobao = RESOURCES.getString(R.string.taobao);
        String tianmao = RESOURCES.getString(R.string.tianmao);
        holder.setTaoBaoTagText(R.id.item_taobao_featured_title,bean.getTitle(),type == 1 ? tianmao : taobao);
        holder.setText(R.id.item_taobao_featured_buy_fanli,mContext.getString(R.string.buy_get_regx,
                mContext.getString(R.string.buy_get),bean.getCom_rebate()));
        holder.setText(R.id.item_taobao_featured_share_fanli,mContext.getString(R.string.buy_get_regx,
                mContext.getString(R.string.share_get),bean.getVip_rebate()));
        holder.setText(R.id.item_taobao_featured_price,bean.getZk_final_price());
        String reserve_price = bean.getReserve_price();
        holder.setTextDeleteLine(R.id.item_taobao_featured_original_price,
                mContext.getString(R.string.commodity_price,
                        type == 1 ? mContext.getString(R.string.tianmao_price) : mContext.getString(R.string.taobao_price_),
                        Double.valueOf(reserve_price)));
        holder.setText(R.id.item_taobao_featured_volume,mContext.getString(R.string.month_volume,bean.getVolume()));
        holder.setViewOnClickListener(R.id.item_taobao_featured_layout, v -> {
            if (listener != null){
                listener.onItemClick(bean.getItem_id());
            }
        });
        boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
        holder.setVisibility(R.id.item_taobao_featured_buy_fanli,isReview ? View.GONE : View.VISIBLE);
        holder.setVisibility(R.id.item_taobao_featured_share_fanli,isReview ? View.GONE : View.VISIBLE);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_taobao_featured_ver;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(long id);
    }

}
