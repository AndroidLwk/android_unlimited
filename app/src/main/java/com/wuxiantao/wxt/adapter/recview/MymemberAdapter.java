package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.MyMemberBean;

import java.util.List;

public class MymemberAdapter extends RcvBaseAdapter<MyMemberBean> {

    public MymemberAdapter(Context context, List<MyMemberBean> list) {
        super(context, list);
    }


    @Override
    protected void convert(BaseViewHolder holder, MyMemberBean bean, int position) {
        holder.setText(R.id.tv_mymember_a, bean.getBigTitle());
        holder.setText(R.id.tv_mymember_b, bean.getSmallTitle());
        holder.setDrawableTopRes(R.id.tv_mymember_a, bean.getImg_res());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_mymember;
    }
}
