package com.wuxiantao.wxt.mvp.user.v;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ModifyView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午2:55
 * Description:${DESCRIPTION}
 */
public interface ModifyView extends MvpView {

    void modifySuccess(String msg);
    void modifyFailure(String failure);

}
