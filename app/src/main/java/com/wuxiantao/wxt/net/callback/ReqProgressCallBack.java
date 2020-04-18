package com.wuxiantao.wxt.net.callback;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ReqProgressCallBack
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:06
 * Description:${DESCRIPTION} 请求体进度回调接口，用于文件上传/下载进度回调
 */
public interface ReqProgressCallBack {

    /**
     * 响应进度更新
     * @param currentSize 当前进度
     * @param totalSize 文件总长度
     * @param progress 进度
     * @param done 是否读取上传或下载完成
     */
    void onProgress(String totalSize,String currentSize,String progress,boolean done);

}
