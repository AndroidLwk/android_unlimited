package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-3 下午7:08
 * Description:${DESCRIPTION}
 */
public class WithdrawBean {

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private double money;

    public WithdrawBean(double money, boolean isSelected) {
        this.money = money;
        this.isSelected = isSelected;
    }

    private boolean isSelected;


}
