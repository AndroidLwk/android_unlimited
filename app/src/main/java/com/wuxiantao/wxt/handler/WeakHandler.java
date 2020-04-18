package com.wuxiantao.wxt.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WeakHandler
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-5 下午4:10
 * Description:${DESCRIPTION}
 */
public class WeakHandler extends Handler {

    public interface IHandler {
        void handleMsg(Message msg);
    }

    private final WeakReference<IHandler> mRef;

    public WeakHandler(IHandler handler) {
        mRef = new WeakReference<>(handler);
    }

    public WeakHandler(Looper looper, IHandler handler) {
        super(looper);
        mRef = new WeakReference<>(handler);
    }

    @SuppressWarnings("unused")
    @Override
    public void handleMessage(Message msg) {
        IHandler handler = mRef.get();
        if (handler != null && msg != null)
            handler.handleMsg(msg);
    }
}
