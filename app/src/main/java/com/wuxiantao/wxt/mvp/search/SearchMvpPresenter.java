package com.wuxiantao.wxt.mvp.search;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchMvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午5:20
 * Description:${DESCRIPTION}
 */
public interface SearchMvpPresenter<V extends SearchView> extends MvpPresenter<V> {

    void onSearch(Map<String,Object> parameters);
}
