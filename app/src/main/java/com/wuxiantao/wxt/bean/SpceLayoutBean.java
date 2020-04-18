package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SpceLayoutBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午6:35
 * Description:${DESCRIPTION}
 */
public class SpceLayoutBean {

    private int type;
    private String name;
    private List<CommodityInfoSpesBean.ListBean> lables;

    public static final int SPACE_TITLE = 0;
    public static final int SPACE_LABLE = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommodityInfoSpesBean.ListBean> getLables() {
        return lables;
    }

    public void setLables(List<CommodityInfoSpesBean.ListBean> lables) {
        this.lables = lables;
    }

    public SpceLayoutBean(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public SpceLayoutBean(int type,List<CommodityInfoSpesBean.ListBean> lables) {
        this.type = type;
        this.lables = lables;
    }
}
