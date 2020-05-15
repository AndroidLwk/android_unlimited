package com.wuxiantao.wxt.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.TaskHallFragmentAdapter;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.MyTaskInfoBean;
import com.wuxiantao.wxt.bean.TaskHallBean;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.presenter.TaskHallPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.activity.MenuActivity;
import com.wuxiantao.wxt.ui.activity.my.MyInvitationCodeActivity;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.popupwindow.ScrapingCardSuccessPopupWindow;
import com.wuxiantao.wxt.utils.AdUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MemberCenterFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-28 上午9:46
 * Description:${DESCRIPTION} 任务大厅
 */
@ContentView(R.layout.fragment_taskhall)
public class TaskHallFragment extends MvpFragment<TaskHallPresenter, TaskHallContract.ITaskHallView> implements TaskHallContract.ITaskHallView {
    @ViewInject(R.id.rv_task_one)
    NestRecyclerView rv_task_one;
    @ViewInject(R.id.tv_help)
    TextView tv_help;
    @ViewInject(R.id.tv_refresh)
    TextView tv_refresh;
    @ViewInject(R.id.rv_task_two)
    NestRecyclerView rv_task_two;
    @ViewInject(R.id.tv_friend_active_title)
    TextView tv_friend_active_title;
    @ViewInject(R.id.iv_box_a)
    ImageView iv_box_a;
    @ViewInject(R.id.iv_box_b)
    ImageView iv_box_b;
    @ViewInject(R.id.iv_box_c)
    ImageView iv_box_c;
    @ViewInject(R.id.iv_box_d)
    ImageView iv_box_d;
    @ViewInject(R.id.iv_box_e)
    ImageView iv_box_e;
    @ViewInject(R.id.lt_frinent_active_title)
    LinearLayout lt_frinent_active_title;
    @ViewInject(R.id.progress_friend)
    ProgressBar progress_friend;
    private List<TaskHallBean> mData_one = new ArrayList<>();
    private List<TaskHallBean> mData_two = new ArrayList<>();
    private TaskHallFragmentAdapter mAdapter_one, mAdapter_two;

    @Override
    public void initView() {
        mPresenter.getTaskInfo(getAppToken());
        setOnClikListener(tv_help, tv_refresh, iv_box_a, iv_box_b, iv_box_c, iv_box_d, iv_box_e);
        rv_task_one.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_task_two.setLayoutManager(new LinearLayoutManager(getContext()));
        mData_one.clear();
        mData_two.clear();
        mData_one.addAll(mPresenter.getData_one());
        mData_two.addAll(mPresenter.getData_two());
        mAdapter_one = new TaskHallFragmentAdapter(getContext(), mData_one);
        rv_task_one.setAdapter(mAdapter_one);
        mAdapter_one.setOnItemBtnClickListener(potion -> {
            switch (potion) {
                case 0:
                    mPresenter.taskSign(getAppToken());
                    break;
                case 1://游戏在线5分钟
                case 2://游戏在线10分钟
                    $startActivity(H5GameActivity.class);
                    break;
            }
        });
        mAdapter_two = new TaskHallFragmentAdapter(getContext(), mData_two);
        rv_task_two.setAdapter(mAdapter_two);
        mAdapter_two.setOnItemBtnClickListener(potion -> {
            switch (potion) {
                case 5:
                case 1:
                    MenuActivity menuActivity = (MenuActivity) getActivity();
                    menuActivity.changeFragment(0, null);
                    menuActivity.menu_tab_taobao.setChecked(true);
                    break;
                case 0://
                case 2:
                    $startActivity(H5GameActivity.class);
                    break;
                case 3:
                case 4:
                    AdUtils.initRewardVideoAd(getActivity(), () -> {
                        //点击广告
                    });
                    break;
                case 6:
                    $startActivity(MyInvitationCodeActivity.class);
                    break;
            }
        });
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.tv_help:
                break;
            case R.id.tv_refresh:
                break;
            case R.id.iv_box_a:
                mPresenter.newestActive(getAppToken(), "10");
                break;
            case R.id.iv_box_b:
                mPresenter.newestActive(getAppToken(), "30");
                break;
            case R.id.iv_box_c:
                mPresenter.newestActive(getAppToken(), "50");
                break;
            case R.id.iv_box_d:
                mPresenter.newestActive(getAppToken(), "100");
                break;
            case R.id.iv_box_e:
                mPresenter.newestActive(getAppToken(), "200");
                break;
        }
    }


    @Override
    protected TaskHallPresenter CreatePresenter() {
        return new TaskHallPresenter();
    }

    @Override
    public void gainBannerSuccess(BannerBean bean) {

    }

    @Override
    public void gainBannerFailure(String failure) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showTaskInfo(MyTaskInfoBean info) {
        mData_one.get(0).setIsFinish(info.getIs_sign());
        mData_one.get(1).setIsFinish(info.getIs_five());
        mData_one.get(2).setIsFinish(info.getOnline_thirty());
        mData_two.get(0).setIsFinish(info.getGame_charge());
        mData_two.get(1).setIsFinish(info.getProduct_charge());
        mData_two.get(2).setIsFinish(info.getBoss());
        mData_two.get(3).setIsFinish(info.getAd_click());
        mData_two.get(4).setIsFinish(info.getVideo());
        mData_two.get(5).setIsFinish(info.getProduct_see());
        mData_two.get(6).setIsFinish(info.getHusao());
        mAdapter_one.notifyDataSetChanged();
        mAdapter_two.notifyDataSetChanged();
        iv_box_a.setImageResource(info.getIs_one() == 0 ? R.drawable.task_baner_close : R.drawable.task_box_open);
        iv_box_b.setImageResource(info.getIs_two() == 0 ? R.drawable.task_baner_close : R.drawable.task_box_open);
        iv_box_c.setImageResource(info.getIs_three() == 0 ? R.drawable.task_baner_close : R.drawable.task_box_open);
        iv_box_d.setImageResource(info.getIs_four() == 0 ? R.drawable.task_baner_close : R.drawable.task_box_open);
        iv_box_e.setImageResource(info.getIs_five() == 0 ? R.drawable.task_baner_close : R.drawable.task_box_open);
        progress_friend.setProgress(info.getActive_people() / 200 * 100);
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    public void refreshData() {//切换界面时刷新数据
        if (mPresenter != null) {
            mPresenter.getTaskInfo(getAppToken());
        }
    }

    @Override
    public void showNewestActive() {//活跃度奖励领取成功业务处理
        new ScrapingCardSuccessPopupWindow.Build(getContext())
                .setWindowData("", -1)
                .setWindowAnimStyle(R.style.custom_dialog)
                .setOnClickListener(() -> mPresenter.getCard(getAppToken(), "normal"))
                .builder().showPopupWindow();

    }

    @Override
    public void signSuccess(MySignInfo info) {//签到成功业务处理
        mPresenter.getTaskInfo(getAppToken());
        new ScrapingCardSuccessPopupWindow.Build(getContext())
                .setWindowData("", -1)
                .setOnClickListener(() -> mPresenter.getCard(getAppToken(), "normal"))
                .setWindowAnimStyle(R.style.custom_dialog)
                .builder().showPopupWindow();
    }

    @Override
    public void getCardSuccess(String msg) {//获取成功后提示
        showOnlyConfirmDialog(msg);
    }
}
