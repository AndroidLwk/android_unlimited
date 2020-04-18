package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommodityInfoSpesBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午5:46
 * Description:${DESCRIPTION} 自营商品规格
 */
public class CommodityInfoSpesBean {


    /**
     * name : 颜色
     * list : [{"name":"红","check":true},{"name":"白","check":true,"url":"属性图片"},{"name":"黑","check":true,"url":"属性图片"}]
     */

    private String name;
    private List<ListBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 红
         * check : true
         * url : 属性图片
         */

        private String name;
        private boolean check;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
