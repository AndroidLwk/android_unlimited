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
public class CommissionWithdrawInfoBean {


    /**
     * amount : 25.510888
     * leiji : 0
     * jijiang : 6
     * nickname : A网站制作公众号小程序开发
     * alicode : 12345678910
     * jisu : ["1","3","8"]
     * changgui : ["30","50"]
     */

    private String amount;
    private double leiji;
    private double jijiang;
    private String nickname;
    private String alicode;
    private List<String> jisu;
    private List<String> changgui;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getLeiji() {
        return leiji;
    }

    public void setLeiji(double leiji) {
        this.leiji = leiji;
    }

    public double getJijiang() {
        return jijiang;
    }

    public void setJijiang(double jijiang) {
        this.jijiang = jijiang;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAlicode() {
        return alicode;
    }

    public void setAlicode(String alicode) {
        this.alicode = alicode;
    }

    public List<String> getJisu() {
        return jisu;
    }

    public void setJisu(List<String> jisu) {
        this.jisu = jisu;
    }

    public List<String> getChanggui() {
        return changgui;
    }

    public void setChanggui(List<String> changgui) {
        this.changgui = changgui;
    }
}
