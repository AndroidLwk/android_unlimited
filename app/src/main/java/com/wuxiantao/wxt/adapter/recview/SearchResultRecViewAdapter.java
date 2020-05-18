package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.SearchResultBean;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchResultRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午5:58
 * Description:${DESCRIPTION}
 */
public class SearchResultRecViewAdapter extends RcvBaseAdapter<SearchResultBean.ResultListBean> {


    public SearchResultRecViewAdapter(Context context, List<SearchResultBean.ResultListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, SearchResultBean.ResultListBean bean, int position) {
        holder.setRoundImageResource(R.id.item_search_result_icon,bean.getPict_url(),R.drawable.no_banner,5.0f);
        int user_type = bean.getUser_type();
        String taobao = mContext.getResources().getString(R.string.taobao);
        String tianmao = mContext.getResources().getString(R.string.tianmao);
        holder.setTaoBaoTagText(R.id.item_search_result_title,bean.getTitle(),user_type == 1 ? tianmao : taobao);
        holder.setText(R.id.item_search_result_profit_buy,bean.getCoupon_info());
        holder.setText(R.id.item_search_result_profit_share,mContext.getString(R.string.buy_get_regx,
                mContext.getString(R.string.share_get),bean.getVip_rebate()));
        holder.setText(R.id.item_search_result_price,bean.getZk_final_price());
        String reserve_price = bean.getReserve_price();
        holder.setTextDeleteLine(R.id.item_search_result_original_price,
                mContext.getString(R.string.commodity_price,
                        user_type == 1 ? mContext.getString(R.string.tianmao_price) : mContext.getString(R.string.taobao_price_),
                        Double.valueOf(reserve_price)));
        holder.setText(R.id.item_search_result_original_volume,mContext.getString(R.string.month_volume,bean.getVolume()));
        holder.setViewOnClickListener(R.id.item_search_result_layout, v -> {
            if (listener != null){
                listener.onItemIDClick(bean.getItem_id());
            }
        });

        boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
        holder.setVisibility(R.id.item_search_result_profit_buy,isReview ? View.GONE : View.VISIBLE);
//        holder.setVisibility(R.id.item_search_result_profit_share,isReview ? View.GONE : View.VISIBLE);

    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_search_result;
    }

    private OnSearchResultItemClickListener listener;
    public void setOnSearchResultItemClickListener(OnSearchResultItemClickListener l){
        this.listener = l;
    }
    public interface OnSearchResultItemClickListener{
        void onItemIDClick(long id);
    }
}
