package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomeBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午3:51
 * Description:${DESCRIPTION}
 */
public class MyIncomeBean {

    /**
     * count : 1
     * list : [{"id":1,"user_id":4,"number":"20.0000","total":"30.6000","time":1573700499}]
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
         * id : 1
         * user_id : 4
         * number : 20.0000
         * total : 30.6000
         * time : 1573700499
         */

        private int id;
        private int user_id;
        private String number;
        private String total;
        private int time;

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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
