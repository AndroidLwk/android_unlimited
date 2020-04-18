package com.wuxiantao.wxt.mvp.contract;


import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.MakeGoldBean;
import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.bean.UnfastenRedbagBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.Map;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 10:12 8-19
 * Description: ${DESCRIPTION} MainContract -主界面的Presenter和View的契约类
 * Author: Administrator Shiming-Shi
 */

public interface DepositContract {

    interface IDepositView extends MvpView {
        //将一些操作界面的方法在这里声明
        void getRedBagInfoSuccess(RedBagInfoBean bean);

        void getRedBagInfoFailure(String failure);

        void openRedBagSuccess(UnfastenRedbagBean bean);

        void openRedBagFailure(String failure);

        void onMakeGoldSuccess(MakeGoldBean bean);

        void onMakeGoldFailure(String failure);

        void onGetFriendListSuccess(FriendListBean bean);

        void onGetFriendListFailure(String failure);
    }

    interface IDepositPresenter extends MvpPresenter<IDepositView> {
        //将一些逻辑处理的方法在此声明
        void getRedBagInfo(String token);

        void openRedBag(String token);

        void onMakeGold(String token, int type);

        void onGetFriendList(Map<String, Object> map);
    }
}
