package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyCardInfo {

    /**
     * lucky_value : 21.00
     * unopen_card : 0
     * list : [{"id":1,"name":"青铜英雄卡分红","card":0,"card_cha":10,"card_all":10,"status":0,"status_total":1,"money":0,"day":0,"bid":1},{"id":2,"name":"黄金英雄卡分红","card":0,"card_cha":10,"card_all":10,"status":0,"status_total":1,"money":0,"day":0,"bid":2},{"id":3,"name":"整套英雄卡分红","card":0,"card_cha":20,"card_all":20,"status":0,"status_total":1,"money":0,"day":0,"bid":3}]
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/a0519bd76b3e651a/3921655ced58bfb3.png"}]
     * jackpot_imgs : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/384c6c100dc3587f/0dc0a974e5595724.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/f34f6a8a4c31db6e/f451729a0eb0adc1.png"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/fac9738759751d43/313a64f54db8a3a2.png"}]
     * jackpot_text : 6-128元王者皮肤兑换卷1-50元王者皮肤兑换卷集齐英雄卡得平台分红
     * is_sign : 0
     * online_award : 0
     * share_award : 0
     */

    private String lucky_value;
    private int unopen_card;

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

    private int is_vip;
    private String jackpot_text;
    private int is_sign;
    private int online_award;
    private int share_award;

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    private int share;
    private List<ListBean> list;
    private List<BannerBean> banner;
    private List<JackpotImgsBean> jackpot_imgs;

    public String getLucky_value() {
        return lucky_value;
    }

    public void setLucky_value(String lucky_value) {
        this.lucky_value = lucky_value;
    }

    public int getUnopen_card() {
        return unopen_card;
    }

    public void setUnopen_card(int unopen_card) {
        this.unopen_card = unopen_card;
    }

    public String getJackpot_text() {
        return jackpot_text;
    }

    public void setJackpot_text(String jackpot_text) {
        this.jackpot_text = jackpot_text;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getOnline_award() {
        return online_award;
    }

    public void setOnline_award(int online_award) {
        this.online_award = online_award;
    }

    public int getShare_award() {
        return share_award;
    }

    public void setShare_award(int share_award) {
        this.share_award = share_award;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<JackpotImgsBean> getJackpot_imgs() {
        return jackpot_imgs;
    }

    public void setJackpot_imgs(List<JackpotImgsBean> jackpot_imgs) {
        this.jackpot_imgs = jackpot_imgs;
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 青铜英雄卡分红
         * card : 0
         * card_cha : 10
         * card_all : 10
         * status : 0
         * status_total : 1
         * money : 0
         * day : 0
         * bid : 1
         */

        private int id;
        private String name;
        private int card;
        private int card_cha;
        private int card_all;
        private int status;
        private int status_total;
        private int money;
        private int day;
        private int bid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCard() {
            return card;
        }

        public void setCard(int card) {
            this.card = card;
        }

        public int getCard_cha() {
            return card_cha;
        }

        public void setCard_cha(int card_cha) {
            this.card_cha = card_cha;
        }

        public int getCard_all() {
            return card_all;
        }

        public void setCard_all(int card_all) {
            this.card_all = card_all;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus_total() {
            return status_total;
        }

        public void setStatus_total(int status_total) {
            this.status_total = status_total;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }
    }

    public static class BannerBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/a0519bd76b3e651a/3921655ced58bfb3.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class JackpotImgsBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/384c6c100dc3587f/0dc0a974e5595724.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
