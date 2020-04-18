package com.wuxiantao.wxt.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;

import com.kf5.sdk.im.api.IMAPI;
import com.kf5.sdk.im.db.IMSQLManager;
import com.kf5.sdk.system.entity.Field;
import com.kf5.sdk.system.entity.ParamsKey;
import com.kf5.sdk.system.init.UserInfoAPI;
import com.kf5.sdk.system.internet.HttpRequestCallBack;
import com.kf5.sdk.system.utils.SPUtils;
import com.kf5.sdk.system.utils.SafeJson;
import com.wuxiantao.wxt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.YUN_KF_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.YUN_KF_APP_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IMUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-10-19 下午12:50
 * Description:${DESCRIPTION}
 */
public class IMUtils {

    private static void loginUser(Map<String,String> map){
        UserInfoAPI.getInstance().loginUser(map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    final JSONObject jsonObj = SafeJson.parseObj(result);
                    int code = SafeJson.safeInt(jsonObj,"error");
                    if (code == 0){
                        //由于先调用了createUser接口，当createUser接口的code不等于0时，
                        // 且传入参数合法时，我们默认用户信息存在，这时我们就调用登陆接口。
                        //当loginUser接口返回值等于0时，我们就认为用户登陆成功，这里我们就
                        // 可以缓存必要的信息，比如说userToken，userId；
                        //如果我们需要用到推送功能，我们也可以在保存userToken之后调用saveToken接口。
                        //SPUtils.saveUserToken(userToken);
                        //SPUtils.saveUserId(id);
                        final JSONObject dataObj = SafeJson.safeObject(jsonObj, Field.DATA);
                        final JSONObject userObj = SafeJson.safeObject(dataObj,Field.USER);
                        if (userObj != null){
                            String userToken = userObj.getString(Field.USERTOKEN);
                            int id = userObj.getInt(Field.ID);
                            SPUtils.saveUserToken(userToken);
                            SPUtils.saveUserId(id);
                        }
                    }else {
                        //那么问题来了，当先调用的是createUser接口，如果用户以存在，这时走到了
                        // loginUser接口，当loginUser接口的返回的code也不等于0呢？
                        //如果按照这种逻辑调用，且code也不等于0时，那么就是属于参数格式不正确，
                        // 或者参数遗漏造成的，参数规则请往下看。
                        if (jsonObj != null){
                            String message = jsonObj.getString("message");
                            LogUtils.loge( "登录失败message:" + message);
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String result) {
                LogUtils.loge( "登录失败" + result);
            }
        });
    }


    public static void createUser(int id, Activity act){
        String email = act.getResources().getString(R.string.user_mall, id);
        WeakReference<Activity> reference = new WeakReference<>(act);
        Map<String,String> map = makeJsonField(email,reference.get());
        SPUtils.saveAppID(YUN_KF_APP_ID);
        SPUtils.saveHelpAddress(YUN_KF_ADDRESS);
        UserInfoAPI.getInstance().createUser(map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                final JSONObject jsonObject = SafeJson.parseObj(result);
                int resultCode = SafeJson.safeInt(jsonObject, "error");
                if (resultCode == 0) {
                    final JSONObject dataObj = SafeJson.safeObject(jsonObject, Field.DATA);
                    JSONObject userObj = SafeJson.safeObject(dataObj, Field.USER);
                    if (userObj != null) {
                        try {
                            String userToken = userObj.getString(Field.USERTOKEN);
                            int id = userObj.getInt(Field.ID);
                            SPUtils.saveUserToken(userToken);
                            SPUtils.saveUserId(id);
                            saveDeviceToken(map);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    loginUser(map);
                }
            }

            @Override
            public void onFailure(String result) {
                LogUtils.loge( "登录失败" + result);
            }
        });
    }

    private static Map<String,String>  makeJsonField(String email,Activity act){
        Map<String,String> map = new HashMap<>();
        //自定义字段
        JSONArray jsonArray = new JSONArray();
        try {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put(ParamsKey.EMAIL, "email");
            map.put(Field.EMAIL,email);
            jsonArray.put(jsonObject);
        } catch (com.alibaba.fastjson.JSONException e) {
            e.printStackTrace();
        }
        map.put(ParamsKey.USER_FIELDS, jsonArray.toString());
        SPUtils.saveUserAgent(IMUtils.getAgent(new SoftReference<>(act)));
        return map;
    }


    public static void delteDeviceToken(Map<String,String> map){
        UserInfoAPI.getInstance().deleteDeviceToken(map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("删除设备Token成功" + result);
            }

            @Override
            public void onFailure(String result) {
                LogUtils.loge("删除设备Token失败" + result);
            }
        });
    }


    private static void saveDeviceToken(Map<String,String> map){
        map.put(ParamsKey.DEVICE_TOKEN, "123456");
        UserInfoAPI.getInstance().saveDeviceToken(map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("保存设备Token成功" + result);
            }

            @Override
            public void onFailure(String result) {
                LogUtils.loge("保存设备Token失败" + result);
            }
        });
    }

    //未读消息数
    public static void getUnReadMessageCount(Activity act,HttpRequestCallBack callBack){
        Map<String,String> map = new HashMap<>();
        map.put(Field.MESSAGE_ID, IMSQLManager.getLastMessageId(act) + "");
        map.put(Field.USERTOKEN,SPUtils.getUserToken());
        IMAPI.getInstance().getUnReadMessageCount(map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                if (callBack != null){
                    callBack.onSuccess(result);
                }
            }

            @Override
            public void onFailure(String result) {
                if (callBack != null){
                    callBack.onFailure(result);
                }
            }
        });
    }


    private static String getAgent(SoftReference<Context> softReference) {
        String agent = "";
        try {
            String ua = System.getProperty("http.agent");
            String packageName = softReference.get().getPackageName();
            PackageInfo info = softReference.get().getPackageManager().getPackageInfo(packageName, 0);
            agent = ua + ", " + packageName + "/" + info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agent;
    }
}
