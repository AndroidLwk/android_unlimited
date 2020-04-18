package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiDirectlyBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午7:20
 * Description:${DESCRIPTION} 直属粉丝
 */
public class FansiDirectlyBean {


    /**
     * list : [{"id":11,"nickname":"","headimg":"","create_at":1560485204,"vip":0,"partner":0,"rebate1":0,"fnum":0},{"id":18,"nickname":"腹黑教官","headimg":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg","create_at":1561611202,"vip":0,"partner":0,"rebate1":0,"fnum":0},{"id":7,"nickname":"张晴明","headimg":"https://wx.qlogo.cn/mmhead/PDUdrUmQaWic6TRFsy3zMwxfibBibN4icMbKo9cl8mEa2cg/132","create_at":1557829409,"vip":1,"partner":0,"rebate1":0,"fnum":2}]
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
         * id : 11
         * nickname :
         * headimg :
         * create_at : 1560485204
         * vip : 0
         * partner : 0
         * rebate : 0 //进贡
         * fnum : 0 //粉丝数量
         */

        private int id;
        private String nickname;
        private String headimg;
        private int create_at;
        private int vip;
        private int partner;
        private int rebate1;
        private int fnum;

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

        public int getCreate_at() {
            return create_at;
        }

        public void setCreate_at(int create_at) {
            this.create_at = create_at;
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

        public int getRebate1() {
            return rebate1;
        }

        public void setRebate1(int rebate1) {
            this.rebate1 = rebate1;
        }

        public int getFnum() {
            return fnum;
        }

        public void setFnum(int fnum) {
            this.fnum = fnum;
        }
    }
}
