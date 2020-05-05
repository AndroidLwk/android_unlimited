package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.bugly.beta.Beta;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.AboutSuperManBean;
import com.wuxiantao.wxt.adapter.recview.AboutSupermanRecViewAdapter;
import com.wuxiantao.wxt.bean.CurrentVersionBean;
import com.wuxiantao.wxt.mvp.contract.AboutSupermanContract;
import com.wuxiantao.wxt.mvp.presenter.AboutSupermanPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.VersionUpdatePopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.CacheDataUtils;
import com.wuxiantao.wxt.utils.VersionUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.ABOUT_SUPER;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AboutWuXianTaoActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 上午8:45
 * Description:${DESCRIPTION} 关于无线淘
 */
@ContentView(R.layout.activity_about_wuxiantao)
public class AboutWuXianTaoActivity extends MvpActivity<AboutSupermanPresenter, AboutSupermanContract.IAboutSupermanView> implements AboutSupermanContract.IAboutSupermanView {
    @ViewInject(R.id.super_man_about_rl)
    SmartRefreshLayout super_man_about_rl;
    @ViewInject(R.id.super_man_about_rv)
    RecyclerView super_man_about_rv;

    @Override
    public void initView() {
        Bundle bundle = getBundle();
        if (bundle != null) {
            String verName = bundle.getString(ABOUT_SUPER);
            super_man_about_rl.setEnableRefresh(false);
            super_man_about_rl.setEnableLoadMore(false);
            initLayout(verName);
        }
    }

    private void initLayout(String verName) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        RecViewItemDecoration decoration = new RecViewItemDecoration(this, LinearLayoutManager.VERTICAL);
        List<AboutSuperManBean> list = new ArrayList<>();
        AboutSuperManBean cacheBean = new AboutSuperManBean(getString(R.string.phone_cache), CacheDataUtils.getTotalCacheSize());
        list.add(new AboutSuperManBean(getString(R.string.version_update), verName));
        list.add(new AboutSuperManBean(getString(R.string.service_protocol), null));
        list.add(cacheBean);
        AboutSupermanRecViewAdapter adapter = new AboutSupermanRecViewAdapter(this, list);
        super_man_about_rv.setLayoutManager(manager);
        super_man_about_rv.addItemDecoration(decoration);
        super_man_about_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> {
            switch (position) {
                //版本更新
                case 0:
                    //获取版本号
                    //mPresenter.getAppCurrentVersion();
                    Beta.checkUpgrade();
                    break;
                //服务协议
                case 1:
                    $startActivity(ServerProtocolActivity.class);
                    break;
                //清除缓存
                case 2:
                    showDialog(getString(R.string.phone_cache), (dialog, which) -> {
                        CacheDataUtils.clearAllCache();
                        cacheBean.setVersion(CacheDataUtils.getTotalCacheSize());
                        list.set(2, cacheBean);
                        adapter.updataList(list, 2, 1);
                    });
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
                .setMiddleTitleText(getResources().getString(R.string.about_wxt)).build();
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
}
