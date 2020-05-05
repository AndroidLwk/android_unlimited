package com.wuxiantao.wxt.ui.fragment.main;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.BannerBean;
import com.wuxiantao.wxt.mvp.contract.TaskHallContract;
import com.wuxiantao.wxt.mvp.presenter.TaskHallPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;

import org.xutils.view.annotation.ContentView;

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
    @Override
    public void initView() {
    }


    @Override
    protected void widgetClick(int id) {

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
