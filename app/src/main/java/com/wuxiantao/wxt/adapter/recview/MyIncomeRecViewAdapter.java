package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.DateUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomeRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午3:51
 * Description:${DESCRIPTION}
 */
public class MyIncomeRecViewAdapter extends RcvBaseAdapter<MyIncomeBean.ListBean> {

    public MyIncomeRecViewAdapter(Context context, List<MyIncomeBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyIncomeBean.ListBean bean, int position) {
        holder.setText(R.id.item_my_income_date, DateUtils.timestampToTime(bean.getTime()));
        String number = BigDecimalUtils.round(bean.getNumber());
        holder.setText(R.id.item_my_income_active_income,number);
        String total = BigDecimalUtils.round(bean.getTotal());
        holder.setText(R.id.item_my_income_total,total);
    }

    @Override
    protected int getDriverLineId() {
        return R.id.item_my_income_line;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_my_income;
    }
}
