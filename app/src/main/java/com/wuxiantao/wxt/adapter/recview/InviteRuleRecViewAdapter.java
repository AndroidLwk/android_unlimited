package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteRuleRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-9 下午5:00
 * Description:${DESCRIPTION}
 */
public class InviteRuleRecViewAdapter extends RcvBaseAdapter<String> {

    public InviteRuleRecViewAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder,String s, int position) {
        holder.setRuleTagText(R.id.item_invite_rule_title,s,position);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_invite_rule;
    }
}
