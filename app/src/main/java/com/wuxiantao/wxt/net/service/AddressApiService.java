package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.bean.DefaultAddressBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.ADD_ADDRESS;
import static com.wuxiantao.wxt.config.Api.DELETE_ADDRESS;
import static com.wuxiantao.wxt.config.Api.EDIT_ADDRESS;
import static com.wuxiantao.wxt.config.Api.GET_ADDRESS_LIST;
import static com.wuxiantao.wxt.config.Api.GET_DEFAULT_ADDRESS;
import static com.wuxiantao.wxt.config.Api.SET_DEFAULT_ADDRESS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 上午11:39
 * Description:${DESCRIPTION}
 */
public interface AddressApiService {

    @POST(EDIT_ADDRESS)
    @FormUrlEncoded
    Observable<BaseResponse<List>> updateAddress(@FieldMap Map<String, Object> parameters);

    @POST(ADD_ADDRESS)
    @FormUrlEncoded
    Observable<BaseResponse<String>> addAddress(@FieldMap Map<String, Object> parameters);

    @POST(SET_DEFAULT_ADDRESS)
    @FormUrlEncoded
    Observable<BaseResponse<List>> setDefaultAddress(@Field("token") String token, @Field("address_id") String address_id);

    @POST(GET_DEFAULT_ADDRESS)
    @FormUrlEncoded
    Observable<BaseResponse<DefaultAddressBean>> getDefaultAddress(@Field("token") String token);

    @POST(DELETE_ADDRESS)
    @FormUrlEncoded
    Observable<BaseResponse<List>> deleteAddress(@Field("token") String token, @Field("address_id") String address_id);

    @POST(GET_ADDRESS_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<AddressListBean>> getAddressList(@Field("token") String token, @Field("page") int page, @Field("pagesize") int pagesize);
}
