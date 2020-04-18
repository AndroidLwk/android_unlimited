package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawRecordingBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午10:08
 * Description:${DESCRIPTION}
 */
public class WithdrawRecordingBean {


    /**
     * list : [{"money":"193","status":1,"create_time":1566049569,"tx_type":4,"msg":""},{"money":"25","status":1,"create_time":1566049535,"tx_type":4,"msg":""},{"money":"1","status":1,"create_time":1562549425,"tx_type":0,"msg":""}]
     * more : 0
     */

    private int more;
    private List<ListBean> list;

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * money : 193
         * status : 1
         * create_time : 1566049569
         * tx_type : 4
         * msg :
         */

        private String money;
        private int status;
        private int create_time;
        private int tx_type;
        private String msg;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getTx_type() {
            return tx_type;
        }

        public void setTx_type(int tx_type) {
            this.tx_type = tx_type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
