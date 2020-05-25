package com.wuxiantao.wxt.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/9/12 0012 10:39 8-19
 * Description: ${DESCRIPTION} 数学计算
 * Author: Administrator Shiming-Shi
 */

public class MathUtils {

    private static String nums[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static String pos_units[] = {"", "拾", "佰", "仟"};

    private static String weight_units[] = {"", "萬", "億"};


    /**
     * 数字转汉字【新】
     * @param num
     * @return
     */
    public static String numberToChinese(long num) {
        if (num == 0) {
            return "零";
        }
        int weigth = 0;//节权位
        String chinese = "";
        String chinese_section = "";
        //下一小节是否需要零，第一次没有上一小节所以为false
        boolean setZero = false;
        while (num > 0) {
            //得到最后面的小节
            long section = num % 10000;
            //判断上一小节的千位是否为零，是就设置零
            if (setZero) {
                chinese = nums[0] + chinese;
            }
            chinese_section = sectionTrans(section);
            if (section != 0) {//判断是都加节权位
                chinese_section = chinese_section + weight_units[weigth];
            }
            chinese = chinese_section + chinese;
            chinese_section = "";

            setZero = (section < 1000) && (section > 0);
            num = num / 10000;
            weigth++;
        }
        if ((chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十")) {
            chinese = chinese.substring(1, chinese.length());
        }
        if (chinese.indexOf("一十") == 0) {
            chinese = chinese.replaceFirst("一十", "十");
        }
        return chinese;
    }

    /**
     * 将每段数字转汉子
     * @param section
     * @return
     */
    private static String sectionTrans(long section) {
        StringBuilder section_chinese = new StringBuilder();
        int pos = 0;//小节内部权位的计数器
        boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
        while (section > 0) {
            int v = (int) (section % 10);//得到最后一个数
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    section_chinese.insert(0, nums[0]);
                }
            } else {
                zero = false;//有非零数字就把置零打开
                section_chinese.insert(0, pos_units[pos]);
                section_chinese.insert(0, nums[v]);
            }
            pos ++;
            section = section / 10;
        }

        return section_chinese.toString();
    }



    /**
     * 小数格式化
     * @param pattern
     * @param target
     * @return
     */
    public  static String decimalFormat(String  pattern,double target){
        DecimalFormat format = new DecimalFormat(pattern);
        return format.format(target);
    }


    /**
     * 小数格式化
     * @param pattern
     * @param target
     * @return
     */
    public static String decimalFormat(String  pattern,String  target){
        double  targetDouble = Double.valueOf(target);
        DecimalFormat format = new DecimalFormat(pattern);
        return format.format(targetDouble);
    }


    /**
     * 金额格式化
     * @param s 金额
     * @param len 小数位数
     * @return 格式后的金额
     */
    public static String formatCurrency(String s, int len) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater;
        double num = Double.parseDouble(s);
        if (len == 0) {
            formater = new DecimalFormat("###.###");
        } else {
            StringBuilder buff = new StringBuilder();
            buff.append("###.");
            for (int i = 0; i < len; i ++ ) {
                buff.append("#");
            }
            formater = new DecimalFormat(buff.toString());
        }
        String str = formater.format(num);
        if (str.contains("-1")){
            return "0.0";
        }
        return str;
    }

    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();

    }

    /**
     * 两个数相减
     *
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();

    }

}
