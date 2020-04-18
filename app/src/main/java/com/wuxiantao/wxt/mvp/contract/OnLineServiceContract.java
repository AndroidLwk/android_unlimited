package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.OnLineServiceBean;
import com.wuxiantao.wxt.net.download.DownloadMvpPresenter;
import com.wuxiantao.wxt.net.download.DownloadView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineServiceContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-21 下午1:55
 * Description:${DESCRIPTION}
 */
public interface OnLineServiceContract {

    interface IOnLineServiceView extends DownloadView {
         void getOnLineServiceInfoSuccess(OnLineServiceBean bean);
         void getOnLineServiceInfoFailure(String failure);
    }

    interface IOnLineServicePresenter extends DownloadMvpPresenter<IOnLineServiceView> {
          void getOnLineServiceInfo(String token);
    }
}
