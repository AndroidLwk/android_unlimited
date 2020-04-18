package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OpenDragonBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午2:42
 * Description:${DESCRIPTION} 收益大厅->打开卡牌
 */
public class OpenDragonBean {


    /**
     * card_message : [{"id":1,"dragon_id":1},{"id":2,"dragon_id":3},{"id":3,"dragon_id":4},{"id":4,"dragon_id":1},{"id":5,"dragon_id":8}]
     * dragon_id : 1
     * title : 元宝
     * num : 100.00
     */

    private int dragon_id;
    private String title;
    private String num;

    public int getCard_num() {
        return card_num;
    }

    public void setCard_num(int card_num) {
        this.card_num = card_num;
    }

    private int card_num;
    private List<CardMessageBean> card_message;

    public int getDragon_id() {
        return dragon_id;
    }

    public void setDragon_id(int dragon_id) {
        this.dragon_id = dragon_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<CardMessageBean> getCard_message() {
        return card_message;
    }

    public void setCard_message(List<CardMessageBean> card_message) {
        this.card_message = card_message;
    }

    public static class CardMessageBean {
        /**
         * id : 1
         * dragon_id : 1
         */

        private int id;
        private int dragon_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDragon_id() {
            return dragon_id;
        }

        public void setDragon_id(int dragon_id) {
            this.dragon_id = dragon_id;
        }
    }
}
