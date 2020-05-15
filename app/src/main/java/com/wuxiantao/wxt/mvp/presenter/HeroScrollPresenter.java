package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.bean.ComposeHeroBean;
import com.wuxiantao.wxt.mvp.contract.HeroScrollContract;
import com.wuxiantao.wxt.mvp.model.HeroScrollModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

public class HeroScrollPresenter extends BasePresenter<HeroScrollContract> {
    private HeroScrollContract view;
    private HeroScrollModel model = new HeroScrollModel();

    /**
     * 英雄轴
     */
    public void myScroll(String token, String pid) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List<HeroScrolllBean>> observer = new BaseObserver<List<HeroScrolllBean>>() {
            @Override
            public void onSuccess(List<HeroScrolllBean> bean) {


            }

            @Override
            public void onFailure(String errorMsg) {

            }
        };
        model.myScroll(observer, token, pid);
    }

    /**
     * 英雄碎片合成
     */
    public void composeHero(String token, String cid) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<ComposeHeroBean> observer = new BaseObserver<ComposeHeroBean>() {
            @Override
            public void onSuccess(ComposeHeroBean bean) {


            }

            @Override
            public void onFailure(String errorMsg) {

            }
        };
        model.composeHero(observer, token, cid);
    }
}
