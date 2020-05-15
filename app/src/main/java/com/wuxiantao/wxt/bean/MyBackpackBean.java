package com.wuxiantao.wxt.bean;

public class MyBackpackBean {
    public MyBackpackBean() {
    }

    public MyBackpackBean(int img_id, int num, int isHaveImg, String heroName) {
        this.img_id = img_id;
        this.num = num;
        this.isHaveImg = isHaveImg;
        this.heroName = heroName;
    }

    private int img_id;
    private int num;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    private int pid;//1英雄碎片 2皮肤3现金

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getIsHaveImg() {
        return isHaveImg;
    }

    public void setIsHaveImg(int isHaveImg) {
        this.isHaveImg = isHaveImg;
    }

    private int isHaveImg;//1：有 0:无

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    private String heroName;
}
