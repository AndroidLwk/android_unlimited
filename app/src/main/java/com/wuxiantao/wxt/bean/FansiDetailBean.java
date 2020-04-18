package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午10:25
 * Description:${DESCRIPTION} 粉丝详情
 */
public class FansiDetailBean {


    /**
     * info : {"nickname":"小绿","headimg":"http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg","wechat":"","share_code":"73UT8Q"}
     * lastcol : 0
     * rates : 0
     */

    private InfoBean info;
    private int lastcol;
    private String rates;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getLastcol() {
        return lastcol;
    }

    public void setLastcol(int lastcol) {
        this.lastcol = lastcol;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public static class InfoBean {
        /**
         * nickname : 小绿
         * headimg : http://chaoren.haowusong.com./uploads/20190703/9445a0f59d6a73b709d3e121b55c768f.jpg
         * wechat :
         * share_code : 73UT8Q
         */

        private String nickname;
        private String headimg;
        private String wechat;
        private String share_code;

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

        public String getShare_code() {
            return share_code;
        }

        public void setShare_code(String share_code) {
            this.share_code = share_code;
        }
    }
}
