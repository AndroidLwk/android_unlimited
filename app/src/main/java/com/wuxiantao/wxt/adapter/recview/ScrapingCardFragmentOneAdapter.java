package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyCardInfo;

import java.util.List;

public class ScrapingCardFragmentOneAdapter extends RcvBaseAdapter<MyCardInfo.JackpotImgsBean> {

    public ScrapingCardFragmentOneAdapter(Context context, List<MyCardInfo.JackpotImgsBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyCardInfo.JackpotImgsBean bean, int position) {
        holder.setRoundImageResource(R.id.iv_scapingcard_icon, bean.getImg());
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scapingcard_a;
    }
}
