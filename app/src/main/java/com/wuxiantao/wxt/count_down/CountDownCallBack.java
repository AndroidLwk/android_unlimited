package com.wuxiantao.wxt.count_down;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CountDownCallBack
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-15 下午7:58
 * Description:${DESCRIPTION}
 */
public interface CountDownCallBack {

    void onNext(Long time);
    void onComplete();
}
