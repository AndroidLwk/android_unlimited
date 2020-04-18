package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.RedBagWithdrawInfoBean;
import com.wuxiantao.wxt.mvp.contract.ApplyWithdrawContract;
import com.wuxiantao.wxt.mvp.model.ApplyWithdrawModel;
import com.wuxiantao.wxt.mvp.withdraw.WithdrawBasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class ApplyWithdrawPresenter extends WithdrawBasePresenter<ApplyWithdrawContract.IApplyView> implements ApplyWithdrawContract.IApplyPresenter {

    private ApplyWithdrawModel model = new ApplyWithdrawModel();
    private ApplyWithdrawContract.IApplyView view;

    @Override
    public void getWithdrawInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<RedBagWithdrawInfoBean> observer = new BaseObserver<RedBagWithdrawInfoBean>(view) {
            @Override
            public void onSuccess(RedBagWithdrawInfoBean bean) {
                view.getWithdrawInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getWithdrawInfoFailure(errorMsg);
            }
        };
        model.getReadBagWithdrawInfo(observer,token);
    }


    @Override
    public void withdraw(Map<String, Object> map) {
        super.withdraw(map,model);
    }


}
