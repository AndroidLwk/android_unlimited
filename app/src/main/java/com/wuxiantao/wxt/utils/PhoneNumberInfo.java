package com.wuxiantao.wxt.utils;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/3/29 0029 15:43 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class PhoneNumberInfo {

    private String name;

    public PhoneNumberInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
