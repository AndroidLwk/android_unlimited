package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MoreThemRecViewAdapter;
import com.wuxiantao.wxt.bean.ShareBackGroundBean;
import com.wuxiantao.wxt.mvp.contract.ShareMoreThemContract;
import com.wuxiantao.wxt.mvp.presenter.ShareMoreThemPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.RESULT_SHARE_MORE_THEM;
import static com.wuxiantao.wxt.config.Constant.SHARE_THEM_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MoreThemActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-6 下午6:11
 * Description:${DESCRIPTION} 更多主题
 */
@ContentView(R.layout.activity_more_them)
public class MoreThemActivity extends MvpActivity<ShareMoreThemPresenter, ShareMoreThemContract.IShareMoreView> implements ShareMoreThemContract.IShareMoreView {
    @ViewInject(R.id.more_them_rl)
    SmartRefreshLayout more_them_rl;
    @ViewInject(R.id.more_them_classics_header)
    ClassicsHeader more_them_classics_header;
    @ViewInject(R.id.more_them_rv)
    RecyclerView more_them_rv;

    private MoreThemRecViewAdapter adapter;
    private LoadingDialog loadingDialog;
    private ShareBackGroundBean datas;
    private int page = 1;

    @Override
    public void initView(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.loading).build();
        mPresenter.getShareBg(page,PAGE_SIZE);
        more_them_rl.setRefreshHeader(more_them_classics_header);
        more_them_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        more_them_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getShareBg(page,PAGE_SIZE);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        more_them_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                mPresenter.getShareBg(++page,PAGE_SIZE);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.more_them).build();
    }

    @Override
    protected ShareMoreThemPresenter CreatePresenter() {
        return new ShareMoreThemPresenter();
    }

    @Override
    public void getShareBgSuccess(ShareBackGroundBean bean) {
        this.datas = bean;
        initVerLayout(bean);
    }

    //垂直列表
    private void initVerLayout(ShareBackGroundBean bean){
        if (adapter == null){
            adapter = new MoreThemRecViewAdapter(this,bean.getList());
            GridLayoutManager manager = new GridLayoutManager(this,3);
            GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3,20,true);
            more_them_rv.setLayoutManager(manager);
            more_them_rv.addItemDecoration(itemDecoration);
            more_them_rv.setAdapter(adapter);
            adapter.setOnBaseViewClickListener(id -> {
                Bundle bundle = new Bundle();
                bundle.putInt(SHARE_THEM_ID,id);
                getResult(RESULT_SHARE_MORE_THEM,bundle);
            });
        }else {
            adapter.addList(bean.getList(),page);
        }
    }


    @Override
    public void getShareBgFailure(String failure) {
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
