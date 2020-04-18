package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InstructContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-14 下午12:31
 * Description:${DESCRIPTION}
 */
public interface InstructContract {

    interface IInstructView extends MvpView{
        void instructSuccess(String url);
        void instructFailure(String failure);
    }

    interface IInstructPresenter extends MvpPresenter<IInstructView>{
        void  instruct();
    }

}
