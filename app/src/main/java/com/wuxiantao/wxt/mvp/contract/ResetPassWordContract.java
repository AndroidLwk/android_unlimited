package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.vercode.ObtainCodeMvpPresenter;
import com.wuxiantao.wxt.mvp.vercode.ObtainCodeView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ResetPassWordContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-6 下午8:22
 * Description:${DESCRIPTION}
 */
public interface ResetPassWordContract {

    interface IResetView extends ObtainCodeView {
        //将一些操作界面的方法在这里声明

    }

    interface IResetPresenter extends ObtainCodeMvpPresenter<IResetView> {
        //将一些逻辑处理的方法在此声明
        void obtainCode(String mobile,int type);
    }
}
