package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.FriendListBean;
import com.wuxiantao.wxt.bean.MakeGoldBean;
import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.bean.UnfastenRedbagBean;
import com.wuxiantao.wxt.mvp.contract.DepositContract;
import com.wuxiantao.wxt.mvp.model.DepositModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class DepositPresenter extends BasePresenter<DepositContract.IDepositView> implements DepositContract.IDepositPresenter {

    private DepositModel model = new DepositModel();
    private DepositContract.IDepositView view;


    @Override
    public void getRedBagInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<RedBagInfoBean> observer = new BaseObserver<RedBagInfoBean>() {
            @Override
            public void onSuccess(RedBagInfoBean bean) {
                view.getRedBagInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getRedBagInfoFailure(errorMsg);
            }
        };
        model.getRedBagInfo(observer,token);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    @Override
    public void openRedBag(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<UnfastenRedbagBean> observer = new BaseObserver<UnfastenRedbagBean>() {
            @Override
            public void onSuccess(UnfastenRedbagBean bean) {
                view.openRedBagSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.openRedBagFailure(errorMsg);
            }
        };
        model.openRedBag(observer,token);
    }

    @Override
    public void onMakeGold(String token, int type) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<MakeGoldBean> observer = new BaseObserver<MakeGoldBean>() {
            @Override
            public void onSuccess(MakeGoldBean bean) {
                view.onMakeGoldSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onMakeGoldFailure(errorMsg);
            }
        };
        if (type == 1){
            model.onMakeGold(observer,token);
        }else {
            model.onMakeGoldDouble(observer,token);
        }
    }


    @Override
    public void onGetFriendList(Map<String, Object> map) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<FriendListBean> observer = new BaseObserver<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean bean) {
                view.onGetFriendListSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onGetFriendListFailure(errorMsg);
            }
        };
        model.onGetFriendList(observer,map);
    }


}
