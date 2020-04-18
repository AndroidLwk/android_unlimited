package com.wuxiantao.wxt.ui.custom.seekbar;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LotterySeekbar
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-12 下午5:23
 * Description:${DESCRIPTION} 抽奖进度
 */
public class LotterySeekbar extends AppCompatSeekBar {

    public LotterySeekbar(Context context) {
        this(context,null);
    }

    public LotterySeekbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LotterySeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (listener != null){
                    listener.onProgressChanged(seekBar,progress,fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private OnSeekBarChangeListener listener;

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener){
        this.listener = listener;
    }

    public interface OnSeekBarChangeListener{
        void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    }

}
