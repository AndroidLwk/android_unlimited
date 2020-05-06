package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.TaskHallBean;
import com.wuxiantao.wxt.mvp.banner.BaseBannerPresenter;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.model.ConfirmOrderModel;
import com.wuxiantao.wxt.mvp.model.QualityModel;
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
public class TaskHallPresenter extends BaseBannerPresenter<TaskHallContract.ITaskHallView> {

    private TaskHallContract.ITaskHallView view;
    private QualityModel model = new QualityModel();
    private ConfirmOrderModel orderModel = new ConfirmOrderModel();

    public void checkOrderStatus(String token, String order_no) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List s) {
                // view.onOrderPaySuccess(RESOURCES.getString(R.string.pay_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                //view.onOrderPayFailure(errorMsg);
            }
        };
        orderModel.checkOrderStatus(observer, token, order_no);
    }

    public List<TaskHallBean> getData_one() {
        List<TaskHallBean> list = new ArrayList<>();
        TaskHallBean taskHallBean = new TaskHallBean(R.drawable.main_taskhall_g, "签到奖励 + 1张刮刮卡");
        list.add(taskHallBean);
        taskHallBean = new TaskHallBean(R.drawable.main_taskhall_c, "登陆奖励 + 1张刮刮卡");
        list.add(taskHallBean);
        taskHallBean = new TaskHallBean(R.drawable.main_taskhall_m, "游戏在线10分钟 +1张刮刮卡");
        list.add(taskHallBean);
        return list;
    }

    public List<TaskHallBean> getData_two() {
        List<TaskHallBean> list = new ArrayList<>();
        TaskHallBean taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_d, "今日游戏充值 +1张刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_k, "下载app +1张刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_b, "触发boss 有几率掉落刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_l, "游戏在线30分钟 +1张刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_f, "观看小视频广告 有几率掉率刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_e, "浏览首页商品10分钟 有几率掉率刮刮卡");
        list.add(taskHallBean2);
        taskHallBean2 = new TaskHallBean(R.drawable.main_taskhall_h, "浏览首页商品10分钟 有几率掉率刮刮卡");
        list.add(taskHallBean2);
        return list;
    }
}
