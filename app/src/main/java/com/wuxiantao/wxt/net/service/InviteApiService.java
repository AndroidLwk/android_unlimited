package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.ActiveStatusBean;
import com.wuxiantao.wxt.bean.DepositDayBean;
import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.bean.TodayShareDayBean;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.APPLY_PARTNER;
import static com.wuxiantao.wxt.config.Api.APPLY_VIP;
import static com.wuxiantao.wxt.config.Api.DEPOSIT_DAY;
import static com.wuxiantao.wxt.config.Api.FREE_RECEIVE_MEMBER_ACTIVE;
import static com.wuxiantao.wxt.config.Api.INVITE_FRIEND_NUM;
import static com.wuxiantao.wxt.config.Api.IS_ACTIVE_STATUS;
import static com.wuxiantao.wxt.config.Api.MEMBER_STATUS_INFO;
import static com.wuxiantao.wxt.config.Api.TODAY_SHARE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InviteApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:53
 * Description:${DESCRIPTION}
 */
public interface InviteApiService {

    @POST(DEPOSIT_DAY)
    @FormUrlEncoded
    Observable<BaseResponse<DepositDayBean>> depositDay(@Field("token") String token);

    @POST(TODAY_SHARE)
    @FormUrlEncoded
    Observable<BaseResponse<TodayShareDayBean>> todayShare(@Field("token") String token);

    @POST(IS_ACTIVE_STATUS)
    @FormUrlEncoded
    Observable<BaseResponse<ActiveStatusBean>> isActiveStatus(@Field("token") String token);

    @POST(MEMBER_STATUS_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<VipStatusInfoBean>> getVipStatusInfo(@Field("token") String token);


    @POST(FREE_RECEIVE_MEMBER_ACTIVE)
    @FormUrlEncoded
    Observable<BaseResponse<List>> receiveMemberActive(@Field("token") String token);

    @POST(INVITE_FRIEND_NUM)
    @FormUrlEncoded
    Observable<BaseResponse<InviteFriendNumBean>> inviteFriendNum(@Field("token") String token, @Field("type") int type);


    @POST(APPLY_VIP)
    @FormUrlEncoded
    Observable<BaseResponse<String>> applyVip(@Field("token") String token);

    @POST(APPLY_PARTNER)
    @FormUrlEncoded
    Observable<BaseResponse<String>> applyPartner(@Field("token") String token);
}
