package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.PromotionLanguageBean;
import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.bean.ShareCodeBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.CREATE_SHARE_CODE;
import static com.wuxiantao.wxt.config.Api.GET_SHARE_BG;
import static com.wuxiantao.wxt.config.Api.GET_TUI_GUANG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午9:18
 * Description:${DESCRIPTION}
 */
public interface ShareApiService {

    @POST(GET_SHARE_BG)
    @FormUrlEncoded
    Observable<BaseResponse<ShareBackGroundBean>> getShareBackGround(@Field("page") int page, @Field("pagesize") int pagesize);


    //key 背景图数组索引，必填
    //type 默认值0，不传或0为正常分享，1为好友助力
    @POST(CREATE_SHARE_CODE)
    @FormUrlEncoded
    Observable<BaseResponse<ShareCodeBean>> createShareCode(@Field("token") String token, @Field("key") int key, @Field("type") int type);


    //获取推广语
    @POST(GET_TUI_GUANG)
    @FormUrlEncoded
    Observable<BaseResponse<PromotionLanguageBean>> getPromotionLanguage(@Field("type") int type);
}
