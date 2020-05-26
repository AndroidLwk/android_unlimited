package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.PhoneLoginBean;
import com.wuxiantao.wxt.mvp.contract.TestContract;
import com.wuxiantao.wxt.mvp.presenter.TestPrensenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.progress.CircleProgressBar;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.LogUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.test_view_layout)
public class TestTwoActivity extends MvpActivity<TestPrensenter, TestContract.ITestView> implements TestContract.ITestView {
    @ViewInject(R.id.tv_test)
    TextView tv_test;
    @ViewInject(R.id.bt_test)
    Button bt_test;
    @ViewInject(R.id.circleIndicator)
    CircleProgressBar circleIndicator;
    @Override
    protected void initView(Bundle savedInstanceState) {
        circleIndicator.setProgress(20);
        setOnClikListener(tv_test,bt_test);
    }
    private LoadingDialog loadingDialog;
    @Override
    protected void widgetClick(int id) {
        switch (id){
            case R.id.tv_test:
                ToastUtils.showToast("kwg sdf");
                LogUtils.loge("tv_test", "");
                break;
            case R.id.bt_test:
                loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.login_loading).build();
                mPresenter.getData();
                break;
        }
    }

    @Override
    protected TestPrensenter CreatePresenter() {
        return new TestPrensenter();
    }
    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();

    }
    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }
    @Override
    public void showUi(PhoneLoginBean response) {

    }

    @Override
    public void error_login(String msg) {
        showOnlyConfirmDialog(msg);

    }

    @Override
    public void loginSuccess(PhoneLoginBean response) {

    }

    @Override
    public void loginFailure(String failure) {
        showOnlyConfirmDialog(failure);

    }
}
