package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.mvp.version.VersionMvpPresenter;
import com.wuxiantao.wxt.mvp.version.VersionView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutSupermanContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 上午10:50
 * Description:${DESCRIPTION}
 */
public interface AboutSupermanContract {

    interface IAboutSupermanView extends VersionView {
        void onStopAppSuccess(String msg);
        void onStopAppFailure(String failure);

        void getPersonalInfoSuccess(PersonalInfoBean bean);
        void getPersonalInfoFailure(String failure);
    }

    interface IAboutSupermanPresenter extends VersionMvpPresenter<IAboutSupermanView> {

        void getPersonalInfo(String token);
    }


}
