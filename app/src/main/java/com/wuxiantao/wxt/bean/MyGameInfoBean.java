package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/18--10:49
 * Description: 斩妖之旅
 * Author: lht
 */
public class MyGameInfoBean {

    /**
     * qu_id : 10
     * qu_name : 仙侠10区
     * level : 73
     * cha : 77
     * actorname : 天枢儒罗
     * headimg : http://super-tao.oss-cn-beijing.aliyuncs.com/uploads/20200324/e3a511f908cfba923a050ef6d4065c6c.jpg
     * game_status : 0
     * money_yesterday : 0
     * money_total : 0
     * money_day : 0
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/4584255e1e164cc0/1947964c97cbf26f.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/9fb45d455ff7133e/337f6c18fd6f8e64.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/60d2520e6f10b1e7/f2a5767499b23e24.png"}]
     */

    private int qu_id;
    private String qu_name;
    private int level;
    private int cha;
    private String actorname;
    private String headimg;
    private int game_status;
    private int money_yesterday;
    private int money_total;
    private int money_day;
    private List<BannerBean> banner;

    public int getQu_id() {
        return qu_id;
    }

    public void setQu_id(int qu_id) {
        this.qu_id = qu_id;
    }

    public String getQu_name() {
        return qu_name;
    }

    public void setQu_name(String qu_name) {
        this.qu_name = qu_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCha() {
        return cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public String getActorname() {
        return actorname;
    }

    public void setActorname(String actorname) {
        this.actorname = actorname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getGame_status() {
        return game_status;
    }

    public void setGame_status(int game_status) {
        this.game_status = game_status;
    }

    public int getMoney_yesterday() {
        return money_yesterday;
    }

    public void setMoney_yesterday(int money_yesterday) {
        this.money_yesterday = money_yesterday;
    }

    public int getMoney_total() {
        return money_total;
    }

    public void setMoney_total(int money_total) {
        this.money_total = money_total;
    }

    public int getMoney_day() {
        return money_day;
    }

    public void setMoney_day(int money_day) {
        this.money_day = money_day;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/4584255e1e164cc0/1947964c97cbf26f.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
