package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TakePhotoPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 下午12:11
 * Description:${DESCRIPTION} 个人信息 拍照/相册 设置头像
 */
public class TakePhotoPopupWindow  extends BasePopupWindow {

    protected TakePhotoPopupWindow(Build build){
       super(build);
    }


    public static class Build extends BaseBuild {

        private OnItemClickListener listener;

        public Build(Context context){
            super(context, R.layout.popupwindow_take_photo);
        }

        public Build setOnItemClickListener(OnItemClickListener l){
            setOnButtonListener(
                    R.id.popup_take_photo_album,
                    R.id.popup_take_photo_selfie,
                    R.id.popup_take_photo_cancel);
            if (listener == null){
                this.listener = l;
            }
            return this;
        }




        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_take_photo_album:
                    if (listener != null){
                        listener.onOpenAlbum();
                    }
                    break;
                case R.id.popup_take_photo_selfie:
                    if (listener != null){
                        listener.onTakePhoto();
                    }
                    break;
                case R.id.popup_take_photo_cancel:

                    break;
            }
        }

        @Override
        public BasePopupWindow builder() {
            return new TakePhotoPopupWindow(this);
        }

        public interface OnItemClickListener{
           void onOpenAlbum();
           void onTakePhoto();
       }
    }


}
