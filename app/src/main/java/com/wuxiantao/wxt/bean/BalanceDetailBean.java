package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BalanceDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午8:47
 * Description:${DESCRIPTION}
 */
public class BalanceDetailBean {

    /**
     * list : [{"id":23,"user_id":49,"msg":"余额购买背包容量","money":"-1","total":"2488.40","time":"2020-05-17 10:43:56"},{"id":22,"user_id":49,"msg":"余额购买背包容量","money":"-1","total":"2489.40","time":"2020-05-17 10:23:55"},{"id":21,"user_id":49,"msg":"余额购买背包容量","money":"-1","total":"2490.40","time":"2020-05-17 10:00:38"}]
     * count : 17
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 23
         * user_id : 49
         * msg : 余额购买背包容量
         * money : -1
         * total : 2488.40
         * time : 2020-05-17 10:43:56
         */

        private int id;
        private int user_id;
        private String msg;
        private String money;
        private String total;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
