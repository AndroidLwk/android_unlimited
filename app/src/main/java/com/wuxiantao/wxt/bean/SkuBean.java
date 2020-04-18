package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SkuBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 下午2:56
 * Description:${DESCRIPTION}
 */
public class SkuBean {

    private String name;
    private String value;

    public SkuBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return  name + ":" + value;
    }
}
