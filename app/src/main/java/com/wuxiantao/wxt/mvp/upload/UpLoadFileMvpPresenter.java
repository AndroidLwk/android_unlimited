package com.wuxiantao.wxt.mvp.upload;

import com.wuxiantao.wxt.mvp.presenter.MvpPresenter;
import com.wuxiantao.wxt.mvp.view.MvpView;

import java.io.File;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MvpPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:30
 * Description:${DESCRIPTION} MvpPresenter-Presenter的基础类，控制的MvpView的子类
 */
public interface UpLoadFileMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void upLoadFile(File file);
}
