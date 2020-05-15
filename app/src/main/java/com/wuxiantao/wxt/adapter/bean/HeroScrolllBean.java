package com.wuxiantao.wxt.adapter.bean;

import java.util.List;

public class HeroScrolllBean {

    public int getIsHave() {
        return isHave;
    }

    public void setIsHave(int isHave) {
        this.isHave = isHave;
    }

    private int isHave;//拥有状态
    /**
     * id : 26
     * name : 青铜李白
     * total : 7
     * img : http://super-tao.oss-cn-beijing.aliyuncs.com/219ee1cd88328a18/f7a9cbe28dad8a2d.png
     * child : [{"id":46,"name":"青铜李白碎片1","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/bcafc5fdf03c7e2c/b6d7ca9971e0c643.png"},{"id":47,"name":"青铜李白碎片2","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/1042fbf37ff865a7/959c8296489c1873.png"},{"id":48,"name":"青铜李白碎片3","total":"10","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/e09188cac0175c93/a1aa5842a3cb1a2f.png"},{"id":49,"name":"青铜李白碎片4","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/235d1a5b5c5a3c1c/4ccd59960328f233.png"},{"id":50,"name":"青铜李白碎片5","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/8e6999ae341b77fc/62920595aa9cd383.png"},{"id":51,"name":"青铜李白碎片6","total":"2","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/bdfd33f1456b3f04/c68faecbdf63202d.png"},{"id":52,"name":"青铜李白碎片7","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/aa6f64f4ff5c0c2d/5d4e0388c2ae9fcb.png"},{"id":53,"name":"青铜李白碎片8","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/57e02daf0f9df249/7a5e57cf89c30a0c.png"},{"id":54,"name":"青铜李白碎片9","total":"0","img":"http://super-tao.oss-cn-beijing.aliyuncs.com/4a5c73390a0438a9/0a9a9ef4f87948ba.png"}]
     */

    private int id;
    private String name;
    private String total;
    private String img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/bcafc5fdf03c7e2c/b6d7ca9971e0c643.png
         */

        private int id;
        private String name;
        private String total;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
