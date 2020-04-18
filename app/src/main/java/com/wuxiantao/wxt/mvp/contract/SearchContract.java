package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.HistoryRecordingBean;
import com.wuxiantao.wxt.bean.SearchHotBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-15 下午3:26
 * Description:${DESCRIPTION}
 */
public interface SearchContract {

    interface ISearchView extends MvpView{
        void getSearchHotSuccess(SearchHotBean bean);
        void getSearchHotFailure(String failure);
        void getHistoryRecordingSuccess(HistoryRecordingBean bean);
        void getHistoryRecordingFailure(String failure);
    }

    interface ISearchPresenter extends MvpPresenter<ISearchView>{
        void getSearchHot();
        void getHistoryRecording(String token);
    }
}
