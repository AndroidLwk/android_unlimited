package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午5:02
 * Description:${DESCRIPTION}
 */
public class CommodityInfoBean {


    /**
     * info : {"id":27,"cate_id":5,"goods_title":"透气超轻跑鞋","goods_content":"","goods_image":"http://chaoren.haowusong.com/static/upload/8b3f77489b8f0c11/7d558667405fc3c1.png|http://chaoren.haowusong.com/static/upload/45a393d6a997b81b/c60f4493fc988de2.png|http://chaoren.haowusong.com/static/upload/17d3830e9c8dd206/2a4302faa7c2b06c.png","price":"365.00","cost":"99.00","express_money":"0.00","sort":100,"status":1,"create_at":"2019-08-05 17:42:36","specs":"[{\"name\":\"默认分组\",\"list\":[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\"}]}]","lists":"[[{\"name\":\"默认规格\",\"check\":true,\"url\":\"属性图片\",\"group\":\"默认分组\",\"span\":1,\"show\":true,\"key\":\"默认分组:默认规格\",\"express\":1,\"virtual\":\"0\",\"market\":\"0.00\",\"selling\":\"0.00\",\"msort\":\"0\",\"status\":true}]]","total_number":10000,"is_low_price":0,"vip_level":1,"is_special":0,"special_end_time":0,"is_sale_tomorrow":0,"sale_num":0,"spec_detail":[{"id":146,"goods_id":27,"goods_spec":"默认分组:默认规格","goods_number":1,"market_price":"0.00","selling_price":"0.00","goods_stock":1,"goods_sale":0,"status":1,"is_deleted":0,"create_at":"2019-08-05 17:44:12"}],"vip_rebate":93.1,"com_rebate":66.5}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 27
         * cate_id : 5
         * goods_title : 透气超轻跑鞋
         * goods_content :
         * goods_image : http://chaoren.haowusong.com/static/upload/8b3f77489b8f0c11/7d558667405fc3c1.png|http://chaoren.haowusong.com/static/upload/45a393d6a997b81b/c60f4493fc988de2.png|http://chaoren.haowusong.com/static/upload/17d3830e9c8dd206/2a4302faa7c2b06c.png
         * price : 365.00
         * cost : 99.00
         * express_money : 0.00
         * sort : 100
         * status : 1
         * create_at : 2019-08-05 17:42:36
         * specs : [{"name":"默认分组","list":[{"name":"默认规格","check":true,"url":"属性图片"}]}]
         * lists : [[{"name":"默认规格","check":true,"url":"属性图片","group":"默认分组","span":1,"show":true,"key":"默认分组:默认规格","express":1,"virtual":"0","market":"0.00","selling":"0.00","msort":"0","status":true}]]
         * total_number : 10000
         * is_low_price : 0
         * vip_level : 1
         * is_special : 0
         * special_end_time : 0
         * is_sale_tomorrow : 0
         * sale_num : 0
         * spec_detail : [{"id":146,"goods_id":27,"goods_spec":"默认分组:默认规格","goods_number":1,"market_price":"0.00","selling_price":"0.00","goods_stock":1,"goods_sale":0,"status":1,"is_deleted":0,"create_at":"2019-08-05 17:44:12"}]
         * vip_rebate : 93.1
         * com_rebate : 66.5
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
        private int status;
        private String create_at;
        private String specs;
        private String lists;
        private int total_number;
        private int is_low_price;
        private int vip_level;
        private int is_special;
        private int special_end_time;
        private int is_sale_tomorrow;
        private int sale_num;
        private double vip_rebate;
        private double com_rebate;
        private List<SpecDetailBean> spec_detail;

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

        public int getIs_low_price() {
            return is_low_price;
        }

        public void setIs_low_price(int is_low_price) {
            this.is_low_price = is_low_price;
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

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

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

        public List<SpecDetailBean> getSpec_detail() {
            return spec_detail;
        }

        public void setSpec_detail(List<SpecDetailBean> spec_detail) {
            this.spec_detail = spec_detail;
        }

        public static class SpecDetailBean {
            /**
             * id : 146
             * goods_id : 27
             * goods_spec : 默认分组:默认规格
             * goods_number : 1
             * market_price : 0.00
             * selling_price : 0.00
             * goods_stock : 1
             * goods_sale : 0
             * status : 1
             * is_deleted : 0
             * create_at : 2019-08-05 17:44:12
             */

            private int id;
            private int goods_id;
            private String goods_spec;
            private int goods_number;
            private String market_price;
            private String selling_price;
            private int goods_stock;
            private int goods_sale;
            private int status;
            private int is_deleted;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(String goods_spec) {
                this.goods_spec = goods_spec;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getSelling_price() {
                return selling_price;
            }

            public void setSelling_price(String selling_price) {
                this.selling_price = selling_price;
            }

            public int getGoods_stock() {
                return goods_stock;
            }

            public void setGoods_stock(int goods_stock) {
                this.goods_stock = goods_stock;
            }

            public int getGoods_sale() {
                return goods_sale;
            }

            public void setGoods_sale(int goods_sale) {
                this.goods_sale = goods_sale;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}
