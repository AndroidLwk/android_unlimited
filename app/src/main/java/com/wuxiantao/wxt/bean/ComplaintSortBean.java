package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ComplaintSortBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-3 下午10:46
 * Description:${DESCRIPTION}
 */
public class ComplaintSortBean {

    /**
     * id : 1
     * name : 意见
     */

    private int id;

    public ComplaintSortBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ComplaintSortBean(){}

    private String name;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
