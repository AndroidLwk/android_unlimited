package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;

import java.util.List;

public class MymemberAdapter extends RcvBaseAdapter<VipStatusInfoBean.BannerBean> {

    public MymemberAdapter(Context context, List<VipStatusInfoBean.BannerBean> list) {
        super(context, list);
    }


    @Override
    protected void convert(BaseViewHolder holder, VipStatusInfoBean.BannerBean bean, int position) {
        holder.setCircleImageResource(R.id.iv_mymember_a, bean.getImg());
        holder.setText(R.id.tv_mymember_b, bean.getName());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_mymember;
    }
}
