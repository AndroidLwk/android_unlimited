package com.wuxiantao.wxt.ui.fragment.mybackpack;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.MyBackpackRecRecViewAdapter;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.presenter.MyBackpackPrewenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.utils.DensityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

@ContentView(R.layout.fragment_backpack)
public class BackPackTemapFragment extends MvpFragment<MyBackpackPrewenter, MyBackpackContract> implements MyBackpackContract {
    @ViewInject(R.id.fragment_tao_bao_featured_sub_classic_header)
    ClassicsHeader fragment_tao_bao_featured_sub_classic_header;
    @ViewInject(R.id.rv_myBackpack)
    RecyclerView rv_myBackpack;
    @ViewInject(R.id.fragment_tao_bao_featured_sub_rl)
    SmartRefreshLayout fragment_tao_bao_featured_sub_rl;
    private int page = 1;
    private MyBackpackRecRecViewAdapter mAdapter;
    private int pid;

    @Override
    protected void initView() {
        if (getArguments() != null) {
            pid = getArguments().getInt("pid");
        }
        fragment_tao_bao_featured_sub_rl.setRefreshHeader(fragment_tao_bao_featured_sub_classic_header);
        fragment_tao_bao_featured_sub_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        fragment_tao_bao_featured_sub_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.myBox(getAppToken(), page, pid);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(4, DensityUtils.dip2px(17), true);
        rv_myBackpack.addItemDecoration(itemDecoration);
        rv_myBackpack.setLayoutManager(manager);
        fragment_tao_bao_featured_sub_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getCount() >= mAdapter.getList().size()) {
                refreshlayout.finishLoadMoreWithNoMoreData();
                return;
            }
            mPresenter.myBox(getAppToken(), ++page, pid);
            refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
        });
        mPresenter.myBox(getAppToken(), page, pid);
    }


    private void initVerLayout(List<MyBoxInfo.ListBean> list) {
        if (mAdapter == null) {
            mAdapter = new MyBackpackRecRecViewAdapter(getContext(), list);
            rv_myBackpack.setAdapter(mAdapter);
        } else {
            mAdapter.addList(list == null ? null : list, page);
        }
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    private MyBoxInfo datas;

    @Override
    public void showMyBackPack(MyBoxInfo bean) {
        this.datas = bean;
        initVerLayout(bean.getList());
    }

    @Override
    public void showBoxType(List<BoxTypeBean> list) {

    }

    @Override
    protected MyBackpackPrewenter CreatePresenter() {
        return new MyBackpackPrewenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
