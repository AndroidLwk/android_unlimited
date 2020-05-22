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
     * game_status_all : 0
     * rate : 0.05
     * game_fenhong_all : 215.8
     * game_fenhong_me : 0
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/4584255e1e164cc0/1947964c97cbf26f.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/9fb45d455ff7133e/337f6c18fd6f8e64.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/60d2520e6f10b1e7/f2a5767499b23e24.png"}]
     * info : {"tiyan_money":"0.00","tiyan_level":0,"tiyan_starttime":0,"tiyan_endtime":0,"num":0,"cha_next":27,"tiyan_count":4,"tiyan_total":9,"tiyan_today_me":9}
     */

    private int qu_id;
    private String qu_name;
    private int level;
    private int cha;
    private String actorname;
    private String headimg;
    private int game_status;
    private int game_status_all;
    private String rate;
    private double game_fenhong_all;
    private int game_fenhong_me;
    private InfoBean info;
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

    public int getGame_status_all() {
        return game_status_all;
    }

    public void setGame_status_all(int game_status_all) {
        this.game_status_all = game_status_all;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getGame_fenhong_all() {
        return game_fenhong_all;
    }

    public void setGame_fenhong_all(double game_fenhong_all) {
        this.game_fenhong_all = game_fenhong_all;
    }

    public int getGame_fenhong_me() {
        return game_fenhong_me;
    }

    public void setGame_fenhong_me(int game_fenhong_me) {
        this.game_fenhong_me = game_fenhong_me;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class InfoBean {
        /**
         * tiyan_money : 0.00
         * tiyan_level : 0
         * tiyan_starttime : 0
         * tiyan_endtime : 0
         * num : 0
         * cha_next : 27
         * tiyan_count : 4
         * tiyan_total : 9
         * tiyan_today_me : 9
         */

        private String tiyan_money;
        private int tiyan_level;
        private int tiyan_starttime;
        private int tiyan_endtime;
        private int num;
        private int cha_next;
        private int tiyan_count;
        private int tiyan_total;
        private int tiyan_today_me;

        public String getTiyan_money() {
            return tiyan_money;
        }

        public void setTiyan_money(String tiyan_money) {
            this.tiyan_money = tiyan_money;
        }

        public int getTiyan_level() {
            return tiyan_level;
        }

        public void setTiyan_level(int tiyan_level) {
            this.tiyan_level = tiyan_level;
        }

        public int getTiyan_starttime() {
            return tiyan_starttime;
        }

        public void setTiyan_starttime(int tiyan_starttime) {
            this.tiyan_starttime = tiyan_starttime;
        }

        public int getTiyan_endtime() {
            return tiyan_endtime;
        }

        public void setTiyan_endtime(int tiyan_endtime) {
            this.tiyan_endtime = tiyan_endtime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getCha_next() {
            return cha_next;
        }

        public void setCha_next(int cha_next) {
            this.cha_next = cha_next;
        }

        public int getTiyan_count() {
            return tiyan_count;
        }

        public void setTiyan_count(int tiyan_count) {
            this.tiyan_count = tiyan_count;
        }

        public int getTiyan_total() {
            return tiyan_total;
        }

        public void setTiyan_total(int tiyan_total) {
            this.tiyan_total = tiyan_total;
        }

        public int getTiyan_today_me() {
            return tiyan_today_me;
        }

        public void setTiyan_today_me(int tiyan_today_me) {
            this.tiyan_today_me = tiyan_today_me;
        }
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
