package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.HistoryRecordingBean;
import com.wuxiantao.wxt.bean.MerchandiseDetailBean;
import com.wuxiantao.wxt.bean.SearchHotBean;
import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.bean.SelfEmployedBean;
import com.wuxiantao.wxt.bean.SuperManBeneBean;
import com.wuxiantao.wxt.bean.TaoBaoHomeBean;
import com.wuxiantao.wxt.bean.TaoBaoLoginBean;
import com.wuxiantao.wxt.bean.TaoBaoOrderDetailBean;
import com.wuxiantao.wxt.bean.TaoBaoOrderListBean;
import com.wuxiantao.wxt.bean.TaoBaoSortBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CATE_SORT;
import static com.wuxiantao.wxt.config.Api.GET_TAOBAO_DETAIL;
import static com.wuxiantao.wxt.config.Api.GET_TAOBAO_ORDER_DETAIL;
import static com.wuxiantao.wxt.config.Api.HOT_KEYWORD;
import static com.wuxiantao.wxt.config.Api.RED_LIMIT_TODAY;
import static com.wuxiantao.wxt.config.Api.SEARCH_RECORDING;
import static com.wuxiantao.wxt.config.Api.SELF_EMPLOYED;
import static com.wuxiantao.wxt.config.Api.SHOPPING_LIST;
import static com.wuxiantao.wxt.config.Api.TABAO_ORDER_LIST;
import static com.wuxiantao.wxt.config.Api.TAOBAO_SEARCH;
import static com.wuxiantao.wxt.config.Api.TAO_BAO_HOME;
import static com.wuxiantao.wxt.config.Api.TAO_BAO_LOGIN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午5:27
 * Description:${DESCRIPTION}
 */
public interface TaoBaoApiService {

    @POST(HOT_KEYWORD)
    Observable<BaseResponse<SearchHotBean>> getSearchHot();

    @POST(SEARCH_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<HistoryRecordingBean>> getSearchRecording(@Field("token") String token);

    @POST(TAOBAO_SEARCH)
    @FormUrlEncoded
    Observable<BaseResponse<SearchResultBean>> onSearch(@FieldMap Map<String,Object> parameters);

    @POST(GET_TAOBAO_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<MerchandiseDetailBean>> getProductDetail(@Field("para") long para);

    @POST(CATE_SORT)
    Observable<BaseResponse<List<TaoBaoSortBean>>> getTaoBaoSort();

    @POST(TAO_BAO_HOME)
    @FormUrlEncoded
    Observable<BaseResponse<TaoBaoHomeBean>> getTaoBaoHome(@Field("page") int page,@Field("pagesize") int pagesize);

    @POST(SELF_EMPLOYED)
    Observable<BaseResponse<SelfEmployedBean>> getSelfEmployed();

    @POST(TAO_BAO_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<TaoBaoLoginBean>> taoBaoLogin(@FieldMap Map<String,Object> parameters);

    @POST(RED_LIMIT_TODAY)
    @FormUrlEncoded
    Observable<BaseResponse<TaoBaoHomeBean>> initRedEnvelope(@Field("token") String token);

    @POST(TABAO_ORDER_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<TaoBaoOrderListBean>> getTaoBaoOrderList(@FieldMap Map<String,Object> parameters);

    @POST(SHOPPING_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<SuperManBeneBean>> getShoppingList(@Field("page") int page, @Field("pagesize") int pagesize);

    @POST(GET_TAOBAO_ORDER_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<TaoBaoOrderDetailBean>> getTaoBaoOrderDetailInfo(@Field("token") String token, @Field("order_id") int order_id);
}
