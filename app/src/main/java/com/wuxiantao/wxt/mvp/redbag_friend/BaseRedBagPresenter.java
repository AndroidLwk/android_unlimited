package com.wuxiantao.wxt.mvp.redbag_friend;

import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.mvp.activate.BaseActivatePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseRedBagPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 下午3:43
 * Description:${DESCRIPTION}
 */
public class BaseRedBagPresenter<V extends RedBagView> extends BaseActivatePresenter<V> {

    private V  view;
    private RedBagModel model = new RedBagModel();

    public void getRedBagFriendList(Map<String, Object> parameters) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<FriendListBean> observer = new BaseObserver<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean friendListBean) {
                view.getRedBagFriendListSuccess(friendListBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getRedBagFriendListFailure(errorMsg);
            }
        };
        model.getRedBagFriendList(observer,parameters);
    }


}
