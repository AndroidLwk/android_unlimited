package com.wuxiantao.wxt.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.listener.PermissionListener;
import com.wuxiantao.wxt.service.CountDownTimeService;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.AppUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.IS_STARTED_LOADING;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WelcomeActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午2:56
 * Description:${DESCRIPTION} 欢迎界面
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {
    @ViewInject(R.id.welcome_adsRl)
    FrameLayout welcome_adsRl;
    @ViewInject(R.id.welcome_img)
    ImageView welcome_img;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initView() {
        setMissionBoard();
        //启动倒计时
        CountDownTimeService.enqueueWork(getApplicationContext(), new Intent());
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).put(IS_STARTED_LOADING, true);
        requestPermission(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                new PermissionListener() {
                    @Override
                    public void permissionFailing(String[] permissions) {

                    }

                    @Override
                    public void permissinSucceed() {
                        loadSplashAd();
                    }
                });
    }

    private void setMissionBoard() {
        //去掉状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //加载开屏广告
    private void loadSplashAd() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
//        String str1 = df.format(new Date());// new Date()为获取当前系统时间
//        String str2 = "2020-05-28 08:00";
//        try {
//            Date date1 = df.parse(str1);
//            Date date2 = df.parse(str2);
//            int i = date1.compareTo(date2);//0 相等 小于0 之前 大于0 之后
//            if (i > 0 || i == 0) {//加载广告
//                AdUtils.initSplashAd(this, welcome_adsRl, welcome_img, this::goToMainActivity);
//            } else {//不加载广告
//                goToMainActivity();
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//            goToMainActivity();
//        }
        if (AppUtils.isVisiableView()) {//是否显示广告
            AdUtils.initSplashAd(this, welcome_adsRl, welcome_img, this::goToMainActivity);
        } else {
            goToMainActivity();
        }
    }


    /**
     * 跳转到主页面
     */
    private void goToMainActivity() {
        $startActivity(MenuActivity.class);
        welcome_adsRl.removeAllViews();
        this.finish();
    }


    //开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
