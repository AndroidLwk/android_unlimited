package com.wuxiantao.wxt.event;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MessageEvent
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-8 上午10:31
 * Description:${DESCRIPTION}
 */
public class MessageEvent {

    public int type;
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(int type) {
        this.type = type;
    }

    public MessageEvent(int type,String message) {
        this.type = type;
        this.message = message;
    }

    public MessageEvent() {

    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}
