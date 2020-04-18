package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BannerBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午3:53
 * Description:${DESCRIPTION}
 */
public class BannerBean {


    /**
     * vip : 0.01
     * vip_img : http://super-tao.oss-cn-beijing.aliyuncs.com/540b74253bd67c10/c4c18f831e3e9ff0.png
     * list : [{"img":"http://chaoren.haowusong.com/static/upload/1f830786219024f0/484f76f2d1c22bcb.png","url":"www,wqe.com"}]
     */

    private String vip;
    private String vip_img;
    private List<ListBean> list;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getVip_img() {
        return vip_img;
    }

    public void setVip_img(String vip_img) {
        this.vip_img = vip_img;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * img : http://chaoren.haowusong.com/static/upload/1f830786219024f0/484f76f2d1c22bcb.png
         * url : www,wqe.com
         */

        private String img;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
