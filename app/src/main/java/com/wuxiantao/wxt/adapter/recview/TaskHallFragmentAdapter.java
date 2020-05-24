package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.TaskHallBean;

import java.util.List;

public class TaskHallFragmentAdapter extends RcvBaseAdapter<TaskHallBean> {

    public TaskHallFragmentAdapter(Context context, List<TaskHallBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, TaskHallBean bean, int position) {
        if (bean.isSign()) {//签到列表
            if (position == 0) {
                holder.setText(R.id.sbt_finish, bean.getIsFinish() == 0 ? "签到" : "已签到");
            } else {
                holder.setText(R.id.sbt_finish, bean.getIsFinish() == 0 ? "去完成" : "已完成");
            }
        } else {//第二个列表
            holder.setText(R.id.sbt_finish, bean.getIsFinish() == 0 ? "去完成" : "已完成");
        }
        holder.setViewEnabled(R.id.sbt_finish, bean.getIsFinish() == 0 ? true : false);
        holder.setDrawableLeftRes(R.id.tv_taskhall_content, bean.getResIcon());
        holder.setText(R.id.tv_taskhall_content, bean.getTaskHallContent());
       // holder.setStateButtonBackGroundColor(R.id.sbt_finish,"#FD6F57");
        holder.setViewOnClickListener(R.id.sbt_finish, v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_taskhall_fragment;
    }

    private OnItemClickListener listener;

    public void setOnItemBtnClickListener(OnItemClickListener l) {
        this.listener = l;
    }

    public interface OnItemClickListener {
        void onItemClick(int potion);
    }
}
