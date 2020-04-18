package com.wuxiantao.wxt.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.wuxiantao.wxt.BuildConfig;
import com.wuxiantao.wxt.app.BaseApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.wuxiantao.wxt.config.Constant.CRASH_REPORTER_EXTENSION;
import static com.wuxiantao.wxt.config.Constant.DENSITY_FILE_PATH;
import static com.wuxiantao.wxt.config.Constant.HTTP_FILE_PATH;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FileUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 下午4:44
 * Description:${DESCRIPTION} 文件操作工具类
 */
public class FileUtils {

    //获取app在外置SD卡的路径
    public static String getAppDir(Context context, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(isSDCardAvailable() ? getAppExternalStoragePath() : getCachePath(context));
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        return createDirs(path) ? path : null;
    }

    //判断sd卡是否挂载
    private static boolean isSDCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }


    //获取SD下当前APP的目录
    private static String getAppExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append("AndroidNAdaption");
        sb.append(File.separator);
        return sb.toString();
    }

    //获取应用的cache目录
    private static String getCachePath(Context context) {
        File f = context.getCacheDir();
        return f == null ? null : f.getAbsolutePath() + "/";
    }

    //创建文件夹
    private static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    //产生图片的路径，带文件夹和文件名，文件名为当前毫秒数
    public static String generateImgePath(Context context) {
        return getAppDir(context, "icon") + (System.currentTimeMillis()) + ".jpg";
    }

    //裁减根据文件路径获取uri
    public static Uri getImageContentUri(Context context, File file) {
        String filePath = file.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (file.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    //根据uri返回bitmap
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        // 先通过getContentResolver方法获得一个ContentResolver实例，
        // 调用openInputStream(Uri)方法获得uri关联的数据流stream
        // 把上一步获得的数据流解析成为bitmap
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //uri转path
    public static String decodeUriAsPath(Context context, Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.EXTRA_REFRESH_SUPPORTED.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
            if (data == null) {
                data = getImageAbsolutePath(context, uri);
            }
        }
        return data;
    }

    //根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
    private static String getImageAbsolutePath(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                switch (type) {
                    case "image":
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "video":
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "audio":
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        break;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    //返回一张压缩后的图片
    public static Bitmap compressImage(Bitmap image, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        //循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > size) {
            baos.reset();
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        //把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    //在自定义目录创建图片
    public static File outputIamge(Context context, Bitmap bitmap) {
        File outputIamge = new File(generateImgePath(context));
        //创建
        try {
            outputIamge.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fOut = null;

        try {
            fOut = new FileOutputStream(outputIamge);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputIamge;
    }

    //适配小米手机
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getImgPath(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    //保存崩溃信息到手机中
    public static String saveCrashInfoToFile(String path, Throwable ex, String suffix) {
        File dir = new File(path);
        clearFile(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Writer info = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(info);
        ex.printStackTrace(mPrintWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(mPrintWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        mPrintWriter.close();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String nowTime = mSimpleDateFormat.format(new Date());
        //崩溃时间
        sb.append("TIME:").append(nowTime);
        //程序信息
        sb.append("\nAPPLICATION_ID:").append(BuildConfig.APPLICATION_ID);
        sb.append("\nVERSION_CODE:").append(BuildConfig.VERSION_CODE);
        sb.append("\nVERSION_NAME:").append(BuildConfig.VERSION_NAME);
        //是否是DEBUG版本
        sb.append("\nBUILD_TYPE:").append(BuildConfig.BUILD_TYPE);
        //设备信息
        sb.append("\nMODEL:").append(Build.MODEL);
        sb.append("\nRELEASE:").append(Build.VERSION.RELEASE);
        sb.append("\nSDK:").append(Build.VERSION.SDK_INT);
        sb.append("\nMANUFACTURER:").append(Build.MANUFACTURER);
        sb.append("\nEXCEPTION:").append(ex.getLocalizedMessage());
        sb.append("\nSTACK_TRACE:").append(result);
        String fileName = path + nowTime + suffix;
        try {
            FileWriter mFileWriter = new FileWriter(fileName);
            mFileWriter.write(sb.toString());
            mFileWriter.flush();
            mFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    //保存手机屏幕信息
    public static void saveDensityInfoToFile() {
        File dir = new File(DENSITY_FILE_PATH);
        clearFile(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        //当前时间
        String nowTime = mSimpleDateFormat.format(new Date());
        WindowManager wm = (WindowManager)
                BaseApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        int width = dm.widthPixels;
        // 屏幕高度（像素）
        int height = dm.heightPixels;
        // 屏幕密度（0.75 / 1.0 / 1.5）
        float density = dm.density;
        // 屏幕密度dpi（120 / 160 / 240）
        int densityDpi = dm.densityDpi;
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        //时间
        sb.append("TIME:").append(nowTime);
        //程序信息
        sb.append("\nAPPLICATION_ID:").append(BuildConfig.APPLICATION_ID);
        sb.append("\nVERSION_CODE:").append(BuildConfig.VERSION_CODE);
        sb.append("\nVERSION_NAME:").append(BuildConfig.VERSION_NAME);
        //设备信息
        sb.append("\nMODEL:").append(Build.MODEL);
        sb.append("\nRELEASE:").append(Build.VERSION.RELEASE);
        sb.append("\nSDK:").append(Build.VERSION.SDK_INT);
        sb.append("\nMANUFACTURER:").append(Build.MANUFACTURER);

        sb.append("\nScreenWidth(Pixel):").append(width);
        sb.append("\nScreenHeight(Pixel):").append(height);
        sb.append("\nDensity(0.75/1.0/1.5):").append(density);
        sb.append("\nDensityDpi(120/160/240):").append(densityDpi);
        sb.append("\nScreenWidth(dp):").append(screenWidth);
        sb.append("\nScreenHeight(dp):").append(screenHeight);
        String fileName = DENSITY_FILE_PATH + nowTime + CRASH_REPORTER_EXTENSION;
        try {
            FileWriter mFileWriter = new FileWriter(fileName);
            mFileWriter.write(sb.toString());
            mFileWriter.flush();
            mFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存网络请求失败信息
    public static void saveHttpErrorInfoToFile(String errorMsg) {
        File dir = new File(HTTP_FILE_PATH);
        clearFile(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        //当前时间
        String nowTime = mSimpleDateFormat.format(new Date());
        //时间
        sb.append("TIME:").append(nowTime);
        //程序信息
        sb.append("\nAPPLICATION_ID:").append(BuildConfig.APPLICATION_ID);
        sb.append("\nVERSION_CODE:").append(BuildConfig.VERSION_CODE);
        sb.append("\nVERSION_NAME:").append(BuildConfig.VERSION_NAME);
        //设备信息
        sb.append("\nMODEL:").append(Build.MODEL);
        sb.append("\nRELEASE:").append(Build.VERSION.RELEASE);
        sb.append("\nSDK:").append(Build.VERSION.SDK_INT);
        sb.append("\nMANUFACTURER:").append(Build.MANUFACTURER);
        sb.append("\nErrorMsg:").append(errorMsg);
        String fileName = HTTP_FILE_PATH + nowTime + CRASH_REPORTER_EXTENSION;
        try {
            FileWriter mFileWriter = new FileWriter(fileName);
            mFileWriter.write(sb.toString());
            mFileWriter.flush();
            mFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存图片到相册
    public static boolean saveBmp2Gallery(Bitmap bmp, String picName) {
        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;
        boolean isSuccess = false;
        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");
            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            //通过io流的方式来压缩保存图片
            isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null){
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(BaseApplication.getAppContext().getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        BaseApplication.getAppContext().sendBroadcast(intent);
        return isSuccess;
    }


    //保存图片到相册
    public static boolean saveBmp2Gallery(Bitmap bmp) {
        return saveBmp2Gallery(bmp,String.valueOf(System.currentTimeMillis()));
    }

    private static void clearFile(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files == null) {
                return;
            }
            if (files.length > 0) {
                for (File file : files) {
                    clearFile(file);
                }
            }
            dir.delete();
        } else if (dir.exists()) {
            dir.delete();
        }
    }

}
