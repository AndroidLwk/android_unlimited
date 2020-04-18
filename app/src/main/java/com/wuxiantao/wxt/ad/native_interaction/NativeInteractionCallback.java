package com.wuxiantao.wxt.ad.native_interaction;

import android.app.Dialog;

import com.bytedance.sdk.openadsdk.TTAdDislike;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:NativeInteractionCallback
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-17 下午3:38
 * Description:${DESCRIPTION}
 */
public class NativeInteractionCallback implements TTAdDislike.DislikeInteractionCallback {

    private Dialog dialog;

    public NativeInteractionCallback(Dialog dialog){
        this.dialog = dialog;
    }

    @Override
    public void onSelected(int position, String value) {
        if (dialog != null){
            dialog.dismiss();
        }
    }

    @Override
    public void onCancel() {

    }
}
