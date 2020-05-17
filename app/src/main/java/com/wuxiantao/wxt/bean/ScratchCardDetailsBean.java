package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/17--17:44
 * Description: 刮刮卡实体类
 * Author: lht
 */
public class ScratchCardDetailsBean {

    /**
     * list : [{"id":6675854,"user_id":49,"types":2000,"num":"-1","msg":"刮卡消耗","time":"2020-05-17 15:39:48"},{"id":6669028,"user_id":49,"types":2000,"num":"-1","msg":"刮卡消耗","time":"2020-05-16 17:44:50"},{"id":6669015,"user_id":49,"types":2000,"num":"-1","msg":"刮卡消耗","time":"2020-05-16 17:44:13"}]
     * count : 19
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
         * id : 6675854
         * user_id : 49
         * types : 2000
         * num : -1
         * msg : 刮卡消耗
         * time : 2020-05-17 15:39:48
         */

        private int id;
        private int user_id;
        private int types;
        private String num;
        private String msg;
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

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
