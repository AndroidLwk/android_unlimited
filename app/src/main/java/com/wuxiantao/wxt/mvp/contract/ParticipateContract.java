package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ParticipateContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 上午9:35
 * Description:${DESCRIPTION}
 */
public interface ParticipateContract {

    interface IParticipateView extends MvpView{
        void getFriendNumSuccess(InviteFriendNumBean bean);
        void getFriendNumFailure(String failure);
    }

    interface IParticipatePresenter extends MvpPresenter<IParticipateView>{
        void getFriendNum(String token,int type);
    }


}
