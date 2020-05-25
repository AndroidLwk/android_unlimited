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
     * total : 3.35
     * total_zhitui : 3.1
     * total_jianjie : 0.25
     * list : [{"id":199903,"headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqkUDSbrAX3iaPtuxz32bqtBsMex5rich4QoKIUbb1NDianoK2ayGZrhOPlezOs0S9n4MkceZL68Ecsw/132","nickname":"chuhao","level":0,"gongxian":0,"is_task":0},{"id":199995,"headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/xyjORojkoLyrG7WOFCZPlb9e5aAQ4tEia89icuaohkRMtd5ibWBwCGtRh100zNmZZ6OPeUwTFZJSJ48PUNDiclUmRA/132","nickname":"South","level":0,"gongxian":3.1,"is_task":0}]
     * count : 2
     */

    private double total;
    private double total_zhitui;
    private double total_jianjie;
    private int count;
    private List<ListBean> list;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal_zhitui() {
        return total_zhitui;
    }

    public void setTotal_zhitui(double total_zhitui) {
        this.total_zhitui = total_zhitui;
    }

    public double getTotal_jianjie() {
        return total_jianjie;
    }

    public void setTotal_jianjie(double total_jianjie) {
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
         * id : 199903
         * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqkUDSbrAX3iaPtuxz32bqtBsMex5rich4QoKIUbb1NDianoK2ayGZrhOPlezOs0S9n4MkceZL68Ecsw/132
         * nickname : chuhao
         * level : 0
         * gongxian : 0
         * is_task : 0
         */

        private int id;
        private String headimg;
        private String nickname;
        private int level;
        private double gongxian;
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

        public double getGongxian() {
            return gongxian;
        }

        public void setGongxian(double gongxian) {
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
