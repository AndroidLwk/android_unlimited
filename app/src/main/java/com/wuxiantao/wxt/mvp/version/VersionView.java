package com.wuxiantao.wxt.mvp.version;

import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VersionView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-15 下午1:18
 * Description:${DESCRIPTION}
 */
public interface VersionView extends MvpView {
    void getAppCurrentVersionSuccess(CurrentVersionBean bean);
    void getAppCurrentVersionFailure(String failure);
}
