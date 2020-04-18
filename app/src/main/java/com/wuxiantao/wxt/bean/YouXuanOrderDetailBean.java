package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:YouXuanOrderDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-22 下午5:29
 * Description:${DESCRIPTION}
 */
public class YouXuanOrderDetailBean {


    /**
     * id : 164
     * order_no : 20190821223400435205
     * goods_id : 37
     * price : 365.00
     * cost : 75.000000
     * money : 365.00
     * is_pay : 0
     * express_no :
     * status : 1
     * create_at : 2019-08-21 22:34:00
     * rate_money : 365
     * vip_level : 1
     * goods : {"id":37,"goods_title":"u型按摩枕 多功能肩颈椎脖子颈部肩颈电动按摩器理疗仪车载护颈仪","goods_image":"http://chaoren.haowusong.com/static/upload/ce5770b130888e13/f7289bfbf2455d6f.jpg|http://chaoren.haowusong.com/static/upload/1d7baf40e59ed512/b099f63035f0b3cc.jpg|http://chaoren.haowusong.com/static/upload/1f2d8c825737ec1b/d1aab62c80b69bf8.jpg","price":"365.00"}
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
         * id : 37
         * goods_title : u型按摩枕 多功能肩颈椎脖子颈部肩颈电动按摩器理疗仪车载护颈仪
         * goods_image : http://chaoren.haowusong.com/static/upload/ce5770b130888e13/f7289bfbf2455d6f.jpg|http://chaoren.haowusong.com/static/upload/1d7baf40e59ed512/b099f63035f0b3cc.jpg|http://chaoren.haowusong.com/static/upload/1f2d8c825737ec1b/d1aab62c80b69bf8.jpg
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
