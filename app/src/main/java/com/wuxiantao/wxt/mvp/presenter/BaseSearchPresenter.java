package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.mvp.search.SearchModel;
import com.wuxiantao.wxt.mvp.search.SearchView;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseSearchPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午5:32
 * Description:${DESCRIPTION}
 */
public class BaseSearchPresenter<V extends SearchView> extends BasePresenter<V> {

    private V view;
    private SearchModel model = new SearchModel();

    protected void onSearch(Map<String,Object> parameters){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<SearchResultBean> observer = new BaseObserver<SearchResultBean>(view) {
            @Override
            public void onSuccess(SearchResultBean bean) {
                view.onSearchSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onSearchFailure(errorMsg);
            }
        };
        model.onSearch(observer,parameters);
    }
}
