package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.HelpCenterBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HelpCenterRcvViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-30 下午1:09
 * Description:${DESCRIPTION}
 */
public class HelpCenterRcvViewAdapter extends RcvBaseAdapter<HelpCenterBean> {


    public HelpCenterRcvViewAdapter(Context context, List<HelpCenterBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, HelpCenterBean bean, int position) {
        String url = bean.getImg();
        holder.setRoundImageResource(R.id.item_help_center_img,url);
        holder.setViewOnClickListener(R.id.item_help_center_img, v -> {
            if (listener != null){
                listener.onHelpLinkClick(bean.getLink());
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_help_center;
    }


    private OnLinkClickListener listener;

    public void setOnLinkClickListener(OnLinkClickListener l){
        this.listener = l;
    }

    public interface OnLinkClickListener{
        void onHelpLinkClick(String link);
    }
}
