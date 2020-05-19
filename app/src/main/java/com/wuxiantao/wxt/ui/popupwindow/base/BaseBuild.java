package com.wuxiantao.wxt.ui.popupwindow.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.timer.CountdownManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseBuild
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 下午2:30
 * Description:${DESCRIPTION}
 */
public abstract class BaseBuild implements View.OnClickListener, View.OnKeyListener, AbsPopupWindow.OnWindowDismissListener {

    protected Activity mActivity;
    protected Context mContext;
    protected AbsPopupWindow mPopupWindow;
    protected View mView;
    private OnKeyBackListener mOnKeyBackListener;
    private CountdownManager mCountdownManager;
    //是否点击消失对话框 默认点击消失
    private boolean isClickDisappear;

    public BaseBuild(Context context, @LayoutRes int layoutId) {
        this(context, layoutId, true);
    }

    public BaseBuild(Context context, @LayoutRes int layoutId, boolean isClickDisappear) {
        this.mContext = context;
        this.mActivity = (Activity) mContext;
        this.isClickDisappear = isClickDisappear;
        init(layoutId);
    }

    private void init(@LayoutRes int layoutId) {
        if (mContext != null && layoutId != 0) {
            mView = LayoutInflater.from(mContext).inflate(layoutId, null);
            //new PopupWindow
            mPopupWindow = new AbsPopupWindow(mContext);
            //设置宽度
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置高度
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            //setContentView
            mPopupWindow.setContentView(mView);
            mPopupWindow.setOnWindowDismissListener(this);
            //返回建监听
            mView.setOnKeyListener(this);
        }
    }

    @Override
    public void onWindowDismiss() {

    }

    //软键盘：隐藏
    protected void hideKeyboard(@IdRes int id) {
        EditText edt = findViewById(id);
        if (edt != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
        }
    }

    // 6.0以上无法拦截返回事件 因为PopupDecorView(继承于FrameLayout)设置了dispatchKeyEvent,
    // 并且 return true,消费了点击事件,所以不会分发给设置的onkeyListner,因此设置监听无效
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if (mOnKeyBackListener != null) {
                mOnKeyBackListener.onKeyBack();
            }
            if (isClickDisappear) {
                dismiss();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        onClick(v.getId());
        if (isClickDisappear) {
            dismiss();
        }
    }

    //设置setRcvConfig
    protected <M extends RecyclerView.LayoutManager, I extends RecyclerView.ItemDecoration,
            A extends RcvBaseAdapter> BaseBuild setRcvConfig(int viewId, M manager, I itemDecoration, A adapter) {
        RecyclerView rv = findViewById(viewId);
        if (rv != null) {
            rv.setLayoutManager(manager);
            rv.addItemDecoration(itemDecoration);
            rv.setAdapter(adapter);
        }
        return this;
    }


    //开始倒计时
    protected BaseBuild startCountDownTime(int viewId, long millisInFuture, OnCountDownCallBack callBack) {
        if (callBack == null) {
            return this;
        }
        if (mCountdownManager == null) {
            mCountdownManager = new CountdownManager(millisInFuture, findViewById(viewId)) {
                @Override
                public void onTimeOver() {
                    callBack.onTimeEnd();
                }
            };
            mCountdownManager.start();
        }
        return this;
    }

    public interface OnCountDownCallBack {
        void onTimeEnd();
    }

    //停止倒计时
    protected BaseBuild stopCountDownTime() {
        if (mCountdownManager != null) {
            mCountdownManager.cancel();
            mCountdownManager = null;
        }
        return this;
    }

    //返回监听
    protected BaseBuild setOnKeyBackListener(OnKeyBackListener listener) {
        this.mOnKeyBackListener = listener;
        return this;
    }

    //显示或隐藏控件
    protected BaseBuild setViewVisibility(int viewId, int visibility) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
        return this;
    }

    protected int getViewVisibility(int viewId) {
        View view = findViewById(viewId);
        if (view != null) {
            return view.getVisibility();
        }
        return 0x00000008;
    }

    //设置按钮是否可点击
    protected BaseBuild setButtonEnabled(int viewId, boolean enabled) {
        Button btn = findViewById(viewId);
        if (btn != null) {
            btn.setEnabled(enabled);
        }
        return this;
    }


    //设置动画
    protected BaseBuild setPopupWindowAnimStyle(int animationStyle) {
        mPopupWindow.setAnimationStyle(animationStyle);
        return this;
    }

    //设置宽度
    protected BaseBuild setPopupWindowWidth(int width) {
        mPopupWindow.setWidth(width);
        return this;
    }

    //设置高度
    protected BaseBuild setPopupWindowHeight(int height) {
        mPopupWindow.setHeight(height);
        return this;
    }

    //设置标签
//    protected BaseBuild setLablesViewList(int vewiId,List<String> lables){
//        setLablesViewList(vewiId,lables,null);
//        return this;
//    }

    //设置标签
//    protected BaseBuild setLablesViewList(int vewiId,List<String> lables,LabelsView.OnLabelClickListener clickListener){
//        LabelsView labelsView = findViewById(vewiId);
//        if (labelsView != null){
//            labelsView.setLabels(lables);
//            if (clickListener != null){
//                labelsView.setOnLabelClickListener(clickListener);
//            }
//        }
//        return this;
//    }


    //获取文本内容
    protected String getTextString(int resId) {
        TextView tv = findViewById(resId);
        if (tv != null) {
            String s = tv.getText().toString().trim();
            return isEmpty(s) ? "null" : s;
        }
        return "null";
    }

    //获取文本内容
    protected boolean getChecked(int resId) {
        CheckBox cb = findViewById(resId);
        if (cb != null) {
            return cb.isChecked();
        }
        return false;
    }

    public String getString(@StringRes int id) {
        return mContext.getResources().getString(id);
    }

    public String getString(@StringRes int id, Object... formatArgs) {
        return mContext.getResources().getString(id, formatArgs);
    }


    //设置背景颜色
    protected BaseBuild setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }

    //加载图片
    protected BaseBuild setImageResource(@IdRes int viewId, @DrawableRes int resid) {
        ImageView img = findViewById(viewId);
        if (img != null && resid != 0) {
            img.setImageResource(resid);
        }
        return this;
    }

    //加载圆角矩形图片
    protected <T> BaseBuild setRoundImageResource(@IdRes int viewId, T url) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            GlideImgManager.loadRoundImg(mContext, url, img);
        }
        return this;
    }

    //加载图片
    protected <T> BaseBuild setImageResource(@IdRes int viewId, T url, int w, int h) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            GlideImgManager.loadImg(mContext, url, img, w, h);
        }
        return this;
    }


    //加载圆角矩形图片
    protected <T> BaseBuild setRoundImageResource(@IdRes int viewId, float radius, T url) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            GlideImgManager.loadRoundImg(mContext, url, img, radius);
        }
        return this;
    }

    //加载圆形图片
    protected <T> BaseBuild setCircleImageResource(@IdRes int viewId, T url) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            GlideImgManager.loadCircleImg(mContext, url, img);
        }
        return this;
    }

    //加载圆形图片
    protected <T> BaseBuild setCircleImageResource(@IdRes int viewId, String url) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                GlideImgManager.loadCircleImg(mContext, R.mipmap.default_user_head_img, img);
            } else {
                GlideImgManager.loadCircleImg(mContext, url, img);
            }
        }
        return this;
    }

    //为ImageView设置圆形图片
    public <T> BaseBuild setGlide(int viewId, T url) {
        ImageView img = findViewById(viewId);
        if (img != null) {
            GlideImgManager.loadInitImg(mContext, url, img);
        }
        return this;
    }

    //设置文本
    protected BaseBuild setText(@StringRes int resid, @IdRes int viewId) {
        if (resid != 0) {
            TextView tv = findViewById(viewId);
            if (tv != null) {
                tv.setText(resid);
            }
        }

        return this;
    }

    //设置文本2
    protected BaseBuild setText(@IdRes int viewId, Spanned spanned) {
        if (viewId != 0) {
            TextView tv = findViewById(viewId);
            if (tv != null) {
                tv.setText(spanned);
            }
        }

        return this;
    }


    //设置文本
    protected BaseBuild setText(SpannableString ss, @IdRes int viewId) {
        if (ss != null) {
            TextView tv = findViewById(viewId);
            if (tv != null) {
                tv.setText(ss);
            }
        }
        return this;
    }

    //设置文本
    protected BaseBuild setText(String str, @IdRes int viewId) {
        if (!isEmpty(str)) {
            TextView tv = findViewById(viewId);
            if (tv != null) {
                tv.setText(str);
            }
        }
        return this;
    }

    //设置按钮文本
    protected BaseBuild setButtonText(String str, int viewId) {
        if (!isEmpty(str)) {
            Button btn = findViewById(viewId);
            if (btn != null) {
                btn.setText(str);
            }
        }
        return this;
    }

    //设置按钮文本
    protected BaseBuild setButtonText(@StringRes int resid, @IdRes int viewId) {
        if (resid != 0) {
            Button btn = findViewById(viewId);
            if (btn != null) {
                btn.setText(resid);
            }
        }

        return this;
    }

    //设置进度条进度
    protected BaseBuild setProgress(@IdRes int viewId, int progress) {
        if (viewId != 0) {
            ProgressBar bar = findViewById(viewId);
            if (bar != null) {
                bar.setProgress(progress);
            }
        }

        return this;
    }

    //设置按钮点击事件
    protected BaseBuild setOnButtonListener(@IdRes int... viewIds) {
        if (viewIds.length > 0) {
            for (int id : viewIds) {
                View view = findViewById(id);
                if (view != null) {
                    view.setOnClickListener(this);
                }
            }
        }

        return this;
    }


    //findview
    protected <T extends View> T findViewById(@IdRes int viewId) {
        if (viewId != 0 && mView != null) {
            return (T) mView.findViewById(viewId);
        }
        return null;
    }

    protected boolean isEmpty(String str) {
        return str == null || TextUtils.isEmpty(str);
    }

    protected boolean isEmpty(String... str) {
        if (str.length <= 0) {
            return false;
        }
        for (String s : str) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    protected void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            stopCountDownTime();
            mPopupWindow.dismiss();
            backGroundAlpha(1.0f);
            mPopupWindow = null;
        }
    }

    //设置背景透明度
    private void backGroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams wl = mActivity.getWindow().getAttributes();
        // 0.0 - 1.0
        wl.alpha = bgAlpha;
        mActivity.getWindow().setAttributes(wl);
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public interface OnKeyBackListener {
        void onKeyBack();
    }

    public abstract BasePopupWindow builder();

    public abstract void onClick(int viewId);
}
