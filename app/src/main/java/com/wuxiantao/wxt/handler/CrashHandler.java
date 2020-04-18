package com.wuxiantao.wxt.handler;

import android.content.Context;
import android.os.Environment;

import com.wuxiantao.wxt.utils.FileUtils;

import java.io.File;

import static com.wuxiantao.wxt.config.Constant.CRASH_FILE_PATH;
import static com.wuxiantao.wxt.config.Constant.CRASH_REPORTER_EXTENSION;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CrashHandler
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午4:11
 * Description:${DESCRIPTION}
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static volatile CrashHandler instance;


    //保证只有一个CrashHandler实例
    private CrashHandler(){

    }

    //获取CrashHandler实例 ,单例模式
    public static CrashHandler newInstance(){
        if (instance == null){
            synchronized (CrashHandler.class){
                if (instance == null){
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    //初始化
    public void initCrashHandler(Context c){
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        ////设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //当UncaughtException发生时会转入该函数来处理
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        handleException(ex);
        if (mDefaultHandler != null){
            //收集完信息后，交给系统自己处理崩溃
            mDefaultHandler.uncaughtException(t,ex);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * @param ex
     */
    private void handleException(final Throwable ex){
        if (ex == null){
            return;
        }
        final String msg = ex.getLocalizedMessage();
        if (msg == null){
            return;
        }
        saveCrashInfoToFile(ex);
    }


    //保存错误信息到文件中
    private void saveCrashInfoToFile(Throwable ex){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return;
        }
        String fileName = FileUtils.saveCrashInfoToFile(CRASH_FILE_PATH,ex,CRASH_REPORTER_EXTENSION);
        unUpdataException(new File(fileName));
    }




    private void unUpdataException(File file){

    }


}
