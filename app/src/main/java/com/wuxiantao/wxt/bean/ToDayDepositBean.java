package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ToDayDepositBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午4:14
 * Description:${DESCRIPTION} 邀请好友登陆->今日昨日前日获得存款
 */
public class ToDayDepositBean {

    private String title;
    private String deposit;

    public ToDayDepositBean(String title,String deposit) {
        this.title = title;
        this.deposit = deposit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }




}
