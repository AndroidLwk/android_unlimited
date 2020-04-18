package com.wuxiantao.wxt.mvp.view.fragment;

import android.view.View;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午1:22
 * Description:${DESCRIPTION}
 */
public abstract class MvpFragment<P extends MvpPresenter,V extends MvpView> extends BasePermissionFragment<P, V> implements  View.OnClickListener{

    private long lastClick = 0;

    @Override
    public void onClick(View v) {
        if (fastClick()){
            widgetClick(v.getId());
        }
    }


    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000){
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
            if (v == null){
                continue;
            }
            v.setOnClickListener(this);
        }
    }

}
