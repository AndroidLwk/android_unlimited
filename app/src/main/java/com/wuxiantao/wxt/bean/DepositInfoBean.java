package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DepositInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 下午4:49
 * Description:${DESCRIPTION} 存款超人信息
 */
public class DepositInfoBean {


    /**
     * status : 0
     * create_time :
     * list : {"create_time":"2019-07-08 17:16:00","work_status":0,"work_time":3600,"work_time_all":3600,"everyday_redpacket_limit":1000,"getred":0,"work_auto":0}
     */

    private int status; //看视屏状态 1的时候不能再看
    private String create_time; //记录看视频处理的时间
    private ListBean list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * create_time : 2019-07-08 17:16:00
         * work_status : 0
         * work_time : 3600
         * work_time_all : 3600
         * everyday_redpacket_limit : 1000
         * getred : 0
         * work_auto : 0
         */

        private long create_time; //超人派出时间
        private int work_status; // 1,超人派出状态0未派 1 已派出
        private int work_time; //1,工作时长（秒）
        private int work_time_all; // 0, 分享增加五分钟页面用的时间（返回的是秒）
        private int everyday_redpacket_limit; //2019, 每日红包上限
        private int getred; //1010, 今日获得红包
        private int work_auto; //0 超人是否永久飞行 状态返回1是永久

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getWork_status() {
            return work_status;
        }

        public void setWork_status(int work_status) {
            this.work_status = work_status;
        }

        public int getWork_time() {
            return work_time;
        }

        public void setWork_time(int work_time) {
            this.work_time = work_time;
        }

        public int getWork_time_all() {
            return work_time_all;
        }

        public void setWork_time_all(int work_time_all) {
            this.work_time_all = work_time_all;
        }

        public int getEveryday_redpacket_limit() {
            return everyday_redpacket_limit;
        }

        public void setEveryday_redpacket_limit(int everyday_redpacket_limit) {
            this.everyday_redpacket_limit = everyday_redpacket_limit;
        }

        public int getGetred() {
            return getred;
        }

        public void setGetred(int getred) {
            this.getred = getred;
        }

        public int getWork_auto() {
            return work_auto;
        }

        public void setWork_auto(int work_auto) {
            this.work_auto = work_auto;
        }
    }
}
