package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.CompositeDragonBean;
import com.wuxiantao.wxt.bean.CompositeScrapBean;
import com.wuxiantao.wxt.bean.DividedDragonDetailBean;
import com.wuxiantao.wxt.bean.DividedDragonListBean;
import com.wuxiantao.wxt.bean.DragonInfoBean;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.bean.GameMessageBean;
import com.wuxiantao.wxt.bean.IncomeHallBean;
import com.wuxiantao.wxt.bean.IncreaseCountBean;
import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.bean.OpenDragonBean;
import com.wuxiantao.wxt.bean.StartExperienceBean;
import com.wuxiantao.wxt.bean.TaskInfoBean;
import com.wuxiantao.wxt.bean.VideoDoubleBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.COMPOSITE_DRAGON;
import static com.wuxiantao.wxt.config.Api.COMPOSITE_SCRAP;
import static com.wuxiantao.wxt.config.Api.DIVIDED_DRAGON_DETAIL;
import static com.wuxiantao.wxt.config.Api.DIVIDED_DRAGON_LIST;
import static com.wuxiantao.wxt.config.Api.GET_DRAGON_INFO;
import static com.wuxiantao.wxt.config.Api.GET_DRAGON_STATUS_INFO;
import static com.wuxiantao.wxt.config.Api.GET_MY_INCOME_LIST;
import static com.wuxiantao.wxt.config.Api.INCOME_HALL_INFO;
import static com.wuxiantao.wxt.config.Api.MY_TASK_INFO;
import static com.wuxiantao.wxt.config.Api.ON_ADS_DEVOTE;
import static com.wuxiantao.wxt.config.Api.ON_BINDING_AREA;
import static com.wuxiantao.wxt.config.Api.ON_GAME_AREA_CHANGE;
import static com.wuxiantao.wxt.config.Api.ON_GAME_MESSAGE;
import static com.wuxiantao.wxt.config.Api.ON_INCREASE_COUNT;
import static com.wuxiantao.wxt.config.Api.ON_OPEN_DRAGON;
import static com.wuxiantao.wxt.config.Api.ON_START_APP;
import static com.wuxiantao.wxt.config.Api.ON_START_EXPERIENCE;
import static com.wuxiantao.wxt.config.Api.ON_STOP_APP;
import static com.wuxiantao.wxt.config.Api.ON_WATCH_VIDEO_FIVE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DragonApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午12:46
 * Description:${DESCRIPTION}
 */
public interface DragonApiService {

    @POST(GET_DRAGON_STATUS_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<DragonStatusInfoBean>> getDragonStatusInfo(@Field("token")String token);

    @POST(ON_OPEN_DRAGON)
    @FormUrlEncoded
    Observable<BaseResponse<OpenDragonBean>> onOpenDragon(@Field("token")String token,@Field("card_id") int card_id);

    @POST(ON_WATCH_VIDEO_FIVE)
    @FormUrlEncoded
    Observable<BaseResponse<VideoDoubleBean>> onVideoDouble(@Field("token")String token,
                                                            @Field("dragon_id") int dragon_id,
                                                            @Field("num") String num);
    @POST(ON_GAME_MESSAGE)
    @FormUrlEncoded
    Observable<BaseResponse<GameMessageBean>> onGameMessage(@Field("token")String token);


    @POST(ON_START_EXPERIENCE)
    @FormUrlEncoded
    Observable<BaseResponse<StartExperienceBean>> onStartExperience(@Field("token")String token);

    @POST(ON_GAME_AREA_CHANGE)
    @FormUrlEncoded
    Observable<BaseResponse<AreaChangeInfoBean>> onChangeAreaInfo(@Field("token")String token);

    @POST(ON_BINDING_AREA)
    @FormUrlEncoded
    Observable<BaseResponse<List<String>>> onBindingArea(@Field("token")String token,@Field("qu_id") int qu_id);

    @POST(ON_INCREASE_COUNT)
    @FormUrlEncoded
    Observable<BaseResponse<IncreaseCountBean>> onIncreaseCount(@Field("token")String token);


    @POST(INCOME_HALL_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<IncomeHallBean>> getIncomeHallInfo(@Field("token")String token);


    @POST(DIVIDED_DRAGON_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<DividedDragonDetailBean>> getDividedDragonDetail(@Field("token")String token);


    @POST(DIVIDED_DRAGON_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<DividedDragonListBean>> getDividedDragonList(@Field("token")String token, @Field("page")int page, @Field("pagesize")int pagesize);

    @POST(MY_TASK_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<TaskInfoBean>> getTaskInfo(@Field("token")String token);

    @POST(ON_START_APP)
    @FormUrlEncoded
    Observable<BaseResponse<List<String>>> onStartApp(@Field("token")String token);

    @POST(ON_STOP_APP)
    @FormUrlEncoded
    Observable<BaseResponse<List<String>>> onStopApp(@Field("token")String token);


    @POST(GET_MY_INCOME_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<MyIncomeBean>> getMyIncomeList(@Field("token")String token, @Field("page")int page, @Field("pagesize")int pagesize);


    @POST(GET_DRAGON_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<DragonInfoBean>> getDragonInfo(@Field("token")String token);

    @POST(COMPOSITE_SCRAP)
    @FormUrlEncoded
    Observable<BaseResponse<CompositeScrapBean>> onCompositeScrap(@Field("token")String token);

    @POST(COMPOSITE_DRAGON)
    @FormUrlEncoded
    Observable<BaseResponse<CompositeDragonBean>> onCompositeDragon(@Field("token")String token);

    @POST(ON_ADS_DEVOTE)
    @FormUrlEncoded
    Observable<BaseResponse<List<String>>> onAdsDevote(@Field("token")String token);
}
