package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProfitRecordingBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午12:38
 * Description:${DESCRIPTION}
 */
public class ProfitRecordingBean {


    /**
     * seven : [{"total":0,"date":"2019-08-14"},{"total":0,"date":"2019-08-13"},{"total":0,"date":"2019-08-12"},{"total":0,"date":"2019-08-11"},{"total":0,"date":"2019-08-10"},{"total":0,"date":"2019-08-09"},{"total":0,"date":"2019-08-08"}]
     * amount : 648.9551739999999
     * sall : [{"num":"0.02","msg":"开启红包收益","time":"1559374840"},{"num":"0.5","msg":"开启红包获得金币","time":"1565762408"},{"num":"432.290108","msg":"开启红包获得金币","time":"1565768028"},{"num":"216.145066","msg":"开启红包获得金币","time":"1565768201"}]
     * more : 0
     */

    private double amount;
    private int more;
    private List<SevenBean> seven;
    private List<SallBean> sall;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<SevenBean> getSeven() {
        return seven;
    }

    public void setSeven(List<SevenBean> seven) {
        this.seven = seven;
    }

    public List<SallBean> getSall() {
        return sall;
    }

    public void setSall(List<SallBean> sall) {
        this.sall = sall;
    }

    public static class SevenBean {
        /**
         * total : 0
         * date : 2019-08-14
         */

        private double total;
        private String date;

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }



    public static class SallBean {
        /**
         * num : 0.02
         * msg : 开启红包收益
         * time : 1559374840
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
