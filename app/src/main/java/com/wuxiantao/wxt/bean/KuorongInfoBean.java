package com.wuxiantao.wxt.bean;

import java.util.List;

public class KuorongInfoBean {


    /**
     * intro : [{"title":"什么是背包扩容?","content":"由于我的背包容量有限，存放的物品有限，因此需要进行背包扩容，背包扩容后我的背包就可以存放更多的物品。每个格子0.1元，每次扩容至少扩容10个格子！"}]
     * total : 900
     * now : 208
     * stash : 2
     * money : 2493.40
     * price : 1
     */

    private int total;
    private int now;
    private int stash;
    private String money;
    private int price;
    private List<IntroBean> intro;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public int getStash() {
        return stash;
    }

    public void setStash(int stash) {
        this.stash = stash;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<IntroBean> getIntro() {
        return intro;
    }

    public void setIntro(List<IntroBean> intro) {
        this.intro = intro;
    }

    public static class IntroBean {
        /**
         * title : 什么是背包扩容?
         * content : 由于我的背包容量有限，存放的物品有限，因此需要进行背包扩容，背包扩容后我的背包就可以存放更多的物品。每个格子0.1元，每次扩容至少扩容10个格子！
         */

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
