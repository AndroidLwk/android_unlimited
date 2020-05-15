package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.model.MyBackpackModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBackpackPrewenter extends BasePresenter<MyBackpackContract> {
    private MyBackpackContract view;
    private MyBackpackModel model = new MyBackpackModel();

    public void myBox(String token, int page, int pid) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("page", page);
        map.put("pageSize", 20);
        map.put("pid", pid);
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MyBoxInfo> observer = new BaseObserver<MyBoxInfo>() {
            @Override
            public void onSuccess(MyBoxInfo bean) {
                view.showMyBackPack(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.myBox(observer, map);
    }

    public void getBoxCate(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List<BoxTypeBean>> observer = new BaseObserver<List<BoxTypeBean>>() {
            @Override
            public void onSuccess(List<BoxTypeBean> list) {
                changeBoxType(list, 0);
                view.showBoxType(list);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.getBoxCate(observer, token);
    }

    public void changeBoxType(List<BoxTypeBean> list, int seletedPotion) {
        for (BoxTypeBean bean : list) {
            bean.setSeleted(list.indexOf(bean) == seletedPotion ? true : false);
        }
    }
}
