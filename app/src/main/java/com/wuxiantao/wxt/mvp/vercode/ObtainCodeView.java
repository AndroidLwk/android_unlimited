package com.wuxiantao.wxt.mvp.vercode;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:28
 * Description:${DESCRIPTION} MvpView-View的基础类
 */
public interface ObtainCodeView extends MvpView {

    //将一些操作界面的方法在这里声明
    void obtainSuccess(String msg);
    void obtainFailure(String failure);
}
