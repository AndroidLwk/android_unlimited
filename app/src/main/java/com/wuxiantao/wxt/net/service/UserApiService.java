package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.BalanceBean;
import com.wuxiantao.wxt.bean.HandValueBean;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.bean.OnLineServiceBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.UpdataLoadFileBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static com.wuxiantao.wxt.config.Api.BALANCE_WITHDRAW;
import static com.wuxiantao.wxt.config.Api.IS_SET_PAY_PWD;
import static com.wuxiantao.wxt.config.Api.NEW_USER_RECEIVE_RB;
import static com.wuxiantao.wxt.config.Api.OBTAIN_BALANCE;
import static com.wuxiantao.wxt.config.Api.OBTAIN_LUCK;
import static com.wuxiantao.wxt.config.Api.OBTAIN_MY_PAGE;
import static com.wuxiantao.wxt.config.Api.OBTAIN_PERSONAL;
import static com.wuxiantao.wxt.config.Api.ON_LINE_SERVICE;
import static com.wuxiantao.wxt.config.Api.SET_CHANGE_PAY_PWD;
import static com.wuxiantao.wxt.config.Api.SET_USER_PASS_WORD;
import static com.wuxiantao.wxt.config.Api.UPDATE_PERSONAL_CENTER;
import static com.wuxiantao.wxt.config.Api.UPLOAD_FILE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:UserApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-1 下午4:03
 * Description:${DESCRIPTION}
 */
public interface UserApiService {

    @POST(OBTAIN_MY_PAGE)
    @FormUrlEncoded
    Observable<BaseResponse<MyDepositBean>> obtainMyDeposit(@Field("token") String token);

    @POST(OBTAIN_PERSONAL)
    @FormUrlEncoded
    Observable<BaseResponse<PersonalInfoBean>> obtainPersonal(@Field("token") String token);

    @POST(UPDATE_PERSONAL_CENTER)
    @FormUrlEncoded
    Observable<BaseResponse<List>> modifyPersonal(@FieldMap Map<String,Object> parameters);

    @Multipart
    @POST(UPLOAD_FILE)
    Observable<BaseResponse<UpdataLoadFileBean>> uploadFiles(@Part MultipartBody.Part file);

    @POST(SET_USER_PASS_WORD)
    @FormUrlEncoded
    Observable<BaseResponse<List>> setUserLoginPassWord(@Field("token") String token,
                                                        @Field("password_old") String password_old,
                                                        @Field("password_new") String password_new);

    @POST(OBTAIN_BALANCE)
    @FormUrlEncoded  //我的余额和累计提现金额
    Observable<BaseResponse<BalanceBean>> obtainBalance(@Field("token") String token);

    @POST(OBTAIN_LUCK)
    @FormUrlEncoded
    Observable<BaseResponse<HandValueBean>> obtainLuck(@Field("token") String token);

    @POST(BALANCE_WITHDRAW)
    @FormUrlEncoded
    Observable<BaseResponse<List>> applyWithdraw(@Field("token") String token,@Field("money") String money,@Field("type") int type);


    @POST(ON_LINE_SERVICE)
    @FormUrlEncoded
    Observable<BaseResponse<OnLineServiceBean>> getOnLineServiceInfo(@Field("token") String token);


    @POST(NEW_USER_RECEIVE_RB)
    @FormUrlEncoded
    Observable<BaseResponse<List>> receiveRedBag(@Field("token") String token);

    @POST(IS_SET_PAY_PWD)
    @FormUrlEncoded      //是否设置支付密码
    Observable<BaseResponse<IsSetPayPassword>> isSetPayPassword(@Field("token") String token);

    @POST(SET_CHANGE_PAY_PWD)
    @FormUrlEncoded     //设置或修改交易密码
    Observable<BaseResponse<List>> setUserPayPassword(@FieldMap Map<String,Object> parameters);
}
