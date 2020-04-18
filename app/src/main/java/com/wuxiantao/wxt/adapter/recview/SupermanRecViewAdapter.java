package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.SupermanMenuBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SupermanRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-14 下午1:23
 * Description:${DESCRIPTION}
 */
public class SupermanRecViewAdapter extends RcvBaseAdapter<SupermanMenuBean> {

    private String new_url;

    public SupermanRecViewAdapter(Context context, List<SupermanMenuBean> list,String new_url) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, SupermanMenuBean bean, int position) {
        holder.setImageResource(R.id.item_super_man_icon,bean.getIcon());
        holder.setText(R.id.item_super_man_title,bean.getTitle());
        holder.setText(R.id.item_super_man_content,bean.getContent());
        holder.setBtnText(R.id.item_super_man_confirm,bean.getText());
        if (position == 4){
            holder.setViewEnabled(R.id.item_super_man_confirm,!isEmpty(new_url));
        }
        holder.setViewOnClickListener(R.id.item_super_man_confirm, v -> {
            if (listener != null){
                switch (position){
                    case 0:
                        listener.onPlayGame();
                        break;
                    case 1:
                        listener.onInviteFriend();
                        break;
                    case 2:
                        listener.onOpenMember();
                        break;
                    case 3:
                        listener.onShopping();
                        break;
                    case 4:
                        listener.onExperience();
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_super_man;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onPlayGame();
        void onInviteFriend();
        void onOpenMember();
        void onShopping();
        void onExperience();
    }
}
