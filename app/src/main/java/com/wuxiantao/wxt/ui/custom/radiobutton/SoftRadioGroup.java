package com.wuxiantao.wxt.ui.custom.radiobutton;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SoftRadioGroup
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午8:45
 * Description:${DESCRIPTION} 带排序按钮的RadioGroup
 */
public class SoftRadioGroup extends LinearLayout {
    // holds the checked id; the selection is empty by default
    private int mCheckedId = -1;
    private boolean orientation;
    //tracks children radio buttons checked state
    private SoftRadioButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    //when true, mOnCheckedChangeListener discards events
    private boolean mProtectFromCheckedChange = false;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;

    public SoftRadioGroup(Context context) {
        this(context,null);
    }

    public SoftRadioGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SoftRadioGroup(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //setOrientation(HORIZONTAL);
        mChildOnCheckedChangeListener = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }


    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        // the user listener is delegated to our pass-through listener
        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // checks the appropriate radio button as requested in the XML file
        if (mCheckedId != -1) {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof SoftRadioButton){
            final  SoftRadioButton button = (SoftRadioButton) child;
            if (button.isChecked()){
                mProtectFromCheckedChange = true;
                if (mCheckedId != -1){
                    setCheckedStateForView(mCheckedId, false);
                }
                mProtectFromCheckedChange = false;
            }
        }
        super.addView(child, index, params);
    }


    public void check(int id){
        // don't even bother
        if (id != -1 && (id == mCheckedId)) {
            return;
        }
        if (mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }
        if (id != -1) {
            setCheckedStateForView(id, true);
        }
        setCheckedId(id);
    }

    private void setCheckedId(int id){
        this.mCheckedId = id;
    }

    public OnCheckedChangeListener getOnCheckedChangeListener(){
        return mOnCheckedChangeListener;
    }

    /**
     * <p>Register a callback to be invoked when the checked radio button
     * changes in this group.</p>
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener){
        this.mOnCheckedChangeListener = listener;
    }

    public void setCheckedStateForView(int viewId,boolean checked){
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof SoftRadioButton) {
            SoftRadioButton radioButton = (SoftRadioButton) checkedView;
            ((SoftRadioButton) checkedView).setChecked(checked,radioButton.isOrientation());
        }
    }

    public int getCheckedRadioButtonId(){
        return mCheckedId;
    }

    public void clearCheck(){
        check(-1);
    }


    public interface  OnCheckedChangeListener{
        /**
         * <p>Called when the checked radio button has changed. When the
         * selection is cleared, checkedId is -1.</p>
         *
         * @param group     the group in which the checked radio button has changed
         * @param checkedId the unique identifier of the newly checked radio button
         */
         void onCheckedChanged(SoftRadioGroup group, @IdRes int checkedId, boolean orientation);
    }


    private class CheckedStateTracker implements SoftRadioButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(SoftRadioButton buttonView, boolean isChecked) {
            if (mProtectFromCheckedChange){
                return;
            }
            mProtectFromCheckedChange = true;
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;

            int id = buttonView.getId();
            setCheckedId(id);
        }
    }


    /**
     * <p>A pass-through listener acts upon the events and dispatches them
     * to another listener. This allows the table layout to set its own internal
     * hierarchy change listener without preventing the user to setup his.</p>
     */
    private class PassThroughHierarchyChangeListener implements OnHierarchyChangeListener{
        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        @Override
        public void onChildViewAdded(View parent, View child) {
            if (parent == SoftRadioGroup.this && child instanceof SoftRadioButton){
                int id = child.getId();
                // generates an id if it's missing
                if (id == View.NO_ID) {
                    throw new RuntimeException("SoftRadioButton must set Id");
                }
                ((SoftRadioButton) child).setOnCheckedChangeWidgetListener(mChildOnCheckedChangeListener);
            }
            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {
            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}
