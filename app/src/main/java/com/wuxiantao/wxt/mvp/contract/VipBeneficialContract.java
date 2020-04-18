package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VipBeneficialContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 上午11:29
 * Description:${DESCRIPTION} 参与邀请好友免费领取会员活动
 */
public interface VipBeneficialContract {

    interface IVipBeneficialView extends MvpView{
        void receiveSuccess(String msg);
        void receiveFailure(String failure);
    }

    interface IVipBeneficialPresenter extends MvpPresenter<IVipBeneficialView>{
        void receiveMemberActive(String token);
    }


}
