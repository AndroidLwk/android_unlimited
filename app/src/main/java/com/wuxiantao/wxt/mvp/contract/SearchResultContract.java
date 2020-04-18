package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.search.SearchMvpPresenter;
import com.wuxiantao.wxt.mvp.search.SearchView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchResultContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午4:49
 * Description:${DESCRIPTION}
 */
public interface SearchResultContract {

    interface IResultView extends SearchView {

    }

    interface IResultPresenter extends SearchMvpPresenter<IResultView> {

    }


}
