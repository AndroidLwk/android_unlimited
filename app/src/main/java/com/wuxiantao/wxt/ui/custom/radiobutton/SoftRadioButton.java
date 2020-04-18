package com.wuxiantao.wxt.ui.custom.radiobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SoftRadioButton
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午8:49
 * Description:${DESCRIPTION}
 */
public class SoftRadioButton extends FrameLayout implements SoftCheckable{
    private boolean mBroadcasting;
    private boolean orientation;
    private TextView mTv_checkable_text;
    private ImageView mIv_checkable_up;
    private ImageView mIv_checkable_down;
    private ImageView mIv_right_icon;
    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;
    private int textColor = Color.BLACK;
    private int textColorChecked = Color.RED;
    private int upImageRes, upImageResChecked, downImageRes, downImageResChecked,rightImgCheck,rightImgChecked;
    private boolean ischecked;
    private OnClickListener mOnClickListener;
    private SoftRadioGroup mSoftRadioGroup;

    public SoftRadioButton(Context context) {
        this(context,null);
    }

    public SoftRadioButton(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SoftRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SoftRadioButton);
        String text = ta.getString(R.styleable.SoftRadioButton_text);
        textColor = ta.getColor(R.styleable.SoftRadioButton_textColor, textColor);
        textColorChecked = ta.getColor(R.styleable.SoftRadioButton_textColorChecked, textColorChecked);
        upImageRes = ta.getResourceId(R.styleable.SoftRadioButton_upImage, 0);
        downImageRes = ta.getResourceId(R.styleable.SoftRadioButton_downImage, 0);
        upImageResChecked = ta.getResourceId(R.styleable.SoftRadioButton_upImageChecked, 0);
        downImageResChecked = ta.getResourceId(R.styleable.SoftRadioButton_downImageChecked, 0);
        rightImgCheck = ta.getResourceId(R.styleable.SoftRadioButton_rightImgCheck,0);
        rightImgChecked = ta.getResourceId(R.styleable.SoftRadioButton_rightImgChecked,0);
        ta.recycle();
        LayoutInflater inflate = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.soft_item_view, this, true);
        mTv_checkable_text =  view.findViewById(R.id.tv_checkable_text);
        mIv_checkable_up =  view.findViewById(R.id.iv_checkable_up);
        mIv_checkable_down =  view.findViewById(R.id.iv_checkable_down);
        mIv_right_icon = view.findViewById(R.id.iv_checkable_right_icon);

        mTv_checkable_text.setText(text);
        mIv_right_icon.setVisibility(rightImgCheck > 0 && rightImgChecked > 0 ? VISIBLE : GONE);

        refreshView();
        setOnClickListener(null);
    }

    public void setText(CharSequence text) {
        mTv_checkable_text.setText(text);
    }

    public CharSequence getText() {
        return mTv_checkable_text.getText();
    }

    @Override
    public void setOnClickListener(View.OnClickListener l) {
        this.mOnClickListener = l;
        super.setOnClickListener(v -> {
            if (mOnClickListener != null){
                mOnClickListener.onClick(v);
            }
            if (isChecked()){
                orientation = !orientation;
                setChecked(true, orientation);
            }else {
                setChecked(true, orientation);
            }
            refreshView();
        });
    }

    @Override
    public void setChecked(boolean checked, boolean isOrientation) {
        if (checked){
            makeCallBack(isOrientation);
        }
        if (ischecked != checked){
            ischecked = checked;
            refreshView();
            if (mBroadcasting){
                return;
            }
            mBroadcasting = true;
            if (mOnCheckedChangeWidgetListener != null){
                mOnCheckedChangeWidgetListener.onCheckedChanged(this,ischecked);
            }
            mBroadcasting = false;
        }else {
            if (isOrientation != orientation){
                refreshView();
                if (mBroadcasting) {
                    return;
                }
                mBroadcasting = true;
                if (mOnCheckedChangeWidgetListener != null) {
                    mOnCheckedChangeWidgetListener.onCheckedChanged(this, ischecked);
                }
                mBroadcasting = false;
            }
        }
    }

    private SoftRadioGroup getGroup(){
        if (mSoftRadioGroup == null){
            if (getParent() != null && getParent() instanceof SoftRadioGroup){
                mSoftRadioGroup = (SoftRadioGroup) getParent();
            }
        }
        return mSoftRadioGroup;
    }

    private void makeCallBack(boolean isOrientation){
        SoftRadioGroup group = getGroup();
        if (group != null && group.getOnCheckedChangeListener() != null){
            group.getOnCheckedChangeListener().onCheckedChanged(group,getId(),isOrientation);
        }
    }

    @Override
    public boolean isChecked() {
        return ischecked;
    }

    public boolean isOrientation(){
        return orientation;
    }

    @Override
    public void toggle() {
        ischecked = !ischecked;
        refreshView();
    }


    private void refreshView(){
        if (mIv_right_icon.getVisibility() == VISIBLE){
            if (isChecked()){
                mIv_right_icon.setBackgroundResource(rightImgChecked);
                mTv_checkable_text.setTextColor(textColorChecked);
            }else {
                mIv_right_icon.setBackgroundResource(rightImgCheck);
                mTv_checkable_text.setTextColor(textColor);
            }
        }else {
            if (isChecked()){
                if (orientation){
                    mIv_checkable_up.setImageResource(upImageResChecked);
                    mIv_checkable_down.setImageResource(downImageRes);
                }else {
                    mIv_checkable_up.setImageResource(upImageRes);
                    mIv_checkable_down.setImageResource(downImageResChecked);
                }
                mTv_checkable_text.setTextColor(textColorChecked);
            }else {
                mIv_checkable_up.setImageResource(upImageRes);
                mIv_checkable_down.setImageResource(downImageRes);
                mTv_checkable_text.setTextColor(textColor);
            }
        }
    }

    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener l){
        this.mOnCheckedChangeWidgetListener = l;
    }

    public interface OnCheckedChangeListener{
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(SoftRadioButton buttonView, boolean isChecked);
    }

}
