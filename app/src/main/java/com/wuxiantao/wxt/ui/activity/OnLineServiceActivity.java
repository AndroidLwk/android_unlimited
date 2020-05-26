package com.wuxiantao.wxt.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.OnLineBean;
import com.wuxiantao.wxt.adapter.recview.OnLineServiceRecViewAdaper;
import com.wuxiantao.wxt.bean.OnLineServiceBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.OnLineServiceContract;
import com.wuxiantao.wxt.mvp.presenter.OnLineServicePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.popupwindow.DownLoadPopupWindow;
import com.wuxiantao.wxt.utils.FileUtils;
import com.wuxiantao.wxt.utils.StatusBarUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OnLineServiceActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 上午11:47
 * Description:${DESCRIPTION} 在线客服
 */
@ContentView(R.layout.activity_service_on_line)
public class OnLineServiceActivity extends MvpActivity<OnLineServicePresenter, OnLineServiceContract.IOnLineServiceView> implements OnLineServiceContract.IOnLineServiceView {
    @ViewInject(R.id.service_on_line_rl)
    SmartRefreshLayout service_on_line_rl;
    @ViewInject(R.id.online_service_rv)
    NestRecyclerView online_service_rv;
    @ViewInject(R.id.online_service_img)
    ImageView online_service_img;
    @ViewInject(R.id.online_service_back)
    ImageView online_service_back;

    private String url;
    private DownLoadPopupWindow.Build build;

    @Override
    public void initView(Bundle savedInstanceState) {
        setStatusBar();
        StatusBarUtils.setDarkMode(this);
        service_on_line_rl.setEnableLoadMore(false);
        service_on_line_rl.setEnableRefresh(false);
        mPresenter.getOnLineServiceInfo(getAppToken());
        setOnLongClikListener(online_service_img);
        setOnClikListener(online_service_back);
    }

    @Override
    protected void widgetClick(int id) {
        if (id == R.id.online_service_back) {
            finish();
        }
    }

    @Override
    protected void onLongClick(int id) {
        if (id == R.id.online_service_img) {
            showDialog(getString(R.string.img_down_load), getString(R.string.img_down_load_msg), (dialog, which) -> {
                if (!isEmpty(url)) {
//                    showProgressWindow();
                    mPresenter.onDownloadFile(url);
                }
            });
        }
    }


    private void showProgressWindow() {
        build = new DownLoadPopupWindow.Build(this);
        build.builder().showPopupWindow();
    }

    @Override
    public void getOnLineServiceInfoSuccess(OnLineServiceBean bean) {
        this.url = bean.getKfimg();
        GlideImgManager.loadImg(this, url, online_service_img);
        initLayout(bean);
    }

    @Override
    public void getOnLineServiceInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    private void initLayout(OnLineServiceBean bean) {
        List<String> wxList = bean.getWx();
        List<String> telList = bean.getTel();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0, 50);
        OnLineServiceRecViewAdaper adaper = new OnLineServiceRecViewAdaper(this, initList(wxList, telList));
        online_service_rv.setLayoutManager(manager);
        online_service_rv.addItemDecoration(decoration);
        online_service_rv.setAdapter(adaper);
        adaper.setOnItemClickListener(TextViewUtils::copy);
    }


    private List<OnLineBean> initList(List<String> wxList, List<String> telList) {
        List<OnLineBean> list = new ArrayList<>();
        if (!wxList.isEmpty()) {
            for (int i = 0; i < wxList.size(); i++) {
                OnLineBean bean = new OnLineBean();
                bean.setBefoType(getString(R.string.weichat_num));
                bean.setAfterName(getString(R.string.kefu));
                bean.setNumber(wxList.get(i));
                bean.setSn(i + 1);
                list.add(bean);
            }
        }
        if (!telList.isEmpty()) {
            for (int i = 0; i < telList.size(); i++) {
                OnLineBean bean = new OnLineBean();
                bean.setBefoType(getString(R.string.number));
                bean.setAfterName(getString(R.string.kefu));
                bean.setNumber(telList.get(i));
                bean.setSn(i + 1);
                list.add(bean);
            }
        }
        return list;
    }

    //图片下载成功
    @Override
    public void downLoadFileSuccess(Bitmap bitmap) {
        //保存手机相册
        boolean isSuccess = FileUtils.saveBmp2Gallery(bitmap);
        runOnUiThread(() -> {
            if (isSuccess){
                ToastUtils.success(getString(R.string.save_img_success));
            }
        });
    }


    //下载进度
    @Override
    public void onProgress(String totalSize, String currentSize, String progress, boolean done) {
        if (build != null) {
            build.setDownloadProgress(totalSize, currentSize, progress);
            if (done) {
                build.downFinish();
            }
        }
    }

    @Override
    public void downLoadFileFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected OnLineServicePresenter CreatePresenter() {
        return new OnLineServicePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}
