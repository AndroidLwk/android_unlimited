package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.presenter.ShareBgMvpPresenter;
import com.wuxiantao.wxt.mvp.share.ShareBgView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareMoreThemContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:45
 * Description:${DESCRIPTION}
 */
public interface ShareMoreThemContract {

    interface IShareMoreView extends ShareBgView {

    }

    interface IShareMorePresenter extends ShareBgMvpPresenter<IShareMoreView>{

    }

}
