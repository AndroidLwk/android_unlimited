package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.mvp.contract.SearchResultContract;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchResultPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午4:51
 * Description:${DESCRIPTION}
 */
public class SearchResultPresenter extends BaseSearchPresenter<SearchResultContract.IResultView> implements SearchResultContract.IResultPresenter {

    @Override
    public void onSearch(Map<String, Object> parameters) {
        super.onSearch(parameters);
    }
}
