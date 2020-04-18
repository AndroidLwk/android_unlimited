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
 * Description:${DESCRIPTION} 文件下载窗口
 */
public class DownLoadPopupWindow extends BasePopupWindow {


    public DownLoadPopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        public Build(Context context) {
            super(context, R.layout.popupwindow_download);
        }

        public Build setDownloadProgress(String totalSize, String currentSize, String progress){
            setProgress(R.id.popup_download_pb,Integer.valueOf(progress.replace("%","")));
            setText(currentSize,R.id.popup_download_current);
            setText(totalSize,R.id.popup_download_sum);
            setText(progress,R.id.popup_download_progress);
            return this;
        }

        public Build downFinish(){
            this.dismiss();
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new DownLoadPopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {

        }
    }

}
