package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaobaoLatelyOrderBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-29 下午6:40
 * Description:${DESCRIPTION}
 */
public class RecentOrderBean extends BaseExampleBean {

    public static final int RECENT_ORDER_TAOBAO = 0;
    public static final int RECENT_ORDER_TMALL = 1;
    public static final int RECENT_ORDER_YOUXUAN = 2;

    public RecentOrderBean(){}

    private String url;
    private String title;
    private String money;
    private int id;
    private int orderType;
    private String rate_money;
    private int vip_level;

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    private int orderStatus;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getRate_money() {
        return rate_money;
    }

    public void setRate_money(String rate_money) {
        this.rate_money = rate_money;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }




}
