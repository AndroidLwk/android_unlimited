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
            default://刮刮卡未刮开的图片
                res_id = R.drawable.scrap_card;
                break;
        }
        return res_id;
    }

    /**
     * 背包现金卡用的图片
     */
    public static int queryPictureId_knapsack_crashcard(int id) {
        int res_id = -1;
        switch (id) {
            case 4://1元现金卡

                res_id = R.drawable.crashcard_a;

                break;
            case 5://5元现金卡

                res_id = R.drawable.crashcard_b;

                break;
            case 6://10元现金卡

                res_id = R.drawable.crashcard_e;

                break;
            case 7://25元现金卡

                res_id = R.drawable.crashcard_d;

                break;
            case 8://50元现金卡
                res_id = R.drawable.crashcard_c;
                break;
        }
        return res_id;
    }

    /**
     * 背包皮肤卡用的图片
     */
    public static int queryPictureId_knapsack_skinCard(int id) {
        int res_id = -1;
        switch (id) {
            case 13://司马懿伴生皮肤卡
                res_id = R.drawable.skincard_a;
                break;
            case 14://嫦娥伴生皮肤卡
                res_id = R.drawable.skincard_b;

                break;
            case 15://蔡文姬伴生皮肤卡


                res_id = R.drawable.skincard_c;

                break;
            case 16://露娜紫霞仙子皮肤卡

                res_id = R.drawable.skincard_d;
                break;
            case 17://曹操幽灵船长皮肤卡

                res_id = R.drawable.skincard_q;
                break;
            case 18://妲己女仆咖啡皮肤卡

                res_id = R.drawable.skincard_e;
                break;
            case 19://赵云未来纪元皮肤卡

                res_id = R.drawable.skincard_f;
                break;
            case 20://诸葛亮黄金分割比皮肤卡

                res_id = R.drawable.skincard_k;
                break;
            case 21://橘右京修罗皮肤卡

                res_id = R.drawable.skincard_n;
                break;
            case 22://干将久胜战神皮肤卡

                res_id = R.drawable.skincard_o;
                break;
            case 23://貂蝉仲夏夜之梦皮肤卡

                res_id = R.drawable.skincard_p;
                break;
        }
        return res_id;
    }

    /**
     * 背包英雄碎片用的图片
     */
    public static int queryPictureId_knapsack_hero_ft(int id) {
        int res_id = -1;
        switch (id) {
            //青铜李白碎片1
            case 46:
                res_id = R.drawable.hero_lb_bronze_a;
                break;
            //青铜李白碎片2
            case 47:
                res_id = R.drawable.hero_lb_bronze_b;
                break;
            case 48://青铜李白碎片3
                res_id = R.drawable.hero_lb_bronze_c;
                break;
            case 49://青铜李白碎片4
                res_id = R.drawable.hero_lb_bronze_d;

                break;
            case 50://青铜李白碎片5
                res_id = R.drawable.hero_lb_bronze_e;

                break;
            case 51://青铜李白碎片6
                res_id = R.drawable.hero_lb_bronze_f;

                break;
            case 52:
                res_id = R.drawable.hero_lb_bronze_g;

                break;
            case 53:
                res_id = R.drawable.hero_lb_bronze_h;

                break;
            case 54:
                res_id = R.drawable.hero_lb_bronze_i;

                break;
            case 55://青铜韩信碎片1
                res_id = R.drawable.hero_hx_bronze_a;

                break;
            case 56:
                res_id = R.drawable.hero_hx_bronze_b;

                break;
            case 57:
                res_id = R.drawable.hero_hx_bronze_c;

                break;
            case 58:
                res_id = R.drawable.hero_hx_bronze_d;
                break;
            case 59:
                res_id = R.drawable.hero_hx_bronze_e;

                break;
            case 60:
                res_id = R.drawable.hero_hx_bronze_f;
                break;
            case 61:
                res_id = R.drawable.hero_hx_bronze_g;

                break;
            case 62:
                res_id = R.drawable.hero_hx_bronze_h;

                break;
            case 63:
                res_id = R.drawable.hero_hx_bronze_i;
                break;
            case 64://青铜孙尚香碎片1
                res_id = R.drawable.hero_ssx_pronze_a;

                break;
            case 65:
                res_id = R.drawable.hero_ssx_pronze_b;

                break;
            case 66:
                res_id = R.drawable.hero_ssx_pronze_c;

                break;
            case 67:
                res_id = R.drawable.hero_ssx_pronze_d;

                break;
            case 68:
                res_id = R.drawable.hero_ssx_pronze_e;

                break;
            case 69:
                res_id = R.drawable.hero_ssx_pronze_f;

                break;
            case 70:
                res_id = R.drawable.hero_ssx_pronze_g;

                break;
            case 71:
                res_id = R.drawable.hero_ssx_pronze_h;

                break;
            case 72:
                res_id = R.drawable.hero_ssx_pronze_i;

                break;
            case 73://青铜伽罗碎片1
                res_id = R.drawable.hero_jialuo_bronze_a;
                break;
            case 74:
                res_id = R.drawable.hero_jialuo_bronze_b;

                break;
            case 75:
                res_id = R.drawable.hero_jialuo_bronze_c;

                break;
            case 76:
                res_id = R.drawable.hero_jialuo_bronze_d;

                break;
            case 77:
                res_id = R.drawable.hero_jialuo_bronze_e;

                break;
            case 78:
                res_id = R.drawable.hero_jialuo_bronze_f;

                break;
            case 79:
                res_id = R.drawable.hero_jialuo_bronze_g;

                break;
            case 80:
                res_id = R.drawable.hero_jialuo_bronze_h;

                break;
            case 81:
                res_id = R.drawable.hero_jialuo_bronze_i;


                break;
            case 82://青铜蔡文姬碎片1

                res_id = R.drawable.hero_cwj_bronze_a;

                break;
            case 83:
                res_id = R.drawable.hero_cwj_bronze_b;

                break;
            case 84:
                res_id = R.drawable.hero_cwj_bronze_c;

                break;
            case 85:
                res_id = R.drawable.hero_cwj_bronze_d;

                break;
            case 86:
                res_id = R.drawable.hero_cwj_bronze_e;

                break;
            case 87:
                res_id = R.drawable.hero_cwj_bronze_f;

                break;
            case 88:
                res_id = R.drawable.hero_cwj_bronze_g;

                break;
            case 89:
                res_id = R.drawable.hero_cwj_bronze_h;

                break;
            case 90:
                res_id = R.drawable.hero_cwj_bronze_i;

                break;
            case 91://青铜大乔碎片1

                res_id = R.drawable.hero_daqiao_bronze_a;

                break;
            case 92:
                res_id = R.drawable.hero_daqiao_bronze_b;

                break;
            case 93:
                res_id = R.drawable.hero_daqiao_bronze_c;

                break;
            case 94:
                res_id = R.drawable.hero_daqiao_bronze_d;

                break;
            case 95:
                res_id = R.drawable.hero_daqiao_bronze_e;

                break;
            case 96:
                res_id = R.drawable.hero_daqiao_bronze_f;

                break;
            case 97:
                res_id = R.drawable.hero_daqiao_bronze_g;

                break;
            case 98:
                res_id = R.drawable.hero_daqiao_bronze_h;

                break;
            case 99:
                res_id = R.drawable.hero_daqiao_bronze_i;

                break;
            case 100://青铜凯碎片1
                res_id = R.drawable.hero_kai_bronze_a;

                break;
            case 101:
                res_id = R.drawable.hero_kai_bronze_b;

                break;
            case 102:
                res_id = R.drawable.hero_kai_bronze_c;

                break;
            case 103:
                res_id = R.drawable.hero_kai_bronze_d;

                break;
            case 104:
                res_id = R.drawable.hero_kai_bronze_e;

                break;
            case 105:
                res_id = R.drawable.hero_kai_bronze_f;

                break;
            case 106:
                res_id = R.drawable.hero_kai_bronze_g;

                break;
            case 107:
                res_id = R.drawable.hero_kai_bronze_h;

                break;
            case 108:
                res_id = R.drawable.hero_kai_bronze_i;

                break;
            case 109://青铜妲己碎片1

                res_id = R.drawable.hero_danji_bronze_a;

                break;
            case 110:
                res_id = R.drawable.hero_danji_bronze_b;

                break;
            case 111:
                res_id = R.drawable.hero_danji_bronze_c;

                break;
            case 112:
                res_id = R.drawable.hero_danji_bronze_d;

                break;
            case 113:
                res_id = R.drawable.hero_danji_bronze_e;
                break;
            case 114:
                res_id = R.drawable.hero_danji_bronze_f;

                break;
            case 115:
                res_id = R.drawable.hero_danji_bronze_g;
                break;
            case 116:
                res_id = R.drawable.hero_danji_bronze_h;
                break;
            case 117:
                res_id = R.drawable.hero_danji_bronze_i;

                break;
            case 118://青铜王昭君碎片1

                res_id = R.drawable.hero_wsj_bronze_a;

                break;
            case 119:
                res_id = R.drawable.hero_wsj_bronze_b;

                break;
            case 120:
                res_id = R.drawable.hero_wsj_bronze_c;

                break;
            case 121:
                res_id = R.drawable.hero_wsj_bronze_d;

                break;
            case 122:
                res_id = R.drawable.hero_wsj_bronze_e;

                break;
            case 123:
                res_id = R.drawable.hero_wsj_bronze_f;

                break;
            case 124:
                res_id = R.drawable.hero_wsj_bronze_g;

                break;
            case 125:
                res_id = R.drawable.hero_wsj_bronze_h;

                break;
            case 126:
                res_id = R.drawable.hero_wsj_bronze_i;

                break;
            case 127://青铜曹操碎片1

                res_id = R.drawable.hero_cc_bronze_a;

                break;
            case 128:
                res_id = R.drawable.hero_cc_bronze_b;

                break;
            case 129:
                res_id = R.drawable.hero_cc_bronze_c;

                break;
            case 130:
                res_id = R.drawable.hero_cc_bronze_d;

                break;
            case 131:
                res_id = R.drawable.hero_cc_bronze_e;

                break;
            case 132:
                res_id = R.drawable.hero_cc_bronze_f;

                break;
            case 133:
                res_id = R.drawable.hero_cc_bronze_g;

                break;
            case 134:
                res_id = R.drawable.hero_cc_bronze_h;

                break;
            case 135:
                res_id = R.drawable.hero_cc_bronze_i;

                break;
            case 136://黄金曹操碎片1

                res_id = R.drawable.hero_cc_gold_a;

                break;
            case 137:
                res_id = R.drawable.hero_cc_gold_b;

                break;
            case 138:
                res_id = R.drawable.hero_cc_gold_c;

                break;
            case 139:
                res_id = R.drawable.hero_cc_gold_d;

                break;
            case 140:
                res_id = R.drawable.hero_cc_gold_e;

                break;
            case 141:
                res_id = R.drawable.hero_cc_gold_f;

                break;
            case 142:
                res_id = R.drawable.hero_cc_gold_g;

                break;
            case 143:
                res_id = R.drawable.hero_cc_gold_h;

                break;
            case 144:
                res_id = R.drawable.hero_cc_gold_i;

                break;
            case 145://黄金王昭君碎片1

                res_id = R.drawable.hero_wsj_gold_a;

                break;
            case 146:
                res_id = R.drawable.hero_wsj_gold_b;

                break;
            case 147:
                res_id = R.drawable.hero_wsj_gold_c;

                break;
            case 148:
                res_id = R.drawable.hero_wsj_gold_d;

                break;
            case 149:
                res_id = R.drawable.hero_wsj_gold_e;

                break;
            case 150:
                res_id = R.drawable.hero_wsj_gold_f;

                break;
            case 151:
                res_id = R.drawable.hero_wsj_gold_g;

                break;
            case 152:
                res_id = R.drawable.hero_wsj_gold_h;

                break;
            case 153:
                res_id = R.drawable.hero_wsj_gold_i;

                break;
            case 154://黄金妲己碎片1

                res_id = R.drawable.hero_danji_gold_a;

                break;
            case 155:
                res_id = R.drawable.hero_danji_gold_b;

                break;
            case 156:
                res_id = R.drawable.hero_danji_gold_c;

                break;
            case 157:
                res_id = R.drawable.hero_danji_gold_d;

                break;
            case 158:
                res_id = R.drawable.hero_danji_gold_e;

                break;
            case 159:
                res_id = R.drawable.hero_danji_gold_f;

                break;
            case 160:
                res_id = R.drawable.hero_danji_gold_g;

                break;
            case 161:
                res_id = R.drawable.hero_danji_gold_h;

                break;
            case 162:
                res_id = R.drawable.hero_danji_gold_i;

                break;
            case 163://黄金凯碎片1

                res_id = R.drawable.hero_kai_gold_a;

                break;
            case 164:
                res_id = R.drawable.hero_kai_gold_b;

                break;
            case 165:
                res_id = R.drawable.hero_kai_gold_c;

                break;
            case 166:
                res_id = R.drawable.hero_kai_gold_d;

                break;
            case 167:
                res_id = R.drawable.hero_kai_gold_e;

                break;

            case 168:
                res_id = R.drawable.hero_kai_gold_f;

                break;
            case 169:
                res_id = R.drawable.hero_kai_gold_g;
                break;

            case 170:
                res_id = R.drawable.hero_kai_gold_h;

                break;
            case 171:
                res_id = R.drawable.hero_kai_gold_i;

                break;
            case 172://黄金大乔碎片1

                res_id = R.drawable.hero_daqiao_gold_a;

                break;
            case 173:
                res_id = R.drawable.hero_daqiao_gold_b;

                break;
            case 174:
                res_id = R.drawable.hero_daqiao_gold_c;
                break;
            case 175:
                res_id = R.drawable.hero_daqiao_gold_d;

                break;
            case 176:
                res_id = R.drawable.hero_daqiao_gold_e;

                break;
            case 177:
                res_id = R.drawable.hero_daqiao_gold_f;

                break;
            case 178:
                res_id = R.drawable.hero_daqiao_gold_g;

                break;
            case 179:
                res_id = R.drawable.hero_daqiao_gold_h;

                break;
            case 180:
                res_id = R.drawable.hero_daqiao_gold_i;

                break;
            case 181://黄金蔡文姬碎片1
                res_id = R.drawable.hero_cwj_gold_a;

                break;
            case 182:
                res_id = R.drawable.hero_cwj_gold_b;

                break;
            case 183:
                res_id = R.drawable.hero_cwj_gold_c;

                break;
            case 184:

                res_id = R.drawable.hero_cwj_gold_d;

                break;
            case 185:
                res_id = R.drawable.hero_cwj_gold_e;

                break;
            case 186:
                res_id = R.drawable.hero_cwj_gold_f;

                break;
            case 187:
                res_id = R.drawable.hero_cwj_gold_g;

                break;
            case 188:
                res_id = R.drawable.hero_cwj_gold_h;

                break;
            case 189:
                res_id = R.drawable.hero_cwj_gold_i;

                break;
            case 190://黄金伽罗碎片1
                res_id = R.drawable.hero_jialuo_a;

                break;
            case 191:
                res_id = R.drawable.hero_jialuo_b;

                break;
            case 192:
                res_id = R.drawable.hero_jialuo_c;

                break;
            case 193:
                res_id = R.drawable.hero_jialuo_d;

                break;
            case 194:
                res_id = R.drawable.hero_jialuo_e;

                break;
            case 195:
                res_id = R.drawable.hero_jialuo_f;

                break;
            case 196:
                res_id = R.drawable.hero_jialuo_g;

                break;
            case 197:
                res_id = R.drawable.hero_jialuo_h;

                break;
            case 198:
                res_id = R.drawable.hero_jialuo_i;

                break;
            case 199://黄金孙尚香碎片1

                res_id = R.drawable.hero_ssx_gold_a;

                break;
            case 200:
                res_id = R.drawable.hero_ssx_gold_b;

                break;
            case 201:
                res_id = R.drawable.hero_ssx_gold_c;

                break;
            case 202:
                res_id = R.drawable.hero_ssx_gold_d;

                break;
            case 203:
                res_id = R.drawable.hero_ssx_gold_e;

                break;
            case 204:
                res_id = R.drawable.hero_ssx_gold_f;

                break;
            case 205:
                res_id = R.drawable.hero_ssx_gold_g;

                break;
            case 206:
                res_id = R.drawable.hero_ssx_gold_h;

                break;
            case 207:
                res_id = R.drawable.hero_ssx_gold_i;

                break;
            case 208://黄金韩信碎片1

                res_id = R.drawable.hero_hx_gold_a;

                break;
            case 209:
                res_id = R.drawable.hero_hx_gold_b;

                break;
            case 210:
                res_id = R.drawable.hero_hx_gold_c;

                break;
            case 211:
                res_id = R.drawable.hero_hx_gold_d;

                break;
            case 212:
                res_id = R.drawable.hero_hx_gold_e;

                break;
            case 213:
                res_id = R.drawable.hero_hx_gold_f;

                break;
            case 214:
                res_id = R.drawable.hero_hx_gold_g;

                break;
            case 215:
                res_id = R.drawable.hero_hx_gold_h;

                break;
            case 216:
                res_id = R.drawable.hero_hx_gold_i;

                break;
            case 217://黄金李白碎片1

                res_id = R.drawable.hero_lb_gold_a;

                break;
            case 218:
                res_id = R.drawable.hero_lb_gold_b;

                break;
            case 219:
                res_id = R.drawable.hero_lb_gold_c;

                break;
            case 220:
                res_id = R.drawable.hero_lb_gold_d;

                break;
            case 221:
                res_id = R.drawable.hero_lb_gold_e;

                break;
            case 222:
                res_id = R.drawable.hero_lb_gold_f;

                break;
            case 223:
                res_id = R.drawable.hero_lb_gold_g;

                break;
            case 224:
                res_id = R.drawable.hero_lb_gold_h;

                break;
            case 225:
                res_id = R.drawable.hero_lb_gold_i;
                break;
        }
        return res_id;
    }
}
