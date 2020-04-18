package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HandValueRecordingBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 下午2:25
 * Description:${DESCRIPTION}
 */
public class HandValueRecordingBean {


    /**
     * list : [{"msg":"您的直属下级购物，您获得存款：40元。","time":"1556694856","num":"265"},{"msg":"淘宝购物，获得存款：140元。","time":"1559373256","num":"140"}]
     * more : 0
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
         * msg : 您的直属下级购物，您获得存款：40元。
         * time : 1556694856
         * num : 265
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
