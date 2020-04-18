package com.wuxiantao.wxt.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

import static com.wuxiantao.wxt.config.Constant.DECIMAL_BIT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BigDecimalUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-25 上午10:19
 * Description:${DESCRIPTION}
 */
public class BigDecimalUtils {

    /**
     * 提供精确的加法运算
     * @param v1 被加数
     * @param v2 加数
     * @param scale 保留scale 位小数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        if (isEmpty(v1) || isEmpty(v2)){
            return "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return formatNumber(b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString(),scale);
    }


    private static boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }

    /**
     * 提供精确的减法运算
     * @param v1 被减数
     * @param v2 减数
     * @param scale 保留scale 位小数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }



    /**
     * 提供精确的乘法运算
     * @param v1 被乘数
     * @param v2 乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(String v1,String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return formatNumber(b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).toString(),scale);
    }

    /**
     * 提供精确的乘法运算
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static int mul(String v1,String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 提供精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        if (isEmpty(v1) || isEmpty(v2)){
            return "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return formatNumber(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString(),scale);
    }

    /**
     * 提供精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static int div(int v1, int v2,int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 提供精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static int div(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 提供精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static int divInt(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static double round(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(DECIMAL_BIT, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String roundDoub(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(DECIMAL_BIT, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * 提供精确的小数位四舍五入处理
     * @param str 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String roundStr(double str) {
        BigDecimal b = new BigDecimal(str);
        return formatNumber(b.setScale(DECIMAL_BIT, BigDecimal.ROUND_HALF_UP).toString(),8);
    }


    /**
     * 提供精确的小数位四舍五入处理
     * @param str 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String roundStr(double str,int scale) {
        BigDecimal b = new BigDecimal(str);
        return formatNumber(b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString(),scale);
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b = new BigDecimal(v);
        return formatNumber(b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString(),scale);
    }


    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static String round(String v) {
        BigDecimal b = new BigDecimal(v);
        return b.setScale(DECIMAL_BIT, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static Double roundDoubleValue(String v) {
        BigDecimal b = new BigDecimal(v);
        return b.setScale(DECIMAL_BIT, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static float roundFloatValue(String v,int scale) {
        BigDecimal b = new BigDecimal(formatNumber(v,scale));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    //格式化小数
    private static String formatNumber(String number,int scale){
        if (number == null || TextUtils.isEmpty(number)){
            return "0";
        }
        if (number.indexOf(".") > 0){
            //正则表达 去掉后面无用的零
            number = number.replaceAll("0+?$", "");
            //如小数点后面全是零则去掉小数点
            number = number.replaceAll("[.]$", "");

            BigDecimal b = new BigDecimal(number);
            number = String.valueOf(b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return number;
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static int roundInt(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static int roundInt(double v) {
        BigDecimal b = new BigDecimal(v);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }



    /**
     * 提供精确的小数位四舍五入处理
     * @param v 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static int roundInt(String v) {
        BigDecimal b = new BigDecimal(v);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 取余数
     * @param v1 被除数
     * @param v2 除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static String remainder(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places reserved must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * 比较大小
     * @param v1 被比较数
     * @param v2 比较数
     * @return 如果v1 大于v2 则 返回true 否则false
     */
    public static boolean compare(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        return bj > 0;
    }

}
