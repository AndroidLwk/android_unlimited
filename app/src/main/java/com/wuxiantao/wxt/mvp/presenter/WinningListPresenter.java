package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.WinningListBean;
import com.wuxiantao.wxt.mvp.contract.WinningListContract;
import com.wuxiantao.wxt.mvp.model.WinningListModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WinningListPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-11 下午2:42
 * Description:${DESCRIPTION}
 */
public class WinningListPresenter extends BasePresenter<WinningListContract.IWinningListView> implements WinningListContract.IWinningPresenter {

    private WinningListContract.IWinningListView view;
    private WinningListModel model = new WinningListModel();

    @Override
    public void getWinningList(String token,int page, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<WinningListBean> observer = new BaseObserver<WinningListBean>() {
            @Override
            public void onSuccess(WinningListBean bean) {
                view.getWinningListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getWinningListFailure(errorMsg);
            }
        };
        //中奖名单列表类型  0.铜钥匙  1.银钥匙 2.金钥匙
        switch (type){
            case 0:
                model.getWinningCopperKeyList(observer,token,page);
                break;
            case 1:
                model.getWinningSilverKeyList(observer,token,page);
                break;
            case 2:
                model.getWinningGoldKeyList(observer,token,page);
                break;
        }
    }
}
