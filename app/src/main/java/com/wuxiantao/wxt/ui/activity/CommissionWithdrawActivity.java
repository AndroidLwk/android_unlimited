package com.wuxiantao.wxt.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.CommonWithwrawRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.FastWithwrawRecViewAdapter;
import com.wuxiantao.wxt.bean.CheckInInfoBean;
import com.wuxiantao.wxt.bean.CommissionWithdrawInfoBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.CommissionWithdrawContract;
import com.wuxiantao.wxt.mvp.presenter.CommissionWithdrawPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.ArrivalProcessPopupWindow;
import com.wuxiantao.wxt.ui.popupwindow.CopyNoPublicPopupWindow;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.AdUtils;
import com.wuxiantao.wxt.utils.NumberFormatUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.WeChatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.IS_ATTENTION_PUBLIC;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_MINE_BALANCE;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_ERROR;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_SUCCESS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CommissionWithdrawActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-15 下午3:36
 * Description:${DESCRIPTION} 佣金提现
 */
@ContentView(R.layout.activity_commission_withdraw)
public class CommissionWithdrawActivity extends MvpActivity<CommissionWithdrawPresenter, CommissionWithdrawContract.IWithdrawView> implements CommissionWithdrawContract.IWithdrawView {
    @ViewInject(R.id.commission_withdraw_rl)
    SmartRefreshLayout commission_withdraw_rl;
    @ViewInject(R.id.commission_withdraw_money)
    TextView commission_withdraw_money;
    @ViewInject(R.id.commission_withdraw_total)
    TextView commission_withdraw_total;
    @ViewInject(R.id.commission_withdraw_doubt)
    ImageView commission_withdraw_doubt;
    @ViewInject(R.id.commission_withdraw_process_money)
    TextView commission_withdraw_process_money;
    @ViewInject(R.id.commission_withdraw_type_rg)
    RadioGroup commission_withdraw_type_rg;
    @ViewInject(R.id.commission_withdraw_wechat)
    RadioButton commission_withdraw_wechat;
    @ViewInject(R.id.commission_withdraw_alipay)
    RadioButton commission_withdraw_alipay;
    @ViewInject(R.id.commission_withdraw_msg)
    TextView commission_withdraw_msg;
    @ViewInject(R.id.commission_withdraw_binding)
    StateButton commission_withdraw_binding;
    @ViewInject(R.id.fast_withdraw_rv)
    RecyclerView fast_withdraw_rv;

    @ViewInject(R.id.commission_withdraw_description_layout)
    LinearLayout commission_withdraw_description_layout;
    @ViewInject(R.id.commission_withdraw_description_money)
    TextView commission_withdraw_description_money;
    @ViewInject(R.id.commission_withdraw_description_day)
    TextView commission_withdraw_description_day;
    @ViewInject(R.id.commission_withdraw_description_qualifications_money)
    TextView commission_withdraw_description_qualifications_money;
    @ViewInject(R.id.commission_withdraw_description_check_in_msg)
    TextView commission_withdraw_description_check_in_msg;
    @ViewInject(R.id.commission_withdraw_description_check_in_day_count)
    TextView commission_withdraw_description_check_in_day_count;
    @ViewInject(R.id.commission_withdraw_description_check_in)
    StateButton commission_withdraw_description_check_in;

    @ViewInject(R.id.common_withdraw_rv)
    RecyclerView common_withdraw_rv;
    @ViewInject(R.id.commission_withdraw_input)
    EditText commission_withdraw_input;
    @ViewInject(R.id.commission_withdraw_input_clear)
    ImageView commission_withdraw_input_clear;
    @ViewInject(R.id.commission_withdraw_able_money)
    TextView commission_withdraw_able_money;
    @ViewInject(R.id.commission_withdraw_all_money)
    TextView commission_withdraw_all_money;

    @ViewInject(R.id.commission_withdraw_btn)
    StateButton commission_withdraw_btn;

    private boolean isBindingAlipay;
    private boolean isBindingWeChat;
    private boolean isAttentionPublic;
    private String balance;
    private CommissionWithdrawInfoBean infoBean;
    private LoadingDialog loadingDialog;
    private Animation mAnimation;
    //提现方式 2.微信  2.支付宝
    private int type = 2;
    //提现类型 1.快速通道  2.常规通道  3.会员通道
    private int tx_type = -1;
    private  Map<String,Object> map = new HashMap<>();

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        Bundle bundle = getBundle();
        if (bundle != null){
            isAttentionPublic = bundle.getBoolean(IS_ATTENTION_PUBLIC);
            loadingDialog = new LoadingDialog.Build(this).build();
            mPresenter.getWithdrawInfo(getAppToken());
        }
        setOnClikListener(commission_withdraw_input,commission_withdraw_doubt,commission_withdraw_binding,commission_withdraw_description_check_in,
                commission_withdraw_input_clear,commission_withdraw_all_money,commission_withdraw_btn);
        commission_withdraw_type_rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.commission_withdraw_wechat:
                    commission_withdraw_btn.setEnabled(isBindingWeChat);
                    if (!isBindingWeChat){
                        commission_withdraw_msg.setText(getString(R.string.not_bingding_wechat));
                        commission_withdraw_binding.setVisibility(View.VISIBLE);
                    }else {
                        String name = infoBean.getNickname();
                        if (!isEmpty(name)){
                            commission_withdraw_msg.setText(getString(R.string.wechat_nickname) + name);
                        }
                        commission_withdraw_binding.setVisibility(View.GONE);
                    }
                    type = 2;
                    break;
                case R.id.commission_withdraw_alipay:
                    commission_withdraw_btn.setEnabled(isBindingAlipay);
                    if (!isBindingAlipay){
                        commission_withdraw_msg.setText(getString(R.string.not_bingding_alipay));
                        commission_withdraw_binding.setVisibility(View.VISIBLE);
                    }
                    else {
                        String alicode = infoBean.getAlicode();
                        if (!isEmpty(alicode)){
                            String code = NumberFormatUtils.phoneNumberSub(alicode);
                            commission_withdraw_msg.setText(getString(R.string.alipay_account) + code);
                        }
                        commission_withdraw_binding.setVisibility(View.GONE);
                    }
                    type = 1;
                    break;
            }
        });
        commission_withdraw_rl.setEnableRefresh(false);
        commission_withdraw_rl.setEnableLoadMore(false);
        commission_withdraw_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

        if (!isVip()){
            commission_withdraw_input.setFocusableInTouchMode(false);
            commission_withdraw_input.setFocusable(false);
        }else {
            commission_withdraw_input.setFocusableInTouchMode(true);
            commission_withdraw_input.setFocusable(true);
        }
        commission_withdraw_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                editTextLengthListener(s.length());
            }
        });
        commission_withdraw_input.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                editTextLengthListener(getEdtText(commission_withdraw_input).length());
            }
            return false;
        });
    }

    private void editTextLengthListener(int len){
        if (len > 0){
            tx_type = 3;
            resetFastSelectInfo();
            resetCommonSelectInfo();
            if (type == 1){
                commission_withdraw_btn.setEnabled(isBindingAlipay);
            }else {
                commission_withdraw_btn.setEnabled(isBindingWeChat);
            }
            commission_withdraw_input_clear.setVisibility(View.VISIBLE);
        }else {
            commission_withdraw_input_clear.setVisibility(View.GONE);
        }
    }


    @Override
    protected void widgetClick(int id) {
        switch (id){
            case R.id.commission_withdraw_input:
                if (!isVip()){
                    showNoMemberDialog();
                }
                break;
            //问号
            case R.id.commission_withdraw_doubt:
                new ArrivalProcessPopupWindow.Build(this)
                        .builder()
                        .showPopupWindow();
                break;
            //绑定信息
            case R.id.commission_withdraw_binding:
                //绑定微信
                if (commission_withdraw_wechat.isChecked()){
                    WeChatUtils.sendWeChatLoginRequest(true);
                }else if (commission_withdraw_alipay.isChecked()){
                  //绑定支付宝
                    $startActivityForResult(BinddingAlipayActivity.class,REQUEST_CODE_BINDING_ALIPAY);
                }
                break;
            //签到
            case R.id.commission_withdraw_description_check_in:
                showRewardVideoAd();
                break;
            //清空输入框
            case R.id.commission_withdraw_input_clear:
                commission_withdraw_input.setText("");
                tx_type = -1;
                break;
            //全部提现
            case R.id.commission_withdraw_all_money:
                if (!isVip()){
                    showNoMemberDialog();
                }else {
                    commission_withdraw_input.setText(balance);
                    commission_withdraw_input.setSelection(balance.length());
                }
                break;
            //申请提现
            case R.id.commission_withdraw_btn:
                if (tx_type == -1){
                    showOnlyConfirmDialog(getString(R.string.please_select_withdraw_tx_type));
                    return;
                }
                if (!isAttentionPublic && !isPrompted){
                    new CopyNoPublicPopupWindow.Build(this)
                            .setOnCopyListener(TextViewUtils::copy)
                            .builder()
                            .showPopupWindow();
                    isPrompted = !isPrompted;
                }else {
                    applyWithdraw();
                }
                break;
        }
    }


    //播放激励视频
    private void showRewardVideoAd(){
        AdUtils.initRewardVideoAd(this,() -> mPresenter.checkIn(getAppToken()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    //是否已提示关注公众号
    private boolean isPrompted;


    private void showNoMemberDialog(){
        showOnlyConfirmDialog(R.string.prompt,R.string.prompt_msg);
    }

    private void applyWithdraw(){
        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.apply_withdrawing).build();
        map.put(TOKEN,getAppToken());
        //提现类型：1支付宝，2微信
        map.put("type",type);
        //1为极速提现，2为常规提现，3为会员专属提现
        map.put("tx_type",tx_type);
        //快速提现
        if (tx_type == 1){
            map.put("js_type",1);
        }
        //会员通道提现
        else if (tx_type == 3){
            money = getEdtText(commission_withdraw_input);
        }
        map.put("money",money);
        mPresenter.withdraw(map);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        switch (event.getType()){
            //微信绑定成功
            case UPDATE_WECHAT_BINGDING_SUCCESS:
                mPresenter.getWithdrawInfo(getAppToken());
                break;
            //微信绑定失败
            case UPDATE_WECHAT_BINGDING_ERROR:
                String msg = event.getMessage();
                String s = getString(R.string.wechat_accout_banding);
                if (isEmpty(msg)){
                    return;
                }
                if (msg.contains(s)){
                    showOnlyConfirmDialog(event.getMessage());
                }
                break;
        }
    }


    //获取提现信息
    @SuppressLint("SetTextI18n")
    @Override
    public void getWithdrawInfoSuccess(CommissionWithdrawInfoBean bean) {
        this.infoBean = bean;
        isBindingWeChat = !isEmpty(bean.getNickname());
        isBindingAlipay = !isEmpty(bean.getAlicode());
        balance = bean.getAmount();
        if (!isBindingWeChat){
            commission_withdraw_msg.setText(getString(R.string.not_bingding_wechat));
            commission_withdraw_binding.setVisibility(View.VISIBLE);
            commission_withdraw_btn.setEnabled(false);
        }
        commission_withdraw_money.setText(bean.getAmount());
        commission_withdraw_able_money.setText(bean.getAmount());
        commission_withdraw_total.setText(String.valueOf(bean.getLeiji()));
        commission_withdraw_process_money.setText(String.valueOf(bean.getJijiang()));
        String name = bean.getNickname();
        if (isBindingWeChat && !isEmpty(name)){
            commission_withdraw_msg.setText(getString(R.string.wechat_nickname) + name);
            commission_withdraw_binding.setVisibility(View.GONE);
        }else {
            commission_withdraw_msg.setText(getString(R.string.not_bingding_wechat));
            commission_withdraw_binding.setVisibility(View.VISIBLE);
        }
        initFastWithdrawLayout(bean);
        initCommonWithdrawLayout(bean);
    }

    private List<Boolean> fastCheckd = new ArrayList<>();
    private List<Boolean> commonChecked = new ArrayList<>();
    private FastWithwrawRecViewAdapter fastAdapter;
    private CommonWithwrawRecViewAdapter commonAdapter;
    private int index = 0;
    private String money = "";

    //快速提现
    private void initFastWithdrawLayout(CommissionWithdrawInfoBean bean){
        GridLayoutManager manager = new GridLayoutManager(this,3);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3,20,true);
        for (int i = 0;i < bean.getJisu().size();i++){
            fastCheckd.add(false);
        }
        fastAdapter = new FastWithwrawRecViewAdapter(this,bean.getJisu(),fastCheckd,balance);
        fast_withdraw_rv.setLayoutManager(manager);
        fast_withdraw_rv.addItemDecoration(itemDecoration);
        fast_withdraw_rv.setAdapter(fastAdapter);
        fastAdapter.setOnBaseViewClickListener(position -> {
            resetCommonSelectInfo();
            if (fastAdapter.update(position)){
                mPresenter.getCheckInInfo(getAppToken());
                index = position;
                money = bean.getJisu().get(position);
                commission_withdraw_input.setText("");
                tx_type = 1;
                if (type == 1){
                    commission_withdraw_btn.setEnabled(isBindingAlipay);
                }else {
                    commission_withdraw_btn.setEnabled(isBindingWeChat);
                }
            }else {
                zoomOutAnimation();
            }
        });
    }

    //常规提现
    private void initCommonWithdrawLayout(CommissionWithdrawInfoBean bean){
        GridLayoutManager manager = new GridLayoutManager(this,3);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3,20,true);
        for (int i = 0;i < bean.getChanggui().size();i++){
            commonChecked.add(false);
        }
        commonAdapter = new CommonWithwrawRecViewAdapter(this,bean.getChanggui(),commonChecked,balance);
        common_withdraw_rv.setLayoutManager(manager);
        common_withdraw_rv.addItemDecoration(itemDecoration);
        common_withdraw_rv.setAdapter(commonAdapter);
        commonAdapter.setOnBaseViewClickListener(position -> {
            resetFastSelectInfo();
            if (commonAdapter.update(position)){
                tx_type = 2;
                money = bean.getChanggui().get(position);
                commission_withdraw_btn.setEnabled(isBindingWeChat);
                commission_withdraw_input.setText("");
                if (type == 1){
                    commission_withdraw_btn.setEnabled(isBindingAlipay);
                }else {
                    commission_withdraw_btn.setEnabled(isBindingWeChat);
                }
            }
        });
    }

    //重置快速提现选择信息
    private void resetFastSelectInfo(){
        for (int i = 0;i < fastCheckd.size();i++){
            if (fastCheckd.get(i)){
                fastAdapter.update(i);
                zoomOutAnimation();
            }
        }
    }

    //重置常规提现选择信息
    private void resetCommonSelectInfo(){
        for (int i = 0;i < commonChecked.size();i++){
            if (commonChecked.get(i)){
                commonAdapter.update(i);
            }
        }
    }

    //获取签到信息成功
    @Override
    public void getCheckInInfoSuccess(CheckInInfoBean bean) {
        spreadAnimation(bean);
    }

    //签到成功
    @Override
    public void checkInSuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> mPresenter.getCheckInInfo(getAppToken()));
    }

    //提现申请成功
    @Override
    public void withdrawSuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) -> {
            EventBus.getDefault().post(new MessageEvent(UPDATE_MINE_BALANCE));
            finish();
        });
    }

    @Override
    public void getWithdrawInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void getCheckInInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void checkInFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void withdrawFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //支付宝绑定完毕
        if (requestCode == REQUEST_CODE_BINDING_ALIPAY && resultCode == RESULT_CODE_BINDING_ALIPAY){
            mPresenter.getWithdrawInfo(getAppToken());
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.withdraw).build();
    }

    @Override
    protected CommissionWithdrawPresenter CreatePresenter() {
        return new CommissionWithdrawPresenter();
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }

    //放大动画
    private void spreadAnimation(CheckInInfoBean checkInInfoBean){
        if (checkInInfoBean != null){
            commission_withdraw_description_check_in.setEnabled(checkInInfoBean.getType() == 0);
            commission_withdraw_description_check_in.setText(checkInInfoBean.getType() == 0 ?
                    getString(R.string.check_in_not) : getString(R.string.check_in_checked));
            //剩余天数
            int days = checkInInfoBean.getRemain().get(index);
            //连续签到天数
            commission_withdraw_description_day.setText(String.valueOf(checkInInfoBean.getDay() + days));
            if (!isEmpty(money)){
                commission_withdraw_description_money.setText(getString(R.string.yuan_description,money));
                commission_withdraw_description_qualifications_money.setText(money);
            }
            commission_withdraw_description_check_in_msg.setText(checkInInfoBean.getType() == 0
                    ? getString(R.string.not_check_in) : getString(R.string.check_in_today));
            commission_withdraw_description_check_in_day_count.setText(String.valueOf(days));
        }
        mAnimation = AnimationUtils.loadAnimation(this,R.anim.spread_anim_withdraw_description);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                commission_withdraw_description_layout.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        commission_withdraw_description_layout.startAnimation(mAnimation);
    }

    //缩小动画
    private void zoomOutAnimation(){
        mAnimation = AnimationUtils.loadAnimation(this,R.anim.zoom_out_anim_withdraw_description);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                commission_withdraw_description_layout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        commission_withdraw_description_layout.startAnimation(mAnimation);
    }

    private void clearAnimation(){
        if (mAnimation != null){
            mAnimation.cancel();
            mAnimation.reset();
            mAnimation = null;
            commission_withdraw_description_layout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAnimation();
        EventBus.getDefault().unregister(this);
    }
}
