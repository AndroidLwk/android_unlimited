package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AlibcLoginBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-19 下午2:52
 * Description:${DESCRIPTION}
 */
public class AlibcLoginBean {

    private String userid;
    private String nick;
    private String avatarUrl;
    private String openId;
    private String openSid;
    private String topAccessToken;
    private String topAuthCode;
    private String topExpireTime;
    private String ssoToken;


    public AlibcLoginBean(String userid, String nick, String avatarUrl, String openId,
                          String openSid, String topAccessToken, String topAuthCode,
                          String topExpireTime, String ssoToken) {
        this.userid = userid;
        this.nick = nick;
        this.avatarUrl = avatarUrl;
        this.openId = openId;
        this.openSid = openSid;
        this.topAccessToken = topAccessToken;
        this.topAuthCode = topAuthCode;
        this.topExpireTime = topExpireTime;
        this.ssoToken = ssoToken;
    }

    public AlibcLoginBean(){}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenSid() {
        return openSid;
    }

    public void setOpenSid(String openSid) {
        this.openSid = openSid;
    }

    public String getTopAccessToken() {
        return topAccessToken;
    }

    public void setTopAccessToken(String topAccessToken) {
        this.topAccessToken = topAccessToken;
    }

    public String getTopAuthCode() {
        return topAuthCode;
    }

    public void setTopAuthCode(String topAuthCode) {
        this.topAuthCode = topAuthCode;
    }

    public String getTopExpireTime() {
        return topExpireTime;
    }

    public void setTopExpireTime(String topExpireTime) {
        this.topExpireTime = topExpireTime;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }
}
