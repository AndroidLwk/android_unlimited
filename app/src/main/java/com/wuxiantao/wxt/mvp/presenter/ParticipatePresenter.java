package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.mvp.contract.ParticipateContract;
import com.wuxiantao.wxt.mvp.model.ShareThemModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ParticipatePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 上午9:39
 * Description:${DESCRIPTION}
 */
public class ParticipatePresenter extends BasePresenter<ParticipateContract.IParticipateView> implements ParticipateContract.IParticipatePresenter {

    private ParticipateContract.IParticipateView view;
    private ShareThemModel model = new ShareThemModel();


    @Override
    public void getFriendNum(String token, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<InviteFriendNumBean> observer = new BaseObserver<InviteFriendNumBean>() {
            @Override
            public void onSuccess(InviteFriendNumBean bean) {
                view.getFriendNumSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getFriendNumFailure(errorMsg);
            }
        };
        model.inviteFriendNum(observer,token,type);
    }
}
