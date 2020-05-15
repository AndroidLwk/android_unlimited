package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.FanSiPotentialBean;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.bean.FansiIndirectBean;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.FANSI_DETAIL;
import static com.wuxiantao.wxt.config.Api.OBTAIN_FANSI;
import static com.wuxiantao.wxt.config.Api.OBTAIN_FANSI_HEAD_INFO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午7:29
 * Description:${DESCRIPTION}
 */
public interface FanSiApiService {

    @POST(OBTAIN_FANSI_HEAD_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<MyFansiHeadInfoBean>> obtainFansiHeadInfo(@Field("token") String token);

    @POST(OBTAIN_FANSI)
    @FormUrlEncoded
    Observable<BaseResponse<FanSiPotentialBean>> obtainPotentialFansi(@FieldMap Map<String, Object> parameters);

    @POST(OBTAIN_FANSI)
    @FormUrlEncoded    //好友列表
    Observable<BaseResponse<FansiDirectlyBean>> obtainDirectlyFansi(@FieldMap Map<String, Object> parameters);

    @POST(OBTAIN_FANSI)
    @FormUrlEncoded
    Observable<BaseResponse<FansiDirectlyBean>> obtainInDirectlyFansi(@FieldMap Map<String, Object> parameters);

    @POST(FANSI_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<FansiDetailBean>> obtainFansiDetail(@Field("uid") int uid);
}
