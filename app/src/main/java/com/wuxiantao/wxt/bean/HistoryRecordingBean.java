package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HistoryRecordingBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午6:46
 * Description:${DESCRIPTION}
 */
public class HistoryRecordingBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 21
         * keyword : 车子
         */

        private int id;
        private String keyword;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
