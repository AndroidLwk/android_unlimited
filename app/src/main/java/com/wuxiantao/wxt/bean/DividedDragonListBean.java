package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DividedDragonDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:42
 * Description:${DESCRIPTION} 分红详情页->列表数据
 */
public class DividedDragonListBean {


    /**
     * count : 1
     * list : [{"id":1,"user_id":4,"nickname":"刘运涛","num":1,"msg":"进度条满自动获得","time":1573524775}]
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
         * nickname : 刘运涛
         * num : 1
         * msg : 进度条满自动获得
         * time : 1573524775
         */

        private int id;
        private int user_id;
        private String nickname;
        private int num;
        private String msg;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
