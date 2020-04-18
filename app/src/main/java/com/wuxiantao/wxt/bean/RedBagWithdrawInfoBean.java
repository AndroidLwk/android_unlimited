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
     * volumes : 0.3170000
     * nickname : 醉清风
     * alicode :
     * show_money : [0.3,20,30,50,100,300,500]
     */

    private String volumes;
    private String nickname;
    private String alicode;
    private List<Double> show_money;

    public String getVolumes() {
        return volumes;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
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

    public List<Double> getShow_money() {
        return show_money;
    }

    public void setShow_money(List<Double> show_money) {
        this.show_money = show_money;
    }
}
