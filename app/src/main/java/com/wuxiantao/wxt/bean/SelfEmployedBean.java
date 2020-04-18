package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SelfEmployedBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-13 上午9:45
 * Description:${DESCRIPTION}
 */
public class SelfEmployedBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 186
         * cate_id : 1
         * goods_title : 男士加绒丝绒外套  抗寒过冬款
         * goods_content : <p><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/1f9448e9cbd2d70e/f80d0c35663287dc.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/5aee846541280668/af37309b3fd2cf44.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/87b3384784e1cc69/958996cd72e159e8.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/c36299de82d664d1/98b6c93971f25480.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/860d3c59ef9148ee/428577d261a81160.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/a560a2195364f50a/a3c264944f275795.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/9181dd4ad6abd7af/a0aa88af9f7807ee.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/f829cedef2372a9b/4e76d205cb858f0b.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/636916a68b9a5ac4/ef4f24fe7b5f6a2f.jpg" title="image" /><img border="0" src="http://super-tao.oss-cn-beijing.aliyuncs.com/1db0416e53ac2dd4/c0e85f543b8d51bf.jpg" title="image" /></p>
         * goods_image : http://super-tao.oss-cn-beijing.aliyuncs.com/a560a2195364f50a/a3c264944f275795.jpg|http://super-tao.oss-cn-beijing.aliyuncs.com/f829cedef2372a9b/4e76d205cb858f0b.jpg|http://super-tao.oss-cn-beijing.aliyuncs.com/636916a68b9a5ac4/ef4f24fe7b5f6a2f.jpg
         * price : 169.00
         * cost : 69.00
         * express_money : 0.00
         * sort : 100
         * create_at : 2019-12-06 17:20:01
         * specs : [{"name":"立领","list":[{"name":"M","check":true,"url":"属性图片"},{"name":"L","check":true,"url":"属性图片"},{"name":"XL","check":true,"url":"属性图片"}]},{"name":"带帽","list":[{"name":"M","check":true},{"name":"L","check":true,"url":"属性图片"},{"name":"XL","check":true,"url":"属性图片"}]}]
         * lists : [[{"name":"M","check":true,"url":"属性图片","group":"立领","span":3,"show":true,"key":"立领","express":1000,"virtual":"0","market":"0.00","selling":"0.00","msort":"0","status":true},{"name":"M","check":true,"group":"带帽","span":1,"show":true}]]
         * total_number : 10000
         * is_tuijian : 1
         * vip_level : 0
         * is_special : 1
         * special_end_time : 0
         * is_sale_tomorrow : 1
         * sale_num : 0
         * goods_shu : http://super-tao.oss-cn-beijing.aliyuncs.com/f829cedef2372a9b/4e76d205cb858f0b.jpg|http://super-tao.oss-cn-beijing.aliyuncs.com/a560a2195364f50a/a3c264944f275795.jpg
         * desc : 新疆偏远地区不发货
         * rebate : 25
         * daoshou : 144
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
        private int is_tuijian;
        private int vip_level;
        private int is_special;
        private int special_end_time;
        private int is_sale_tomorrow;
        private int sale_num;
        private String goods_shu;
        private String desc;
        private int rebate;
        private int daoshou;

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

        public int getIs_tuijian() {
            return is_tuijian;
        }

        public void setIs_tuijian(int is_tuijian) {
            this.is_tuijian = is_tuijian;
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

        public String getGoods_shu() {
            return goods_shu;
        }

        public void setGoods_shu(String goods_shu) {
            this.goods_shu = goods_shu;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getRebate() {
            return rebate;
        }

        public void setRebate(int rebate) {
            this.rebate = rebate;
        }

        public int getDaoshou() {
            return daoshou;
        }

        public void setDaoshou(int daoshou) {
            this.daoshou = daoshou;
        }
    }
}
