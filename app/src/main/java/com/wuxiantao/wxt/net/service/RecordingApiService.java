package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.BalanceDetailBean;
import com.wuxiantao.wxt.bean.HandValueRecordingBean;
import com.wuxiantao.wxt.bean.LeaderboardBean;
import com.wuxiantao.wxt.bean.LogRecordingBean;
import com.wuxiantao.wxt.bean.TotalDepositBean;
import com.wuxiantao.wxt.bean.WithdrawRecordingBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.BALANCE_DETAIL;
import static com.wuxiantao.wxt.config.Api.DEPOSIT_DETAIL;
import static com.wuxiantao.wxt.config.Api.HAND_VALUE_RECORDING;
import static com.wuxiantao.wxt.config.Api.LEADERBOARD_RECORDING;
import static com.wuxiantao.wxt.config.Api.SUPER_LOG_RECORDING;
import static com.wuxiantao.wxt.config.Api.WITHDRAW_RECORDING;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RecordingApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午6:05
 * Description:${DESCRIPTION}
 */
public interface RecordingApiService {

    @POST(BALANCE_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<BalanceDetailBean>> obtainBalanceDetails(@Field("token") String token,
                                                                     @Field("page") int page,
                                                                     @Field("pagesize") int pagesize);

    @POST(WITHDRAW_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<WithdrawRecordingBean>> obtainWithdrawRecording(@Field("token") String token,
                                                                            @Field("page") int page,
                                                                            @Field("pagesize") int pagesize);

    @POST(DEPOSIT_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<TotalDepositBean>> obtainDepositDetail(@Field("token") String token,
                                                                   @Field("page") int page,
                                                                   @Field("pagesize") int pagesize);

    @POST(LEADERBOARD_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<LeaderboardBean>> obtainLeaderboardRecording(
            @Field("token") String token,
            @Field("page") int page,
            @Field("type") int type,
            @Field("pagesize") int pagesize);

    @POST(HAND_VALUE_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<HandValueRecordingBean>> obtainHandValueRecording(@Field("token") String token,
                                                                              @Field("page") int page,
                                                                              @Field("pagesize") int pagesize);

    @POST(SUPER_LOG_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<LogRecordingBean>> obtainLogRecording(@Field("token") String token,
                                                                  @Field("page") int page,
                                                                  @Field("type") int type,
                                                                  @Field("pagesize") int pagesize);
}
