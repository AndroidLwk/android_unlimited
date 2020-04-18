package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.bean.DepositInfoBean;
import com.wuxiantao.wxt.bean.InstructBean;
import com.wuxiantao.wxt.bean.InterestRateBean;
import com.wuxiantao.wxt.bean.ProfitRecordingBean;
import com.wuxiantao.wxt.bean.VedioBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.GAIN_BANNER;
import static com.wuxiantao.wxt.config.Api.GET_APP_VERSION;
import static com.wuxiantao.wxt.config.Api.OBTAIN_RATE;
import static com.wuxiantao.wxt.config.Api.OBTAIN_SUPER_INFO;
import static com.wuxiantao.wxt.config.Api.PROFIT_RECORDING;
import static com.wuxiantao.wxt.config.Api.SAVE_MONEY_INSTRUCT;
import static com.wuxiantao.wxt.config.Api.SEND_SUPER;
import static com.wuxiantao.wxt.config.Api.VEDIO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SuperApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 下午4:43
 * Description:${DESCRIPTION}
 */
public interface SuperApiService {

    @POST(OBTAIN_SUPER_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<DepositInfoBean>> obtainSuperInfo(@Field("token") String token);

    @POST(SEND_SUPER)
    @FormUrlEncoded
    Observable<BaseResponse<List>> sendSuper(@Field("token") String token, @Field("send") int send);

    @POST(VEDIO)
    @FormUrlEncoded
    Observable<BaseResponse<VedioBean>> vedio(@Field("token") String token);

    @POST(OBTAIN_RATE)
    @FormUrlEncoded
    Observable<BaseResponse<InterestRateBean>> obtainRate(@Field("token") String token);

    @POST(GAIN_BANNER)
    @FormUrlEncoded
    Observable<BaseResponse<BannerBean>> gainBanner(@Field("type") int type);

    @POST(SAVE_MONEY_INSTRUCT)
    Observable<BaseResponse<InstructBean>> instruct();

    @POST(PROFIT_RECORDING)
    @FormUrlEncoded
    Observable<BaseResponse<ProfitRecordingBean>> getProfitRecording(@Field("token") String token, @Field("page") int page, @Field("pagesize") int pagesize);

    @POST(GET_APP_VERSION)
    Observable<BaseResponse<CurrentVersionBean>> getAppCurrentVersion();
}

