package com.wuxiantao.wxt.mvp.withdraw;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawInfoView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午9:02
 * Description:${DESCRIPTION}
 */
public interface WithdrawView extends MvpView {

    void withdrawSuccess(String msg);
    void withdrawFailure(String failure);
}
