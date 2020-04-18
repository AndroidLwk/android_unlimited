package com.wuxiantao.wxt.adapter.recview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.utils.AnimaUtils;
import com.wuxiantao.wxt.utils.PropUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividedDragonRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午3:41
 * Description:${DESCRIPTION}
 */
public class DividedDragonRecViewAdapter extends RcvBaseAdapter<DragonStatusInfoBean.CardMessageBean> {


    private boolean isShowing;

    public DividedDragonRecViewAdapter(Context context, List<DragonStatusInfoBean.CardMessageBean> list) {
        super(context, list);
    }

    public void updataList(List<DragonStatusInfoBean.CardMessageBean> list,boolean isShowing) {
        super.updataList(list);
        this.isShowing = isShowing;
    }

    @Override
    protected void convert(BaseViewHolder holder, DragonStatusInfoBean.CardMessageBean bean, int position) {
        holder.setImageResource(R.id.item_divided_dragon_icon, PropUtils.queryPictureId(bean.getDragon_id()));
        if (isComplete(getList())){
            startAnim(holder);
        }else {
            if (bean.getDragon_id() == 99){
                holder.setViewOnClickListener(R.id.item_divided_dragon_icon, v -> {
                    if (listener != null){
                        listener.onOpenDividedDragon(bean.getId());
                    }
                });
            }else {
                holder.setViewOnClickListener(R.id.item_divided_dragon_icon,null);
            }
        }
    }

    //检测是否完成翻牌
    private static boolean isComplete(List<DragonStatusInfoBean.CardMessageBean> lists){
        for (DragonStatusInfoBean.CardMessageBean bean : lists){
            if (bean.getDragon_id() == 99){
                return false;
            }
        }
        return true;
    }


    //开始动画
    private void startAnim(BaseViewHolder holder){
        AnimaUtils.startShakeByPropertyAnim(holder.findView(R.id.item_divided_dragon_icon),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (listener != null && !isShowing){
                            isShowing = true;
                            listener.onAnimationEnd();
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_divided_dragon_img;
    }

    private OnOpenDividedDragonListener listener;

    public void setOnOpenDividedDragonListener(OnOpenDividedDragonListener listener) {
        this.listener = listener;
    }

    public interface OnOpenDividedDragonListener {
        void onOpenDividedDragon(int id);
        void onAnimationEnd();
    }


}
