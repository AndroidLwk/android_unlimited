package com.wuxiantao.wxt.share;

import android.content.Context;

import com.wuxiantao.wxt.app.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 18:38 8-19
 * Description: ${DESCRIPTION} 通过管理类管理接口
 * Author: Administrator Shiming-Shi
 */

public class WeChatShareListener {

    private static WeChatShareListener instance;

    private Context mContext;

    private List<WeChatShareResultListener> resultList;

    private WeChatShareListener() {
        mContext = BaseApplication.getAppContext();
        resultList = new ArrayList<>();
    }

    public static WeChatShareListener getInstance() {
        if (instance == null) {
            synchronized (WeChatShareListener.class) {
                if (instance == null) {
                    return instance = new WeChatShareListener();
                }
            }
        }
        return instance;
    }


    public void addListener(WeChatShareResultListener listener) {
        if (!resultList.contains(listener)) {
            resultList.add(listener);
        }
    }


    public void removeListener(WeChatShareResultListener listener) {
        if (resultList.contains(listener)) {
            resultList.remove(listener);
        }
    }

    public void addSuccess() {
        for (WeChatShareResultListener l : resultList) {
            l.onShareSuccess();
        }
    }


    public void addCancel() {
        for (WeChatShareResultListener l : resultList) {
            l.onShareCancel();
        }
    }


    public void addError() {
        for (WeChatShareResultListener l : resultList) {
            l.onShareError();
        }
    }


}

