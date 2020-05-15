package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.MyCardInfo;
import com.wuxiantao.wxt.bean.MySignInfo;
import com.wuxiantao.wxt.bean.ScrapingCardBean;
import com.wuxiantao.wxt.mvp.contract.ScrapingCardFragmentContract;
import com.wuxiantao.wxt.mvp.model.ScrapingCardFragmentModel;
import com.wuxiantao.wxt.mvp.model.TaskHallFragmentModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 下午2:26
 * Description:${DESCRIPTION}
 */
public class ScrapingCardFragmentPresenter extends BasePresenter<ScrapingCardFragmentContract.IScrapingCardFragmentView> {

    private ScrapingCardFragmentContract.IScrapingCardFragmentView view;

    public void getMyCardInfo(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MyCardInfo> observer = new BaseObserver<MyCardInfo>() {
            @Override
            public void onSuccess(MyCardInfo bean) {
                view.showMyCardInfo(bean);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.getMyCardInfoFailure(errorMsg);
            }
        };
        new ScrapingCardFragmentModel().getMyCardInfo(observer, token);
    }

    /**
     * 签到
     */
    public void taskSign(String token) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<MySignInfo> observer = new BaseObserver<MySignInfo>() {
            @Override
            public void onSuccess(MySignInfo info) {
                view.signSuccess(info);
            }

            @Override
            public void onFailure(String errorMsg) {
                view.signFailure(errorMsg);
            }
        };
        new TaskHallFragmentModel().taskSign(observer, token);
    }

    public void enrollBonus(String token, String type) {
        if (view == null) {
            view = getMvpView();
        }
        BaseObserver<List> observer = new BaseObserver<List>() {
            @Override
            public void onSuccess(List bean) {
                view.enrollBonusSuccess("分红成功");

            }

            @Override
            public void onFailure(String errorMsg) {
                view.getMyCardInfoFailure(errorMsg);
            }
        };
        new ScrapingCardFragmentModel().enrollBonus(observer, token, type);
    }

    public List<ScrapingCardBean> getScarapCasdTask() {
        List<ScrapingCardBean> mData_b = new ArrayList<>();
        ScrapingCardBean scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("任务大厅签到  +3张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("二维码邀请好友  +1张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("分享无限淘  +1张");
        mData_b.add(scrapingCardBean_b);
        scrapingCardBean_b = new ScrapingCardBean();
        scrapingCardBean_b.setNumTitle("登录斩妖诀游戏且在线5分钟  +1张");
        mData_b.add(scrapingCardBean_b);
        return mData_b;
    }
}
