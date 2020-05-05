package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MyDepositBean;
import com.wuxiantao.wxt.mvp.contract.MineContract;
import com.wuxiantao.wxt.mvp.model.MineModel;
import com.wuxiantao.wxt.mvp.order.BaseOrderTypePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class MinePresenter extends BaseOrderTypePresenter<MineContract.IMineView> implements MineContract.ILoginPresenter {

    private MineModel model = new MineModel();
    private MineContract.IMineView view;

    @Override
    public void obtainMyDeposit(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<MyDepositBean> observer = new BaseObserver<MyDepositBean>(view) {
            @Override
            public void onSuccess(MyDepositBean bean) {
                  view.obtainMyDepositSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                 view.obtainMyDepositFailure(errorMsg);
            }
        };
        model.obtainMyDeposit(observer,token);
    }

    @Override
    public void getLatelyOrder(int type, String token, String order_no) {

    }

    @Override
    public void getOrderType(String token) {
        super.getOrderType(token);
    }
}
