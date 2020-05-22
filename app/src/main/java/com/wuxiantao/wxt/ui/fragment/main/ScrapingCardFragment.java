package com.wuxiantao.wxt.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ScrapingCardFragmentOneAdapter;
import com.wuxiantao.wxt.adapter.recview.ScrapingCardFragmentThreeAdapter;
import com.wuxiantao.wxt.adapter.recview.ScrapingCardFragmentTwoAdapter;
import com.wuxiantao.wxt.bean.MyCardInfo;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.ScrapingCardBean;
import com.wuxiantao.wxt.mvp.contract.ScrapingCardFragmentContract;
import com.wuxiantao.wxt.mvp.presenter.ScrapingCardFragmentPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.H5GameActivity;
import com.wuxiantao.wxt.ui.activity.HelpCenterActivity;
import com.wuxiantao.wxt.ui.activity.MenuActivity;
import com.wuxiantao.wxt.ui.activity.my.MyInvitationCodeActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.HeroScrollActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.MyBackpackActivity;
import com.wuxiantao.wxt.ui.activity.scrapingcard.PointToCardActivity;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.popupwindow.TradingHallPopupWindow;

import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-29 下午4:53
 * Description:${DESCRIPTION} 刮刮卡
 */
@ContentView(R.layout.fragment_scrapingcard)
public class ScrapingCardFragment extends MvpFragment<ScrapingCardFragmentPresenter, ScrapingCardFragmentContract.IScrapingCardFragmentView> implements ScrapingCardFragmentContract.IScrapingCardFragmentView {
    @ViewInject(R.id.deposit_super_man_rl)
    SmartRefreshLayout deposit_super_man_rl;
    @ViewInject(R.id.deposit_super_man_classic_header)
    ClassicsHeader deposit_super_man_classic_header;
    @ViewInject(R.id.tv_help)
    TextView tv_help;
    @ViewInject(R.id.rv_scrapingcard_one)
    NestRecyclerView rv_scrapingcard_one;
    @ViewInject(R.id.tv_more)
    TextView tv_more;
    @ViewInject(R.id.tv_my_heroscroll)
    TextView tv_my_heroscroll;
    @ViewInject(R.id.nrv_info)
    NestRecyclerView nrv_info;

    @ViewInject(R.id.rv_scrapingcard_two)
    NestRecyclerView rv_scrapingcard_two;
    @ViewInject(R.id.tv_mypack)
    TextView tv_mypack;

    @ViewInject(R.id.tv_dating)
    TextView tv_dating;
    @ViewInject(R.id.iv_openCard)
    ImageView iv_openCard;
    private List<MyCardInfo.JackpotImgsBean> mData_a = new ArrayList<>();
    private List<ScrapingCardBean> mData_b = new ArrayList<>();
    private List<MyCardInfo.ListBean> mData_c = new ArrayList<>();
    private ScrapingCardFragmentOneAdapter mAdapter_a;
    private ScrapingCardFragmentTwoAdapter mAdapter_b;
    private ScrapingCardFragmentThreeAdapter mAdaper_c;

    @Override
    protected ScrapingCardFragmentPresenter CreatePresenter() {
        return new ScrapingCardFragmentPresenter();
    }

    @Override
    public void initView() {
        setOnClikListener(tv_dating, iv_openCard, tv_mypack,
                tv_more, tv_my_heroscroll, tv_help);
        mPresenter.getMyCardInfo(getAppToken());
        deposit_super_man_classic_header.setBackgroundResource(R.drawable.base_title_background);
        deposit_super_man_rl.setRefreshHeader(deposit_super_man_classic_header);
        deposit_super_man_rl.setEnableLoadMore(false);
        deposit_super_man_rl.setOnRefreshListener(refreshlayout -> {
            mPresenter.getMyCardInfo(getAppToken());
            refreshlayout.resetNoMoreData();
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_scrapingcard_one.setLayoutManager(layoutManager);
        mAdapter_a = new ScrapingCardFragmentOneAdapter(getContext(), mData_a);
        rv_scrapingcard_one.setAdapter(mAdapter_a);
        rv_scrapingcard_two.setLayoutManager(new LinearLayoutManager(getContext()));
        mData_b.clear();
        mData_b.addAll(mPresenter.getScarapCasdTask());
        mAdapter_b = new ScrapingCardFragmentTwoAdapter(getContext(), mData_b);
        mAdapter_b.setOnItemBtnClickListener(potion -> {
            switch (potion) {
                case 0://签到
                    MenuActivity menuActivity = (MenuActivity) getActivity();
                    menuActivity.changeFragment(3, null);
                    menuActivity.menu_tab_income_hall.setChecked(true);
                    break;
                case 1://邀请好友
                case 2://分享无限淘
                    $startActivity(MyInvitationCodeActivity.class);
                    break;
                case 3://在线游戏
                    $startActivity(H5GameActivity.class);
                    break;
            }
        });
        rv_scrapingcard_two.setAdapter(mAdapter_b);
        nrv_info.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdaper_c = new ScrapingCardFragmentThreeAdapter(getContext(), mData_c);
        nrv_info.setAdapter(mAdaper_c);
        mAdaper_c.setOnItemClickListener(bean -> mPresenter.enrollBonus(getAppToken(), "1"));
    }

    public void refreshData() {
        if (mPresenter != null) {
            mPresenter.getMyCardInfo(getAppToken());
        }
    }

    @Subscribe
    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.iv_openCard:
                $startActivity(PointToCardActivity.class);
                break;
            case R.id.tv_more:
                MenuActivity menuActivity = (MenuActivity) getActivity();
                menuActivity.changeFragment(3, null);
                menuActivity.menu_tab_income_hall.setChecked(true);
                break;
            case R.id.tv_mypack:
                //我的背包
                $startActivity(MyBackpackActivity.class);
                break;
            case R.id.tv_my_heroscroll:
                $startActivity(HeroScrollActivity.class);
                break;
            case R.id.tv_help:
                $startActivity(HelpCenterActivity.class);
                break;
            case R.id.tv_dating:
                new TradingHallPopupWindow.Build(getContext())
                        .setWindowAnimStyle(R.style.custom_dialog)
                        .builder().showPopupWindow();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private MyCardInfo info;

    @Override
    public void showMyCardInfo(MyCardInfo info) {
        this.info = info;
        mData_a.clear();
        mData_a.addAll(info.getJackpot_imgs());
        mAdapter_a.notifyDataSetChanged();
        mData_c.clear();
        mData_c.addAll(info.getList());
        mAdaper_c.notifyDataSetChanged();
        mData_b.get(0).setNum(info.getIs_vip() == 1 ? 3 : 1);
        if (info.getIs_vip() == 1 && info.getIs_sign() == 1) {
            mData_b.get(0).setFinishText("完成3/3");
        } else if (info.getIs_vip() == 0 && info.getIs_sign() == 1) {
            mData_b.get(0).setFinishText("完成1/1");
        } else {
            mData_b.get(0).setFinishText("完成0/1");
        }
        mData_b.get(1).setFinishText("完成" + info.getHusao() + "/" + info.getHusao_total());
        mData_b.get(2).setFinishText(info.getShare() == 1 ? "完成1/1" : "完成0/1");
        mData_b.get(3).setFinishText(info.getOnline_award() == 1 ? "完成1/1" : "完成0/1");
        mData_b.get(0).setIsFinish(info.getIs_sign());
        mData_b.get(1).setIsFinish(info.getShare_award());//互扫
        mData_b.get(2).setIsFinish(info.getShare());//邀请
        mData_b.get(3).setIsFinish(info.getOnline_award());//在线
        mAdapter_b.notifyDataSetChanged();
    }

    @Override
    public void getMyCardInfoFailure(String errorMsg) {
        showOnlyConfirmDialog(errorMsg);
    }

    @Override
    public void enrollBonusSuccess(String msg) {
        mPresenter.getMyCardInfo(getAppToken());
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void signFailure(String msg) {
        showOnlyConfirmDialog(msg);
    }

    @Override
    public void signSuccess(MySignInfo info) {
        //showOnlyConfirmDialog(info.getMsg());
        //mPresenter.getMyCardInfo(getAppToken());
    }
}
