package com.wuxiantao.wxt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.WithdrawBean;
import com.wuxiantao.wxt.adapter.recview.BalanceWithdrawRecViewAdapter;
import com.wuxiantao.wxt.adapter.recview.RedEnvelopeRecViewAdapter;
import com.wuxiantao.wxt.bean.RedBagWithdrawInfoBean;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.mvp.contract.ApplyWithdrawContract;
import com.wuxiantao.wxt.mvp.presenter.ApplyWithdrawPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.GridSpacingItemDecoration;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.ui.popupwindow.CopyNoPublicPopupWindow;
import com.wuxiantao.wxt.ui.title.CNToolbar;
import com.wuxiantao.wxt.utils.BigDecimalUtils;
import com.wuxiantao.wxt.utils.TextViewUtils;
import com.wuxiantao.wxt.utils.WeChatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.BALANCE_SCARCITY;
import static com.wuxiantao.wxt.config.Constant.BINDIND_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.BINDIND_WECHAT;
import static com.wuxiantao.wxt.config.Constant.CONFIRM_WITHDRAW;
import static com.wuxiantao.wxt.config.Constant.IS_ATTENTION_PUBLIC;
import static com.wuxiantao.wxt.config.Constant.MORE_THAN_THE_INPUT;
import static com.wuxiantao.wxt.config.Constant.REQUEST_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_BINDING_ALIPAY;
import static com.wuxiantao.wxt.config.Constant.TOKEN;
import static com.wuxiantao.wxt.config.Constant.UPDATE_MINE_BALANCE;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_ERROR;
import static com.wuxiantao.wxt.config.Constant.UPDATE_WECHAT_BINGDING_SUCCESS;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MineBalanceActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 下午4:57
 * Description:${DESCRIPTION} 余额提现
 */
@ContentView(R.layout.activity_balance_withdraw)
public class BalanceWithdrawActivity extends MvpActivity<ApplyWithdrawPresenter, ApplyWithdrawContract.IApplyView> implements ApplyWithdrawContract.IApplyView {
    @ViewInject(R.id.balance_with_draw_input)
    EditText balance_with_draw_input;
    @ViewInject(R.id.balance_with_draw_clear)
    ImageView balance_with_draw_clear;
    @ViewInject(R.id.balance_with_draw_money_rv)
    RecyclerView balance_with_draw_money_rv;
    @ViewInject(R.id.balance_with_draw_able_money)
    TextView balance_with_draw_able_money;
    @ViewInject(R.id.balance_with_draw_all_money)
    TextView balance_with_draw_all_money;
    @ViewInject(R.id.balance_with_draw_btn)
    StateButton balance_with_draw_btn;
    @ViewInject(R.id.balance_with_draw_rv)
    RecyclerView balance_with_draw_rv;
    @ViewInject(R.id.fragment_member_center_toolbar)
    CNToolbar fragment_member_center_toolbar;

    private LoadingDialog loadingDialog;
    private boolean isAttentionPublic;

    //余额不足
    private boolean scarcity;
    //提现类型：1为支付宝，2为微信
    private int withdrawType = 1;
    private RedEnvelopeRecViewAdapter adapter;
    private List<WithdrawBean> list;
    //是否已提示关注公众号
    private boolean isPrompted;
    //当前余额
    private String balance = "";
    //是否绑定支付宝
    private boolean isBindingAlipay;
    //是否绑定微信
    private boolean isBindingWeChat;

    @Override
    public void initView() {
        setStatusBar();
        EventBus.getDefault().register(this);
        Bundle bundle = getBundle();
        if (bundle != null) {
            isAttentionPublic = bundle.getBoolean(IS_ATTENTION_PUBLIC);
        }
        loadingDialog = new LoadingDialog.Build(this).build();
        mPresenter.getWithdrawInfo(getAppToken());
        balance_with_draw_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
        setOnClikListener(balance_with_draw_clear,
                balance_with_draw_all_money, balance_with_draw_btn);
        fragment_member_center_toolbar.setOnLeftButtonClickListener(() -> {
            finish();
        });
        initWithdrawList();
    }


    //获取提现信息成功
    @Override
    public void getWithdrawInfoSuccess(RedBagWithdrawInfoBean bean) {
        balance = bean.getMoney();
        balance_with_draw_able_money.setText(balance);
        scarcity = isEmpty(balance) || BigDecimalUtils.compare("0", balance);
        isBindingAlipay = !isEmpty(bean.getAlicode());
        isBindingWeChat = bean.getIs_wx() == 1 ? true : false;

        initEnabledText();
        balance_with_draw_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                setEnabledText(len);
                balance_with_draw_clear.setVisibility(len > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });
        balance_with_draw_input.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                int len = getEdtText(balance_with_draw_input).length();
                setEnabledText(len);
                balance_with_draw_clear.setVisibility(len > 0 ? View.VISIBLE : View.INVISIBLE);
            }
            return false;
        });
        initMoneyLayout(bean);
    }


    private void initEnabledText() {
        //余额不足
        if (scarcity) {
            balance_with_draw_btn.setText(BALANCE_SCARCITY);
        }
        //未绑定支付宝
        else if (!isBindingAlipay) {
            balance_with_draw_btn.setText(BINDIND_ALIPAY);
            balance_with_draw_btn.setEnabled(true);
        } else {
            balance_with_draw_btn.setText(CONFIRM_WITHDRAW);
        }
    }


    private void setEnabledText(int len) {
        //余额不足
        if (scarcity) {
            balance_with_draw_btn.setText(BALANCE_SCARCITY);
            balance_with_draw_btn.setEnabled(false);
        }
        //未绑定支付宝
        else if (withdrawType == 1 && !isBindingAlipay) {
            balance_with_draw_btn.setText(BINDIND_ALIPAY);
            balance_with_draw_btn.setEnabled(true);
        }
        //未绑定微信
        else if (withdrawType == 2 && !isBindingWeChat) {
            balance_with_draw_btn.setText(BINDIND_WECHAT);
            balance_with_draw_btn.setEnabled(true);
        } else {
            try {
                if (BigDecimalUtils.compare(getEdtText(balance_with_draw_input), getEdtText(balance_with_draw_input))) {
                    balance_with_draw_btn.setText(MORE_THAN_THE_INPUT);
                    balance_with_draw_btn.setEnabled(false);
                } else {
                    balance_with_draw_btn.setText(CONFIRM_WITHDRAW);
                    balance_with_draw_btn.setEnabled(len > 0);
                }
            } catch (NumberFormatException e) {
                balance_with_draw_btn.setText(CONFIRM_WITHDRAW);
                balance_with_draw_btn.setEnabled(len > 0);
            }
        }
    }


    //初始化支付类型列表:支付宝 微信
    private void initWithdrawList() {
        List<String> type = Arrays.asList(getResources().getStringArray(R.array.withdraw_title));
        List<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);
        BalanceWithdrawRecViewAdapter adapter = new BalanceWithdrawRecViewAdapter(this, type, booleans);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0, 40);
        balance_with_draw_rv.setLayoutManager(manager);
        balance_with_draw_rv.addItemDecoration(decoration);
        balance_with_draw_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> {
            setShowSelectText(position);
            adapter.updateChecdList(position);
        });
    }

    //初始化金额选择列表:九宫格
    private void initMoneyLayout(RedBagWithdrawInfoBean bean) {
        list = initMoneyList(bean);
        adapter = new RedEnvelopeRecViewAdapter(this, list);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(3, 20, true);
        balance_with_draw_money_rv.setLayoutManager(manager);
        balance_with_draw_money_rv.addItemDecoration(itemDecoration);
        balance_with_draw_money_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelected(false);
            }
            list.get(position).setSelected(!list.get(position).isSelected());
            adapter.updataList(list);
            String money = String.valueOf(list.get(position).getMoney());
            balance_with_draw_input.setText(money);
            balance_with_draw_input.setSelection(money.length());
        });
    }

    private List<WithdrawBean> initMoneyList(RedBagWithdrawInfoBean bean) {
        List<WithdrawBean> list = new ArrayList<>();
        for (int i = 0; i < bean.getShow_money().size(); i++) {
            WithdrawBean b = new WithdrawBean(bean.getShow_money().get(i), false);
            list.add(b);
        }
        return list;
    }


    private void setShowSelectText(int position) {
        //余额不足
        if (scarcity) {
            balance_with_draw_btn.setText(BALANCE_SCARCITY);
        } else if (position == 0) {
            //未绑定支付宝
            if (!isBindingAlipay) {
                balance_with_draw_btn.setText(BINDIND_ALIPAY);
                balance_with_draw_btn.setEnabled(true);
            } else {
                filterMoney();
            }
        } else {
            //未绑定微信
            if (!isBindingWeChat) {
                balance_with_draw_btn.setText(BINDIND_WECHAT);
                balance_with_draw_btn.setEnabled(true);
            } else {
                filterMoney();
            }
        }
        withdrawType = position == 0 ? 1 : 2;
    }

    private void filterMoney() {
        try {
            if (Double.valueOf(getEdtText(balance_with_draw_input)) > Double.valueOf(balance)) {
                balance_with_draw_btn.setEnabled(false);
                balance_with_draw_btn.setText(MORE_THAN_THE_INPUT);
            } else {
                balance_with_draw_btn.setText(CONFIRM_WITHDRAW);
                balance_with_draw_btn.setEnabled(getEdtText(balance_with_draw_input).length() > 0);
            }
        } catch (NumberFormatException e) {
            balance_with_draw_btn.setEnabled(getEdtText(balance_with_draw_input).length() > 0);
            balance_with_draw_btn.setText(CONFIRM_WITHDRAW);
        }
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId) {
            //清空
            case R.id.balance_with_draw_clear:
                if (list != null && adapter != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelected(false);
                    }
                    adapter.updataList(list);
                }
                balance_with_draw_input.setText("");
                break;
            //全部提现
            case R.id.balance_with_draw_all_money:
                if (list != null && adapter != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setSelected(false);
                    }
                    adapter.updataList(list);
                }
                String money = balance_with_draw_able_money.getText().toString();
                balance_with_draw_input.setText(money);
                balance_with_draw_input.setSelection(money.length());
                break;
            //确认提现
            case R.id.balance_with_draw_btn:
                String btnText = getTextViewText(balance_with_draw_btn);
                //绑定支付宝
                if (btnText.equals(BINDIND_ALIPAY)) {
                    $startActivityForResult(BinddingAlipayActivity.class, REQUEST_CODE_BINDING_ALIPAY);
                }
                //绑定微信
                else if (btnText.equals(BINDIND_WECHAT)) {
                    WeChatUtils.sendWeChatLoginRequest(true);
                }
                //未关注公众号
                else if (!isAttentionPublic && !isPrompted) {
                    new CopyNoPublicPopupWindow.Build(this)
                            .setOnCopyListener(TextViewUtils::copy)
                            .builder()
                            .showPopupWindow();
                    isPrompted = !isPrompted;
                    return;
                }
                //确认提现
                else if (btnText.equals(CONFIRM_WITHDRAW)) {
                    loadingDialog = new LoadingDialog.Build(this)
                            .setLoadingText(R.string.apply_withdrawing).build();
                    map.put(TOKEN, getAppToken());
                    map.put("money", getEdtText(balance_with_draw_input));
                    map.put("type", withdrawType);
                 //   map.put(MODE, 1);
                    mPresenter.withdraw(map);
                }
                break;
        }
    }


    private Map<String, Object> map = new HashMap<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            //微信绑定成功
            case UPDATE_WECHAT_BINGDING_SUCCESS:
                mPresenter.getWithdrawInfo(getAppToken());
                break;
            //微信绑定失败
            case UPDATE_WECHAT_BINGDING_ERROR:
                String msg = event.getMessage();
                String s = getString(R.string.wechat_accout_banding);
                if (isEmpty(msg)) {
                    return;
                }
                if (msg.contains(s)) {
                    showOnlyConfirmDialog(event.getMessage());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //支付宝绑定完毕
        if (requestCode == REQUEST_CODE_BINDING_ALIPAY && resultCode == RESULT_CODE_BINDING_ALIPAY) {
            mPresenter.getWithdrawInfo(getAppToken());
        }
    }


    @Override
    protected ApplyWithdrawPresenter CreatePresenter() {
        return new ApplyWithdrawPresenter();
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
    public void withdrawFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }

    @Override
    public void getWithdrawInfoFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }
}
