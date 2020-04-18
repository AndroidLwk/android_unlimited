package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.BindingAlipayContract;
import com.wuxiantao.wxt.mvp.user.m.ModifyModel;
import com.wuxiantao.wxt.mvp.user.p.BaseModifyPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class BindingAlipayPresenter extends BaseModifyPresenter<BindingAlipayContract.IBindingView> implements BindingAlipayContract.IBindingPresenter {


    @Override
    public void modifyPersonal(Map<String, Object> parameters) {
        super.modifyPersonal(new ModifyModel(),parameters);
    }


}
