package com.wuxiantao.wxt.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/11/4 0004 15:45 8-19
 * Description: ${DESCRIPTION} 微信分享图片处理工具类
 * Author: Administrator Shiming-Shi
 */

public class WeChatImgUtils {

    private static final int MAX_DECODE_PICTURE_SIZE = 1920 * 1440;


    /**
     * 微信分享图片，缩略图要求32K以内，NOTE: The file size should be within 32KB；
     * 经测试，等于32K也不可以分享
     * @param image
     * @param needRecycle
     * @return
     */
    public static byte[] compressImage2ByteArray(Bitmap image, boolean needRecycle) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        int quality = 100;
        while (bos.toByteArray().length / 1024 >= 32) {
            if (quality <= 0) {
                return bos.toByteArray();
            }
            //重置bos 即清空bos
            bos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            //每次都减少10
            quality -= 10;
        }
        if (needRecycle) {
            image.recycle();
        }
        return bos.toByteArray();
    }


    public static byte[] bmpToByteArray(Bitmap bmp, boolean needRecycle) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        if (needRecycle) {
            bmp.recycle();
        }
        byte result[] = bos.toByteArray();
        try {
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static byte[] getHtmlByteArray(String url) {
        URL htmlUrl = null;
        InputStream is = null;
        try {
            htmlUrl = new URL(url);
            URLConnection connection = htmlUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            int responsedeCode = httpURLConnection.getResponseCode();
            if (responsedeCode == HttpURLConnection.HTTP_OK) {
                is = httpURLConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte data[] = inputStreamToByte(is);
        return data;
    }


    public static byte[] inputStreamToByte(InputStream is) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                bos.write(ch);
            }
            byte imgdata[] = bos.toByteArray();
            bos.close();
            return imgdata;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] readFromFile(String fileName, int offset, int len) {
        if (fileName == null) {
            return null;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        if (len == -1) {
            len = (int) file.length();
        }
        if (offset < 0) {
            return null;
        }
        if (len <= 0) {
            return null;
        }
        if (offset + len > (int) file.length()) {
            return null;
        }
        byte b[] = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            b = new byte[len];
            raf.seek(offset);
            raf.readFully(b);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }


    public static Bitmap extractThumbNail(String path, int height, int width, boolean crop) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            Bitmap tmp = BitmapFactory.decodeFile(path, options);
            if (tmp != null) {
                tmp.recycle();
                tmp = null;
            }
            final double beY = options.outHeight * 1.0 / height;
            final double beX = options.outWidth * 1.0 / width;
            options.inSampleSize = (int) (crop ? (beY > beX ? beX : beY) : (beY < beX ? beX : beY));
            if (options.inSampleSize <= 1) {
                options.inSampleSize = 1;
            }
            // NOTE: out of memory error
            while (options.outHeight * options.outWidth / options.inSampleSize > MAX_DECODE_PICTURE_SIZE) {
                options.inSampleSize++;
            }
            int newHeight = height;
            int newWidth = width;
            if (crop) {
                if (beY > beX) {
                    newHeight = (int) (newWidth * 1.0 * options.outHeight / options.outWidth);
                } else {
                    newWidth = (int) (newHeight * 1.0 * options.outWidth / options.outHeight);
                }
            } else {
                if (beY < beX) {
                    newHeight = (int) (newWidth * 1.0 * options.outHeight / options.outWidth);
                } else {
                    newWidth = (int) (newHeight * 1.0 * options.outWidth / options.outHeight);
                }
            }
            options.inJustDecodeBounds = false;
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            if (bm == null) {
                return null;
            }
            final Bitmap scale = Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
            if (scale != null) {
                bm.recycle();
                bm = scale;
            }
            if (crop) {
                final Bitmap cropped = Bitmap.createBitmap(bm, (bm.getWidth() - width) >> 1, (bm.getHeight() - height) >> 1, width, height);
                if (cropped == null) {
                    return bm;
                }

                bm.recycle();
                bm = cropped;
            }
            return bm;

        } catch (final OutOfMemoryError e) {
            options = null;
        }
        return null;
    }


}
