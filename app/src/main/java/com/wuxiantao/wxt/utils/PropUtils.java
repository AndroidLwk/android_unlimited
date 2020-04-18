package com.wuxiantao.wxt.utils;

import com.wuxiantao.wxt.R;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PropUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-3 下午7:52
 * Description:${DESCRIPTION} 道具类 根据id查找图片
 */
public class PropUtils {


    public static int queryPictureId(int id) {
        int res_id = -1;
        switch (id) {
            //元宝
            case 1:
                res_id = R.mipmap.game_ingots;
                break;
            //初级经验瓶
            case 2:
                res_id = R.mipmap.game_low_level_bottle;
                break;
            //中级经验瓶
            case 3:
                res_id = R.mipmap.game_intermediate_bottle;
                break;
            //金条
            case 4:
                res_id = R.mipmap.game_bullion;
                break;
            //定身玉符
            case 5:

                break;
            //转生丹
            case 6:

                break;
            //洗髓丹
            case 7:
                res_id = R.mipmap.game_powder;
                break;
            //羽毛
            case 8:
                res_id = R.mipmap.game_feather;
                break;
            //强化石
            case 9:
                res_id = R.mipmap.game_rock;
                break;
            //聚灵石
            case 10:
                res_id = R.mipmap.game_rock;
                break;
            //仙守坠饰
            case 11:

                break;
            //御器精魄
            case 12:
                res_id = R.mipmap.game_utensil;
                break;
            //高级经验瓶
            case 13:
                res_id = R.mipmap.game_advanced_bottle;
                break;
            //低级经验卡
            case 14:
                res_id = R.mipmap.game_low_level_card;
                break;
            //中级经验卡
            case 15:
                res_id = R.mipmap.game_intermediate_card;
                break;
            //高级经验卡
            case 16:
                res_id = R.mipmap.game_advanced_card;
                break;
            //未翻牌 默认牌
            case 99:
            default:
                res_id = R.mipmap.divided_dragon_icon;
                break;
        }
        return res_id;
    }




}
