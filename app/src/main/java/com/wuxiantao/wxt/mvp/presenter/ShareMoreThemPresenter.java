package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.ShareMoreThemContract;
import com.wuxiantao.wxt.mvp.model.ShareBgModel;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareMoreThemPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:47
 * Description:${DESCRIPTION}
 */
public class ShareMoreThemPresenter extends BaseShareBgPresenter<ShareMoreThemContract.IShareMoreView> implements ShareMoreThemContract.IShareMorePresenter {

    @Override
    public void getShareBg(int page, int pageSize) {
        super.getShareBg(new ShareBgModel(),page,pageSize);
    }
}
