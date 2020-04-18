package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DailyTaskBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午5:54
 * Description:${DESCRIPTION}
 */
public class DailyTaskBean extends MyTaskBean {

    private boolean isCheckIn;
    private boolean isOrder;
    private int friends_num;
    private int ad_num;
    private String online;

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean order) {
        isOrder = order;
    }

    public int getFriends_num() {
        return friends_num;
    }

    public void setFriends_num(int friends_num) {
        this.friends_num = friends_num;
    }

    public int getAd_num() {
        return ad_num;
    }

    public void setAd_num(int ad_num) {
        this.ad_num = ad_num;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

}
