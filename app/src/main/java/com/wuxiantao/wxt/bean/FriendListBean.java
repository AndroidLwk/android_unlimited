package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FriendListBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午1:43
 * Description:${DESCRIPTION} 红包好友列表
 */
public class FriendListBean {


    /**
     * list : [{"id":124216,"nickname":"木马Mua【专业暴利】","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/JOJziaahUIniakytuBLhoLFMiblmhHpaR0rvHRJrUJ5cq97jmwAula7Nk4x93MhWBwichePpw1rzX6d8CSfHGOKXtg/132","create_at":"2020-01-12 15:43:41"},{"id":124154,"nickname":"C","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/5gx0MRHTDRRdibXWzo8YnvXiaXWSz6h2QFZib9diagSlA4BU0FIm2Crfv8oLEysAsV4X2PwDeRtKYVcnpiazyI55iaZw/132","create_at":"2020-01-11 18:20:07"},{"id":123954,"nickname":"蓝图","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJFjbpINibPZoyfP2kZK4qbr9J3YPw12AIiafSkToDfx2BpjK2xCh8fYAaBskiaa0fdM7xBuWKdzLgBA/132","create_at":"2020-01-09 22:22:31"},{"id":123888,"nickname":"时光","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/AyUEibEbicwM8CQ3jEeTXLa24Wr7GLHHSsywnT6pmDRqw8ygKjbPkMVcsas8kfZqu7904VrKCJiaqBquZnHj3D4MA/132","create_at":"2020-01-09 14:29:17"},{"id":123843,"nickname":"呆橘","headimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqfchl4zyZjGDcicwVcJany3zRF18ickOVMgm2eehUIPPpp5dCNBj88Dg7tzNpOicWb6NjT4q1foPfBQ/132","create_at":"2020-01-09 01:02:15"}]
     * more : 1
     * all_amount : 380
     */

    private int more;
    private int all_amount;
    private List<ListBean> list;

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public int getAll_amount() {
        return all_amount;
    }

    public void setAll_amount(int all_amount) {
        this.all_amount = all_amount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 124216
         * nickname : 木马Mua【专业暴利】
         * headimg : http://thirdwx.qlogo.cn/mmopen/vi_32/JOJziaahUIniakytuBLhoLFMiblmhHpaR0rvHRJrUJ5cq97jmwAula7Nk4x93MhWBwichePpw1rzX6d8CSfHGOKXtg/132
         * create_at : 2020-01-12 15:43:41
         */

        private int id;
        private String nickname;
        private String headimg;
        private String create_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }
    }
}
