package com.wuxiantao.wxt.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PhotoUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 下午4:42
 * Description:${DESCRIPTION} 照相/打开手机相册工具类
 */
public class PhotoUtils {

    private Activity mActivity;

    private Fragment mFragment;

    //默认不开启裁剪
    private boolean isTailor = false;

    //裁剪宽高的比例,默认是是 1 ：1
    private int aspectX = 1;
    private int aspectY = 1;

    //裁剪图片的宽高,默认是是 1 ：1
    private int outputX = 350;
    private int outputY = 350;

    //拿到未裁剪相片的回调码（拍照后）
    private static final int CODE_ORIGINAL_PHOTO_CAMERA = 101;

    //拿到未裁剪相片的回调码（选择本地图库后）
    private static final int CODE_ORIGINAL_PHOTO_ALBUM = 102;

    //拿到已裁剪相片的回调码
    private static final int CODE_TAILOR_PHOTO = 103;

    //布尔值，true：在mActivity进行操作 ；false :Fragment操作
    private boolean isActicity;

    //上下文
    private Context mContext;

    //activity
    private Activity tempActivity;

    //FileProvider的主机名：一般是包名+".fileprovider"
    private String FILE_PROVIDER_AUTHORITY = "";

    //临时存储相片地址
    private String imgPath = "";

    //最终得到的Url
    private Uri outputUri = null;

    //是否压缩图片 默认开启压缩图片的
    private boolean isCompressor = true;

    //图片回调接口
    private takePictureCallBackListener takeCallBacklistener;

    //内部权限接口，学习于郭神
    private PermissionListener permissionListener;


    public PhotoUtils(Activity mActivity) {
        this.mActivity = mActivity;
        tempActivity = mActivity;
        isActicity = true;
        mContext = mActivity;
        FILE_PROVIDER_AUTHORITY = mActivity.getPackageName() + ".fileprovider";
    }

    public PhotoUtils(Fragment mFragment) {
        this.mFragment = mFragment;
        isActicity = false;
        mContext = mFragment.getActivity();
        tempActivity = mFragment.getActivity();
        FILE_PROVIDER_AUTHORITY = mFragment.getActivity().getPackageName() + ".fileprovider";
    }


    //开始拍照
    public void startTakeWayByCarema() {
        startOpencamera();
    }

    //开始从图库获取
    public void startTakeWayByAlbum() {
        imgPath = FileUtils.generateImgePath(mContext);
        startAlbum();
    }


    private void startAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_ALBUM);
        } else {
            mFragment.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_ALBUM);
        }
    }


    /**
     * 对外接口，是否裁剪
     * @param aspectX 要裁剪的宽比例
     * @param aspectY 要裁剪的高比例
     * @param outputX 要裁剪图片的宽
     * @param outputY 要裁剪图片的高
     */

    public void setTailor(int aspectX, int aspectY, int outputX, int outputY) {
        isTailor = true;
        this.aspectX = aspectX;
        this.aspectY = aspectY;
        this.outputX = outputX;
        this.outputY = outputY;
    }

    /**
     * 裁剪方法
     * @param srcFile
     * @param output
     */

    private void statZoom(File srcFile, File output) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(FileUtils.getImageContentUri(mContext, srcFile), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_TAILOR_PHOTO);
        } else {
            mFragment.startActivityForResult(intent, CODE_TAILOR_PHOTO);
        }

    }


    /**
     * 获取到的相片回调方法，
     * 必须要在当前的Activity或Fragment中的onActivityResult下调用！
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void attachToActivityForResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            return;
        }
        File temFile = null;
        File srcFile = null;
        File outPutFile = null;
        switch (requestCode) {
            //拍照后得到的图片
            case CODE_ORIGINAL_PHOTO_CAMERA:

                srcFile = new File(imgPath);
                outPutFile = new File(FileUtils.generateImgePath(mContext));
                outputUri = Uri.fromFile(outPutFile);
                Uri imageContentUri = FileUtils.getImageContentUri(mContext, srcFile);

                if (isTailor) {
                    statZoom(srcFile, outPutFile);
                } else {
                    if (takeCallBacklistener != null) {
                        takeCallBacklistener.successful(false, srcFile, imageContentUri);
                    }
                }

                break;

            //选择相册后得到的图片
            case CODE_ORIGINAL_PHOTO_ALBUM:
                if (data != null) {
                    Uri sourceUri = data.getData();
                    String pickPath = FileUtils.getImgPath(mContext, sourceUri);
                    srcFile = new File(pickPath);
                    temFile = srcFile;

                    if (isTailor) {
                        //裁剪之后的文件和ur
                        outPutFile = new File(FileUtils.generateImgePath(mContext));
                        outputUri = Uri.fromFile(outPutFile);
                        //发起裁剪请求
                        statZoom(srcFile, outPutFile);
                    } else {

                        outputUri = Uri.fromFile(srcFile);
                        //如果选择返回一个压缩后的图片
                        if (isCompressor) {
                            temFile = FileUtils.outputIamge(mContext, FileUtils.compressImage(FileUtils.decodeUriAsBitmap(mContext,outputUri), 100));
                            outputUri = Uri.fromFile(temFile);
                        }

                        if (takeCallBacklistener != null) {
                            takeCallBacklistener.successful(true, temFile, outputUri);
                        }
                    }

                } else {
                    if (takeCallBacklistener != null) {
                        takeCallBacklistener.failed(0, null);
                    }
                }
                break;

            //裁剪后的图片：
            case CODE_TAILOR_PHOTO:
                //拿到图片之后，用户可能会舍弃，所以先判断
                if (data != null) {
                    if (outputUri != null) {
                        //如果是拍照的,删除临时文件
                        temFile = new File(imgPath);

                        if (temFile.exists()) {
                            temFile.delete();
                        }

                        //返回一个压缩后的图片
                        if (isCompressor) {
                            temFile = FileUtils.outputIamge(mContext, FileUtils.compressImage(FileUtils.decodeUriAsBitmap(mContext,outputUri), 100));
                            outputUri = Uri.fromFile(temFile);
                        }

                        if (takeCallBacklistener != null) {
                            takeCallBacklistener.successful(true, temFile, outputUri);
                        }

                    }
                } else {
                    if (takeCallBacklistener != null) {
                        takeCallBacklistener.failed(0, null);
                    }
                }

                break;
        }
    }


    //打开相机
    private void startOpencamera() {
        imgPath = FileUtils.generateImgePath(mContext);
        File imgFile = new File(imgPath);
        Uri imgUri = null;

        //判断当前手机版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imgUri = FileProvider.getUriForFile(mContext, FILE_PROVIDER_AUTHORITY, imgFile);
        } else {
            imgUri = Uri.fromFile(imgFile);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);

        //判断当前在哪启动
        if (isActicity) {
            mActivity.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_CAMERA);
        } else {
            mFragment.startActivityForResult(intent, CODE_ORIGINAL_PHOTO_CAMERA);
        }
    }


    public void setTakePictureCallBackListener(takePictureCallBackListener takeCallBacklistener) {
        this.takeCallBacklistener = takeCallBacklistener;
    }


    //得到图片回调接口（内部）
    public interface takePictureCallBackListener {
        /**
         * 成功回调
         * @param isTailor 是否开启了裁剪
         * @param outFile
         * @param filePath
         */
        void successful(boolean isTailor, File outFile, Uri filePath);

        /**
         * 失败回调
         * @param errorCode         错误码  0：图片发生错误  1：被拒绝的权限
         * @param deniedPermissions 被拒绝的权限
         */
        void failed(int errorCode, List<String> deniedPermissions);
    }


    private interface PermissionListener {

        void onGranted();

        void onDenied(List<String> deniedPermissions);
    }

}
