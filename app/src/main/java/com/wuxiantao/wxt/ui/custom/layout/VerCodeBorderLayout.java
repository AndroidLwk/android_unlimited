package com.wuxiantao.wxt.ui.custom.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VerCodeBorderLayout
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 上午10:00
 * Description:${DESCRIPTION} 验证码 输入框
 */
public class VerCodeBorderLayout extends LinearLayout implements TextWatcher, View.OnKeyListener {

    //输入框类型
    private final static String TYPE_NUMBER = "number";
    private final static String TYPE_TEXT = "text";
    private final static String TYPE_PASSWORD = "password";
    private final static String TYPE_PHONE = "phone";

    //输入框个数
    private int maxNum = 4;

    //输入框背景颜色
    private Drawable normalBackground = null;
    private Drawable pressedBackground = null;

    //字体颜色
    private int normalTextColor = Color.parseColor("#000000");
    private int pressedTextColor = Color.parseColor("#FFFFFF");

    //宽高
    private int vc_box_width = 80;
    private int vc_box_height = 80;

    //间距
    private int vc_box_padding = 0;

    private boolean focus = false;
    private int currentPosition = 0;

    private List<EditText> boxs = new ArrayList<>();

    private OnCompleteListener listener;

    private StringBuilder sb = new StringBuilder();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public VerCodeBorderLayout(Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public VerCodeBorderLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public VerCodeBorderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VerCodeBorderLayout);
        maxNum = ta.getInteger(R.styleable.VerCodeBorderLayout_vc_maxNum,maxNum);
        vc_box_width = ta.getDimensionPixelSize(R.styleable.VerCodeBorderLayout_vc_box_width,vc_box_width);
        vc_box_height = ta.getDimensionPixelSize(R.styleable.VerCodeBorderLayout_vc_box_height,vc_box_height);
        vc_box_padding = ta.getDimensionPixelSize(R.styleable.VerCodeBorderLayout_vc_box_padding,vc_box_padding);
        normalBackground = ta.getDrawable(R.styleable.VerCodeBorderLayout_vc_normalBackground);
        pressedBackground = ta.getDrawable(R.styleable.VerCodeBorderLayout_vc_pressedBackground);
        normalTextColor = ta.getColor(R.styleable.VerCodeBorderLayout_vc_normalTextColor,normalTextColor);
        pressedTextColor = ta.getColor(R.styleable.VerCodeBorderLayout_vc_pressedTextColor,pressedTextColor);
        ta.recycle();

        for (int i = 0;i < maxNum;i++){
            EditText edt = new EditText(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(vc_box_width,vc_box_height);
            params.leftMargin = vc_box_padding;
            params.rightMargin = vc_box_padding;
            params.gravity = Gravity.CENTER;
            edt.setOnKeyListener(this);
            setEditTextBg(edt,false);
            edt.setTextColor(normalTextColor);
            edt.setLayoutParams(params);
            edt.setGravity(Gravity.CENTER);
            edt.setId(i);
            edt.setEms(1);
            edt.setPadding(0,0,0,0);
            edt.setInputType(InputType.TYPE_CLASS_NUMBER);
            edt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            changeCursorColor(edt);
            edt.addTextChangedListener(this);
            addView(edt,i);
            boxs.add(edt);
        }
        showKeyWord();
    }

    private void changeCursorColor(EditText edt){
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(edt,R.drawable.text_cursor);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void clear(){
        for (int i = 0;i < boxs.size();i++){
            focus = false;
            currentPosition = 0;
            boxs.get(i).setText("");
            boxs.get(0).requestFocus();
            setEditTextBg(boxs.get(i),false);
            boxs.get(i).setEnabled(true);
        }
        showKeyWord();
    }

    private void showKeyWord(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.showSoftInput(boxs.get(0),0);
            }
        },200);
    }


    private void focus(){
        int count = getChildCount();
        EditText edt;
        for (int i = 0;i < count;i++){
            edt = (EditText) getChildAt(i);
            if (edt.getText().length() < 1){
                edt.requestFocus();
                return;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setEditTextBg(EditText edt, boolean focus){
        if (normalBackground != null && !focus){
            edt.setBackground(normalBackground);
            edt.setTextColor(normalTextColor);
        }
        else if (pressedBackground != null && focus){
            edt.setBackground(pressedBackground);
            edt.setTextColor(pressedTextColor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setEditTextBg(){
        int count = getChildCount();
        EditText edt;
        for (int i = 0;i < count;i++){
            edt = (EditText) getChildAt(i);
            if (normalBackground != null && !focus){
                edt.setBackground(normalBackground);
            }else {
                edt.setBackground(pressedBackground);
            }
        }
    }


    private void checkAndCommit(){
        boolean full = true;
        for (int i = 0;i < maxNum;i++){
            EditText edt = (EditText) getChildAt(i);
            String content = edt.getText().toString();
            if (content.length() <= 0){
                full = false;
                break;
            }else {
                sb.append(content);
            }
        }
        if (full){
            if (listener != null){
                listener.onComplete(sb.toString());
                setEnabled(false);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        int childCount = getChildCount();
        for (int i = 0;i < childCount;i++){
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }

    public void setOnCompleteListener(OnCompleteListener l){
        this.listener = l;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0;i < count;i++){
            View child = getChildAt(i);
            this.measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        if (count > 0){
            View child = getChildAt(0);
            int cHeight = child.getMeasuredHeight();
            int cWidth = child.getMeasuredWidth();
            int maxH = cHeight + 2 * vc_box_padding;
            int maxW = (cWidth + vc_box_padding) * maxNum + vc_box_padding;
            setMeasuredDimension(resolveSize(maxW,widthMeasureSpec),resolveSize(maxH,heightMeasureSpec));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0;i < childCount;i++){
            View child = getChildAt(i);
            child.setVisibility(VISIBLE);
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();
            int cl = (i) * (cWidth + vc_box_padding);
            int cr = cl + cWidth;
            int ct = vc_box_padding;
            int cb = ct + cHeight;
            child.layout(cl,ct,cr,cb);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (start == 0 && count >= 1 && currentPosition < boxs.size()){
            boxs.get(currentPosition).requestFocus();
            setEditTextBg(boxs.get(currentPosition),true);
            currentPosition++;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0){
            sb.setLength(0);
            focus();
            checkAndCommit();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        EditText edt = (EditText) v;
        if (keyCode == KeyEvent.KEYCODE_DEL && edt.getText().length() == 0){
            int action = event.getAction();
            if (currentPosition != 0 && action == KeyEvent.ACTION_DOWN){
                currentPosition --;
                boxs.get(currentPosition).requestFocus();
                setEditTextBg(boxs.get(currentPosition),false);
                boxs.get(currentPosition).setText("");
            }
        }
        return false;
    }

    public interface OnCompleteListener{
        void onComplete(String content);
    }
}
