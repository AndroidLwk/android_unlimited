package com.wuxiantao.wxt.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ssm.sp.SPSecuredUtils;
import com.tencent.bugly.beta.Beta;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.AboutSuperManBean;
import com.wuxiantao.wxt.adapter.recview.AboutSupermanRecViewAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.mvp.contract.AboutSupermanContract;
import com.wuxiantao.wxt.mvp.presenter.AboutSupermanPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.VersionUpdatePopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.AppUtils;
import com.wuxiantao.wxt.utils.CacheDataUtils;
import com.wuxiantao.wxt.utils.VersionUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.MESSAGE_OUT_LOGIN;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutWuXianTaoActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 上午8:45
 * Description:${DESCRIPTION} 设置
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends MvpActivity<AboutSupermanPresenter, AboutSupermanContract.IAboutSupermanView> implements AboutSupermanContract.IAboutSupermanView {
    @ViewInject(R.id.super_man_about_rl)
    SmartRefreshLayout super_man_about_rl;
    @ViewInject(R.id.super_man_about_rv)
    RecyclerView super_man_about_rv;
    @ViewInject(R.id.my_information_exit)
    StateButton my_information_exit;

    @Override
    public void initView() {
        setOnClikListener(my_information_exit);
        super_man_about_rl.setEnableRefresh(false);
        super_man_about_rl.setEnableLoadMore(false);
        initLayout();
    }

    @Override
    protected void widgetClick(int id) {
        super.widgetClick(id);
        if (id == R.id.my_information_exit) {//退出登录
            showDropOutLoginDialog(getResources().getString(R.string.are_you_quit), (dialog, which) ->
                    mPresenter.onStopApp(getAppToken()));
        }
    }

    private void showDropOutLoginDialog(String title, DialogInterface.OnClickListener listener) {
        showDialog(title, listener);
    }

    private void initLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        RecViewItemDecoration decoration = new RecViewItemDecoration(this, LinearLayoutManager.VERTICAL);
        List<AboutSuperManBean> list = new ArrayList<>();
        AboutSuperManBean cacheBean = new AboutSuperManBean(getString(R.string.phone_cache), CacheDataUtils.getTotalCacheSize());
        list.add(new AboutSuperManBean(getString(R.string.udpate_user_pw), null));
        list.add(new AboutSuperManBean(getString(R.string.setting_text1), null));
        list.add(cacheBean);
        list.add(new AboutSuperManBean(getString(R.string.version_update), AppUtils.getVersionName()));
        list.add(new AboutSuperManBean(getString(R.string.setting_text2), null));
        AboutSupermanRecViewAdapter adapter = new AboutSupermanRecViewAdapter(this, list);
        super_man_about_rv.setLayoutManager(manager);
        // super_man_about_rv.addItemDecoration(decoration);
        super_man_about_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> {
            switch (position) {
                case 0://修改密码
                    $startActivity(SettingPassWordActivity.class);
                    break;
                case 1://客服服务
                    $startActivity(HelpCenterActivity.class);
                    break;
                case 2://清除缓存
                    showDialog(getString(R.string.phone_cache), (dialog, which) -> {
                        CacheDataUtils.clearAllCache();
                        cacheBean.setVersion(CacheDataUtils.getTotalCacheSize());
                        list.set(2, cacheBean);
                        adapter.updataList(list, 2, 1);
                    });
                case 3://版本更新
                    Beta.checkUpgrade();
                    break;
                case 4://关于我们
                    break;
            }
        });
    }

    //版本获取成功
    @Override
    public void getAppCurrentVersionSuccess(CurrentVersionBean bean) {
        boolean isNeedUpdate = VersionUtils.newInstance().isNeedUpdate(bean);
        if (isNeedUpdate) {
            showVersionUpdateWindow(bean.getSite_url());
        } else {
            showOnlyConfirmDialog(getString(R.string.already_latest_version));
        }
    }

    @Override
    public void getAppCurrentVersionFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    private void showVersionUpdateWindow(String url) {
        new VersionUpdatePopupWindow.Build(this)
                .setPopupWindowWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnPopupClickListener(() -> {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }).builder().showPopupWindow();
    }


    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.setting_text3)).build();
    }

    @Override
    protected AboutSupermanPresenter CreatePresenter() {
        return new AboutSupermanPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
    @SuppressLint("HandlerLeak")
    private Handler exitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_OUT_LOGIN) {
                loadingDialog.dismissLoadingDialog();
                $startActivity(WeChatLoginActivity.class, true);
                finish();
            }
        }
    };
    private LoadingDialog loadingDialog;
    @Override
    public void onStopAppSuccess(String msg) {
        loadingDialog = new LoadingDialog.Build(mContext)
                .setLoadingText(R.string.exit_loading_).build();
        showLoading();
        exitHandler.sendEmptyMessageDelayed(MESSAGE_OUT_LOGIN, REFRESH_LOAD_MORE_TIME);
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).remove(TOKEN);
    }

    @Override
    public void onStopAppFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }
}
