package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyBoxInfo {


    /**
     * is_full : 0
     * box_volume : 30
     * list : [{"id":123,"card_id":226,"name":"刮刮卡","number":1,"pid":0,"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/e55406d67a2a93b2/49fe8ac79a5e599c.png","is_suipian":0,"is_send":1,"is_can_use":0,"rate":"0.10","is_discard":1}]
     * count : 1
     */

    private int is_full;
    private int box_volume;
    private int count;
    private List<ListBean> list;

    public int getIs_full() {
        return is_full;
    }

    public void setIs_full(int is_full) {
        this.is_full = is_full;
    }

    public int getBox_volume() {
        return box_volume;
    }

    public void setBox_volume(int box_volume) {
        this.box_volume = box_volume;
    }

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
         * id : 123
         * card_id : 226
         * name : 刮刮卡
         * number : 1
         * pid : 0
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/e55406d67a2a93b2/49fe8ac79a5e599c.png
         * is_suipian : 0
         * is_send : 1
         * is_can_use : 0
         * rate : 0.10
         * is_discard : 1
         */

        private int id;
        private int card_id;
        private String name;
        private int number;
        private int pid;
        private String img;
        private int is_suipian;
        private int is_send;
        private int is_can_use;
        private String rate;
        private int is_discard;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIs_suipian() {
            return is_suipian;
        }

        public void setIs_suipian(int is_suipian) {
            this.is_suipian = is_suipian;
        }

        public int getIs_send() {
            return is_send;
        }

        public void setIs_send(int is_send) {
            this.is_send = is_send;
        }

        public int getIs_can_use() {
            return is_can_use;
        }

        public void setIs_can_use(int is_can_use) {
            this.is_can_use = is_can_use;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public int getIs_discard() {
            return is_discard;
        }

        public void setIs_discard(int is_discard) {
            this.is_discard = is_discard;
        }
    }
}
