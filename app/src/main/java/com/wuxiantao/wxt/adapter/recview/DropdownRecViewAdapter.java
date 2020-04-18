package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ComplaintSortBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DropdownRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-17 下午3:39
 * Description:${DESCRIPTION}
 */
public class DropdownRecViewAdapter extends RcvBaseAdapter<ComplaintSortBean> {

    public DropdownRecViewAdapter(Context context, List<ComplaintSortBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ComplaintSortBean bean, int position) {
        holder.setText(R.id.item_dropdown_list_tv,bean.getName());
        holder.setViewOnClickListener(R.id.item_dropdown_list_layout, v -> {
            if (listener != null){
                listener.onSelectedData(bean.getName(),bean.getId());
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_dropdown_list;
    }

    private OnDropdowSelectedListener listener;

    public void setOnDropdowSelectedListener(OnDropdowSelectedListener listener){
        this.listener = listener;
    }
    public interface OnDropdowSelectedListener{
        void onSelectedData(String data, int id);
    }
}
