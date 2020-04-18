package com.wuxiantao.wxt.mvp.contract;

import android.graphics.Bitmap;

import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.bean.ShareCodeBean;
import com.wuxiantao.wxt.bean.ShareRewardBean;
import com.wuxiantao.wxt.mvp.presenter.ShareBgMvpPresenter;
import com.wuxiantao.wxt.mvp.share.ShareBgView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareThemContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午8:59
 * Description:${DESCRIPTION}
 */
public interface ShareThemContract {

    interface IShareThemView extends ShareBgView {
        void createShareCodeSuccess(ShareCodeBean bean);

        void createShareCodeFailure(String failure);

        void getFriendNumSuccess(InviteFriendNumBean bean);

        void getFriendNumFailure(String failure);

        void downLoadImgSuccess(Bitmap bitmap);

        void downLoadImgFailure(String failure);

        void onShareRewardSuccess(ShareRewardBean bean);

        void onShareRewardFailure(String failure);
    }

    interface IShareThemPresenter extends ShareBgMvpPresenter<IShareThemView> {
        void getFriendNum(String token, int type);

        void createShareCode(String token, int key, int type);

        void downLoadImg(String url);

        void onShareReward(String token);
    }
}
