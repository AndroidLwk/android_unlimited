package com.wuxiantao.wxt.mvp.delegate;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 9:53 8-19
 * Description: ${DESCRIPTION} .创建一个ActyvityDelegate，与activity生命周期一致，
 *    目的是为了通过activity的生命周期去控制是否要attachView
 * Author: Administrator Shiming-Shi
 */

public interface ActyvityDelegate {

    void onCreate();

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();
}
