package com.wuxiantao.wxt.mvp.vercode;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ObtainCodeMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午4:43
 * Description:${DESCRIPTION}
 */
public interface ObtainCodeMvpPresenter<V extends ObtainCodeView> extends MvpPresenter<V> {
    void obtainCode(String mobile,int type);
}
