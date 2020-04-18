package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.InviteFriendNumBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ParticipateRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 上午10:21
 * Description:${DESCRIPTION}立即参加活动->免费获取VIP权益 头像横向列表适配器
 */
public class ParticipateRecViewAdapter extends RcvBaseAdapter<InviteFriendNumBean.UserBean> {

    public ParticipateRecViewAdapter(Context context, List<InviteFriendNumBean.UserBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, InviteFriendNumBean.UserBean bean, int position) {
        holder.setCircleImageResource(R.id.item_participate_img,bean.getHeader());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_participate;
    }
}
