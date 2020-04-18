package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderPayModeBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-22 下午5:55
 * Description:${DESCRIPTION}
 */
public class OrderPayModeBean {

    private int payIcon;
    private String payName;
    private boolean paySelected;

    public int getPayIcon() {
        return payIcon;
    }

    public void setPayIcon(int payIcon) {
        this.payIcon = payIcon;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public boolean isPaySelected() {
        return paySelected;
    }

    public void setPaySelected(boolean paySelected) {
        this.paySelected = paySelected;
    }

}
