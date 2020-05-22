package com.wuxiantao.wxt.bean;

import java.util.List;

public class MyCardInfo {


    /**
     * is_vip : 1
     * lucky_value : 25.00
     * unopen_card : 46
     * list : [{"id":1,"name":"青铜英雄卡分红","card":1,"card_cha":9,"card_all":10,"status":1,"status_total":1,"fen_today":1298.3999999999999,"my_today":"1298.39","rate":"0.3","bid":1},{"id":2,"name":"黄金英雄卡分红","card":0,"card_cha":10,"card_all":10,"status":1,"status_total":1,"fen_today":865.6,"my_today":"865.6","rate":"0.2","bid":2},{"id":3,"name":"整套英雄卡分红","card":1,"card_cha":19,"card_all":20,"status":1,"status_total":1,"fen_today":432.8,"my_today":"1298.39","rate":"0.1","bid":3}]
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/a084c7038d937435/952a6d580f1fa356.png"}]
     * jackpot_imgs : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/384c6c100dc3587f/0dc0a974e5595724.png","name":"1-50元现金红包"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/f34f6a8a4c31db6e/f451729a0eb0adc1.png","name":"集齐可平台分红"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/fac9738759751d43/313a64f54db8a3a2.png","name":"6-168王者皮肤卷"}]
     * is_sign : 0
     * share : 0
     * online_award : 1
     * share_award : 0
     * husao_total : 10
     * husao : 0
     */

    private int is_vip;
    private String lucky_value;
    private int unopen_card;
    private int is_sign;
    private int share;
    private int online_award;
    private int share_award;
    private int husao_total;
    private int husao;
    private List<ListBean> list;
    private List<BannerBean> banner;
    private List<JackpotImgsBean> jackpot_imgs;

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

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

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
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

    public int getHusao_total() {
        return husao_total;
    }

    public void setHusao_total(int husao_total) {
        this.husao_total = husao_total;
    }

    public int getHusao() {
        return husao;
    }

    public void setHusao(int husao) {
        this.husao = husao;
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
         * card : 1
         * card_cha : 9
         * card_all : 10
         * status : 1
         * status_total : 1
         * fen_today : 1298.3999999999999
         * my_today : 1298.39
         * rate : 0.3
         * bid : 1
         */

        private int id;
        private String name;
        private int card;
        private int card_cha;
        private int card_all;
        private int status;
        private int status_total;
        private double fen_today;
        private String my_today;
        private String rate;
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

        public double getFen_today() {
            return fen_today;
        }

        public void setFen_today(double fen_today) {
            this.fen_today = fen_today;
        }

        public String getMy_today() {
            return my_today;
        }

        public void setMy_today(String my_today) {
            this.my_today = my_today;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
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
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/a084c7038d937435/952a6d580f1fa356.png
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
         * name : 1-50元现金红包
         */

        private String img;
        private String name;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
