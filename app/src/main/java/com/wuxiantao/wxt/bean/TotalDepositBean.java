package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TotalDepositBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午10:27
 * Description:${DESCRIPTION}
 */
public class TotalDepositBean {


    /**
     * total : 1250
     * yongjind : 0.0025
     * yongjinz : 0
     * yongjinf : 1250
     * list : [{"num":"1230","msg":"下属会员团队购物返利","time":"1560771834"}]
     * more : 0
     */

    private double total;
    private double yongjind;
    private double yongjinz;
    private double yongjinf;
    private int more;
    private List<ListBean> list;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getYongjind() {
        return yongjind;
    }

    public void setYongjind(double yongjind) {
        this.yongjind = yongjind;
    }

    public double getYongjinz() {
        return yongjinz;
    }

    public void setYongjinz(double yongjinz) {
        this.yongjinz = yongjinz;
    }

    public double getYongjinf() {
        return yongjinf;
    }

    public void setYongjinf(double yongjinf) {
        this.yongjinf = yongjinf;
    }

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
         * num : 1230
         * msg : 下属会员团队购物返利
         * time : 1560771834
         */

        private String num;
        private String msg;
        private String time;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

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
    }
}
