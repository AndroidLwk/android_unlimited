package com.wuxiantao.wxt.ui.custom.radiobutton;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SoftCheckable
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午9:15
 * Description:${DESCRIPTION}
 */
public interface SoftCheckable {

    void setChecked(boolean checked, boolean isOrientation);

    /**
     * @return The current checked state of the view
     */
    boolean isChecked();

    /**
     * Change the checked state of the view to the inverse of its current state
     */
    void toggle();
}
