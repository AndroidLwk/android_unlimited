package com.wuxiantao.wxt.mvp.withdraw;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawInfoMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午9:03
 * Description:${DESCRIPTION}
 */
public interface WithdrawMvpPresenter<V extends WithdrawView> extends MvpPresenter<V> {

    void withdraw(Map<String,Object> map);
}
