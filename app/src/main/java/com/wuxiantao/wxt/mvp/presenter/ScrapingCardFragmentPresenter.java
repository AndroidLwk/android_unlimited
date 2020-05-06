package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.RedBagInfoBean;
import com.wuxiantao.wxt.mvp.contract.ScrapingCardFragmentContract;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class ScrapingCardFragmentPresenter extends BasePresenter<ScrapingCardFragmentContract.IScrapingCardFragmentView>  {

    private ScrapingCardFragmentContract.IScrapingCardFragmentView view;
    public void getRedBagInfo(String token) {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<RedBagInfoBean> observer = new BaseObserver<RedBagInfoBean>() {
            @Override
            public void onSuccess(RedBagInfoBean bean) {
               // view.getRedBagInfoSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
              //  view.getRedBagInfoFailure(errorMsg);
            }
        };
       // model.getRedBagInfo(observer,token);
    }
}
