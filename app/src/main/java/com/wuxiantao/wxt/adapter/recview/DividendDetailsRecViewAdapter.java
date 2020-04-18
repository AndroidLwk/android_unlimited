package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.DividedDragonListBean;
import com.wuxiantao.wxt.utils.DateUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividendDetailsRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午3:27
 * Description:${DESCRIPTION}
 */
public class DividendDetailsRecViewAdapter extends RcvBaseAdapter<DividedDragonListBean.ListBean> {

    public DividendDetailsRecViewAdapter(Context context, List<DividedDragonListBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, DividedDragonListBean.ListBean bean, int position) {
        holder.setText(R.id.item_dividend_detail_date, DateUtils.timestampToTime(bean.getTime()));
        holder.setText(R.id.item_dividend_detail_nickname,bean.getNickname());
        holder.setText(R.id.item_dividend_detail_obtain_model,bean.getMsg());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_dividended_detail;
    }
}
