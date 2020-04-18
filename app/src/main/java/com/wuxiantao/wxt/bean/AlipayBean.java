package com.wuxiantao.wxt.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AlipayBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 下午3:50
 * Description:${DESCRIPTION}
 */
public class AlipayBean {


    /**
     * order_id : 41
     * alipay_message : alipay_sdk=alipay-sdk-php-20180705&app_id=2016100100641424&biz_content=%7B%22body%22%3A%22%5Cu5e74%5Cu4f1a%5Cu5458%5Cu4ea7%5Cu54c1%22%2C%22subject%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%2220190713154616375430%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.SPApplication.pay&notify_url=https%3A%2F%2Fchaoren.haowusong.com%2Fapi%2Fpay%2Fnotify&sign_type=RSA2&timestamp=2019-07-13+15%3A46%3A16&version=1.0&sign=Yabd%2FeT5MpgCkDMDZrwcsV2%2F6oPiPD0S5BpBgLdrPBt2gi6%2BCHbHQ35zYjz48zw4b6YTfsiaXFCkbcBwc6DmSh8JbAaXx5yo6XPGARa65Y2ibY9nw2VP7cNiKAhwdIPl%2FtKHB1kSXfCLAbbaheHmfuIHzADP45hrZfmzcXmQwdancyH1tb2mXu1iLRlDfPy2WOa4LLqRgBsuvIP9yNp0jqW9Ply6MAYh8R10LTH%2B6R%2FHWQUBlmElufD%2BoWCQH4Nrv%2FDP4KdllwSP8KXYwRsbmUAxDB9teFOQ1zZ8tVr8iybPkL6XwVOZISeixDCX%2FfR2hoPt7egqlU%2Fo%2FedaFqJuCw%3D%3D
     */

    private String order_id;
    private String alipay_message;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAlipay_message() {
        return alipay_message;
    }

    public void setAlipay_message(String alipay_message) {
        this.alipay_message = alipay_message;
    }
}
