package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyCardInfo;

import java.util.List;

public class ScrapingCardFragmentThreeAdapter extends RcvBaseAdapter<MyCardInfo.ListBean> {
    public ScrapingCardFragmentThreeAdapter(Context context, List<MyCardInfo.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyCardInfo.ListBean bean, int position) {

        holder.setText(R.id.tv_name_one, bean.getName());
        holder.setText(R.id.tv_amount, bean.getMoney() + "");
        holder.setText(R.id.tv_today_money, "");
        holder.setText(R.id.tv_cha, bean.getCard_cha()+"");
        holder.setText(R.id.tv_num, bean.getStatus_total() + "");
        int progress_one = 0;
        if (bean.getCard_all() + bean.getCard_cha() > 0) {
            progress_one = bean.getCard_all() / (bean.getCard_all() + bean.getCard_cha());
        }
        // holder.setProgress(R.id.progress_scrapingcard_b, progress_one);
        // holder.setOnCheckedListener(R.id.sbt_startHall_scrapCard, (view, isChecked) -> listener.Onclick(bean));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scrapingcardfragment_a;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void Onclick(MyCardInfo.ListBean bean);
    }
}
