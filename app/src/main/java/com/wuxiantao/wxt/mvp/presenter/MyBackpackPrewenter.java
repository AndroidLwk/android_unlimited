package com.wuxiantao.wxt.mvp.presenter;

import android.util.Log;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AlipayBean;
import com.wuxiantao.wxt.bean.BoxTypeBean;
import com.wuxiantao.wxt.bean.CardInfoBean;
import com.wuxiantao.wxt.bean.ExchangeBean;
import com.wuxiantao.wxt.bean.IsSetPayPassword;
import com.wuxiantao.wxt.bean.MyBoxInfo;
import com.wuxiantao.wxt.bean.OrderStatusBean;
import com.wuxiantao.wxt.bean.WeChatPayBean;
import com.wuxiantao.wxt.mvp.contract.MyBackpackContract;
import com.wuxiantao.wxt.mvp.model.MyBackpackModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.RESOURCES;

public class MyBackpackPrewenter extends BasePresenter<MyBackpackContract> {
    private MyBackpackContract view;
    private MyBackpackModel model = new MyBackpackModel();

    public void isSetPayPassword(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<IsSetPayPassword> observer = new BaseObserver<IsSetPayPassword>() {
            @Override
            public void onSuccess(IsSetPayPassword bean) {
                view.isSetPayPasswordSuccess(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.isSetPayPassword(token, observer);
    }

    public void myBox(String token, int page, int pid) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("page", page);
        map.put("pagesize", 200);
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

    public void exchange(String token, String card_id, String hid, String pass, String num) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("card_id", card_id);
        map.put("hid", hid);
        map.put("pass", pass);
        map.put("num", num);
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<ExchangeBean> observer = new BaseObserver<ExchangeBean>() {
            @Override
            public void onSuccess(ExchangeBean list) {
                view.exchangeSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.exchange(observer, map);
    }

    public void exchange_alipay(String token, String card_id, String hid, String pass, String num) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("card_id", card_id);
        map.put("hid", hid);
        map.put("pass", pass);
        map.put("num", num);
        map.put("type", "1");
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<AlipayBean> observer = new BaseObserver<AlipayBean>() {
            @Override
            public void onSuccess(AlipayBean bean) {
                view.onAliPay(bean.getOrder_id(), bean.getAlipay_message());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderCreateFailure(errorMsg);

            }
        };
        model.exchange_alipay(observer, map);
    }

    public void exchange_wx(String token, String card_id, String hid, String pass, String num) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("card_id", card_id);
        map.put("hid", hid);
        map.put("pass", pass);
        map.put("num", num);
        map.put("type", "2");
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<WeChatPayBean> observer = new BaseObserver<WeChatPayBean>() {
            @Override
            public void onSuccess(WeChatPayBean bean) {
                view.onWeChatPay(bean);

            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderCreateFailure(errorMsg);
            }
        };
        model.exchange_wx(observer, map);
    }


    public void useCard(String token, String cid, String num) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<CardInfoBean> observer = new BaseObserver<CardInfoBean>() {
            @Override
            public void onSuccess(CardInfoBean bean) {
                view.useCardSuccess(bean.getMsg());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.useCard(observer, token, cid, num);
    }

    public void discard(String token, String card_id, String num) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<CardInfoBean> observer = new BaseObserver<CardInfoBean>() {
            @Override
            public void onSuccess(CardInfoBean bean) {
                view.discardSuccess(bean.getMsg());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onFailure(errorMsg);
            }
        };
        model.discard(observer, token, card_id, num);
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

    public void checkOrderStatus(String token, String order_no) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<OrderStatusBean> observer = new BaseObserver<OrderStatusBean>() {
            @Override
            public void onSuccess(OrderStatusBean s) {
                view.onOrderPaySuccess(RESOURCES.getString(R.string.pay_success));
            }

            @Override
            public void onFailure(String errorMsg) {
                view.onOrderPayFailure(errorMsg);
            }
        };
        model.checkOrderStatus(observer, token, order_no);
    }

    public void changeBoxType(List<BoxTypeBean> list, int seletedPotion) {
        for (BoxTypeBean bean : list) {
            bean.setSeleted(list.indexOf(bean) == seletedPotion ? true : false);
        }
    }

    public List<MyBoxInfo.ListBean> getBoxAllData(List<MyBoxInfo.ListBean> list, int box_volume) {
        if (box_volume > 0) {
            int count = box_volume - list.size();
            for (int i = 0; i < count; i++) {
                list.add(new MyBoxInfo.ListBean());
            }

        }
        return list;
    }
}
