package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SupermanMenuBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-15 上午9:33
 * Description:${DESCRIPTION}
 */
public class SupermanMenuBean {

    private int icon;
    private String title;
    private String content;
    private String text;

    public SupermanMenuBean(){}

    public SupermanMenuBean(int icon, String title, String content, String text) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
