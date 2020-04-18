package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.LogRecordingBean;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.MathUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DurationRecordingRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午4:08
 * Description:${DESCRIPTION}
 */
public class DurationRecordingRecViewAdapter extends RcvBaseAdapter<LogRecordingBean.ListBean> {

    public DurationRecordingRecViewAdapter(Context context, List<LogRecordingBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, LogRecordingBean.ListBean bean, int position) {
        holder.setText(R.id.item_log_recording_title,bean.getMsg());
        holder.setText(R.id.item_log_recording_time, DateUtils.timestampToTime(bean.getTime()));
        holder.setText(R.id.item_log_recording_num, MathUtils.formatCurrency(bean.getNum(),2));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_duration_recording;
    }
}
