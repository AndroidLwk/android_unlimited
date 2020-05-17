package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CREATE_CZ_ORDER;
import static com.wuxiantao.wxt.config.Api.CREATE_PAY_ORDER;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/16--16:57
 * Description: 充值Service
 * Author: lht
 */
public interface PayService {

    @POST(CREATE_CZ_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<AlipayBean>> onCreateAliOrder(@FieldMap Map<String,Object> map);

    @POST(CREATE_CZ_ORDER)
    @FormUrlEncoded
    Observable<BaseResponse<WeChatPayBean>> onCreateWeChatOrder(@FieldMap Map<String,Object> map);
}
