package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.VipBeneficialContract;
import com.wuxiantao.wxt.mvp.presenter.VipBeneficialPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.TASK_STATUS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:VipBeneficialActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-21 上午11:09
 * Description:${DESCRIPTION} 免费获VIP权益
 */
@ContentView(R.layout.activity_vip_beneficial)
public class VipBeneficialActivity extends MvpActivity<VipBeneficialPresenter, VipBeneficialContract.IVipBeneficialView> implements VipBeneficialContract.IVipBeneficialView {
    @ViewInject(R.id.vip_beneficial_sv)
    ScrollView vip_beneficial_sv;
    @ViewInject(R.id.vip_beneficial_rl)
    SmartRefreshLayout vip_beneficial_rl;
    @ViewInject(R.id.vip_beneficial_doing_rule)
    RelativeLayout vip_beneficial_doing_rule;
    @ViewInject(R.id.vip_beneficial_join_activity)
    StateButton vip_beneficial_join_activity;
    @ViewInject(R.id.vip_beneficial_understanding_more)
    TextView vip_beneficial_understanding_more;

    private LoadingDialog loadingDialog;
    private int status = -2;

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle bundle = getBundle();
        if (bundle != null) {
            status = bundle.getInt(TASK_STATUS);
            switch (status) {
                //-1是任务失败,
                case -1:
                //0是未参与任务
                case 0:
                //2任务完成
                case 2:
                    vip_beneficial_join_activity.setText(getResources().getString(R.string.Immediately_canjia));
                    break;
                //1任务进行中
                case 1:
                    vip_beneficial_join_activity.setText(getResources().getString(R.string.view_task_progress));
                    break;
            }
        }
        vip_beneficial_rl.setEnableRefresh(false);
        vip_beneficial_rl.setEnableLoadMore(false);
        setOnClikListener(vip_beneficial_doing_rule, vip_beneficial_join_activity, vip_beneficial_understanding_more);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //活动规则
            case R.id.vip_beneficial_doing_rule:
                //滚到到底部
                vip_beneficial_sv.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            //立即参加活动/查看任务进度
            case R.id.vip_beneficial_join_activity:
                switch (status) {
                    //-1是任务失败,
                    case -1:
                        //0是未参与任务
                    case 0:
                        //2任务完成
                    case 2:
                        loadingDialog = new LoadingDialog.Build(this)
                                .setLoadingText(R.string.request_join_active_loading).build();
                        mPresenter.receiveMemberActive(getAppToken());
                        break;
                        //1任务进行中
                    case 1:
                        $startActivity(ParticipateActivity.class);
                        break;
                }
                break;
            //了解更多权益
            case R.id.vip_beneficial_understanding_more:
                $startActivity(SuperManBeneficialActivity.class);
                break;
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.free_vip_quanyhi)).build();
    }

    @Override
    protected VipBeneficialPresenter CreatePresenter() {
        return new VipBeneficialPresenter();
    }

    @Override
    public void receiveSuccess(String msg) {
        $startActivity(ParticipateActivity.class);
    }

    @Override
    public void receiveFailure(String failure) {
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
