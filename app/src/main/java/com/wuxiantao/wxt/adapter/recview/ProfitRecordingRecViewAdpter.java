package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.MathUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingRecViewAdpter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-25 下午4:46
 * Description:${DESCRIPTION} 收益记录列表适配器
 */
public class ProfitRecordingRecViewAdpter extends RcvBaseAdapter<ProfitRecordingBean.SallBean> {

    public ProfitRecordingRecViewAdpter(Context context, List<ProfitRecordingBean.SallBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ProfitRecordingBean.SallBean bean, int position) {
        holder.setText(R.id.item_profit_recording_msg,bean.getMsg());
        holder.setText(R.id.item_profit_recording_time, DateUtils.timestampToTime(bean.getTime()));
        holder.setText(R.id.item_profit_recording_num, MathUtils.formatCurrency(bean.getNum(),5));
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_profit_recording;
    }
}
