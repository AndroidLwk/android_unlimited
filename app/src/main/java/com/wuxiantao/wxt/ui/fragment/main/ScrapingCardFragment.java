package com.wuxiantao.wxt.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ScrapingCardFragmentOneAdapter;
import com.wuxiantao.wxt.adapter.recview.ScrapingCardFragmentTwoAdapter;
import com.wuxiantao.wxt.bean.ScrapingCardBean;
import com.wuxiantao.wxt.mvp.contract.ScrapingCardFragmentContract;
import com.wuxiantao.wxt.mvp.presenter.ScrapingCardFragmentPresenter;
import com.wuxiantao.wxt.mvp.view.fragment.MvpFragment;
import com.wuxiantao.wxt.ui.activity.scrapingcard.PointToCardActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;

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
    @ViewInject(R.id.tv_myBag)
    TextView tv_myBag;
    @ViewInject(R.id.iv_crapCard)
    ImageView iv_crapCard;
    @ViewInject(R.id.tv_getValue_help)
    TextView tv_getValue_help;
    @ViewInject(R.id.progress_scrapingcard_a)
    ProgressBar progress_scrapingcard_a;
    @ViewInject(R.id.tv_bronze_num)
    TextView tv_bronze_num;
    @ViewInject(R.id.progress_scrapingcard_b)
    ProgressBar progress_scrapingcard_b;
    @ViewInject(R.id.tv_gold_num)
    TextView tv_gold_num;
    @ViewInject(R.id.sbt_startHall)
    StateButton sbt_startHall;
    @ViewInject(R.id.tv_heroCard_num)
    TextView tv_heroCard_num;
    @ViewInject(R.id.tv_money)
    TextView tv_money;
    @ViewInject(R.id.tv_day_num)
    TextView tv_day_num;
    @ViewInject(R.id.rv_scrapingcard_one)
    NestRecyclerView rv_scrapingcard_one;
    @ViewInject(R.id.tv_more)
    TextView tv_more;
    @ViewInject(R.id.rv_scrapingcard_two)
    NestRecyclerView rv_scrapingcard_two;
    private List<ScrapingCardBean> mData_a = new ArrayList<>();
    private List<ScrapingCardBean> mData_b = new ArrayList<>();

    @Override
    protected ScrapingCardFragmentPresenter CreatePresenter() {
        return new ScrapingCardFragmentPresenter();
    }

    @Override
    public void initView() {
        deposit_super_man_classic_header.setBackgroundResource(R.drawable.base_title_background);
        deposit_super_man_rl.setRefreshHeader(deposit_super_man_classic_header);
        deposit_super_man_rl.setEnableLoadMore(false);
        deposit_super_man_rl.setOnRefreshListener(refreshlayout -> {
            refreshlayout.resetNoMoreData();
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        setOnClikListener(iv_crapCard, tv_getValue_help, sbt_startHall, tv_heroCard_num,
                tv_more);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        /**
         * 测试数据
         */
        rv_scrapingcard_one.setLayoutManager(layoutManager);
        ScrapingCardBean scrapingCardBean = new ScrapingCardBean();
        scrapingCardBean.setImg_res(R.drawable.screpingcard_b);
        mData_a.add(scrapingCardBean);
        scrapingCardBean = new ScrapingCardBean();
        scrapingCardBean.setImg_res(R.drawable.screpingcard_c);
        mData_a.add(scrapingCardBean);
        scrapingCardBean = new ScrapingCardBean();
        scrapingCardBean.setImg_res(R.drawable.screpingcard_d);
        mData_a.add(scrapingCardBean);
        scrapingCardBean = new ScrapingCardBean();
        scrapingCardBean.setImg_res(R.drawable.screpingcard_d);
        mData_a.add(scrapingCardBean);
        scrapingCardBean = new ScrapingCardBean();
        scrapingCardBean.setImg_res(R.drawable.screpingcard_d);
        mData_a.add(scrapingCardBean);
        scrapingCardBean.setImg_res(R.drawable.screpingcard_d);
        ScrapingCardFragmentOneAdapter mAdapter_a = new ScrapingCardFragmentOneAdapter(getContext(), mData_a);
        rv_scrapingcard_one.setAdapter(mAdapter_a);
        rv_scrapingcard_two.setLayoutManager(new LinearLayoutManager(getContext()));

        ScrapingCardBean scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("任务大厅签到  +3张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("二维码邀请好友  +1张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("分享无限淘  +1张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("登录斩妖诀游戏且在线5分钟  +1张");
        mData_b.add(scrapingCardBean_b);
        ScrapingCardFragmentTwoAdapter mAdapter_b = new ScrapingCardFragmentTwoAdapter(getContext(), mData_b);
        rv_scrapingcard_two.setAdapter(mAdapter_b);
    }

    @Subscribe
    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.iv_crapCard:
                $startActivity(PointToCardActivity.class);
                break;
            case R.id.tv_getValue_help:
                break;
            case R.id.sbt_startHall:
                break;
            case R.id.tv_heroCard_num:
                break;
            case R.id.tv_more:
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
}
