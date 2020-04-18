package com.ssm.aesecb;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AESUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-5 下午1:17
 * Description:${DESCRIPTION} AES加密解密工具
 */
public class AESUtils {

    private static final String AES = "AES/ECB/NoPadding";

    /**
     * AES加密
     *
     * @param data
     *            将要加密的内容
     * @param key
     *            密钥
     * @return 已经加密的内容
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (key == null){
            key = AES.getBytes();
        }
        //不足16字节，补齐内容为差值
        int len = 16 - data.length % 16;
        for (int i = 0; i < len; i++) {
            byte[] bytes = { (byte) len };
            data = ArrayUtils.concat(data, bytes);
        }
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

    /**
     * AES解密
     *
     * @param data
     *            将要解密的内容
     * @param key
     *            密钥
     * @return 已经解密的内容
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        if (key == null){
            key = AES.getBytes();
        }
        data = ArrayUtils.noPadding(data, -1);
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decryptData = cipher.doFinal(data);
            int len = 2 + ByteUtils.byteToInt(decryptData[4]) + 3;
            return ArrayUtils.noPadding(decryptData, len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

}
