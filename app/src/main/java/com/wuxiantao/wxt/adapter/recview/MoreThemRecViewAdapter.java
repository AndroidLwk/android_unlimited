package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.ShareBackGroundBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MoreThemRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 下午6:15
 * Description:${DESCRIPTION}
 */
public class MoreThemRecViewAdapter extends RcvBaseAdapter<ShareBackGroundBean.ListBean> {

    public MoreThemRecViewAdapter(Context context, List<ShareBackGroundBean.ListBean> list) {
        super(context, list);
    }


    @Override
    public void convert(BaseViewHolder holder, ShareBackGroundBean.ListBean bean, int position) {
        holder.setRoundImageResource(R.id.item_more_them_img,bean.getImg(),R.drawable.no_banner,0.0f);
        holder.setText(R.id.item_more_them_name,bean.getTheme_name());
        holder.setViewOnClickListener(R.id.item_more_them_layout, v -> {
            if (listener != null){
                listener.OnItemClick(bean.getId());
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_more_them;
    }
}
