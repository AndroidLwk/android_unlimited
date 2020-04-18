package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaobaoLatelyOrderBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:33
 * Description:${DESCRIPTION} 自营->最近订单
 */
public class YouXuanLatelyOrderBean {


    /**
     * id : 239
     * order_no : 20190824121957756704
     * goods_id : 51
     * price : 365.00
     * cost : 99.000000
     * money : 365.00
     * is_pay : 0
     * express_no :
     * status : 1
     * create_at : 2019-08-24 12:19:57
     * rate_money : 365
     * vip_level : 1
     * goods : {"id":51,"goods_title":"男士手包编织菱格真皮男长款横拉链男士羊皮钱包","goods_image":"http://chaoren.haowusong.com/static/upload/ca91a9d8e4c1fefd/2d6212b5092205dd.jpg|http://chaoren.haowusong.com/static/upload/397650cf7b1bb208/66b6e160fa71a4a6.jpg|http://chaoren.haowusong.com/static/upload/40836f664ec7751f/d341d18e218b218c.jpg|http://chaoren.haowusong.com/static/upload/0791344623f5fc69/50a6a7fb51031623.jpg|http://chaoren.haowusong.com/static/upload/868bd2ee8d934c9c/3a2fe3fc41d3a5cf.jpg","price":"365.00"}
     */

    private int id;
    private String order_no;
    private int goods_id;
    private String price;
    private String cost;
    private String money;
    private int is_pay;
    private String express_no;
    private int status;
    private String create_at;
    private String rate_money;
    private int vip_level;
    private GoodsBean goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public String getExpress_no() {
        return express_no;
    }

    public void setExpress_no(String express_no) {
        this.express_no = express_no;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getRate_money() {
        return rate_money;
    }

    public void setRate_money(String rate_money) {
        this.rate_money = rate_money;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * id : 51
         * goods_title : 男士手包编织菱格真皮男长款横拉链男士羊皮钱包
         * goods_image : http://chaoren.haowusong.com/static/upload/ca91a9d8e4c1fefd/2d6212b5092205dd.jpg|http://chaoren.haowusong.com/static/upload/397650cf7b1bb208/66b6e160fa71a4a6.jpg|http://chaoren.haowusong.com/static/upload/40836f664ec7751f/d341d18e218b218c.jpg|http://chaoren.haowusong.com/static/upload/0791344623f5fc69/50a6a7fb51031623.jpg|http://chaoren.haowusong.com/static/upload/868bd2ee8d934c9c/3a2fe3fc41d3a5cf.jpg
         * price : 365.00
         */

        private int id;
        private String goods_title;
        private String goods_image;
        private String price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
