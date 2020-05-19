package com.wuxiantao.wxt.wxapi;

import android.graphics.Bitmap;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.wuxiantao.wxt.utils.WeChatImgUtils;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/25 0025 18:05 8-19
 * Description: ${DESCRIPTION} 微信分享核心类
 * Author: Administrator Shiming-Shi
 */

public class WXShare {

    private static WXShare instance;

    private IWXAPI api;

    //4.2以上支持发送到朋友圈
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private static final String WEB_PAGE = "webpage";

    private WXShare() {
        this.api = BaseApplication.getInstance().api;
    }

    /**
     * 单例
     *
     * @return
     */
    public static WXShare getInstance() {
        if (instance == null) {
            synchronized (WXShare.class) {
                if (instance == null) {
                    instance = new WXShare();
                }
            }
        }
        return new WXShare();
    }


    /**
     * 分享WXAppExtendObject类型的数据，只能分享给好友
     *
     * @param title       标题
     * @param imgPath     图片路径
     * @param description 内容描述
     * @param extInfo     其他信息系
     */
    public void shareAppDataToFriend(String title, String imgPath, String description, String extInfo) {
        try {
            WXAppExtendObject appData = new WXAppExtendObject();
            String path = imgPath;
            appData.fileData = WeChatImgUtils.readFromFile(path, 0, -1);
            appData.extInfo = extInfo;
            WXMediaMessage msg = new WXMediaMessage();
            msg.setThumbImage(WeChatImgUtils.extractThumbNail(path, 150, 150, true));
            msg.title = title;
            msg.description = description;
            msg.mediaObject = appData;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("appdata");
            req.message = msg;
            req.scene = SendMessageToWX.Req.WXSceneSession;
            if (api != null) {
                api.sendReq(req);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 微信分享文本消息
     *
     * @param isTimeLine  是否分享到朋友圈，false为分享给好友
     * @param text        文本内容
     * @param description 描述文本
     */
    public void shareTextMessage(boolean isTimeLine, String text, String description) {
        try {
            if (text == null || text.length() == 0) {
                return;
            }
            //初始化一个WXTextObject对象
            WXTextObject object = new WXTextObject();
            object.text = text;
            //用WXTextObject对象初始化一个WXMediaMessage对象
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = object;
            // 发送文本类型的消息时，title字段不起作用
            //msg.title="Will be ignored";
            msg.description = description;
            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            // transaction字段用于唯一标识一个请求
            req.transaction = buildTransaction("text");
            req.message = msg;
            if (!isTimeLine) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                if (isSupportTimeLine()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    ToastUtils.error(RESOURCES.getString(R.string.wechat_version_not_share_group));
                    return;
                }
            }
            // 调用api接口发送数据到微信
            api.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 微信分享图片消息
     *
     * @param isTimeLine 是否分享到朋友圈，false为分享给好友
     * @param bMap       图片
     */
    public void shareImgMessage(boolean isTimeLine, Bitmap bMap) {
        try {
            if (bMap == null) {
                return;
            }
            // 为保证能较为清晰的分享，先按长宽比例压缩大小，再压缩质量，直到缩略图小于32k
            int thumb_size_height = 600;
            int bitmapHeight = bMap.getHeight();
            int bitmapWidth = bMap.getWidth();
            int thumb_size_width = bitmapWidth * thumb_size_height / bitmapHeight;
            WXImageObject object = new WXImageObject(bMap);
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = object;
            //msg.description = "这是图片";
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bMap, thumb_size_width, thumb_size_height, true);
            byte[] outByteArray = WeChatImgUtils.compressImage2ByteArray(thumbBmp, true);
            if (outByteArray.length / 1024 >= 32) {
                ToastUtils.error(RESOURCES.getString(R.string.img_share_error_big));
                return;
            }
            // 设置缩略图  NOTE: The file size should be within 32KB.
            msg.thumbData = outByteArray;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction(RESOURCES.getString(R.string.img));
            req.message = msg;
            if (!isTimeLine) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                if (isSupportTimeLine()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    ToastUtils.error(RESOURCES.getString(R.string.wechat_version_not_share_group));
                    return;
                }
            }
            api.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bMap.recycle();
    }


    public void shareImgMessag(boolean isTimeLine, Bitmap bMap) {
        try {
            if (bMap == null) {
                return;
            }
            // 为保证能较为清晰的分享，先按长宽比例压缩大小，再压缩质量，直到缩略图小于32k
            int thumb_size_height = 600;
            int bitmapHeight = bMap.getHeight();
            int bitmapWidth = bMap.getWidth();
            int thumb_size_width = bitmapWidth * thumb_size_height / bitmapHeight;
            WXImageObject object = new WXImageObject(bMap);
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = object;
            //msg.description = "这是图片";
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bMap, thumb_size_width, thumb_size_height, true);
            byte[] outByteArray = WeChatImgUtils.compressImage2ByteArray(thumbBmp, true);
            if (outByteArray.length / 1024 >= 32) {
                ToastUtils.error(RESOURCES.getString(R.string.img_share_error_big));
                return;
            }
            // 设置缩略图  NOTE: The file size should be within 32KB.
            msg.thumbData = outByteArray;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction(RESOURCES.getString(R.string.img));
            req.message = msg;
            if (!isTimeLine) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                if (isSupportTimeLine()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    ToastUtils.error(RESOURCES.getString(R.string.wechat_version_not_share_group));
                    return;
                }
            }
            api.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        bMap.recycle();
    }

    /**
     * 微信分享网页消息
     * @param isTimeLine  是否分享到朋友圈，false为分享给好友
     * @param webPageUrl  网页的网址
     * @param title       标题
     * @param description 内容描述
     * @param bMap        网页分享中的左侧小图片
     */
    public void shareWebPage(boolean isTimeLine, String webPageUrl, String title, String description, Bitmap bMap) {
        try {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = webPageUrl;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = description;
            byte[] outByteArray = WeChatImgUtils.compressImage2ByteArray(bMap, true);
            if (outByteArray.length / 1024 >= 32) {
                ToastUtils.error(RESOURCES.getString(R.string.img_share_error_big));
                return;
            }
            msg.thumbData = outByteArray;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction(WEB_PAGE);
            req.message = msg;
            if (!isTimeLine) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                if (isSupportTimeLine()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    ToastUtils.error(RESOURCES.getString(R.string.wechat_version_not_share_group));
                    return;
                }
            }
            api.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 启动微信
     *
     * @return
     */
    public boolean lanuchWX() {
        return api.openWXApp();
    }


    /**
     * 是否支持发送到朋友圈
     *
     * @return
     */
    private boolean isSupportTimeLine() {
        return api.getWXAppSupportAPI() >= TIMELINE_SUPPORTED_VERSION;
    }


    /**
     * Transaction ID corresponding to this request
     *
     * @param type
     * @return
     */
    private String buildTransaction(String type) {
        return type == null ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


}
