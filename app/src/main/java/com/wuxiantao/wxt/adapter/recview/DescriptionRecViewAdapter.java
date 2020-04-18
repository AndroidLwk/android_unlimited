package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DescriptionRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 上午11:02
 * Description:${DESCRIPTION} 邀请好友登陆->活动说明适配器
 */
public class DescriptionRecViewAdapter extends RcvBaseAdapter<String> {

    public DescriptionRecViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.item_invite_friend_login_activi_description_mt,s);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_invite_friend_login_activi_description;
    }
}
