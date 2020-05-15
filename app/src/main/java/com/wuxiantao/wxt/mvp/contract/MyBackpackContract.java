package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.mvp.view.MvpView;

public interface MyBackpackContract extends MvpView {
    void onFailure(String msg);
    void  showMyBackPack(MyBoxInfo list);
}
