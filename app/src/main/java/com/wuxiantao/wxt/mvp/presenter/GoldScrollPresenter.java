package com.wuxiantao.wxt.mvp.presenter;

import android.content.res.TypedArray;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.contract.GoldScrollContract;

import java.util.ArrayList;
import java.util.List;

public class GoldScrollPresenter extends BasePresenter<GoldScrollContract> {
    public List<HeroScrolllBean> getData_1() {
        List<HeroScrolllBean> list = new ArrayList<>();
        TypedArray ta;
        for (int i = 0; i < 9; i++) {
            HeroScrolllBean beanxx = new HeroScrolllBean();
            ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_lb_gold);//默认展示李白
            beanxx.setId(36);
            beanxx.setHero_img_background(ta.getResourceId(i, 0));
            list.add(beanxx);
        }
        return list;
    }

    public List<HeroScrolllBean> getData_2() {
        List<HeroScrolllBean> list = new ArrayList<>();
        HeroScrolllBean bean = new HeroScrolllBean(R.drawable.bronze_cc, 0, 0, 36);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_dj, 0, 0, 37);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_dq, 0, 0, 38);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_hwj, 0, 0, 39);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_hx, 12, 1, 40);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_jl, 0, 0, 41);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_k, 0, 0, 42);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_lb, 0, 0, 43);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_ssx, 0, 0, 44);
        list.add(bean);
        bean = new HeroScrolllBean(R.drawable.bronze_wsj, 0, 0, 45);
        list.add(bean);
        for (HeroScrolllBean beanx : list) {
            switch (beanx.getId()) {
                case 36://黄金李白
                    beanx.setHero_img_background(R.drawable.hero_gold_lb);
                    break;
                case 37://黄金韩信
                    beanx.setHero_img_background(R.drawable.hero_gold_hx);
                    break;
                case 38://黄金孙尚香
                    beanx.setHero_img_background(R.drawable.hero_gold_ssx);
                    break;
                case 39://黄金伽罗
                    beanx.setHero_img_background(R.drawable.hero_gold_jl);
                    break;
                case 40://黄金蔡文姬
                    beanx.setHero_img_background(R.drawable.hero_gold_cyj);
                    break;
                case 41://黄金大乔
                    beanx.setHero_img_background(R.drawable.hero_gold_dq);
                    break;
                case 42://黄金凯
                    beanx.setHero_img_background(R.drawable.hero_gold_kai);
                    break;
                case 43://黄金妲己
                    beanx.setHero_img_background(R.drawable.hero_gold_dj);
                    break;
                case 44://黄金王昭君
                    beanx.setHero_img_background(R.drawable.hero_gold_wsj);
                    break;
                case 45://黄金曹操
                    beanx.setHero_img_background(R.drawable.hero_gold_cc);
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
            switch (id) {
                case 36://黄金李白
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_lb_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 37://黄金韩信
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_hx_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 38://黄金孙尚香
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_ssx_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 39://黄金伽罗
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_jialuo_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 40://黄金蔡文姬
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_cyj_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 41://黄金大乔
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_dq_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 42://黄金凯
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_kai_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 43://黄金妲己
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_dj_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));

                    break;
                case 44://黄金王昭君
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_wsj_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                case 45://黄金曹操
                    ta = BaseApplication.getAppContext().getResources().obtainTypedArray(R.array.hero_cc_gold);
                    bean.setHero_img_background(ta.getResourceId(list.indexOf(bean), 0));
                    break;
                default:
                    break;
            }
        }

    }
}
