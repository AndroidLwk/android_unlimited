package com.wuxiantao.wxt.net.base;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ResultException
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-3 上午11:02
 * Description:${DESCRIPTION}
 */
public class ResultException extends RuntimeException {

    private int errorCode = 0;
    private String errorMessage;
    public ResultException(int errorCode, String msg){
        super(msg);
        this.errorCode = errorCode;
        this.errorMessage = msg;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }


}
