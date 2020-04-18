package com.wuxiantao.wxt.ui.popupwindow.base;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 下午2:28
 * Description:${DESCRIPTION}
 */
public abstract class BasePopupWindow implements PopupWindow.OnDismissListener{

    private View mView;
    private PopupWindow mPopupWindow;
    private Activity mActivity;

    public BasePopupWindow(BaseBuild build){
        this.mView = build.mView;
        this.mPopupWindow = build.mPopupWindow;
        this.mActivity = build.mActivity;
        this.mPopupWindow.setOnDismissListener(this);
    }

    public void  showPopupWindow(int gravity){
        if (mView != null && !mActivity.isFinishing() && !mPopupWindow.isShowing()){
            mPopupWindow.showAtLocation(mView,gravity,0,0);
            backGroundAlpha(0.5f);
        }
    }



    public void  showPopupWindow(){
        showPopupWindow(Gravity.BOTTOM);
    }


    //设置背景透明度
    private void backGroundAlpha(float bgAlpha){
        WindowManager.LayoutParams wl = mActivity.getWindow().getAttributes();
        // 0.0 - 1.0
        wl.alpha = bgAlpha;
        mActivity.getWindow().setAttributes(wl);
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onDismiss() {
        backGroundAlpha(1.0f);
    }


}
