package com.wuxiantao.wxt.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class AudioUtils {
    /**
     *  * 监听系统静音模式
     * <p>
     *  * @param mContext
     * <p>
     *  
     */

    public static void modeIndicater(Context mContext, int res) {
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        final int ringerMode = am.getRingerMode();

        switch (ringerMode) {

            case AudioManager.RINGER_MODE_NORMAL://普通模式

                playFromRawFile(mContext, res);

                break;

            case AudioManager.RINGER_MODE_VIBRATE://静音模式

                break;

            case AudioManager.RINGER_MODE_SILENT://震动模式

                break;

        }

    }

    /**
     *  * 提示音
     * <p>
     *  * @param mContext
     * <p>
     *  
     */

    private static void playFromRawFile(Context mContext, int res) {

        try {

            MediaPlayer player = new MediaPlayer();

            AssetFileDescriptor file = mContext.getResources().openRawResourceFd(res);

            try {

                player.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());

                file.close();

                if (!player.isPlaying()) {

                    player.prepare();

                    player.start();

                }

            } catch (IOException e) {

                player = null;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
