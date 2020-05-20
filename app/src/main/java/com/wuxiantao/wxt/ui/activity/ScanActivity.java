package com.wuxiantao.wxt.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.ScanBean;
import com.wuxiantao.wxt.mvp.contract.ScanContract;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.presenter.ScannerPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Copyright (C), 成都都爱玩科技有限公司
 * Date: 2020/5/20--14:47
 * Description: 扫一扫页面
 * Author: lht
 */
@ContentView(R.layout.activity_scan)
public class ScanActivity extends MvpActivity<ScannerPresenter, ScanContract.IScanView> implements ScanContract.IScanView, QRCodeView.Delegate {
    @ViewInject(R.id.img_scan_back)
    ImageView img_scan_back;
    @ViewInject(R.id.zbarview)
    ZBarView zbarview;

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    @Override
    protected void initView() {
        setStatusBar();
//        StatusBarUtil.setStatusBarColor(ScanActivity.this,getResources().getColor(R.color.white));
//        StatusBarUtil.setStatusBarDarkTheme(ScanActivity.this,true);
        zbarview.setDelegate(this);
        img_scan_back.setOnClickListener(v -> finish());
    }

    @Override
    protected ScannerPresenter CreatePresenter() {
        return new ScannerPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void scanAwardSuccess(ScanBean bean) {
        showOnlyConfirmDialog(bean.getMsg());
    }

    @Override
    public void scanAwardFailure(String failure) {
        ToastUtils.showToast(failure);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        ToastUtils.showToast(result);
        String[]  strs=result.split("pid=");
        if (strs.length==2){
            mPresenter.scanAward(getAppToken(),strs[1]);
        }
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(mContext, "打开相机出错，请允许相关权限！", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        zbarview.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        zbarview.startSpotAndShowRect(); // 显示扫描框，并且延迟0.1秒后开始识别
    }

    @Override
    protected void onStop() {
        zbarview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        zbarview.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
//            zbarview.decodeQRCode(picturePath);
        }
    }

}
