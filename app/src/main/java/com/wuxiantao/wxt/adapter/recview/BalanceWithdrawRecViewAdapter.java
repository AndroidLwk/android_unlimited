package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BalanceWithdrawRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午12:54
 * Description:${DESCRIPTION}
 */
public class BalanceWithdrawRecViewAdapter extends RcvBaseAdapter<String> {

    private List<Boolean> checkedList;
    private List<Integer> icons = new ArrayList<>();

    public BalanceWithdrawRecViewAdapter(Context context, List<String> list,List<Boolean> checkedList) {
        super(context, list);
        this.checkedList = checkedList;
        icons.add(R.mipmap.withdraw_alipay_icon);
        icons.add(R.mipmap.withdraw_wechat_icon);
    }

    public void updateChecdList(int position){
        for (int i = 0;i < checkedList.size();i++){
            checkedList.set(i,false);
        }
        checkedList.set(position,true);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setImageResource(R.id.item_balance_withdraw_icon,icons.get(position));
        holder.setText(R.id.item_balance_withdraw_type,s);
        holder.setVisibility(R.id.balance_withdraw_checked,checkedList.get(position) ? View.VISIBLE : View.GONE);
        holder.setViewOnClickListener(R.id.item_balance_withdraw_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_balance_withdraw;
    }

}
