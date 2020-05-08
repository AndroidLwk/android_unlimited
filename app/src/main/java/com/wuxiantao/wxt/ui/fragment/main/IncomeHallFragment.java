package com.wuxiantao.wxt.ui.fragment.main;

import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.DividedDragonRecViewAdapter;
import com.wuxiantao.wxt.bean.AreaChangeInfoBean;
import com.wuxiantao.wxt.bean.DragonStatusInfoBean;
import com.wuxiantao.wxt.bean.GameMessageBean;
import com.wuxiantao.wxt.bean.IncomeHallBean;
import com.wuxiantao.wxt.bean.IncreaseCountBean;
import com.wuxiantao.wxt.bean.OpenDragonBean;
import com.wuxiantao.wxt.bean.StartExperienceBean;
import com.wuxiantao.wxt.bean.VideoDoubleBean;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.imgloader.GlideImageLoader;
import com.wuxiantao.wxt.mvp.contract.IncomeHallContract;
import com.wuxiantao.wxt.mvp.presenter.IncomeHallPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.ChangeAreaDialog;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.FinishAimsPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.GameRewardPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OpenCardPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OperatePromptPopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_FLOP_COUNT;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_FLOP_COUNT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IncomeHallFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-18 上午9:00
 * Description:${DESCRIPTION} 游戏分红
 */
@ContentView(R.layout.fragment_income_hall)
public class IncomeHallFragment extends MvpFragment<IncomeHallPresenter, IncomeHallContract.IIncomeHallView> implements IncomeHallContract.IIncomeHallView {
    @ViewInject(R.id.income_hall_rl)
    SmartRefreshLayout income_hall_rl;
    @ViewInject(R.id.income_hall_banner)
    Banner mBanner;
    @ViewInject(R.id.income_hall_current_area)
    TextView income_hall_current_area;

    @ViewInject(R.id.income_hall_experience)
    StateButton income_hall_experience;

    @ViewInject(R.id.income_hall_progress1)
    ProgressBar income_hall_progress1;

    @ViewInject(R.id.income_hall_progress2)
    ProgressBar income_hall_progress2;
    @ViewInject(R.id.income_hall_people_level)
    TextView people_level;
    @ViewInject(R.id.income_hall_next_tv)
    TextView income_hall_next_tv;
    @ViewInject(R.id.tv_current_leave)
    TextView tv_current_leave;
    @ViewInject(R.id.tv_benefit)
    TextView tv_benefit;
    @ViewInject(R.id.tv_benefit_money)
    TextView tv_benefit_money;
    private LoadingDialog loadingDialog;

    @Override
    protected void initView() {
        loadingDialog = createLoadingDialog();
        mPresenter.getIncomeHallInfo(getAppToken());
        mPresenter.onGameMessage(getAppToken());
        mPresenter.getDragonStatusInfo(getAppToken());
        setOnClikListener(income_hall_experience
                ,income_hall_current_area);
        income_hall_rl.setEnableLoadMore(false);
        income_hall_rl.setOnRefreshListener(refreshLayout -> {
            refreshLayout.resetNoMoreData();
            onRefreshData();
            refreshLayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
    }




    @Override
    protected void widgetClick(int id) {
        switch (id) {
            //体验分红
            case R.id.income_hall_experience:
                loadingDialog = createLoadingDialog();
                mPresenter.onStartExperience(getAppToken());
                break;
            //切换区
            case R.id.income_hall_current_area:
                loadingDialog = createLoadingDialog();
                mPresenter.onChangeAreaInfo(getAppToken());
                break;
        }
    }

    //收益信息获取成功
    @Override
    public void getIncomeHallInfoSuccess(IncomeHallBean bean) {
        tv_benefit.setText(getString(R.string.game_text4,bean.getActive()));
        //阶段金额限制
        tv_benefit_money.setText(getString(R.string.game_text5,bean.getLimit()));
        //当前阶段以及加倍速度
        String spedd = bean.getMultiple();
        //当前进度
        String active = bean.getActive();
        //阶段金额限制
        String limit = bean.getLimit();
        String progress = BigDecimalUtils.div(active, limit, 2);
        income_hall_progress2.setMax(BigDecimalUtils.roundInt(limit));
        income_hall_progress2.setProgress(BigDecimalUtils.roundInt(active));

        if (bean.getIs_show() == 1){
            showFinisAimsWindow(active,BigDecimalUtils.roundStr(bean.getMoney()),spedd);
        }
    }
    //完成目标展示对话框
    private void showFinisAimsWindow(String income, String money, String speed) {
        new FinishAimsPopupWindow.Build(getContext())
                .setWindowData(income, money, speed)
                .setOnWindClickListener(this::onRefreshData)
                .builder().showPopupWindow();
    }
    private List<String> bannerList = new ArrayList<>();

    //体验次数时间
    @Override
    public void onGameMessageSuccess(GameMessageBean bean) {
        //type 1:有账号 2.没有账号
        if (bean.getType() == 2){
            showDialog(R.string.not_have_game_account, R.string.now_create_account, (dialog, which)
                    -> $startActivity(H5GameActivity.class));
        }
        //初始化banner图
        bannerList.clear();
        for (GameMessageBean.ListBean listBean : bean.getList()){
            bannerList.add(listBean.getImg());
        }
        initBanner(bannerList);
        //当前所在区
        income_hall_current_area.setText(getString(R.string.default_area,bean.getQu_name()));
        //可体验的次数
        int num = bean.getNum();
        //游戏等级
        int level = bean.getLevel();
        people_level.setText(getString(R.string.game_text3,bean.getLevel()));
        tv_current_leave.setText(getString(R.string.game_text1,bean.getLevel()));
        income_hall_progress1.setMax(150);
        income_hall_progress1.setProgress(level);
        //体验结束时间
        int end_time = bean.getInfo().getTiyan_endtime();
        if (num > 0 && bean.getType() != 2) {
            income_hall_experience.setEnabled(true);
        }
        long diff = end_time - System.currentTimeMillis() / 1000;
        if (diff > 0) {
            startDividendTime(bean.getInfo().getTiyan_money(),end_time,diff);
        } else {
            income_hall_next_tv.setText(getString(R.string.game_text2, bean.getCha()));
            //点击体验分红
            income_hall_experience.setText(getString(R.string.click_dividend_experience));
        }
    }

    private void initBanner(List<String> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        //设置图片集合
        mBanner.setImages(list);
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
        mBanner.setOnBannerListener(position -> $startActivity(H5GameActivity.class));
        mBanner.start();
    }


    //开始分红体验倒计时
    private void startDividendTime(String money,int end_time,long diff){
        //正在体验分红
        income_hall_experience.setText(getString(R.string.dividend_experience_ing));
//        income_hall_money_title.setTextColor(Color.parseColor("#FD4E4B"));
//        if (!income_hall_money_title.isStarted()){
//            income_hall_money_title.setNumber(money,diff * 1000);
//        }
        addObserver(end_time, new CountDownCallBack() {
            @Override
            public void onNext(Long time) {
                String t = DateUtils.timeParse(time);
//                income_hall_next_tv.setTextColor(Color.parseColor("#FD4E4B"));
//                income_hall_next_tv.setText(getString(R.string.dividend_experience, t));
            }

            @Override
            public void onComplete() {
                showGameRewardWindow(2,money);
            }
        });
    }

    private DividedDragonRecViewAdapter dragonRecViewAdapter;


    //获取卡牌进度信息
    @Override
    public void getDragonStatusInfoSuccess(DragonStatusInfoBean bean) {
        initDividedDragonLayout(bean.getCard_message(), bean.getCard_num());
    }

    private void initDividedDragonLayout(List<DragonStatusInfoBean.CardMessageBean> lists, int num) {

    }


    private void showDownTimeWindow(long time) {
        new OperatePromptPopupWindow.Build(getContext())
                .setCountDowntime(String.valueOf(time))
                .builder().showPopupWindow();
    }


    //卡牌开启成功
    @Override
    public void onOpenDragonSuccess(OpenDragonBean bean) {
        String title = bean.getTitle();
        int dragon_id = bean.getDragon_id();
        if (isComplete(bean.getCard_message())){
            dragonRecViewAdapter.updataList(lists,false);
        }else {
            showCompositeWindow(title,dragon_id,bean.getNum());
        }
    }

    //翻倍成功
    @Override
    public void onVideoDoubleSuccess(VideoDoubleBean bean) {
        ToastUtils.success(bean.getMsg());
        onRefreshData();
    }

    private List<DragonStatusInfoBean.CardMessageBean> lists = new ArrayList<>();

    //检测是否完成翻牌
    private  boolean isComplete(List<OpenDragonBean.CardMessageBean> beanList){
        lists.clear();
        for (OpenDragonBean.CardMessageBean bean : beanList){
            if (bean.getDragon_id() == 99){
                return false;
            }else {
                DragonStatusInfoBean.CardMessageBean cardMessageBean = new DragonStatusInfoBean.CardMessageBean();
                cardMessageBean.setDragon_id(bean.getDragon_id());
                cardMessageBean.setId(bean.getId());
                lists.add(cardMessageBean);
            }
        }
        return true;
    }


    //开始体验分红
    @Override
    public void onStartExperienceSuccess(StartExperienceBean bean) {
        showGameRewardWindow(1,String.valueOf(bean.getTiyan_endtime()));
    }

    //开始分红/分红体验结束显示对话框 type:类型 1.体验前  2.体验后
    private void showGameRewardWindow(int type,String data){
        new GameRewardPopupWindow.Build(getContext())
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .setWindowData(type,data)
                .setOnWindowDismissListener(this::onRefreshData)
                .builder().showPopupWindow();
    }


    //切换区
    @Override
    public void onChangeAreaInfoSuccess(AreaChangeInfoBean bean) {
        new ChangeAreaDialog.Builder(getContext())
                .setSelectList(bean.getList())
                .setOnAreaSelectListener(area_id -> {
                    loadingDialog = createLoadingDialog();
                    mPresenter.onBindingArea(getAppToken(),area_id);
                })
                .build().show();
    }


    //更改绑定区
    @Override
    public void onBindingAreaSuccess(List<String> msg) {
        showOnlyConfirmDialog(getString(R.string.big_area_change_success), (dialog, which) -> onRefreshData());
    }

    //看视频增加次数
    @Override
    public void onIncreaseCountSuccess(IncreaseCountBean bean) {
    }

    //刷新数据
    private void onRefreshData(){
        loadingDialog = createLoadingDialog();

        mPresenter.onGameMessage(getAppToken());
        mPresenter.getIncomeHallInfo(getAppToken());
        mPresenter.getDragonStatusInfo(getAppToken());
    }

    //显示卡牌开启五个之后对话框
    private void showCompositeWindow(String title,int dragon_id,String num) {
        new OpenCardPopupWindow.Build(getContext())
                .setWindowType(title, dragon_id,num)
                .setWindowAnimStyle(R.style.custom_dialog)
                .setOnClickListener(isVideoDouble -> {
                    //视频翻倍
                    if (isVideoDouble){
                        AdUtils.initRewardVideoAd(getActivity(), () ->
                                mPresenter.onVideoDouble(getAppToken(), dragon_id,num));
                    }else {
                        //刷新数据
                        mPresenter.getDragonStatusInfo(getAppToken());
                    }
                })
                .builder().showPopupWindow();
    }

    private LoadingDialog createLoadingDialog(){
        return new LoadingDialog.Build(getContext()).build();
    }


    @Override
    public void getIncomeHallInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }


    @Override
    public void getDragonStatusInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onOpenDragonFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onGameMessageFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onStartExperienceFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onChangeAreaInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onBindingAreaFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onIncreaseCountFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onVideoDoubleFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FLOP_COUNT && resultCode == RESULT_CODE_FLOP_COUNT) {
            mPresenter.getIncomeHallInfo(getAppToken());
            mPresenter.getDragonStatusInfo(getAppToken());
        }
    }


    @Override
    protected IncomeHallPresenter CreatePresenter() {
        return new IncomeHallPresenter();
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }


}
