package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 下午3:29
 * Description:${DESCRIPTION}
 */
public class RedBagInfoBean {


    /**
     * list : {"amount":4.646,"make_starttime":0,"make_endtime":0,"make_total":0,"make_per":0,"send_time":"0","deposit_amount":"324.037","store":80640,"is_vip":1,"money":0,"doubleStatus":0,"new_url":""}
     */

    private ListBean list;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * amount : 4.646
         * make_starttime : 0
         * make_endtime : 0
         * make_total : 0
         * make_per : 0
         * send_time : 0
         * deposit_amount : 324.037
         * store : 80640
         * is_vip : 1
         * money : 0
         * doubleStatus : 0
         * new_url :
         */

        private double amount;
        private int make_starttime;
        private int make_endtime;
        private int make_total;
        private int make_per;
        private String send_time;
        private String deposit_amount;
        private int store;
        private int is_vip;
        private int money;
        private int doubleStatus;
        private String new_url;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getMake_starttime() {
            return make_starttime;
        }

        public void setMake_starttime(int make_starttime) {
            this.make_starttime = make_starttime;
        }

        public int getMake_endtime() {
            return make_endtime;
        }

        public void setMake_endtime(int make_endtime) {
            this.make_endtime = make_endtime;
        }

        public int getMake_total() {
            return make_total;
        }

        public void setMake_total(int make_total) {
            this.make_total = make_total;
        }

        public int getMake_per() {
            return make_per;
        }

        public void setMake_per(int make_per) {
            this.make_per = make_per;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public String getDeposit_amount() {
            return deposit_amount;
        }

        public void setDeposit_amount(String deposit_amount) {
            this.deposit_amount = deposit_amount;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getDoubleStatus() {
            return doubleStatus;
        }

        public void setDoubleStatus(int doubleStatus) {
            this.doubleStatus = doubleStatus;
        }

        public String getNew_url() {
            return new_url;
        }

        public void setNew_url(String new_url) {
            this.new_url = new_url;
        }
    }
}
