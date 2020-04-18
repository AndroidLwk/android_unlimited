package com.wuxiantao.wxt.net.service;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseApiService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午1:40
 * Description:${DESCRIPTION}
 */
public interface BaseApiService {

    @GET()
    Observable<ResponseBody> downLoadFile();


    @POST()
    @FormUrlEncoded
    Flowable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> maps);

    @POST()
    Flowable<ResponseBody> postBody(@Url String url, @Body Object object);

    @DELETE()
    Flowable<ResponseBody> delete(@Url String url, @QueryMap Map<String, String> maps);

    @PUT()
    Flowable<ResponseBody> put(@Url String url, @QueryMap Map<String, String> maps);

    @POST()
    Flowable<ResponseBody> putBody(@Url String url, @Body Object object);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFlie(@Url String fileUrl, @Part("description") RequestBody description, @Part("files") MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url String url, @Part() List<MultipartBody.Part> parts);

    @Streaming
    @POST()
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @POST()
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Flowable<ResponseBody> postJson(@Url String url, @Body RequestBody jsonBody);

    @POST()
    Flowable<ResponseBody> postBody(@Url String url, @Body RequestBody body);
}
