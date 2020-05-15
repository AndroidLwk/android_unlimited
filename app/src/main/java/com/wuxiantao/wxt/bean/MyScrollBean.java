package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyScrollBean {

    /**
     * id : 26
     * name : 青铜李白
     * total : 0
     * child : [{"id":46,"name":"青铜李白碎片1","total":"0"},{"id":47,"name":"青铜李白碎片2","total":"0"},{"id":48,"name":"青铜李白碎片3","total":"3"},{"id":49,"name":"青铜李白碎片4","total":"0"},{"id":50,"name":"青铜李白碎片5","total":"0"},{"id":51,"name":"青铜李白碎片6","total":"0"},{"id":52,"name":"青铜李白碎片7","total":"0"},{"id":53,"name":"青铜李白碎片8","total":"0"},{"id":54,"name":"青铜李白碎片9","total":"0"}]
     */

    private int id;
    private String name;
    private String total;
    private List<ChildBean> child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public static class ChildBean {
        /**
         * id : 46
         * name : 青铜李白碎片1
         * total : 0
         */

        private int id;
        private String name;
        private String total;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
