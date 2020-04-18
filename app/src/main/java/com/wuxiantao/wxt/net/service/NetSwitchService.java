package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.NetSwitchBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:NetSwitchService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-13 上午9:59
 * Description:${DESCRIPTION}
 */
public interface NetSwitchService {

    @POST
    Observable<BaseResponse<NetSwitchBean>> getSwitchType(@Url String url);
}
