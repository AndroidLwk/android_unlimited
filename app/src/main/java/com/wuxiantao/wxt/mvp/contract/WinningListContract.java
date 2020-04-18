package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:41
 * Description:${DESCRIPTION} 中奖名单
 */
public interface WinningListContract  {

    interface IWinningListView extends MvpView{
        void getWinningListSuccess(WinningListBean listBeans);
        void getWinningListFailure(String failure);
    }

    interface IWinningPresenter extends MvpPresenter<IWinningListView>{
        void getWinningList(String token,int page,int type);
    }
}
