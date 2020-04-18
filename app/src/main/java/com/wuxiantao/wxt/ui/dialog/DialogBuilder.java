package com.wuxiantao.wxt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.timer.CountdownManager;
import com.wuxiantao.wxt.ui.custom.edittext.filter.MaxTextLengthFilter;
import com.wuxiantao.wxt.ui.custom.edittext.watcher.InputTextWatcher;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/12 0012 11:01 8-19
 * Description: ${DESCRIPTION} Dialog Package
 * Author: Administrator Shiming-Shi
 */

public class DialogBuilder {

    //Dialog
    private Dialog mDialog;


    //confirm button
    private Button base_dialog_confirm;

    //cancel button
    private Button base_dialog_cancel;

    private boolean isSetConfirm;
    private boolean isSetCancel;

    /**
     * structure function
     *
     * @param builder builder
     */
    protected DialogBuilder(Builder builder) {
        this.mDialog = builder.mDialog;
        this.isSetConfirm = builder.isSetConfirm;
        this.isSetCancel = builder.isSetCancel;
        this.base_dialog_confirm = builder.base_dialog_confirm;
        this.base_dialog_cancel = builder.base_dialog_cancel;
    }


    public static class Builder implements View.OnClickListener {
        //Context
        private Context mContext;

        //Dialog
        private Dialog mDialog;

        //Title layout
        private RelativeLayout base_dialog_title_layout;

        //Title
        private TextView base_dialog_title;

        //Title icon
        private ImageView base_dialog_title_icon;

        //Close img
        private ImageView base_dialog_close;

        //Line
        private View base_dialog_h_line;
        private View base_dialog_v_line;

        //CountDown time message layout
        private LinearLayout base_dialog_count_down_time_message_layout;

        //Befo CountDown time
        private TextView base_dialog_count_down_time_msg_befo;

        //CountDown message
        private TextView base_dialog_count_down_time_msg;

        //CountDown icon
        private ImageView base_dialog_count_down_time_icon;

        //After CountDown time
        private TextView base_dialog_count_down_time_msg_after;

        //MessageEvent layout
        private RelativeLayout base_dialog_message_layout;

        //MessageEvent prompt
        private TextView base_dialog_msg_pro;

        //MessageEvent
        private TextView base_dialog_msg;

        //MessageEvent icon
        private ImageView base_dialog_message_icon;

        //Input layout
        private RelativeLayout base_dialog_input_layout;

        //Input edit
        private EditText base_dialog_input_edit;

        //Curreent character
        private TextView base_dialog_input_curreent_character;

        //Sum character
        private TextView base_dialog_input_sum_character;

        //Confirm Button
        private Button base_dialog_confirm;

        //Cancel Button
        private Button base_dialog_cancel;

        //Confirm Listener
        private DialogInterface.OnClickListener mPositiveBtnClickListener;

        //Cancel Listener
        private DialogInterface.OnClickListener mNegativeBtnClickListener;
        //CountdownManager
        private CountdownManager timeManager;
        private static final long DEFAULT_TIME = 20 * 1000;

        private static final String DEFAULT_CONFIRM_TEXT = BaseApplication.getAppContext().getString(R.string.confirm);
        private static final String DEFAULT_CANCEL_TEXT = BaseApplication.getAppContext().getString(R.string.cancel);
        private static final String DEFAULT_INPUT_HINT = BaseApplication.getAppContext().getString(R.string.input_info);
        private static final int DEFAULT_INPUT_CHARACTER = 40;
        private boolean isSetConfirm;
        private boolean isSetCancel;

        public Builder(Context context) {
            this.mContext = context;
            createDialog();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //confirm
                case R.id.base_dialog_confirm:
                    if (mPositiveBtnClickListener != null) {
                        mPositiveBtnClickListener.onClick(mDialog, DialogInterface.BUTTON_POSITIVE);
                    } else if (listener != null) {
                        listener.onComponClick(getInputEditText());
                    }
                    dismiss();
                    break;
                //cancel
                case R.id.base_dialog_cancel:
                    if (mNegativeBtnClickListener != null) {
                        mNegativeBtnClickListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                    dismiss();
                    break;
                //close
                case R.id.base_dialog_close:
                    dismiss();
                    break;
            }
        }

        /**
         * Set Dialog title
         *
         * @param title title text
         * @return
         */
        public Builder setDialogTitle(String title) {
            if (!isEmpty(title)) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title.setText(title);
            }
            return this;
        }

        /**
         * Set Dialog title
         *
         * @param title title text
         * @return
         */
        public Builder setDialogTitle(@StringRes int title) {
            if (title != 0) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title.setText(title);
            }
            return this;
        }

        /**
         * Set Dialog title
         *
         * @param color title color
         * @return
         */
        public Builder setDialogTitleColor(@ColorInt int color) {
            if (color != 0) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title.setTextColor(color);
            }
            return this;
        }

        /**
         * Set Dialog title
         *
         * @param color title color
         * @return
         */
        public Builder setDialogTitleColor(String color) {
            if (!isEmpty(color)) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title.setTextColor(Color.parseColor(color));
            }
            return this;
        }

        /**
         * Set Dialog icon
         *
         * @param resId title icon-id
         * @return
         */
        public Builder setDialogTitleIcon(@DrawableRes int resId) {
            if (resId != 0) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title_icon.setVisibility(View.VISIBLE);
                base_dialog_title_icon.setBackgroundResource(resId);
            }
            return this;
        }

        /**
         * Set Dialog icon
         *
         * @param url title icon-id
         * @return
         */
        public Builder setDialogTitleIcon(String url) {
            if (!isEmpty(url)) {
                base_dialog_title_layout.setVisibility(View.VISIBLE);
                base_dialog_title_icon.setVisibility(View.VISIBLE);
                GlideImgManager.loadCircleImg(mContext, url, base_dialog_title_icon);
            }
            return this;
        }

        /**
         * Set Dialog line color
         *
         * @param color color
         * @return
         */
        public Builder setDialogLineColor(String color) {
            base_dialog_h_line.setVisibility(View.VISIBLE);
            base_dialog_v_line.setVisibility(View.VISIBLE);
            if (!isEmpty(color)) {
                base_dialog_h_line.setBackgroundColor(Color.parseColor(color));
                base_dialog_v_line.setBackgroundColor(Color.parseColor(color));
            }
            return this;
        }


        /**
         * Set Dialog line color
         * @param color color
         * @return
         */
        public Builder setDialogLineColor(@ColorInt int color) {
            base_dialog_h_line.setVisibility(View.VISIBLE);
            base_dialog_v_line.setVisibility(View.VISIBLE);
            if (color != 0) {
                base_dialog_h_line.setBackgroundColor(color);
                base_dialog_v_line.setBackgroundColor(color);
            }
            return this;
        }

        /**
         * Set Dialog MessageEvent Icon
         *
         * @param resId icon
         * @return
         */
        public Builder setDialogMessageIcon(@DrawableRes int resId) {
            if (resId != 0) {
                base_dialog_message_layout.setVisibility(View.VISIBLE);
                base_dialog_message_icon.setVisibility(View.VISIBLE);
                base_dialog_message_icon.setBackgroundResource(resId);
            }
            return this;
        }

        /**
         * Set Dialog MessageEvent Icon
         *
         * @param url icon
         * @return
         */
        public Builder setDialogMessageIcon(String url) {
            if (!isEmpty(url)) {
                base_dialog_message_layout.setVisibility(View.VISIBLE);
                base_dialog_message_icon.setVisibility(View.VISIBLE);
                GlideImgManager.loadRoundImg(mContext, url, base_dialog_message_icon);
            }
            return this;
        }

        /**
         * Set Dialog MessageEvent
         *
         * @param msg MessageEvent
         * @return
         */
        public Builder setDialogMessage(String msg) {
            if (!isEmpty(msg)) {
                base_dialog_message_layout.setVisibility(View.VISIBLE);
                base_dialog_msg.setText(msg);
            }
            return this;
        }

        /**
         * Set Dialog MessageEvent
         *
         * @param resId MessageEvent
         * @return
         */
        public Builder setDialogMessage(@StringRes int resId) {
            if (resId != 0) {
                base_dialog_message_layout.setVisibility(View.VISIBLE);
                base_dialog_msg.setText(resId);
            }
            return this;
        }

        /**
         * Set Dialog is DialogCloseImg
         *
         * @return
         */
        public Builder showDialogCloseImg() {
            base_dialog_title_layout.setVisibility(View.VISIBLE);
            base_dialog_close.setVisibility(View.VISIBLE);
            base_dialog_close.setOnClickListener(this);
            return this;
        }

        /**
         * Set Dialog is SystemAlerType
         *
         * @return
         */
        public Builder setDialogSystemAlertType() {
            mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            return this;
        }

        /**
         * Set Dialog count down time
         * @param befoText  befo
         * @param afterText after
         * @return
         */
        public Builder setDialogCountDownTime(String befoText, String afterText) {
            if (!isEmpty(befoText)){
                base_dialog_count_down_time_msg_befo.setText(befoText);
            }
            if (!isEmpty(afterText)){
                base_dialog_count_down_time_msg_after.setText(afterText);
            }
            base_dialog_count_down_time_message_layout.setVisibility(View.VISIBLE);
            return this;
        }


        /**
         * Set count down text color
         * @param befoTextColor befo text color
         * @param afterTextColor after text color
         * @param timeColor time text color
         * @return this
         */
        public Builder setCountDownTimeColor(String befoTextColor,String afterTextColor,String timeColor){
            if (!isEmpty(befoTextColor)){
                base_dialog_count_down_time_msg_befo.setTextColor(Color.parseColor(befoTextColor));
            }
            if (!isEmpty(afterTextColor)){
                base_dialog_count_down_time_msg_after.setTextColor(Color.parseColor(afterTextColor));
            }
            if (!isEmpty(timeColor)){
                base_dialog_count_down_time_msg.setTextColor(Color.parseColor(timeColor));
            }
            return this;
        }

        /**
         * Set count down icon
         * @param iconId iconid
         * @return this
         */
        public Builder setCountDownTimeIcon(@IdRes int iconId){
            if (iconId != 0){
                base_dialog_title_icon.setBackgroundResource(R.mipmap.release_order_time);
            }
            base_dialog_count_down_time_icon.setVisibility(View.VISIBLE);
            return this;
        }



        /**
         * Start count down time
         * @return this
         */
        public Builder startCountDowntime(long time){
            if (base_dialog_count_down_time_message_layout.getVisibility() == View.VISIBLE){
                isSetConfirm = true;
                base_dialog_h_line.setVisibility(View.VISIBLE);
                base_dialog_confirm.setVisibility(View.VISIBLE);
                base_dialog_confirm.setText(DEFAULT_CONFIRM_TEXT);
                startCountDownTime(time);
            }
            return this;
        }

        /**
         * Start count down time
         * @return this
         */
        public Builder startCountDowntime(){
            if (base_dialog_count_down_time_message_layout.getVisibility() == View.VISIBLE){
                isSetConfirm = true;
                base_dialog_h_line.setVisibility(View.VISIBLE);
                base_dialog_confirm.setVisibility(View.VISIBLE);
                base_dialog_confirm.setText(DEFAULT_CONFIRM_TEXT);
                startCountDownTime();
            }
            return this;
        }

        /**
         * Set Dialog Input type
         * @return
         */
        public Builder setDialogInputEditext(OnComponentListener l) {
            setDialogInputEditext(DEFAULT_INPUT_HINT, DEFAULT_INPUT_CHARACTER, InputType.TYPE_CLASS_TEXT, l);
            return this;
        }

        /**
         * Set Dialog Input type
         *
         * @return
         */
        public Builder setDialogInputEditext(String hint, OnComponentListener l) {
            setDialogInputEditext(hint, DEFAULT_INPUT_CHARACTER, InputType.TYPE_CLASS_TEXT, l);
            return this;
        }

        /**
         * Set Dialog Input type
         *
         * @param sumCharacter max character
         * @return
         */
        public Builder setDialogInputEditext(int sumCharacter, OnComponentListener l) {
            if (sumCharacter > 0) {
                setDialogInputEditext(DEFAULT_INPUT_HINT, sumCharacter, InputType.TYPE_CLASS_TEXT, l);
            }
            return this;
        }

        /**
         * Set Dialog Input type
         *
         * @param hint         hint text
         * @param sumCharacter max character
         * @return
         */
        public Builder setDialogInputEditext(String hint, int sumCharacter, OnComponentListener l) {
            setDialogInputEditext(hint, sumCharacter, InputType.TYPE_CLASS_TEXT, l);
            return this;
        }

        /**
         * Set Dialog Input type
         *
         * @param hint         hint text
         * @param sumCharacter max character
         * @return
         */
        public Builder setDialogInputEditext(String hint, int inputType, int sumCharacter, OnComponentListener l) {
            if (!isEmpty(hint) && inputType > 0 && sumCharacter > 0 && l != null) {
                base_dialog_input_edit.setHint(hint);
                base_dialog_input_sum_character.setText(String.valueOf(sumCharacter));
                base_dialog_input_curreent_character.setText(String.valueOf(sumCharacter));
                base_dialog_input_edit.setFilters(new InputFilter[]{new MaxTextLengthFilter(sumCharacter, "")});
                base_dialog_input_edit.setInputType(inputType);
                base_dialog_input_edit.addTextChangedListener(new InputTextWatcher(sumCharacter, base_dialog_input_curreent_character));

                base_dialog_confirm.setVisibility(View.VISIBLE);
                base_dialog_input_layout.setVisibility(View.VISIBLE);
                int inputTypeTextColor = mContext.getResources().getColor(R.color.gray);
//                  base_dialog_msg_pro.setVisibility(View.GONE);
                base_dialog_title.setTextColor(inputTypeTextColor);
                this.listener = l;
            }
            return this;
        }


        /**
         * Set Dialog Confirm button
         * @return
         */
        public Builder setDialogPositiveBtn() {
            setDialogPositiveBtn(null, null,null);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @return
         */
        public Builder setDialogPositiveBtn(String positiveBtnText) {
            setDialogPositiveBtn(positiveBtnText, null,null);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @return
         */
        public Builder setDialogPositiveBtn(@StringRes int positiveBtnText, DialogInterface.OnClickListener listener) {
            setDialogPositiveBtn(mContext.getString(positiveBtnText),listener,0);
            return this;
        }


        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @return
         */
        public Builder setDialogPositiveBtn(String positiveBtnText,String color) {
            setDialogPositiveBtn(positiveBtnText, null,color);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @return
         */
        public Builder setDialogPositiveBtn(String positiveBtnText,@ColorInt int color) {
            setDialogPositiveBtn(positiveBtnText, null,color);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param listener  button listener
         * @return
         */
        public Builder setDialogPositiveBtn(DialogInterface.OnClickListener listener) {
            setDialogPositiveBtn(null,listener,null);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param listener  button listener
         * @return
         */
        public Builder setDialogPositiveBtn(DialogInterface.OnClickListener listener,String color) {
            setDialogPositiveBtn(null,listener,color);
            return this;
        }


        /**
         * Set Dialog Confirm button
         * @param listener  button listener
         * @return
         */
        public Builder setDialogPositiveBtn(DialogInterface.OnClickListener listener,@ColorInt int color) {
            setDialogPositiveBtn(null,listener,color);
            return this;
        }

        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @param listener        button listener
         * @return
         */
        public Builder setDialogPositiveBtn(String positiveBtnText, DialogInterface.OnClickListener listener,@ColorInt int color) {
            base_dialog_confirm.setVisibility(View.VISIBLE);
            base_dialog_confirm.setText(isEmpty(positiveBtnText) ? DEFAULT_CONFIRM_TEXT : positiveBtnText);
            base_dialog_confirm.setTextColor(color < 0 ? Color.parseColor("#000000") : color);
            this.mPositiveBtnClickListener = listener;
            isSetConfirm = true;
            return this;
        }


        /**
         * Set Dialog Confirm button
         * @param positiveBtnText button text
         * @param listener        button listener
         * @return
         */
        public Builder setDialogPositiveBtn(String positiveBtnText, DialogInterface.OnClickListener listener,String color) {
            base_dialog_confirm.setVisibility(View.VISIBLE);
            base_dialog_confirm.setText(isEmpty(positiveBtnText) ? DEFAULT_CONFIRM_TEXT : positiveBtnText);
            base_dialog_confirm.setTextColor(isEmpty(color) ? Color.parseColor("#000000") : Color.parseColor(color));
            this.mPositiveBtnClickListener = listener;
            isSetConfirm = true;
            return this;
        }


        /**
         * Set Dialog cancel button
         * @return
         */
        public Builder setDialogNegativeBtn() {
            setDialogNegativeBtn(null, null,null);
            return this;
        }

        /**
         * Set Dialog cancel button
         *
         * @param negativeBtnText button text
         * @return
         */
        public Builder setDialogNegativeBtn(String negativeBtnText) {
            setDialogNegativeBtn(negativeBtnText, null);
            return this;
        }

        /**
         * Set Dialog cancel button
         * @param listener   button listener
         * @return
         */
        public Builder setDialogNegativeBtn(DialogInterface.OnClickListener listener) {
            setDialogNegativeBtn(null,listener,null);
            return this;
        }

        /**
         * Set Dialog cancel button
         * @param negativeBtnText button text
         * @return
         */
        public Builder setDialogNegativeBtn(String negativeBtnText, @ColorInt int color) {
            setDialogNegativeBtn(negativeBtnText, null,color);
            return this;
        }

        /**
         * Set Dialog cancel button
         *
         * @param negativeBtnText button text
         * @return
         */
        public Builder setDialogNegativeBtn(String negativeBtnText,String color) {
            setDialogNegativeBtn(negativeBtnText, null,color);
            return this;
        }

        /**
         * Set Dialog cancel button
         * @param listener   button listener
         * @return
         */
        public Builder setDialogNegativeBtn(DialogInterface.OnClickListener listener,String color) {
            setDialogNegativeBtn(null,listener,color);
            return this;
        }


        /**
         * Set Dialog cancel button
         * @param listener   button listener
         * @return
         */
        public Builder setDialogNegativeBtn(DialogInterface.OnClickListener listener,@ColorInt int color) {
            setDialogNegativeBtn(null,listener,color);
            return this;
        }

        /**
         * Set Dialog cancel button
         *
         * @param negativeBtnText button text
         * @param listener        button listener
         * @return
         */
        public Builder setDialogNegativeBtn(String negativeBtnText, DialogInterface.OnClickListener listener,@ColorInt int color) {
            base_dialog_cancel.setVisibility(View.VISIBLE);
            base_dialog_cancel.setText(isEmpty(negativeBtnText) ? DEFAULT_CANCEL_TEXT : negativeBtnText);
            base_dialog_cancel.setTextColor(color < 0 ? Color.parseColor("#757575") : color);
            this.mNegativeBtnClickListener = listener;
            isSetCancel = true;
            return this;
        }


        /**
         * Set Dialog cancel button
         *
         * @param negativeBtnText button text
         * @param listener        button listener
         * @return
         */
        public Builder setDialogNegativeBtn(String negativeBtnText, DialogInterface.OnClickListener listener,String color) {
            base_dialog_cancel.setVisibility(View.VISIBLE);
            base_dialog_cancel.setText(isEmpty(negativeBtnText) ? DEFAULT_CANCEL_TEXT : negativeBtnText);
            base_dialog_cancel.setTextColor(isEmpty(color) ? Color.parseColor("#757575") : Color.parseColor(color));
            this.mNegativeBtnClickListener = listener;
            isSetCancel = true;
            return this;
        }

        /**
         * Get input text
         *
         * @return
         */
        private String getInputEditText() {
            return getEditText(base_dialog_input_edit);
        }

        /**
         * Get EditText text
         *
         * @param edt
         * @return
         */
        private String getEditText(EditText edt) {
            return edt.getText().toString().trim();
        }

        public DialogBuilder build() {
            return new DialogBuilder(this);
        }

        private void createDialog() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.base_dialog, null);
            mDialog = new Dialog(mContext, R.style.custom_dialog);
            mDialog.setCanceledOnTouchOutside(false);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
            mDialog.addContentView(view, params);
            //findViewById
            findViewById(view);
            //set listener
            base_dialog_confirm.setOnClickListener(this);
            base_dialog_cancel.setOnClickListener(this);
            //set Default text
            base_dialog_confirm.setText(DEFAULT_CONFIRM_TEXT);
            base_dialog_cancel.setText(DEFAULT_CANCEL_TEXT);
            //set ContentView
            mDialog.setContentView(view);
        }

        /**
         * view findViewById
         * @param view
         */
        private void findViewById(View view) {
            //title layout
            base_dialog_title_layout = view.findViewById(R.id.base_dialog_title_layout);
            //title
            base_dialog_title = view.findViewById(R.id.base_dialog_title);
            //title icon
            base_dialog_title_icon = view.findViewById(R.id.base_dialog_title_icon);
            //close img
            base_dialog_close = view.findViewById(R.id.base_dialog_close);
            //line
            base_dialog_h_line = view.findViewById(R.id.base_dialog_h_line);
            base_dialog_v_line = view.findViewById(R.id.base_dialog_v_line);
            //CountDown time message layout
            base_dialog_count_down_time_message_layout = view.findViewById(R.id.base_dialog_count_down_time_message_layout);
            //Befo CountDown time
            base_dialog_count_down_time_msg_befo = view.findViewById(R.id.base_dialog_count_down_time_msg_befo);
            //CountDown message
            base_dialog_count_down_time_msg = view.findViewById(R.id.base_dialog_count_down_time_msg);
            //After CountDown time
            base_dialog_count_down_time_msg_after = view.findViewById(R.id.base_dialog_count_down_time_msg_after);
            //CountDown icon
            base_dialog_count_down_time_icon = view.findViewById(R.id.base_dialog_count_down_time_icon);
            //message layout
            base_dialog_message_layout = view.findViewById(R.id.base_dialog_message_layout);
            //message pro
//            base_dialog_msg_pro = view.findViewById(R.id.base_dialog_msg_pro);
            //message
            base_dialog_msg = view.findViewById(R.id.base_dialog_msg);
            //message icon
            base_dialog_message_icon = view.findViewById(R.id.base_dialog_message_icon);
            //input layout
            base_dialog_input_layout = view.findViewById(R.id.base_dialog_input_layout);
            //input edit
            base_dialog_input_edit = view.findViewById(R.id.base_dialog_input_edit);
            //curreent character
            base_dialog_input_curreent_character = view.findViewById(R.id.base_dialog_input_curreent_character);
            //sum character
            base_dialog_input_sum_character = view.findViewById(R.id.base_dialog_input_sum_character);
            //confirm
            base_dialog_confirm = view.findViewById(R.id.base_dialog_confirm);
            //cancel
            base_dialog_cancel = view.findViewById(R.id.base_dialog_cancel);
        }

        /**
         * dialog on dismiss
         */
        private void dismiss() {
            stopCountDownTime();
            //得到InputMethodManager的实例
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(base_dialog_input_edit.getWindowToken(), 0);
            }
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        }


        //start count down time
        private void startCountDownTime(long time) {
            timeManager = new CountdownManager(time, base_dialog_count_down_time_msg) {
                @Override
                public void onTimeOver() {
                    onTimeEnd();
                }
            };
            timeManager.start();
        }

        //start count down time
        private void startCountDownTime() {
            timeManager = new CountdownManager(DEFAULT_TIME, base_dialog_count_down_time_msg) {
                @Override
                public void onTimeOver() {
                    onTimeEnd();
                }
            };
            timeManager.start();
        }

        public void onTimeEnd(){
            dismiss();
        }

        //stop count down time
        private void stopCountDownTime() {
            if (timeManager != null){
                timeManager.cancel();
                timeManager = null;
            }
        }


        /**
         * String is Empty
         *
         * @param s String
         * @return true or false
         */
        private boolean isEmpty(String s) {
            if (s == null) {
                return true;
            } else if (s.equals("") && TextUtils.isEmpty(s)) {
                return true;
            }
            return false;
        }


        private OnComponentListener listener;

        public interface OnComponentListener {
            void onComponClick(String input);
        }
    }

    /**
     * Show Dialog
     */
    public DialogBuilder show() {
        if (isSetConfirm && isSetCancel) {
            base_dialog_confirm.setBackgroundResource(R.drawable.confirm_right_bottom_selector);
            base_dialog_cancel.setBackgroundResource(R.drawable.cancel_left_bottom_selector);
        }
        if (isSetConfirm && !isSetCancel) {
            base_dialog_confirm.setBackgroundResource(R.drawable.confirm_selector);
        }
        if (!isSetConfirm && isSetCancel) {
            base_dialog_cancel.setBackgroundResource(R.drawable.cancel_selector);
        }
        if (mDialog != null && !isShowing()) {
            mDialog.show();
        }
        return this;
    }


    /**
     * Dialog is showing
     *
     * @return
     */
    private boolean isShowing() {
        return mDialog.isShowing();
    }


}
