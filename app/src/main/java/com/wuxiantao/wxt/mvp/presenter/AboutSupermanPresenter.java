package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.AboutSupermanContract;
import com.wuxiantao.wxt.mvp.model.AboutSupermanModel;
import com.wuxiantao.wxt.mvp.version.BaseVersionPresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutSupermanPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 上午10:52
 * Description:${DESCRIPTION}
 */
public class AboutSupermanPresenter extends BaseVersionPresenter<AboutSupermanContract.IAboutSupermanView> implements AboutSupermanContract.IAboutSupermanPresenter {


    @Override
    public void getAppCurrentVersion() {
        super.getAppCurrentVersion(new AboutSupermanModel());
    }
}
