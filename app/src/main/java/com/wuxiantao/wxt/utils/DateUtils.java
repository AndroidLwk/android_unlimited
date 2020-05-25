package com.wuxiantao.wxt.utils;

import android.annotation.SuppressLint;

import com.ssm.sp.SPSecuredUtils;
import com.wuxiantao.wxt.app.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wuxiantao.wxt.config.Constant.FAST_CLICK_DELAY_TIME;
import static com.wuxiantao.wxt.config.Constant.FAST_CLICK_DIVIDED_DRAGON;
import static com.wuxiantao.wxt.config.Constant.FAST_CLICK_RED_BAG;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/11/20 0020 11:54 8-19
 * Description: ${DESCRIPTION} 日期 时间 工具类
 * Author: Administrator Shiming-Shi
 */

public class DateUtils {

    // 1分钟
    private final static long minute = 60 * 1000;

    // 1小时
    private final static long hour = 60 * minute;

    // 1天
    private final static long day = 24 * hour;

    // 月
    private final static long month = 31 * day;

    // 年
    private final static long year = 12 * month;

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat format = new SimpleDateFormat(PATTERN);

    /**
     * 获取时间差
     *
     * @param time
     * @return
     */
    public static String getTimeDifference(String time) {
        try {
            Date d1 = new Date(System.currentTimeMillis());
            Date d2 = new Date(time);
            return getTimeText(d1.getTime() - d2.getTime());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 返回文字描述的日期
     *
     * @param diff
     * @return
     */
    private static String getTimeText(long diff) {
        if (diff > year) {
            return diff / year + "年前";
        }
        if (diff > month) {
            return diff / month + "个月前";
        }
        if (diff > day) {
            return diff / day + "天前";
        }
        if (diff > hour) {
            return diff / hour + "个小时前";
        }
        if (diff > minute) {
            return diff / minute + "分钟前";
        }
        return "刚刚";
    }


    /**
     * 此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）
     *
     * @param time 时间
     * @return 返回时间戳
     */
    public static String timeToTimestamp(String time) {
        Date date;
        String timestamp = null;
        try {
            date = format.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            timestamp = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTimeToday() {
        return format.format(new Date());
    }


    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param timestamp 时间戳
     * @return 返回时间 如:2014年06月14日16时09分00秒
     */
    public static String timestampToTime(String timestamp) {
        int i = Integer.parseInt(timestamp);
        return format.format(new Date(i * 1000L));
    }


    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param timestamp 时间戳
     * @return 返回时间 如:2014年06月14日16时09分00秒
     */
    public static String timestampToTime(long timestamp) {
        return format.format(new Date(timestamp * 1000L));
    }


    /**
     * 将秒数转为时分秒 并格式化
     *
     * @param s 秒数
     * @return 时分秒
     */
    @SuppressLint("DefaultLocale")
    public static String timeParse(long s) {
        String time;
        //小于1分钟
        if (s < 60) {
            time = String.format("00:%02d", s % 60);
        }
        //小于1个小时
        else if (s < 3600) {
            time = String.format("%02d:%02d", s / 60, s % 60);
        }
        //小于一小时
        else if (s < 3600 * 24) {
            time = String.format("%02d:%02d:%02d", s / 3600, s % 3600 / 60, s % 60);
        }
        //大于一小时
        else {
            time = String.format("%02d天%02d:%02d:%02d", s / 86400, s % 86400 / 3600, s % 86400 % 3600 / 60, s % 86400 % 3600 % 60);
        }
        return time;
    }

    private static Date date = new Date();

    /**
     * 将毫秒转化成秒
     *
     * @param time
     * @return
     */
    public static int convertToSecond(long time) {
        date.setTime(time);
        return date.getSeconds();
    }

    /**
     * 将秒数转为时分秒 并格式化
     *
     * @param s 秒数
     * @return 时分秒
     */
    @SuppressLint("DefaultLocale")
    public static String secondParse(long s) {
        String time;
        if (s < 3600) {
            time = String.format("%2d分钟", s / 60);
        } else {
            time = String.format("%2d小时%2d分钟", s / 3600, s % 3600 / 60);
        }
        return time;
    }


    /**
     * 把时间戳转换为毫秒
     *
     * @param timestamp 时间戳
     * @return 毫秒
     */
    public static long timeStampToMillisecond(long timestamp) {
        try {
            return format.parse(String.valueOf(timestamp)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 把时间戳转换为毫秒
     *
     * @param timestamp 时间戳
     * @return 毫秒
     */
    public static long timeStampToMillisecond(String timestamp) {
        try {
            return format.parse(String.valueOf(timestamp)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 把毫秒转换成(天 时 分 秒 毫秒)
     *
     * @param timestamp 时间戳
     * @return
     */
    public static String formatMillisecond(long timestamp) {
        return millisecondToTime(timeStampToMillisecond(System.currentTimeMillis() - timestamp));
    }

    private static StringBuffer sb;

    /**
     * 把毫秒转换成(天 时 分 秒 毫秒)
     *
     * @param ms 毫秒
     * @return
     */
    private static String millisecondToTime(long ms) {
        if (sb == null) {
            sb = new StringBuffer();
        }
        if (sb.length() > 0) {
            sb.setLength(0);
        }
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;


        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }


    public static boolean isCanClick1() {
        boolean flag = false;
        long lastTime1 = (long) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(FAST_CLICK_DIVIDED_DRAGON, 0L);
        long curClickTime = System.currentTimeMillis() / 1000;
        long diff = curClickTime - lastTime1;
        if (diff >= FAST_CLICK_DELAY_TIME) {
            //可以点击
            flag = true;
        }
        return flag;
    }

    public static boolean isCanClick2() {
        boolean flag = false;
        long lastTime2 = (long) SPSecuredUtils.newInstance(BaseApplication.getInstance()).get(FAST_CLICK_RED_BAG, 0L);
        long curClickTime = System.currentTimeMillis() / 1000;
        long diff = curClickTime - lastTime2;
        if (diff >= FAST_CLICK_DELAY_TIME) {
            //可以点击
            flag = true;
        }
        return flag;
    }

    /**
     * 获取时间戳字符串
     *
     * @param time
     * @return
     */
    public static String getStringTimestamp(String time) {
        String timestamp = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long longTime = sdf.parse(time).getTime();
            timestamp = Long.toString(longTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
