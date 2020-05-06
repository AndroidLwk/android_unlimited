package com.wuxiantao.wxt.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.TaskHallFragmentAdapter;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.bean.TaskHallBean;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.presenter.TaskHallPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;

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
    @ViewInject(R.id.tv_task_title)
    TextView tv_task_title;
    @ViewInject(R.id.rv_task_one)
    NestRecyclerView rv_task_one;
    @ViewInject(R.id.tv_help)
    TextView tv_help;
    @ViewInject(R.id.tv_refresh)
    TextView tv_refresh;
    @ViewInject(R.id.rv_task_two)
    NestRecyclerView rv_task_two;
    private List<TaskHallBean> mData_one = new ArrayList<>();
    private List<TaskHallBean> mData_two = new ArrayList<>();

    @Override
    public void initView() {
        setOnClikListener(tv_help, tv_refresh);
        rv_task_one.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_task_two.setLayoutManager(new LinearLayoutManager(getContext()));
        mData_one.clear();
        mData_two.clear();
        mData_one.addAll(mPresenter.getData_one());
        mData_two.addAll(mPresenter.getData_two());
        TaskHallFragmentAdapter mAdapter_one = new TaskHallFragmentAdapter(getContext(), mData_one);
        rv_task_one.setAdapter(mAdapter_one);
        mAdapter_one.setOnItemBtnClickListener(potion -> {
            switch (potion) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        });
        TaskHallFragmentAdapter mAdapter_two = new TaskHallFragmentAdapter(getContext(), mData_two);
        rv_task_two.setAdapter(mAdapter_two);
        mAdapter_two.setOnItemBtnClickListener(potion -> {
            switch (potion) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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

}
