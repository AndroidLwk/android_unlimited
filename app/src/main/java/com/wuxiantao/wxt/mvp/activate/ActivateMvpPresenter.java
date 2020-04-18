package com.wuxiantao.wxt.mvp.activate;

import com.wuxiantao.wxt.mvp.promotion.PromotionMvpPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivateMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-4 下午10:45
 * Description:${DESCRIPTION}
 */
public interface ActivateMvpPresenter<V extends ActivateView> extends PromotionMvpPresenter<V> {

    void onActivateFriend(Map<String,Object> map,int type);
}
