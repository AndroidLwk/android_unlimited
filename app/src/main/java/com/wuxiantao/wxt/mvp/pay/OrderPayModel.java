package com.wuxiantao.wxt.mvp.pay;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.GameApiService;
import com.wuxiantao.wxt.net.service.OrderApiService;

import java.util.Map;

import retrofit2.http.FieldMap;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderPayModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:33
 * Description:${DESCRIPTION}
 */
public class OrderPayModel extends BaseModel {

    /**
     *  确认订单 微信
     * @param //type 支付类型：1为支付宝，2为微信
     * @param //order_no 订单号,选填
     * @param //goods_id 商品ID,必填
     * @param //address_id 收货地址ID,必填
     * @param //selectName 属性规格 ，字符串类型，如： 颜色:黑白各一件;尺码:L，选填
     * @param //desc 备注,选填
     */
    public void onOrderCreateWeChat(BaseObserver<WeChatPayBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .createOrderWeChat(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    /**
     *  确认订单 支付宝
     * @param //type 支付类型：1为支付宝，2为微信
     * @param //order_no 订单号,选填
     * @param //goods_id 商品ID,必填
     * @param //address_id 收货地址ID,必填
     * @param //selectName 属性规格 ，字符串类型，如： 颜色:黑白各一件;尺码:L，选填
     * @param //desc 备注,选填
     */
    public void onOrderCreateAlipay(BaseObserver<AlipayBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .createOrderAlipay(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onCreateAliOrder(BaseObserver<AlipayBean> observer,@FieldMap Map<String,Object> map){
        HttpManager.newInstance()
                .createService(GameApiService.class)
                .onCreateAliOrder(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    public void onCreateWeChatOrder(BaseObserver<WeChatPayBean> observer,@FieldMap Map<String,Object> map){
        HttpManager.newInstance()
                .createService(GameApiService.class)
                .onCreateWeChatOrder(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }


    /**
     * 验证订单状态
     * @param observer observer
     * @param token token
     * @param order_id 订单号
     */
    public void checkOrderStatus(BaseObserver<OrderStatusBean> observer, String token, String order_id){
        HttpManager.newInstance()
                .createService(OrderApiService.class)
                .checkOrderStatus(token,order_id)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
