package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.bean.TaoBaoSortBean;
import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSortContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午4:37
 * Description:${DESCRIPTION}
 */
public interface TaoBaoSortContract {

    interface ITaoBaoSortView extends MvpView{
        void getTaoBaoSortSuccess(List<TaoBaoSortBean> bean);
        void getTaoBaoSortFailure(String failure);
        void receiveRedBagSuccess(String msg);
        void receiveRedBagFailure(String failure);
        void noticeFailure(String failure);

        void noticeSuccess(String content);
    }

    interface ITaoBaoSortPresenter extends MvpPresenter<ITaoBaoSortView>{
         void getTaoBaoSort();
         void receiveRedBag(String token);
    }
}
