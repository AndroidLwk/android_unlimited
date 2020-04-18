package com.wuxiantao.wxt.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:17
 * Description:${DESCRIPTION} 潜在粉丝
 */
public class FanSiPotentialBean extends FansiSubBean{


    /**
     * list : [{"id":1,"nickname":"佳佳的酸酸","headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4u7B1PKdWONXzudtqNs2xTKecV7XdFF0ichbglPLu0Oy81sKzZSL5CzibPAxFJBNGkZDrp0HxicFibQ/132","vip":1,"partner":0,"create_at":1557824952,"wechat":""},{"id":2,"nickname":"苏苏","headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoYP5lxm3LA8rUqsgMgGDcaqM86Qy3EPZOyLSfvsBWwib46pGthF6HicYGdiazSm7RELno2qovSMcMZQ/132","vip":1,"partner":0,"create_at":1557825039,"wechat":""}]
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
         * id : 1
         * nickname : 佳佳的酸酸
         * headimg : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4u7B1PKdWONXzudtqNs2xTKecV7XdFF0ichbglPLu0Oy81sKzZSL5CzibPAxFJBNGkZDrp0HxicFibQ/132
         * vip : 1
         * partner : 0
         * create_at : 1557824952
         * wechat :
         */

        private int id;
        private String nickname;
        private String headimg;
        private int vip;
        private int partner;
        @SerializedName("create_at")
        private long create_at;
        private String wechat;

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

        public long getCreate_at() {
            return create_at;
        }

        public void setCreate_at(long create_at) {
            this.create_at = create_at;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
