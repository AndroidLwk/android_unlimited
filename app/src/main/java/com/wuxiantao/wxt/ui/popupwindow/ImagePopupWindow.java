package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 显示图片对话框
 */
public class ImagePopupWindow extends BasePopupWindow {


    public ImagePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_img);
        }


        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        public Build setImageUrl(String url){
            if (!isEmpty(url)){
                setImageResource(R.id.popup_img_img,url,320,320);
            }
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new ImagePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {

        }


    }

}
