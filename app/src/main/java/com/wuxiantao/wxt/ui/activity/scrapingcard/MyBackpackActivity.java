package com.wuxiantao.wxt.ui.activity.scrapingcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MyBoxTypeAdapter;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.presenter.MyBackpackPrewenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackAllFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackCrashCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackHerFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackHeroCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackSkinCardFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackPackTemapFragment;
import com.wuxiantao.wxt.ui.fragment.mybackpack.BackpackScreptCardFragment;
import com.wuxiantao.wxt.ui.title.CNToolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_SETPAY_PASS;

/**
 * 我的背包
 */
@ContentView(R.layout.activity_mybackpack)
public class MyBackpackActivity extends MvpActivity<MyBackpackPrewenter, MyBackpackContract> implements MyBackpackContract {
    @ViewInject(R.id.cntoolbar_title)
    CNToolbar cntoolbar_title;
    @ViewInject(R.id.ft_mybackpack)
    FrameLayout ft_mybackpack;
    @ViewInject(R.id.rv_boxType)
    RecyclerView rv_boxType;
    private List<BoxTypeBean> mData = new ArrayList<>();
    private MyBoxTypeAdapter mAdapter;

    @Override
    protected void initView() {
        mPresenter.isSetPayPassword(getAppToken());
        cntoolbar_title.setOnLeftButtonClickListener(() -> finish());
        cntoolbar_title.setOnRightButtonClickListener(() -> $startActivity(BackpackExpansionActivity.class));
        mPresenter.getBoxCate(getAppToken());
        rv_boxType.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyBoxTypeAdapter(this, mData);
        rv_boxType.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((bean, potion) -> {
            mPresenter.changeBoxType(mData, potion);//切换效果
            mAdapter.notifyDataSetChanged();
            Bundle bundle = new Bundle();
            bundle.putInt("pid", bean.getId());
            changeFragment(potion, bundle);//切换界面
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

    private FragmentTransaction mTransaction;

    //隐藏所有fragment
    private void hideFragments() {
        hideFragments(mBackPackAllFragment);
        hideFragments(mBackPackCrashCardFragment);
        hideFragments(mBackPackHerFragment);
        hideFragments(mBackPackHeroCardFragment);
        hideFragments(mBackpackScreptCardFragment);
        hideFragments(mBackPackSkinCardFragment);
        hideFragments(mBackPackTemapFragment);
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
    private BackPackTemapFragment mBackPackTemapFragment;

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
            case 6://暂存
                if (mBackPackTemapFragment == null) {
                    mBackPackTemapFragment = new BackPackTemapFragment();
                    mTransaction.add(R.id.ft_mybackpack, mBackPackTemapFragment);
                } else {
                    mTransaction.show(mBackPackTemapFragment);
                }
                if (bundle != null) {
                    mBackPackTemapFragment.setArguments(bundle);
                }
                break;

        }
        mTransaction.commit();
    }


    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void exchangeSuccess() {

    }

    @Override
    public void useCardSuccess(String msg) {

    }

    @Override
    public void discardSuccess(String msg) {

    }

    @Override
    public void showMyBackPack(MyBoxInfo list) {

    }

    @Override
    public void showBoxType(List<BoxTypeBean> list) {
        mData.clear();
        mData.addAll(list);
        mAdapter.notifyDataSetChanged();
        if (list.size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("pid", list.get(0).getId());
            changeFragment(0, bundle);
        }
    }

    @Override
    public void isSetPayPasswordSuccess(IsSetPayPassword info) {
        //保存设置密码状态
        saveUserInfo(IS_SETPAY_PASS, info.getStatus()+"");
    }

}
