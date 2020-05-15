package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyBoxInfo {

    /**
     * is_full : 1
     * list : [{"id":2,"user_id":49,"name":"青铜孙尚香碎片2","number":2,"card_id":65,"pid":4},{"id":3,"user_id":49,"name":"青铜妲己碎片4","number":1,"card_id":112,"pid":4},{"id":4,"user_id":49,"name":"青铜妲己碎片9","number":4,"card_id":117,"pid":4},{"id":5,"user_id":49,"name":"嫦娥伴生皮肤卡","number":3,"card_id":14,"pid":2},{"id":6,"user_id":49,"name":"青铜伽罗碎片9","number":3,"card_id":81,"pid":4},{"id":8,"user_id":49,"name":"黄金妲己碎片7","number":4,"card_id":160,"pid":4},{"id":9,"user_id":49,"name":"黄金妲己碎片8","number":3,"card_id":161,"pid":4},{"id":10,"user_id":49,"name":"青铜凯碎片9","number":3,"card_id":108,"pid":4},{"id":11,"user_id":49,"name":"青铜瑶碎片6","number":2,"card_id":96,"pid":4},{"id":12,"user_id":49,"name":"青铜妲己碎片3","number":5,"card_id":111,"pid":4},{"id":13,"user_id":49,"name":"青铜司马懿碎片3","number":2,"card_id":129,"pid":4},{"id":15,"user_id":49,"name":"曹操幽灵船长皮肤卡","number":4,"card_id":17,"pid":2},{"id":16,"user_id":49,"name":"青铜瑶碎片1","number":5,"card_id":91,"pid":4},{"id":17,"user_id":49,"name":"青铜伽罗碎片7","number":2,"card_id":79,"pid":4},{"id":18,"user_id":49,"name":"青铜王昭君碎片9","number":5,"card_id":126,"pid":4},{"id":19,"user_id":49,"name":"黄金瑶碎片9","number":1,"card_id":180,"pid":4},{"id":20,"user_id":49,"name":"青铜韩信","number":5,"card_id":27,"pid":1},{"id":21,"user_id":49,"name":"黄金伽罗碎片1","number":3,"card_id":190,"pid":4},{"id":23,"user_id":49,"name":"青铜蔡文姬碎片8","number":2,"card_id":89,"pid":4},{"id":24,"user_id":49,"name":"青铜孙尚香碎片9","number":3,"card_id":72,"pid":4},{"id":25,"user_id":49,"name":"青铜凯碎片1","number":5,"card_id":100,"pid":4},{"id":31,"user_id":49,"name":"青铜李白碎片3","number":5,"card_id":48,"pid":4},{"id":34,"user_id":49,"name":"青铜李白碎片6","number":2,"card_id":51,"pid":4},{"id":41,"user_id":49,"name":"青铜李白碎片3","number":5,"card_id":48,"pid":4},{"id":44,"user_id":49,"name":"青铜李白","number":5,"card_id":26,"pid":1},{"id":48,"user_id":49,"name":"青铜李白","number":2,"card_id":26,"pid":1},{"id":107,"user_id":49,"name":"青铜孙尚香碎片1","number":1,"card_id":64,"pid":4},{"id":109,"user_id":49,"name":"5元现金卡","number":1,"card_id":5,"pid":3},{"id":110,"user_id":49,"name":"10元现金卡","number":2,"card_id":6,"pid":3},{"id":114,"user_id":49,"name":"刮刮卡","number":1,"card_id":226,"pid":5}]
     * count : 30
     * ggk_rate : 0.1
     * gold_rate : 0.1
     * copper_rate : 0.05
     */

    private int is_full;
    private int count;
    private String ggk_rate;
    private String gold_rate;
    private String copper_rate;
    private List<ListBean> list;

    public int getIs_full() {
        return is_full;
    }

    public void setIs_full(int is_full) {
        this.is_full = is_full;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGgk_rate() {
        return ggk_rate;
    }

    public void setGgk_rate(String ggk_rate) {
        this.ggk_rate = ggk_rate;
    }

    public String getGold_rate() {
        return gold_rate;
    }

    public void setGold_rate(String gold_rate) {
        this.gold_rate = gold_rate;
    }

    public String getCopper_rate() {
        return copper_rate;
    }

    public void setCopper_rate(String copper_rate) {
        this.copper_rate = copper_rate;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2
         * user_id : 49
         * name : 青铜孙尚香碎片2
         * number : 2
         * card_id : 65
         * pid : 4
         */

        private int id;
        private int user_id;
        private String name;
        private int number;
        private int card_id;
        private int pid;

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

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }
    }
}
