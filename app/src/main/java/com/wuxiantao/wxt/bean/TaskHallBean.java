package com.wuxiantao.wxt.bean;

public class TaskHallBean {
    public TaskHallBean(int resIcon, String taskHallContent) {
        this.resIcon = resIcon;
        this.taskHallContent = taskHallContent;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public String getTaskHallContent() {
        return taskHallContent;
    }

    public void setTaskHallContent(String taskHallContent) {
        this.taskHallContent = taskHallContent;
    }

    private int resIcon;
    private String taskHallContent;

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    private  int isFinish;//是否完成0:未完成 1：已完成

    public boolean isSign() {
        return isSign;
    }

    public void setSign(boolean sign) {
        isSign = sign;
    }

    private  boolean isSign;//是否是签到列表界面
}
