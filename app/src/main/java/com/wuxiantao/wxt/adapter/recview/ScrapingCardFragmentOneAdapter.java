package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ScrapingCardBean;

import java.util.List;

public class ScrapingCardFragmentOneAdapter extends RcvBaseAdapter<ScrapingCardBean> {

    public ScrapingCardFragmentOneAdapter(Context context, List<ScrapingCardBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ScrapingCardBean bean, int position) {
        holder.setImageResource(R.id.iv_scapingcard_icon, bean.getImg_res());
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scapingcard_a;
    }
}
