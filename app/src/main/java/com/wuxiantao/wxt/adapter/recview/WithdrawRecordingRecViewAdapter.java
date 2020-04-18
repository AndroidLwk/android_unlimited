package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.WithdrawRecordingBean;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.DateUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawRecordingRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午6:23
 * Description:${DESCRIPTION}
 */
public class WithdrawRecordingRecViewAdapter extends RcvBaseAdapter<WithdrawRecordingBean.ListBean> {


    public WithdrawRecordingRecViewAdapter(Context context, List<WithdrawRecordingBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder,WithdrawRecordingBean.ListBean bean, int position) {
        int status = bean.getStatus();
        switch (status){
            case 0:
                holder.setText(R.id.item_withdraw_recording_type, R.string.withdraw_failure);
                holder.setTextColor(R.id.item_withdraw_recording_type,"#EE4000");
                break;
            case 1:
                holder.setText(R.id.item_withdraw_recording_type, R.string.withdraw_doing);
                holder.setTextColor(R.id.item_withdraw_recording_type,"#EEB422");
                break;
            case 2:
                holder.setText(R.id.item_withdraw_recording_type, R.string.withdraw_success);
                holder.setTextColor(R.id.item_withdraw_recording_type,"#32CD32");
                break;
        }
        holder.setText(R.id.item_withdraw_recording_time, DateUtils.timestampToTime(bean.getCreate_time()));
        holder.setText(R.id.item_withdraw_recording_money, BigDecimalUtils.round(bean.getMoney(),2));
    }

    @Override
    protected int getDriverLineId() {
        return R.id.item_withdraw_recording_line;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_withdraw_recording;
    }
}
