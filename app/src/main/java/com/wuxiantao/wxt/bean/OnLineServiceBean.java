package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-21 下午1:57
 * Description:${DESCRIPTION}
 */
public class OnLineServiceBean {

    private List<String> wx;
    private List<String> tel;
    /**
     * kfimg : https://chaoren.haowusong.com/static/kf.jpg?r=1569393886
     */

    private String kfimg;

    public List<String> getWx() {
        return wx;
    }

    public void setWx(List<String> wx) {
        this.wx = wx;
    }

    public List<String> getTel() {
        return tel;
    }

    public void setTel(List<String> tel) {
        this.tel = tel;
    }

    public String getKfimg() {
        return kfimg;
    }

    public void setKfimg(String kfimg) {
        this.kfimg = kfimg;
    }
}
