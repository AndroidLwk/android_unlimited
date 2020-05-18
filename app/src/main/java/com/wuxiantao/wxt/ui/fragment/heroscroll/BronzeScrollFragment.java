package com.wuxiantao.wxt.ui.fragment.heroscroll;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.adapter.recview.HeroScrollTwoAdapter;
import com.wuxiantao.wxt.adapter.recview.HeroScrolllOneAdapter;
import com.wuxiantao.wxt.mvp.contract.HeroScrollContract;
import com.wuxiantao.wxt.mvp.presenter.HeroScrollPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.utils.DensityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_bronzescroll)
public class BronzeScrollFragment extends MvpFragment<HeroScrollPresenter, HeroScrollContract> implements HeroScrollContract {
    @ViewInject(R.id.recylerview_one)
    RecyclerView recylerview_one;
    @ViewInject(R.id.recylerview_two)
    RecyclerView recylerview_two;
    @ViewInject(R.id.sbt_hecheng)
    StateButton sbt_hecheng;
    private List<HeroScrolllBean.ChildBean> mData_one = new ArrayList<>();
    private List<HeroScrolllBean> mData_two = new ArrayList<>();
    private HeroScrolllOneAdapter mAdapter_one;
    private HeroScrollTwoAdapter mAdapter_two;

    @Override
    protected void initView() {
        setOnClikListener(sbt_hecheng);
        mPresenter.myScroll(getAppToken(), "24");
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3, DensityUtils.dip2px(3), true);
        recylerview_one.addItemDecoration(itemDecoration);
        recylerview_one.setLayoutManager(manager);
        mAdapter_one = new HeroScrolllOneAdapter(getContext(), mData_one);
        recylerview_one.setAdapter(mAdapter_one);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recylerview_two.setLayoutManager(layoutManager);
        mAdapter_two = new HeroScrollTwoAdapter(getContext(), mData_two);
        mAdapter_two.setOnItemClickListener((heroScrolllBean, potion) -> {
            this.id = heroScrolllBean.getId();
            mData_one.clear();
            mData_one.addAll(heroScrolllBean.getChild());
            mAdapter_one.notifyDataSetChanged();
        });
        recylerview_two.setAdapter(mAdapter_two);
    }

    @Override
    protected HeroScrollPresenter CreatePresenter() {
        return new HeroScrollPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.sbt_hecheng) {
            if (id > 0) {
                showLoading();
                mPresenter.composeHero(getAppToken(), id + "");
            }
        }
    }

    private int id;//要合成的英雄卡ID

    @Override
    public void showMyScroll(List<HeroScrolllBean> list) {
        mData_two.clear();
        mData_one.clear();
        if (list.size() > 0) {
            mData_one.addAll(list.get(0).getChild());
            this.id = list.get(0).getId();
        }
        mData_two.addAll(list);
        mAdapter_one.notifyDataSetChanged();
        mAdapter_two.notifyDataSetChanged();
    }

    @Override
    public void getMyScrollOnFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void composeHeroOnFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void composeHeroSuccess(String msg) {
        mPresenter.myScroll(getAppToken(), "24");
        showOnlyConfirmDialog(msg);
    }
}
