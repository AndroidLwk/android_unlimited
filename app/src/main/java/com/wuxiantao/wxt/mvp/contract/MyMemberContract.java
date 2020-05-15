package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

public interface MyMemberContract extends OrderPayView {
    void showMymemberInfo(VipStatusInfoBean info);

    void onFailure(String msg);
}
