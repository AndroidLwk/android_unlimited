package com.wuxiantao.wxt.imgloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.enums.CornerType;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GlideImgManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-4 上午9:26
 * Description:${DESCRIPTION} Glide工具类
 */
public class GlideImgManager {

    //站位图
    private static final int ERROR = R.drawable.no_banner;
    //默认宽高
//    private static final int IMG_WIDTH = (int)BaseApplication.getAppContext().getResources().getDimension(R.dimen.dp_100);
//    private static final int IMG_HEIGHT = (int)BaseApplication.getAppContext().getResources().getDimension(R.dimen.dp_100);

    //加载图片
    public static <T> void loadImg(Context context, T url, ImageView img) {
        loadRound(context, url, img, createOptions());
    }


    //加载图片 指定宽高
    public static <T> void loadImg(Context context, T url, ImageView img, int width, int height) {
        loadRound(context, url, img, createOptions().override(width, height));
    }

    //加载圆角矩形图片 使用默认的圆角大小 并默认全部圆角
    public static <T> void loadRoundImg(Context context, T url, ImageView img) {
        loadRound(context, url, img, createOptions().optionalTransform(new GlideRoundTransform(CornerType.ALL)));
    }


    //加载圆角矩形图片 使用默认的圆角大小 设置圆角方向
    public static <T> void loadRoundImg(Context context, T url, CornerType type, ImageView img) {
        loadRound(context, url, img, createOptions().optionalTransform(new GlideRoundTransform(type)));
    }


    //加载圆角矩形图片 使用指定的圆角大小 默认圆角方向为所有
    public static <T> void loadRoundImg(Context context, T url, ImageView img, float radius) {
        loadRound(context, url, img, createOptions().optionalTransform(new GlideRoundTransform(radius)));
    }

    //加载圆角矩形图片 使用指定的圆角大小 指定圆角方向
    public static <T> void loadRoundImg(Context context, T url, ImageView img, CornerType type, float radius) {
        loadRound(context, url, img, createOptions().optionalTransform(new GlideRoundTransform(radius, type)));
    }

    // 图片加载库采用Glide框架
    private static <T> void loadRound(Context context, T url, ImageView img, RequestOptions options) {
        if (!((Activity) context).isFinishing()) {
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(options)
                    .into(img);
        }
    }


    //createOptions
    private static RequestOptions createOptions() {
        //RequestOptions 设置请求参数，通过apply方法设置
        return new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.HIGH)//指定加载的优先级，优先级越高越优先加载，
                .error(ERROR)
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)//设置图片解码格式
                .skipMemoryCache(false)
                .placeholder(null)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//               .optionalTransform(new GlideRoundTransform(CornerType.ALL));
    }

    //加载圆形图片
    public static <T> void loadCircleImg(Context context, T url, ImageView img) {
        if (!((Activity) context).isFinishing()) {
            //RequestOptions 设置请求参数，通过apply方法设置
            RequestOptions options = new RequestOptions()
                    // 但不保证所有图片都按序加载
                    // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                    // 默认为Priority.NORMAL
                    // 如果没设置fallback，model为空时将显示error的Drawable，
                    // 如果error的Drawable也没设置，就显示placeholder的Drawable
                    .priority(Priority.NORMAL)//指定加载的优先级，优先级越高越优先加载，
                    .placeholder(null)
                    .error(ERROR)
//                .centerCrop()
                    .circleCrop()
                    .skipMemoryCache(false)
                    .transform(new GlideCircleTransform());
            // 图片加载库采用Glide框架
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            img.setImageDrawable(resource);
                        }
                    });
        }
    }



}
