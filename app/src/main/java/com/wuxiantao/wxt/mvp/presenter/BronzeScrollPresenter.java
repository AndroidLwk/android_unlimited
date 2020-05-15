package com.wuxiantao.wxt.mvp.presenter;

import android.content.res.TypedArray;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.contract.BronzeScrollContract;

import java.util.ArrayList;
import java.util.List;

public class BronzeScrollPresenter extends BasePresenter<BronzeScrollContract> {
    public List<HeroScrolllBean> getData_1() {
        List<HeroScrolllBean> list = new ArrayList<>();
        TypedArray ta;
        for (int i = 0; i < 9; i++) {
            HeroScrolllBean beanxx = new HeroScrolllBean();
            ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_lb_bronze);//默认展示李白
            beanxx.setId(26);
            beanxx.setHero_img_background(ta.getResourceId(i, 0));
            list.add(beanxx);
        }
        return list;
    }

    public List<HeroScrolllBean> getData_2() {
        List<HeroScrolllBean> list = new ArrayList<>();
        HeroScrolllBean bean = new HeroScrolllBean(R.drawable.bronze_cc, 0, 0, 26);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_dj, 0, 0, 27);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_dq, 0, 0, 28);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_hwj, 0, 0, 29);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_hx, 12, 1, 30);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_jl, 0, 0, 31);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_k, 0, 0, 32);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_lb, 0, 0, 33);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_ssx, 0, 0, 34);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_wsj, 0, 0, 35);
        list.add(bean);
        for (HeroScrolllBean beanx : list) {
            switch (beanx.getId()) {
                case 26://青铜李白
                    beanx.setHero_img_background(R.drawable.bronze_lb);
                    break;
                case 27://青铜韩信
                    beanx.setHero_img_background(R.drawable.bronze_hx);
                    break;
                case 28://青铜孙尚香
                    beanx.setHero_img_background(R.drawable.bronze_ssx);
                    break;
                case 29://青铜伽罗
                    beanx.setHero_img_background(R.drawable.bronze_jl);
                    break;
                case 30://青铜蔡文姬
                    beanx.setHero_img_background(R.drawable.bronze_hwj);
                    break;
                case 31://青铜大乔

                    beanx.setHero_img_background(R.drawable.bronze_dq);
                    break;
                case 32://青铜凯

                    beanx.setHero_img_background(R.drawable.bronze_k);
                    break;
                case 33://青铜妲己
                    beanx.setHero_img_background(R.drawable.bronze_dj);
                    break;
                case 34://青铜王昭君
                    beanx.setHero_img_background(R.drawable.bronze_wsj);
                    break;
                case 35://青铜曹操
                    beanx.setHero_img_background(R.drawable.bronze_cc);
                    break;

            }
        }
        return list;
    }

    /**
     * 修改数据
     */
    public void notifyData_1(List<HeroScrolllBean> list, int id) {
        TypedArray ta;
        for (HeroScrolllBean bean : list) {
            bean.setId(id);
            switch (id) {//青铜
                case 26://李白
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_lb_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 27://青铜韩信
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_hx_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 28://青铜孙尚香
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_ssx_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 29://青铜伽罗
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_jialuo_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 30://青铜蔡文姬
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_cyj_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 31://青铜大乔
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_dq_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 32://青铜凯
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_kai_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 33://青铜妲己
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_dj_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 34://青铜王昭君
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_wsj_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 35://青铜曹操
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_cc_bronze);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                default:
                    break;
            }
        }

    }
}
