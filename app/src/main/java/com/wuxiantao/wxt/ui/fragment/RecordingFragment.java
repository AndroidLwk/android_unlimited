package com.wuxiantao.wxt.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.DurationRecordingRecViewAdapter;
import com.wuxiantao.wxt.bean.LogRecordingBean;
import com.wuxiantao.wxt.mvp.contract.LogRecordingContract;
import com.wuxiantao.wxt.mvp.presenter.LogRecordingPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.RECORDING_TYPE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RecordingFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午3:50
 * Description:${DESCRIPTION} 时长记录/上限记录
 */
@ContentView(R.layout.fragment_recording)
public class RecordingFragment extends MvpFragment<LogRecordingPresenter, LogRecordingContract.IRecordingView> implements LogRecordingContract.IRecordingView {
    @ViewInject(R.id.duration_recording_rl)
    SmartRefreshLayout duration_recording_rl;
    @ViewInject(R.id.duration_recording_classic_header)
    ClassicsHeader duration_recording_classic_header;
    @ViewInject(R.id.duration_recording_rv)
    RecyclerView duration_recording_rv;

    private DurationRecordingRecViewAdapter adapter;
    private LogRecordingBean datas;
    private int page = 1;
    private LoadingDialog loadingDialog;
    private int recordingType;

    @Override
    protected LogRecordingPresenter CreatePresenter() {
        return new LogRecordingPresenter();
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        if (bundle != null){
            recordingType = bundle.getInt(RECORDING_TYPE);
        }
        loadingDialog = new LoadingDialog.Build(getContext()).build();
        mPresenter.obtainLogRecording(getAppToken(),page,1);
        duration_recording_rl.setRefreshHeader(duration_recording_classic_header);
        duration_recording_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        duration_recording_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.obtainLogRecording(getAppToken(),page,recordingType);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        duration_recording_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas.getMore() == 1){
                mPresenter.obtainLogRecording(getAppToken(),++page,recordingType);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }



    @Override
    public void obtainLogRecordingSuccess(LogRecordingBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(LogRecordingBean bean){
        if (adapter == null){
            adapter = new DurationRecordingRecViewAdapter(getContext(),bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            RecViewItemDecoration itemDecoration = new RecViewItemDecoration(getContext(),1);
            duration_recording_rv.setLayoutManager(manager);
            duration_recording_rv.addItemDecoration(itemDecoration);
            duration_recording_rv.setAdapter(adapter);
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        page = 1;
        adapter = null;
    }

    @Override
    public void obtainLogRecordingFailure(String failure) {
        ToastUtils.error(failure);
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
