package com.wuxiantao.wxt.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WeChatPayBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 下午3:58
 * Description:${DESCRIPTION}
 */
public class WeChatPayBean {


    /**
     * appid : wxb0edfd75f76d8c1a
     * partnerid : 1528325511
     * prepayid : wx131605215780892f8984d25d1211248900
     * noncestr : 5d2990c199314
     * timestamp : 1563005121
     * package : Sign=WXPay
     * sign : E1351054252DD34CE2DCB0AF310306F1
     * order_id : 43
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private long timestamp;
    @SerializedName("package")
    private String packageValue;
    private String sign;
    private String order_id;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
