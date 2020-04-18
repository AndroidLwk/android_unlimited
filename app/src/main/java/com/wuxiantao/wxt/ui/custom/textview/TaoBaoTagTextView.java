package com.wuxiantao.wxt.ui.custom.textview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TagTextView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-20 下午11:29
 * Description:${DESCRIPTION} 淘宝/天猫/优选标签
 */
public class TaoBaoTagTextView extends AppCompatTextView {

    private TextView tv_tag;
    private RelativeLayout layout;
    //标签布局的最外层布局
    private View view;
    private List<String> list = new ArrayList<>();
    private StringBuffer content_buffer = new StringBuffer();

    public static final int TYPE_TIANMAO = 1;
    public static final int TYPE_TAOBAO = 2;
    public static final int TYPE_YOUXUAN = 3;
    public static final int TYPE_SELF = 4;

    public TaoBaoTagTextView(Context context) {
        this(context, null);
    }

    public TaoBaoTagTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaoBaoTagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.tag_viewlable_taobao, null);
        layout = view.findViewById(R.id.tag_viewlable_taobao_bg);
        tv_tag = view.findViewById(R.id.tag_viewlable_taobao_tv);
    }


    public void setContentAndTag(String content, int type) {
        if (!list.isEmpty()) {
            list.clear();
        }
        if (content_buffer.length() > 0) {
            content_buffer.setLength(0);
        }
        String tianmao = getResources().getString(R.string.tianmao);
        String taobao = getResources().getString(R.string.taobao);
        String youxuan = getResources().getString(R.string.youxuan);
        String self = getResources().getString(R.string.self_employed);
        switch (type) {
            case TYPE_TIANMAO:
                list.add(tianmao);
                break;
            case TYPE_TAOBAO:
                list.add(taobao);
                break;
            case TYPE_YOUXUAN:
                list.add(youxuan);
                break;
            case TYPE_SELF:
                list.add(self);
                break;
        }
        //将每个tag的内容添加到content后边，之后将用drawable替代这些tag所占的位置
        for (String item : list) {
            content_buffer.append(item);
        }
        content_buffer.append(content);
        SpannableString spannableString = new SpannableString(content_buffer);
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            switch (type) {
                case TYPE_TIANMAO:
                    //天猫
                    layout.setBackgroundResource(R.drawable.tianmao_lable_bg);
                    tv_tag.setTextColor(getResources().getColor(R.color.white));
                    break;
                case TYPE_TAOBAO:
                    //淘宝
                    layout.setBackgroundResource(R.drawable.taobao_lable_bg);
                    tv_tag.setTextColor(getResources().getColor(R.color.white));
                    break;
                case TYPE_YOUXUAN:
                    //优选
                    layout.setBackgroundResource(R.drawable.youxuan_lable_bg);
                    tv_tag.setTextColor(Color.parseColor("#FB5654"));
                    break;
                case TYPE_SELF:
                    //自营
                    layout.setBackgroundResource(R.drawable.self_lable_bg);
                    tv_tag.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
            tv_tag.setText(item);
            Bitmap bitmap = convertViewToBitmap(view);
            BitmapDrawable d = new BitmapDrawable(bitmap);
            d.setBounds(0, 0, layout.getWidth(), layout.getHeight());
            //图片将对齐底部边线
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
            int startIndex;
            int endIndex;
            startIndex = getLastLength(list, i);
            endIndex = startIndex + item.length();
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }


    private Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }


    private int getLastLength(List<String> list, int maxLength) {
        int length = 0;
        for (int i = 0; i < maxLength; i++) {
            length += list.get(i).length();
        }
        return length;
    }

}
