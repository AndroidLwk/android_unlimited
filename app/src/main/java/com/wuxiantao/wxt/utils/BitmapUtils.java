package com.wuxiantao.wxt.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BitmapUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-4 下午3:12
 * Description:${DESCRIPTION} bitmap工具类
 */
public class BitmapUtils {

    public static Bitmap createBitmapFromByteData(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        // 这里一定要将其设置回false，因为之前我们将其设置成了true
        // 设置inJustDecodeBounds为true后，decodeFile并不分配空间，
        // 即，BitmapFactory解码出来的Bitmap为Null,但可计算出原始图片的长度和宽度
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }


    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }


    public static void setBitmapToImg(Bitmap resource, ImageView img) {
        if (resource == null || img == null){
            return;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            resource.compress(Bitmap.CompressFormat.PNG, 100, baos);
            InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
            //BitmapRegionDecoder newInstance(InputStream is, boolean isShareable)
            //用于创建BitmapRegionDecoder，isBm表示输入流，只有jpeg和png图片才支持这种方式，
            // isShareable如果为true，那BitmapRegionDecoder会对输入流保持一个表面的引用，
            // 如果为false，那么它将会创建一个输入流的复制，并且一直使用它。即使为true，程序也有可能会创建一个输入流的深度复制。
            // 如果图片是逐步解码的，那么为true会降低图片的解码速度。如果路径下的图片不是支持的格式，那就会抛出异常
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(isBm, true);
            final int imgWidth = decoder.getWidth();
            final int imgHeight = decoder.getHeight();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //计算图片要被切分成几个整块，
            // 如果sum=0 说明图片的长度不足3000px，不进行切分 直接添加
            // 如果sum>0 先添加整图，再添加多余的部分，否则多余的部分不足3000时底部会有空白
            int sum = imgHeight / 3000;
            int redundant = imgHeight % 3000;
            List<Bitmap> bitmapList = new ArrayList<>();
            //说明图片的长度 < 3000
            if (sum == 0) {
                //直接加载
                bitmapList.add(resource);
            } else {
                Rect mRect = new Rect();
                //说明需要切分图片
                for (int i = 0; i < sum; i++) {
                    //需要注意：mRect.set(left, top, right, bottom)的第四个参数，
                    //也就是图片的高不能大于这里的4096
                    mRect.set(0, i * 3000, imgWidth, (i + 1) * 3000);
                    Bitmap bm = decoder.decodeRegion(mRect, opts);
                    bitmapList.add(bm);
                }

                //将多余的不足3000的部分作为尾部拼接
                if (redundant > 0) {
                    mRect.set(0, sum * 3000, imgWidth, imgHeight);
                    Bitmap bm = decoder.decodeRegion(mRect, opts);
                    bitmapList.add(bm);
                }

            }
            Bitmap bigbitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
            Canvas bigcanvas = new Canvas(bigbitmap);
            Paint paint = new Paint();
            int iHeight = 0;

            //将之前的bitmap取出来拼接成一个bitmap
            for (int i = 0; i < bitmapList.size(); i++) {
                Bitmap bmp = bitmapList.get(i);
                bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
                iHeight += bmp.getHeight();
                bmp.recycle();
            }
            img.setImageBitmap(bigbitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //去除分享图片黑色边框
    public static Bitmap drawableBitmapOnWhiteBg(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

}
