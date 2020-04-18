package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiIndirectBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:16
 * Description:${DESCRIPTION} 间接粉丝
 */
public class FansiIndirectBean extends FansiSubBean{


    /**
     * list : [{"id":24,"nickname":"小明","headimg":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg","vip":0,"partner":0,"pid":21,"create_at":1561611202,"rebate2":0,"sp":[{"id":21,"nickname":"用户18381831680"}]},{"id":25,"nickname":"小白","headimg":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg","vip":0,"partner":0,"pid":21,"create_at":1561611202,"rebate2":0,"sp":[{"id":21,"nickname":"用户18381831680"}]},{"id":26,"nickname":"小黑","headimg":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg","vip":0,"partner":0,"pid":7,"create_at":1562216002,"rebate2":0,"sp":[{"id":7,"nickname":"张晴明"}]}]
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
         * id : 24
         * nickname : 小明
         * headimg : http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg
         * vip : 0
         * partner : 0
         * pid : 21
         * create_at : 1561611202
         * rebate2 : 0
         * sp : [{"id":21,"nickname":"用户18381831680"}]
         */

        private int id;
        private String nickname;
        private String headimg;
        private int vip;
        private int partner;
        private int pid;
        private int create_at;
        private int rebate2;
        private List<SpBean> sp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public int getPartner() {
            return partner;
        }

        public void setPartner(int partner) {
            this.partner = partner;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getCreate_at() {
            return create_at;
        }

        public void setCreate_at(int create_at) {
            this.create_at = create_at;
        }

        public int getRebate2() {
            return rebate2;
        }

        public void setRebate2(int rebate2) {
            this.rebate2 = rebate2;
        }

        public List<SpBean> getSp() {
            return sp;
        }

        public void setSp(List<SpBean> sp) {
            this.sp = sp;
        }

        public static class SpBean {
            /**
             * id : 21
             * nickname : 用户18381831680
             */

            private int id;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
