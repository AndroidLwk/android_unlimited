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
}
