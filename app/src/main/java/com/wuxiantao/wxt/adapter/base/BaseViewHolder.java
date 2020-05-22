package com.wuxiantao.wxt.adapter.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.progress.CircleProgressBar;
import com.wuxiantao.wxt.ui.custom.switchbutton.SwitchButton;
import com.wuxiantao.wxt.ui.custom.textview.BuyZeroTagTextView;
import com.wuxiantao.wxt.ui.custom.textview.RuleTagTextView;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.utils.TextViewUtils;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseViewHolder
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 上午8:58
 * Description:${DESCRIPTION}
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    //内存中缓存的view
    private SparseArray<View> views;
    private Context mContext;
    private View mView;

    protected BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    public static BaseViewHolder create(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return create(context, itemView);
    }

    public static BaseViewHolder create(Context context, View itemView) {
        return new BaseViewHolder(context, itemView);
    }

    public View getRootView() {
        return itemView;
    }

    //通过viewId获取控件
    public <T extends View> T findView(int viewId) {
        if (views == null) {
            views = new SparseArray<>();
        }
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    //为TextView设置字符串
    public BaseViewHolder setText(int viewId, CharSequence text) {
        TextView view = findView(viewId);
        if (view != null) {
            view.setText(text == null ? "" : text);
        }
        return this;
    }

    //为TextView设置字符串
    public BaseViewHolder setText(int viewId, String text) {
        TextView view = findView(viewId);
        if (view != null) {
            if (isEmpty(text)) {
                view.setText(RESOURCES.getString(R.string._zero));
            } else {
                view.setText(text);
            }
        }
        return this;
    }

    //为TextView设置字符串
    public BaseViewHolder setCircleProgress(int viewId, int progress) {
        CircleProgressBar view = findView(viewId);
        if (view != null) {
            view.setProgress(progress);
        }
        return this;
    }

    //为TextView设置字符串
    public BaseViewHolder setText(int viewId, @StringRes int text) {
        TextView view = findView(viewId);
        if (view != null && text != 0) {
            view.setText(text);
        }
        return this;
    }


    //设置textview颜色
    public BaseViewHolder setTextColor(int viewId, @ColorInt int color) {
        TextView view = findView(viewId);
        if (view != null) {
            view.setTextColor(color);
        }
        return this;
    }

    //设置textview颜色
    public BaseViewHolder setTextColor(int viewId, String color) {
        TextView view = findView(viewId);
        if (view != null && color != null) {
            view.setTextColor(Color.parseColor(color));
        }
        return this;
    }

    //设置textview支持超链接
    public BaseViewHolder setTextLink(int viewId) {
        TextView view = findView(viewId);
        if (view != null) {
            Linkify.addLinks(view, Linkify.ALL);
        }
        return this;
    }

    //设置textview支持超链接
    public BaseViewHolder setTextLink(int viewId, String url, CharSequence source) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextLink(view, url, source);
        }
        return this;
    }


    //设置背景色高亮
    public BaseViewHolder setBackgroundHightLight(int viewId, CharSequence source) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextViewBgHightLight(view, source);
        }
        return this;
    }

    //设置关键字颜色高亮
    public BaseViewHolder setKeyWordHightLight(int viewId, CharSequence source, String regex) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextViewKeyWordHightLight(view, source, regex);
        }
        return this;
    }

    //设置textview的下划线
    public BaseViewHolder setTextUnderLine(int viewId, CharSequence source) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextViewUnderLine(view, source);
        }
        return this;
    }

    //设置textview的删除线
    public BaseViewHolder setTextDeleteLine(int viewId, CharSequence source) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextViewDeleteLine(view, source);
        }
        return this;
    }

    //设置textview的首行缩进
    public BaseViewHolder setTextIndentation(int viewId, CharSequence source) {
        TextView view = findView(viewId);
        if (view != null) {
            TextViewUtils.setTextViewIndentation(view, source);
        }
        return this;
    }

    //设置textview的Typeface
    public BaseViewHolder setTextTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = findView(viewId);
            if (view != null) {
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
        }
        return this;
    }


    //设置button的文本
    public BaseViewHolder setBtnText(int viewId, CharSequence text) {
        Button button = findView(viewId);
        if (button != null) {
            button.setText(text == null ? "" : text);
        }
        return this;
    }

    //设置button的文本
    public BaseViewHolder setBtnText(int viewId, @StringRes int resId) {
        Button button = findView(viewId);
        if (button != null) {
            button.setText(resId);
        }
        return this;
    }

    //设置button的文本
    public BaseViewHolder setBtnText(int viewId, String text) {
        Button button = findView(viewId);
        if (button != null) {
            button.setText(text);
        }
        return this;
    }

    //为ImageView设置图片
    public BaseViewHolder setImageResource(int viewId, @DrawableRes int resId) {
        ImageView img = findView(viewId);
        if (img != null && resId != 0) {
            img.setImageResource(resId);
        }
        return this;
    }

    //为ImageView设置图片
    public BaseViewHolder setImageResource(int viewId, Bitmap bm) {
        ImageView img = findView(viewId);
        if (img != null) {
            img.setImageBitmap(bm);
        }
        return this;
    }

    //为ImageView设置圆形图片
    public <T> BaseViewHolder setImageDrawable(int viewId) {
        ImageView img = findView(viewId);
        if (img != null) {
            img.setImageDrawable(null);
        }
        return this;
    }

    //为ImageView设置圆形图片
    public <T> BaseViewHolder setGlide(int viewId, T url) {
        ImageView img = findView(viewId);
        if (img != null) {
            GlideImgManager.loadInitImg(mContext, url, img);
        }
        return this;
    }

    //为ImageView设置圆形图片
    public <T> BaseViewHolder setCircleImageResource(int viewId, T url) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (url == null) {
                GlideImgManager.loadCircleImg(mContext, R.mipmap.default_user_head_img, img);
            } else {
                GlideImgManager.loadCircleImg(mContext, url, img);
            }
        }
        return this;
    }

    //为ImageView设置圆形图片
    public <T> BaseViewHolder setCircleImageResource(int viewId, String url) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                GlideImgManager.loadCircleImg(mContext, R.mipmap.default_user_head_img, img);
            } else {
                GlideImgManager.loadCircleImg(mContext, url, img);
            }
        }
        return this;
    }

    //为ImageView设置圆角矩形图片
    public BaseViewHolder setRoundImageResource(int viewId, String url) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                //加载默认头像
                GlideImgManager.loadRoundImg(mContext, R.mipmap.default_user_head_img, img);
            } else {
                GlideImgManager.loadRoundImg(mContext, url, img);
            }
        }
        return this;
    }

    //为ImageView设置圆角矩形图片
    public BaseViewHolder setRoundImageResource(int viewId, String url, int width, int height) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                //加载默认头像
                GlideImgManager.loadImg(mContext, R.drawable.no_banner, img, width, height);
            } else {
                GlideImgManager.loadImg(mContext, url, img, width, height);
            }
        }
        return this;
    }

    //为ImageView设置圆角矩形图片
    public BaseViewHolder setRoundImageResource(int viewId, String url, int errorId) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                //加载默认图片
                GlideImgManager.loadRoundImg(mContext, errorId, img);
            } else {
                GlideImgManager.loadRoundImg(mContext, url, img);
            }
        }
        return this;
    }

    //为ImageView设置圆角矩形图片
    public BaseViewHolder setRoundImageResource(int viewId, String url, int errorId, float radius) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                //加载默认图片
                GlideImgManager.loadRoundImg(mContext, errorId, img, radius);
            } else {
                GlideImgManager.loadRoundImg(mContext, url, img, radius);
            }
        }
        return this;
    }

    //为ImageView设置圆角矩形图片
    public BaseViewHolder setRoundImageResource(int viewId, String url, float radius) {
        ImageView img = findView(viewId);
        if (img != null) {
            if (isEmpty(url)) {
                //加载默认头像
                GlideImgManager.loadRoundImg(mContext, R.mipmap.default_user_head_img, img, radius);
            } else {
                GlideImgManager.loadRoundImg(mContext, url, img, radius);
            }
        }
        return this;
    }

    //为ImageView设置图片颜色
    public BaseViewHolder setImageColorFilter(int viewId, @ColorInt int color) {
        ImageView img = findView(viewId);
        if (img != null) {
            img.setColorFilter(color);
        }
        return this;
    }

    //为ImageView设置图片颜色
    public BaseViewHolder setImageColorFilter(int viewId, String color) {
        ImageView img = findView(viewId);
        if (img != null) {
            img.setColorFilter(Color.parseColor(color));
        }
        return this;
    }

    //为ImageView设置图片
    public BaseViewHolder setImageResource(int viewId, Uri uri) {
        ImageView img = findView(viewId);
        if (img != null) {
            img.setImageURI(uri);
        }
        return this;
    }


    //为View设置点击事件
    public BaseViewHolder setViewOnClickListener(int viewId, View.OnClickListener l) {
        View view = findView(viewId);
        if (view != null) {
            view.setOnClickListener(l);
        }
        return this;
    }


    //setOnCheckedChangeListener
    public BaseViewHolder setOnCheckedChangeListener(int viewId, SwitchButton.OnCheckedChangeListener listener) {
        SwitchButton view = findView(viewId);
        if (view != null && listener != null) {
            view.setOnCheckedChangeListener(listener);
        }
        return this;
    }

    //为View设置长按事件
    public BaseViewHolder setViewOnLongClickListener(int viewId, View.OnLongClickListener l) {
        View view = findView(viewId);
        if (view != null && l != null) {
            view.setOnLongClickListener(l);
        }
        return this;
    }

    public BaseViewHolder setLabelViewList(int viewId, List<String> labels) {
        LabelsView labelsView = findView(viewId);
        if (labelsView != null) {
            labelsView.setLabels(labels);
        }
        return this;
    }

    public BaseViewHolder setLabelViewList(int viewId, List<String> labels, LabelsView.OnLabelSelectChangeListener l) {
        LabelsView labelsView = findView(viewId);
        if (l != null && labelsView != null) {
            labelsView.setLabels(labels);
            labelsView.setOnLabelSelectChangeListener(l);
        } else {
            setLabelViewList(viewId, labels);
        }
        return this;
    }

    //为View设置背景色
    public BaseViewHolder setViewBackGroundColor(int viewId, @ColorInt int color) {
        View view = findView(viewId);
        if (view != null && color > 0) {
            if (view instanceof StateButton) {
                StateButton btn = (StateButton) view;
                btn.setEnabled(false);
                btn.setUnableBackgroundColor(color);
            } else {
                view.setBackgroundColor(color);
            }
        }
        return this;
    }

    //为View设置背景色
    public BaseViewHolder setViewBackGroundColor(int viewId, String color) {
        View view = findView(viewId);
        if (view != null && !isEmpty(color)) {
            if (view instanceof StateButton) {
                StateButton btn = (StateButton) view;
                btn.setEnabled(false);
                btn.setUnableBackgroundColor(Color.parseColor(color));
            } else {
                view.setBackgroundColor(Color.parseColor(color));
            }
        }
        return this;
    }

    //为View设置背景色
    public BaseViewHolder setViewBackGroundResources(int viewId, @DrawableRes int resid) {
        View view = findView(viewId);
        if (view != null) {
            if (view instanceof StateButton) {
                StateButton btn = (StateButton) view;
                btn.setUnableBackgroundColor(resid);
            } else {
                view.setBackgroundResource(resid);
            }
        }
        return this;
    }

    //设置View是否可选
    public BaseViewHolder setViewChecked(int viewId, boolean checked) {
        Checkable view = findView(viewId);
        if (view != null) {
            view.setChecked(checked);
        }
        return this;
    }

    //
    public BaseViewHolder setDrawableLeftRes(int viewId, int res) {
        TextView view = findView(viewId);
        Drawable drawable = mContext.getResources().getDrawable(res);
        //这一步必须要做, 否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable, null, null, null);
        return this;
    }

    public BaseViewHolder setDrawableTopRes(int viewId, int res) {
        TextView view = findView(viewId);
        Drawable drawable = mContext.getResources().getDrawable(res);
        //这一步必须要做, 否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, drawable, null, null);
        return this;
    }

    //获取check状态
    public boolean getViewChecked(int viewId) {
        Checkable view = findView(viewId);
        if (view != null) {
            return view.isChecked();
        }
        return false;
    }

    //设置进度条
    public BaseViewHolder setProgress(int viewId, int progress) {
        if (viewId != 0) {
            ProgressBar view = findView(viewId);
            if (view != null) {
                view.setProgress(progress);
            }
        }
        return this;
    }


    //设置View不可点击
    public BaseViewHolder setViewEnabled(int viewId, boolean enabled) {
        View view = findView(viewId);
        if (view != null) {
            view.setEnabled(enabled);
        }
        return this;
    }

    //获取SwitchButton选择状态
    public BaseViewHolder setOnCheckedListener(int viewId, SwitchButton.OnCheckedChangeListener listener) {
        View view = findView(viewId);
        if (view instanceof SwitchButton) {
            SwitchButton sb = (SwitchButton) view;
            if (listener != null) {
                sb.setOnCheckedChangeListener(listener);
            }
        }
        return this;
    }

    //设置View是否可见
    public BaseViewHolder setVisibility(int viewId, int visibility) {
        if (viewId != 0) {
            View view = findView(viewId);
            if (view != null) {
                view.setVisibility(visibility);
            }
        }
        return this;
    }

    public BaseViewHolder setViewSize(int viewId, int widthpx, int heightpx) {
        CircleProgressBar view = findView(viewId);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams();

        linearParams.width = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthpx, mContext.getResources().getDisplayMetrics()));
        linearParams.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightpx, mContext.getResources().getDisplayMetrics()));
        view.setLayoutParams(linearParams);
        return this;
    }

    //设置View四周间距
    public BaseViewHolder setMargin(int viewId, int px) {
        View view = findView(viewId);
        if (view != null) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(px, px, px, px);
        }
        return this;
    }

    //获取View是否可见
    public boolean getVisibility(int viewId) {
        View view = findView(viewId);
        if (view != null) {
            return view.getVisibility() == View.VISIBLE;
        }
        return false;
    }

    //设置recyclerView
    public BaseViewHolder setRecyclerView(int viewId, RcvViewParameter parameter) {
        RecyclerView view = findView(viewId);
        if (view != null && parameter != null) {
            RecyclerView.Adapter adapter = parameter.getAdapter();
            RecyclerView.LayoutManager manager = parameter.getManager();
            RecyclerView.ItemDecoration decoration = parameter.getDecoration();
            if (manager != null) {
                view.setLayoutManager(manager);
            }
            if (decoration != null) {
                view.addItemDecoration(decoration);
            }
            if (adapter != null) {
                view.setAdapter(adapter);
            }
        }
        return this;
    }


    public BaseViewHolder setRuleTagText(int viewId, String content, int index) {
        RuleTagTextView tv = findView(viewId);
        if (tv != null && !isEmpty(content)) {
            tv.setContentAndTag(content, index);
        }
        return this;
    }

    public BaseViewHolder setBuyZeroTagText(int viewId, String content, int type) {
        BuyZeroTagTextView tv = findView(viewId);
        if (tv != null && !isEmpty(content)) {
            tv.setContentAndTag(content, type);
        }
        return this;
    }


    public BaseViewHolder setTaoBaoTagText(int viewId, String content) {
        setTaoBaoTagText(viewId, content, YOUXUAN);
        return this;
    }

    public BaseViewHolder setTaoBaoTagText(int viewId, String content, String type) {
        TaoBaoTagTextView tv = findView(viewId);
        if (tv != null && !isEmpty(content)) {
            switch (type) {
                case TIANMAO:
                    tv.setContentAndTag(content, TaoBaoTagTextView.TYPE_TIANMAO);
                    break;
                case TAOBAO:
                    tv.setContentAndTag(content, TaoBaoTagTextView.TYPE_TAOBAO);
                    break;
                case YOUXUAN:
                    tv.setContentAndTag(content, TaoBaoTagTextView.TYPE_YOUXUAN);
                    break;
                case SELF:
                    tv.setContentAndTag(content, TaoBaoTagTextView.TYPE_SELF);
                    break;
            }
        }
        return this;
    }


    private boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }

    private static final String TIANMAO = "天猫";
    private static final String TAOBAO = "淘宝";
    private static final String YOUXUAN = "优选";
    private static final String SELF = "自营";
}
