package com.wuxiantao.wxt.base;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.umeng.analytics.MobclickAgent;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.listener.PermissionListener;
import com.wuxiantao.wxt.ui.dialog.DialogBuilder;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午7:28
 * Description:${DESCRIPTION}
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    //权限申请回掉接口
    private PermissionListener permissionListener;
    //权限申请码
    public int REQUEST_CODE = 0x001;
    private   long lastClick = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //PushAgent.getInstance(this).onAppStart();//【友盟+】Push后台进行日活统计及多维度推送
        //设置状态栏透明
//        StatusBarUtil.setTranslucentStatus(this);
        //设置默认不弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        setTitle();
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    // 设置 SPApplication 字体不随系统字体设置改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res != null) {
            Configuration config = res.getConfiguration();
            if (config != null && config.fontScale != 1.0f) {
                config.fontScale = 1.0f;
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        }
        return res;
    }


    @Override
    public void onClick(View v) {
        if (fastClick()){
            widgetClick(v.getId());
        }
    }

    //设置状态栏完全透明
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 500){
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }


    protected  void setOnClikListener(View ...components) {
        if (components.length <= 0){
            return;
        }
        for (View v : components){
            v.setOnClickListener(this);
        }
    }

    private Bundle  addIntegerBundle(String key,Object value){
        Bundle  bundle = new Bundle();
        if (value instanceof Integer){
            bundle.putInt(key,(Integer) value);
        }
        else if (value instanceof String){
            bundle.putString(key,(String) value);
        }
        else if (value instanceof Double){
            bundle.putDouble(key,(Double) value);
        }
        return bundle;
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla) {
        Intent intent = new Intent(this,cla);
        overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param bundle
     */
    protected void $startActivity(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(this,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value) {
        Intent intent = new Intent(this,cla);
        intent.putExtras(addIntegerBundle(key,value));
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value,int enterAnim, int exitAnim) {
        Intent intent = new Intent(this,cla);
        intent.putExtras(addIntegerBundle(key,value));
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(enterAnim,exitAnim);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param enterAnim
     * @param exitAnim
     */
    protected void $startActivity(Class<?> cla,int enterAnim, int exitAnim) {
        Intent intent = new Intent(this,cla);
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(enterAnim,exitAnim);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param str
     */
    protected void $startActivity(Class<?> cla,String ...str) {
        if (str.length <= 0){
            $startActivity(cla);
        }else {
            Bundle bundle = new Bundle();
            String key = "";
            String value = "";
            for (int i = 0;i < str.length;i++){
                if (i % 2 == 0){
                    key = str[i];
                }else {
                    value = str[i];
                }
                bundle.putString(key,value);
            }
            Intent intent = new Intent(this,cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(cla,bundle);
        }
    }

    /**
     *  界面跳转
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla,int requestCode) {
        Intent intent = new Intent(this,cla);
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivityForResult(intent,requestCode);
    }

    /**
     *  界面跳转
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla,Bundle bundle,int requestCode) {
        if (bundle == null){
            $startActivityForResult(cla,requestCode);
        }else {
            Intent intent = new Intent(this,cla);
//            BaseApplication.getInstance().addActivity(this);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            startActivityForResult(intent,requestCode);
        }
    }


    public void requestPermission(String[] permissions, PermissionListener listener) {
        this.permissionListener = listener;
        //检查是否授权
        if (checkPermissions(permissions)){
            //申请成功
            permissionListener.permissinSucceed();
        }else {
            List<String> needPermissions = getPermissions(permissions);
            ActivityCompat.requestPermissions(this,needPermissions.toArray(new String[needPermissions.size()]),REQUEST_CODE);
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
//                showFaiingDialog(permissions);
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
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
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
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }


    //获取 APP 详情页面intent
    public  Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package",getPackageName(), null));
        }
        else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }

    public void openNetSetting(){
        Intent intent=null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if(Build.VERSION.SDK_INT > 10){
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        }else{
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        startActivity(intent);
    }


    //点击软键盘之外的空白处，隐藏软件盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            hideSoftInput();
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)){
            return true;
        }
        return onTouchEvent(ev);
    }


    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    protected Bundle getBundle(){
        return getIntent().getExtras();
    }

    protected void getResult(int resultCode){
        Intent intent = getIntent();
        setResult(resultCode,intent);
        this.finish();
    }

    protected void getResult(int resultCode,Bundle bundle){
        Intent intent = getIntent();
        if (bundle != null){
            intent.putExtras(bundle);
            setResult(resultCode,intent);
            this.finish();
        }else {
            getResult(resultCode);
        }
    }

    protected void showDialog(String title, DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener)
                .setDialogNegativeBtn().build().show();
    }

    protected void showDialog(String title, String msg,DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(listener)
                .setDialogNegativeBtn().build().show();
    }

    protected void showDialog(String title, String msg,String confirmText,String confirmColor,String cancelColor,
                              DialogInterface.OnClickListener listener){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogPositiveBtn(confirmText,listener,confirmColor)
                .setDialogNegativeBtn(BaseApplication.getAppContext().getString(R.string.cancel),cancelColor).build().show();
    }

    protected void showDialog(String title,String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

    protected void showDialog(String title,String msg,String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

    protected void showDialog(String title,@StringRes int msg, String cancelText){
        new DialogBuilder.Builder(this)
                .setDialogTitle(title)
                .setDialogMessage(msg)
                .setDialogLineColor(Color.GRAY)
                .setDialogNegativeBtn(cancelText).build().show();
    }

    /**
     * 判断非空
     * @param str
     * @return
     */
    protected boolean isEmpty(String str){
        if (str == null){
            return true;
        }
        else if (str.equals("") && TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取文本输入框内容文本
     * @param editText
     * @return
     */
    protected String  getEdtText(EditText editText){
        //去除空格
        return editText.getText().toString().replace(" ","");
    }

    public void setTitle(){}

    public void initView(){}

    public void widgetClick(int viewId){}
}
