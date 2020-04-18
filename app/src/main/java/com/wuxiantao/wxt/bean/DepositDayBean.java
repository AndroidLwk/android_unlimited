package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DepositDayBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:54
 * Description:${DESCRIPTION} 邀请奖励存款 昨日-今日-前日
 */
public class DepositDayBean {


    /**
     * deposit_all : 100
     * today : 0
     * yesterday : 0
     * beforeyesterday : 0
     * about : {"id":3,"title":"分享规则","digest":"分享规则","content":"<p>邀请的好友登陆后可以领取系统发放的一定金额的存款， 好友领取存款后你也可以获得相同金额的存款。每天领 取存款上限2000元。<\/p>\n\n<p>好友购物获得存款时，你也可以获得20％。当你成为会 员后，好友购物获得存款时，你会获得40％。<\/p>\n\n<p>当你的好友成为会员时，你可以获得2000元存款奖励。<\/p>\n\n<p>如发现用户有作弊等不正当行为，存款超人有权回收奖 励，取消用户参与资格。<\/p>\n\n<p>存款超人有权调整活动时间或终止活动。存款超人对本 活动享有最终解释权，如有疑问，请联系客服。<\/p>\n","create_at":"2019-05-27 15:28:59"}
     */

    private int deposit_all;
    private int today;
    private int yesterday;
    private int beforeyesterday;
    private AboutBean about;

    public int getDeposit_all() {
        return deposit_all;
    }

    public void setDeposit_all(int deposit_all) {
        this.deposit_all = deposit_all;
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }

    public int getYesterday() {
        return yesterday;
    }

    public void setYesterday(int yesterday) {
        this.yesterday = yesterday;
    }

    public int getBeforeyesterday() {
        return beforeyesterday;
    }

    public void setBeforeyesterday(int beforeyesterday) {
        this.beforeyesterday = beforeyesterday;
    }

    public AboutBean getAbout() {
        return about;
    }

    public void setAbout(AboutBean about) {
        this.about = about;
    }

    public static class AboutBean {
        /**
         * id : 3
         * title : 分享规则
         * digest : 分享规则
         * content : <p>邀请的好友登陆后可以领取系统发放的一定金额的存款， 好友领取存款后你也可以获得相同金额的存款。每天领 取存款上限2000元。</p>

         <p>好友购物获得存款时，你也可以获得20％。当你成为会 员后，好友购物获得存款时，你会获得40％。</p>

         <p>当你的好友成为会员时，你可以获得2000元存款奖励。</p>

         <p>如发现用户有作弊等不正当行为，存款超人有权回收奖 励，取消用户参与资格。</p>

         <p>存款超人有权调整活动时间或终止活动。存款超人对本 活动享有最终解释权，如有疑问，请联系客服。</p>
         * create_at : 2019-05-27 15:28:59
         */

        private int id;
        private String title;
        private String digest;
        private String content;
        private String create_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }
    }
}
