package com.wuxiantao.wxt.mvp.search;

import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchView
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午5:19
 * Description:${DESCRIPTION}
 */
public interface SearchView extends MvpView{
    void onSearchSuccess(SearchResultBean bean);
    void onSearchFailure(String failure);
}
