package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreaListBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:42
 * Description:${DESCRIPTION}
 */
public class HighAreaListBean {


    /**
     * status : 0
     * more : 0
     * goodslist : [{"id":28,"cate_id":6,"goods_title":"测试商品1","goods_content":"","goods_image":"","price":"100.00","cost":"50.00","express_money":"0.00","sort":100,"create_at":"2019-08-15 10:38:55","specs":"[{\"name\":\"默认分组\",\"list\":[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\"}]}]","lists":"[[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\",\"group\":\"默认分组\",\"span\":1,\"show\":true,\"key\":\"默认分组:默认规格\",\"express\":1,\"virtual\":\"0\",\"market\":\"0.00\",\"selling\":\"0.00\",\"msort\":\"0\",\"status\":true}]]","total_number":100,"vip_level":0,"is_special":0,"special_end_time":0,"is_sale_tomorrow":0,"sale_num":0},{"id":29,"cate_id":6,"goods_title":"测试2","goods_content":"<p><img border=\"0\" src=\"http://chaoren.haowusong.com/static/upload/64670fa558cc2048/7a708c5b0a2a6acd.jpg\" title=\"image\" /><\/p>\r\n","goods_image":"http://chaoren.haowusong.com/static/upload/e72463d4e393c4dc/c01acb254647b2a4.jpg|http://chaoren.haowusong.com/static/upload/690ed92d26d94fb1/496d8030dd8366a7.jpg|http://chaoren.haowusong.com/static/upload/fdeb5e7fd53b5321/a1a9420efbc62a4d.jpg","price":"200.00","cost":"50.00","express_money":"0.00","sort":100,"create_at":"2019-08-15 10:39:16","specs":"[{\"name\":\"默认分组\",\"list\":[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\"}]}]","lists":"[[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\",\"group\":\"默认分组\",\"span\":1,\"show\":true,\"key\":\"默认分组:默认规格\",\"express\":1,\"virtual\":\"0\",\"market\":\"0.00\",\"selling\":\"0.00\",\"msort\":\"0\",\"status\":true}]]","total_number":100,"vip_level":0,"is_special":0,"special_end_time":0,"is_sale_tomorrow":0,"sale_num":0}]
     */

    private int status;
    private int more;
    private List<GoodslistBean> goodslist;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<GoodslistBean> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodslistBean> goodslist) {
        this.goodslist = goodslist;
    }

    public static class GoodslistBean {
        /**
         * id : 28
         * cate_id : 6
         * goods_title : 测试商品1
         * goods_content :
         * goods_image :
         * price : 100.00
         * cost : 50.00
         * express_money : 0.00
         * sort : 100
         * create_at : 2019-08-15 10:38:55
         * specs : [{"name":"默认分组","list":[{"name":"默认规格","check":true,"url":"属性图片"}]}]
         * lists : [[{"name":"默认规格","check":true,"url":"属性图片","group":"默认分组","span":1,"show":true,"key":"默认分组:默认规格","express":1,"virtual":"0","market":"0.00","selling":"0.00","msort":"0","status":true}]]
         * total_number : 100
         * vip_level : 0
         * is_special : 0
         * special_end_time : 0
         * is_sale_tomorrow : 0
         * sale_num : 0
         */

        private int id;
        private int cate_id;
        private String goods_title;
        private String goods_content;
        private String goods_image;
        private String price;
        private String cost;
        private String express_money;
        private int sort;
        private String create_at;
        private String specs;
        private String lists;
        private int total_number;
        private int vip_level;
        private int is_special;
        private int special_end_time;
        private int is_sale_tomorrow;
        private String sale_num;

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

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_content() {
            return goods_content;
        }

        public void setGoods_content(String goods_content) {
            this.goods_content = goods_content;
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

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getExpress_money() {
            return express_money;
        }

        public void setExpress_money(String express_money) {
            this.express_money = express_money;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public String getLists() {
            return lists;
        }

        public void setLists(String lists) {
            this.lists = lists;
        }

        public int getTotal_number() {
            return total_number;
        }

        public void setTotal_number(int total_number) {
            this.total_number = total_number;
        }

        public int getVip_level() {
            return vip_level;
        }

        public void setVip_level(int vip_level) {
            this.vip_level = vip_level;
        }

        public int getIs_special() {
            return is_special;
        }

        public void setIs_special(int is_special) {
            this.is_special = is_special;
        }

        public int getSpecial_end_time() {
            return special_end_time;
        }

        public void setSpecial_end_time(int special_end_time) {
            this.special_end_time = special_end_time;
        }

        public int getIs_sale_tomorrow() {
            return is_sale_tomorrow;
        }

        public void setIs_sale_tomorrow(int is_sale_tomorrow) {
            this.is_sale_tomorrow = is_sale_tomorrow;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }
    }
}
