package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.utils.DateUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:50
 * Description:${DESCRIPTION}
 */
public class WinningListRecViewAdapter extends RcvBaseAdapter<WinningListBean.ListBean> {

    public WinningListRecViewAdapter(Context context, List<WinningListBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, WinningListBean.ListBean bean, int position) {
        holder.setCircleImageResource(R.id.item_winning_list_icon,bean.getHeadimg());
        holder.setText(R.id.item_winning_list_name,bean.getNickname());
        holder.setText(R.id.item_winning_list_date, DateUtils.timestampToTime(bean.getTime()));
        holder.setText(R.id.item_winning_list_title,bean.getMsg());
    }

    @Override
    protected int getDriverLineId() {
        return R.id.item_winning_list_line;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_winning_list;
    }
}
