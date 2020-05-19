package com.wuxiantao.wxt.config;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:Api
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午5:54
 * Description:${DESCRIPTION} api接口类
 */
public class Api {

    //switch
    public static final String DKX_SWITCH = "https://www.doukx.com/index/index/get_type";

    //base url
    public static final String BASE_URL = "https://chaoren.haowusong.com/api/";

    //domain name
    public static final String DOMAIN_NAME = "https://chaoren.haowusong.com/";

    //手机注册
    public static final String PHONE_REG = "Phone/phone_reg";

    //微信登陆
    public static final String WECHAT_LOGIN = "User/appLogin";

    //微信绑定
    public static final String WECHAT_BINDING = "user/wechatBind";

    //账号密码登陆/验证码登陆
    public static final String PHONE_LOGIN = "Phone/phone_login";

    //获取验证码
    public static final String OBTAIN_CODE = "Sendsms/index";

    //重置密码
    public static final String RESET_PASS_WORD = "phone/phone_setpassword";

    //编辑个人中心
    public static final String UPDATE_PERSONAL_CENTER = "user/personal";

    //获取我的存款
    public static final String OBTAIN_MY_DEPOSIT = "user/myDeposit";

    //我的页面2019/8/14
    public static final String OBTAIN_MY_PAGE = "Nativebank/profit";

    //个人中心
    public static final String OBTAIN_PERSONAL = "user/personal";

    //文件上传
    public static final String UPLOAD_FILE = "user/uploadFile";

    //个人中心设置密码、修改密码
    public static final String SET_USER_PASS_WORD = "user/setPassword";

    //我的粉丝头部数据信息
    public static final String OBTAIN_FANSI_HEAD_INFO = "user/myfansup";

    //好友列表  1徒弟2徒孙
//    public static final String OBTAIN_FANSI = "user/myfans";
    public static final String OBTAIN_FANSI = "newest/friendsList";

    //粉丝详情
    public static final String FANSI_DETAIL = "user/fansDetail";

    //帮助中心
    public static final String HELP_CENTER_TITLE = "user/myHelpNew";

    //帮助中心 详情
    public static final String HELP_CENTER_DETAILS = "user/helpDetail";

    //余额明细
//    public static final String BALANCE_DETAIL = "user/DetailBalance";
    public static final String BALANCE_DETAIL = "newest/moneyRecord";

    //刮刮卡明细
    public static final String SCRATCH_CARD_DETAIL = "newest/ggkDetail";

    //提现记录 withdraw
    public static final String WITHDRAW_RECORDING = "user/tixianlog";

    //存款明细 佣金收益明细记录8/15
    public static final String DEPOSIT_DETAIL = "user/savings";

    //排行榜 1今日排行 2 总收益排行
    public static final String LEADERBOARD_RECORDING = "user/rankingList";

    //手气值记录
    public static final String HAND_VALUE_RECORDING = "super/getLuckLog";

    //红包手气值
    public static final String OBTAIN_LUCK = "super/luck";

    //超人日志记录
    public static final String SUPER_LOG_RECORDING = "super/getDepLog";

    //我的余额和累计提现金额
//    public static final String OBTAIN_BALANCE = "user/myAmount";
    public static final String OBTAIN_BALANCE = "newest/myMoneyCash";

    //余额提现申请
    public static final String BALANCE_WITHDRAW = "user/withdraw";

    //派出超人
    public static final String SEND_SUPER = "super/sendSuper";

    //获取超人信息
    public static final String OBTAIN_SUPER_INFO = "super/getDep";

    //看视频加上限
    public static final String VEDIO = "super/vediol";

    //年化利率等级
    public static final String OBTAIN_RATE = "user/addRate";

    //轮播图接口
    public static final String GAIN_BANNER = "goods/banner";

    //VIP礼包列表
    public static final String GAIN_VIP_LIST = "goods/vipGoods";

    //自营商品详情
    public static final String OBTAIN_COMMODITY_INFO = "goods/detail";

    //确认订单
    public static final String CREATE_ORDER = "pay/createOrder";

    //验证订单状态
    public static final String CHECK_ORDER_STATUS = "pay/checkOrderStatus";

    //收货地址修改
    public static final String EDIT_ADDRESS = "user/addressUpdate";

    //收货地址添加
    public static final String ADD_ADDRESS = "user/addressAdd";

    //设置默认收货地址
    public static final String SET_DEFAULT_ADDRESS = "User/addressDefault";

    //获取默认收货地址
    public static final String GET_DEFAULT_ADDRESS = "User/addressm";

    //设置默认收货地址
    public static final String DELETE_ADDRESS = "User/addressDel";

    //获取收货地址列表
    public static final String GET_ADDRESS_LIST = "User/address";

    //省钱教程
    public static final String SAVE_MONEY_INSTRUCT = "goods/shengqian";

    //分享背景图列表
    public static final String GET_SHARE_BG = "user/getShareBackgroundPic";

    //生成分享二维码
    public static final String CREATE_SHARE_CODE = "user/getSharePic";

    //推广语
    public static final String GET_TUI_GUANG = "user/tuiguang";

    //分享进群红包统计
//    public static final String CREATE_SHARE_CODE = "user/getSharePic";

    //七日年化页面 - 收益记录 ProfitRecording
    public static final String PROFIT_RECORDING = "user/senveny";

    //邀请奖励存款 （昨天前天今天）
    public static final String DEPOSIT_DAY = "user/depositDay";

    //今日分享排行榜
    public static final String TODAY_SHARE = "user/todayShare";

    //参与邀请好友免费领取会员活动
    public static final String FREE_RECEIVE_MEMBER_ACTIVE = "user/active";

    //邀请好友活动状态
    public static final String IS_ACTIVE_STATUS = "user/isActiveStatus";

    //获取会员状态信息
    public static final String MEMBER_STATUS_INFO = "user/vipStatus";

    //获取邀请好友个数
    public static final String INVITE_FRIEND_NUM = "user/getFriendNum";

    //按邀请好友人数申请成为会员 邀请人数达到30人后，需要手动领取月会员
    public static final String APPLY_VIP = "user/isShareVip";

    //按邀请好友人数申请成为合伙人
    public static final String APPLY_PARTNER = "user/sharePartner";

    //淘宝热搜关键词 每次返回10个词组
    public static final String HOT_KEYWORD = "weyitao/hot_search";

    //搜索记录
    public static final String SEARCH_RECORDING = "weyitao/searchLog";

    //淘宝搜索、分类接口
    public static final String TAOBAO_SEARCH = "weyitao/search";

    //淘宝商品详情
    public static final String GET_TAOBAO_DETAIL = "weyitao/detail";

    //淘宝分类
    public static final String CATE_SORT = "weyitao/catelist";

    //淘宝优选 首页接口 淘宝商品
    public static final String TAO_BAO_HOME = "weyitao/tuijian";

    //淘宝优选 首页接口 自营商品
    public static final String SELF_EMPLOYED = "goods/tuijian";

    //淘宝授权
    public static final String TAO_BAO_LOGIN = "weyitao/Taologin";

    //每日红包上限、收取红包个数初始化
    public static final String RED_LIMIT_TODAY = "weyitao/renewRedLimit";

    //自营订单列表
    public static final String SELF_ORDER_LIST = "user/myOrderList";

    //淘宝订单列表
    public static final String TABAO_ORDER_LIST = "weyitao/getOrderList";

    //自营订单详情
    public static final String GET_YOUXUAN_ORDER_DETAIL = "user/orderDetail";

    //淘宝订单详情
    public static final String GET_TAOBAO_ORDER_DETAIL = "weyitao/taoOrderDetail";

    //最近订单接口
    public static final String RECENT_ORDER = "user/latelyOrder";

    //购物清单列表 shopping
    public static final String SHOPPING_LIST = "goods/goodslist";

    //最近订单状态
    public static final String GET_ORDER_TYPE = "user/orderType";

    //邀请提高年化利率气泡展示 好友助力-弹幕 Boost
    public static final String GOOD_FRIEND_BOOST = "user/getratelog";

    //超人隐私政策 Privacy Policy
    public static final String PRIVACY_POLICY = "https://chaoren.haowusong.com/sever/privacy.html";

    //用户协议 User Agreement
    public static final String USER_AGREEMENT = "https://chaoren.haowusong.com/sever/sever.html";

    //好友列表2019/8/14
    public static final String RED_BAG_FRIEND_LIST = "Nativebank/friend_step";

    //获取红包信息
    public static final String GET_RED_BAG_INFO = "nativebank/getNewInfo";

    //开启红包
    public static final String UNFASTEN_RED_BAG = "nativebank/openNewPackage";

    //打造金币
    public static final String MAKE_GOLD = "nativebank/makeGold";
    //翻倍打造金币
    public static final String MAKE_GOLD_DOUBLE = "nativebank/makeDoubleGold";

    //分享发放奖励
    public static final String SHARE_SEND_REWARD = "news/shareAward";

    //获取佣金提现信息
    public static final String GET_COMMISSION_WITHDRAW_INFO = "user/tixianList";

    //获取红包提现信息
    public static final String GET_RED_BAG_WITHDRAW_INFO = "user/redList";

    //签到8/15 Check in
    public static final String CHECK_IN = "user/taskSign";

    //获取签到信息8/15
    public static final String GET_CHECK_IN_INFO = "user/getSignCount";

    //红包提现
    public static final String RED_BAG_WITHDRAW = "user/redWithdraw";

    //佣金提现
    public static final String COMMISSION_WITHDRAW = "user/rateWithdraw";

    // 0元购和高拥专区
    public static final String HIGH_AREA_ZERO = "goods/zeroBuy";

    // 在线客服
    public static final String ON_LINE_SERVICE = "user/zxkefu";

    // 新手领取红包 receive
    public static final String NEW_USER_RECEIVE_RB = "user/newMemberGetAward";

    // 激活好友/助手
    public static final String ACTIVATE_FRIEND = "nativebank/activateNew";

    // 获取好友列表
    public static final String GET_FRIEND_LIST = "nativebank/friendList";

    // 激活好友/助手 双倍
    public static final String ACTIVATE_FRIEND_DOUBLE = "nativebank/activateNewDouble";

    // 分享/点击链接增加1元红包
    public static final String SHARE_RED_BAG_LINK = "news/reg";

    //获取当前app版本
    public static final String GET_APP_VERSION = "user/version";

    //获取抽奖列表
    public static final String GET_LOTTERY_LIST = "wheel/wheelList";
    //抽奖
    public static final String ON_LOTTERY = "wheel/prize";
    //抽奖页钥匙日志-铜钥匙
    public static final String LOTTERY_COPPER_KEY_LOG = "wheel/wheelCopperLog";
    //抽奖页钥匙日志-金钥匙
    public static final String LOTTERY_GOLD_KEY_LOG = "wheel/wheelGoldLog";
    //抽奖页钥匙日志-银钥匙
    public static final String LOTTERY_SILVER_KEY_LOG = "wheel/wheelSilverLog";
    //抽奖页所有日志
    public static final String LOTTERY_ALL_LOG = "wheel/wheelAllLog";

    //我的抽奖日志/抽奖记录
    public static final String LOTTERY_RECORDING = "wheel/wheelMyLog";

    //分红详情页数据
    public static final String DIVIDED_DRAGON_DETAIL = "card/redDragonMessage";
    //分红龙日志
    public static final String DIVIDED_DRAGON_LIST = "card/redDragonLog";

    //用户阶段及收益信息  收益大厅
    public static final String INCOME_HALL_INFO = "card/stageInfo";

    //报名分红
    public static final String ENROLL_BONUS = "Scratchcard/enrollBonus";

    //平台分红龙信息  收益大厅
    public static final String PLATFORM_DRAGON_INFO = "card/redDragonInfo";

    //新卡牌信息    收益大厅
    public static final String GET_DRAGON_STATUS_INFO = "game/cardInfo";

    //翻牌  收益大厅
    public static final String ON_OPEN_DRAGON = "game/openCard";

    //看视频五倍  收益大厅
    public static final String ON_WATCH_VIDEO_FIVE = "game/moreAward";

    //体验次数时间  收益大厅
    public static final String ON_GAME_MESSAGE = "game/gameMessage";

    //开始体验分红  收益大厅
    public static final String ON_START_EXPERIENCE = "game/startTiyan";

    //游戏区个数-切换区  收益大厅
    public static final String ON_GAME_AREA_CHANGE = "news/quList";

    //更改绑定的区   收益大厅
    public static final String ON_BINDING_AREA = "game/bindQu";

    //看视频增加翻牌次数    收益大厅
    public static final String ON_INCREASE_COUNT = "game/addCardNum";

    //我的游戏
    public static final String MY_GAME_INFO = "game/myGameInfo";

    //游戏载入图
    public static final String GET_GAME_LOADING_IMG = "news/gamePic";

    //生成游戏订单
    public static final String CREATE_PAY_ORDER = "pay/charge";

    //任务详情 我的任务
    public static final String MY_TASK_INFO = "card/taskInfo";

    //用户启动app或从后台转入
    public static final String ON_START_APP = "card/startApp";
    //用户离开app或转入后台
    public static final String ON_STOP_APP = "card/leaveApp";

    //我的收益信息 列表
    public static final String GET_MY_INCOME_LIST = "card/myStageMoneyInfo";

    //合成分红龙详情 获取用户各种龙的数量信息
    public static final String GET_DRAGON_INFO = "card/dragonInfo";

    //碎片合成
    public static final String COMPOSITE_SCRAP = "card/compose";

    //福禄寿喜财五龙合成分红龙
    public static final String COMPOSITE_DRAGON = "card/fiveCompose";

    //广告贡献
    public static final String ON_ADS_DEVOTE = "card/adAward";

    //游戏支付 type类型:1.支付宝 2.微信 3.分享
    public static final String H5_WE_CHAT_PAY = "http://chaoren.haowusong.com/api/pay";
    //友盟APPKEY
    public static final String UM_APPKEY = "5e614e02895cca527e000862";
    //友盟 Push推送业务的secret
    public static final String UM_MESSAGE_SECRECT = "0daccf39fffcbbab9413d9d98ee324eb";
    //刮刮卡页面信息
    public static final String MY_CARDINFO = "Scratchcard/myCardInfo";
    //任务大厅信息
    public static final String TASK_INFO = "Task/taskInfo";
    //活跃度奖励领取
    public static final String NEWEST_ACTIVE = "newest/active";
    //任务页签到
    public static final String TASK_SIGN = "Task/sign";
    //领取刮出的卡片
    public static final String GET_CARD = "Scratchcard/getCard";
    //我的背包
    public static final String MY_BOX = "Scratchcard/myBox";
    //我的卷轴
    public static final String MY_SCROLL = "Scratchcard/myScroll";
    //碎片合成英雄卡
    public static final String COMPOSE_HERO = "Scratchcard/composeHero";
    //开始刮卡
    public static final String START_STRAPING = "Scratchcard/startStraping";
    //我的幸运值(狂点页面)
    public static final String MY_LUCKYINFO = "Scratchcard/myLuckyInfo";
    //分享二维码图片
    public static final String GET_SHAREPIC = "newest/getSharePic";
    //背包分类
    public static final String GET_BOXCATE = "Scratchcard/getBoxCate";
    //卷轴分类
    public static final String GET_SCROLLCATE = "Scratchcard/getScrollCate";
    //我的余额和累计提现金额
    public static final String MY_MONEYCASH = "newest/myMoneyCash";
    //使用卡片
    public static final String USE_CARD = "Scratchcard/userCard";
    //销毁卡片
    public static final String DIS_CARD = "Scratchcard/discard";
    /*转赠刮刮卡和碎片 start*/
    public static final String EXCHANGE = "newest/exchange";//余额
    public static final String EXCHANGE_ALIPAY = "newest/exchange";
    public static final String EXCHANGE_WX = "newest/exchange";
    /*转赠刮刮卡和碎片 end*/
    //生成充值订单
    public static final String CREATE_CZ_ORDER = "payment/chargeMoney";

    // 是否设置支付密码
    public static final String IS_SET_PAY_PWD = "newest/isSetPayPassword";

    // 设置或修改交易密码
    public static final String SET_CHANGE_PAY_PWD = "newest/setPayPassword";
    //扩容页面信息
    public static final String KUORONG_INFO = "newest/kuorongInfo";
    /*背包扩容 start*/
    public static final String ADDBOX_BALANCE = "payment/addBox";//余额
    public static final String ADDBOX_ALIPAY = "payment/addBox";//支付宝
    public static final String ADDBOX_WX = "payment/addBox";//微信
    /*背包扩容 end*/
    // 看视频浏览商品触发Boss点击广告任务奖励
    public static final String RANDGET_CARD = "Task/randGetCard";
    // 签到看视频双倍
    public static final String SIGN_DOUBLE = "task/signDouble";
}
