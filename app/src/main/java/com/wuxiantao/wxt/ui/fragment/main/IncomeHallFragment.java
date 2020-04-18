package com.wuxiantao.wxt.ui.fragment.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wuxiantao.wxt.ui.activity.DividendDetailsActivity;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.activity.MineFanSiActivity;
import com.wuxiantao.wxt.ui.activity.MyTaskActivity;
import com.wuxiantao.wxt.ui.activity.PlayActivity;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.textview.RunNumTextView;
import com.wuxiantao.wxt.ui.dialog.ChangeAreaDialog;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.FinishAimsPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.GameRewardPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OpenCardPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.OperatePromptPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.RedBagMachinePopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.DateUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
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
    @ViewInject(R.id.fragment_income_hall_rv)
    RecyclerView fragment_income_hall_rv;
    @ViewInject(R.id.fragment_income_hall_play)
    LinearLayout hall_play;
    @ViewInject(R.id.income_hall_next_tv)
    TextView income_hall_next_tv;
    @ViewInject(R.id.income_hall_current_area)
    TextView income_hall_current_area;
    @ViewInject(R.id.income_hall_detail)
    LinearLayout income_hall_detail;
    @ViewInject(R.id.income_hall_super_peoples)
    TextView super_peoples;
    @ViewInject(R.id.income_hall_people_counts)
    TextView people_counts;
    @ViewInject(R.id.income_hall_experience)
    StateButton income_hall_experience;
    @ViewInject(R.id.income_hall_people_level)
    TextView people_level;
    @ViewInject(R.id.income_hall_progress1)
    ProgressBar income_hall_progress1;
    @ViewInject(R.id.income_hall_money_title)
    RunNumTextView income_hall_money_title;

    @ViewInject(R.id.income_hall_total)
    TextView income_hall_total;
    @ViewInject(R.id.income_hall_income)
    TextView income_hall_income;
    @ViewInject(R.id.income_hall_total_income)
    TextView total_income;
    @ViewInject(R.id.income_hall_speed)
    TextView income_hall_speed;
    @ViewInject(R.id.income_hall_progress2)
    ProgressBar income_hall_progress2;
    @ViewInject(R.id.income_hall_unlock)
    TextView income_hall_unlock;
    @ViewInject(R.id.fragment_income_hall_invite)
    StateButton invite_friend;
    @ViewInject(R.id.income_hall_card_count)
    TextView card_count;
    @ViewInject(R.id.income_hall_more_count)
    ImageView moreCount;
    @ViewInject(R.id.income_hall_friend_count)
    LinearLayout income_hall_friend_count;
    @ViewInject(R.id.income_hall_get_count)
    ImageView income_hall_get_count;

    private LoadingDialog loadingDialog;

    @Override
    protected void initView() {
        loadingDialog = createLoadingDialog();
        mPresenter.getIncomeHallInfo(getAppToken());
        mPresenter.onGameMessage(getAppToken());
        mPresenter.getDragonStatusInfo(getAppToken());
        setOnClikListener(invite_friend, hall_play, total_income, moreCount,
                income_hall_detail, income_hall_experience, income_hall_speed,
                income_hall_friend_count,income_hall_current_area,income_hall_get_count);
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
            //总收入问号
            case R.id.income_hall_total_income:
            case R.id.income_hall_speed:
                showMessageWindow();
                break;
            //邀请
            case R.id.fragment_income_hall_invite:
                $startActivity(ShareThemActivity.class);
                break;
            //玩法
            case R.id.fragment_income_hall_play:
                $startActivity(PlayActivity.class);
                break;
            //更多次数
            case R.id.income_hall_more_count:
                $startActivityForResult(MyTaskActivity.class, REQUEST_CODE_FLOP_COUNT);
                break;
            //获取次数
            case R.id.income_hall_get_count:
                AdUtils.initRewardVideoAd(getActivity(), () -> {
                    loadingDialog = createLoadingDialog();
                    mPresenter.onIncreaseCount(getAppToken());
                });
                break;
            //分红详情
            case R.id.income_hall_detail:
                $startActivityForResult(DividendDetailsActivity.class, REQUEST_CODE_FLOP_COUNT);
                break;
            //体验分红
            case R.id.income_hall_experience:
                loadingDialog = createLoadingDialog();
                mPresenter.onStartExperience(getAppToken());
                break;
            //好友人数
            case R.id.income_hall_friend_count:
                $startActivity(MineFanSiActivity.class);
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
        //当前总收入
        income_hall_total.setText(bean.getActive());
        //阶段金额限制
        String limit = bean.getLimit();
        income_hall_income.setText(limit);
        //当前阶段以及加倍速度
        String spedd = bean.getMultiple();
        income_hall_speed.setText(getString(R.string.income_speed, bean.getStage_level(), spedd));
        //当前进度
        String active = bean.getActive();
        String progress = BigDecimalUtils.div(active, limit, 2);
        income_hall_progress2.setMax(BigDecimalUtils.roundInt(limit));
        income_hall_progress2.setProgress(BigDecimalUtils.roundInt(active));
        income_hall_unlock.setText(getString(R.string.unlock_progress, progress, "%", limit));
        if (bean.getIs_show() == 1){
            showFinisAimsWindow(active,BigDecimalUtils.roundStr(bean.getMoney()),spedd);
        }
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
        income_hall_current_area.setText(bean.getQu_name());
        //今日超级玩家分红
        super_peoples.setText(getString(R.string.divided_dragon_unit_price, bean.getSuper_money()));
        //超级玩家数量
        people_counts.setText(String.valueOf(bean.getSuper_total()));
        //可体验的次数
        int num = bean.getNum();
        //游戏等级
        int level = bean.getLevel();
        people_level.setText(getString(R.string.people_level, bean.getLevel()));
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
            income_hall_money_title.setTextColor(Color.parseColor("#000000"));
            //还差多少级体验下次分红
            income_hall_next_tv.setTextColor(Color.parseColor("#000000"));
            String s = getString(R.string.next_upgrade, bean.getCha());
            TextViewUtils.setTextViewKeyWordHighlight(income_hall_next_tv, s,
                    Color.parseColor("#FD4E4B"), new Pair<>(9, s.length()));
            //每升15级体验分红
            String experience = getString(R.string.experience_msg);
            TextViewUtils.setTextViewKeyWordHighlight(income_hall_money_title, experience,
                    Color.parseColor("#FD4E4B"), new Pair<>(7,9));
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
        income_hall_money_title.setTextColor(Color.parseColor("#FD4E4B"));
        if (!income_hall_money_title.isStarted()){
            income_hall_money_title.setNumber(money,diff * 1000);
        }
        addObserver(end_time, new CountDownCallBack() {
            @Override
            public void onNext(Long time) {
                String t = DateUtils.timeParse(time);
                income_hall_next_tv.setTextColor(Color.parseColor("#FD4E4B"));
                income_hall_next_tv.setText(getString(R.string.dividend_experience, t));
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
        card_count.setText(String.valueOf(num));
        if (dragonRecViewAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(0);
            SpaceItemDecoration decoration = new SpaceItemDecoration(40, 0);
            dragonRecViewAdapter = new DividedDragonRecViewAdapter(getContext(), lists);
            fragment_income_hall_rv.setLayoutManager(manager);
            fragment_income_hall_rv.addItemDecoration(decoration);
            fragment_income_hall_rv.setAdapter(dragonRecViewAdapter);
            dragonRecViewAdapter.setOnOpenDividedDragonListener(new DividedDragonRecViewAdapter.OnOpenDividedDragonListener() {
                @Override
                public void onOpenDividedDragon(int id) {
                    loadingDialog = createLoadingDialog();
                    mPresenter.onOpenDragon(getAppToken(), id);
                }

                @Override
                public void onAnimationEnd() {
                    mPresenter.getDragonStatusInfo(getAppToken());
                }
            });
        } else {
            dragonRecViewAdapter.updataList(lists);
        }
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
        card_count.setText(String.valueOf(bean.getCard_num()));
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


    //提示对话框
    private void showMessageWindow() {
        new RedBagMachinePopupWindow.Build(getContext())
                .setWindowTitle(getString(R.string.tips))
                .setWindowMessage(getString(R.string.missed_income_msg))
                .setPopupWindowAnimStyle(R.style.custom_dialog)
                .builder()
                .showPopupWindow();
    }


    //完成目标展示对话框
    private void showFinisAimsWindow(String income, String money, String speed) {
        new FinishAimsPopupWindow.Build(getContext())
                .setWindowData(income, money, speed)
                .setOnWindClickListener(this::onRefreshData)
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
