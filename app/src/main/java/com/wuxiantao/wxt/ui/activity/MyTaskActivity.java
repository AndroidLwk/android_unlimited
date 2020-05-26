package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.DailyTaskBean;
import com.wuxiantao.wxt.adapter.bean.MyTaskBean;
import com.wuxiantao.wxt.adapter.recview.DailyTaskRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.RecommendedTaskRecViewAdapter;
import com.wuxiantao.wxt.bean.TaskInfoBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.MyTaskContract;
import com.wuxiantao.wxt.mvp.presenter.MyTaskPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.NumberFormatUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.WeChatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.IS_BINDING_WECHAT;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_ADS_NUM;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_CHECK_IN;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_IS_INVITE;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_IS_ORDER;
import static com.wuxiantao.wxt.config.Constant.MY_TASK_ONLINE_TIME;
import static com.wuxiantao.wxt.config.Constant.PUBLIC_NICKNAME;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_FLOP_COUNT;
import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_ERROR;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_SUCCESS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyTaskActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午12:50
 * Description:${DESCRIPTION} 我的任务
 */
@ContentView(R.layout.activity_my_task)
public class MyTaskActivity extends MvpActivity<MyTaskPresenter, MyTaskContract.ITaskView> implements MyTaskContract.ITaskView {
    @ViewInject(R.id.my_task_rl)
    SmartRefreshLayout my_task_rl;
    @ViewInject(R.id.my_task_classic_header)
    ClassicsHeader my_task_classic_header;
    @ViewInject(R.id.my_task_back)
    ImageView my_task_back;
    @ViewInject(R.id.my_task_flop_energy)
    TextView my_task_flop_energy;
    @ViewInject(R.id.my_task_grade)
    TextView my_task_grade;
    @ViewInject(R.id.my_task_grade_progress)
    ProgressBar my_task_grade_progress;
    @ViewInject(R.id.my_task_grade_progress_tv)
    TextView my_task_grade_progress_tv;
    @ViewInject(R.id.my_task_daily_task_rv)
    RecyclerView my_task_daily_task_rv;
    @ViewInject(R.id.my_task_recommended_task_rv)
    RecyclerView my_task_recommended_task_rv;
    @ViewInject(R.id.my_task_recommended_task_layout)
    LinearLayout my_task_recommended_task_layout;

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setStatusBar();
        initRefreshLoad();
        setOnClikListener(my_task_back);
    }

    //获取信息成功
    @Override
    public void getTaskInfoSuccess(TaskInfoBean bean) {
        //是否关注公众号
        boolean is_focus = bean.getIs_focus() == 1;
        //是否绑定手机号
        boolean is_phone = bean.getIs_phone() == 1;
        //是否绑定微信
        boolean is_wx = bean.getIs_wx() == 1;
        //是否填写微信号
        boolean is_write = bean.getIs_wechat() == 1;
        int visibility = is_focus && is_phone && is_wx && is_write ? View.GONE : View.VISIBLE;
        my_task_recommended_task_layout.setVisibility(visibility);
        saveUserInfo(IS_BINDING_WECHAT,is_wx);
        //任务完成次数
        int task_num = bean.getTask_num();
        //翻牌能量(次数)
        int card_num = bean.getCard_num();
        //任务星级
        int task_star = bean.getTask_star();
        //task_full
        int task_full = bean.getTask_full();
        my_task_flop_energy.setText(String.valueOf(card_num));
        my_task_grade.setText(String.valueOf(task_star));
        my_task_grade_progress_tv.setText(getString(R.string.task_grade, NumberFormatUtils.numberToChar(task_star)));
        my_task_grade_progress.setMax(task_full);
        my_task_grade_progress.setProgress(task_num);

        initDailyTaskLayout(bean);
        initRecommendedTaskLayout(is_write,is_focus,is_phone,is_wx);
    }


    @Override
    protected void widgetClick(int id) {
        if (id == R.id.my_task_back) {
            getResult(RESULT_CODE_FLOP_COUNT);
        }
    }

    private void initRefreshLoad() {
        mPresenter.getTaskInfo(getAppToken());
        my_task_classic_header.setBackgroundResource(R.drawable.base_title_background);
        my_task_rl.setRefreshHeader(my_task_classic_header);
        my_task_rl.setEnableLoadMore(false);
        my_task_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            mPresenter.getTaskInfo(getAppToken());
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
    }


    private DailyTaskRecViewAdapter dailyAdapter;
    private RecommendedTaskRecViewAdapter recommendedAdapter;

    //日常任务
    private void initDailyTaskLayout(TaskInfoBean bean) {
        if (dailyAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(10, 0);
            dailyAdapter = new DailyTaskRecViewAdapter(this, initDailyTask(bean),initDailyTaskMap(bean));
            my_task_daily_task_rv.setLayoutManager(manager);
            my_task_daily_task_rv.addItemDecoration(decoration);
            my_task_daily_task_rv.setAdapter(dailyAdapter);
            dailyAdapter.setOnItemClickListener(new DailyTaskRecViewAdapter.OnItemClickListener() {
                @Override
                public void onCheckIn() {
                    //签到
                    AdUtils.initRewardVideoAd(MyTaskActivity.this,() -> mPresenter.checkIn(getAppToken()));
                }

                @Override
                public void onBuyOrder() {
                    //每日一单
                    $startActivity(MenuActivity.class,SHIFT_ID,1);
                }

                @Override
                public void onInviteFriend() {
                    //邀请好友
                    $startActivity(ShareThemActivity.class);
                }

                @Override
                public void onAdsDevote() {
                    //广告贡献
                    AdUtils.initNativeInteractionAd(MyTaskActivity.this, (view, type) ->
                            mPresenter.onAdsDevote(getAppToken()));
                }
            });
        }else {
            dailyAdapter.updataList(initDailyTask(bean));
            dailyAdapter.upDateMap(initDailyTaskMap(bean));
        }
    }



    //推荐任务
    private void initRecommendedTaskLayout(boolean is_write,boolean is_focus,boolean is_phone,boolean is_wx) {
        if (recommendedAdapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(10, 0);
            recommendedAdapter = new RecommendedTaskRecViewAdapter(this, initRecommendedTask(is_write,is_focus,is_phone,is_wx));
            my_task_recommended_task_rv.setLayoutManager(manager);
            my_task_recommended_task_rv.addItemDecoration(decoration);
            my_task_recommended_task_rv.setAdapter(recommendedAdapter);
            recommendedAdapter.setOnItemClickListener(new RecommendedTaskRecViewAdapter.OnItemClickListener() {
                //绑定微信
                @Override
                public void onBindWeChat() {
                    WeChatUtils.sendWeChatLoginRequest(true);
                }
                //个人中心设置
                @Override
                public void onSettingInfo() {
                    $startActivity(MyInformationActivity.class);
                }
                //复制公众号
                @Override
                public void onCopyNoPublic() {
                    TextViewUtils.copy(PUBLIC_NICKNAME);
                }
            });
        } else {
            recommendedAdapter.updataList(initRecommendedTask(is_write,is_focus,is_phone,is_wx));
        }
    }

    private List<Integer> dailyTaskIcons = new ArrayList<>();
    private List<MyTaskBean> dailyTaskList = new ArrayList<>();
    private Map<String,Object>  dailyTaskMap = new HashMap<>();

    private List<MyTaskBean> initDailyTask(TaskInfoBean bean) {
        dailyTaskIcons.clear();
        dailyTaskList.clear();
        dailyTaskIcons.add(R.mipmap.daily_task_check_in);
        dailyTaskIcons.add(R.mipmap.daily_task_one_order);
        dailyTaskIcons.add(R.mipmap.daily_task_invite_five);
        dailyTaskIcons.add(R.mipmap.daily_task_online_30);
        dailyTaskIcons.add(R.mipmap.daily_task_ads_contribution);
        String[] titles = getResources().getStringArray(R.array.item_daily_task_title);
        for (int i = 0; i < titles.length; i++) {
            DailyTaskBean taskBean = new DailyTaskBean();
            taskBean.setIcon(dailyTaskIcons.get(i));
            taskBean.setTitle(titles[i]);
            switch (i){
                case 0:
                    taskBean.setContent(getString(R.string.handsel_regx_opportunity,bean.getAward_one()));
                    break;
                case 1:
                    taskBean.setContent(getString(R.string.handsel_regx_opportunity,bean.getAward_two()));
                    break;
                case 2:
                    taskBean.setContent(getString(R.string.handsel_regx_opportunity,bean.getAward_three()));
                    break;
                case 3:
                    taskBean.setContent(getString(R.string.handsel_regx_opportunity,bean.getAward_four()));
                    break;
                case 4:
                    taskBean.setContent(getString(R.string.handsel_regx_opportunity,bean.getAward_five()));
                    break;
            }
            dailyTaskList.add(taskBean);
        }
        return dailyTaskList;
    }

    private Map<String,Object> initDailyTaskMap(TaskInfoBean bean){
        dailyTaskMap.clear();
        dailyTaskMap.put(MY_TASK_CHECK_IN,bean.getIs_sign() == 1);
        dailyTaskMap.put(MY_TASK_IS_ORDER,bean.getIs_order() == 1);
        dailyTaskMap.put(MY_TASK_IS_INVITE,bean.getFriends_num() > 5);
        dailyTaskMap.put(MY_TASK_ONLINE_TIME,bean.getLongX());
        dailyTaskMap.put(MY_TASK_ADS_NUM,bean.getAd_num());
        return dailyTaskMap;
    }


    private List<MyTaskBean> recommendedTaskList = new ArrayList<>();


    private List<MyTaskBean> initRecommendedTask(boolean is_write,boolean is_focus,boolean is_phone,boolean is_wx) {
        recommendedTaskList.clear();
        int icon = R.mipmap.recommended_task_write;
        String title = getString(R.string.write_wechat_no);
        String content = getString(R.string.handsel_two_opportunity);
        //未填写微信号
        if (!is_write){
            //填写微信号
            recommendedTaskList.add(createMyTaskBean(icon, title, content,MyTaskBean.WRITE_WECHAT_NO));
        }
        content = getString(R.string.handsel_one_opportunity);
        //是否关注公众号
        if (!is_focus) {
            icon = R.mipmap.recommended_task_attention;
            title = getString(R.string.attention_wechat);
            recommendedTaskList.add(createMyTaskBean(icon, title, content,MyTaskBean.ATTENTION_WECHAT));
        }
        //未绑定手机号
        if (!is_phone) {
            icon = R.mipmap.recommended_task_binding;
            title = getString(R.string.banding_number);
            recommendedTaskList.add(createMyTaskBean(icon, title, content,MyTaskBean.BINDING_NUMBER));
        }
        //未绑定微信
        if (!is_wx) {
            icon = R.mipmap.recommended_task_binding;
            title = getString(R.string.bingding_wechat);
            recommendedTaskList.add(createMyTaskBean(icon, title, content,MyTaskBean.BINDING_CHAT));
        }
        return recommendedTaskList;
    }

    private MyTaskBean createMyTaskBean(Integer icon, String title, String content,int type) {
        return new MyTaskBean(icon, title, content,type);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //监听返回键
            getResult(RESULT_CODE_FLOP_COUNT);
        }
        return super.onKeyDown(keyCode, event);
    }

    //签到成功
    @Override
    public void checkInSuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.getTaskInfo(getAppToken()));
    }

    //广告贡献成功
    @Override
    public void onAdsDevoteSuccess(String msg) {
        mPresenter.getTaskInfo(getAppToken());
    }

    @Override
    public void checkInFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected MyTaskPresenter CreatePresenter() {
        return new MyTaskPresenter();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        if (event.getType() == UPDATE_WECHAT_BINGDING_SUCCESS){
            mPresenter.getTaskInfo(getAppToken());
        }
        else if (event.getType() == UPDATE_WECHAT_BINGDING_ERROR){
            showOnlyConfirmDialog(event.getMessage());
        }
    }

    @Override
    public void onAdsDevoteFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void getTaskInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
