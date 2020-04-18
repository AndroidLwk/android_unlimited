package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BalanceDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午8:47
 * Description:${DESCRIPTION}
 */
public class BalanceDetailBean {

    /**
     * list : [{"num":"-0.014999595","time":"1560827230","msg":"提现"},{"num":"-0.014999595","time":"1560827215","msg":"提现"},{"num":"-0.01","time":"1560827200","msg":"提现"},{"num":"-0.01","time":"1560827185","msg":"提现"},{"num":"-0.01","time":"1560827170","msg":"提现"},{"num":"-0.01","time":"1560827155","msg":"提现"},{"num":"-0.01","time":"1560827140","msg":"提现"},{"num":"-0.01","time":"1560827125","msg":"提现"},{"num":"0.014999595","time":"1560827110","msg":"每日利息"},{"num":"0.014999595","time":"1560827095","msg":"每日利息"}]
     * more : 1
     */

    private int more;
    private List<ListBean> list;

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * num : -0.014999595
         * time : 1560827230
         * msg : 提现
         */

        private String num;
        private String time;
        private String msg;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
