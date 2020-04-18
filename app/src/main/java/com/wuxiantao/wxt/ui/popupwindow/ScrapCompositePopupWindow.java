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
 * Description:${DESCRIPTION} 碎片合成提示框
 */
public class ScrapCompositePopupWindow extends BasePopupWindow {


    public ScrapCompositePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_scrap_composite);
            setOnButtonListener(R.id.popup_scrap_composite_know);
        }

        private OnItemClickListener listener;

        public Build setWindowData(int type,String title,String desc,int dragon_id){
            if (isEmpty(title,desc)){
                return this;
            }
            setText(title,R.id.popup_scrap_composite_title);
            setText(desc,R.id.popup_scrap_composite_desc);
            setDragonBackImg(type,dragon_id);
            return this;
        }

        private void setDragonBackImg(int type,int dragon_id){
            int imgId = 0;
            if (type == 0){
                switch (dragon_id){

                }
            }else {

            }
            setImageResource(R.id.popup_scrap_composite_img,imgId);
        }

        public Build setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new ScrapCompositePopupWindow(this);
        }


        @Override
        public void onClick(int viewId) {
            if (viewId == R.id.popup_scrap_composite_know){
                if (listener != null){
                    listener.onItemClick();
                }
            }
        }

        public interface OnItemClickListener{
            void onItemClick();
        }
    }

}
