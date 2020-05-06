package com.wuxiantao.wxt.ui.fragment.main;

import android.support.annotation.StringRes;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MineMenuBean;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.presenter.MinePresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.MineFanSiActivity;
import com.wuxiantao.wxt.ui.activity.MoreIncomeActivity;
import com.wuxiantao.wxt.ui.activity.MyInformationActivity;
import com.wuxiantao.wxt.ui.activity.SettingActivity;
import com.wuxiantao.wxt.ui.activity.my.MyMemberActivity;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.IS_SHOW_SAMALL_ICON;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TYPE_NORMAL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 我的
 */
@ContentView(R.layout.fragment_my_deposit)
public class MyDepositFragment extends MvpFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {
    @ViewInject(R.id.mine_classic_header)
    ClassicsHeader mine_classic_header;
    @ViewInject(R.id.mine_rl)
    SmartRefreshLayout mine_rl;
    @ViewInject(R.id.tv_mine_member)
    TextView tv_mine_member;
    @ViewInject(R.id.tv_mine_friend)
    TextView tv_mine_friend;
    @ViewInject(R.id.tv_mine_code)
    TextView tv_mine_code;
    @ViewInject(R.id.tv_mine_order)
    TextView tv_mine_order;
    @ViewInject(R.id.tv_mine_profit)
    TextView tv_mine_profit;
    @ViewInject(R.id.tv_mine_set)
    TextView tv_mine_set;
    @ViewInject(R.id.tv_mine_freedback)
    TextView tv_mine_freedback;
    @ViewInject(R.id.rt_personInfo)
    TextView rt_personInfo;
    private LoadingDialog loadingDialog;

    @Override
    protected MinePresenter CreatePresenter() {
        return new MinePresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(tv_mine_member, tv_mine_set,
                tv_mine_friend, tv_mine_code, tv_mine_order, tv_mine_profit, tv_mine_freedback, rt_personInfo);
        loadingDialog = createLoadingDialog();
        initRefreshLoad();
    }

    private LoadingDialog createLoadingDialog() {
        return new LoadingDialog.Build(getContext()).build();
    }

    private void initRefreshLoad() {
        mine_classic_header.setBackgroundResource(R.drawable.base_title_background);
        mine_rl.setRefreshHeader(mine_classic_header);
        mine_rl.setEnableLoadMore(false);
        mine_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            if (!isEmpty(getAppToken())) {
                //  mPresenter.obtainMyDeposit(getAppToken());
            }
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });

    }


    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.tv_mine_member:
                $startActivity(MyMemberActivity.class);
                break;
            case R.id.tv_mine_friend:
                $startActivity(MineFanSiActivity.class);
                break;
            case R.id.tv_mine_code:
                break;
            case R.id.tv_mine_order:
                break;
            case R.id.tv_mine_profit:
                $startActivity(MoreIncomeActivity.class);
                break;
            case R.id.tv_mine_set:
                $startActivity(SettingActivity.class);
                break;
            case R.id.tv_mine_freedback:
                break;
            case R.id.rt_personInfo:
                $startActivity(MyInformationActivity.class);
                break;
        }
    }
    private MineMenuBean createMineMenuBean(int icon, @StringRes int title) {
        MineMenuBean bean = new MineMenuBean(icon, getString(title), isUserAuthorization(IS_SHOW_SAMALL_ICON));
        bean.setType(TYPE_NORMAL);
        return bean;
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
    public void onDestroy() {
        super.onDestroy();
    }
}
