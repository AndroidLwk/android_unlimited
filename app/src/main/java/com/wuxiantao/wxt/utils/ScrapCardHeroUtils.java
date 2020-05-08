package com.wuxiantao.wxt.utils;

import com.wuxiantao.wxt.R;

public class ScrapCardHeroUtils {
    /**
     * 刮卡弹窗用的图片
     */
    public static int queryPictureId_pop(int id) {
        int res_id = -1;
        switch (id) {
            //司马懿
            case 1:
                res_id = R.drawable.scrapcard_open_pop_a;
                break;
            //司马懿
            case 2:
                res_id = R.drawable.scrapcard_open_pop_b;
                break;
            default:
                res_id = R.mipmap.divided_dragon_icon;
                break;
        }
        return res_id;
    }

    /**
     * 背包用的图片
     */
    public static int queryPictureId_knapsack(int id) {
        int res_id = -1;
        switch (id) {
            //司马懿
            case 1:
                res_id = R.mipmap.game_ingots;
                break;
            //嫦娥
            case 2:
                res_id = R.mipmap.game_low_level_bottle;
                break;
            default:
                res_id = R.mipmap.divided_dragon_icon;
                break;
        }
        return res_id;
    }
}
