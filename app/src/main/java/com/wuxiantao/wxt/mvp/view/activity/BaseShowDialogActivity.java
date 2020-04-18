package com.wuxiantao.wxt.mvp.view.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.StringRes;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.ui.dialog.DialogBuilder;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseShowDialogActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-27 上午10:42
 * Description:${DESCRIPTION}
 */
public abstract class BaseShowDialogActivity<P extends MvpPresenter,V extends MvpView> extends BasePermissionActivity<P,V> {


    protected  void onTimeEnd(){

    }

    protected void showTimeDialog(String title,String befoText,String afterText,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this){
            @Override
            public void onTimeEnd() {
                super.onTimeEnd();
                BaseShowDialogActivity.this.onTimeEnd();
            }
        }
                .setDialogTitle(title)
                .setDialogCountDownTime(befoText,afterText)
                .setDialogPositiveBtn(listener)
                .startCountDowntime().build().show();
    }

    protected void showTimeDialog(String title,String befoText,String afterText,DialogInterface.OnClickListener listener,long time){
        new DialogBuilder.Builder(this){
            @Override
            public void onTimeEnd() {
                super.onTimeEnd();
                BaseShowDialogActivity.this.onTimeEnd();
            }
        }
                .setDialogTitle(title)
                .setDialogCountDownTime(befoText,afterText)
                .setDialogPositiveBtn(listener)
                .startCountDowntime(time).build().show();
    }

    protected void showDialog(String title, DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener)
                .setDialogNegativeBtn().build().show();
    }

    protected void showOnlyConfirmDialog(String title,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener).build().show();
    }

    protected void showOnlyConfirmDialog(String title,String msg){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .build().show();
    }

    protected void showOnlyConfirmDialog(@StringRes int title,@StringRes  int msg){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn()
                .build()
                .show();
    }


    protected void showOnlyConfirmDialog(String title){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn().build().show();
    }


    protected void showDialog(String title, String msg,DialogInterface.OnClickListener listener1,DialogInterface.OnClickListener listener2){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener1)
                .setDialogNegativeBtn(listener2).build().show();
    }

    protected void showDialog(String title, String msg,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener)
                .setDialogNegativeBtn().build().show();
    }


    protected void showDialog(String title, String msg,String confirmText,String confirmColor,String cancelColor,
                              DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(confirmText,listener,confirmColor)
                .setDialogNegativeBtn(BaseApplication.getAppContext().getString(R.string.cancel),cancelColor).build().show();
    }

    protected void showDialog(String title,String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

    protected void showDialog(String title,String msg,String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

    protected void showDialog(String title, @StringRes int msg, String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

}
