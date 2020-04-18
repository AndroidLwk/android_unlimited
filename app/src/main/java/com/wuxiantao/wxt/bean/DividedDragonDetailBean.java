package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividedDragonDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:42
 * Description:${DESCRIPTION}
 */
public class DividedDragonDetailBean {


    /**
     * ad_money_yesterday : 1
     * ad_money_historey : 1
     * yesterday_pre : 10
     * total : 100
     * total_already : 10
     * total_reset : 90
     * time : 12:00
     */

    private double ad_money_yesterday;
    private double ad_money_historey;
    private double yesterday_pre;
    private double total;
    private double total_already;
    private double total_reset;
    private String time;

    public double getAd_money_yesterday() {
        return ad_money_yesterday;
    }

    public void setAd_money_yesterday(double ad_money_yesterday) {
        this.ad_money_yesterday = ad_money_yesterday;
    }

    public double getAd_money_historey() {
        return ad_money_historey;
    }

    public void setAd_money_historey(double ad_money_historey) {
        this.ad_money_historey = ad_money_historey;
    }

    public double getYesterday_pre() {
        return yesterday_pre;
    }

    public void setYesterday_pre(double yesterday_pre) {
        this.yesterday_pre = yesterday_pre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_already() {
        return total_already;
    }

    public void setTotal_already(double total_already) {
        this.total_already = total_already;
    }

    public double getTotal_reset() {
        return total_reset;
    }

    public void setTotal_reset(double total_reset) {
        this.total_reset = total_reset;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
