package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.NetSwitchBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.mvp.version.VersionMvpPresenter;
import com.wuxiantao.wxt.mvp.version.VersionView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MenuContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-13 上午10:54
 * Description:${DESCRIPTION}
 */
public interface MenuContract {

    interface IMenuView extends VersionView {
        void onStartAppSuccess(String msg);
        void onStartAppFailure(String failure);
        void onStopAppSuccess(String msg);
        void onStopAppFailure(String failure);
        void getSwitchTypeSuccess(NetSwitchBean bean);
        void getSwitchTypeFailure(String failure);
        void getPersonalInfoSuccess(PersonalInfoBean bean);
        void getPersonalInfoFailure(String failure);
    }

    interface IMenuPresenter extends VersionMvpPresenter<IMenuView> {
        void onStartApp(String token);
        void onStopApp(String token);
        void getSwitchType(String url);
        void getPersonalInfo(String token);
    }

}
