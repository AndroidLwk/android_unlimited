package com.wuxiantao.wxt.ui.popupwindow.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AbsPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-6 下午5:34
 * Description:${DESCRIPTION}
 */
public class AbsPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener  {
    private Context mContext;
    private OnWindowDismissListener listener;

    AbsPopupWindow(Context context){
        this.mContext = context;
        //setBackgroundDrawable
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置动画
        this.setAnimationStyle(R.style.popup_window_anim);
        //设置PopupWindow是否可点击
        this.setTouchable(true);
        //设置PopupWindow是否获取焦点
        this.setFocusable(true);
        //设置点击外部区域PopupWindow是否消失
        this.setOutsideTouchable(false);
        //监听window消失
        this.setOnDismissListener(this);
        //设置宽度
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置高度
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
    }

    @Override
    public void dismiss() {
        if (listener != null){
            listener.onWindowDismiss();
        }
        super.dismiss();
    }

    protected void setOnWindowDismissListener(OnWindowDismissListener listener){
        this.listener = listener;
    }


    public interface OnWindowDismissListener{
        void onWindowDismiss();
    }


    @Override
    public void onDismiss() {

    }

}
