package com.wuxiantao.wxt.mvp.view.activity;

import android.view.View;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.ui.activity.WeChatLoginActivity;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午1:08
 * Description:${DESCRIPTION}
 */
public abstract class MvpActivity<P extends MvpPresenter,V extends MvpView> extends BaseShowDialogActivity<P,V> implements View.OnClickListener, View.OnLongClickListener {


    private   long lastClick = 0;


    @Override
    protected void loginOut() {
        showDialog(
                getString(R.string.login_prompt),
                getString(R.string.is_login),
                getString(R.string.immediately_login), "#000000", "#C2C2C2",
                (dialog, which) -> $startActivity(WeChatLoginActivity.class));
    }

    protected String getTextViewText(TextView tv){
        return tv != null ? tv.getText().toString().trim() : "";
    }

    @Override
    public void onClick(View v) {
        if (fastClick()){
            widgetClick(v.getId());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (fastClick()){
            onLongClick(v.getId());
        }
        return false;
    }

    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 500){
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }


    protected  void setOnClikListener(View ...components) {
        if (components.length <= 0){
            return;
        }
        for (View v : components){
            v.setOnClickListener(this);
        }
    }


    protected  void setOnLongClikListener(View ...components) {
        if (components.length <= 0){
            return;
        }
        for (View v : components){
            v.setOnLongClickListener(this);
        }
    }

}
