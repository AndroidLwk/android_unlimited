package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SynthesisDetailBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-14 下午2:40
 * Description:${DESCRIPTION} 收益大厅 分红龙状态及剩余翻牌次数
 */
public class DragonStatusInfoBean {


    /**
     * card_message : [{"id":1,"dragon_id":99},{"id":2,"dragon_id":99},{"id":3,"dragon_id":99},{"id":4,"dragon_id":99},{"id":5,"dragon_id":99}]
     * card_num : 5
     */

    private int card_num;
    private List<CardMessageBean> card_message;

    public int getCard_num() {
        return card_num;
    }

    public void setCard_num(int card_num) {
        this.card_num = card_num;
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
         * dragon_id : 99
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
