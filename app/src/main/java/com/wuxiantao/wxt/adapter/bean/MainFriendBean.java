package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainFriendBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-15 上午10:25
 * Description:${DESCRIPTION}
 */
public class MainFriendBean extends BaseExampleBean {

    public static final int TYPE_INVITE = 0x10000;
    public static final int TYPE_FRIEND = 0x10001;
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
