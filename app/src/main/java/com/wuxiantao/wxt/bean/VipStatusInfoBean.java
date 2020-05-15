package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VipStatusInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-14 下午3:18
 * Description:${DESCRIPTION}
 */
public class VipStatusInfoBean {

    /**
     * vip : 0
     * vip_end_time : 0
     * headimg : http://super-tao.oss-cn-beijing.aliyuncs.com/uploads/20200324/e3a511f908cfba923a050ef6d4065c6c.jpg
     * nickname : 涛
     * vip_price : 29.9
     * quanyi : 充值立得10张刮刮卡
     会员签到每日可多得2张刮刮卡
     会员二维码邀请上限上升至10人，且每邀请一人获得3张刮刮卡
     我的钱包提现额度低至10元
     赠送超豪华游戏礼包包括10万游戏金币、100强化石、15飞升丹
     * banner : [{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/cdbf463e7a112704/61188ee74aadebe6.png","name":"签到刮刮卡"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/f481d4831d9403d9/e9c3d40075ccae04.png","name":"幸运值特权"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/20da8a8a4a90bed4/6c260ed2b2c8f1bc.png","name":"提现特权"},{"img":"http://super-tao.oss-cn-beijing.aliyuncs.com/1efddc4cfbbcc1ba/3b812ab55e325e17.png","name":"游戏礼包"}]
     */

    private int vip;
    private int vip_end_time;
    private String headimg;
    private String nickname;
    private double vip_price;
    private String quanyi;
    private List<BannerBean> banner;

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getVip_end_time() {
        return vip_end_time;
    }

    public void setVip_end_time(int vip_end_time) {
        this.vip_end_time = vip_end_time;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getVip_price() {
        return vip_price;
    }

    public void setVip_price(double vip_price) {
        this.vip_price = vip_price;
    }

    public String getQuanyi() {
        return quanyi;
    }

    public void setQuanyi(String quanyi) {
        this.quanyi = quanyi;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * img : http://super-tao.oss-cn-beijing.aliyuncs.com/cdbf463e7a112704/61188ee74aadebe6.png
         * name : 签到刮刮卡
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
