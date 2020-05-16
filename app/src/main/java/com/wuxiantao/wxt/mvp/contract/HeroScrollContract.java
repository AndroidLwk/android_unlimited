package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.List;

public interface HeroScrollContract extends MvpView {
    void showMyScroll(List<HeroScrolllBean> list);

    void getMyScrollOnFailure(String msg);
    void composeHeroOnFailure(String msg);
    void composeHeroSuccess(String msg);
}
