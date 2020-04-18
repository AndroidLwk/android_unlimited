package com.wuxiantao.wxt.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wuxiantao.wxt.ui.activity.WeChatLoginActivity;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LoginBroadcastReceiver
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-24 下午5:15
 * Description:${DESCRIPTION} 重新登陆
 */
public class LoginBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent in = new Intent(context, WeChatLoginActivity.class);
        if (!(context instanceof Activity)){
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(in);
    }
}
