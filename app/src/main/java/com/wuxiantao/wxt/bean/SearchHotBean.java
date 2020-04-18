package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchHotBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午3:27
 * Description:${DESCRIPTION}
 */
public class SearchHotBean {


    /**
     * result : []
     * count : 10
     * querys : ["半身裙","板鞋","内裤女","靴子","马甲男","小智淘宝店男装","西裤","外套","男士裤子","杰克琼斯"]
     */

    private String count;
    private List<?> result;
    private List<String> querys;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

    public List<String> getQuerys() {
        return querys;
    }

    public void setQuerys(List<String> querys) {
        this.querys = querys;
    }
}
