package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareThemRecViewAdater
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-9 下午12:15
 * Description:${DESCRIPTION}
 */
public class ShareThemRecViewAdater extends RcvBaseAdapter<String> {


    public ShareThemRecViewAdater(Context context, List<String> list) {
        super(context,list);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setRoundImageResource(R.id.img,s);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_share_them;
    }
}
