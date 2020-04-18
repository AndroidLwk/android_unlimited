package com.wuxiantao.wxt.mvp.withdraw;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.CommissionService;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.MODE;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:WithdrawInfoModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-17 上午9:04
 * Description:${DESCRIPTION}
 */
public class WithdrawModel extends BaseModel {

    protected void withdraw(BaseObserver<List> observer, Map<String,Object> map){
        int mode = 0;
        if (map.containsKey(MODE)){
            mode = (int) map.get(MODE);
        }
        switch (mode){
            case 1:
                map.remove(MODE);
                readBagWithdraw(observer,map);
                break;
            case 2:
            default:
                map.remove(MODE);
                commissionWithdraw(observer,map);
                break;
        }
    }

    //红包提现
    private void readBagWithdraw(BaseObserver<List> observer, Map<String,Object> map){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .redBagWithdraw(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

    //佣金提现
    private void commissionWithdraw(BaseObserver<List> observer, Map<String,Object> map){
        HttpManager.newInstance()
                .createService(CommissionService.class)
                .commissionWithdraw(map)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }

}
