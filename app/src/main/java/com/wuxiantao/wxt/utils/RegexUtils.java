package com.wuxiantao.wxt.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RegexUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午4:20
 * Description:${DESCRIPTION} 手机号码的工具类
 */
public class RegexUtils {

    /**
     * 判断手机号是否正确
     *@author android
     *@time 19-5-27 下午4:28
     */
    public static boolean checkPhone(String phone) {
        String telRegex = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$";
        return phone.matches(telRegex);
    }

    /**
     * 判断邮箱格式
     *@author android
     *@time 19-5-27 下午4:30
     */
    public static boolean checkEmail(String email){
        String regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,4}";
        return email.matches(regex);
    }

    /**
     * 车牌号验证
     *@author android
     *@time 19-5-27 下午4:31
     */
    public static boolean checkCarNo(String carNo){
        String regex = "^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\\u4e00-\\u9fa5]$";
        return carNo.matches(regex);
    }



    /**
     * 验证密码格式
     *  6-16位 大小写特殊字符，无划线，横线等
     *@author android
     *@time 19-5-27 下午4:28
     */
    public static boolean checkPassword(String password) {
        String psRegex = "^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,16}$";

        return password.matches(psRegex);
    }


    /**
     *检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾
     *  用户名有最小长度和最大长度限制，比如用户名必须是4-20位
     *@author android
     *@time 19-5-27 下午4:28
     */
    public boolean checkUsername(String username, int min, int max) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
        return startCheck(regex, username);
    }


    /**
     * 检验空白符
     *@author android
     *@time 19-5-27 下午4:27
     */
    public boolean checkWhiteLine(String line) {
        String regex = "(\\s|\\t|\\r)+";
        return startCheck(regex, line);
    }


    private boolean startCheck(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    /**
     * 字符串分隔
     * @param reg
     * @return
     */
    public static String imgUrlSeparate(String source,String reg){
        if (!isEmpty(reg) && !isEmpty(source)){
            if (array != null){
                array = null;
            }
            array = source.split(reg);
            return array[0];
        }
        return "";
    }

    private static String array[];

    /**
     * 字符串分隔
     * @return
     */
    public static String imgUrlSeparate(String source){
        return imgUrlSeparate(source,"\\|");
    }

    private static boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }
}
