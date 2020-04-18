package com.wuxiantao.wxt.mvp.fansi.view;

import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FansiView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-5 下午6:30
 * Description:${DESCRIPTION}
 */
public interface FansiView extends MvpView {

    void obtainFansiDetailSuccess(FansiDetailBean bean);
    void obtainFansiDetailFailure(String failure);
    void obtainFansFailure(String failure);
}
