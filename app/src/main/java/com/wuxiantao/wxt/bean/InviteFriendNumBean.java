package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteFriendNumBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午12:15
 * Description:${DESCRIPTION}
 */
public class InviteFriendNumBean {


    /**
     * friend_num : 5
     * start_time : 1563244636
     * end_time : 1565836636
     * user : [{"id":7,"header":"https://wx.qlogo.cn/mmhead/PDUdrUmQaWic6TRFsy3zMwxfibBibN4icMbKo9cl8mEa2cg/132"},{"id":11,"header":""},{"id":18,"header":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg"},{"id":27,"header":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg"},{"id":28,"header":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg"}]
     */

    private int friend_num;
    private int start_time;
    private int end_time;
    private List<UserBean> user;

    public int getFriend_num() {
        return friend_num;
    }

    public void setFriend_num(int friend_num) {
        this.friend_num = friend_num;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 7
         * header : https://wx.qlogo.cn/mmhead/PDUdrUmQaWic6TRFsy3zMwxfibBibN4icMbKo9cl8mEa2cg/132
         */

        private int id;
        private String header;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }
    }
}
