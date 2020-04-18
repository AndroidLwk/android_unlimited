package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FastWithwrawRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 下午12:55
 * Description:${DESCRIPTION} 常规提现
 */
public class CommonWithwrawRecViewAdapter extends RcvBaseAdapter<String> {

    private List<Boolean> checkedList;
    private String money;

    public CommonWithwrawRecViewAdapter(Context context, List<String> list,List<Boolean> checkedList,String money) {
        super(context, list);
        this.checkedList = checkedList;
        this.money = money;
    }

    public boolean update(int position){
        int count = -1;
        for (int i = 0;i < checkedList.size();i++){
            if (checkedList.get(i) && i != position){
                count = i;
                checkedList.set(i,false);
            }
        }
        boolean isChecked = !checkedList.get(position);
        checkedList.set(position,isChecked);
        if (count != -1){
            this.notifyItemChanged(count);
        }
        this.notifyItemChanged(position);
        return isChecked;
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.item_common_withdraw_money,s);
        holder.setImageResource(R.id.item_common_withdraw_status_img,checkedList.get(position)
                ? R.mipmap.withdraw_money_selected : R.mipmap.withdraw_money_selecte);
        holder.setViewOnClickListener(R.id.item_common_withdraw_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
        if (Double.valueOf(money) > Double.valueOf(s)){
            holder.setViewEnabled(R.id.item_common_withdraw_layout,true);
            holder.setVisibility(R.id.item_common_withdraw_status_enbled, View.GONE);
            holder.setVisibility(R.id.item_common_withdraw_status_img, View.VISIBLE);
            holder.setTextColor(R.id.item_common_withdraw_money, Color.BLACK);
            holder.setTextColor(R.id.item_common_withdraw_tv, Color.BLACK);
        }else {
            holder.setViewEnabled(R.id.item_common_withdraw_layout,false);
            holder.setVisibility(R.id.item_common_withdraw_status_enbled, View.VISIBLE);
            holder.setVisibility(R.id.item_common_withdraw_status_img,View.GONE);
            holder.setTextColor(R.id.item_common_withdraw_money, Color.GRAY);
            holder.setTextColor(R.id.item_common_withdraw_tv, Color.GRAY);
        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_common_withdraw;
    }
}
