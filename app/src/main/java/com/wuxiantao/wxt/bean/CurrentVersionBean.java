package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CurrentVersionBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午12:36
 * Description:${DESCRIPTION}
 */
public class CurrentVersionBean {


    /**
     * version_ios : 3.1.2
     * version_andorid : 11.2
     * site_url : https://android.myapp.com/myapp/detail.htm?apkName=com.baolai.zsyx&ADTAG=mobile
     */

    private String version_ios;
    private String version_andorid;
    private String site_url;

    public String getVersion_ios() {
        return version_ios;
    }

    public void setVersion_ios(String version_ios) {
        this.version_ios = version_ios;
    }

    public String getVersion_andorid() {
        return version_andorid;
    }

    public void setVersion_andorid(String version_andorid) {
        this.version_andorid = version_andorid;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }
}
