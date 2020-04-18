package com.wuxiantao.wxt.adapter.bean;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:LableSelecedInfo
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午8:15
 * Description:${DESCRIPTION}
 */
public class LableSelecedInfo {

    private String name;
    private String lable;

    public LableSelecedInfo(String name,String lables) {
        this.name = name;
        this.lable = lables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LableSelecedInfo){
            LableSelecedInfo info = (LableSelecedInfo) obj;
            return info.name.equals(this.name);
        }
        return false;
    }


}
