package com.wuxiantao.wxt.ui.activity.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.MyInvitationContract;
import com.wuxiantao.wxt.mvp.presenter.MyInvitationPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.share.WeChatShareListener;
import com.wuxiantao.wxt.share.WeChatShareResultListener;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.ShareInviteCodePopWindow;
import com.wuxiantao.wxt.ui.popupwindow.SharePosterPopupWindow;
import com.wuxiantao.wxt.utils.DensityUtils;
import com.wuxiantao.wxt.utils.QRCodeUtil;
import com.wuxiantao.wxt.wxapi.WXShare;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.IS_SHARE_SUCCESS;
import static com.wuxiantao.wxt.config.Constant.REQUEST_SHARE_MORE_THEM;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_SHARE;
import static com.wuxiantao.wxt.config.Constant.RESULT_SHARE_MORE_THEM;
import static com.wuxiantao.wxt.config.Constant.REWARD_MONEY;
import static com.wuxiantao.wxt.config.Constant.SHARE_THEM_ID;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/18--12:01
 * Description: 我的邀请码
 * Author: lht
 */
@ContentView(R.layout.activity_myinvitationcode)
public class MyInvitationCodeActivity extends MvpActivity<MyInvitationPresenter, MyInvitationContract> implements MyInvitationContract, WeChatShareResultListener {
    @ViewInject(R.id.mine_fansi_back)
    RelativeLayout mine_fansi_back;
    @ViewInject(R.id.mine_fansi_title_img)
    ImageView mine_fansi_title_img;
    @ViewInject(R.id.mine_fansi_title_text)
    TextView mine_fansi_title_text;
    @ViewInject(R.id.tv_hearderInfo)
    TextView tv_hearderInfo;
    @ViewInject(R.id.iv_invateCode)
    ImageView iv_invateCode;
    @ViewInject(R.id.iv_header)
    ImageView iv_header;
    @ViewInject(R.id.iv_centerherader)
    ImageView iv_centerherader;
    @ViewInject(R.id.img_invateCode_share)
    ImageView img_invateCode_share;

    @Override
    protected void initView() {
        setStatusBar();
        mPresenter.getSharePic(getAppToken());
        setOnClikListener(mine_fansi_back, iv_invateCode,img_invateCode_share);
        WeChatShareListener.getInstance().addListener(this);
    }


    private void showSharePosterWindow(Bitmap bitmap) {
        new ShareInviteCodePopWindow.Build(this)
                .setRoundImageResource(bitmap)
                .setOnPopupClickListener(new ShareInviteCodePopWindow.Build.OnPopupClickListener() {
                    @Override
                    public void onShareWechat() {
                        //微信好友
                        WXShare.getInstance().shareImgMessag(false, bitmap);
                    }
                    @Override
                    public void onShareFriends() {
                        //微信朋友圈
                        WXShare.getInstance().shareImgMessag(true, bitmap);
                    }
                }).builder().showPopupWindow();
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.mine_fansi_back:
//                back();
                finish();
            case R.id.img_invateCode_share:
                runOnUiThread(() -> showSharePosterWindow(mBitmap));
                break;
            case R.id.iv_invateCode:
                break;
        }
    }

    @Override
    protected MyInvitationPresenter CreatePresenter() {
        return new MyInvitationPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private Bitmap mBitmap;

    @Override
    public void showShareCode(SharePicBean info) {
        runOnUiThread(() -> {
            tv_hearderInfo.setText(info.getNickname());
            GlideImgManager.loadRoundImg(MyInvitationCodeActivity.this, info.getHeadimg(), iv_centerherader);
            GlideImgManager.loadCircleImg(this, info.getHeadimg(), iv_header);
            String imgUrl = info.getSrc() + getLocalUserId();
            mBitmap = QRCodeUtil.createQRCodeBitmap(imgUrl, DensityUtils.dip2px(261), DensityUtils.dip2px(261));
            iv_invateCode.setImageBitmap(mBitmap);
        });
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
//        finish();
    }


    @Override
    public void onShareSuccess() {
        showOnlyConfirmDialog("分享成功！");
        Log.e("33333333", "onShareSuccess: 分享成功");
    }

    @Override
    public void onShareError() {
        showOnlyConfirmDialog("分享失败！");
        Log.e("33333333", "onShareSuccess: 分享失败");
    }

    @Override
    public void onShareCancel() {
        showOnlyConfirmDialog("分享取消！");
        Log.e("33333333", "onShareSuccess: 分享取消");
    }
}
