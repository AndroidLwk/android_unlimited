package com.wuxiantao.wxt.ui.custom.textview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
 * Description:${DESCRIPTION} 0元购/高佣标签
 */
public class BuyZeroTagTextView extends AppCompatTextView {

    private TextView tv_tag;
    private RelativeLayout layout;
    private ImageView img;
    //标签布局的最外层布局
    private View view;
    private List<String> list = new ArrayList<>();
    private StringBuffer content_buffer = new StringBuffer();

    public static final int TYPE_BUY_ZERO = 4;

    public static final int TYPE_HIGH_COMMISSION = 5;

    public BuyZeroTagTextView(Context context) {
        this(context,null);
    }

    public BuyZeroTagTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BuyZeroTagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        view = LayoutInflater.from(context).inflate(R.layout.tag_viewlable_buyzero, null);
        layout = view.findViewById(R.id.tag_viewlable_buyzero_bg);
        tv_tag = view.findViewById(R.id.tag_viewlable_buyzero_tv);
        img = view.findViewById(R.id.tag_viewlable_buyzero_img);
    }


    public void setContentAndTag(String content,int type){
        if (!list.isEmpty()){
            list.clear();
        }
        if (content_buffer.length() > 0){
            content_buffer.setLength(0);
        }
        String buyZero = getResources().getString(R.string.buy_zero);
        String hightCommission = getResources().getString(R.string.high_commission);
        switch (type){
            case TYPE_BUY_ZERO:
                list.add(buyZero);
                break;
            case TYPE_HIGH_COMMISSION:
                list.add(hightCommission);
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
            switch (type){
                //0元购
                case TYPE_BUY_ZERO:
                    img.setBackgroundResource(R.mipmap.buy_zero_icon);
                    break;
                case TYPE_HIGH_COMMISSION:
                    //高佣
                    img.setBackgroundResource(R.mipmap.commission_icon);
                    break;
            }
            tv_tag.setVisibility(GONE);
            tv_tag.setText(item);
            Bitmap bitmap = convertViewToBitmap(view);
            Drawable d = new BitmapDrawable(bitmap);
            d.setBounds(0, 0, layout.getWidth(),layout.getHeight());
            //图片将对齐底部边线
            ImageSpan span = new ImageSpan(d,ImageSpan.ALIGN_BOTTOM);
            int startIndex;
            int endIndex;
            startIndex = getLastLength(list, i );
            endIndex = startIndex + item.length();
            spannableString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private Bitmap convertViewToBitmap(View view){
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }


    private int getLastLength(List<String> list,int maxLength){
        int length = 0;
        for (int i = 0;i < maxLength;i++){
            length += list.get(i).length();
        }
        return length;
    }

}
