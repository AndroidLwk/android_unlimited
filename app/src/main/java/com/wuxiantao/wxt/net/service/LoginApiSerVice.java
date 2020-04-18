package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.bean.WeChatLoginBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.OBTAIN_CODE;
import static com.wuxiantao.wxt.config.Api.PHONE_LOGIN;
import static com.wuxiantao.wxt.config.Api.PHONE_REG;
import static com.wuxiantao.wxt.config.Api.RESET_PASS_WORD;
import static com.wuxiantao.wxt.config.Api.WECHAT_BINDING;
import static com.wuxiantao.wxt.config.Api.WECHAT_LOGIN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LoginApiSerVice
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-27 下午12:02
 * Description:${DESCRIPTION}
 */
public interface LoginApiSerVice {

    @POST(PHONE_REG)
    @FormUrlEncoded
    Observable<BaseResponse<PhoneLoginBean>> phoneRegister(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String password);

    @POST(WECHAT_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<WeChatLoginBean>> weChatLogin(@Field("code") String code);

    @POST(WECHAT_BINDING)
    @FormUrlEncoded
    Observable<BaseResponse<List>> weChatBinding(@Field("token") String token, @Field("code") String code);

    @POST(PHONE_LOGIN)
    @FormUrlEncoded
    Observable<BaseResponse<PhoneLoginBean>> phoneLogin(@Field("mobile") String mobile, @Field("para") String para, @Field("type") int type);

    @POST(OBTAIN_CODE)
    @FormUrlEncoded
    Observable<BaseResponse<List>> obtainCode(@Field("phone_number") String mobile, @Field("type") int type);

    @POST(RESET_PASS_WORD)
    @FormUrlEncoded
    Observable<BaseResponse<List>> resetPassWord(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String newPassWord);

}
