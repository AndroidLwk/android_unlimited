package com.wuxiantao.wxt.mvp.user.p;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.user.v.ModifyView;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ModifyMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午2:58
 * Description:${DESCRIPTION}
 */
public interface ModifyMvpPresenter<V extends ModifyView> extends MvpPresenter<V> {

    void modifyPersonal(Map<String,Object> parameters);
}
