package com.wuxiantao.wxt.adapter.bean;

public class HeroScrolllBean {
    public HeroScrolllBean() {
    }

    public HeroScrolllBean(int hero_img_background, int hero_num, int isHave, int id) {
        this.hero_img_background = hero_img_background;
        this.hero_num = hero_num;
        this.isHave = isHave;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getHero_img_background() {
        return hero_img_background;
    }

    public void setHero_img_background(int hero_img_background) {
        this.hero_img_background = hero_img_background;
    }

    public int getHero_num() {
        return hero_num;
    }

    public void setHero_num(int hero_num) {
        this.hero_num = hero_num;
    }

    public int getIsHave() {
        return isHave;
    }

    public void setIsHave(int isHave) {
        this.isHave = isHave;
    }

    private int hero_img_background;

    private int hero_num;
    private int isHave;//拥有状态
}
