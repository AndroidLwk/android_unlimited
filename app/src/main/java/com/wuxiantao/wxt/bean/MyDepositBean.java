package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyDepositBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-1 下午2:48
 * Description:${DESCRIPTION}我的存款
 */
public class MyDepositBean {


    /**
     * list : {"red_profit":4.6456765,"yongjin":30.827,"total":35.4726765,"tao_ferrze":0,"amount":35.472676500000006,"today_profit":0,"level":1,"cha":149,"qun_img":"http://super-tao.oss-cn-beijing.aliyuncs.com/c8f4d16d956d4e21/136ede92cf80647c.jpg","fenhong":"2504.00"}
     */

    private ListBean list;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * red_profit : 4.6456765
         * yongjin : 30.827
         * total : 35.4726765
         * tao_ferrze : 0
         * amount : 35.472676500000006
         * today_profit : 0
         * level : 1
         * cha : 149
         * qun_img : http://super-tao.oss-cn-beijing.aliyuncs.com/c8f4d16d956d4e21/136ede92cf80647c.jpg
         * fenhong : 2504.00
         */

        private double red_profit;
        private double yongjin;
        private double total;
        private int tao_ferrze;
        private double amount;
        private double today_profit;
        private int level;
        private int cha;
        private String qun_img;
        private String fenhong;

        public double getRed_profit() {
            return red_profit;
        }

        public void setRed_profit(double red_profit) {
            this.red_profit = red_profit;
        }

        public double getYongjin() {
            return yongjin;
        }

        public void setYongjin(double yongjin) {
            this.yongjin = yongjin;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public int getTao_ferrze() {
            return tao_ferrze;
        }

        public void setTao_ferrze(int tao_ferrze) {
            this.tao_ferrze = tao_ferrze;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getToday_profit() {
            return today_profit;
        }

        public void setToday_profit(double today_profit) {
            this.today_profit = today_profit;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getCha() {
            return cha;
        }

        public void setCha(int cha) {
            this.cha = cha;
        }

        public String getQun_img() {
            return qun_img;
        }

        public void setQun_img(String qun_img) {
            this.qun_img = qun_img;
        }

        public String getFenhong() {
            return fenhong;
        }

        public void setFenhong(String fenhong) {
            this.fenhong = fenhong;
        }
    }
}
