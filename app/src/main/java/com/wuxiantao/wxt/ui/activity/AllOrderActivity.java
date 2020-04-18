package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.fragment.all_order.SelfOrderOrderFragment;
import com.wuxiantao.wxt.ui.fragment.all_order.TaoBaoOrderFragment;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AllOrderActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午3:43
 * Description:${DESCRIPTION} 全部订单
 */
@ContentView(R.layout.activity_all_order)
public class AllOrderActivity extends BaseActivity {
    @ViewInject(R.id.all_order_back)
    RelativeLayout all_order_back;
    @ViewInject(R.id.all_order_rg)
    RadioGroup all_order_rg;
    @ViewInject(R.id.all_order_taobao)
    RadioButton all_order_taobao;
    @ViewInject(R.id.all_order_commodity)
    RadioButton all_order_commodity;
    @ViewInject(R.id.all_order_frament)
    FrameLayout all_order_frament;

    private List<RadioButton> mRBlist;
    private FragmentTransaction mTransaction;
    private TaoBaoOrderFragment mTaoBaoOrderFragment;
    private SelfOrderOrderFragment mSelfOrderOrderFragment;
    private static final int DEFAUT_INDEX = 0;
    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        initRadioButton();
        setOnClikListener(all_order_back);
        all_order_rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.all_order_commodity:
                    changeFragment(0,null);
                    break;
                case R.id.all_order_taobao:
                    changeFragment(1,null);
                    break;
            }
        });
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.all_order_back){
            finish();
        }
    }

    //初始化RadioButton
    private void initRadioButton(){
        if (mRBlist == null){
            mRBlist = new ArrayList<>();
        }
        mRBlist.add(all_order_commodity);
        mRBlist.add(all_order_taobao);
        mRBlist.get(DEFAUT_INDEX).setChecked(true);
        changeFragment(DEFAUT_INDEX,null);
    }



    //切换页面
    private void changeFragment(int index, Bundle bundle){
        FragmentManager mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        hideFragments();
        switch (index){
            case 0:
                if (mSelfOrderOrderFragment == null){
                    mSelfOrderOrderFragment =  new SelfOrderOrderFragment();
                    mTransaction.add(R.id.all_order_frament,mSelfOrderOrderFragment);
                }else {
                    mTransaction.show(mSelfOrderOrderFragment);
                }
                if (bundle != null){
                    mSelfOrderOrderFragment.setArguments(bundle);
                }
                break;
            case 1:
                if (mTaoBaoOrderFragment == null){
                    mTaoBaoOrderFragment =  new TaoBaoOrderFragment();
                    mTransaction.add(R.id.all_order_frament,mTaoBaoOrderFragment);
                }else {
                    mTransaction.show(mTaoBaoOrderFragment);
                }
                if (bundle != null){
                    mTaoBaoOrderFragment.setArguments(bundle);
                }
                break;
        }
        mTransaction.commit();
    }

    //隐藏所有fragment
    private void hideFragments(){
        hideFragments(mSelfOrderOrderFragment);
        hideFragments(mTaoBaoOrderFragment);
    }

    //隐藏
    private void hideFragments(Fragment fragment){
        if (fragment != null){
            mTransaction.hide(fragment);
        }
    }
}
