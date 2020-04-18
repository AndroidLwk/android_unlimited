package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryRecordingBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午8:14
 * Description:${DESCRIPTION}
 */
public class LotteryRecordingBean {


    /**
     * list : [{"id":3422648,"user_id":4,"types":150,"num":"3.00","msg":"抽奖获得3.00元现金红包","time":1573536319,"fen_id":0},{"id":3422638,"user_id":4,"types":150,"num":"5.00","msg":"抽奖获得5.00元现金红包","time":1573536280,"fen_id":0},{"id":3422568,"user_id":4,"types":150,"num":"1.00","msg":"抽奖获得1.00把金钥匙","time":1573536057,"fen_id":0}]
     * count : 3
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
         * id : 3422648
         * user_id : 4
         * types : 150
         * num : 3.00
         * msg : 抽奖获得3.00元现金红包
         * time : 1573536319
         * fen_id : 0
         */

        private int id;
        private int user_id;
        private int types;
        private String num;
        private String msg;
        private int time;
        private int fen_id;

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

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getFen_id() {
            return fen_id;
        }

        public void setFen_id(int fen_id) {
            this.fen_id = fen_id;
        }
    }
}
