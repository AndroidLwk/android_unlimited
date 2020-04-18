package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.BindingPhoneContract;
import com.wuxiantao.wxt.mvp.vercode.BaseObtainCodePresenter;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class BindNumberPresenter extends BaseObtainCodePresenter<BindingPhoneContract.IBindingView> implements BindingPhoneContract.IBindingPresenter {

    @Override
    public void obtainCode(String mobile,int type) {
        super.obtainCode(mobile,type);
    }


}
