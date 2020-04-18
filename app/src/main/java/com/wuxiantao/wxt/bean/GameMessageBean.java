package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GameMessageBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-3 下午8:58
 * Description:${DESCRIPTION}
 */
public class GameMessageBean {


    /**
     * num : 4
     * level : 80
     * cha : 0
     * super_total : 0
     * super_money : 0
     * qu_id : 1
     * qu_name : 仙侠1区
     * info : {"tiyan_money":"0.00","tiyan_level":0,"tiyan_starttime":0,"tiyan_endtime":0}
     * list : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/4584255e1e164cc0/1947964c97cbf26f.png"}]
     */

    private int num;
    private int level;
    private int cha;
    private int super_total;
    private double super_money;
    private int qu_id;
    private String qu_name;
    private InfoBean info;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;
    private List<ListBean> list;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCha() {
        return cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public int getSuper_total() {
        return super_total;
    }

    public void setSuper_total(int super_total) {
        this.super_total = super_total;
    }

    public double getSuper_money() {
        return super_money;
    }

    public void setSuper_money(double super_money) {
        this.super_money = super_money;
    }

    public int getQu_id() {
        return qu_id;
    }

    public void setQu_id(int qu_id) {
        this.qu_id = qu_id;
    }

    public String getQu_name() {
        return qu_name;
    }

    public void setQu_name(String qu_name) {
        this.qu_name = qu_name;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * tiyan_money : 0.00
         * tiyan_level : 0
         * tiyan_starttime : 0
         * tiyan_endtime : 0
         */

        private String tiyan_money;
        private int tiyan_level;
        private int tiyan_starttime;
        private int tiyan_endtime;

        public String getTiyan_money() {
            return tiyan_money;
        }

        public void setTiyan_money(String tiyan_money) {
            this.tiyan_money = tiyan_money;
        }

        public int getTiyan_level() {
            return tiyan_level;
        }

        public void setTiyan_level(int tiyan_level) {
            this.tiyan_level = tiyan_level;
        }

        public int getTiyan_starttime() {
            return tiyan_starttime;
        }

        public void setTiyan_starttime(int tiyan_starttime) {
            this.tiyan_starttime = tiyan_starttime;
        }

        public int getTiyan_endtime() {
            return tiyan_endtime;
        }

        public void setTiyan_endtime(int tiyan_endtime) {
            this.tiyan_endtime = tiyan_endtime;
        }
    }

    public static class ListBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/4584255e1e164cc0/1947964c97cbf26f.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
