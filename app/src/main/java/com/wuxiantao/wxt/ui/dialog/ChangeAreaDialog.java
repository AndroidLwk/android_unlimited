package com.wuxiantao.wxt.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.ComplaintSortBean;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.layout.XCDropDownLayout;
import com.wuxiantao.wxt.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ChangeAreaDialog
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-7 下午7:28
 * Description:${DESCRIPTION} 切换游戏区
 */
public class ChangeAreaDialog {

    //Dialog
    private Dialog mDialog;

    public ChangeAreaDialog(Builder builder){
        this.mDialog = builder.mDialog;
    }

    public static class Builder implements View.OnClickListener {

        //Context
        private Context mContext;

        //Dialog
        private Dialog mDialog;

        private ImageView close;
        private StateButton cancel;
        private StateButton confirm;
        private onAreaSelectListener listener;
        private int id = -1;
        private XCDropDownLayout layout;

        public Builder setOnAreaSelectListener(onAreaSelectListener l){
            this.listener = l;
            return this;
        }


        public Builder(Context context) {
            this.mContext = context;
            createDialog();
        }

        public Builder setSelectList(List<AreaChangeInfoBean.ListBean> datas){
            if (datas == null || datas.isEmpty()){
                return this;
            }
            List<ComplaintSortBean> list = new ArrayList<>();
            ComplaintSortBean first = new ComplaintSortBean();
            first.setId(0);
            first.setName(mContext.getString(R.string.please_big_area));
            list.add(first);
            for (AreaChangeInfoBean.ListBean bean : datas){
                list.add(new ComplaintSortBean(bean.getId(),bean.getName()));
            }

            layout.setItemsData(list);
            layout.setOnDropdowSelectedListener(i -> id = i);
            return this;
        }


        private void createDialog() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_change_area, null);
            mDialog = new Dialog(mContext, R.style.custom_dialog);
            mDialog.setCanceledOnTouchOutside(false);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
            mDialog.addContentView(view, params);
            //findViewById
            findViewById(view);
            //set listener
            close.setOnClickListener(this);
            cancel.setOnClickListener(this);
            confirm.setOnClickListener(this);
            //set ContentView
            mDialog.setContentView(view);
        }


        private void findViewById(View view) {
            close = view.findViewById(R.id.popup_change_area_close);
            cancel = view.findViewById(R.id.popup_change_area_cancel);
            confirm = view.findViewById(R.id.popup_change_area_id_confirm);
            layout = view.findViewById(R.id.popup_change_area_select);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.popup_change_area_close:
                case R.id.popup_change_area_cancel:
                    if (layout != null){
                        layout.closePopWindow();
                    }
                    dismiss();
                    break;
                case R.id.popup_change_area_id_confirm:
                    if (listener != null && id > 0){
                        listener.onAreaSelected(id);
                        dismiss();

                    }else {
                        ToastUtils.warning(mContext.getString(R.string.please_big_area));
                    }
                    break;
            }
        }

        public interface onAreaSelectListener{
            void onAreaSelected(int area_id);
        }

        public ChangeAreaDialog build() {
            return new ChangeAreaDialog(this);
        }

        private void dismiss() {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        }
    }



    public ChangeAreaDialog show() {
        if (mDialog != null && !isShowing()) {
            mDialog.show();
        }
        return this;
    }

    private boolean isShowing() {
        return mDialog.isShowing();
    }
}
