package com.wuxiantao.wxt.bean;

public class MyMoneyCashBean {

    /**
     * money : 2493.40
     * cash : 0
     * up_nickname : 暂无师傅
     * is_vip : 0
     * qun_img : http://super-tao.oss-cn-beijing.aliyuncs.com/0670e9668abcba1b/fe7e747f914009ac.jpg
     * unopen_card : 2
     * total_use : 89
     * total_yesterday : 2
     */

    private String money;
    private int cash;
    private String up_nickname;
    private int is_vip;
    private String qun_img;
    private int unopen_card;
    private int total_use;
    private int total_yesterday;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getUp_nickname() {
        return up_nickname;
    }

    public void setUp_nickname(String up_nickname) {
        this.up_nickname = up_nickname;
    }

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

    public String getQun_img() {
        return qun_img;
    }

    public void setQun_img(String qun_img) {
        this.qun_img = qun_img;
    }

    public int getUnopen_card() {
        return unopen_card;
    }

    public void setUnopen_card(int unopen_card) {
        this.unopen_card = unopen_card;
    }

    public int getTotal_use() {
        return total_use;
    }

    public void setTotal_use(int total_use) {
        this.total_use = total_use;
    }

    public int getTotal_yesterday() {
        return total_yesterday;
    }

    public void setTotal_yesterday(int total_yesterday) {
        this.total_yesterday = total_yesterday;
    }
}
