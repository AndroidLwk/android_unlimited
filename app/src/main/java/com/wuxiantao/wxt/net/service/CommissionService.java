package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.bean.RedBagWithdrawInfoBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CHECK_IN;
import static com.wuxiantao.wxt.config.Api.COMMISSION_WITHDRAW;
import static com.wuxiantao.wxt.config.Api.GET_CHECK_IN_INFO;
import static com.wuxiantao.wxt.config.Api.GET_COMMISSION_WITHDRAW_INFO;
import static com.wuxiantao.wxt.config.Api.GET_RED_BAG_WITHDRAW_INFO;
import static com.wuxiantao.wxt.config.Api.RED_BAG_WITHDRAW;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:27
 * Description:${DESCRIPTION} 佣金
 */
public interface CommissionService {

    @POST(GET_COMMISSION_WITHDRAW_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<CommissionWithdrawInfoBean>> getCommissionWithdrawInfo(@Field("token") String token);

    @POST(GET_RED_BAG_WITHDRAW_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<RedBagWithdrawInfoBean>> getRedBagWithdrawInfo(@Field("token") String token);

    @POST(GET_CHECK_IN_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<CheckInInfoBean>> getCheckInInfo(@Field("token") String token);

    @POST(CHECK_IN)
    @FormUrlEncoded
    Observable<BaseResponse<List>> checkIn(@Field("token") String token);

    @POST(RED_BAG_WITHDRAW)
    @FormUrlEncoded
    Observable<BaseResponse<List>> redBagWithdraw(@FieldMap Map<String, Object> map);


    @POST(COMMISSION_WITHDRAW)
    @FormUrlEncoded
    Observable<BaseResponse<List>> commissionWithdraw(@FieldMap Map<String, Object> map);
}
