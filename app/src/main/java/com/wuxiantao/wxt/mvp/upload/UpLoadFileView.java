package com.wuxiantao.wxt.mvp.upload;

import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:UpLoadFileView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-2 下午5:26
 * Description:${DESCRIPTION}
 */
public interface UpLoadFileView extends MvpView {

    void upLoadFileSuccess(String url);
    void upLoadFileFailure(String failure);

}
