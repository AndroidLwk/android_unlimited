package com.wuxiantao.wxt.ui.fragment.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.ActiveStatusBean;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.bean.VipStatusInfoBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.QualityContract;
import com.wuxiantao.wxt.mvp.presenter.QualityPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.pay.PayListener;
import com.wuxiantao.wxt.pay.PayManager;
import com.wuxiantao.wxt.ui.activity.SuperManBeneficialActivity;
import com.wuxiantao.wxt.ui.activity.VipBeneficialActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.popupwindow.OrderPayModePopupWindow;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.MEMBER_CENTER_TOPPING;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_ALI;
import static com.wuxiantao.wxt.config.Constant.PAY_TYPE_WX;
import static com.wuxiantao.wxt.config.Constant.TASK_STATUS;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MemberCenterFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-28 上午9:46
 * Description:${DESCRIPTION} 会员中心
 */
@ContentView(R.layout.activity_member_center)
public class MemberCenterFragment extends MvpFragment<QualityPresenter, QualityContract.IQualityView> implements QualityContract.IQualityView {
    @ViewInject(R.id.quality_preference_head_img)
    ImageView quality_preference_head_img;
    @ViewInject(R.id.fragment_quality_preference_member_status)
    TextView member_status;
    @ViewInject(R.id.member_center_hat)
    ImageView member_center_hat;
    @ViewInject(R.id.quality_preference_head_nick_name)
    TextView quality_preference_head_nick_name;
    @ViewInject(R.id.fragment_quality_preference__banner)
    Banner mBanner;
    @ViewInject(R.id.member_center_end_time)
    TextView member_center_end_time;
    @ViewInject(R.id.member_center_menu_img)
    ImageView member_center_menu_img;
    @ViewInject(R.id.member_center_open)
    StateButton member_center_open;

    private List<String> bannerListImg = new ArrayList<>();
    private int status = -2;
    private ArrayList<String> headImgList = new ArrayList<>();
    //开通会员的价格
    private String money;

    @Override
    public void initView() {
        //注册
        EventBus.getDefault().register(this);

        mPresenter.isActiveStatus(getAppToken());
        mPresenter.gainBanner(1);

        setOnClikListener(quality_preference_head_img, member_center_open, member_center_menu_img);
        PayListener.getInstance().addListener(this);
        MobclickAgent.onProfileSignIn("example_id");
        Toast.makeText(getContext(), "已完成用户登录", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void widgetClick(int id) {
        if (id == R.id.quality_preference_head_img) {
            if (!headImgList.isEmpty()) {
                previewImg(headImgList, 0);
            }
        }
        //开通会员
        else if (id == R.id.member_center_open) {
            MobclickAgent.onEvent(getContext(), "click", "button");
            showPayModeWindow();
        } else if (id == R.id.member_center_menu_img) {
            $startActivity(SuperManBeneficialActivity.class);
        }
    }


    //选择支付方式
    private void showPayModeWindow() {
        new OrderPayModePopupWindow.Build(getContext())
                .setOnItemClickListener(payType -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put(TOKEN, getAppToken());
                    map.put("goods_id", 9);
                    map.put("type", payType);
                    mPresenter.onOrderCreate(map, payType);
                })
                .setOrderPayMoney(money)
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .builder()
                .showPopupWindow();
    }


    @Override
    protected QualityPresenter CreatePresenter() {
        return new QualityPresenter();
    }

    //banner图获取成功
    @Override
    public void gainBannerSuccess(BannerBean bean) {
        this.money = bean.getVip();
        GlideImgManager.loadImg(getContext(), bean.getVip_img(), member_center_menu_img);

        mPresenter.getVipStatusInfo(getAppToken());
        initBanner(bean.getList());
    }


    private void initBanner(List<BannerBean.ListBean> listBeans) {
        if (bannerListImg.size() > 0) {
            bannerListImg.clear();
        }
        for (BannerBean.ListBean bean : listBeans) {
            bannerListImg.add(bean.getImg());
        }
        //设置图片集合
        mBanner.setImages(bannerListImg);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader(5.0f));
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        quality_banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(1500);
        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        quality_banner.start();
        mBanner.setOnBannerListener(position -> {
            switch (status) {
                //-1是任务失败,
                case -1:
                    //0是未参与任务
                case 0:
                    //1任务进行中
                case 1:
                    //2任务完成
                case 2:
                    Bundle bundle = new Bundle();
                    bundle.putInt(TASK_STATUS, status);
                    $startActivity(VipBeneficialActivity.class, bundle);
                    break;
            }
        });
        mBanner.start();
    }

    //会员状态获取成功
    @Override
    public void getVipStatusInfoSuccess(VipStatusInfoBean bean) {
        //0不是会员 1年会会员 2月会会员 -1会员已过期
        member_center_end_time.setVisibility(bean.getVip() != 0 ? View.VISIBLE : View.GONE);
        int vipStatus = bean.getVip();
        String time = "";
        String type = "";
        switch (vipStatus) {
            case -1:
                member_status.setText(getString(R.string.ordinary_member));
                time = getString(R.string.member_center_expired_time, DateUtils.timestampToTime(bean.getVip_end_time()));
                type = getString(R.string.member_center_open, money);
                break;
            case 0:
                member_status.setText(getString(R.string.ordinary_member));
                type = getString(R.string.member_center_open, money);
                break;
            case 1:
                member_status.setText(getString(R.string.year_member));
                time = getString(R.string.member_center_end_time, DateUtils.timestampToTime(bean.getVip_end_time()));
                type = getString(R.string.member_center_renew, money);
                break;
            case 2:
                member_status.setText(getString(R.string.month_member));
                time = getString(R.string.member_center_end_time, DateUtils.timestampToTime(bean.getVip_end_time()));
                type = getString(R.string.member_center_renew, money);
                break;
        }
        member_center_end_time.setText(time);
        member_center_open.setText(type);
    }

    //激活状态获取成功
    @Override
    public void isActiveStatusSuccess(ActiveStatusBean bean) {
        this.status = bean.getStatus();
        String head_img = getUserInfo(USER_HEAD_IMG);
        String nickname = getUserInfo(NICK_NAME);
        headImgList.clear();
        headImgList.add(head_img);
        if (!isEmpty(head_img)) {
            GlideImgManager.loadCircleImg(getContext(), head_img, quality_preference_head_img);
        } else {
            GlideImgManager.loadCircleImg(getContext(), R.drawable.ic_person_outline_black_24dp, quality_preference_head_img);
        }
        quality_preference_head_nick_name.setText(nickname);
    }

    //订单号
    private String order_sn;

    //微信支付
    @Override
    public void onWeChatPay(WeChatPayBean infoBean) {
        order_sn = infoBean.getOrder_id();
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_WX, infoBean);
    }

    //支付宝支付
    @Override
    public void onAliPay(String order_id, String res) {
        order_sn = order_id;
        PayManager.getInstance(getActivity()).pay(PAY_TYPE_ALI, res);
    }

    //请求服务器查询订单
    @Override
    public void onPaySuccess() {
        if (!isEmpty(order_sn)) {
            mPresenter.checkOrderStatus(getAppToken(), order_sn);
        }
    }

    //支付成功
    @Override
    public void onOrderPaySuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.getVipStatusInfo(getAppToken()));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == MEMBER_CENTER_TOPPING) {

        }
    }

    @Override
    public void isActiveStatusFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void getVipStatusInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void onDestroy() {
        //结束轮播
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
        //注册
        EventBus.getDefault().unregister(this);
        PayListener.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onOrderCreateFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOrderPayFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void gainBannerFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void onPayError() {
        showOnlyConfirmDialog(getString(R.string.order_pay_error));
    }

    @Override
    public void onPayCancel() {
        showOnlyConfirmDialog(getString(R.string.order_pay_cancel));
    }
}
