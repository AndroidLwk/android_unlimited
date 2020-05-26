package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.HistoryRecordingBean;
import com.wuxiantao.wxt.bean.SearchHotBean;
import com.wuxiantao.wxt.mvp.contract.SearchContract;
import com.wuxiantao.wxt.mvp.model.SearchPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.SEARCH_KEYWORD;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午6:13
 * Description:${DESCRIPTION} 搜索界面
 */
@ContentView(R.layout.activity_search)
public class SearchActivity  extends MvpActivity<SearchPresenter, SearchContract.ISearchView> implements TextWatcher,SearchContract.ISearchView {
    @ViewInject(R.id.search_back)
    RelativeLayout search_back;
    @ViewInject(R.id.search_cancel)
    TextView search_cancel;
    @ViewInject(R.id.search_edt)
    EditText search_edt;
    @ViewInject(R.id.search_delete)
    ImageView search_delete;
    @ViewInject(R.id.search_hot_labelview)
    LabelsView search_hot_labelview;
    @ViewInject(R.id.search_history_labelview)
    LabelsView search_history_labelview;
    @ViewInject(R.id.search_history_no_recording)
    TextView search_history_no_recording;
    @ViewInject(R.id.search_save_money_tutorial)
    TextView search_save_money_tutorial;

    @Override
    protected SearchPresenter CreatePresenter() {
        return new SearchPresenter();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.whitesmoke));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        mPresenter.getSearchHot();
        mPresenter.getHistoryRecording(getAppToken());
//        search_edt.setFilters(new InputFilter[]{new MaxTextLengthFilter(10)});
        setOnClikListener(search_back,search_delete,search_cancel);
        search_edt.addTextChangedListener(this);
        //删除
        search_edt.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL){
                search_delete.setVisibility(getEdtText(search_edt).length() > 0 ? View.VISIBLE : View.GONE);
            }
            return false;
        });
        //搜索
        search_edt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                startSearch(getEdtText(search_edt));
            }
            return false;
        });
        setOnClikListener(search_save_money_tutorial);
    }


    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            //返回
            case R.id.search_cancel:
            case R.id.search_back:
                finish();
                break;
            //清空输入框
            case R.id.search_delete:
                search_edt.setText("");
                break;
            //省钱教程
            case R.id.search_save_money_tutorial:
                $startActivity(InstructActivity.class);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        search_delete.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
    }

    //获取热搜关键词
    @Override
    public void getSearchHotSuccess(SearchHotBean bean) {
        search_hot_labelview.setLabels(bean.getQuerys());
        search_hot_labelview.setOnLabelSelectChangeListener((label, data, isSelect, position) -> {
            if (isSelect){
                search_edt.setText(getTextViewText(label));
                search_edt.setSelection(getTextViewText(label).length());
                startSearch(getTextViewText(label));
            }
        });
    }


    @Override
    public void getHistoryRecordingSuccess(HistoryRecordingBean bean) {
        List<HistoryRecordingBean.ListBean> beans = bean.getList();
        List<String> list = new ArrayList<>();
        for (HistoryRecordingBean.ListBean b : beans){
            list.add(b.getKeyword());
        }
        search_history_labelview.setVisibility(View.VISIBLE);
        search_history_no_recording.setVisibility(View.GONE);
        search_history_labelview.setLabels(list);
        search_history_labelview.setOnLabelSelectChangeListener((label, data, isSelect, position) -> {
            if (isSelect){
                search_edt.setText(getTextViewText(label));
                search_edt.setSelection(getTextViewText(label).length());
                startSearch(getTextViewText(label));
            }
        });
    }


    @Override
    public void getSearchHotFailure(String failure) {
        ToastUtils.error(failure);
    }



    @Override
    public void getHistoryRecordingFailure(String failure) {
        if (failure.contains(getString(R.string.no_records))){
            search_history_labelview.setVisibility(View.VISIBLE);
            search_history_no_recording.setVisibility(View.GONE);
        }else {
            ToastUtils.error(failure);
        }
    }

    private void startSearch(String content){
        $startActivity(SearchResultActivity.class,SEARCH_KEYWORD,content);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
