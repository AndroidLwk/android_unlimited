package com.wuxiantao.wxt.net.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:DownLoadService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-9 下午5:09
 * Description:${DESCRIPTION} 下载
 */
public interface DownLoadService {

    @GET
    @Streaming
    Observable<ResponseBody> downLoadImg(@Url String url);
}
