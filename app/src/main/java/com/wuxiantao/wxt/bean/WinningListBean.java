package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:10
 * Description:${DESCRIPTION}
 */
public class WinningListBean {


    /**
     * list : [{"id":3452352,"user_id":4,"types":151,"num":"1.00","msg":"抽奖获得1.00把银钥匙","time":1573736877,"fen_id":0,"nickname":"刘运涛","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIavBvxZz8M4RzOiaVL6bO6FzC3CKHsa9lDIdriagGUcq9NLEkoakuxXkVOuGDibEibjFadD4iaSkJq91A/132"},{"id":3447938,"user_id":44,"types":151,"num":"1.00","msg":"抽奖获得1.00把铜钥匙","time":1573703211,"fen_id":0,"nickname":"用户18328088390","headimg":""},{"id":3447923,"user_id":44,"types":151,"num":"2.00","msg":"抽奖获得2.00把铜钥匙","time":1573703091,"fen_id":0,"nickname":"用户18328088390","headimg":""},{"id":3447905,"user_id":44,"types":151,"num":"1.00","msg":"抽奖获得1.00把银钥匙","time":1573702929,"fen_id":0,"nickname":"用户18328088390","headimg":""},{"id":3422568,"user_id":4,"types":151,"num":"1.00","msg":"抽奖获得1.00把金钥匙","time":1573536057,"fen_id":0,"nickname":"刘运涛","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIavBvxZz8M4RzOiaVL6bO6FzC3CKHsa9lDIdriagGUcq9NLEkoakuxXkVOuGDibEibjFadD4iaSkJq91A/132"}]
     * count : 5
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3452352
         * user_id : 4
         * types : 151
         * num : 1.00
         * msg : 抽奖获得1.00把银钥匙
         * time : 1573736877
         * fen_id : 0
         * nickname : 刘运涛
         * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIavBvxZz8M4RzOiaVL6bO6FzC3CKHsa9lDIdriagGUcq9NLEkoakuxXkVOuGDibEibjFadD4iaSkJq91A/132
         */

        private int id;
        private int user_id;
        private int types;
        private String num;
        private String msg;
        private int time;
        private int fen_id;
        private String nickname;
        private String headimg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getFen_id() {
            return fen_id;
        }

        public void setFen_id(int fen_id) {
            this.fen_id = fen_id;
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
