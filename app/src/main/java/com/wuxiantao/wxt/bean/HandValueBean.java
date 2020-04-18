package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HandValueBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 下午3:25
 * Description:${DESCRIPTION}
 */
public class HandValueBean {


    /**
     * luck : 1
     * level : 0
     * msg : 手气极差
     * num : 0
     * lucklog : [{"msg":"淘宝购物，获得存款：140元。","time":"1559373256","num":"140"}]
     */

    private int luck;
    private int level;
    private String msg;
    private int num;
    private List<LucklogBean> lucklog;

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<LucklogBean> getLucklog() {
        return lucklog;
    }

    public void setLucklog(List<LucklogBean> lucklog) {
        this.lucklog = lucklog;
    }

    public static class LucklogBean {
        /**
         * msg : 淘宝购物，获得存款：140元。
         * time : 1559373256
         * num : 140
         */

        private String msg;
        private String time;
        private String num;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
