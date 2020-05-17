package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.List;

public interface MyBackpackContract extends MvpView {
    void onFailure(String msg);

    void exchangeSuccess();

    void useCardSuccess(String msg);

    void discardSuccess(String msg);

    void showMyBackPack(MyBoxInfo list);

    void showBoxType(List<BoxTypeBean> list);

    void isSetPayPasswordSuccess(IsSetPayPassword info);
}
