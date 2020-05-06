package com.wuxiantao.wxt.adapter.bean;

public class MyMemberBean {
    public MyMemberBean(int img_res, String bigTitle, String smallTitle) {
        this.img_res = img_res;
        this.bigTitle = bigTitle;
        this.smallTitle = smallTitle;
    }

    private int img_res;

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    private String bigTitle;
    private String smallTitle;
}
