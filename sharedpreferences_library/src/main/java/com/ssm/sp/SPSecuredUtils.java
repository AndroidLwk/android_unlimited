package com.ssm.sp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.ssm.aescbc.Base64Decoder;
import com.ssm.aescbc.Base64Encoder;
import com.ssm.androidkeystoresign.AndroidKeyStoreRSAUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SPSecuredUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-5 下午1:24
 * Description:${DESCRIPTION}
 */
public final class SPSecuredUtils {

    //https://gitee.com/huangxiaoguo/androidGeZhongJiaMiZongJie/tree/master/app/src/main/java/tsou/com/encryption

    //AES128加密解密
    //AESECB加密解密
    //AESCBC加密解密
    //非对称RSA加密解密
    //对称DES加密解密
    //MD5加密
    //Base64加密解密
    //异或加密解密
    //Android sharedpreferences 数据进行加密
    //Android AndroidKeyStore 对数据进行签名和验证
    //Android AndroidKeyStore 非对称RSA加密解密
    //google  tink加密解密
    private static volatile SPSecuredUtils instance;

    //文件名
    private static final String FILE_NAME = "sp_secured";
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;
    //是否使用加密
    private boolean isEncryption = false;

    @SuppressLint("CommitPrefEdits")
    private SPSecuredUtils(Application app) {
        //name 文件名
        //mode 模式
        //MODE_PRIVATE, 默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下
        //写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中 可以使用Activity.MODE_APPEND
        //MODE_WORLD_READABLE 表示当前文件可以被其他应用读取，
        //MODE_WORLD_WRITEABLE,表示当前文件可以被其他应用写入；
        //如果希望文件被其他应用读和写，可以传入:MODE_WORLD_READABLE+Activity.MODE_WORLD_WRITEABLE
        //MODE_APPEND 该模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
        mSharedPreferences = app.getApplicationContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }


    public static SPSecuredUtils newInstance(Application app) {
        if (instance == null) {
            synchronized (SPSecuredUtils.class) {
                if (instance == null) {
                    instance = new SPSecuredUtils(app);
                }
            }
        }
        return instance;
    }

    //保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
    public boolean put(String key, Object value) {
        RSAPublicKey publicKey = (RSAPublicKey) AndroidKeyStoreRSAUtils.getLocalPublicKey();
        if (isEmpty(key) || value == null || publicKey == null) {
            return false;
        }
        boolean result = false;
        try {
            String type = value.getClass().getSimpleName();
            switch (type) {
                case "String":
                    if (isEncryption) {
                        //使用公钥加密
                        byte[] encryptBytes = AndroidKeyStoreRSAUtils.encryptByPublicKey(((String) value).getBytes(),
                                publicKey.getEncoded());
                        editor.putString(key, Base64Encoder.encode(encryptBytes));
                    } else {
                        editor.putString(key, (String) value);
                    }
                    break;
                case "Integer":
                    if (isEncryption) {
                        put(key, Integer.toString((Integer) value));
                    } else {
                        editor.putInt(key, (Integer) value);
                    }
                    break;
                case "Boolean":
                    if (isEncryption) {
                        put(key, Boolean.toString((Boolean) value));
                    } else {
                        editor.putBoolean(key, (Boolean) value);
                    }
                    break;
                case "Long":
                    if (isEncryption) {
                        put(key, Long.toString((Long) value));
                    } else {
                        editor.putLong(key, (Long) value);
                    }
                    break;
                default:
                    if (isEncryption) {
                        put(key, value.toString());
                    } else {
                        editor.putString(key, (String) value);
                    }
                    break;
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferencesCompat.apply(editor);
        return result;
    }


    //得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
    public Object get(String key, Object value) {
        if (isEmpty(key) || value == null) {
            return null;
        }
        String type = value.getClass().getSimpleName();
        switch (type) {
            case "String":
                String strStr = mSharedPreferences.getString(key, (String) value);
                Log.e("key", key);
                if (isEncryption && !isEmpty(strStr) && !strStr.equals(value)) {
                     strStr = new String(getDecryptBytes(strStr));
                }
                return strStr;
            case "Integer":
                if (isEncryption) {
                    String intStr = mSharedPreferences.getString(key, Integer.toString((Integer) value));
                    if (!isEmpty(intStr)) {
                        return Integer.valueOf(new String(getDecryptBytes(intStr)));
                    }
                }
                return mSharedPreferences.getInt(key, (Integer) value);
            case "Boolean":
                if (isEncryption) {
                    String booStr = mSharedPreferences.getString(key, Boolean.toString((Boolean) value));
                    if (!isEmpty(booStr)) {
                        return Boolean.valueOf(new String(getDecryptBytes(booStr)));
                    }
                }
                return mSharedPreferences.getBoolean(key, (Boolean) value);
            case "Float":
                if (isEncryption) {
                    String fStr = mSharedPreferences.getString(key, Float.toString((Float) value));
                    if (!isEmpty(fStr)) {
                        return Float.valueOf(new String(getDecryptBytes(fStr)));
                    }
                }
                return mSharedPreferences.getFloat(key, (Float) value);
            case "Long":
                if (isEncryption) {
                    String lStr = mSharedPreferences.getString(key, Long.toString((Long) value));
                    if (!isEmpty(lStr)) {
                        return Long.valueOf(new String(getDecryptBytes(lStr)));
                    }
                }
                return mSharedPreferences.getLong(key, (Long) value);
            case "Double":
                if (isEncryption) {
                    String dStr = mSharedPreferences.getString(key, Double.toString((Double) value));
                    if (!isEmpty(dStr)) {
                        if (!dStr.equals(Double.toString((Double) value))) {
                            return Double.valueOf(new String(getDecryptBytes(dStr)));
                        }
                    }
                }
            default:
                return mSharedPreferences.getString(key, value.toString());
        }
    }


    private byte[] getDecryptBytes(String s) {
        try {
            //使用私钥解密
            byte[] b = Base64Decoder.decodeToBytes(s);
            Log.e("s", s);
            Log.e("解密前", new String(b));
            byte[] c = AndroidKeyStoreRSAUtils.decryptByPrivateKey(b);
            Log.e("解密后", new String(c));
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    /**
     * 将对象储存到sharepreference
     *
     * @param key
     * @param device
     * @param <T>
     */
    public static <T> boolean saveDeviceData(String key, T device, RSAPublicKey publicKey) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {   //Device为自定义类
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(device);
            // 将字节流编码成base64的字符串
            String oAuth_Base64 = new String(Base64.encode
                    (baos.toByteArray(), Base64.DEFAULT));
            byte[] encryptBytes = AndroidKeyStoreRSAUtils.encryptByPublicKey(oAuth_Base64.getBytes(),
                    publicKey.getEncoded());
            editor.putString(key, Base64Encoder.encode(encryptBytes)).apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将对象从shareprerence中取出来
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getDeviceData(String key) {
        try {
            T device = null;
            String productBase64 = mSharedPreferences.getString(key, null);
            if (productBase64 == null) {
                return null;
            }
            byte[] decryptBytes = AndroidKeyStoreRSAUtils.decryptByPrivateKey(
                    Base64Decoder.decodeToBytes(productBase64));
            // 读取字节
            byte[] base64 = Base64.decode(new String(decryptBytes).getBytes(), Base64.DEFAULT);
            // 封装到字节流
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            // 再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            // 读取对象
            device = (T) bis.readObject();
            return device;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }

    //移除某个key值已经对应的值
    public void remove(String key) {
        if (mSharedPreferences.contains(key)) {
            editor.remove(key);
            SharedPreferencesCompat.apply(editor);
        }
    }

    //清除所有数据
    public static void clear() {
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    //查询某个key是否已经存在
    public static boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }

    //返回所有的键值对
    public static Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    //创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        //反射查找apply的方法
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        //如果找到则使用apply执行，否则使用commit
        private static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

}
