package com.wuxiantao.wxt.adapter.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.imgloader.GlideImgManager;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PreviewImgViewPagerAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-24 下午7:58
 * Description:${DESCRIPTION}
 */
public class PreviewImgViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> imgList;

    public PreviewImgViewPagerAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.imgList = list;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return imgList == null ? 0 : imgList.size();
    }

    //判断当前的View 和 我们想要的Object(值为View) 是否一样;返回 true/false
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    //将当前view添加到ViewGroup中，并返回当前View
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_preview_img, container, false);
        PhotoView img = itemView.findViewById(R.id.item_preview_img_img);
        GlideImgManager.loadImg(mContext, imgList.get(position), img);
        container.addView(itemView);
        return itemView;
    }

    //删除当前的View
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
