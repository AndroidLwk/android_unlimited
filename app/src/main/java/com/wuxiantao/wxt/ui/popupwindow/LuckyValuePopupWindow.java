package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;

/**
 * 幸运值弹框
 */
public class LuckyValuePopupWindow extends BasePopupWindow {
    public LuckyValuePopupWindow(BaseBuild build) {
        super(build);
    }

    public static class Build extends BaseBuild {
        public Build(Context context) {
            super(context, R.layout.popupwindow_luckyvalue);
            String str = "幸运值初始值<br /> <font color='#FA5758'>游戏等级/是否会员</font>将影响幸运值的初始值;<br />普通用户初始幸运值为5，会员为15;<br />" +
                    "游戏等级<font color='#FA5758'>0-50获得5点</font>初始幸运值<br />游戏等级<font color='#FA5758'>51-100 获得10点</font>幸运值<br />游戏等级<font color='#FA5758'>101-150 获得20点</font>幸运值<br /><br />" +
                    "如何获取幸运值<br /><font color='#FA5758'>刮卡狂点</font>可增加幸运值，<br />" +
                    "幸运值的用处<br />" +
                    "幸运值越高，开出的卡片越稀有，<font color='#FA5758'>幸运值达到100<br />点时必会开出稀有卡;</font><br />" +
                    "幸运值刷新<br />幸运值将会在用户点击开始分红后进行刷新";
            Spanned spanned = Html.fromHtml(str);
            setText(R.id.tv_luckvalue_help, spanned);
            setOnButtonListener(R.id.sbt_confirm);
        }

        public LuckyValuePopupWindow.Build setWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }

        @Override
        public BasePopupWindow builder() {
            return new LuckyValuePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case R.id.sbt_confirm:
                    dismiss();
                    break;
            }
        }
    }
}
