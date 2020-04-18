package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.adapter.bean.MyTaskBean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DailyTaskRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午12:44
 * Description:${DESCRIPTION} 推荐任务
 */
public class RecommendedTaskRecViewAdapter extends RcvBaseAdapter<MyTaskBean> {

    public RecommendedTaskRecViewAdapter(Context context, List<MyTaskBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, MyTaskBean bean, int position) {
        switch (bean.getType()) {
            //填写微信号
            case MyTaskBean.WRITE_WECHAT_NO:
                holder.setText(R.id.item_recommended_task_btn, mContext.getString(R.string.go_write));

                holder.setViewOnClickListener(R.id.item_recommended_task_btn, v -> {
                    if (listener != null) {
                        listener.onSettingInfo();
                    }
                });

                break;
            //关注微信公众号
            case MyTaskBean.ATTENTION_WECHAT:
                holder.setText(R.id.item_recommended_task_btn, mContext.getString(R.string.copy));
                holder.setViewOnClickListener(R.id.item_recommended_task_btn, v -> {
                    if (listener != null) {
                        listener.onCopyNoPublic();
                    }
                });

                break;
            //绑定手机号
            case MyTaskBean.BINDING_NUMBER:
                holder.setText(R.id.item_recommended_task_btn, mContext.getString(R.string.go_binding));
                holder.setViewOnClickListener(R.id.item_recommended_task_btn, v -> {
                    if (listener != null) {
                        listener.onSettingInfo();
                    }
                });

                break;
            //绑定微信
            case MyTaskBean.BINDING_CHAT:
                holder.setText(R.id.item_recommended_task_btn, mContext.getString(R.string.go_binding));
                holder.setViewOnClickListener(R.id.item_recommended_task_btn, v -> {
                    if (listener != null) {
                        listener.onBindWeChat();
                    }
                });

                break;
        }
        holder.setImageResource(R.id.item_recommended_task_icon, bean.getIcon());
        holder.setText(R.id.item_recommended_task_title, bean.getTitle());
        holder.setText(R.id.item_recommended_task_content, bean.getContent());
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommended_task;
    }

    public OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onBindWeChat();

        void onSettingInfo();

        void onCopyNoPublic();
    }
}
