package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareBackGroundBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:27
 * Description:${DESCRIPTION}
 */
public class ShareBackGroundBean {


    /**
     * list : [{"id":3,"img":"http://chaoren.haowusong.com/static/upload/264c8a4df6513422/d48c8398df585f45.jpg","theme _name":"主题4"},{"id":4,"img":"http://chaoren.haowusong.com/static/upload/264c8a4df6513422/d48c8398df585f45.jpg","theme _name":"主题5"},{"id":5,"img":"http://chaoren.haowusong.com/static/upload/264c8a4df6513422/d48c8398df585f45.jpg","theme _name":""}]
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
         * id : 3
         * img : http://chaoren.haowusong.com/static/upload/264c8a4df6513422/d48c8398df585f45.jpg
         * theme _name : 主题4
         */

        private int id;
        private String img;
        private String theme_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }
    }
}
