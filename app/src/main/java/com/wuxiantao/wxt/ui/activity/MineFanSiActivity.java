package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewPagerAdapter;
import com.wuxiantao.wxt.bean.MyFansiHeadInfoBean;
import com.wuxiantao.wxt.imgloader.GlideImgManager;
import com.wuxiantao.wxt.mvp.contract.MineFansiContract;
import com.wuxiantao.wxt.mvp.presenter.MineFansiPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.indicator.VPindicator;
import com.wuxiantao.wxt.ui.custom.viewpager.LazyViewPager;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiDirectlyFragment;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiIndirectFragment;
import com.wuxiantao.wxt.ui.fragment.fansi.FanSiPotentialFragment;
import com.wuxiantao.wxt.ui.popupwindow.InviteMePopupWindow;
import com.wuxiantao.wxt.utils.AnimaUtils;
import com.wuxiantao.wxt.utils.MathUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineFanSiActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午4:24
 * Description:${DESCRIPTION}我的粉丝
 */
@ContentView(R.layout.activity_mine_fansi)
public class MineFanSiActivity extends MvpActivity<MineFansiPresenter, MineFansiContract.IFansiView> implements MineFansiContract.IFansiView {
    @ViewInject(R.id.mine_fansi_back)
    private RelativeLayout mine_fansi_back;
    @ViewInject(R.id.mine_fansi_title_img)
    private ImageView mine_fansi_title_img;
    @ViewInject(R.id.mine_fansi_title_text)
    private TextView mine_fansi_title_text;
    @ViewInject(R.id.mine_fansi_title_btn)
    private StateButton mine_fansi_title_btn;
    @ViewInject(R.id.mine_fansi_doubt)
    private ImageView mine_fansi_doubt;
    @ViewInject(R.id.mine_fansi_app_bar)
    private AppBarLayout mine_fansi_app_bar;
    @ViewInject(R.id.mine_fansi_indicator)
    private VPindicator mIndicator;
    @ViewInject(R.id.mine_fansi_viewpager)
    private LazyViewPager mViewpager;
    @ViewInject(R.id.mine_fansi_headimg)
    private ImageView mine_fansi_headimg;
    @ViewInject(R.id.mine_fansi_nickname)
    private TextView mine_fansi_nickname;
    @ViewInject(R.id.mine_fansi_invite)
    private StateButton mine_fansi_invite;
    @ViewInject(R.id.mine_fansi_total)
    private TextView mine_fansi_total;
    @ViewInject(R.id.mine_fansi_total_deposit)
    private TextView mine_fansi_total_deposit;
    @ViewInject(R.id.mine_fansi_view_invite)
    private TextView mine_fansi_view_invite;
    @ViewInject(R.id.mine_fansi_qa)
    private ImageView mine_fansi_qa;

    private List<Fragment> mFragments = new ArrayList<>();

    private LoadingDialog loadingDialog;
    private MyFansiHeadInfoBean datas;
    private ArrayList<String> headImgList = new ArrayList<>();
    //是否暂无上级
    private boolean isSuperior;
    //头像
    private String headImg;
    //昵称
    private String nickname;

    @Override
    protected MineFansiPresenter CreatePresenter() {
        return new MineFansiPresenter();
    }

    @Override
    public void initView() {
        setStatusBar();
        initFragment();
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.obtainFansiHeadInfo(getAppToken());

        initViewPager();

        mine_fansi_app_bar.addOnOffsetChangedListener((appBarLayout, offset) -> {
            int toolbarHeight = mine_fansi_app_bar.getTotalScrollRange();
            int dy = Math.abs(offset);
            if (dy >= toolbarHeight){
                AnimaUtils.showView(MineFanSiActivity.this,mine_fansi_title_img);
                AnimaUtils.showView(MineFanSiActivity.this,mine_fansi_title_btn);
                if (!isEmpty(headImg)){
                    GlideImgManager.loadCircleImg(this,headImg,mine_fansi_title_img);
                }else {
                    GlideImgManager.loadCircleImg(this,R.mipmap.default_user_head_img,mine_fansi_title_img);
                }
                mine_fansi_title_text.setText(nickname);
            }else {
                mine_fansi_title_img.setVisibility(View.GONE);
                mine_fansi_title_btn.setVisibility(View.GONE);
                mine_fansi_title_text.setText(getString(R.string.my_fans));
            }
        });

        setOnClikListener(mine_fansi_back, mine_fansi_doubt, mine_fansi_headimg, mine_fansi_invite, mine_fansi_view_invite,
                mine_fansi_qa,mine_fansi_title_btn,mine_fansi_title_text,mine_fansi_title_img);
    }


    private void initViewPager() {
        String[] titleArray = getResources().getStringArray(R.array.mine_fansi_title);
        FragmentManager fm = getSupportFragmentManager();
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(fm, mFragments, titleArray);
        // 设置adapter
        mViewpager.setAdapter(adapter);
        // 绑定indicator
        mIndicator.setViewPager(mViewpager);
        setTabPagerIndicator();
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //返回
            case R.id.mine_fansi_back:
                finish();
                break;
            //展开appbar
            case R.id.mine_fansi_title_text:
            case R.id.mine_fansi_title_img:
                mine_fansi_app_bar.setExpanded(true);
                break;
            //右上角问号 我的团队
            case R.id.mine_fansi_doubt:
                $startActivity(MyTeamActivity.class);
                break;
            //头像
            case R.id.mine_fansi_headimg:
                if (!isSuperior && !headImgList.isEmpty()){
                    previewImg(headImgList,0);
                }else {
                    showNoSuperiorDialog();
                }
                break;
            //邀请粉丝
            case R.id.mine_fansi_title_btn:
            case R.id.mine_fansi_invite:
                $startActivity(ShareThemActivity.class);
                break;
            //查看邀请我的人
            case R.id.mine_fansi_view_invite:
                if (!isSuperior){
                    showViewInviteMeWindow();
                }else {
                    showNoSuperiorDialog();
                }
                break;
            //不包含潜在粉丝人数
            case R.id.mine_fansi_qa:
                showOnlyConfirmDialog(getString(R.string.not_included_potential_fansi));
                break;
        }
    }


    private void showViewInviteMeWindow() {
        if (datas != null) {
            String wechat_no = datas.getMyup().getWechat();
            String name = datas.getMyup().getNickname();
            if (isEmpty(wechat_no)){
                wechat_no = getString(R.string.w_weichat_regex,getString(R.string.w_weichat_nickname),name);
            }else {
                wechat_no = getString(R.string.w_weichat_regex,getString(R.string.w_weichat_no),wechat_no);
            }
            new InviteMePopupWindow.Build(this)
                    .setCircleImageResource(datas.getMyup().getHeadimg())
                    .setNickNameText(name)
                    .setWeChatText(wechat_no)
                    .setPopupWindowAnimStyle(R.style.loading_dialog_anim)
                    .setOnPopupClickListener(TextViewUtils::copy)
                    .builder().showPopupWindow();
        }
    }

    private void initFragment() {
        mFragments.add(new FanSiPotentialFragment());
        mFragments.add(new FanSiDirectlyFragment());
        mFragments.add(new FanSiIndirectFragment());
    }

    private void showNoSuperiorDialog(){
        showOnlyConfirmDialog(R.string.prompt,R.string.voluntary_occupation);
    }



    //设置指示器颜色
    private void setTabPagerIndicator() {
        // 设置模式，一定要先设置模式
        mIndicator.setIndicatorMode(VPindicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);
        // 设置分割线的颜色
//        my_coupon_indicator.setDividerColor(Color.parseColor("#00bbcf"));
        //设置
        mIndicator.setDividerPadding(getResources().getDimensionPixelSize(R.dimen.dp_10));
        // 设置底部导航线的颜色
        mIndicator.setIndicatorColor(Color.parseColor("#FF615B"));
        // 设置tab标题选中的颜色
        mIndicator.setTextColorSelected(Color.parseColor("#FF615B"));
        // 设置tab标题未被选中的颜色
        mIndicator.setTextColor(Color.parseColor("#FF111111"));
        // 设置字体大小
        mIndicator.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_15));
    }

    //获取我的粉丝头部信息
    @Override
    public void obtainFansiHeadInfoSuccess(MyFansiHeadInfoBean bean) {
        this.datas = bean;
        mine_fansi_total.setText(String.valueOf(bean.getFenall()));
        mine_fansi_total_deposit.setText(MathUtils.formatCurrency(String.valueOf(bean.getPaytribute()),4));
        MyFansiHeadInfoBean.MyupBean myupBean = bean.getMyup();
        headImg = myupBean.getHeadimg();
        nickname = myupBean.getNickname();
        isSuperior = !isEmpty(headImg);
        if (isSuperior) {
            GlideImgManager.loadCircleImg(this, headImg, mine_fansi_headimg);
            headImgList.clear();
            headImgList.add(headImg);
        } else {
            headImg = getUserInfo(USER_HEAD_IMG);
            nickname = getUserInfo(NICK_NAME);
        }
        if (!isEmpty(headImg)){
            GlideImgManager.loadCircleImg(this,headImg,mine_fansi_headimg);
        }else {
            GlideImgManager.loadCircleImg(this,R.drawable.ic_person_outline_black_24dp,mine_fansi_headimg);
        }
        isSuperior = !isSuperior;
        mine_fansi_nickname.setText(nickname);
    }

    @Override
    public void obtainFansiHeadInfoFailure(String failure) {
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
