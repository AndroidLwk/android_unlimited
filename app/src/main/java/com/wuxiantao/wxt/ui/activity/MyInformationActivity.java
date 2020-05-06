package com.wuxiantao.wxt.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.ViewGroup;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MyInformationRecViewAdapter;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.AlibcLoginBean;
import com.wuxiantao.wxt.bean.PersonalInfoBean;
import com.wuxiantao.wxt.bean.TaoBaoLoginBean;
import com.wuxiantao.wxt.callback.TaobaoLoginCallback;
import com.wuxiantao.wxt.callback.TaobaoLogoutCallback;
import com.wuxiantao.wxt.listener.PermissionListener;
import com.wuxiantao.wxt.mvp.contract.MyInfomationContract;
import com.wuxiantao.wxt.mvp.presenter.InfomationPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.ApprovePopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.TakePhotoPopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.AppUtils;
import com.wuxiantao.wxt.utils.PhotoUtils;
import com.wuxiantao.wxt.utils.TaoBaoUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ABOUT_SUPER;
import static com.wuxiantao.wxt.config.Constant.ALI_CODE;
import static com.wuxiantao.wxt.config.Constant.ALI_NAME;
import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.IS_SETTING_PW;
import static com.wuxiantao.wxt.config.Constant.IS_TAO_BAO_AUTH;
import static com.wuxiantao.wxt.config.Constant.MESSAGE_OUT_LOGIN;
import static com.wuxiantao.wxt.config.Constant.NEW_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.OLD_PASS_WORD;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_SET_LOGIN_PW;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_UPDATE_NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_WECHAT_ID;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_SET_LOGIN_PW;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_UPDATE_NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_WECHAT_ID;
import static com.wuxiantao.wxt.config.Constant.SHARE_CODE;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;
import static com.wuxiantao.wxt.config.Constant.VERCODE_BINDING_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_CHANGE_NUMBER;
import static com.wuxiantao.wxt.config.Constant.VERCODE_TYPE;
import static com.wuxiantao.wxt.config.Constant.WECHAT_NO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyInformationActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 上午8:47
 * Description:${DESCRIPTION} 我的个人信息
 */
@ContentView(R.layout.activity_my_information)
public class MyInformationActivity extends MvpActivity<InfomationPresenter, MyInfomationContract.IInfomationView> implements MyInfomationContract.IInfomationView {

    @ViewInject(R.id.my_information_rv)
    NestRecyclerView mRecyclerView;
    @ViewInject(R.id.my_information_exit)
    StateButton exitButton;

    private PhotoUtils photoUtils;
    private MyInformationRecViewAdapter adapter;
    private Map<String,Object> parameters = new HashMap<>();
    private LoadingDialog loadingDialog;

    @Override
    public void initView() {
        if (!isEmpty(getAppToken())){
            loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
            mPresenter.obtainPersonal(getAppToken());
            parameters.put(TOKEN,getAppToken());
        }
        setOnClikListener(exitButton);
        photoUtils = new PhotoUtils(this);
    }


    //更换头像
    private void changeHeadImg(){
        new TakePhotoPopupWindow.Build(this)
                .setOnItemClickListener(new TakePhotoPopupWindow.Build.OnItemClickListener() {
                    @Override
                    public void onOpenAlbum() {
                        changeHeadImg(false);
                    }

                    @Override
                    public void onTakePhoto() {
                        changeHeadImg(true);
                    }
                }).builder().showPopupWindow();
    }


    //更换头像
    private void changeHeadImg(boolean isCamera){
        if (photoUtils != null){
            //开启裁剪 比例 1:3 宽高 350 350  (默认不裁剪)
            photoUtils.setTailor(3,3,350,350);
            if (isCamera){
                //拍照方式
                photoUtils.startTakeWayByCarema();
            }else {
                //打开手机相册
                photoUtils.startTakeWayByAlbum();
            }
            //监听回调
            photoUtils.setTakePictureCallBackListener(new PhotoUtils.takePictureCallBackListener() {
                @Override
                public void successful(boolean isTailor, File outFile, Uri filePath) {
                    //成功拿到图片,isTailor 是否裁剪？ ,outFile 拿到的文件 ,filePath拿到的URl
                    //Bitmap bm = FileUtils.decodeUriAsBitmap(MyInformationActivity.this,filePath);
//                    String path = FileUtils.decodeUriAsPath(MyInformationActivity.this,filePath);
                    loadingDialog = new LoadingDialog.Build(MyInformationActivity.this).setLoadingText(R.string.headimg_uploading).build();
                    mPresenter.upLoadFile(outFile);
                }
                @Override
                public void failed(int errorCode, List<String> deniedPermissions) {
                    //失败回调
                }
            });
        }
    }

    //退出登陆
    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.my_information_exit){
            showDropOutLoginDialog(getResources().getString(R.string.are_you_quit), (dialog, which) ->
                    mPresenter.onStopApp(getAppToken()));
        }
    }

    //退出成功
    @Override
    public void onStopAppSuccess(String msg) {
        loadingDialog = new LoadingDialog.Build(MyInformationActivity.this)
                .setLoadingText(R.string.exit_loading_).build();
        showLoading();
        exitHandler.sendEmptyMessageDelayed(MESSAGE_OUT_LOGIN,REFRESH_LOAD_MORE_TIME);
        SPSecuredUtils.newInstance(BaseApplication.getInstance()).remove(TOKEN);
    }

    @Override
    public void onStopAppFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @SuppressLint("HandlerLeak")
    private Handler exitHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_OUT_LOGIN){
                loadingDialog.dismissLoadingDialog();
                $startActivity(WeChatLoginActivity.class,true);
                finish();
            }
        }
    };

    private void showDropOutLoginDialog(String title,DialogInterface.OnClickListener listener){
        showDialog(title,listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null){
            return;
        }
        //设置昵称
        if (requestCode == REQUEST_CODE_UPDATE_NICK_NAME && resultCode == RESULT_CODE_UPDATE_NICK_NAME){
            String nickName = data.getStringExtra(NICK_NAME) ;
            if (!isEmpty(nickName)){
                adapter.updataMap(NICK_NAME,nickName);
                saveUserInfo(NICK_NAME,nickName);
            }
        }
        //绑定手机号 更换手机号
        else if (requestCode == REQUEST_CODE_BINDING_NUMBER && resultCode == RESULT_CODE_BINDING_NUMBER
                || requestCode == REQUEST_CODE_CHANGE_NUMBER && resultCode == RESULT_CODE_CHANGE_NUMBER){
            String number = data.getStringExtra(NUMBER);
            if (!isEmpty(number)){
                adapter.updataMap(NUMBER,number);
                saveUserInfo(NUMBER,number);
            }
        }
        //绑定支付宝
        else if (requestCode == REQUEST_CODE_BINDING_ALIPAY && resultCode == RESULT_CODE_BINDING_ALIPAY){
            String account = data.getStringExtra(ALI_CODE);
            String name = data.getStringExtra(ALI_NAME);
            if (!isEmpty(account) && !isEmpty(name)){
                adapter.updataMap(ALI_CODE,account);
                adapter.updataMap(ALI_NAME,name);
            }
        }
        //填写微信号
        else if (requestCode == REQUEST_CODE_WECHAT_ID && resultCode == RESULT_CODE_WECHAT_ID){
            String wechat_id = data.getStringExtra(WECHAT_NO);
            if (!isEmpty(wechat_id)){
                adapter.updataMap(WECHAT_NO,wechat_id);
                saveUserInfo(WECHAT_NO,wechat_id);
            }
        }
        //设置或修改登陆密码
        else if (requestCode == REQUEST_CODE_SET_LOGIN_PW && resultCode == RESULT_CODE_SET_LOGIN_PW){
            String password_old = data.getStringExtra(OLD_PASS_WORD);
            String password_new = data.getStringExtra(NEW_PASS_WORD);
            if (!isEmpty(password_new) || !isEmpty(password_old)){
                adapter.updataMap(IS_SETTING_PW,false);
            }
        }
        else {
            photoUtils.attachToActivityForResult(requestCode,resultCode,data);
        }
    }

    @Override
    protected InfomationPresenter CreatePresenter() {
        return new InfomationPresenter();
    }

    @Override
    public void obtainPersonalSuccess(PersonalInfoBean bean) {
        saveUserInfo(SHARE_CODE,bean.getShare_code());
        initMap(bean);
        initLayout(bean);
        boolean isAuth = bean.getIs_taobao() == 0;
        if (isAuth){
            showApproveWindow();
        }
    }

    //显示淘宝授权对话框
    private void showApproveWindow(){
        new ApprovePopupWindow.Build(this)
                .setPopupWindowWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setPopupWindowAnimStyle(R.style.popupwindow_anim)
                .setOnPopupClickListener(new ApprovePopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void onApprove() {
                        adapter.updataMap(IS_TAO_BAO_AUTH,true);
                        TaoBaoUtils.newInstance().authorAliLogin(new TaobaoLoginCallback() {
                            @Override
                            public void onLoginSuccess(AlibcLoginBean bean) {
                                Map<String,Object> p = new HashMap<>();
                                p.put(TOKEN,getAppToken());
                                p.put("access_token",bean.getTopAccessToken());
                                p.put("open_uid",bean.getOpenId());
                                p.put("avatarurl",bean.getAvatarUrl());
                                p.put("opensid",bean.getOpenSid());
                                p.put("userid",bean.getUserid());
                                p.put("nick",bean.getNick());
                                //1.android 2.ios
                                p.put("device",1);
                                mPresenter.taoBaoLogin(p);
                            }

                            @Override
                            public void onLoginFailure(int errorCode, String errorMsg) {
                                showOnlyConfirmDialog(errorMsg, (dialog, which) ->
                                        adapter.updataMap(IS_TAO_BAO_AUTH,false));
                            }
                        });

                    }

                    @Override
                    public void onClose() {
                        adapter.updataMap(IS_TAO_BAO_AUTH,false);
                    }
                })
                .setOnKeyBackListener(() -> adapter.updataMap(IS_TAO_BAO_AUTH,false))
                .builder().showPopupWindow(Gravity.CENTER);
    }

    private void initLayout(PersonalInfoBean bean){
        List<String> list = Arrays.asList(getResources().getStringArray(!isEmpty(bean.getPhone())
                ? R.array.my_information_title1 : R.array.my_information_title2));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,0);
        adapter = new MyInformationRecViewAdapter(this,list,initMap(bean));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClikListener(new MyInformationRecViewAdapter.OnItemClikListener() {
            //更换头像
            @Override
            public void onChangeHeadImg() {
                requestPermission(new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        new PermissionListener() {
                            @Override
                            public void permissionFailing(String[] permissions) {

                            }

                            @Override
                            public void permissinSucceed() {
                                changeHeadImg();
                            }
                        });

            }

            //显示userid
            @Override
            public void onShowUserId(String userId) {

            }

            //修改昵称
            @Override
            public void onChangeNickName(String nickName) {
                Bundle bundle = new Bundle();
                bundle.putString(NICK_NAME,nickName);
                $startActivityForResult(SettingNickNameActivity.class,bundle,REQUEST_CODE_UPDATE_NICK_NAME);
            }
            //绑定手机号
            @Override
            public void onBindNumber() {
                Bundle bundle = new Bundle();
                bundle.putInt(VERCODE_TYPE,VERCODE_BINDING_NUMBER);
                $startActivityForResult(BinddingNumberActivity.class,bundle,REQUEST_CODE_BINDING_NUMBER);
            }
            //更换手机号
            @Override
            public void onChangeNumber(String number) {
                Bundle bundle = new Bundle();
                bundle.putInt(VERCODE_TYPE,VERCODE_CHANGE_NUMBER);
                bundle.putString(NUMBER,number);
                $startActivityForResult(BinddingNumberActivity.class,bundle,REQUEST_CODE_CHANGE_NUMBER);
            }

            //绑定支付宝
            @Override
            public void onBindAlipay() {
                $startActivityForResult(BinddingAlipayActivity.class,REQUEST_CODE_BINDING_ALIPAY);
            }

            //查看我的支付宝信息支付宝
            @Override
            public void onChangeAlipay(String alicode,String aliname) {
                Bundle bundle = new Bundle();
                bundle.putString(ALI_CODE,alicode);
                bundle.putString(ALI_NAME,aliname);
                $startActivityForResult(MyAlipayInfoActivity.class,bundle,REQUEST_CODE_BINDING_ALIPAY);
            }

            //填写微信号
            @Override
            public void onWriteWeChatId(String wechat_id) {
                Bundle bundle = new Bundle();
                bundle.putString(WECHAT_NO,wechat_id);
                $startActivityForResult(WriteWeChatIdActivity.class,bundle,REQUEST_CODE_WECHAT_ID);
            }

            //收货地址管理
            @Override
            public void onAddressManagement() {
                $startActivity(AddressManagementActivity.class);
            }

            //设置或修改登陆密码
            @Override
            public void onSettingPassWord(boolean isSettingPassWord,String number) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(IS_SETTING_PW,isSettingPassWord);
                bundle.putString(NUMBER,number);
                $startActivityForResult(SettingPassWordActivity.class,bundle,REQUEST_CODE_SET_LOGIN_PW);
            }

            //开启或关闭授权
            @Override
            public void onAuthorization(boolean isAllow) {
                if (isAllow){
                    showApproveWindow();
                }else {
                    showDialog(getString(R.string.cancel_auth), getString(R.string.cancel_msg), (dialog, which) -> {
                        isModiyfTaobao = true;
                        parameters.put(IS_TAO_BAO_AUTH, 0);
                        mPresenter.modifyPersonal(parameters);
                    }, (dialog, which) -> adapter.updataMap(IS_TAO_BAO_AUTH,true));
                }
            }
            //关于
            @Override
            public void onAboutSuperman(String verName) {
                Bundle bundle = new Bundle();
                bundle.putString(ABOUT_SUPER,verName);
                $startActivity(SettingActivity.class,bundle);
            }
        });
    }

    private boolean isModiyfTaobao;

    private Map<String,Object>  initMap(PersonalInfoBean bean){
        Map<String,Object> map = new HashMap<>();
        String headimg = bean.getHeadimg();
        String nickname = bean.getNickname();
        String number = bean.getPhone();

        saveUserInfo(APP_USER_ID,bean.getId());
        saveUserInfo(USER_HEAD_IMG,headimg);
        saveUserInfo(NICK_NAME,nickname);
        saveUserInfo(WECHAT_NO,bean.getWechat());
        saveUserInfo(NUMBER,number);
        saveUserInfo(IS_TAO_BAO_AUTH,bean.getIs_taobao() == 1);

        map.put(USER_HEAD_IMG,headimg);
        map.put(APP_USER_ID,String.valueOf(bean.getId()));
        map.put(NICK_NAME,nickname);
        map.put(NUMBER,number);
        if (!isEmpty(number)){
            map.put(IS_SETTING_PW,bean.getPassword() == 0);
        }
        map.put(ALI_CODE,bean.getAlicode());
        map.put(ALI_NAME,bean.getAliname());
        map.put(WECHAT_NO,bean.getWechat());
        map.put(IS_TAO_BAO_AUTH,bean.getIs_taobao() == 1);
        map.put(ABOUT_SUPER,String.valueOf(AppUtils.getVersionName()));
        return map;
    }

    @Override
    public void obtainPersonalFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void taoBaoLoginSuccess(TaoBaoLoginBean bean) {
        showOnlyConfirmDialog(getString(R.string.taobao_auth_success), (dialog, which) -> {
            saveUserInfo(IS_TAO_BAO_AUTH,true);
            mPresenter.obtainPersonal(getAppToken());
        });
    }

    @Override
    public void taoBaoLoginFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> adapter.updataMap(IS_TAO_BAO_AUTH,false));
    }

    @Override
    public void upLoadFileSuccess(String url) {
        adapter.updataMap(USER_HEAD_IMG,url);
        saveUserInfo(USER_HEAD_IMG,url);
        parameters.put("file",url);
        mPresenter.modifyPersonal(parameters);
    }

    @Override
    public void upLoadFileFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.person_info)).build();
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
    public void modifySuccess(String msg) {
        if (isModiyfTaobao){
            //淘宝退出登陆
            TaoBaoUtils.newInstance().authorAliLoginOut(new TaobaoLogoutCallback() {
                @Override
                public void onSuccess() {
                    saveUserInfo(IS_TAO_BAO_AUTH,false);
                    isModiyfTaobao = false;
                    adapter.updataMap(IS_TAO_BAO_AUTH,false);
                    mPresenter.obtainPersonal(getAppToken());
                }

                @Override
                public void onFailure(int code, String msg) {
                       showOnlyConfirmDialog(msg);
                }
            });
        }else {
            ToastUtils.success(msg);
        }
    }

    @Override
    public void modifyFailure(String failure) {
        ToastUtils.error(failure);
    }
}
