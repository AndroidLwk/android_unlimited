package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyTaskBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-8 下午12:34
 * Description:${DESCRIPTION}
 */
public class MyTaskBean {

    private Integer icon;
    private String title;
    private String content;
    private int type;

    //填写微信号
    public static final int WRITE_WECHAT_NO = 0x1000;
    //关注微信公众号
    public static final int ATTENTION_WECHAT = 0x1001;
    //绑定手机号
    public static final int BINDING_NUMBER = 0x1002;
    //绑定微信
    public static final int BINDING_CHAT = 0x1003;

    public MyTaskBean(Integer icon, String title, String content, int type) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public MyTaskBean(Integer icon, String title, String content) {
        this.icon = icon;
        this.title = title;
        this.content = content;
    }

    public MyTaskBean() {
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
