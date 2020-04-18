package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.RedBagFriendContract;
import com.wuxiantao.wxt.mvp.redbag_friend.BaseRedBagPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RedBagFriendPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午1:55
 * Description:${DESCRIPTION}
 */
public class RedBagFriendPresenter extends BaseRedBagPresenter<RedBagFriendContract.IRedBagFriendView> implements RedBagFriendContract.IRedBagFriendPresenter {

    @Override
    public void getRedBagFriendList(Map<String, Object> parameters) {
        super.getRedBagFriendList(parameters);
    }

    @Override
    public void onActivateFriend(Map<String, Object> map,int type) {
        super.onActivateFriend(map,type);
    }

    @Override
    public void getPromotionLanguage(int type) {
        super.getPromotionLanguage(type);
    }
}
