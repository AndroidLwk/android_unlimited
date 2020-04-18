package com.wuxiantao.wxt.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.DateUtils;

import static com.wuxiantao.wxt.config.Constant.COUNT_DOWN_TIME_SERVICE;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CountDownTimeService
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-19 下午3:14
 * Description:${DESCRIPTION} 倒计时
 */
public class CountDownTimeService extends JobIntentService {

    //RedBagActivationPopupWindow
    //RedBagWithdrawalPopupWindow
    //LotteryModelPopupWindow

    private static final String COUNT_DOWN_TIME = "2020-1-2 22:00:00";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, CountDownTimeService.class, COUNT_DOWN_TIME_SERVICE, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String timestamp = DateUtils.timeToTimestamp(COUNT_DOWN_TIME);
        long current = System.currentTimeMillis() / 1000;
        long diff = Long.valueOf(timestamp) - current;
        //存储数据
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(IS_REVIEW,diff > 0);
    }

}
