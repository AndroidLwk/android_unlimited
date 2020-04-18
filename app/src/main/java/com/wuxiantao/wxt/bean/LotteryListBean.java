package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryListBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:25
 * Description:${DESCRIPTION} 抽奖列表
 */
public class LotteryListBean {


    /**
     * list : [{"id":20,"title":"现金红包3元","number":"3.00","wid":1},{"id":21,"title":"现金红包5元","number":"5.00","wid":1},{"id":22,"title":"现金红包15元","number":"15.00","wid":1},{"id":23,"title":"金钥匙X1","number":"1.00","wid":2},{"id":24,"title":"银钥匙X1","number":"1.00","wid":3},{"id":25,"title":"铜钥匙X1","number":"1.00","wid":4},{"id":26,"title":"铜钥匙X2","number":"2.00","wid":4},{"id":27,"title":"抽奖券X1","number":"1.00","wid":5}]
     * active_key : 0.03
     * silver_key_num_surplus : 3
     * silver_key_num : 3
     * gold_key_num : 0
     * gold_key_num_surplus : 0
     * lucky_num : 0.00
     */

    private String active_key;
    private int silver_key_num_surplus;
    private int silver_key_num;
    private int gold_key_num;
    private int gold_key_num_surplus;
    private String lucky_num;
    private List<ListBean> list;

    public String getActive_key() {
        return active_key;
    }

    public void setActive_key(String active_key) {
        this.active_key = active_key;
    }

    public int getSilver_key_num_surplus() {
        return silver_key_num_surplus;
    }

    public void setSilver_key_num_surplus(int silver_key_num_surplus) {
        this.silver_key_num_surplus = silver_key_num_surplus;
    }

    public int getSilver_key_num() {
        return silver_key_num;
    }

    public void setSilver_key_num(int silver_key_num) {
        this.silver_key_num = silver_key_num;
    }

    public int getGold_key_num() {
        return gold_key_num;
    }

    public void setGold_key_num(int gold_key_num) {
        this.gold_key_num = gold_key_num;
    }

    public int getGold_key_num_surplus() {
        return gold_key_num_surplus;
    }

    public void setGold_key_num_surplus(int gold_key_num_surplus) {
        this.gold_key_num_surplus = gold_key_num_surplus;
    }

    public String getLucky_num() {
        return lucky_num;
    }

    public void setLucky_num(String lucky_num) {
        this.lucky_num = lucky_num;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 20
         * title : 现金红包3元
         * number : 3.00
         * wid : 1
         */

        private int id;
        private String title;
        private String number;
        private int wid;

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
    }
}
