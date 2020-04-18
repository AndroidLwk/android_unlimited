package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:32
 * Description:${DESCRIPTION}
 */
public class LotteryBean {


    /**
     * id : 26
     * title : 铜钥匙X2
     * number : 2.00
     * wid : 4
     * active_key : 0.52
     */

    private int id;
    private String title;
    private String number;
    private int wid;
    private double active_key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public double getActive_key() {
        return active_key;
    }

    public void setActive_key(double active_key) {
        this.active_key = active_key;
    }
}
