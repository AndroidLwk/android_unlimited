package com.wuxiantao.wxt.db;

import org.xutils.db.annotation.Column;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:User
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午2:50
 * Description:${DESCRIPTION}
 */
public class User {

    //id   列名主键，主动增长
    @Column(name = "id", isId = true, autoGen = true)
    private int id;//列名

    //临时token
    @Column(name = "token")
    private String token;//列名

    //头像
    @Column(name = "headimg")
    private String headimg; //列名

    //昵称
    @Column(name = "nickname")
    private String nickname; //列名

    //绑定的手机号
    @Column(name = "number")
    private String number; //列名

    //支付宝账号
    @Column(name = "alicode")
    private String alicode; //列名

    //支付宝姓名
    @Column(name = "aliname")
    private String aliname; //列名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

}
