package com.wuxiantao.wxt.mvp.withdraw;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.presenter.BasePresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseWithdrawInfoPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午9:06
 * Description:${DESCRIPTION}
 */
public class WithdrawBasePresenter<V extends WithdrawView> extends BasePresenter<V> {

    private V v;

    protected void withdraw(Map<String,Object> map,WithdrawModel model){
        if (v == null){
            v= getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(v) {
            @Override
            public void onSuccess(List msg) {
                v.withdrawSuccess(RESOURCES.getString(R.string.withdraw_processed));
            }

            @Override
            public void onFailure(String errorMsg) {
                v.withdrawFailure(errorMsg);
            }
        };
        model.withdraw(observer,map);
    }


}
