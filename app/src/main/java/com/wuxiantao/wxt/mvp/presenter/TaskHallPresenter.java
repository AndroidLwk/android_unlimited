package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.MyTaskInfoBean;
import com.wuxiantao.wxt.bean.TaskHallBean;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.model.TaskHallFragmentModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:QualityPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午3:44
 * Description:${DESCRIPTION}
 */
public class TaskHallPresenter extends BasePresenter<TaskHallContract.ITaskHallView> {

    private TaskHallContract.ITaskHallView view;
    private TaskHallFragmentModel model = new TaskHallFragmentModel();

    /**
     * 获取任务信息
     */
    public void getTaskInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MyTaskInfoBean> observer = new BaseObserver<MyTaskInfoBean>() {
            @Override
            public void onSuccess(MyTaskInfoBean info) {
                view.showTaskInfo(info);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getMyTaskInfo(observer, token);
    }

    /**
     * 获取好友活跃信息
     */
    public void newestActive(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List s) {
                view.showNewestActive();
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.newestActive(observer, token, type);
    }

    /**
     * 签到
     */
    public void taskSign(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MySignInfo> observer = new BaseObserver<MySignInfo>() {
            @Override
            public void onSuccess(MySignInfo info) {
                view.signSuccess(info);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.taskSign(observer, token);
    }

    /**
     * 领取刮出的卡片
     */
    public void getCard(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<CardInfoBean> observer = new BaseObserver<CardInfoBean>() {
            @Override
            public void onSuccess(CardInfoBean info) {
                view.getCardSuccess(info.getMsg());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getCard(observer, token, type);
    }

    public List<TaskHallBean> getData_one() {
        List<TaskHallBean> list = new ArrayList<>();
        TaskHallBean taskHallBean = new TaskHallBean(R.drawable.main_taskhall_g, "签到奖励 + 1张刮刮卡");
        taskHallBean.setSign(true);
        list.add(taskHallBean);
        taskHallBean = new TaskHallBean(R.drawable.main_taskhall_c, "登录斩妖诀游戏且在线5分钟+1张刮刮卡");
        taskHallBean.setSign(true);
        list.add(taskHallBean);
        taskHallBean = new TaskHallBean(R.drawable.main_taskhall_m, "游戏在线30分钟 +1张刮刮卡");
        taskHallBean.setSign(true);
        list.add(taskHallBean);
        return list;
    }

    public List<TaskHallBean> getData_two() {
        List<TaskHallBean> list = new ArrayList<>();
        TaskHallBean taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_d, "今日游戏充值 +1张刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.task_today_home, "每日商品首次下单  +3张刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_b, "触发boss 有几率掉落刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.task_today_video_a, "点击激励视频广告  +1张");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.task_today_video, "观看激励视频  必定掉率刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_e, "浏览首页商品10分钟 有几率掉率刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.task_today_code, "好友互扫 上限10次");
        list.add(taskHallBean2);
        return list;
    }
}
