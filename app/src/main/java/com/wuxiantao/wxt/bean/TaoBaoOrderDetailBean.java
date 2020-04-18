package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:YouXuanOrderDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:29
 * Description:${DESCRIPTION}
 */
public class TaoBaoOrderDetailBean {


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
     * create_time : 2019-07-22 10:54:58
     * earning_time : 0
     * deposit_status : 1
     * seller_shop_title :
     * rebate : 0
     */

    private int id;
    private int user_id;
    private String trade_id;
    private String trade_parent_id;
    private long num_iid;
    private String item_title;
    private int item_num;
    private String pict_url;
    private String price;
    private String pay_price;
    private String commission_rate;
    private String commission;
    private int tk_status;
    private String order_type;
    private String create_time;
    private String earning_time;
    private int deposit_status;
    private String seller_shop_title;
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

    public long getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(long num_iid) {
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

    public int getDeposit_status() {
        return deposit_status;
    }

    public void setDeposit_status(int deposit_status) {
        this.deposit_status = deposit_status;
    }

    public String getSeller_shop_title() {
        return seller_shop_title;
    }

    public void setSeller_shop_title(String seller_shop_title) {
        this.seller_shop_title = seller_shop_title;
    }

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }
}
