package com.wuxiantao.wxt.imgloader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.internal.FastBlur;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BlurTransformation
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-4 上午11:45
 * Description:${DESCRIPTION} Glide 加载 高斯模糊
 */
public class BlurTransformation extends BitmapTransformation {

    private static final int VERSION = 1;
    private static final String ID = "BlurTransformation." + VERSION;
    private static int MAX_RADIUS = 25;
    private static int DEFAULT_DOWN_SAMPLING = 1;
    private int mRadius;
    private int mSampling;

    public BlurTransformation(){
        this(MAX_RADIUS,DEFAULT_DOWN_SAMPLING);
    }
    public BlurTransformation(int radius){
        this(radius,DEFAULT_DOWN_SAMPLING);
    }
    public BlurTransformation(int radius,int sampling){
        this.mRadius = radius;
        this.mSampling = sampling;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        int scaledWidth = width / mSampling;
        int scaledHeight = height / mSampling;
        Bitmap bitmap = pool.get(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap); canvas.scale(1 / (float) mSampling, 1 / (float) mSampling);
        Paint paint = new Paint(); paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(toTransform, 0, 0, paint);
        bitmap = FastBlur.blur(bitmap, mRadius, true);

        return bitmap;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof BlurTransformation &&
                ((BlurTransformation) obj).mRadius == mRadius
                && ((BlurTransformation) obj).mSampling == mSampling;
    }

    @Override
    public int hashCode() {
        return ID.hashCode() + mRadius * 1000 + mSampling * 10;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + mRadius + mSampling).getBytes(CHARSET));
    }
}
