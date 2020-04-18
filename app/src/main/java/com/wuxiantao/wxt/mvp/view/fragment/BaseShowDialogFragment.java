package com.wuxiantao.wxt.mvp.view.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.ui.dialog.DialogBuilder;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseShowDialogFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午6:29
 * Description:${DESCRIPTION}
 */
public abstract class BaseShowDialogFragment<P extends MvpPresenter,V extends MvpView> extends BaseMvpFragment<P,V> {

    protected void showDialog(String title, String msg,String confirmText,String confirmColor,String cancelColor,
                              DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(getContext())
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(confirmText,listener,confirmColor)
                .setDialogNegativeBtn(getString(R.string.cancel),cancelColor).build().show();
    }

    protected void showDialog(@StringRes int msg, @StringRes int confirmText,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(getContext())
                .setDialogMessage(msg)
                .setDialogTitle(R.string.tips)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(getString(confirmText),listener,Color.parseColor("#FF5655"))
                .setDialogNegativeBtn().build().show();
    }


    protected void showOnlyConfirmDialog(String title,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(getContext())
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener).build().show();
    }

    protected void showOnlyConfirmDialog(String title){
        new DialogBuilder.Builder(getContext())
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn().build().show();
    }

    protected void showOnlyConfirmDialog(String title,String msg){
        new DialogBuilder.Builder(getContext())
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn().build().show();
    }

    protected void showOnlyConfirmDialog(@StringRes int title,@StringRes int msg){
        new DialogBuilder.Builder(getContext())
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn().build().show();
    }

    protected void showLoginDialog(){
        showDialog(
                getString(R.string.login_prompt),
                getString(R.string.is_login),
                getString(R.string.immediately_login), "#000000", "#C2C2C2",
                (dialog, which) -> login());
    }

    protected void login(){

    }
}
