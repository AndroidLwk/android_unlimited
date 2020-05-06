package com.wuxiantao.wxt.ui.activity.my;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MyMemberBean;
import com.wuxiantao.wxt.adapter.recview.MymemberAdapter;
import com.wuxiantao.wxt.mvp.contract.MyMemberContract;
import com.wuxiantao.wxt.mvp.presenter.MyMemberPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.recyclerview.NestRecyclerView;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.DensityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_mymember)
public class MyMemberActivity extends MvpActivity<MyMemberPresenter, MyMemberContract> implements MyMemberContract {
    @ViewInject(R.id.base_title_left_img)
    ImageView base_title_left_img;
    @ViewInject(R.id.base_title_left_tv)
    TextView base_title_left_tv;
    @ViewInject(R.id.base_title_left_layout)
    RelativeLayout base_title_left_layout;
    @ViewInject(R.id.base_title_center_tv)
    TextView base_title_center_tv;
    @ViewInject(R.id.base_title_center_img)
    ImageView base_title_center_img;
    @ViewInject(R.id.base_title_right_img)
    ImageView base_title_right_img;
    @ViewInject(R.id.base_title_right_tv)
    TextView base_title_right_tv;
    @ViewInject(R.id.base_title_right_layout)
    RelativeLayout base_title_right_layout;
    @ViewInject(R.id.base_title_layout)
    RelativeLayout base_title_layout;
    @ViewInject(R.id.base_title)
    LinearLayout base_title;
    @ViewInject(R.id.iv_mine_header)
    ImageView iv_mine_header;
    @ViewInject(R.id.iv_headerName)
    TextView iv_headerName;
    @ViewInject(R.id.bt_openMember)
    Button bt_openMember;
    @ViewInject(R.id.rt_personInfo)
    RelativeLayout rt_personInfo;
    @ViewInject(R.id.tv_member_title)
    TextView tv_member_title;
    @ViewInject(R.id.rv_equity)
    NestRecyclerView rv_equity;
    List<MyMemberBean> mData = new ArrayList<>();

    @Override
    protected void initView() {
        setOnClikListener(bt_openMember);
        int spanCount = 3;
        GridLayoutManager manager = new GridLayoutManager(this, spanCount);
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(spanCount, DensityUtils.dip2px(20), true);
        rv_equity.setLayoutManager(manager);
        rv_equity.addItemDecoration(decoration);
        /**
         * 测试数据
         */
        MyMemberBean myMemberBean = new MyMemberBean(R.drawable.mymember_d, "额度特权", "每周提现额度增加");
        mData.add(myMemberBean);
        myMemberBean = new MyMemberBean(R.drawable.mymember_e, "次数特权", "每周提现次数增加");
        mData.add(myMemberBean);
        myMemberBean = new MyMemberBean(R.drawable.mymember_f, "游戏特权", "赠送游戏打折卡");
        mData.add(myMemberBean);
        myMemberBean = new MyMemberBean(R.drawable.mymember_h, "商城特权", "每周提现额度增加");
        mData.add(myMemberBean);
        myMemberBean = new MyMemberBean(R.drawable.mymember_g, "师徒特权", "每周提现次数增加");
        mData.add(myMemberBean);
        myMemberBean = new MyMemberBean(R.drawable.mymember_c, "  更多  ", "赠送游戏打折卡");
        mData.add(myMemberBean);
        rv_equity.setAdapter(new MymemberAdapter(mContext, mData));
    }
    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.drawable.back_mymember)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(getResources().getString(R.string.mymember_text6)).build();
    }
    @Override
    protected void widgetClick(int id) {
        if (id == R.id.bt_openMember) {//开通会员
            Log.e("开通会员","");
        }
    }

    @Override
    protected MyMemberPresenter CreatePresenter() {
        return new MyMemberPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


}
