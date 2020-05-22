package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyCardInfo;

import java.math.BigDecimal;
import java.util.List;

public class IncomeHallAdapter extends RcvBaseAdapter<MyCardInfo.ListBean> {
    public IncomeHallAdapter(Context context, List<MyCardInfo.ListBean> list) {
        super(context, list);
    }
    @Override
    protected void convert(BaseViewHolder holder, MyCardInfo.ListBean bean, int position) {
        holder.setText(R.id.tv_name_one, bean.getName());
        BigDecimal bd = new BigDecimal(bean.getFen_today());
        double fen_today = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.setText(R.id.tv_amount, fen_today + "");

        BigDecimal bd2 = new BigDecimal(bean.getMy_today());
        double my_today = bd2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        holder.setText(R.id.tv_mytoday, my_today + "");
        double pr = Double.parseDouble(bean.getRate()) * 100;
        holder.setText(R.id.tv_cha, "距离享受平台" + pr + "%收益分红，只差" + bean.getCard_cha() + "张!");
        holder.setText(R.id.tv_num, bean.getStatus_total() + "");
        int progress_one = 0;
        if (bean.getCard_all() + bean.getCard_cha() > 0) {
            progress_one = bean.getCard_all() / (bean.getCard_all() + bean.getCard_cha());
        }
        holder.setCircleProgress(R.id.circleIndicator, progress_one);
        holder.setViewEnabled(R.id.circleIndicator, progress_one == 100 ? true : false);
        holder.setOnCheckedListener(R.id.circleIndicator, (view, isChecked) -> listener.Onclick(bean));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_scrapingcardfragment_a;
    }

    public void setOnItemClickListener(ScrapingCardFragmentThreeAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    private ScrapingCardFragmentThreeAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void Onclick(MyCardInfo.ListBean bean);
    }
}
