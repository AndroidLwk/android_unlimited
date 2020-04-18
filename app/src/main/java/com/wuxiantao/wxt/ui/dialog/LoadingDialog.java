package com.wuxiantao.wxt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/9 0009 9:37 8-19
 * Description: ${DESCRIPTION} 网络加载对话框
 * Author: Administrator Shiming-Shi
 */

public class LoadingDialog {

    private Dialog  mDialog;

    public LoadingDialog(Build build){
       this.mDialog = build.mDialog;
    }

    public static  class Build{
        private Context  mContext;
        private Dialog mDialog;
        private TextView tv;
        public Build(Context  context){
            this.mContext = context;
            this.init();
        }

        private void init(){
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading,null);
            tv = view.findViewById(R.id.dialog_loading_progress);
            mDialog = new Dialog(mContext,R.style.custom_dialog);
            //是否可以按 返回键消失
            mDialog.setCancelable(true);
            //点击加载框以外的地方
            mDialog.setCanceledOnTouchOutside(false);
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(width,height);
            mDialog.setContentView(view,params);
            Window  window = mDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setGravity(Gravity.CENTER);
            window.setAttributes(wlp);
            window.setWindowAnimations(R.style.loading_dialog_anim);
        }

        public Build setLoadingText(String name){
            if (!isEmpty(name)){
                tv.setText(name);
            }
            return this;
        }

        public Build setLoadingText(@StringRes int resId){
            if (resId != 0){
                tv.setText(resId);
            }
            return this;
        }

        private boolean isEmpty(String s){
            return  s == null || TextUtils.isEmpty(s);
        }

        public LoadingDialog build(){
            return new LoadingDialog(this);
        }
    }


    private boolean isShowing(){
        return mDialog.isShowing();
    }


    public void showLoadingDialog(){
        if (mDialog != null && !isShowing()){
            mDialog.show();
        }
    }


    public  void dismissLoadingDialog(){
        if (mDialog != null && isShowing()){
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
