package com.wuxiantao.wxt.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaskInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午3:01
 * Description:${DESCRIPTION} 我的任务
 */
public class TaskInfoBean {


    /**
     * task_full : 20
     * card_num : 38
     * task_num : 15
     * task_star : 1
     * is_sign : 1
     * is_order : 0
     * long : 4654
     * ad_num : 3
     * is_focus : 0
     * friends_num : 0
     * is_wx : 0
     * is_phone : 1
     * award_one : 1
     * award_two : 2
     * award_three : 5
     * award_four : 1
     * award_five : 3
     */

    private int task_full;
    private int card_num;
    private int task_num;
    private int task_star;
    private int is_sign;
    private int is_order;
    @SerializedName("long")
    private int longX;
    private int ad_num;
    private int is_focus;
    private int friends_num;
    private int is_wx;
    private int is_phone;
    private int award_one;
    private int award_two;
    private int award_three;
    private int award_four;
    private int award_five;

    public int getIs_wechat() {
        return is_wechat;
    }

    public void setIs_wechat(int is_wechat) {
        this.is_wechat = is_wechat;
    }

    private int is_wechat;

    public int getTask_full() {
        return task_full;
    }

    public void setTask_full(int task_full) {
        this.task_full = task_full;
    }

    public int getCard_num() {
        return card_num;
    }

    public void setCard_num(int card_num) {
        this.card_num = card_num;
    }

    public int getTask_num() {
        return task_num;
    }

    public void setTask_num(int task_num) {
        this.task_num = task_num;
    }

    public int getTask_star() {
        return task_star;
    }

    public void setTask_star(int task_star) {
        this.task_star = task_star;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getIs_order() {
        return is_order;
    }

    public void setIs_order(int is_order) {
        this.is_order = is_order;
    }

    public int getLongX() {
        return longX;
    }

    public void setLongX(int longX) {
        this.longX = longX;
    }

    public int getAd_num() {
        return ad_num;
    }

    public void setAd_num(int ad_num) {
        this.ad_num = ad_num;
    }

    public int getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(int is_focus) {
        this.is_focus = is_focus;
    }

    public int getFriends_num() {
        return friends_num;
    }

    public void setFriends_num(int friends_num) {
        this.friends_num = friends_num;
    }

    public int getIs_wx() {
        return is_wx;
    }

    public void setIs_wx(int is_wx) {
        this.is_wx = is_wx;
    }

    public int getIs_phone() {
        return is_phone;
    }

    public void setIs_phone(int is_phone) {
        this.is_phone = is_phone;
    }

    public int getAward_one() {
        return award_one;
    }

    public void setAward_one(int award_one) {
        this.award_one = award_one;
    }

    public int getAward_two() {
        return award_two;
    }

    public void setAward_two(int award_two) {
        this.award_two = award_two;
    }

    public int getAward_three() {
        return award_three;
    }

    public void setAward_three(int award_three) {
        this.award_three = award_three;
    }

    public int getAward_four() {
        return award_four;
    }

    public void setAward_four(int award_four) {
        this.award_four = award_four;
    }

    public int getAward_five() {
        return award_five;
    }

    public void setAward_five(int award_five) {
        this.award_five = award_five;
    }
}
