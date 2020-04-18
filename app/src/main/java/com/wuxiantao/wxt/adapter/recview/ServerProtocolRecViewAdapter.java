package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ServerProtocolRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 下午4:16
 * Description:${DESCRIPTION}
 */
public class ServerProtocolRecViewAdapter extends RcvBaseAdapter<String> {

    public ServerProtocolRecViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.item_server_protocol_tv,s);
        holder.setViewOnClickListener(R.id.item_server_protocol_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_server_protocol;
    }
}
