package com.wuxiantao.wxt.ui.activity.my;

import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.presenter.MyBackpackPrewenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_mybackpack)
public class MyBackpackActivity extends MvpActivity<MyBackpackPrewenter, MyBackpackContract> {
    @ViewInject(R.id.cntoolbar_title)
    CNToolbar cntoolbar_title;
    @ViewInject(R.id.rb_hero_card)
    RadioButton rb_hero_card;
    @ViewInject(R.id.rb_pifu_card)
    RadioButton rb_pifu_card;
    @ViewInject(R.id.rb_crash_card)
    RadioButton rb_crash_card;
    @ViewInject(R.id.rg_title)
    RadioGroup rg_title;
    @ViewInject(R.id.rv_myBackpack)
    RecyclerView rv_myBackpack;

    @Override
    protected void initView() {
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        cntoolbar_title.setOnRightButtonClickListener(() -> {
        });
    }

    @Override
    protected MyBackpackPrewenter CreatePresenter() {
        return new MyBackpackPrewenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
