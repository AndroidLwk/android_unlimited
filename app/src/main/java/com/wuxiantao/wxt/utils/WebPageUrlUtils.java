package com.wuxiantao.wxt.utils;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.config.Api;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WebPageUrlUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-6 下午2:42
 * Description:${DESCRIPTION}
 */
public class WebPageUrlUtils {

    public static String createShareUrl(int user_id,int type){
        return RESOURCES.getString(R.string.share_line, Api.BASE_URL,
                Api.SHARE_RED_BAG_LINK,user_id,"&",type);
    }

    public static String createShareUrl(int user_id){
        return createShareUrl(user_id,0);
    }
}
