package com.wuxiantao.wxt.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Pair;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TextViewUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-26 下午3:20
 * Description:${DESCRIPTION}
 */
public class TextViewUtils {

    private static  ClipboardManager cbm;

    //文本复制
    public static void copy(String s){
        if (s != null && !TextUtils.isEmpty(s)){
            if (cbm == null){
                // 得到剪贴板管理器
                cbm = (ClipboardManager) BaseApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
            }
            //创建ClipData对象
            //第一个参数只是一个标记，随便传入
            //第二个参数是要复制到剪贴版的内容
            ClipData clip = ClipData.newPlainText("spread_anim text",s);
            //传入clipdata对象.
            cbm.setPrimaryClip(clip);
            ToastUtils.success("文本已复制");
        }
    }

    //设置textview删除线
    public static void setTextViewDeleteLine(TextView tv,CharSequence source){
        SpannableString sp = new SpannableString(source);
        //设置背景颜色
        sp.setSpan(new StrikethroughSpan(), 0 ,source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sp);
        //设置TextView可点击
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //设置textview的下划线
    public static void setTextViewUnderLine(TextView tv,CharSequence source){
        // 创建一个 SpannableString对象
        SpannableString sp = new SpannableString(source);
        //设置背景颜色
        sp.setSpan(new UnderlineSpan(), 3 ,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sp);
        //设置TextView可点击
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }


    //设置textview超链接
    public static void setTextLink(TextView tv,String url,CharSequence source){
        // 创建一个 SpannableString对象
        SpannableString sp = new SpannableString(source);
        // 设置超链接
        sp.setSpan(new URLSpan(url),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(sp);
        //设置TextView可点击
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //设置背景色高亮
    public static void setTextViewBgHightLight(TextView view, CharSequence source){
        // 创建一个 SpannableString对象
        SpannableString sp = new SpannableString(source);
        //设置背景颜色
        sp.setSpan(new BackgroundColorSpan(R.drawable.base_title_background),0,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(sp);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //设置关键字颜色高亮
    public static void setTextViewKeyWordHighlight(TextView tv, String source, @ColorInt int color, Pair<Integer, Integer>... pairs){
        if (tv == null || pairs.length <= 0){
            return;
        }
        //去掉所有空格
        source = source.replaceAll(" ","");
        // 创建一个 SpannableString对象
        WeakReference<TextView> reference = new WeakReference<>(tv);
        SpannableStringBuilder sp = new SpannableStringBuilder(source);
        try {
            for (Pair<Integer, Integer> pair : pairs) {
                int start = pair.first;
                int end = pair.second;
                sp.setSpan(new ForegroundColorSpan(color),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        reference.get().setText(sp);
    }


    //设置关键字颜色高亮
    public static void setTextViewKeyWordHightLight(TextView tv,CharSequence source,String regex){
        // 创建一个 SpannableString对象
        SpannableString sp = new SpannableString(source);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        //通过正则查找，逐个高亮
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")),
                    start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(sp);
    }

    //设置TextView首行缩进
    public static void setTextViewIndentation(TextView tv,CharSequence source){
        // 创建一个 SpannableStringBuilder对象
        SpannableStringBuilder span = new SpannableStringBuilder("缩进\u3000" + source);
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT),0,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(span);
    }
}
