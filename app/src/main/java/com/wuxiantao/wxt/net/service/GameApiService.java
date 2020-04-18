package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CREATE_PAY_ORDER;
import static com.wuxiantao.wxt.config.Api.GET_GAME_LOADING_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GameApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-4 上午1:01
 * Description:${DESCRIPTION}
 */
public interface GameApiService {

    @POST(GET_GAME_LOADING_IMG)
    Observable<BaseResponse<String>> onGetGameLoadingImg();

    @POST(CREATE_PAY_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<AlipayBean>> onCreateAliOrder(@FieldMap Map<String,Object> map);

    @POST(CREATE_PAY_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<WeChatPayBean>> onCreateWeChatOrder(@FieldMap Map<String,Object> map);
}
