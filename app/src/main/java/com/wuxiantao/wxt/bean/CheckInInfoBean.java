package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CheckInInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午8:57
 * Description:${DESCRIPTION}
 */
public class CheckInInfoBean {


    /**
     * type : 0
     * day : 0
     * remain : [2,11,20]
     */

    private int type;
    private int day;
    private List<Integer> remain;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Integer> getRemain() {
        return remain;
    }

    public void setRemain(List<Integer> remain) {
        this.remain = remain;
    }
}
