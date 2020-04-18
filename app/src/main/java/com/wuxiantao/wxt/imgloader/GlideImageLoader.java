package com.wuxiantao.wxt.imgloader;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GlideImageLoader
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-20 下午6:05
 * Description:${DESCRIPTION}
 */
public class GlideImageLoader extends ImageLoader {

    private float radius;
    private int width = 0;
    private int height = 0;

    public GlideImageLoader(){
        this(0.0f);
    }

    public GlideImageLoader(float radius){
        this.radius = radius;
    }

    public GlideImageLoader(int width,int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void displayImage(Context context,Object path, ImageView imageView) {
        if (width > 0 && height > 0){
            GlideImgManager.loadImg(context,path,imageView,width,height);
        }else {
            GlideImgManager.loadRoundImg(context,path,imageView,radius);
        }
    }


}
