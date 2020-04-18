package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.AboutSuperManBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutSupermanRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 下午4:37
 * Description:${DESCRIPTION}
 */
public class AboutSupermanRecViewAdapter extends RcvBaseAdapter<AboutSuperManBean> {

    public AboutSupermanRecViewAdapter(Context context, List<AboutSuperManBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, AboutSuperManBean bean, int position) {
        holder.setText(R.id.item_about_superman_title,bean.getTitle());
        if (bean.getVersion() == null){
            holder.setVisibility(R.id.item_about_superman_version,View.GONE);
        }else {
            holder.setText(R.id.item_about_superman_version,bean.getVersion());
            holder.setVisibility(R.id.item_about_superman_version,View.VISIBLE);
        }
        holder.setViewOnClickListener(R.id.item_about_superman_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_about_superman;
    }
}
