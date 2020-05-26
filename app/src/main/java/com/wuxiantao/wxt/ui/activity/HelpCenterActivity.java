package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kf5.sdk.im.ui.KF5ChatActivity;
import com.kf5.sdk.system.internet.HttpRequestCallBack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.HelpCenterRcvViewAdapter;
import com.wuxiantao.wxt.bean.HelpCenterBean;
import com.wuxiantao.wxt.bean.MessageBean;
import com.wuxiantao.wxt.mvp.contract.HelpCenterContract;
import com.wuxiantao.wxt.mvp.presenter.HelpCenterPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.net.gson.GsonManager;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.IMUtils;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_CONTENT_TYPE;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_URL;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_URL;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:HelpCenterActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-4 下午5:20
 * Description:${DESCRIPTION} 帮助中心
 */
@ContentView(R.layout.activity_help_center)
public class HelpCenterActivity extends MvpActivity<HelpCenterPresenter, HelpCenterContract.IHelpCenterView> implements HelpCenterContract.IHelpCenterView {
    @ViewInject(R.id.help_center_rl)
    SmartRefreshLayout help_center_rl;
    @ViewInject(R.id.help_center_back)
    ImageView help_center_back;
    @ViewInject(R.id.help_center_online)
    RelativeLayout help_center_online;
    @ViewInject(R.id.help_center_rv)
    RecyclerView help_center_rv;
    @ViewInject(R.id.help_center_online_msg_count)
    TextView msg_count;

    private LoadingDialog loadingDialog;
    private int page = 1;
    private HelpCenterRcvViewAdapter adapter;
    private List<HelpCenterBean> list;

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        initRefreshLoadMore();
        setOnClikListener(help_center_back, help_center_online);
        getUnReadMessageCount();
    }

    private void getUnReadMessageCount(){
        IMUtils.getUnReadMessageCount(this, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                runOnUiThread(() -> {
                    MessageBean bean = GsonManager.newInstance().getGson().fromJson(result,MessageBean.class);
                    if (bean != null && bean.getCount() > 0){
                        msg_count.setText(getString(R.string.default_no_read_msg_count, bean.getCount()));
                    }
                });
            }

            @Override
            public void onFailure(String result) {
                ToastUtils.error(result);
            }
        });
    }

    private void initRefreshLoadMore(){
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.onGetImgList(getAppToken(),page);
        help_center_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        help_center_rl.setOnRefreshListener(refreshlayout ->{
            page = 1;
            refreshlayout.resetNoMoreData();
            loadingDialog = new LoadingDialog.Build(this).build();
            mPresenter.onGetImgList(getAppToken(),page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        help_center_rl.setOnLoadMoreListener(refreshlayout -> {
            if (list != null && !list.isEmpty()){
                loadingDialog = new LoadingDialog.Build(this).build();
                mPresenter.onGetImgList(getAppToken(),++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    protected void widgetClick(int id) {
        switch (id) {
            //返回
            case R.id.help_center_back:
                finish();
                break;
            //在线客服
            case R.id.help_center_online:
                $startActivity(KF5ChatActivity.class);
                break;
        }
    }


    @Override
    protected HelpCenterPresenter CreatePresenter() {
        return new HelpCenterPresenter();
    }

    //获取图片列表
    @Override
    public void onGetImgListSuccess(List<HelpCenterBean> list) {
        if (adapter == null){
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(0, 40);
            adapter = new HelpCenterRcvViewAdapter(this, list);
            help_center_rv.setLayoutManager(manager);
            help_center_rv.addItemDecoration(decoration);
            help_center_rv.setAdapter(adapter);
            adapter.setOnLinkClickListener(link -> {
                if (!isEmpty(link)){
                    Bundle  bundle = new Bundle();
                    bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_URL);
                    bundle.putString(WEB_VIEW_URL,link);
                    $startActivity(ProtocolActivity.class,bundle);
                }
            });
        }else {
            adapter.addList(list,page);
        }
    }




    @Override
    public void onGetImgListFailure(String failure) {
        showOnlyConfirmDialog(failure);
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
