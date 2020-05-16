package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.adapter.bean.HeroScrolllBean;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.bean.ComposeHeroBean;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.bean.MyCardInfo;
import com.wuxiantao.wxt.bean.MyLuckyInfoBean;
import com.wuxiantao.wxt.bean.MyMoneyCashBean;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.MyTaskInfoBean;
import com.wuxiantao.wxt.bean.RedBagWithdrawInfoBean;
import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.bean.StartStrapingBean;
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
import static com.wuxiantao.wxt.config.Api.COMPOSE_HERO;
import static com.wuxiantao.wxt.config.Api.DIS_CARD;
import static com.wuxiantao.wxt.config.Api.ENROLL_BONUS;
import static com.wuxiantao.wxt.config.Api.EXCHANGE;
import static com.wuxiantao.wxt.config.Api.GET_BOXCATE;
import static com.wuxiantao.wxt.config.Api.GET_CARD;
import static com.wuxiantao.wxt.config.Api.GET_CHECK_IN_INFO;
import static com.wuxiantao.wxt.config.Api.GET_COMMISSION_WITHDRAW_INFO;
import static com.wuxiantao.wxt.config.Api.GET_RED_BAG_WITHDRAW_INFO;
import static com.wuxiantao.wxt.config.Api.GET_SCROLLCATE;
import static com.wuxiantao.wxt.config.Api.GET_SHAREPIC;
import static com.wuxiantao.wxt.config.Api.MY_BOX;
import static com.wuxiantao.wxt.config.Api.MY_CARDINFO;
import static com.wuxiantao.wxt.config.Api.MY_LUCKYINFO;
import static com.wuxiantao.wxt.config.Api.MY_MONEYCASH;
import static com.wuxiantao.wxt.config.Api.MY_SCROLL;
import static com.wuxiantao.wxt.config.Api.NEWEST_ACTIVE;
import static com.wuxiantao.wxt.config.Api.RED_BAG_WITHDRAW;
import static com.wuxiantao.wxt.config.Api.START_STRAPING;
import static com.wuxiantao.wxt.config.Api.TASK_INFO;
import static com.wuxiantao.wxt.config.Api.TASK_SIGN;
import static com.wuxiantao.wxt.config.Api.USE_CARD;

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

    @POST(MY_CARDINFO)
    @FormUrlEncoded
    Observable<BaseResponse<MyCardInfo>> myCardInfo(@Field("token") String token);

    @POST(ENROLL_BONUS)
    @FormUrlEncoded
    Observable<BaseResponse<List>> enrollBonus(@Field("token") String token, @Field("type") String type);

    @POST(TASK_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<MyTaskInfoBean>> taskInfo(@Field("token") String token);

    @POST(NEWEST_ACTIVE)
    @FormUrlEncoded
    Observable<BaseResponse<List>> newestActive(@Field("token") String token, @Field("type") String type);

    @POST(TASK_SIGN)
    @FormUrlEncoded
    Observable<BaseResponse<MySignInfo>> taskSign(@Field("token") String token);

    @POST(GET_CARD)
    @FormUrlEncoded
    Observable<BaseResponse<CardInfoBean>> getCard(@Field("token") String token, @Field("type") String type);

    @POST(MY_BOX)
    @FormUrlEncoded
    Observable<BaseResponse<MyBoxInfo>> myBox(@FieldMap Map<String, Object> map);

    @POST(MY_SCROLL)
    @FormUrlEncoded
    Observable<BaseResponse<List<HeroScrolllBean>>> myScroll(@Field("token") String token, @Field("pid") String pid);

    @POST(COMPOSE_HERO)
    @FormUrlEncoded
    Observable<BaseResponse<ComposeHeroBean>> composeHero(@Field("token") String token, @Field("cid") String cid);

    @POST(START_STRAPING)
    @FormUrlEncoded
    Observable<BaseResponse<StartStrapingBean>> startStraping(@Field("token") String token);

    @POST(MY_LUCKYINFO)
    @FormUrlEncoded
    Observable<BaseResponse<MyLuckyInfoBean>> myLuckyInfo(@Field("token") String token, @Field("type") String type);

    @POST(GET_SHAREPIC)
    @FormUrlEncoded
    Observable<BaseResponse<SharePicBean>> getSharePic(@Field("token") String token);

    @POST(GET_BOXCATE)
    @FormUrlEncoded
    Observable<BaseResponse<List<BoxTypeBean>>> getBoxCate(@Field("token") String token);

    @POST(GET_SCROLLCATE)
    @FormUrlEncoded
    Observable<BaseResponse<List<BoxTypeBean>>> getScrollCate(@Field("token") String token);

    @POST(MY_MONEYCASH)
    @FormUrlEncoded
    Observable<BaseResponse<MyMoneyCashBean>> myMoneyCash(@Field("token") String token);

    @POST(USE_CARD)
    @FormUrlEncoded
    Observable<BaseResponse<CardInfoBean>> useCard(@Field("token") String token, @Field("cid") String cid, @Field("num") String num);
    @POST(DIS_CARD)
    @FormUrlEncoded
    Observable<BaseResponse<CardInfoBean>> discard(@Field("token") String token, @Field("card_id") String cid, @Field("num") String num);
    @POST(EXCHANGE)
    @FormUrlEncoded
    Observable<BaseResponse<List>> exchange(@FieldMap Map<String, Object> map);


}
