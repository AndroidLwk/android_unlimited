package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WeChatLoginBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 下午6:48
 * Description:${DESCRIPTION} 微信登陆
 */
public class WeChatLoginBean {

    /**
     * id : 18
     * pid : 0
     * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7JoJ0kMYcaWDRhdlIpdWu9dwsPrcLrB7eiagIH7oRFVza254zJ0LxnwqYzTnsgvzvqIHutrRZkrw/132
     * nickname : 非法昵称
     * unionid : o1voQ1ThuGsFIJDRJZ9dwgstdc2A
     * app_openid : ohiub1Dqn0aLt37fj8KCKdes9E14
     * wechat :
     */
    private int id;
    private int pid;
    private String headimg;
    private String nickname;
    private String unionid;
    private String app_openid;
    private String wechat;

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    private String accountname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getApp_openid() {
        return app_openid;
    }

    public void setApp_openid(String app_openid) {
        this.app_openid = app_openid;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}
