package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiDirectlyBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午7:20
 * Description:${DESCRIPTION} 直属粉丝
 */
public class FansiDirectlyBean {

    /**
     * total : 0
     * total_zhitui : 0
     * total_jianjie : 0
     * list : [{"id":84,"headimg":"","nickname":"朱古力👿","level":148,"gongxian":0,"is_task":0},{"id":58,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epPkRJOceZ3wRF9uhice7BfRZVqPXDD1umVXubMHL1tiaFu6gobJgm7nBEGaibbz0YGtib9KQzXIb9tvw/132","nickname":"🐲 领航晓瀚 🐲","level":93,"gongxian":0,"is_task":0},{"id":34981,"headimg":"https://wx.qlogo.cn/mmopen/vi_32/EyQxj5PiczTgQ0VOqnpcemsX73iaiady6ojfO6T9P8XLHxhFENicqOZJbLibKtXmCB5l3jbicibFbgg4d97pic1PnO3p1Q/132","nickname":"最初的梦","level":85,"gongxian":0,"is_task":0}]
     * count : 9
     */

    private int total;
    private int total_zhitui;
    private int total_jianjie;
    private int count;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_zhitui() {
        return total_zhitui;
    }

    public void setTotal_zhitui(int total_zhitui) {
        this.total_zhitui = total_zhitui;
    }

    public int getTotal_jianjie() {
        return total_jianjie;
    }

    public void setTotal_jianjie(int total_jianjie) {
        this.total_jianjie = total_jianjie;
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
         * id : 84
         * headimg :
         * nickname : 朱古力👿
         * level : 148
         * gongxian : 0
         * is_task : 0
         */

        private int id;
        private String headimg;
        private String nickname;
        private int level;
        private int gongxian;
        private int is_task;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getGongxian() {
            return gongxian;
        }

        public void setGongxian(int gongxian) {
            this.gongxian = gongxian;
        }

        public int getIs_task() {
            return is_task;
        }

        public void setIs_task(int is_task) {
            this.is_task = is_task;
        }
    }
}
