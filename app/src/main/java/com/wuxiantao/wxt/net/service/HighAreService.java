package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.HighAreaListBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.HIGH_AREA_ZERO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HighAreService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:50
 * Description:${DESCRIPTION} 高佣专区
 */
public interface HighAreService {

    @POST(HIGH_AREA_ZERO)
    @FormUrlEncoded
    Observable<BaseResponse<HighAreaListBean>> getHighAreaList(@Field("token") String token,
                                                               @Field("page") int page,
                                                               @Field("pagesize") int pagesize);
}
