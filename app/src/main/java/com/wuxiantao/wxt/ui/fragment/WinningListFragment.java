package com.wuxiantao.wxt.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.WinningListRecViewAdapter;
import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.WinningListContract;
import com.wuxiantao.wxt.mvp.presenter.WinningListPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.WINNING_LIST_TYPE;
import static com.wuxiantao.wxt.config.Constant.WINNING_LOAD_FINISH;
import static com.wuxiantao.wxt.config.Constant.WINNING_LOAD_MORE;
import static com.wuxiantao.wxt.config.Constant.WINNING_LOAD_RESET;
import static com.wuxiantao.wxt.config.Constant.WINNING_REFRESH;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:40
 * Description:${DESCRIPTION} 中奖名单
 */
@ContentView(R.layout.fragment_winning_list)
public class WinningListFragment extends MvpFragment<WinningListPresenter, WinningListContract.IWinningListView> implements WinningListContract.IWinningListView {
    @ViewInject(R.id.fragment_winning_list_rv)
    RecyclerView fragment_winning_list_rv;

    private int page = 1;
    private WinningListRecViewAdapter adapter;
    //中奖名单列表类型  0.铜钥匙  1.银钥匙 2.金钥匙
    private int type = -1;
    private WinningListBean datas;

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(WINNING_LIST_TYPE);
        }
        mPresenter.getWinningList(getAppToken(), page, type);
    }


    @Override
    public void onPause() {
        super.onPause();
        adapter = null;
        page = 1;
    }

    @Override
    public void getWinningListSuccess(WinningListBean bean) {
        this.datas = bean;
        initLayout(bean);
    }


    private void initLayout(WinningListBean bean) {
        if (adapter == null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(0, 0);
            adapter = new WinningListRecViewAdapter(getContext(), bean.getList());
            fragment_winning_list_rv.addItemDecoration(decoration);
            fragment_winning_list_rv.setLayoutManager(manager);
            fragment_winning_list_rv.setAdapter(adapter);
        } else {
            if (bean.getList().isEmpty()) {
                EventBus.getDefault().post(new MessageEvent(WINNING_LOAD_FINISH));
            }
            adapter.addList(bean.getList(), page);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String index = event.getMessage();
        switch (event.getType()) {
            //下拉刷新
            case WINNING_REFRESH:
                EventBus.getDefault().post(new MessageEvent(WINNING_LOAD_RESET));
                page = 1;
                //铜钥匙
                if (type == 0 && index.equals("0")) {
                    mPresenter.getWinningList(getAppToken(), page, type);
                }
                //银钥匙
                else if (type == 1 && index.equals("1")) {
                    mPresenter.getWinningList(getAppToken(), page, type);
                }
                //金钥匙
                else if (type == 2 && index.equals("2")) {
                    mPresenter.getWinningList(getAppToken(), page, type);
                }
                break;
            //上拉加载
            case WINNING_LOAD_MORE:
                //铜钥匙
                if (type == 0 && index.equals("0")) {
                    mPresenter.getWinningList(getAppToken(), ++page, type);
                }
                //银钥匙
                else if (type == 1 && index.equals("1")) {
                    mPresenter.getWinningList(getAppToken(), ++page, type);
                }
                //金钥匙
                else if (type == 2 && index.equals("2")) {
                    mPresenter.getWinningList(getAppToken(), ++page, type);
                }
                break;
        }
    }


    @Override
    protected WinningListPresenter CreatePresenter() {
        return new WinningListPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public void getWinningListFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }
}
