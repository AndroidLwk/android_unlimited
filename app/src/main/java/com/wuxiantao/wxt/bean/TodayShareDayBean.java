package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TodayShareDayBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午3:56
 * Description:${DESCRIPTION} 今日邀请奖励排行榜
 */
public class TodayShareDayBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * user_id : 3
         * deposit_amount : 1957
         * nickname : A网站制作公众号小程序开发
         * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ3tWDgdcn8CnPLJnEPjaxX2N1rvOhyoOU6IWq3vahL6kiaxdJUMBVk5qBqbHXaft8JtCexmrYuu5g/132
         */

        private int user_id;
        private int deposit_amount;
        private String nickname;
        private String headimg;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getDeposit_amount() {
            return deposit_amount;
        }

        public void setDeposit_amount(int deposit_amount) {
            this.deposit_amount = deposit_amount;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
