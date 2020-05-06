package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ScrapingCardBean;

import java.util.List;

public class ScrapingCardFragmentTwoAdapter extends RcvBaseAdapter<ScrapingCardBean> {

    public ScrapingCardFragmentTwoAdapter(Context context, List<ScrapingCardBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ScrapingCardBean bean, int position) {
        holder.setText(R.id.tv_num_title, bean.getNumTitle());
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scrapingcard_b;
    }
}
