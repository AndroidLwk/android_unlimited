package com.wuxiantao.wxt.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.ui.activity.PreviewImgActivity;
import com.wuxiantao.wxt.ui.activity.WeChatLoginActivity;

import java.util.ArrayList;

import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_LIST;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_POSITION;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.VIP_STATUS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseJumpFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午6:27
 * Description:${DESCRIPTION}
 */
public abstract class BaseJumpFragment<P extends MvpPresenter,V extends MvpView> extends BaseShowDialogFragment<P,V> {

    protected void saveUserInfo(String key, Object value){
        put(key,value);
    }

    protected String getUserInfo(String key){
        return getSPString(key);
    }


    protected String getAppToken(){
        return getUserInfo(TOKEN);
    }

    protected int getLocalUserId(){
        return getSPInt(APP_USER_ID);
    }


    protected int getUserStatus(String key) {
        return getSPInt(key);
    }

    protected void saveUserStatus(String key, int status) {
        put(key,status);
    }

    protected void saveUserInfo(String key, boolean b) {
        put(key,b);
    }


    protected boolean isUserAuthorization(String key) {
        return getSPBoolean(key);
    }


    protected boolean isVip(){
        int status = getUserStatus(VIP_STATUS);
        return  status != 0 && status != -1;
    }

    protected void previewImg(ArrayList<String> imgList, int position){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PREVIEW_IMG_LIST,imgList);
        bundle.putInt(PREVIEW_IMG_POSITION,position);
        $startActivity(PreviewImgActivity.class,bundle);
    }


    protected String getFragmentArguments(String key){
        if (!isEmpty(key)){
            Bundle bundle = getArguments();
            if (bundle != null){
                return bundle.getString(key);
            }
        }
        return "1";
    }

    private void $startActivity(Intent intent){
//        BaseApplication.getInstance().addActivity(mActivity);
        if (isEmpty(getAppToken())){
              showLoginDialog();
        }else {
            if (intent != null){
                startActivity(intent);
            }
        }
    }

    /**
     * 界面跳转
     * @param intent
     */
    private void $startActivity(Intent intent,boolean isNotCheckToken){
//        BaseApplication.getInstance().addActivity(mActivity);
        if (isNotCheckToken){
            if (intent != null){
                startActivity(intent);
            }
        }else {
            if (isEmpty(getAppToken())){
                showLoginDialog();
            }
        }
    }

    @Override
    protected void login() {
        $startActivity(WeChatLoginActivity.class,true);
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla) {
        Intent  intent = new Intent(mActivity,cla);
        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent);
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla, Bundle bundle, int requestCode) {
        if (bundle == null) {
            $startActivityForResult(cla, requestCode);
        } else {
            Intent intent = new Intent(mActivity, cla);
            intent.putExtras(bundle);
            $startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla, int requestCode) {
        Intent intent = new Intent(mActivity, cla);
        $startActivityForResult(intent, requestCode);
    }


    private void $startActivityForResult(Intent intent, int requestCode) {
//        BaseApplication.getInstance().addActivity(this);
        if (!isEmpty(getAppToken())) {
            startActivityForResult(intent, requestCode);
        } else {
            loginOut();
        }
    }

    private void $startActivityForResult(Intent intent, int requestCode, boolean isNotCheckToken) {
        if (isNotCheckToken) {
            startActivityForResult(intent, requestCode);
        } else {
            if (!isEmpty(getAppToken())) {
                startActivityForResult(intent, requestCode);
            } else {
                loginOut();
            }
        }
    }

    protected void loginOut(){}

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla,boolean isNotCheckToken) {
        Intent  intent = new Intent(mActivity,cla);
        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent,isNotCheckToken);
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla,Bundle bundle) {
        if (bundle == null){
            $startActivity(cla);
        }else {
            Intent  intent = new Intent(mActivity,cla);
            intent.putExtras(bundle);
            mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(intent);
        }
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla,Bundle bundle,boolean isNotCheckToken) {
        if (bundle == null){
            $startActivity(cla,isNotCheckToken);
        }else {
            Intent  intent = new Intent(mActivity,cla);
            intent.putExtras(bundle);
            mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(intent,isNotCheckToken);
        }
    }

    /**
     *  界面跳转
     * @param cla
     * @param enterAnim 开始动画
     * @param exitAnim 结束动画
     */
    protected void $startActivity(Class<?> cla,int enterAnim, int exitAnim) {
        Intent  intent = new Intent(mActivity,cla);
        mActivity.overridePendingTransition(enterAnim,exitAnim);
        $startActivity(intent);
    }

    /**
     *  界面跳转
     * @param cla
     * @param enterAnim 开始动画
     * @param exitAnim 结束动画
     */
    protected void $startActivity(Class<?> cla,int enterAnim, int exitAnim,boolean isNotCheckToken) {
        Intent  intent = new Intent(mActivity,cla);
        mActivity.overridePendingTransition(enterAnim,exitAnim);
        $startActivity(intent,isNotCheckToken);
    }


    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,int value) {
        Intent intent = new Intent(mActivity,cla);
        intent.putExtras(addIntegerBundle(key,value));

        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,int value,boolean isNotCheckToken) {
        Intent intent = new Intent(mActivity,cla);
        intent.putExtras(addIntegerBundle(key,value));
        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent,isNotCheckToken);
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value) {
        Intent intent = new Intent(mActivity,cla);
        intent.putExtras(addIntegerBundle(key,value));
        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent);
    }


    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value,boolean isNotCheckToken) {
        Intent intent = new Intent(mActivity,cla);
        intent.putExtras(addIntegerBundle(key,value));
        mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        $startActivity(intent,isNotCheckToken);
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
            Intent intent = new Intent(mActivity,cla);
            intent.putExtras(bundle);
            mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(intent);
        }
    }

    /**
     * 界面跳转
     * @param cla
     * @param str
     */
    protected void $startActivity(Class<?> cla,boolean isNotCheckToken,String ...str) {
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
            Intent intent = new Intent(mActivity,cla);
            intent.putExtras(bundle);
            mActivity.overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(intent,isNotCheckToken);
        }
    }


    private   Bundle  addIntegerBundle(String key,Object value){
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
}
