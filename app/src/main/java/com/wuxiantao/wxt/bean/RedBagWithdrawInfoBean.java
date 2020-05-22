package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionWithdrawInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:57
 * Description:${DESCRIPTION} 佣金提现 信息bean
 */
public class RedBagWithdrawInfoBean {


    /**
     * money : 2488.40
     * alicode : 1214347737@qq.com
     * is_wx : 1
     * show_money : [30,60,90,300]
     */

    private String money;
    private String alicode;
    private int is_wx;
    private List<Integer> show_money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAlicode() {
        return alicode;
    }

    public void setAlicode(String alicode) {
        this.alicode = alicode;
    }

    public int getIs_wx() {
        return is_wx;
    }

    public void setIs_wx(int is_wx) {
        this.is_wx = is_wx;
    }

    public List<Integer> getShow_money() {
        return show_money;
    }

    public void setShow_money(List<Integer> show_money) {
        this.show_money = show_money;
    }
}
