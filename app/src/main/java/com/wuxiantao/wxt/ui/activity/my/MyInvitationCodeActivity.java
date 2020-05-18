package com.wuxiantao.wxt.ui.activity.my;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.SharePicBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.MyInvitationContract;
import com.wuxiantao.wxt.mvp.presenter.MyInvitationPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.popupwindow.SharePosterPopupWindow;
import com.wuxiantao.wxt.utils.DensityUtils;
import com.wuxiantao.wxt.utils.QRCodeUtil;
import com.wuxiantao.wxt.wxapi.WXShare;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的邀请码
 */
@ContentView(R.layout.activity_myinvitationcode)
public class MyInvitationCodeActivity extends MvpActivity<MyInvitationPresenter, MyInvitationContract> implements MyInvitationContract {
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

    @Override
    protected void initView() {
        setStatusBar();
        mPresenter.getSharePic(getAppToken());
        setOnClikListener(mine_fansi_back, iv_invateCode);

    }

    private void showSharePosterWindow(Bitmap bitmap) {
        new SharePosterPopupWindow.Build(this)
                .setRoundImageResource(bitmap)
                .setOnPopupClickListener(new SharePosterPopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void onShareWechat() {
                        //微信好友
                        WXShare.getInstance().shareImgMessage(false, bitmap);
                    }

                    @Override
                    public void onShareFriends() {
                        //微信朋友圈
                        WXShare.getInstance().shareImgMessage(true, bitmap);
                    }
                }).builder().showPopupWindow();
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.mine_fansi_back:
                finish();
                break;
            case R.id.iv_invateCode:
                if (mBitmap == null) {
                    return;
                }
                showSharePosterWindow(mBitmap);
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
        tv_hearderInfo.setText(info.getNickname());
        GlideImgManager.loadRoundImg(MyInvitationCodeActivity.this, info.getHeadimg(), iv_centerherader);
        GlideImgManager.loadCircleImg(this, R.mipmap.default_user_head_img, iv_header);
        String imgUrl = info.getSrc() + getLocalUserId();
        mBitmap = QRCodeUtil.createQRCodeBitmap(imgUrl, DensityUtils.dip2px(261), DensityUtils.dip2px(261));
        iv_invateCode.setImageBitmap(mBitmap);
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
        finish();
    }
}
