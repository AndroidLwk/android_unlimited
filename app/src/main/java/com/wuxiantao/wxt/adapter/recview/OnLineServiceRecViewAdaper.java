package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.bean.OnLineBean;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineServiceRecViewAdaper
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 下午10:40
 * Description:${DESCRIPTION}
 */
public class OnLineServiceRecViewAdaper extends RcvBaseAdapter<OnLineBean> {

    public OnLineServiceRecViewAdaper(Context context, List<OnLineBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, OnLineBean bean, int position) {
        holder.setText(R.id.item_service_on_line_aftername,bean.getAfterName());
        holder.setText(R.id.item_service_on_line_sn,String.valueOf(bean.getSn()));
        holder.setText(R.id.item_service_on_line_befotype,bean.getBefoType());
        holder.setText(R.id.item_service_on_line_no,bean.getNumber());
        holder.setViewOnClickListener(R.id.item_service_on_line_copy, v -> {
            if (listener != null){
                listener.onItemClick(bean.getNumber());
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_service_on_line;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(String no);
    }
}
