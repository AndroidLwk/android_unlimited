package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PersonalInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-1 下午3:31
 * Description:${DESCRIPTION}个人信息
 */
public class PersonalInfoBean {

    /**
     * id : 20
     * nickname : 用户18280224024
     * headimg :
     * phone : 18280224024
     * alicode :
     * aliname :
     * is_taobao : 1
     * password : 0
     */
    private int id;
    private String nickname;
    private String headimg;
    private String phone;
    private String alicode;
    private String aliname;
    private int is_taobao; //淘宝是否授权:0关，1开
    private int password;
    private int vip; //会员等级 -1 会员过期，0未注册会员，1年会员，2月会员
    private int partner;//是否是合伙人
    private String share_code;//分享码
    private int new_award_status;//是否领过新手礼包  0.未领取 1.领取过
    private int buy_status; //高用商品类型 0:0元购  1:高佣

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    private String accountname;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    private String wechat;//微信号
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlicode() {
        return alicode;
    }

    public void setAlicode(String alicode) {
        this.alicode = alicode;
    }

    public String getAliname() {
        return aliname;
    }

    public void setAliname(String aliname) {
        this.aliname = aliname;
    }

    public int getIs_taobao() {
        return is_taobao;
    }

    public void setIs_taobao(int is_taobao) {
        this.is_taobao = is_taobao;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public String getShare_code() {
        return share_code;
    }

    public void setShare_code(String share_code) {
        this.share_code = share_code;
    }

    public int getNew_award_status() {
        return new_award_status;
    }

    public void setNew_award_status(int new_award_status) {
        this.new_award_status = new_award_status;
    }

    public int getBuy_status() {
        return buy_status;
    }

    public void setBuy_status(int buy_status) {
        this.buy_status = buy_status;
    }

    @Override
    public String toString() {
        return "PersonalInfoBean{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", headimg='" + headimg + '\'' +
                ", phone='" + phone + '\'' +
                ", alicode='" + alicode + '\'' +
                ", aliname='" + aliname + '\'' +
                ", is_taobao=" + is_taobao +
                ", password=" + password +
                ", vip=" + vip +
                ", partner=" + partner +
                ", share_code='" + share_code + '\'' +
                ", new_award_status=" + new_award_status +
                ", buy_status=" + buy_status +
                ", accountname='" + accountname + '\'' +
                ", wechat='" + wechat + '\'' +
                '}';
    }
}
