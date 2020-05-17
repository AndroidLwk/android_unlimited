package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.KuorongInfoBean;
import com.wuxiantao.wxt.mvp.pay.OrderPayView;

public interface BackpackExpansionContract extends OrderPayView {
    void addbox_balanceSuccess(String msg);

    void addbox_balanceFailure(String msg);

    void showKuorongInfo(KuorongInfoBean info);

    void showKuorongInfo_failure(String msg);
}
