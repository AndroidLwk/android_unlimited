package com.wuxiantao.wxt.config;

import android.content.res.Resources;
import android.os.Environment;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;

import java.io.File;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:Constant
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午5:53
 * Description:${DESCRIPTION} 常量类
 */
public class Constant {
    public static final Resources RESOURCES = BaseApplication.getAppContext().getResources();
    public static final String SHARED_FILE_NAME = "info";
    //无限陶下载地址
    public static final String WUXIANTAO_URL = "https://android.myapp.com//myapp//detail.htm?apkName=com.wuxiantao.wxt&ADTAG=mobile";
    //app下载路径
    private static final String PATH = "/无限淘.apk";
    //指定apk缓存路径和文件名 默认是在SD卡中的Download文件夹
    public static final String APK_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + PATH;

    //文件路径 文件名
    private static final String APP_NAME = RESOURCES.getString(R.string.app_name);
    //Base_Directory
    private static final String BASE_DIRECTORY = Environment.getExternalStorageDirectory().getPath() + File.separator + APP_NAME + File.separator;
    //应用程序崩溃
    public static final String CRASH_FILE_PATH = BASE_DIRECTORY + "Crash" + File.separator;
    //屏幕尺寸
    public static final String DENSITY_FILE_PATH = BASE_DIRECTORY + "Density" + File.separator;
    //网络请求失败
    public static final String HTTP_FILE_PATH = BASE_DIRECTORY + "Http" + File.separator;
    //文件后缀
    public static final String CRASH_REPORTER_EXTENSION = ".text";

    //是否是调试模式
    public static final String IS_DEBUG = "is_debug";
    //token
    public static final String TOKEN = "token";

    //腾讯bugly id
    //public static final String BUGLY_ID = "ddf6197528";
    public static final String BUGLY_ID = "97d1d301f4";

    //淘宝分类ID cate_id
    public static final String CATE_ID = "cate_id";

    //淘宝商品id
    public static final String TAO_BAO_ID = "tao_bao_id";

    //收益类型 1今日排行 2 总收益排行
    public static final String REVENUE_TYPE = "revenue_type";

    //isBingding 是否是绑定微信
    public static final String BINGDING_WECHAT = "isBingding_Wechat";
    //默认的加载提示语
    public static final String DEFAULT_LOADING = RESOURCES.getString(R.string.loading);

    //阿里百川
    public static final String ALI_BAICHUANG_APP_KEY = "27628051";
    public static final String ALI_BAICHUANG_APP_SECRET = "e611271718ec6599947afae8b6f5a64c";

    //微信apiId
    public static final String WEIXIN_ID = "wxb0edfd75f76d8c1a";

    //支付标记
    public static final int ALIPAY_FLAG = 10001;

    //修改昵称
    public static final int REQUEST_CODE_UPDATE_NICK_NAME = 1;
    public static final int RESULT_CODE_UPDATE_NICK_NAME = 2;

    //绑定手机号
    public static final int REQUEST_CODE_BINDING_NUMBER = 3;
    public static final int RESULT_CODE_BINDING_NUMBER = 4;

    //更换手机号
    public static final int REQUEST_CODE_CHANGE_NUMBER = 5;
    public static final int RESULT_CODE_CHANGE_NUMBER = 6;

    //绑定支付宝
    public static final int REQUEST_CODE_BINDING_ALIPAY = 7;
    public static final int RESULT_CODE_BINDING_ALIPAY = 8;

    //填写微信号
    public static final int REQUEST_CODE_WECHAT_ID = 21;
    public static final int RESULT_CODE_WECHAT_ID = 22;

    //设置或修改支付密码
    public static final int REQUEST_CODE_SET_LOGIN_PW = 9;
    public static final int RESULT_CODE_SET_LOGIN_PW = 10;

    //编辑收货地址
    public static final int REQUEST_CODE_EDIT_ADDRESS = 11;
    public static final int RESULT_CODE_EDIT_ADDRESS = 12;
    //添加收货地址
    public static final int REQUEST_CODE_ADD_ADDRESS = 13;
    public static final int RESULT_CODE_ADD_ADDRESS = 14;

    //分享更多主题
    public static final int REQUEST_SHARE_MORE_THEM = 17;
    public static final int RESULT_SHARE_MORE_THEM = 18;

    //备注订单
    public static final int REQUEST_CODE_ORDER_REMARK = 19;
    public static final int RESULT_CODE_ORDER_REMARK = 20;
    public static final String ORDER_REMARK = "order_remark";

    //获取次数
    public static final int REQUEST_CODE_FLOP_COUNT = 21;
    public static final int RESULT_CODE_FLOP_COUNT = 22;

    //分享
    public static final int REQUEST_CODE_SHARE = 23;
    public static final int RESULT_CODE_SHARE = 24;
    //是否分享成功
    public static final String IS_SHARE_SUCCESS = "is_share_success";
    //奖励金额
    public static final String REWARD_MONEY = "reward_money";

    //分享主题id
    public static final String SHARE_THEM_ID = "share_them_id";

    //倒计时时间 millisInFuture
    public static final long MILLIS_IN_FUTURE = 60 * 1000;
    //倒计时间隔时间 1s countDownInterval
    public static final long COUNT_DOWN_INTERVAL = 1000;
    //timer 延迟时间
    public static final long COUNT_DOWN_DELAY = 0;
    //timer 间隔时间
    public static final long COUNT_DOWN_PERIOD = 1000;


    //自动收红包时长
    public static final String AUTO_RED_PG_TIME = "auto_red_pg_time";
    //列表item纵向间隔
    public static final int ITEM_V_SPACE = 20;

    //VIP礼包列表
    public static final int VIP_GOODS_365 = 1;
    public static final int VIP_GOODS_68 = 2;

    //正常布局
    public final static int TYPE_NORMAL = 1005;
    //订单信息布局
    public final static int TYPE_ORDER_INFO = 1006;
    //退出登陆
    public static final int MESSAGE_OUT_LOGIN = 1007;
    //pagesize
    public static final int PAGE_SIZE = 10;
    //刷新加载时间 refresh Loadmore
    public static final int REFRESH_LOAD_MORE_TIME = 1000;
    //修改密码 跳转登陆界面时间
    public static final int GO_LOGIN_TIME = 5000;
    //进度条持续时间
    public static final int PROGRESS_DURATION = 600;
    //搜索关键词
    public static final String SEARCH_KEYWORD = "search_key_word";
    //邀请玛
    public static final String SHARE_CODE = "share_code";
    //头像地址
    public static final String USER_HEAD_IMG = "headImg";
    //商品类型 1.自营商品  2.0元购商品
    public static final String GOOD_TYPE = "good_type";
    //自营
    public static final int SELF_EMPLOYED = 1;
    //0元购
    public static final int ZERO_BUY = 2;

    //自营商品图文详情
    public static final String GOOD_CONTENT = "good_content";
    //商品id
    public static final String GOOD_ID = "good_id";
    //订单id
    public static final String ORDER_ID = "order_id";
    //订单类型 0.淘宝订单详情 1.自营订单详情
    public static final String ORDER_TYPE = "order_type";
    //界面跳转id
    public static final String SHIFT_ID = "shift_id";
    //用户id
    public static final String APP_USER_ID = "app_user_id";
    //支付宝账号
    public static final String ALI_CODE = "aliCode";
    //支付宝姓名
    public static final String ALI_NAME = "aliName";
    //微信号
    public static final String WECHAT_NO = "wechat_no";
    //手机号
    public static final String NUMBER = "number";
    //昵称
    public static final String NICK_NAME = "nick_name";
    //淘宝是否授权
    public static final String IS_TAO_BAO_AUTH = "is_taobao";
    //是否显示奖励小图标isShowSmallIcon
    public static final String IS_SHOW_SAMALL_ICON = "isShowSmallIcon";
    //关于
    public static final String ABOUT_SUPER = "about_super_man";
    //是否设置密码
    public static final String IS_SETTING_PW = "isSettingPassWord";
    //新密码
    public static final String NEW_PASS_WORD = "newPassWord";
    //旧密码
    public static final String OLD_PASS_WORD = "oldPassWord";
    //VerCode Type 验证码类型
    public static final String VERCODE_TYPE = "vercode_type";

    //价格price
    public static final String COMMODITY_PRICE = "commodity_price";
    //图片
    public static final String COMMODITY_TITLE = "commodity_title";
    //图片
    public static final String COMMODITY_IMG = "commodity_img";
    //规格
    public static final String COMMODITY_SPACE = "commodity_space";
    //规格字符串
    public static final String COMMODITY_SELECT_NAME = "commodity_select_name";

    //地址类型:1.添加地址 2.修改地址
    public static final String ADDRESS_TYPE = "address_type";
    //地址类型:1.添加地址
    public static final int ADDRESS_ADD_TYPE = 1;
    //地址类型:2.修改地址
    public static final int ADDRESS_EDIT_TYPE = 2;
    //编辑收货地址
    public static final String EDIT_ADDRESS_INFO = "edit_address_info";

    //验证码类型:验证码登陆
    public static final int VERCODE_LOGIN = 0;
    //验证码类型:绑定手机号
    public static final int VERCODE_BINDING_NUMBER = 1;
    //验证码类型:更换手机号
    public static final int VERCODE_CHANGE_NUMBER = 2;
    //验证码类型:重置密码
    public static final int VERCODE_RESET_PASS_WORD = 3;
    //验证码类型:验证码注册
    public static final int VERCODE_REGISTERED = 4;

    //binding scarcity
    public static final String BINDIND_ALIPAY = RESOURCES.getString(R.string.binding_alipay);
    public static final String BINDIND_WECHAT = RESOURCES.getString(R.string.binding_wechat);
    public static final String CONFIRM_WITHDRAW = RESOURCES.getString(R.string.confirm_withdraw);
    public static final String BALANCE_SCARCITY = RESOURCES.getString(R.string.balance_scarcity);
    public static final String MORE_THAN_THE_INPUT = RESOURCES.getString(R.string.more_than_the_input);

    //保留小数点后几位 Decimal
    public static final int DECIMAL_BIT = 2;

    //支付类型:微信支付
    public static final int PAY_TYPE_WX = 1;
    //支付类型:支付宝支付
    public static final int PAY_TYPE_ALI = 2;

    //中奖名单列表类型 0.铜钥匙  1.银钥匙 2.金钥匙
    public static final String WINNING_LIST_TYPE = "winning_list_type";

    //订单状态 0.全部订单 1.验证中 2.验证完成 3.已失效
    public static final String ORDER_STATUS = "order_status";

    //记录类型 1.时长记录 2.上限记录
    public static final String RECORDING_TYPE = "recording_type";

    //图片查看器
    public static final String PREVIEW_IMG_LIST = "preview_img_list";
    public static final String PREVIEW_IMG_POSITION = "preview_img_position";

    //webview类型1.隐私政策 2.用户注册协议 3.APP升级 4.首页banner链接 5.url跳转
    public static final String WEB_VIEW_CONTENT_TYPE = "webview_content_type";
    public static final int WEB_VIEW_TYPE_PRIVACY_POLICY = 1;
    public static final int WEB_VIEW_TYPE_USER_AGREEMENT = 2;
    public static final int WEB_VIEW_TYPE_UPDATE_APP = 3;
    public static final int WEB_VIEW_TYPE_MAIN_BANNER = 4;
    public static final int WEB_VIEW_TYPE_URL = 5;
    public static final String MAIN_BANNER_URL = "main_banner_url";
    public static final String APP_URL = "app_url";
    public static final String WEB_VIEW_URL = "web_view_url";

    //是否绑定微信、支付宝、关注公众号等
    public static final String IS_BINDING_WECHAT = "isBindingWeChat";
    public static final String IS_BINDING_ALIPAY = "isBindingAlipay";
    public static final String IS_ATTENTION_PUBLIC = "isAttentionPublic";

    //提现类型 1.红包提现  2.佣金提现
    public static final String MODE = "mode";

    //公众号昵称
    public static final String PUBLIC_NICKNAME = "无限淘";

    //好友类型Friend 1.专属好友  2.普通好友
    public static final String FRIEND_TYPE = "friend_type";

    //粉丝类型 1.潜在粉丝  2.直属粉丝 3.简介粉丝
    //1徒弟2徒孙
    public static final int FANSI_TYPE_POTENTIAL = 1;
    public static final int FANSI_TYPE_DIRECTLY = 2;
    public static final int FANSI_TYPE_INDIRECT = 3;

    //刷新广播 UpdateReceiver
    public static final String RECEIVE_UPDATE_DATE = "com.wuxiantao.receiver.update";
    //重新登陆
    public static final String RECEIVE_LOGIN = "com.wuxiantao.receiver.login";
    //任务状态
    public static final String TASK_STATUS = "task_status";

    //会员状态:-1会员过期，0未注册会员，1年会员，2月会员
    public static final String VIP_STATUS = "vip_status";
    //昵称
    public static final String NICKNAME = "nickname";
    // 0元购购物状态：0未购，1已购
    public static final String BUY_STATUS = "buy_status";
    //领取新手红包状态：0未领取，1已领取
    public static final String NEW_AWARD_STATUS = "new_award_status";

    //刷新数据code
    //刷新红包信息和红包好友列表
    public static final int UPDATE_RED_BAG_INFO_LIST = 0;
    //刷新红包信息
    public static final int UPDATE_RED_BAG_MAIN_INFO = 1;
    //刷新红包好友列表
    public static final int UPDATE_RED_BAG_LIST = 2;
    //刷新默认地址
    public static final int UPDATE_DEFAULT_ADDRESS = 3;
    //微信绑定成功
    public static final int UPDATE_WECHAT_BINGDING_SUCCESS = 4;
    //微信绑定失败
    public static final int UPDATE_WECHAT_BINGDING_ERROR = 5;
    //通知淘宝主界面置顶
    public static final int MAIN_TAO_BAO_OPEN = 6;
    //通知淘宝主界面置顶
    public static final int MAIN_TAO_BAO_CLOSE = 7;
    //通知淘宝列表置顶
    public static final int MAIN_TAO_BAO_REC_VIEW = 8;
    //通知淘宝精选列表置顶
    public static final int TAO_BAO_FEATURED_REC_VIEW = 9;
    //会员中心礼包商品列表置顶
    public static final int MEMBER_CENTER_TOPPING = 10;
    //自营商品详情页面置顶
    public static final int COMMODITY_DETAIL_TOPPING = 11;

    //高用商品列表刷新
    public static final int UPDATE_HIGH_AREA = 12;

    //云客服系统
    public static final String YUN_KF_ADDRESS = "zsyxkefu.kf5.com";
    public static final String YUN_KF_APP_ID = "0015d8c37ad6127376ad85628e729739e9c1c60640775b87";

    //穿山甲广告id
    public static final String TT_APP_ID = "5035141";
    //开屏广告id
    public static final String SPLASH_AD_ID = "835141246";
    //激励视频广告id
    public static final String VIDEO_AD_ID = "935141206";
    //插屏广告id
    public static final String NATIVE_INTERACTION_AD_ID = "935141757";
    //信息流广告id
    public static final String INFORMATION_AD_ID = "935141279";

    //EventBus:1.下拉刷新
    public static final int WINNING_REFRESH = 13;
    //EventBus:2.上拉加载
    public static final int WINNING_LOAD_MORE = 14;
    //EventBus:3.上拉加载已全加载完毕
    public static final int WINNING_LOAD_FINISH = 15;
    //EventBus:3.上拉加载 重置加载状态
    public static final int WINNING_LOAD_RESET = 16;

    //EventBus:刷新我的余额界面
    public static final int UPDATE_MINE_BALANCE = 17;

    //我的任务:是否签到
    public static final String MY_TASK_CHECK_IN = "my_task_check_in";
    //我的任务:是否下单
    public static final String MY_TASK_IS_ORDER = "my_task_is_order";
    //我的任务:今日是否还能邀请好友
    public static final String MY_TASK_IS_INVITE = "my_task_is_invite";
    //我的任务:在线时长(秒)
    public static final String MY_TASK_ONLINE_TIME = "my_task_online_time";
    //我的任务:广告点击次数
    public static final String MY_TASK_ADS_NUM = "my_task_ads_num";

    //两次点击间隔不能少于 50s
    public static final int FAST_CLICK_DELAY_TIME = 50;

    //红包点击倒计时
    public static final String FAST_CLICK_RED_BAG = "red_bag_last_click_time";
    //翻牌倒计时
    public static final String FAST_CLICK_DIVIDED_DRAGON = "divided_dragon_last_click_time";

    //信息流广告时间
    public static final long FEED_ADS_TIME = 3;

    //Service ID 倒计时
    public static final int COUNT_DOWN_TIME_SERVICE = 1000;
    //是否在审核状态 Review
    public static final String IS_REVIEW = "is_review";
    //game account
    public static final String GAME_ACCOUNT = "game_account";

    //是否已加载过
    public static final String IS_STARTED_LOADING = "isStartedLoading";
    //是否设置交易密码
    public static final String IS_SETPAY_PASS = "isSetPayPassword";
    // 刮刮卡界面没有获取到卡弹框返回
    public static final String FAIURE_POINTTOCARD_BACK = "pointToCardFalureBack";
    // 扩容界面支付成功返回
    public static final String SUCCESS_EXPANSION_BACK = "successExpansionBack";
    //显示版本时间
    public static final String VISIABLE_TIME = "2020-05-30 17:00";
    //第一次显示公告
    public static final String NOTICE_FIRST = "noticeFirst";
}
