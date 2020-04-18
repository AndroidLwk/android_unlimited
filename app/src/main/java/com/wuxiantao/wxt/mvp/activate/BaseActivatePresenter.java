package com.wuxiantao.wxt.mvp.activate;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.promotion.BasePromotionPresenter;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseActivatePresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-4 下午10:52
 * Description:${DESCRIPTION}
 */
public class BaseActivatePresenter<V extends ActivateView> extends BasePromotionPresenter<V> {

    private V  view;
    private ActivateModel model = new ActivateModel();

    protected void onActivateFriend(Map<String,Object> map,int type){
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List list) {
                view.onActivateSuccess(RESOURCES.getString(R.string.activate_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onActivateFailure(errorMsg);
            }
        };
        if (type == 2){
            model.onActivateDoubleFriend(observer,map);
        }else {
            model.onActivateFriend(observer,map);
        }
    }




}
