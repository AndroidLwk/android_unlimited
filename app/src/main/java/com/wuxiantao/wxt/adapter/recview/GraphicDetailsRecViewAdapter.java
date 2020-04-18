package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GraphicDetailsRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-26 上午9:58
 * Description:${DESCRIPTION}
 */
public class GraphicDetailsRecViewAdapter extends RcvBaseAdapter<String> {

    public GraphicDetailsRecViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setRoundImageResource(R.id.item_graphic_details_img,s,R.drawable.no_banner,5.0f);
        holder.setViewOnClickListener(R.id.item_graphic_details_img, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_graphic_details;
    }
}
