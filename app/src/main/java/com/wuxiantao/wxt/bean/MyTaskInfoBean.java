package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyTaskInfoBean {

    /**
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/8348257e14bfc263/8a7f5cfa1506548d.png"}]
     * active_people : 0
     * is_one : 0
     * is_two : 0
     * is_three : 0
     * is_four : 0
     * is_five : 0
     * online_thirty : 0
     * online_five : 1
     * game_charge : 0
     * product_charge : 0
     * is_sign : 0
     * ad_click : 0
     * boss : 0
     * product_see : 0
     * video : 0
     * husao : 0
     */

    private int active_people;
    private int is_one;
    private int is_two;
    private int is_three;
    private int is_four;
    private int is_five;
    private int online_thirty;
    private int online_five;
    private int game_charge;
    private int product_charge;
    private int is_sign;
    private int ad_click;
    private int boss;
    private int product_see;
    private int video;
    private int husao;
    private List<BannerBean> banner;

    public int getActive_people() {
        return active_people;
    }

    public void setActive_people(int active_people) {
        this.active_people = active_people;
    }

    public int getIs_one() {
        return is_one;
    }

    public void setIs_one(int is_one) {
        this.is_one = is_one;
    }

    public int getIs_two() {
        return is_two;
    }

    public void setIs_two(int is_two) {
        this.is_two = is_two;
    }

    public int getIs_three() {
        return is_three;
    }

    public void setIs_three(int is_three) {
        this.is_three = is_three;
    }

    public int getIs_four() {
        return is_four;
    }

    public void setIs_four(int is_four) {
        this.is_four = is_four;
    }

    public int getIs_five() {
        return is_five;
    }

    public void setIs_five(int is_five) {
        this.is_five = is_five;
    }

    public int getOnline_thirty() {
        return online_thirty;
    }

    public void setOnline_thirty(int online_thirty) {
        this.online_thirty = online_thirty;
    }

    public int getOnline_five() {
        return online_five;
    }

    public void setOnline_five(int online_five) {
        this.online_five = online_five;
    }

    public int getGame_charge() {
        return game_charge;
    }

    public void setGame_charge(int game_charge) {
        this.game_charge = game_charge;
    }

    public int getProduct_charge() {
        return product_charge;
    }

    public void setProduct_charge(int product_charge) {
        this.product_charge = product_charge;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getAd_click() {
        return ad_click;
    }

    public void setAd_click(int ad_click) {
        this.ad_click = ad_click;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    public int getProduct_see() {
        return product_see;
    }

    public void setProduct_see(int product_see) {
        this.product_see = product_see;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getHusao() {
        return husao;
    }

    public void setHusao(int husao) {
        this.husao = husao;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/8348257e14bfc263/8a7f5cfa1506548d.png
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
