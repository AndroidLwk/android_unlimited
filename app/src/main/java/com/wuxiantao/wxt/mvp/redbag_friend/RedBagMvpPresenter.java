package com.wuxiantao.wxt.mvp.redbag_friend;

import com.wuxiantao.wxt.mvp.activate.ActivateMvpPresenter;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 下午3:40
 * Description:${DESCRIPTION}
 */
public interface RedBagMvpPresenter<V extends RedBagView> extends ActivateMvpPresenter<V> {

    void getRedBagFriendList(Map<String,Object> parameters);
}
