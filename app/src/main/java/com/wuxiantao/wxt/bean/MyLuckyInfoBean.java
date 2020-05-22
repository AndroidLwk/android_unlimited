package com.wuxiantao.wxt.bean;

public class MyLuckyInfoBean {

    /**
     * lucky_value : 15.00
     * rand : 2
     */

    private String lucky_value;
    private int rand;

    public int getIs_full() {
        return is_full;
    }

    public void setIs_full(int is_full) {
        this.is_full = is_full;
    }

    private int is_full;

    public String getLucky_value() {
        return lucky_value;
    }

    public void setLucky_value(String lucky_value) {
        this.lucky_value = lucky_value;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }
}
