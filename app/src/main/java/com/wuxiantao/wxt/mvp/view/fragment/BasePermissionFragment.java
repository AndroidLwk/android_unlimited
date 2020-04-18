package com.wuxiantao.wxt.mvp.view.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.wuxiantao.wxt.listener.PermissionListener;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BasePermissionFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午6:31
 * Description:${DESCRIPTION}
 */
public abstract class BasePermissionFragment<P extends MvpPresenter,V extends MvpView> extends BaseJumpFragment<P,V> {

    //权限申请回掉接口
    private PermissionListener permissionListener;

    //权限申请码
    public int REQUEST_CODE = 0x001;

    public void requestPermission(String[] permissions,PermissionListener listener) {
        this.permissionListener = listener;
        //检查是否授权
        if (checkPermissions(permissions)){
            //申请成功
            permissionListener.permissinSucceed();
        }else {
            List<String> needPermissions = getPermissions(permissions);
            ActivityCompat.requestPermissions(mActivity,needPermissions.toArray(new String[needPermissions.size()]),REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE){
            if (verificationPermissions(grantResults)){
                //权限申请成功
                if (permissionListener != null){
                    permissionListener.permissinSucceed();
                }
            }else {
                //权限申请失败
                if (permissionListener != null){
                    permissionListener.permissionFailing(permissions);
                }
                startActivity(getAppDetailSettingIntent());
            }
        }
    }

    /**
     * 需要申请的权限
     * @param permissions
     * @return
     */
    private List<String> getPermissions(String permissions[]){
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(mActivity,permission) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(mActivity,permission)){
                permissionList.add(permission);
            }
        }
        return permissionList;
    }


    private boolean verificationPermissions(int results[]){
        for (int result : results){
            if (result != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }




    //获取 APP 详情页面intent
    public Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package",mActivity.getPackageName(), null));
        }
        else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mActivity.getPackageName());
        }
        return localIntent;
    }


    /**
     * 检查权限
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String permissions[]){
        //判断sdk版本是否 < 23
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return  true;
        }
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(mActivity,permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}
