package com.wuxiantao.wxt.mvp.model;

import com.wuxiantao.wxt.bean.HistoryRecordingBean;
import com.wuxiantao.wxt.bean.SearchHotBean;
import com.wuxiantao.wxt.mvp.contract.SearchContract;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午3:29
 * Description:${DESCRIPTION}
 */
public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {

    private SearchContract.ISearchView view;
    private SearchModel model = new SearchModel();

    @Override
    public void getSearchHot() {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<SearchHotBean> observer = new BaseObserver<SearchHotBean>() {
            @Override
            public void onSuccess(SearchHotBean bean) {
                if (bean != null){
                    view.getSearchHotSuccess(bean);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getSearchHotFailure(errorMsg);
            }
        };
        model.getSearchHot(observer);
    }

    @Override
    public void getHistoryRecording(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<HistoryRecordingBean> observer = new BaseObserver<HistoryRecordingBean>() {
            @Override
            public void onSuccess(HistoryRecordingBean list) {
                view.getHistoryRecordingSuccess(list);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getHistoryRecordingFailure(errorMsg);
            }
        };
        model.getHistoryRecording(observer,token);
    }


}
