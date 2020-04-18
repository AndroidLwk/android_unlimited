package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutSuperManBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 下午4:38
 * Description:${DESCRIPTION}
 */
public class AboutSuperManBean {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String title;
    private String version;

    public AboutSuperManBean(String title, String version) {
        this.title = title;
        this.version = version;
    }

}
