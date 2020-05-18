package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.CommodityInfoBean;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.OrderTypeBean;
import com.wuxiantao.wxt.bean.SelfOrderListBean;
import com.wuxiantao.wxt.bean.TaobaoLatelyOrderBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.bean.YouXuanLatelyOrderBean;
import com.wuxiantao.wxt.bean.YouXuanOrderDetailBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CHECK_ORDER_STATUS;
import static com.wuxiantao.wxt.config.Api.CREATE_ORDER;
import static com.wuxiantao.wxt.config.Api.GET_ORDER_TYPE;
import static com.wuxiantao.wxt.config.Api.GET_YOUXUAN_ORDER_DETAIL;
import static com.wuxiantao.wxt.config.Api.OBTAIN_COMMODITY_INFO;
import static com.wuxiantao.wxt.config.Api.RECENT_ORDER;
import static com.wuxiantao.wxt.config.Api.SELF_ORDER_LIST;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午10:36
 * Description:${DESCRIPTION}
 */
public interface OrderApiService {

    @POST(OBTAIN_COMMODITY_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<CommodityInfoBean>> obtainCommodityInfo(@Field("good_id") int good_id);

    @POST(CREATE_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<WeChatPayBean>> createOrderWeChat(@FieldMap Map<String, Object> parameters);

    @POST(CREATE_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<AlipayBean>> createOrderAlipay(@FieldMap Map<String, Object> parameters);

    @POST(CHECK_ORDER_STATUS)
    @FormUrlEncoded
    Observable<BaseResponse<OrderStatusBean>> checkOrderStatus(@Field("token") String token, @Field("order_id") String order_id);

    @POST(SELF_ORDER_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<SelfOrderListBean>> getSelfOrderList(@FieldMap Map<String, Object> parameters);

    @POST(GET_YOUXUAN_ORDER_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<YouXuanOrderDetailBean>> getOrderYouXuanDetailInfo(@Field("token") String token, @Field("order_id") int order_id);

    @POST(RECENT_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<TaobaoLatelyOrderBean>> getTaobaoLatelyOrder(@Field("token") String token, @Field("order_no") String order_no);

    @POST(RECENT_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<YouXuanLatelyOrderBean>> getYouXuanLatelyOrder(@Field("token") String token, @Field("order_no") String order_no);

    @POST(GET_ORDER_TYPE)
    @FormUrlEncoded
    Observable<BaseResponse<OrderTypeBean>> getOrderType(@Field("token") String token);
}
