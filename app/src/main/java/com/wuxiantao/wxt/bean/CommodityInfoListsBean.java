package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoListsBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午5:49
 * Description:${DESCRIPTION} 自营商品规格
 */
public class CommodityInfoListsBean {


    /**
     * name : 红
     * check : true
     * group : 颜色
     * span : 4
     * show : true
     * key : 颜色:红;尺码:39
     * express : 1
     * virtual : 0
     * market : 0.00
     * selling : 0.00
     * msort : 0
     * status : true
     */

    private String name;
    private boolean check;
    private String group;
    private int span;
    private boolean show;
    private String key;
    private int express;
    private String virtual;
    private String market;
    private String selling;
    private String msort;
    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
    }

    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSelling() {
        return selling;
    }

    public void setSelling(String selling) {
        this.selling = selling;
    }

    public String getMsort() {
        return msort;
    }

    public void setMsort(String msort) {
        this.msort = msort;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
