package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineMenuBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-29 下午5:49
 * Description:${DESCRIPTION}
 */
public class MineMenuBean extends BaseExampleBean {

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

    private int icon;
    private String title;

    public boolean isShowSmallIcon() {
        return isShowSmallIcon;
    }

    public void setShowSmallIcon(boolean showSmallIcon) {
        isShowSmallIcon = showSmallIcon;
    }

    private boolean isShowSmallIcon;

    public MineMenuBean(int icon, String title,boolean isShowSmallIcon) {
        this.icon = icon;
        this.title = title;
        this.isShowSmallIcon = isShowSmallIcon;
    }
}
