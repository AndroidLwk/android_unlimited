package com.wuxiantao.wxt.bean;

public class ScrapingCardBean {
    public String getNumTitle() {
        return numTitle;
    }

    public void setNumTitle(String numTitle) {
        this.numTitle = numTitle;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private String numTitle;

    public String getFinishText() {
        return finishText;
    }

    public void setFinishText(String finishText) {
        this.finishText = finishText;
    }

    private String finishText;
    private int num;

    public int getNum_finish() {
        return num_finish;
    }

    public void setNum_finish(int num_finish) {
        this.num_finish = num_finish;
    }

    private int num_finish;

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    private int img_res;

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    private  int isFinish;
}
