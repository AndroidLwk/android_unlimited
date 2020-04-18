package com.wuxiantao.wxt.mvp.login;

import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:28
 * Description:${DESCRIPTION} MvpView-View的基础类
 */
public interface LoginView extends MvpView {

    void loginSuccess(PhoneLoginBean response);
    void loginFailure(String failure);
}
