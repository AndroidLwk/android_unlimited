package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.WeChatLoginBean;
import com.wuxiantao.wxt.mvp.contract.WeChatLoginContract;
import com.wuxiantao.wxt.mvp.model.WeChatLoginModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class WeChatLoginPresenter extends BasePresenter<WeChatLoginContract.ILoginView> implements WeChatLoginContract.ILoginPresenter {

    private WeChatLoginContract.ILoginView view;
    private WeChatLoginModel model = new WeChatLoginModel();


    public void weChatLogin(String code) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<WeChatLoginBean> observer = new BaseObserver<WeChatLoginBean>() {
            @Override
            public void onSuccess(WeChatLoginBean weChatLoginBean) {
                view.weChatLoginSuccess(weChatLoginBean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.weChatLoginFailure(errorMsg);
            }
        };
        model.weChatLogin(observer,code);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    @Override
    public void weChatBingding(String token, String code) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>(view) {
            @Override
            public void onSuccess(List list) {
                view.weChatBindingSuccess(RESOURCES.getString(R.string.wechat_bingding_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.weChatBindingFailure(errorMsg);
            }
        };
        model.weChatBinding(observer,token,code);
    }
}
