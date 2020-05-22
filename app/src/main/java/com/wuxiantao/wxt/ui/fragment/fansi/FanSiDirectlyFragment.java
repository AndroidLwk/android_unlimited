package com.wuxiantao.wxt.ui.fragment.fansi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.FanSiDirectlyRecViewAdapter;
import com.wuxiantao.wxt.bean.FansiDetailBean;
import com.wuxiantao.wxt.bean.FansiDirectlyBean;
import com.wuxiantao.wxt.mvp.fansi.c.FansiDirectlyContract;
import com.wuxiantao.wxt.mvp.fansi.p.FanSiDirectlyPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.ShareThemActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.FanSiInfoPopupWindow;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.FANSI_TYPE_POTENTIAL;
import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:FanSiPotentialFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午5:10
 * Description:${DESCRIPTION} 直属粉丝
 */
@ContentView(R.layout.fragment_fansi_directly)
public class FanSiDirectlyFragment extends MvpFragment<FanSiDirectlyPresenter, FansiDirectlyContract.IFansiView> implements FansiDirectlyContract.IFansiView {
    @ViewInject(R.id.fansi_directly_rl)
    SmartRefreshLayout fansi_directly_rl;
    @ViewInject(R.id.fansi_directly_classic_header)
    ClassicsHeader fansi_directly_classic_header;
    @ViewInject(R.id.fansi_directly_rv)
    RecyclerView fansi_directly_rv;
    @ViewInject(R.id.sbt_share_code)
    StateButton sbt_share_code;
    private FanSiDirectlyRecViewAdapter adapter;
    private FansiDirectlyBean datas;
    private LoadingDialog loadingDialog;
    private int page = 1;
    private Map<String,Object> parameters = new HashMap<>();

    @Override
    protected FanSiDirectlyPresenter CreatePresenter() {
        return new FanSiDirectlyPresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(sbt_share_code);
        loadingDialog = new LoadingDialog.Build(getContext()).build();
        parameters.put(TOKEN,getAppToken());
//        parameters.put(TOKEN,"o1voQ1Xik7iCxobahGFXoBpi1KS8");
        parameters.put("page",page);
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("type",FANSI_TYPE_POTENTIAL);
        mPresenter.obtainFansi(parameters);
        initRefreshLoad();

    }
    @Override
    protected void widgetClick(int id) {
        if(id==R.id.sbt_share_code){
            $startActivity(ShareThemActivity.class);
        }
    }
    private void initRefreshLoad(){
        fansi_directly_rl.setRefreshHeader(fansi_directly_classic_header);
        fansi_directly_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        fansi_directly_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            parameters.put("page",page);
            mPresenter.obtainFansi(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        fansi_directly_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas.getCount()>page){
                parameters.put("page",++page);
                mPresenter.obtainFansi(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void obtainFansSuccess(FansiDirectlyBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(FansiDirectlyBean bean){
        if (adapter == null){
            adapter = new FanSiDirectlyRecViewAdapter(getContext(),bean.getList());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,20);
            fansi_directly_rv.setLayoutManager(manager);
            fansi_directly_rv.addItemDecoration(itemDecoration);
            fansi_directly_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(new FanSiDirectlyRecViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int uid) {
                    //mPresenter.obtainFansiDetail(uid);
                }

                @Override
                public void onInviteFriend() {
                    $startActivity(ShareThemActivity.class);
                }
            });
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    @Override
    public void obtainFansFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter = null;
        page = 1;
    }

    @Override
    public void obtainFansiDetailSuccess(FansiDetailBean bean) {
        showFansiInfoWindow(bean);
    }

    private void showFansiInfoWindow(FansiDetailBean bean){
        new FanSiInfoPopupWindow.Build(getContext())
                .setWindowDatas(bean)
                .setOnCopyClickListener(new FanSiInfoPopupWindow.OnCopyClickListener() {
                    @Override
                    public void onCopyWeChatId(String wechat) {
                        TextViewUtils.copy(wechat);
                    }

                    @Override
                    public void onCopyInviteId(String inviteId) {
                        TextViewUtils.copy(inviteId);
                    }
                })
                .setPopupWindowAnimStyle(R.style.popupwindow_anim)
                .setPopupWindowWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .builder().showPopupWindow(Gravity.CENTER);
    }

    @Override
    public void obtainFansiDetailFailure(String failure) {
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
