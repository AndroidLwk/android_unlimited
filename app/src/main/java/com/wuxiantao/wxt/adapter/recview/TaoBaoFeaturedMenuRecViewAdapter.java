package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.TaoBaoFeaturedMenuBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedMenuRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 下午3:08
 * Description:${DESCRIPTION}淘宝精选-九宫格菜单
 */
public class TaoBaoFeaturedMenuRecViewAdapter extends RcvBaseAdapter<TaoBaoFeaturedMenuBean> {

    public TaoBaoFeaturedMenuRecViewAdapter(Context context, List<TaoBaoFeaturedMenuBean> list){
        super(context,list);
    }


    @Override
    public void convert(BaseViewHolder holder, TaoBaoFeaturedMenuBean bean, int position) {
        holder.setImageResource(R.id.item_taobao_featured_menu_icon,bean.getIcon());
        holder.setText(R.id.item_taobao_featured_menu_title,bean.getName());
        holder.setViewOnClickListener(R.id.item_taobao_featured_menu_layout, v -> {
            if (listener != null){
                listener.OnItemClick(position);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_taobao_featured_menu;
    }
}
