package com.wuxiantao.wxt.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.bean.ShareCodeBean;
import com.wuxiantao.wxt.bean.ShareRewardBean;
import com.wuxiantao.wxt.mvp.contract.ShareThemContract;
import com.wuxiantao.wxt.mvp.model.ShareThemModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.base.DownObserver;
import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareThemPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 上午10:12
 * Description:${DESCRIPTION}
 */
public class ShareThemPresenter extends BaseShareBgPresenter<ShareThemContract.IShareThemView> implements ShareThemContract.IShareThemPresenter, ReqProgressCallBack {

    private ShareThemModel model = new ShareThemModel();
    private ShareThemContract.IShareThemView view;

    @Override
    public void getShareBg(int page, int pageSize) {
        super.getShareBg(model,page,pageSize);
    }

    @Override
    public void getFriendNum(String token, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<InviteFriendNumBean> observer = new BaseObserver<InviteFriendNumBean>() {
            @Override
            public void onSuccess(InviteFriendNumBean bean) {
                view.getFriendNumSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getFriendNumFailure(errorMsg);
            }
        };
       model.inviteFriendNum(observer,token,type);
    }

    @Override
    public void createShareCode(String token,int key, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ShareCodeBean> observer = new BaseObserver<ShareCodeBean>(view) {
            @Override
            public void onSuccess(ShareCodeBean bean) {
                view.createShareCodeSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.createShareCodeFailure(errorMsg);
            }
        };
        model.createShareCode(observer,token,key,type);
    }

    @Override
    public void downLoadImg(String url) {
        if (view == null){
            view = getMvpView();
        }
        DownObserver<ResponseBody> observer = new DownObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody body) {
                new DownLoadThread(view,body).start();
            }

            @Override
            public void onFailure(String errorMsg) {
                view.downLoadImgFailure(errorMsg);
            }
        };
        model.downLoadImg(observer,url,this);
    }

    private  class DownLoadThread extends Thread{

        private ResponseBody body;
        private byte[] bys;
        private Bitmap bitmap;
        private ShareThemContract.IShareThemView view;
        DownLoadThread(ShareThemContract.IShareThemView view,ResponseBody body){
            this.body = body;
            this.view = view;
        }

        @Override
        public void run() {
            try {
                bys = body.bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
            view.downLoadImgSuccess(bitmap);
        }
    }

    @Override
    public void onProgress(String totalSize, String currentSize, String progress, boolean done) {

    }



    @Override
    public void onShareReward(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<ShareRewardBean> observer = new BaseObserver<ShareRewardBean>() {
            @Override
            public void onSuccess(ShareRewardBean bean) {
                view.onShareRewardSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onShareRewardFailure(errorMsg);
            }
        };
        model.onShareReward(observer,token);
    }

}
