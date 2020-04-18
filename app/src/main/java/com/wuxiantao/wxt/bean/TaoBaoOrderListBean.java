package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoOrderListBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-18 上午9:32
 * Description:${DESCRIPTION}
 */
public class TaoBaoOrderListBean {


    /**
     * list : [{"id":7,"user_id":20,"trade_id":"548131299255070137","trade_parent_id":"548131299255070137","num_iid":"546016960788","item_title":"陕西特产农家自产鲜枣黄河滩红枣免洗大红枣熬粥泡茶500g包邮","item_num":1,"pict_url":"","price":"19.00","pay_price":"0.00","commission_rate":"1.00","commission":"0.00","tk_status":12,"order_type":"淘宝","site_id":"370950428","adzone_id":"104906800320","click_time":"2019-07-22 11:56:24","create_time":"2019-07-22 10:54:58","earning_time":"0","tao_id":"073701","is_jidan":0,"is_gold":0,"gid":"","is_finish":0,"open_uid":"AAHz1mS6AJVZF5IQsSKaCHWa","deposit_status":1,"luck_status":0,"luck":"","redpack_limit":1}]
     * count : 1
     * more : 0
     */

    private int count;
    private int more;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
         * id : 7
         * user_id : 20
         * trade_id : 548131299255070137
         * trade_parent_id : 548131299255070137
         * num_iid : 546016960788
         * item_title : 陕西特产农家自产鲜枣黄河滩红枣免洗大红枣熬粥泡茶500g包邮
         * item_num : 1
         * pict_url :
         * price : 19.00
         * pay_price : 0.00
         * commission_rate : 1.00
         * commission : 0.00
         * tk_status : 12
         * order_type : 淘宝
         * site_id : 370950428
         * adzone_id : 104906800320
         * click_time : 2019-07-22 11:56:24
         * create_time : 2019-07-22 10:54:58
         * earning_time : 0
         * tao_id : 073701
         * is_jidan : 0
         * is_gold : 0
         * gid :
         * is_finish : 0
         * open_uid : AAHz1mS6AJVZF5IQsSKaCHWa
         * deposit_status : 1
         * luck_status : 0
         * luck :
         * redpack_limit : 1
         */

        private int id;
        private int user_id;
        private String trade_id;
        private String trade_parent_id;
        private String num_iid;
        private String item_title;
        private int item_num;
        private String pict_url;
        private String price;
        private String pay_price;
        private String commission_rate;
        private String commission;
        private int tk_status;
        private String order_type;
        private String site_id;
        private String adzone_id;
        private String click_time;
        private String create_time;
        private String earning_time;
        private String tao_id;
        private int is_jidan;
        private int is_gold;
        private String gid;
        private int is_finish;
        private String open_uid;
        private int deposit_status;
        private int luck_status;
        private String luck;
        private int redpack_limit;

        public double getRebate() {
            return rebate;
        }

        public void setRebate(double rebate) {
            this.rebate = rebate;
        }

        private double rebate;


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

        public String getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
        }

        public String getTrade_parent_id() {
            return trade_parent_id;
        }

        public void setTrade_parent_id(String trade_parent_id) {
            this.trade_parent_id = trade_parent_id;
        }

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public int getItem_num() {
            return item_num;
        }

        public void setItem_num(int item_num) {
            this.item_num = item_num;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public int getTk_status() {
            return tk_status;
        }

        public void setTk_status(int tk_status) {
            this.tk_status = tk_status;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getAdzone_id() {
            return adzone_id;
        }

        public void setAdzone_id(String adzone_id) {
            this.adzone_id = adzone_id;
        }

        public String getClick_time() {
            return click_time;
        }

        public void setClick_time(String click_time) {
            this.click_time = click_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEarning_time() {
            return earning_time;
        }

        public void setEarning_time(String earning_time) {
            this.earning_time = earning_time;
        }

        public String getTao_id() {
            return tao_id;
        }

        public void setTao_id(String tao_id) {
            this.tao_id = tao_id;
        }

        public int getIs_jidan() {
            return is_jidan;
        }

        public void setIs_jidan(int is_jidan) {
            this.is_jidan = is_jidan;
        }

        public int getIs_gold() {
            return is_gold;
        }

        public void setIs_gold(int is_gold) {
            this.is_gold = is_gold;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public int getIs_finish() {
            return is_finish;
        }

        public void setIs_finish(int is_finish) {
            this.is_finish = is_finish;
        }

        public String getOpen_uid() {
            return open_uid;
        }

        public void setOpen_uid(String open_uid) {
            this.open_uid = open_uid;
        }

        public int getDeposit_status() {
            return deposit_status;
        }

        public void setDeposit_status(int deposit_status) {
            this.deposit_status = deposit_status;
        }

        public int getLuck_status() {
            return luck_status;
        }

        public void setLuck_status(int luck_status) {
            this.luck_status = luck_status;
        }

        public String getLuck() {
            return luck;
        }

        public void setLuck(String luck) {
            this.luck = luck;
        }

        public int getRedpack_limit() {
            return redpack_limit;
        }

        public void setRedpack_limit(int redpack_limit) {
            this.redpack_limit = redpack_limit;
        }
    }
}
