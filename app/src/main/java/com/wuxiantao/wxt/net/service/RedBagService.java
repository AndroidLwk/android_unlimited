package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.MakeGoldBean;
import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.bean.ShareRewardBean;
import com.wuxiantao.wxt.bean.UnfastenRedbagBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.ACTIVATE_FRIEND;
import static com.wuxiantao.wxt.config.Api.ACTIVATE_FRIEND_DOUBLE;
import static com.wuxiantao.wxt.config.Api.GET_FRIEND_LIST;
import static com.wuxiantao.wxt.config.Api.GET_RED_BAG_INFO;
import static com.wuxiantao.wxt.config.Api.MAKE_GOLD;
import static com.wuxiantao.wxt.config.Api.MAKE_GOLD_DOUBLE;
import static com.wuxiantao.wxt.config.Api.RED_BAG_FRIEND_LIST;
import static com.wuxiantao.wxt.config.Api.SHARE_SEND_REWARD;
import static com.wuxiantao.wxt.config.Api.UNFASTEN_RED_BAG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午1:57
 * Description:${DESCRIPTION}
 */
public interface RedBagService {

    @POST(RED_BAG_FRIEND_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<FriendListBean>> getRedBagFriendList(@FieldMap Map<String, Object> parameters);

    @POST(UNFASTEN_RED_BAG)
    @FormUrlEncoded
    Observable<BaseResponse<UnfastenRedbagBean>> unfastenRedBag(@Field("token") String token);

    @POST(MAKE_GOLD)
    @FormUrlEncoded
    Observable<BaseResponse<MakeGoldBean>> onMakeGold(@Field("token") String token);

    @POST(MAKE_GOLD_DOUBLE)
    @FormUrlEncoded
    Observable<BaseResponse<MakeGoldBean>> onMakeGoldDouble(@Field("token") String token);

    @POST(SHARE_SEND_REWARD)
    @FormUrlEncoded
    Observable<BaseResponse<ShareRewardBean>> onShareReward(@Field("token") String token);

    @POST(GET_RED_BAG_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<RedBagInfoBean>> getRedBagInfo(@Field("token") String token);


    @POST(GET_FRIEND_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<FriendListBean>> onGetFriendList(@FieldMap Map<String, Object> parameters);

    @POST(ACTIVATE_FRIEND)
    @FormUrlEncoded
    Observable<BaseResponse<List>> onActivateFriend(@FieldMap Map<String, Object> parameters);

    @POST(ACTIVATE_FRIEND_DOUBLE)
    @FormUrlEncoded
    Observable<BaseResponse<List>> onActivateDoubleFriend(@FieldMap Map<String, Object> parameters);

}
