package com.wuxiantao.wxt.net.service;

import com.wuxiantao.wxt.bean.HelpCenterBean;
import com.wuxiantao.wxt.net.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

import static com.wuxiantao.wxt.config.Api.HELP_CENTER_TITLE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HelpCenterService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午8:55
 * Description:${DESCRIPTION}
 */
public interface HelpCenterService {


    @POST(HELP_CENTER_TITLE)
    Observable<BaseResponse<List<HelpCenterBean>>> onGetImgList();
}
