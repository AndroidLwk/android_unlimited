package com.wuxiantao.wxt.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.MyIncomeBean;
import com.wuxiantao.wxt.adapter.recview.MyIncomeRecViewAdapter;
import com.wuxiantao.wxt.mvp.contract.MyIncomeContract;
import com.wuxiantao.wxt.mvp.presenter.MyIncomePresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyIncomeActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:16
 * Description:${DESCRIPTION} 我的收益
 */
@ContentView(R.layout.activity_my_income)
public class MyIncomeActivity extends MvpActivity<MyIncomePresenter, MyIncomeContract.IMyIncomeView> implements MyIncomeContract.IMyIncomeView {
    @ViewInject(R.id.my_income_rl)
    SmartRefreshLayout my_income_rl;
    @ViewInject(R.id.my_income_classic_header)
    ClassicsHeader my_income_classic_header;
    @ViewInject(R.id.my_income_rv)
    RecyclerView my_income_rv;

    private MyIncomeRecViewAdapter adapter;
    private int page = 1;
    private MyIncomeBean datas;

    @Override
    protected void initView() {
        initRefreshLoadMore();
    }

    private void initRefreshLoadMore(){
        mPresenter.getMyIncomeList(getAppToken(),page);
        my_income_rl.setRefreshHeader(my_income_classic_header);
        my_income_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        my_income_rl.setOnRefreshListener(refreshlayout ->{
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.getMyIncomeList(getAppToken(),page);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        my_income_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && !datas.getList().isEmpty()){
                mPresenter.getMyIncomeList(getAppToken(),++page);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void getMyIncomeListSuccess(MyIncomeBean bean) {
        this.datas = bean;
        initLayout(bean);
    }

    private void initLayout(MyIncomeBean bean){
        if (adapter == null){
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(0,0);
            adapter = new MyIncomeRecViewAdapter(this,bean.getList());
            my_income_rv.setLayoutManager(manager);
            my_income_rv.addItemDecoration(decoration);
            my_income_rv.setAdapter(adapter);
        }else {
            adapter.addList(bean.getList(),page);
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.my_income)
                .build();
    }

    @Override
    protected MyIncomePresenter CreatePresenter() {
        return new MyIncomePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void getMyIncomeListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }
}
