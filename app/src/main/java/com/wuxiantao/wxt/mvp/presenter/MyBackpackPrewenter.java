package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MyBackpackBean;
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

    public void myBox(String token, int page,int pid) {
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

    /**
     * 碎片
     *
     * @return
     */
    public List<MyBackpackBean> getData_one(List<MyBackpackBean> list) {
        list.clear();
        for (int i = 46; i <= 225; i++) {
            MyBackpackBean bean = new MyBackpackBean();
            bean.setPid(1);
            bean.setHeroName("曹操碎片" + i);
            bean.setImg_id(i);
            list.add(bean);
        }
        return list;
    }

    /**
     * 皮肤卡
     *
     * @param list
     */
    public void notify_data2(List<MyBackpackBean> list) {
        list.clear();
        for (int i = 13; i <= 23; i++) {
            MyBackpackBean bean = new MyBackpackBean();
            bean.setPid(2);
            bean.setHeroName("皮肤卡" + i);
            bean.setImg_id(i);
            list.add(bean);
        }

    }

    /**
     * 现金卡
     *
     * @param list
     */
    public void notify_data3(List<MyBackpackBean> list) {
        list.clear();
        for (int i = 4; i <= 8; i++) {
            MyBackpackBean bean = new MyBackpackBean();
            bean.setPid(3);
            bean.setHeroName("现金卡" + i);
            bean.setImg_id(i);
            list.add(bean);
        }

    }
}
