package com.wuxiantao.wxt.task;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.FileUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ImageDownLoadTask
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-30 下午3:26
 * Description:${DESCRIPTION} 图片下载
 */
public class ImageDownLoadTask extends AsyncTask<String,Void,Bitmap> {

    private Bitmap mBitmap;
    @SuppressLint("StaticFieldLeak")
    private ImageView img;
    private boolean isSave;

    public ImageDownLoadTask(ImageView img,boolean isSave){
        this.img = img;
        this.isSave = isSave;
    }


    // 表示任务执行之前的操作
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 主要是完成耗时的操作
    @Override
    protected Bitmap doInBackground(String... strings) {
        String path = strings[0];
        try {
            //打开输入流
            InputStream in = getImageStream(path);
            if (in != null){
                //对网上资源进行下载转换位图图片
                mBitmap = BitmapFactory.decodeStream(in);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mBitmap;
    }

    //主要是更新UI的操作
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (mBitmap != null && img != null){
            img.setImageBitmap(bitmap);
        }
        if (isSave){
            boolean isSuccess = FileUtils.saveBmp2Gallery(bitmap);
            if (isSuccess){
                ToastUtils.success(BaseApplication.getAppContext().getString(R.string.save_img_success));
            }
        }
    }

    private static InputStream getImageStream(String path) throws IOException {
        URL url = new URL(path);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
            return connection.getInputStream();
        }
       return null;
    }
}
