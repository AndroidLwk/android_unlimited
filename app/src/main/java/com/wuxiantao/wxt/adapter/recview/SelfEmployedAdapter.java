package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.View;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.utils.RegexUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SelfEmployedAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-13 上午9:55
 * Description:${DESCRIPTION}
 */
public class SelfEmployedAdapter extends RcvBaseAdapter<SelfEmployedBean.ListBean> {

    public SelfEmployedAdapter(Context context, List<SelfEmployedBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, SelfEmployedBean.ListBean bean, int position) {
        String imgs = bean.getGoods_image();
        holder.setRoundImageResource(R.id.item_self_employed_icon,RegexUtils.imgUrlSeparate(imgs),R.drawable.no_banner,5.0f);
        holder.setTaoBaoTagText(R.id.item_self_employed_title,bean.getGoods_title(),mContext.getString(R.string.self_employed));
        holder.setText(R.id.item_self_employed_price,bean.getPrice());
        String rebate_text = mContext.getString(R.string.self_buy_after,bean.getRebate());
        TextViewUtils.setTextViewKeyWordHighlight(holder.findView(R.id.item_self_employed_gold), rebate_text,
                Color.parseColor("#F35550"), new Pair<>(5, rebate_text.length() - 2));
        holder.setViewOnClickListener(R.id.item_self_employed_layout, v -> {
            if (listener != null){
                listener.OnItemClick(bean.getId());
            }
        });
        holder.setViewOnClickListener(R.id.item_self_employed_grab, v -> {
            if (listener != null){
                listener.OnItemClick(bean.getId());
            }
        });

        boolean isReview = (boolean) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(IS_REVIEW,false);
        holder.setVisibility(R.id.item_self_employed_gold,isReview ? View.GONE : View.VISIBLE);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_self_employed;
    }
}
