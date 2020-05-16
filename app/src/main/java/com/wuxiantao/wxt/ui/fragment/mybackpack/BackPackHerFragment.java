package com.wuxiantao.wxt.ui.fragment.mybackpack;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.kf5.sdk.im.ui.KF5ChatActivity;
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
import com.wuxiantao.wxt.ui.activity.scrapingcard.HeroScrollActivity;
import com.wuxiantao.wxt.ui.popupwindow.PackOperationPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.TransferScratchCardPopupWindow;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * 英雄碎片
 */
@ContentView(R.layout.fragment_backpack)
public class BackPackHerFragment extends MvpFragment<MyBackpackPrewenter, MyBackpackContract> implements MyBackpackContract {
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
        fragment_tao_bao_featured_sub_rl.setEnableLoadMore(false);
        fragment_tao_bao_featured_sub_rl.setRefreshHeader(fragment_tao_bao_featured_sub_classic_header);
        fragment_tao_bao_featured_sub_rl.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        fragment_tao_bao_featured_sub_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            page = 1;
            mPresenter.myBox(getAppToken(), page, pid);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        rv_myBackpack.setLayoutManager(manager);

        mPresenter.myBox(getAppToken(), page, pid);
    }

    private MyBoxInfo.ListBean myBackpackBean;//点击事件的数据

    //操作弹框
    private void showOperationDialog(MyBoxInfo.ListBean myBackpackBean) {
        this.myBackpackBean = myBackpackBean;
        new PackOperationPopupWindow.Build(getContext())
                .setWindowAnimStyle(R.style.custom_dialog)
                .setButton(myBackpackBean)
                .setOnPopupClickListener(new PackOperationPopupWindow.Build.OnPopupClickListener() {
                    @Override
                    public void goOpenCard() {
                        getActivity().finish();
                    }

                    @Override
                    public void cardTransfer() {
                        new TransferScratchCardPopupWindow.Build(getContext())
                                .setWindowAnimStyle(R.style.custom_dialog)
                                .setTitleText(myBackpackBean.getPid() == 1 ? getResources().getString(R.string.pointtocard_text15) : getResources().getString(R.string.pointtocard_text18))
                                .setOnItemClickListener((id, num, sxxh, pass) -> {
                                    if (id.equals("null")) {
                                        showOnlyConfirmDialog("转赠ID不能为空");
                                    } else if (num.equals("null")) {
                                        showOnlyConfirmDialog("转赠数量不能为空");
                                    } else if (sxxh.equals("null")) {
                                        showOnlyConfirmDialog("手续消耗不能为空");
                                    } else if (pass.equals("null")) {
                                        showOnlyConfirmDialog("二级密码不能为空");
                                    } else {
                                        //调接口
                                        mPresenter.exchange(getAppToken(), myBackpackBean.getCard_id() + "", id, pass, num);
                                    }
                                })
                                .builder().showPopupWindow();
                    }

                    @Override
                    public void carUse() {
                        mPresenter.useCard(getAppToken(), myBackpackBean.getCard_id() + "", "1");
                    }

                    @Override
                    public void synthesis() {
                        getActivity().finish();
                        $startActivity(HeroScrollActivity.class);
                    }

                    @Override
                    public void discard() {
                        mPresenter.discard(getAppToken(), myBackpackBean.getCard_id() + "", "1");
                    }
                })
                .builder().showPopupWindow();

    }

    private void initVerLayout(List<MyBoxInfo.ListBean> list) {
        if (mAdapter == null) {
            mAdapter = new MyBackpackRecRecViewAdapter(getContext(), list);
            mAdapter.setOnItemClickListener((myBackpackBean, position) -> {
                if (!TextUtils.isEmpty(myBackpackBean.getImg())) {
                    showOperationDialog(myBackpackBean);
                }

            });
            rv_myBackpack.setAdapter(mAdapter);
        } else {
            mAdapter.addList(list == null ? null : list, page);
        }
    }

    @Override
    public void onFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void exchangeSuccess() {
        showOnlyConfirmDialog("转赠成功");
        mPresenter.myBox(getAppToken(), page, pid);
    }

    @Override
    public void useCardSuccess(String msg) {
        if (myBackpackBean.getPid() == 2) {//皮肤卡使用
            $startActivity(KF5ChatActivity.class);
            mPresenter.myBox(getAppToken(), page, pid);
        } else {//现金卡使用
            getActivity().finish();
        }
    }

    @Override
    public void discardSuccess(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void showMyBackPack(MyBoxInfo bean) {
        initVerLayout(mPresenter.getBoxAllData(bean.getList(), bean.getBox_volume()));
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
