package com.wuxiantao.wxt.bean;

public class StartStrapingBean {

    /**
     * code : 200
     * msg : ok
     * data : {"card_id":108,"card_img":"http://super-tao.oss-cn-beijing.aliyuncs.com/44c0062c94d39070/573a99d2a1c40f31.png","name":"青铜凯碎片9","msg":"成功刮出青铜凯碎片9"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * card_id : 108
         * card_img : http://super-tao.oss-cn-beijing.aliyuncs.com/44c0062c94d39070/573a99d2a1c40f31.png
         * name : 青铜凯碎片9
         * msg : 成功刮出青铜凯碎片9
         */

        private int card_id;
        private String card_img;
        private String name;
        private String msg;

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public String getCard_img() {
            return card_img;
        }

        public void setCard_img(String card_img) {
            this.card_img = card_img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
