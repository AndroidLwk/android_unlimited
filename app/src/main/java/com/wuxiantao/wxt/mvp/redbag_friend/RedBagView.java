package com.wuxiantao.wxt.mvp.redbag_friend;

import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.mvp.activate.ActivateView;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 下午3:39
 * Description:${DESCRIPTION}
 */
public interface RedBagView extends ActivateView {

    void getRedBagFriendListSuccess(FriendListBean bean);
    void getRedBagFriendListFailure(String failure);
}
