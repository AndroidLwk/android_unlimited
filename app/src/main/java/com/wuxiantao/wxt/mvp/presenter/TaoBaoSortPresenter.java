package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.NoticeBean;
import com.wuxiantao.wxt.bean.TaoBaoSortBean;
import com.wuxiantao.wxt.mvp.contract.TaoBaoSortContract;
import com.wuxiantao.wxt.mvp.model.InfomationModel;
import com.wuxiantao.wxt.mvp.model.TaoBaoSortModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSortPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午4:42
 * Description:${DESCRIPTION}
 */
public class TaoBaoSortPresenter extends BasePresenter<TaoBaoSortContract.ITaoBaoSortView> implements TaoBaoSortContract.ITaoBaoSortPresenter {

    private TaoBaoSortContract.ITaoBaoSortView view;
    private TaoBaoSortModel model = new TaoBaoSortModel();
    private InfomationModel model2 = new InfomationModel();


    @Override
    public void getTaoBaoSort() {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List<TaoBaoSortBean>> observer = new BaseObserver<List<TaoBaoSortBean>>() {
            @Override
            public void onSuccess(List<TaoBaoSortBean> baoSortBean) {
                view.getTaoBaoSortSuccess(baoSortBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getTaoBaoSortFailure(errorMsg);
            }
        };
        model.getTaoBaoSort(observer);
    }

    @Override
    public void receiveRedBag(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List list) {
                view.receiveRedBagSuccess("红包已领取成功");
            }

            @Override
            public void onFailure(String errorMsg) {
                view.receiveRedBagFailure(errorMsg);
            }
        };
        model.receiveRedBag(observer,token);
    }
    public void notice(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<NoticeBean> observer = new BaseObserver<NoticeBean>() {
            @Override
            public void onSuccess(NoticeBean bean) {
                view.noticeSuccess(bean.getContent());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.noticeFailure(errorMsg);
            }
        };
        model2.notice(observer, token);
    }
}
