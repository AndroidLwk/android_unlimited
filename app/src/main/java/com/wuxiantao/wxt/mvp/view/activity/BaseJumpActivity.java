package com.wuxiantao.wxt.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;
import com.wuxiantao.wxt.ui.activity.PreviewImgActivity;

import java.util.ArrayList;

import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_LIST;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_POSITION;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.VIP_STATUS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseJumpActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 下午6:43
 * Description:${DESCRIPTION}
 */
public abstract class BaseJumpActivity<P extends MvpPresenter, V extends MvpView> extends BaseMvpActivity<P, V> {

    protected boolean isVip() {
        int status = getUserStatus(VIP_STATUS);
        return status != 0 && status != -1;
    }

    protected void saveUserInfo(String key, Object value) {
        put(key,value);
    }

    protected String getUserInfo(String key) {
        return getSPString(key);
    }

    protected long getUserInfoLong(String key) {
        return getSPLong(key);
    }

    protected int getUserStatus(String key) {
        return getSPInt(key);
    }

    protected void saveUserStatus(String key, int status) {
        put(key,status);
    }

    protected boolean isUserAuthorization(String key) {
        return  getSPBoolean(key);
    }

    protected String getAppToken() {
        return getSPString(TOKEN);
    }

    protected int getLocalUserId() {
        return getSPInt(APP_USER_ID);
    }

    protected void previewImg(ArrayList<String> imgList, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PREVIEW_IMG_LIST, imgList);
        bundle.putInt(PREVIEW_IMG_POSITION, position);
        $startActivity(PreviewImgActivity.class, bundle);
    }


    private Bundle addIntegerBundle(String key, Object value) {
        Bundle bundle = new Bundle();
        if (value instanceof Long) {
            bundle.putLong(key, (Long) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof Double) {
            bundle.putDouble(key, (Double) value);
        }
        return bundle;
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivity(Class<?> cla, boolean isNotCheckToken) {
        Intent intent = new Intent(this, cla);
        overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
        $startActivity(intent, isNotCheckToken);
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivity(Class<?> cla) {
        Intent intent = new Intent(this, cla);
        overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
        $startActivity(intent);
    }


    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivity(Class<?> cla, Bundle bundle, boolean isNotCheckToken) {
        if (bundle != null) {
            Intent intent = new Intent(this, cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
            $startActivity(intent, isNotCheckToken);
        } else {
            $startActivity(cla);
        }
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivity(Class<?> cla, Bundle bundle) {
        if (bundle != null) {
            Intent intent = new Intent(this, cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
            $startActivity(intent);
        } else {
            $startActivity(cla);
        }
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla, String key, Object value) {
        Intent intent = new Intent(this, cla);
        intent.putExtras(addIntegerBundle(key, value));
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
        $startActivity(intent);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla, String key, Object value, boolean isNotCheckToken) {
        Intent intent = new Intent(this, cla);
        intent.putExtras(addIntegerBundle(key, value));
        overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
        $startActivity(intent, isNotCheckToken);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla, String key, Object value, int enterAnim, int exitAnim, boolean isNotCheckToken) {
        Intent intent = new Intent(this, cla);
        intent.putExtras(addIntegerBundle(key, value));
//        BaseApplication.getInstance().addActivity(this);
        overridePendingTransition(enterAnim, exitAnim);
        $startActivity(intent, isNotCheckToken);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla, String key, Object value, int enterAnim, int exitAnim) {
        Intent intent = new Intent(this, cla);
        intent.putExtras(addIntegerBundle(key, value));
        overridePendingTransition(enterAnim, exitAnim);
        $startActivity(intent);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param enterAnim
     * @param exitAnim
     */
    protected void $startActivity(Class<?> cla, int enterAnim, int exitAnim, boolean isNotCheckToken) {
        Intent intent = new Intent(this, cla);
        overridePendingTransition(enterAnim, exitAnim);
        $startActivity(intent, isNotCheckToken);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param enterAnim
     * @param exitAnim
     */
    protected void $startActivity(Class<?> cla, int enterAnim, int exitAnim) {
        Intent intent = new Intent(this, cla);
        overridePendingTransition(enterAnim, exitAnim);
        $startActivity(intent);
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param str
     */
    protected void $startActivity(Class<?> cla, boolean isNotCheckToken, String... str) {
        if (str.length <= 0) {
            $startActivity(cla, isNotCheckToken);
        } else {
            Bundle bundle = new Bundle();
            String key = "";
            String value = "";
            for (int i = 0; i < str.length; i++) {
                if (i % 2 == 0) {
                    key = str[i];
                } else {
                    value = str[i];
                }
                bundle.putString(key, value);
            }
            Intent intent = new Intent(mContext, cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
            $startActivity(cla, bundle, isNotCheckToken);
        }
    }

    /**
     * 界面跳转
     *
     * @param cla
     * @param str
     */
    protected void $startActivity(Class<?> cla, String... str) {
        if (str.length <= 0) {
            $startActivity(cla);
        } else {
            Bundle bundle = new Bundle();
            String key = "";
            String value = "";
            for (int i = 0; i < str.length; i++) {
                if (i % 2 == 0) {
                    key = str[i];
                } else {
                    value = str[i];
                }
                bundle.putString(key, value);
            }
            Intent intent = new Intent(mContext, cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
            $startActivity(cla, bundle);
        }
    }

    /**
     * 界面跳转
     *
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla, int requestCode) {
        Intent intent = new Intent(this, cla);
        overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
        $startActivityForResult(intent, requestCode);
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
            Intent intent = new Intent(this, cla);
            intent.putExtras(bundle);
            overridePendingTransition(R.anim.translate_into, R.anim.translate_into);
            $startActivityForResult(intent, requestCode);
        }
    }


    private void $startActivity(Intent intent, boolean isNotCheckToken) {
//        BaseApplication.getInstance().addActivity(this);
        if (isNotCheckToken) {
            startActivity(intent);
        } else {
            if (!isEmpty(getAppToken())) {
                startActivity(intent);
            } else {
                loginOut();
            }
        }
    }


    private void $startActivity(Intent intent) {
//        BaseApplication.getInstance().addActivity(this);
        if (!isEmpty(getAppToken())) {
            startActivity(intent);
        } else {
            loginOut();
        }
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
}
