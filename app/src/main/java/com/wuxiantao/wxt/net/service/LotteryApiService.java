package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.LotteryBean;
import com.wuxiantao.wxt.bean.LotteryListBean;
import com.wuxiantao.wxt.bean.LotteryRecordingBean;
import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.GET_LOTTERY_LIST;
import static com.wuxiantao.wxt.config.Api.LOTTERY_ALL_LOG;
import static com.wuxiantao.wxt.config.Api.LOTTERY_COPPER_KEY_LOG;
import static com.wuxiantao.wxt.config.Api.LOTTERY_GOLD_KEY_LOG;
import static com.wuxiantao.wxt.config.Api.LOTTERY_RECORDING;
import static com.wuxiantao.wxt.config.Api.LOTTERY_SILVER_KEY_LOG;
import static com.wuxiantao.wxt.config.Api.ON_LOTTERY;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotteryApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:15
 * Description:${DESCRIPTION}
 */
public interface LotteryApiService {

    @POST(GET_LOTTERY_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<LotteryListBean>> getLotteryList(@Field("token") String token);

    @POST(ON_LOTTERY)
    @FormUrlEncoded
    Observable<BaseResponse<LotteryBean>> onLottery(@Field("token") String token);

    @POST(LOTTERY_COPPER_KEY_LOG)
    @FormUrlEncoded
    Observable<BaseResponse<WinningListBean>> getWinningCopperKeyList(@Field("token") String token,@Field("page") int page, @Field("pagesize") int pagesize);

    @POST(LOTTERY_GOLD_KEY_LOG)
    @FormUrlEncoded
    Observable<BaseResponse<WinningListBean>> getWinningGoldKeyList(@Field("token") String token,@Field("page") int page, @Field("pagesize") int pagesize);

    @POST(LOTTERY_SILVER_KEY_LOG)
    @FormUrlEncoded
    Observable<BaseResponse<WinningListBean>> getWinningSilverKeyList(@Field("token") String token,@Field("page") int page, @Field("pagesize") int pagesize);

    @POST(LOTTERY_ALL_LOG)
    @FormUrlEncoded
    Observable<BaseResponse<WinningListBean>> getWinningAllList(@Field("token") String token,@Field("page") int page, @Field("pagesize") int pagesize);

    @POST(LOTTERY_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<LotteryRecordingBean>> getLotteryRecording(@Field("token") String token,@Field("page") int page, @Field("pagesize") int pagesize);
}
