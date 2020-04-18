package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoHomeBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午7:56
 * Description:${DESCRIPTION}
 */
public class TaoBaoHomeBean {


    /**
     * error : 0
     * msg : 查询成功！
     * search_type : 1
     * is_similar : 0
     * total_results : 1556653
     * result_list : [{"category_id":251707,"category_name":"拼图/拼板","commission_rate":"1000","coupon_amount":"1","coupon_end_time":"2019-08-06","coupon_id":"429033ae7b824bec8877ff1a3482a83a","coupon_info":"满5元减1元","coupon_remain_count":79000,"coupon_start_fee":"5","coupon_start_time":"2019-06-07","coupon_total_count":100000,"item_description":"","item_id":553131838005,"item_url":"https://item.taobao.com/item.htm?id=553131838005","level_one_category_id":25,"level_one_category_name":"玩具/童车/益智/积木/模型","nick":"linyuanwei68","num_iid":553131838005,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/656953360/O1CN01W8QP131agvGkvRUae_!!656953360.jpg","provcity":"浙江 丽水","real_post_fee":"0.00","reserve_price":"6.1","seller_id":656953360,"shop_dsr":48230,"shop_title":"妈咪放心购厂家直销店","short_title":"幼儿童木质宝宝2-3-4-5-6-7岁拼图","small_images":["https://img.alicdn.com/i2/656953360/TB2NtazXVuWBuNjSszbXXcS7FXa_!!656953360.jpg","https://img.alicdn.com/i4/656953360/TB2PWSWxCtYBeNjSspaXXaOOFXa_!!656953360.jpg","https://img.alicdn.com/i3/656953360/TB280kgX_lYBeNjSszcXXbwhFXa_!!656953360.jpg","https://img.alicdn.com/i3/656953360/TB2R1uEX4SYBuNjSspjXXX73VXa_!!656953360.jpg"],"title":"幼儿童拼图木质宝宝男孩女孩2-3-4-5-6-7岁8益智力平图玩具100片","tk_total_commi":"11969.1","tk_total_sales":"14848","user_type":0,"volume":101360,"white_image":"https://img.alicdn.com/bao/uploaded/i2/656953360/TB2NtazXVuWBuNjSszbXXcS7FXa_!!656953360.jpg","zk_final_price":"6.1","buyfanli":6.1,"sharefanli":1.22},{"category_id":50000436,"category_name":"T恤","commission_rate":"2000","coupon_amount":"2","coupon_end_time":"2019-07-18","coupon_id":"50a89fa3212c462096a2824ab5bf3823","coupon_info":"满9元减2元","coupon_remain_count":99000,"coupon_start_fee":"9","coupon_start_time":"2019-07-15","coupon_total_count":100000,"item_description":"我们承诺 不起球 不变形 正品保障","item_id":532530634044,"item_url":"https://detail.tmall.com/item.htm?id=532530634044","level_one_category_id":30,"level_one_category_name":"男装","nick":"凯恒千色旗舰店","num_iid":532530634044,"pict_url":"https://img.alicdn.com/bao/uploaded/i3/2451796575/O1CN01kkDAz51yROc0tAMMe_!!0-item_pic.jpg","provcity":"广东 广州","real_post_fee":"0.00","reserve_price":"69","seller_id":2451796575,"shop_dsr":47465,"shop_title":"凯恒千色旗舰店","short_title":"夏季男士t恤男短袖百搭修身打底衫","small_images":["https://img.alicdn.com/i2/TB16msZPVXXXXXWapXXXXXXXXXX_!!0-item_pic.jpg","https://img.alicdn.com/i2/2451796575/O1CN01mx23pr1yRObzDcCyi_!!2451796575.jpg","https://img.alicdn.com/i2/2451796575/O1CN01G3fyi51yROenfKIMc_!!2451796575.jpeg","https://img.alicdn.com/i1/2451796575/O1CN01k0pPNW1yRObsfQjFs_!!2451796575.jpg"],"title":"夏季男士T恤男短袖百搭修身体恤情侣半袖白丅衣服男装打底衫潮流","tk_total_commi":"58471","tk_total_sales":"21460","user_type":1,"volume":46010,"white_image":"https://img.alicdn.com/bao/uploaded/TB25Y32jbVkpuFjSspcXXbSMVXa_!!2451796575.png","zk_final_price":"9.8","buyfanli":9.8,"sharefanli":1.9600000000000002}]
     * more : 1
     */

    private String error;
    private String msg;
    private String search_type;
    private String is_similar;
    private int total_results;
    private int more;
    private List<ResultListBean> result_list;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getIs_similar() {
        return is_similar;
    }

    public void setIs_similar(String is_similar) {
        this.is_similar = is_similar;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<ResultListBean> getResult_list() {
        return result_list;
    }

    public void setResult_list(List<ResultListBean> result_list) {
        this.result_list = result_list;
    }

    public static class ResultListBean {
        /**
         * category_id : 251707
         * category_name : 拼图/拼板
         * commission_rate : 1000
         * coupon_amount : 1
         * coupon_end_time : 2019-08-06
         * coupon_id : 429033ae7b824bec8877ff1a3482a83a
         * coupon_info : 满5元减1元
         * coupon_remain_count : 79000
         * coupon_start_fee : 5
         * coupon_start_time : 2019-06-07
         * coupon_total_count : 100000
         * item_description :
         * item_id : 553131838005
         * item_url : https://item.taobao.com/item.htm?id=553131838005
         * level_one_category_id : 25
         * level_one_category_name : 玩具/童车/益智/积木/模型
         * nick : linyuanwei68
         * num_iid : 553131838005
         * pict_url : https://img.alicdn.com/bao/uploaded/i3/656953360/O1CN01W8QP131agvGkvRUae_!!656953360.jpg
         * provcity : 浙江 丽水
         * real_post_fee : 0.00
         * reserve_price : 6.1
         * seller_id : 656953360
         * shop_dsr : 48230
         * shop_title : 妈咪放心购厂家直销店
         * short_title : 幼儿童木质宝宝2-3-4-5-6-7岁拼图
         * small_images : ["https://img.alicdn.com/i2/656953360/TB2NtazXVuWBuNjSszbXXcS7FXa_!!656953360.jpg","https://img.alicdn.com/i4/656953360/TB2PWSWxCtYBeNjSspaXXaOOFXa_!!656953360.jpg","https://img.alicdn.com/i3/656953360/TB280kgX_lYBeNjSszcXXbwhFXa_!!656953360.jpg","https://img.alicdn.com/i3/656953360/TB2R1uEX4SYBuNjSspjXXX73VXa_!!656953360.jpg"]
         * title : 幼儿童拼图木质宝宝男孩女孩2-3-4-5-6-7岁8益智力平图玩具100片
         * tk_total_commi : 11969.1
         * tk_total_sales : 14848
         * user_type : 0
         * volume : 101360
         * white_image : https://img.alicdn.com/bao/uploaded/i2/656953360/TB2NtazXVuWBuNjSszbXXcS7FXa_!!656953360.jpg
         * zk_final_price : 6.1
         * buyfanli : 6.1
         * sharefanli : 1.22
         */

        private int category_id;
        private String category_name;
        private String commission_rate;
        private String coupon_amount;
        private String coupon_end_time;
        private String coupon_id;
        private String coupon_info;
        private int coupon_remain_count;
        private String coupon_start_fee;
        private String coupon_start_time;
        private int coupon_total_count;
        private String item_description;
        private long item_id;
        private String item_url;
        private int level_one_category_id;
        private String level_one_category_name;
        private String nick;
        private long num_iid;
        private String pict_url;
        private String provcity;
        private String real_post_fee;
        private String reserve_price;
        private long seller_id;
        private int shop_dsr;
        private String shop_title;
        private String short_title;
        private String title;
        private String tk_total_commi;
        private String tk_total_sales;
        private int user_type;
        private int volume;
        private String white_image;
        private String zk_final_price;
        private double buyfanli;
        private double sharefanli;
        private List<String> small_images;

        public double getVip_rebate() {
            return vip_rebate;
        }

        public void setVip_rebate(double vip_rebate) {
            this.vip_rebate = vip_rebate;
        }

        public double getCom_rebate() {
            return com_rebate;
        }

        public void setCom_rebate(double com_rebate) {
            this.com_rebate = com_rebate;
        }

        private double vip_rebate;
        private double com_rebate;


        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCommission_rate() {
            return commission_rate;
        }

        public void setCommission_rate(String commission_rate) {
            this.commission_rate = commission_rate;
        }

        public String getCoupon_amount() {
            return coupon_amount;
        }

        public void setCoupon_amount(String coupon_amount) {
            this.coupon_amount = coupon_amount;
        }

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(String coupon_info) {
            this.coupon_info = coupon_info;
        }

        public int getCoupon_remain_count() {
            return coupon_remain_count;
        }

        public void setCoupon_remain_count(int coupon_remain_count) {
            this.coupon_remain_count = coupon_remain_count;
        }

        public String getCoupon_start_fee() {
            return coupon_start_fee;
        }

        public void setCoupon_start_fee(String coupon_start_fee) {
            this.coupon_start_fee = coupon_start_fee;
        }

        public String getCoupon_start_time() {
            return coupon_start_time;
        }

        public void setCoupon_start_time(String coupon_start_time) {
            this.coupon_start_time = coupon_start_time;
        }

        public int getCoupon_total_count() {
            return coupon_total_count;
        }

        public void setCoupon_total_count(int coupon_total_count) {
            this.coupon_total_count = coupon_total_count;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public String getItem_url() {
            return item_url;
        }

        public void setItem_url(String item_url) {
            this.item_url = item_url;
        }

        public int getLevel_one_category_id() {
            return level_one_category_id;
        }

        public void setLevel_one_category_id(int level_one_category_id) {
            this.level_one_category_id = level_one_category_id;
        }

        public String getLevel_one_category_name() {
            return level_one_category_name;
        }

        public void setLevel_one_category_name(String level_one_category_name) {
            this.level_one_category_name = level_one_category_name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public long getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(long num_iid) {
            this.num_iid = num_iid;
        }

        public String getPict_url() {
            return pict_url;
        }

        public void setPict_url(String pict_url) {
            this.pict_url = pict_url;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getReal_post_fee() {
            return real_post_fee;
        }

        public void setReal_post_fee(String real_post_fee) {
            this.real_post_fee = real_post_fee;
        }

        public String getReserve_price() {
            return reserve_price;
        }

        public void setReserve_price(String reserve_price) {
            this.reserve_price = reserve_price;
        }

        public long getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(long seller_id) {
            this.seller_id = seller_id;
        }

        public int getShop_dsr() {
            return shop_dsr;
        }

        public void setShop_dsr(int shop_dsr) {
            this.shop_dsr = shop_dsr;
        }

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTk_total_commi() {
            return tk_total_commi;
        }

        public void setTk_total_commi(String tk_total_commi) {
            this.tk_total_commi = tk_total_commi;
        }

        public String getTk_total_sales() {
            return tk_total_sales;
        }

        public void setTk_total_sales(String tk_total_sales) {
            this.tk_total_sales = tk_total_sales;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getWhite_image() {
            return white_image;
        }

        public void setWhite_image(String white_image) {
            this.white_image = white_image;
        }

        public String getZk_final_price() {
            return zk_final_price;
        }

        public void setZk_final_price(String zk_final_price) {
            this.zk_final_price = zk_final_price;
        }

        public double getBuyfanli() {
            return buyfanli;
        }

        public void setBuyfanli(double buyfanli) {
            this.buyfanli = buyfanli;
        }

        public double getSharefanli() {
            return sharefanli;
        }

        public void setSharefanli(double sharefanli) {
            this.sharefanli = sharefanli;
        }

        public List<String> getSmall_images() {
            return small_images;
        }

        public void setSmall_images(List<String> small_images) {
            this.small_images = small_images;
        }
    }
}
