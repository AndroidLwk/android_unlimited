package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.redbag_friend.RedBagMvpPresenter;
import com.wuxiantao.wxt.mvp.redbag_friend.RedBagView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagFriendContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午1:53
 * Description:${DESCRIPTION}
 */
public interface RedBagFriendContract {

    interface IRedBagFriendView extends RedBagView {

    }

    interface IRedBagFriendPresenter extends RedBagMvpPresenter<IRedBagFriendView> {

    }


}
