package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.SearchResultRecViewAdapter;
import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.mvp.contract.SearchResultContract;
import com.wuxiantao.wxt.mvp.presenter.SearchResultPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioButton;
import com.wuxiantao.wxt.ui.custom.radiobutton.SoftRadioGroup;
import com.wuxiantao.wxt.ui.custom.scroller.TopSmoothScroller;
import com.wuxiantao.wxt.ui.custom.switchbutton.SwitchButton;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.PAGE_SIZE;
import static com.wuxiantao.wxt.config.Constant.REFRESH_LOAD_MORE_TIME;
import static com.wuxiantao.wxt.config.Constant.SEARCH_KEYWORD;
import static com.wuxiantao.wxt.config.Constant.TAO_BAO_ID;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchResultActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午4:47
 * Description:${DESCRIPTION} 搜索结果
 */
@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends MvpActivity<SearchResultPresenter, SearchResultContract.IResultView> implements SearchResultContract.IResultView {
    @ViewInject(R.id.search_result_dl)
    DrawerLayout search_result_dl;
    @ViewInject(R.id.search_result_appbar)
    AppBarLayout search_result_appbar;
    @ViewInject(R.id.search_result_rl)
    SmartRefreshLayout search_result_rl;
    @ViewInject(R.id.search_result_classic_header)
    ClassicsHeader search_result_classic_header;

    @ViewInject(R.id.search_result_layout)
    RelativeLayout search_result_layout;
    @ViewInject(R.id.search_result_right_rg)
    RadioGroup search_result_right_rg;
    @ViewInject(R.id.search_result_right_tianmao)
    RadioButton search_result_right_tianmao;
    @ViewInject(R.id.search_result_right_unlimited)
    RadioButton search_result_right_unlimited;
    @ViewInject(R.id.search_result_right_input_lowest)
    EditText search_result_right_input_lowest;
    @ViewInject(R.id.search_result_right_input_highest)
    EditText search_result_right_input_highest;
    @ViewInject(R.id.search_result_right_reset)
    StateButton search_result_right_reset;
    @ViewInject(R.id.search_result_right_confirm)
    StateButton search_result_right_confirm;

    @ViewInject(R.id.search_result_delete)
    ImageView search_result_delete;
    @ViewInject(R.id.search_result_cancel)
    TextView search_result_cancel;
    @ViewInject(R.id.search_result_edt)
    EditText search_result_edt;
    @ViewInject(R.id.search_result_back)
    RelativeLayout search_result_back;
    @ViewInject(R.id.search_filter_coupon_sb)
    SwitchButton search_filter_coupon_sb;
    @ViewInject(R.id.search_filter_rb)
    SoftRadioGroup search_filter_rb;
    @ViewInject(R.id.search_filter_comprehensive_rb)
    SoftRadioButton search_filter_comprehensive_rb;
    @ViewInject(R.id.search_filter_volume_rb)
    SoftRadioButton search_filter_volume_rb;
    @ViewInject(R.id.search_filter_coupon_rb)
    SoftRadioButton search_filter_coupon_rb;
    @ViewInject(R.id.search_filter_filter_rb)
    SoftRadioButton search_filter_filter_rb;

    @ViewInject(R.id.search_result_rv)
    RecyclerView search_result_rv;
    @ViewInject(R.id.search_result_topping)
    ImageView search_result_topping;

    private LoadingDialog loadingDialog;
    private Map<String,Object> parameters = new HashMap<>();
    private SearchResultRecViewAdapter adapter;
    private int page = 1;
    private SearchResultBean datas;
    private TopSmoothScroller mScroller;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.whitesmoke));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        mScroller = new TopSmoothScroller(this);
        loadingDialog = new LoadingDialog.Build(this).build();
//        search_result_edt.setFilters(new InputFilter[]{new MaxTextLengthFilter(10)});
        parameters.put(TOKEN,getAppToken());
        parameters.put("pagesize",PAGE_SIZE);
        parameters.put("page",page);
        Bundle bundle = getBundle();
        if (bundle != null){
            String searchKeyWord = bundle.getString(SEARCH_KEYWORD);
            if (!isEmpty(searchKeyWord)){
                search_result_edt.setText(searchKeyWord);
                search_result_edt.setSelection(searchKeyWord.length());
                search_result_delete.setVisibility(searchKeyWord.length() > 0 ? View.VISIBLE : View.GONE);
                parameters.put("para",searchKeyWord);
                mPresenter.onSearch(parameters);
            }
        }
        search_filter_rb.setOnCheckedChangeListener((group, checkedId, orientation) -> {
            switch (checkedId){
                //综合
                case R.id.search_filter_comprehensive_rb:
                    parameters.put("sort",0);
                    break;
                //销量
                case R.id.search_filter_volume_rb:
                    parameters.put("sort",orientation ? 1 : 2);
                    break;
                //券后价
                case R.id.search_filter_coupon_rb:
                    parameters.put("sort",orientation ? 3 : 4);
                    break;
                // 筛选
                case R.id.search_filter_filter_rb:
                    //右边菜单栏  如果打开 就关闭
                    if (search_result_dl.isDrawerOpen(Gravity.END)){
                        search_result_dl.closeDrawer(Gravity.END);
                    }else {
                        //如果关闭就打开
                        search_result_dl.openDrawer(Gravity.END);
                    }
                    break;
            }
            mPresenter.onSearch(parameters);
        });

        search_result_right_rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                //天猫
                case R.id.search_result_right_tianmao:

                    break;
                //不限制
                case R.id.search_result_right_unlimited:

                    break;
            }
        });

        search_result_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int len = s.length();
                search_result_delete.setVisibility(len > 0 ? View.VISIBLE : View.GONE);
            }
        });
        //删除
        search_result_delete.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL){
                int len = getEdtText(search_result_edt).length();
                search_result_delete.setVisibility(len > 0 ? View.VISIBLE : View.GONE);
            }
            return false;
        });
        //搜索
        search_result_edt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                parameters.put("para",getEdtText(search_result_edt));
                mPresenter.onSearch(parameters);
            }
            return false;
        });
        //优惠券
        search_filter_coupon_sb.setOnCheckedChangeListener((view, isChecked) -> {
            parameters.put("coupon",isChecked ? 1 : 0);
            mPresenter.onSearch(parameters);
        });
        setOnClikListener(search_result_back,search_result_cancel,search_result_delete,
                search_result_right_reset,search_result_right_confirm,search_result_topping);

        setRefreshLoadmore();
    }

    private void setRefreshLoadmore(){
        search_result_rl.setRefreshHeader(search_result_classic_header);
        search_result_rl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        search_result_rl.setOnRefreshListener(refreshlayout ->{
            page = 1;
            refreshlayout.resetNoMoreData();
            parameters.put("page",page);
            mPresenter.onSearch(parameters);
            refreshlayout.finishRefresh(REFRESH_LOAD_MORE_TIME);
        });
        search_result_rl.setOnLoadMoreListener(refreshlayout -> {
            if (datas != null && datas.getMore() == 1){
                parameters.put("page",++page);
                mPresenter.onSearch(parameters);
                refreshlayout.finishLoadMore(REFRESH_LOAD_MORE_TIME);
            }else {
                refreshlayout.finishLoadMoreWithNoMoreData();
            }
        });
    }


    @Override
    protected void widgetClick(int id) {
        switch (id){
            //返回
            case R.id.search_result_back:
            case R.id.search_result_cancel:
                finish();
                break;
            //清空输入框
            case R.id.search_result_delete:
                search_result_edt.setText("");
                break;
            //重置
            case R.id.search_result_right_reset:
                search_result_right_input_lowest.setText("");
                search_result_right_input_highest.setText("");
                if (!search_result_right_unlimited.isChecked()){
                    search_result_right_unlimited.setChecked(true);
                }
                parameters.remove("is_tmall");
                parameters.remove("start_price");
                parameters.remove("end_price");
                mPresenter.onSearch(parameters);
                break;
            //确定
            case R.id.search_result_right_confirm:
                parameters.put("is_tmall",search_result_right_unlimited.isChecked() ? 0 : 1);
                parameters.put("start_price",getEdtText(search_result_right_input_lowest));
                parameters.put("end_price",getEdtText(search_result_right_input_highest));
                search_result_dl.closeDrawer(Gravity.END);
                mPresenter.onSearch(parameters);
                break;
            //置顶
            case R.id.search_result_topping:
                if (manager != null && mScroller != null){
                    search_result_appbar.setExpanded(true);
                    mScroller.setTargetPosition(0);
                    manager.startSmoothScroll(mScroller);
                }
                break;
        }
    }


    @Override
    public void onSearchSuccess(SearchResultBean bean) {
        this.datas = bean;
        initLayout(bean);
    }

    private LinearLayoutManager manager;

    private void initLayout(SearchResultBean bean){
        if (adapter == null){
            manager = new LinearLayoutManager(this);
            manager.setOrientation(1);
            SpaceItemDecoration decoration = new SpaceItemDecoration(20,30);
            adapter = new SearchResultRecViewAdapter(this,bean.getResult_list());
            search_result_rv.setLayoutManager(manager);
            search_result_rv.addItemDecoration(decoration);
            search_result_rv.setAdapter(adapter);
            adapter.setOnSearchResultItemClickListener(id ->
                    $startActivity(MerchandiseDetailsActivity.class,TAO_BAO_ID,id));
        }else {
            adapter.addList(bean.getResult_list(),page);
        }
    }

    @Override
    public void onSearchFailure(String failure) {
        showOnlyConfirmDialog(failure, (dialog, which) -> finish());
    }

    @Override
    protected SearchResultPresenter CreatePresenter() {
        return new SearchResultPresenter();
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
