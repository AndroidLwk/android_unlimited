package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyFansiHeadInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午8:00
 * Description:${DESCRIPTION}
 */
public class MyFansiHeadInfoBean {


    /**
     * paytribute : 321
     * fenall : 2
     * myup : {"id":1,"nickname":"佳佳的酸酸","headimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4u7B1PKdWONXzudtqNs2xTKecV7XdFF0ichbglPLu0Oy81sKzZSL5CzibPAxFJBNGkZDrp0HxicFibQ/132","wechat":""}
     */

    private double paytribute;
    private int fenall;
    private MyupBean myup;

    public double getPaytribute() {
        return paytribute;
    }

    public void setPaytribute(double paytribute) {
        this.paytribute = paytribute;
    }

    public int getFenall() {
        return fenall;
    }

    public void setFenall(int fenall) {
        this.fenall = fenall;
    }

    public MyupBean getMyup() {
        return myup;
    }

    public void setMyup(MyupBean myup) {
        this.myup = myup;
    }

    public static class MyupBean {
        /**
         * id : 1
         * nickname : 佳佳的酸酸
         * headimg : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4u7B1PKdWONXzudtqNs2xTKecV7XdFF0ichbglPLu0Oy81sKzZSL5CzibPAxFJBNGkZDrp0HxicFibQ/132
         * wechat :
         */

        private int id;
        private String nickname;
        private String headimg;
        private String wechat;

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

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
