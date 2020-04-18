package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.InviteRuleRecViewAdapter;
import com.wuxiantao.wxt.bean.InviteFriendNumBean;
import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.bean.ShareCodeBean;
import com.wuxiantao.wxt.bean.ShareRewardBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.ShareThemContract;
import com.wuxiantao.wxt.mvp.presenter.ShareThemPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.share.WeChatShareListener;
import com.wuxiantao.wxt.share.WeChatShareResultListener;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.viewpager.CoverFlowViewPager;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.SharePosterPopupWindow;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.wuxiantao.wxt.wxapi.WXShare;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.wuxiantao.wxt.config.Api.DOMAIN_NAME;
import static com.wuxiantao.wxt.config.Constant.IS_REVIEW;
import static com.wuxiantao.wxt.config.Constant.IS_SHARE_SUCCESS;
import static com.wuxiantao.wxt.config.Constant.REQUEST_SHARE_MORE_THEM;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_SHARE;
import static com.wuxiantao.wxt.config.Constant.RESULT_SHARE_MORE_THEM;
import static com.wuxiantao.wxt.config.Constant.REWARD_MONEY;
import static com.wuxiantao.wxt.config.Constant.SHARE_THEM_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ShareThemActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 下午5:07
 * Description:${DESCRIPTION} 分享 主题
 */
@ContentView(R.layout.activity_share_them)
public class ShareThemActivity extends MvpActivity<ShareThemPresenter, ShareThemContract.IShareThemView> implements ShareThemContract.IShareThemView, WeChatShareResultListener {
    @ViewInject(R.id.share_them_title_back)
    RelativeLayout share_them_title_back;
    @ViewInject(R.id.share_them_more)
    TextView share_them_more;
    @ViewInject(R.id.share_them_vp)
    CoverFlowViewPager share_them_vp;
    @ViewInject(R.id.share_them_current_count)
    TextView share_them_current_count;
    @ViewInject(R.id.share_them_sum_count)
    TextView share_them_sum_count;
    @ViewInject(R.id.share_them_poster)
    StateButton share_them_poster;
    @ViewInject(R.id.share_them_invite_num)
    TextView share_them_invite_num;
    @ViewInject(R.id.share_them_rule_rv)
    RecyclerView share_them_rule_rv;
    @ViewInject(R.id.share_them_total_layout)
    LinearLayout share_them_total_layout;
    @ViewInject(R.id.share_them_rule_layout)
    LinearLayout share_them_rule_layout;
    private int position = 0;
    private LoadingDialog loadingDialog;
    private boolean isShare;

    @Override
    protected ShareThemPresenter CreatePresenter() {
        return new ShareThemPresenter();
    }

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
        mPresenter.getShareBg(1, 3);
        mPresenter.getFriendNum(getAppToken(), 0);

        boolean isReview = getSPBoolean(IS_REVIEW);
        if (isReview){
            share_them_rule_layout.setVisibility(View.GONE);
        }else {
            share_them_rule_layout.setVisibility(View.VISIBLE);
            initInviteRuleLayout();
        }
        setOnClikListener(share_them_title_back, share_them_more, share_them_poster,share_them_total_layout);
        WeChatShareListener.getInstance().addListener(this);
    }

    //规则列表
    private void initInviteRuleLayout() {
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.share_them_rule_title));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0, 20);
        InviteRuleRecViewAdapter adapter = new InviteRuleRecViewAdapter(this, list);
        share_them_rule_rv.setLayoutManager(manager);
        share_them_rule_rv.addItemDecoration(decoration);
        share_them_rule_rv.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //返回
            case R.id.share_them_title_back:
                back();
                break;
            //查看更多主题
            case R.id.share_them_more:
                $startActivityForResult(MoreThemActivity.class, REQUEST_SHARE_MORE_THEM);
                break;
            //分享专属海报 生成二维码
            case R.id.share_them_poster:
                mPresenter.onShareReward(getAppToken());
                break;
            //累计成功邀请
            case R.id.share_them_total_layout:
                $startActivity(MineFanSiActivity.class);
                break;
        }
    }

    private void back(){
        Bundle bundle = new Bundle();
        bundle.putString(REWARD_MONEY,reward);
        bundle.putBoolean(IS_SHARE_SUCCESS,isShare);
        getResult(RESULT_CODE_SHARE,bundle);
    }

    @Override
    public void getShareBgSuccess(ShareBackGroundBean bean) {
        initShareThemLayout(bean);
    }

    private void initShareThemLayout(ShareBackGroundBean bean) {
        List<ShareBackGroundBean.ListBean> bgList = bean.getList();
        List<View> views = new ArrayList<>();
        for (int i = 0; i < bgList.size(); i++) {
            ImageView img = new ImageView(this);
            GlideImgManager.loadRoundImg(this, bgList.get(i).getImg(), img, 5.0f);
            views.add(img);
        }
        share_them_sum_count.setText(String.valueOf(views.size()));
        share_them_vp.setViewList(views);
        share_them_vp.setOnPageSelectListener(p -> {
            this.position = p;
            share_them_current_count.setText(String.valueOf(p + 1));
        });
    }

    private String reward;

    //分享获取奖励
    @Override
    public void onShareRewardSuccess(ShareRewardBean bean) {
        reward = String.valueOf(bean.getNum());
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.create_poster_loading).build();
        mPresenter.createShareCode(getAppToken(), position, 0);
    }


    //二维码生成成功
    @Override
    public void createShareCodeSuccess(ShareCodeBean bean) {
        String url = DOMAIN_NAME + bean.getSrc();
        //下载二维码图片
        mPresenter.downLoadImg(url);
    }


    //二维码生成成功后 分享
    @Override
    public void downLoadImgSuccess(Bitmap bitmap) {
        runOnUiThread(() -> showSharePosterWindow(bitmap));
    }

    @Override
    public void onShareRewardFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void getShareBgFailure(String failure) {
        ToastUtils.error(failure);
    }


    @Override
    public void downLoadImgFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_SHARE_MORE_THEM && resultCode == RESULT_SHARE_MORE_THEM) {
            if (data != null) {
                this.position = data.getIntExtra(SHARE_THEM_ID, 0);
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.create_poster_loading).build();
                mPresenter.createShareCode(getAppToken(), position, 0);
            }
        }
    }

    private void showSharePosterWindow(Bitmap bitmap) {
        new SharePosterPopupWindow.Build(this)
                .setRoundImageResource(bitmap)
                .setOnPopupClickListener(new SharePosterPopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void onShareWechat() {
                        //微信好友
                        WXShare.getInstance().shareImgMessage(false,bitmap);
                    }

                    @Override
                    public void onShareFriends() {
                        //微信朋友圈
                        WXShare.getInstance().shareImgMessage(true,bitmap);
                    }
                }).builder().showPopupWindow();
    }

    @Override
    public void createShareCodeFailure(String failure) {
        ToastUtils.error(failure);
    }


    @Override
    public void getFriendNumSuccess(InviteFriendNumBean bean) {
        share_them_invite_num.setText(String.valueOf(bean.getFriend_num()));
    }

    @Override
    public void getFriendNumFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }

    @Override
    public void onShareSuccess() {
        isShare = true;
        showOnlyConfirmDialog(getString(R.string.share_success));
    }

    @Override
    public void onShareError() {
        showOnlyConfirmDialog(getString(R.string.share_error));
    }

    @Override
    public void onShareCancel() {
        isShare = true;
        showOnlyConfirmDialog(getString(R.string.share_cancel));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeChatShareListener.getInstance().removeListener(this);
    }


}
