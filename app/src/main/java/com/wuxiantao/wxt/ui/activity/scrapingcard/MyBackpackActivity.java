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
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.event.MessageEvent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.IS_SETPAY_PASS;
import static com.wuxiantao.wxt.config.Constant.SUCCESS_EXPANSION_BACK;

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
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setStatusBar();
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
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
                mBackPackAllFragment.refreshData();
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
                mBackpackScreptCardFragment.refreshData();
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
                mBackPackHerFragment.refreshData();
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
                mBackPackHeroCardFragment.refreshData();
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
                mBackPackSkinCardFragment.refreshData();
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
                mBackPackCrashCardFragment.refreshData();
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
                mBackPackTemapFragment.refreshData();
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
        saveUserInfo(IS_SETPAY_PASS, info.getStatus() + "");
    }

    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {

    }

    @Override
    public void onAliPay(String order_id, String res) {

    }

    @Override
    public void onOrderCreateFailure(String failure) {

    }

    @Override
    public void onOrderPaySuccess(String msg) {

    }

    @Override
    public void onOrderPayFailure(String failure) {

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPayError() {

    }

    @Override
    public void onPayCancel() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals(SUCCESS_EXPANSION_BACK)) {
            switch (mPresenter.getSeletedPotion(mData)) {
                case 0:
                    mBackPackAllFragment.refreshData();
                    break;
                case 1:
                    mBackpackScreptCardFragment.refreshData();
                    break;
                case 2:
                    mBackPackHerFragment.refreshData();
                    break;
                case 3:
                    mBackPackHeroCardFragment.refreshData();
                    break;
                case 4:
                    mBackPackSkinCardFragment.refreshData();
                    break;
                case 5:
                    mBackPackCrashCardFragment.refreshData();
                    break;
                case 6:
                    mBackPackTemapFragment.refreshData();
                    break;
            }
        }
    }
}
