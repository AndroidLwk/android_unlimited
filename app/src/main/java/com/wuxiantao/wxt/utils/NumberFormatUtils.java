/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NumberFormatUtils
 * Author: Administrator
 * Date: 2018/8/19 0019 16:35
 * Description: 手机号码格式验证工具类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.wuxiantao.wxt.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.wuxiantao.wxt.app.BaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Copyright (C), 2018-2018, 都可信有限公司
 *  Date: 2018/8/19 0019 16:35 8-19
 *  Description: 手机号码格式验证工具类
 *  Author: Administrator Shiming-Shi 
 */
public class NumberFormatUtils {

    // 号码
    private final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    private final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    //联系人提供者的uri
    private static Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    /**
     * 验证手机格式是否正确
     * @param phoneNumber
     * @return
     */
    public static  boolean  isVerificationNo(String phoneNumber){
        if (isMatchLength(phoneNumber,11) && isPhoneNo(phoneNumber)) {
            return  true;
        }
        return  false;
    }


    /**
     *  验证手机格式
     * @param phoneNumber
     * @return
     */
    private static boolean isPhoneNo(String phoneNumber){
        String phone="[1][345789]\\d{9}";
        if (TextUtils.isEmpty(phoneNumber)){
            return  false;
        }else {
            return phoneNumber.matches(phone);
        }
    }


    /**
     * 数字转汉字
     * @param number 数字
     * @return
     */
    public static String numberToChar(int number){
        //数字对应的汉字
        String[] num = {"零","一","二","三","四","五","六","七","八","九"};
        //单位
        String[] unit = {"","十","百","千","万","十","百","千","亿","十","百","千","万亿"};
        //将输入数字转换为字符串
        String result = String.valueOf(number);
        //将该字符串分割为数组存放
        char[] ch = result.toCharArray();
        //结果 字符串
        StringBuilder str = new StringBuilder();
        int length = ch.length;
        for (int i = 0; i < length; i++) {
            int c = (int)ch[i]-48;
            if(c != 0) {
                str.append(num[c]).append(unit[length - i - 1]);
            } else {
                str.append(num[c]);
            }
        }
        return str.toString();
    }


    /**
     *  判断手机位数
     * @param str
     * @param len
     * @return
     */
    private   static boolean isMatchLength(String str,int len) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == len;
        }
    }


    /**
     * 截取手机号
     * @param phoneNumber
     * @return
     */
    public static String  phoneNumberSub(String phoneNumber){
        if (isVerificationNo(phoneNumber)){
            StringBuffer  sb = new StringBuffer();
            for (int i = 0;i < phoneNumber.length();i++){
                char c = phoneNumber.charAt(i);
                if (i >= 3 && i <= 6){
                    sb.append("*");
                }else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * String字符串中提取数字
     *
     * @param data 数据源
     * @return
     */
    public static int StrFindInteger(String data) {
        if (isEmpty(data)) {
            return -1;
        }
        try {
            Pattern mPattern = Pattern.compile("\\d+");
            Matcher mMatcher = mPattern.matcher(data);
            boolean isFind = mMatcher.find();
            String group = mMatcher.group();
            return isFind ? Integer.parseInt(group) : -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }

    //获取通讯录所有号码
    public static List<PhoneNumberInfo> getAllPhoneNumber(){
        List<PhoneNumberInfo> contacts = new ArrayList<>();
        ContentResolver resolver = BaseApplication.getInstance().getContentResolver();
        Cursor cursor = resolver.query(phoneUri,new String[]{NUM,NAME},null,null,null);
        while (cursor.moveToNext()){
            PhoneNumberInfo info = new PhoneNumberInfo(cursor.getString(cursor.getColumnIndex(NAME)),cursor.getString(cursor.getColumnIndex(NUM)));
            contacts.add(info);
        }
        cursor.close();
        return contacts;
    }


    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        return strEmail != null && TextUtils.isEmpty(strEmail) && strEmail.matches(strPattern);
    }
}