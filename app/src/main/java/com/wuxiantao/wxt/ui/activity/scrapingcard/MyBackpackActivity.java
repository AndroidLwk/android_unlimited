package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.presenter.MyBackpackPrewenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackAllFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackCrashCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackHerFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackHeroCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackSkinCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackpackScreptCardFragment;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的背包
 */
@ContentView(R.layout.activity_mybackpack)
public class MyBackpackActivity extends MvpActivity<MyBackpackPrewenter, MyBackpackContract> implements RadioGroup.OnCheckedChangeListener {
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
    @ViewInject(R.id.rb_all)
    RadioButton rb_all;
    @ViewInject(R.id.rb_scrapingcard)
    RadioButton rb_scrapingcard;
    @ViewInject(R.id.rb_hero_ft)
    RadioButton rb_hero_ft;
    @ViewInject(R.id.ft_mybackpack)
    FrameLayout ft_mybackpack;

    @Override
    protected void initView() {
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        rg_title.setOnCheckedChangeListener(this);
        changeFragment(0, null);
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

    private FragmentTransaction mTransaction;

    //隐藏所有fragment
    private void hideFragments() {
        hideFragments(mBackPackAllFragment);
        hideFragments(mBackPackCrashCardFragment);
        hideFragments(mBackPackHerFragment);
        hideFragments(mBackPackHeroCardFragment);
        hideFragments(mBackpackScreptCardFragment);
        hideFragments(mBackPackSkinCardFragment);
    }

    //隐藏
    private void hideFragments(Fragment fragment) {
        if (fragment != null) {
            mTransaction.hide(fragment);
        }
    }

    private BackPackAllFragment mBackPackAllFragment;
    private BackPackCrashCardFragment mBackPackCrashCardFragment;
    private BackPackHerFragment mBackPackHerFragment;
    private BackPackHeroCardFragment mBackPackHeroCardFragment;
    private BackpackScreptCardFragment mBackpackScreptCardFragment;
    private BackPackSkinCardFragment mBackPackSkinCardFragment;

    //切换页面
    private void changeFragment(int index, Bundle bundle) {
        FragmentManager mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        hideFragments();
        switch (index) {
            //所有
            case 0:
                if (mBackPackAllFragment == null) {
                    mBackPackAllFragment = new BackPackAllFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackAllFragment);
                } else {
                    mTransaction.show(mBackPackAllFragment);
                }
                if (bundle != null) {
                    mBackPackAllFragment.setArguments(bundle);
                }
                break;
            //刮刮卡
            case 1:
                if (mBackpackScreptCardFragment == null) {
                    mBackpackScreptCardFragment = new BackpackScreptCardFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackpackScreptCardFragment);
                } else {
                    mTransaction.show(mBackpackScreptCardFragment);
                }
                if (bundle != null) {
                    mBackpackScreptCardFragment.setArguments(bundle);
                }
                break;
            //英雄碎片
            case 2:
                if (mBackPackHerFragment == null) {
                    mBackPackHerFragment = new BackPackHerFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackHerFragment);
                } else {
                    mTransaction.show(mBackPackHerFragment);
                }
                if (bundle != null) {
                    mBackPackHerFragment.setArguments(bundle);
                }
                break;
            case 3://英雄卡
                if (mBackPackHeroCardFragment == null) {
                    mBackPackHeroCardFragment = new BackPackHeroCardFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackHeroCardFragment);
                } else {
                    mTransaction.show(mBackPackHeroCardFragment);
                }
                if (bundle != null) {
                    mBackPackHeroCardFragment.setArguments(bundle);
                }
                break;
            //皮肤卡
            case 4:
                if (mBackPackSkinCardFragment == null) {
                    mBackPackSkinCardFragment = new BackPackSkinCardFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackSkinCardFragment);
                } else {
                    mTransaction.show(mBackPackSkinCardFragment);
                }
                if (bundle != null) {
                    mBackPackSkinCardFragment.setArguments(bundle);
                }
                break;
            case 5://现金卡
                if (mBackPackCrashCardFragment == null) {
                    mBackPackCrashCardFragment = new BackPackCrashCardFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackCrashCardFragment);
                } else {
                    mTransaction.show(mBackPackCrashCardFragment);
                }
                if (bundle != null) {
                    mBackPackCrashCardFragment.setArguments(bundle);
                }
                break;
        }
        mTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_all:
                changeFragment(0, null);
                break;
            case R.id.rb_scrapingcard:
                changeFragment(1, null);
                break;
            case R.id.rb_hero_ft:
                changeFragment(2, null);
                break;
            case R.id.rb_hero_card:
                changeFragment(3, null);
                break;
            case R.id.rb_pifu_card:
                changeFragment(4, null);
                break;
            case R.id.rb_crash_card:
                changeFragment(5, null);
                break;
        }
    }
}
