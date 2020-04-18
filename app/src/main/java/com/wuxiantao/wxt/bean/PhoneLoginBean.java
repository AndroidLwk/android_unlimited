package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PhoneLoginBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-27 下午1:32
 * Description:${DESCRIPTION} 手机号登陆
 */
public class PhoneLoginBean {


    /**
     * id : 20
     * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/UgFkFShR3a8X4zngCr4oDDibDZF1gqYVnicohZib0ZW9icDAyf7dYM9EVrRaYVGeQmONI84WcWPLACLrel8bkVnhOg/132
     * nickname : 余家辉
     * unionid : o1voQ1eu8wX1kqCtmW4uJkbJc8-M
     * pid : 23
     * login_time : 0
     * login_num : 0
     */

    private int id;
    private String headimg;
    private String nickname;
    private String unionid;
    private int pid;
    private long login_time;
    private int login_num;

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getLogin_time() {
        return login_time;
    }

    public void setLogin_time(long login_time) {
        this.login_time = login_time;
    }

    public int getLogin_num() {
        return login_num;
    }

    public void setLogin_num(int login_num) {
        this.login_num = login_num;
    }
}
