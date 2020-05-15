package com.wuxiantao.wxt.ui.fragment.heroscroll;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.adapter.recview.HeroScrollTwoAdapter;
import com.wuxiantao.wxt.adapter.recview.HeroScrolllOneAdapter;
import com.wuxiantao.wxt.mvp.contract.BronzeScrollContract;
import com.wuxiantao.wxt.mvp.presenter.BronzeScrollPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.utils.DensityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_bronzescroll)
public class BronzeScrollFragment extends MvpFragment<BronzeScrollPresenter, BronzeScrollContract> {
    @ViewInject(R.id.recylerview_one)
    RecyclerView recylerview_one;
    @ViewInject(R.id.recylerview_two)
    RecyclerView recylerview_two;
    private List<HeroScrolllBean> mData_one = new ArrayList<>();
    private List<HeroScrolllBean> mData_two = new ArrayList<>();
    private HeroScrolllOneAdapter mAdapter_one;
    @Override
    protected void initView() {
            GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
            GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3, DensityUtils.dip2px(3), true);
            recylerview_one.addItemDecoration(itemDecoration);
            recylerview_one.setLayoutManager(manager);
            mData_one.clear();
            mData_one.addAll(mPresenter.getData_1());
            mAdapter_one = new HeroScrolllOneAdapter(getContext(), mData_one);
            recylerview_one.setAdapter(mAdapter_one);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
            //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recylerview_two.setLayoutManager(layoutManager);
            mData_two.clear();
            mData_two.addAll(mPresenter.getData_2());
            HeroScrollTwoAdapter mAdapter_two = new HeroScrollTwoAdapter(getContext(), mData_two);
            mAdapter_two.setOnItemClickListener((heroScrolllBean, potion) -> {
                mPresenter.notifyData_1(mData_one, heroScrolllBean.getId());
                mAdapter_one.notifyDataSetChanged();
            });
            recylerview_two.setAdapter(mAdapter_two);
    }

    @Override
    protected BronzeScrollPresenter CreatePresenter() {
        return new BronzeScrollPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
