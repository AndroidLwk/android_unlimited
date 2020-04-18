package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BalanceBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午12:11
 * Description:${DESCRIPTION}
 */
public class BalanceBean {


    /**
     * subscribe : 0
     * is_wx : 0
     * is_ali : 1
     * amount : 25.166701
     * volumes : 0.000000
     * total : 25.166701
     */

    private int subscribe;
    private int is_wx;
    private int is_ali;
    private String amount;
    private String volumes;
    private double total;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public int getIs_wx() {
        return is_wx;
    }

    public void setIs_wx(int is_wx) {
        this.is_wx = is_wx;
    }

    public int getIs_ali() {
        return is_ali;
    }

    public void setIs_ali(int is_ali) {
        this.is_ali = is_ali;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
