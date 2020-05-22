package com.wuxiantao.wxt.ui.fragment.main;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.MyMoneyCashBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.presenter.MinePresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.BalanceWithdrawActivity;
import com.wuxiantao.wxt.ui.activity.ChangePassWordActivity;
import com.wuxiantao.wxt.ui.activity.HelpCenterActivity;
import com.wuxiantao.wxt.ui.activity.MineBalanceActivity;
import com.wuxiantao.wxt.ui.activity.MineFanSiActivity;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;
import com.wuxiantao.wxt.ui.activity.ScanActivity;
import com.wuxiantao.wxt.ui.activity.ScratchCardActivity;
import com.wuxiantao.wxt.ui.activity.SettingActivity;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.activity.my.MyInvitationCodeActivity;
import com.wuxiantao.wxt.ui.activity.my.MyMemberActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.ImagePopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.wuxiantao.wxt.config.Constant.IS_ATTENTION_PUBLIC;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 我的
 */
@ContentView(R.layout.fragment_my_deposit)
public class MyDepositFragment extends MvpFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {
    @ViewInject(R.id.mine_classic_header)
    ClassicsHeader mine_classic_header;
    @ViewInject(R.id.mine_rl)
    SmartRefreshLayout mine_rl;
    @ViewInject(R.id.tv_mine_member)
    TextView tv_mine_member;
    @ViewInject(R.id.tv_mine_friend)
    TextView tv_mine_friend;
    @ViewInject(R.id.tv_mine_code)
    TextView tv_mine_code;
    @ViewInject(R.id.tv_promotioninvitation)
    TextView tv_promotioninvitation;
    @ViewInject(R.id.tv_mine_second_pass)
    TextView tv_mine_second_pass;
    @ViewInject(R.id.tv_mine_set)
    TextView tv_mine_set;
    @ViewInject(R.id.tv_mine_freedback)
    TextView tv_mine_freedback;
    @ViewInject(R.id.rt_personInfo)
    RelativeLayout rt_personInfo;
    @ViewInject(R.id.tv_blanseValue)
    TextView tv_blanseValue;
    @ViewInject(R.id.tv_officialGroup)
    TextView tv_officialGroup;
    @ViewInject(R.id.tv_money_title)
    TextView tv_money_title;
    @ViewInject(R.id.tv_crashMoney_title)
    TextView tv_crashMoney_title;
    @ViewInject(R.id.tv_crashValue)
    TextView tv_crashValue;
    @ViewInject(R.id.iv_mine_header)
    ImageView iv_mine_header;
    @ViewInject(R.id.iv_headerName)
    TextView iv_headerName;
    @ViewInject(R.id.tv_scrapcard_title)
    TextView tv_scrapcard_title;
    @ViewInject(R.id.tv_scrapcard_num)
    TextView tv_scrapcard_num;
    @ViewInject(R.id.tv_num_title)
    TextView tv_num_title;
    @ViewInject(R.id.tv_yesterday_num)
    TextView tv_yesterday_num;
    @ViewInject(R.id.tv_dirver)
    TextView tv_dirver;
    @ViewInject(R.id.tv_bag_title)
    TextView tv_bag_title;
    @ViewInject(R.id.tv_mynickname)
    TextView tv_mynickname;
    @ViewInject(R.id.rt_balance)
    RelativeLayout rt_balance;
    @ViewInject(R.id.tv_driver_two)
    TextView tv_driver_two;
    @ViewInject(R.id.tv_total_num)
    TextView tv_total_num;
    @ViewInject(R.id.sbt_moreInfo)
    StateButton sbt_moreInfo;
    @ViewInject(R.id.iv_isvip)
    ImageView iv_isvip;
    @ViewInject(R.id.ll_mine_scan)
    LinearLayout ll_mine_scan;


    private LoadingDialog loadingDialog;

    @Override
    protected MinePresenter CreatePresenter() {
        return new MinePresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(sbt_moreInfo, tv_mine_member, tv_mine_set, tv_blanseValue, tv_officialGroup, tv_money_title, tv_crashMoney_title, tv_crashValue,
                tv_mine_friend, tv_mine_code, tv_promotioninvitation, tv_mine_second_pass, tv_mine_freedback, rt_personInfo, ll_mine_scan);
        loadingDialog = createLoadingDialog();
        mPresenter.myMoneyCash(getAppToken());
        initRefreshLoad();
    }

    private LoadingDialog createLoadingDialog() {
        return new LoadingDialog.Build(getContext()).build();
    }

    private void initRefreshLoad() {
        mine_classic_header.setBackgroundResource(R.drawable.base_title_background);
        mine_rl.setRefreshHeader(mine_classic_header);
        mine_rl.setEnableLoadMore(false);
        mine_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            if (!isEmpty(getAppToken())) {
                mPresenter.myMoneyCash(getAppToken());
            }
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });

    }

    private void showOfficialWindow(String url) {
        new ImagePopupWindow.Build(getContext())
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setImageUrl(url)
                .builder().showPopupWindow();

    }

    //官方url
    private String official_url;

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.tv_crashMoney_title://提现
            case R.id.tv_crashValue:
                Bundle bundle = new Bundle();
                bundle.putBoolean(IS_ATTENTION_PUBLIC, true);
                $startActivity(BalanceWithdrawActivity.class, bundle);
                break;
            case R.id.tv_officialGroup:
                if (!isEmpty(official_url)) {
                    showOfficialWindow(official_url);
                }
                break;
            case R.id.tv_blanseValue://余额
            case R.id.tv_money_title:
                $startActivity(MineBalanceActivity.class);
                break;
            case R.id.tv_mine_member:
                $startActivity(MyMemberActivity.class);
                break;
            case R.id.tv_mine_friend:
                $startActivity(MineFanSiActivity.class);
                break;
            case R.id.tv_mine_code:
                $startActivity(MyInvitationCodeActivity.class);
                break;
            case R.id.tv_promotioninvitation:
                $startActivity(ShareThemActivity.class);
                break;
            case R.id.tv_mine_second_pass:
//                $startActivity(SettingPassWordActivity.class);
                $startActivity(ChangePassWordActivity.class);
                break;
            case R.id.tv_mine_set:
                $startActivity(SettingActivity.class);
                break;
            case R.id.tv_mine_freedback://问题反馈
                $startActivity(HelpCenterActivity.class);
                break;
            case R.id.rt_personInfo:
                $startActivity(MyInformationActivity.class);
                break;
            case R.id.sbt_moreInfo://更多详情
                $startActivity(ScratchCardActivity.class);
                break;
            case R.id.ll_mine_scan://扫一扫
                requestCodeQRCodePermissions();
                break;
        }
    }

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        } else {
            $startActivity(ScanActivity.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isEmpty(getAppToken())) {
            mPresenter.myMoneyCash(getAppToken());
        }
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void showMyMoneyCash(MyMoneyCashBean info) {
        this.official_url = info.getQun_img();
        if (!TextUtils.isEmpty(getUserInfo(USER_HEAD_IMG))) {
            GlideImgManager.loadCircleImg(getContext(), getUserInfo(USER_HEAD_IMG), iv_mine_header);
        }
        iv_headerName.setText(info.getUp_nickname());
        iv_isvip.setVisibility(info.getIs_vip() != 0 ? View.VISIBLE : View.INVISIBLE);
        String time = "";
        switch (info.getIs_vip()) {
            case -1:
                time = getString(R.string.ordinary_member);
                break;
            case 0:
                time = "";
                break;
            case 1:
                time = getString(R.string.year_member);
                break;
            case 2:
                time = getString(R.string.month_member);
                break;
        }
        tv_mynickname.setText(time);
        tv_scrapcard_num.setText(info.getUnopen_card() + "");
        tv_total_num.setText("累计刮卡" + info.getTotal_use() + "张");
        tv_yesterday_num.setText("昨日获得" + info.getTotal_yesterday() + "张");
        tv_blanseValue.setText("￥" + info.getMoney() + "元");
        tv_crashValue.setText("￥" + info.getCash() + "元");

    }
}
