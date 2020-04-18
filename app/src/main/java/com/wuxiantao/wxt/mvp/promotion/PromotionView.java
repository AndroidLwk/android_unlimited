package com.wuxiantao.wxt.mvp.promotion;

import com.wuxiantao.wxt.bean.PromotionLanguageBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PromotionView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午1:46
 * Description:${DESCRIPTION} 推广语
 */
public interface PromotionView extends MvpView {

    void getLanguageSuccess(PromotionLanguageBean bean);
    void getLanguageFailure(String failure);
}
