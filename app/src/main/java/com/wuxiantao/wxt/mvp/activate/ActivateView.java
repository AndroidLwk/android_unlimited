package com.wuxiantao.wxt.mvp.activate;

import com.wuxiantao.wxt.mvp.promotion.PromotionView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivateView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-4 下午10:44
 * Description:${DESCRIPTION} 激活好友/助手
 */
public interface ActivateView extends PromotionView {

    void onActivateSuccess(String msg);
    void onActivateFailure(String failure);
}
