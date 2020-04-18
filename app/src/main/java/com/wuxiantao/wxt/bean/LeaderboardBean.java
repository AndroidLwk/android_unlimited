package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LeaderboardBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 下午12:47
 * Description:${DESCRIPTION} 排行榜记录
 */
public class LeaderboardBean {


    /**
     * list : [{"user_id":30,"total":178.1240635,"nickname":"酸酸，","headimg":"http://chaoren.haowusong.com./uploads/20190819/a4b339fa2234f1e38d18e8d9e593d0a6.png"},{"user_id":45,"total":0.526153,"nickname":"风吹屁屁凉","headimg":"http://chaoren.haowusong.com./uploads/20190820/8654d35c094d1ea96ee986652b5dce11.png"},{"user_id":46,"total":0.3,"nickname":"","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJUMDdWev1h25XxCCFa3Q66d8DMjaibZGSyd59odnvf1SUng8Nqkz1bauOrG5OVzTnYEojote56wrA/132"},{"user_id":38,"total":0.1721755,"nickname":"敲代码的猴子11","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/k2pSYy6W17AXnIyjPw5mwF3oG0S2WoFPvzqySrCRjdiaTF0xKunxPM6eaWcTNIaWOrmH0sDbLzarG61f0yIIRibg/132"},{"user_id":33,"total":0.0381695,"nickname":"自流水飘零花","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIW9SpOeJOWTbVoGX4Deh9ytPuXVLtTVfNjpsumhuc1Yic3P5mJJYTrQWlDnDGwmZwya9MNvkrgP1w/132"},{"user_id":20,"total":0.0357405,"nickname":"秋风落叶","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/UgFkFShR3a8X4zngCr4oDDibDZF1gqYVnicohZib0ZW9icDAyf7dYM9EVrRaYVGeQmONI84WcWPLACLrel8bkVnhOg/132"},{"user_id":44,"total":5.505E-4,"nickname":"刘运涛","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIavBvxZz8M4RzOiaVL6bO6FzC3CKHsa9lDIdriagGUcq9NLEkoakuxXkVOuGDibEibjFadD4iaSkJq91A/132"},{"user_id":36,"total":3.04E-4,"nickname":"简单","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbCQeTVFMCzazjn5YxOa9ckojlu1B2Hx0eLk4gBQwGcOkNribIAjY3iaRxaAIWHicg7GHu0ibvMLQ77Q/132"}]
     * more : 0
     */

    private int more;
    private List<ListBean> list;

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * user_id : 30
         * total : 178.1240635
         * nickname : 酸酸，
         * headimg : http://chaoren.haowusong.com./uploads/20190819/a4b339fa2234f1e38d18e8d9e593d0a6.png
         */

        private int user_id;
        private double total;
        private String nickname;
        private String headimg;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
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
