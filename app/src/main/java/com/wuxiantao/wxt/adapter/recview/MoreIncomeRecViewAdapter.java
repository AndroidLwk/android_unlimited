package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.MoreIncomeBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MoreIncomeRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-19 上午9:16
 * Description:${DESCRIPTION}
 */
public class MoreIncomeRecViewAdapter extends RcvBaseAdapter<MoreIncomeBean> {

    public MoreIncomeRecViewAdapter(Context context, List<MoreIncomeBean> list) {
        super(context, list);
    }


    @Override
    protected void convert(BaseViewHolder holder, MoreIncomeBean bean, int position) {
        holder.setText(R.id.item_more_income_title,bean.getTitle());
        holder.setText(R.id.item_more_income_msg,bean.getMsg());
        holder.setText(R.id.item_more_income_type,String.format("%s%s",bean.getText(),">"));
        holder.setImageResource(R.id.item_more_income_img,bean.getIcon());
        holder.setViewOnClickListener(R.id.item_more_income_type, v -> {
            if (listener != null){
                switch (position){
                    case 0:
                        listener.onInviteFriend();
                        break;
                    case 1:
                        listener.onDividend();
                        break;
                    case 2:
                        listener.onUpgradeMember();
                        break;
                    case 3:
                        listener.onShopping();
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_more_income;
    }



    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onInviteFriend();
        void onDividend();
        void onUpgradeMember();
        void onShopping();
    }
}
