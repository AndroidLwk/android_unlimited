package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSubBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午5:27
 * Description:${DESCRIPTION}
 */
public class TaoBaoSubBean {

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public TaoBaoSubBean(String title, String cateId) {
        this.title = title;
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String cateId;

}

