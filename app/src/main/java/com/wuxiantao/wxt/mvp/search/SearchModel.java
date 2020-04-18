package com.wuxiantao.wxt.mvp.search;

import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.bean.SearchResultBean;
import com.wuxiantao.wxt.mvp.model.BaseModel;
import com.wuxiantao.wxt.net.base.BaseObserver;
import com.wuxiantao.wxt.net.helper.RxHelper;
import com.wuxiantao.wxt.net.http.HttpManager;
import com.wuxiantao.wxt.net.service.TaoBaoApiService;

import java.util.Map;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SearchModel
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-16 下午5:25
 * Description:${DESCRIPTION}
 */
public class SearchModel extends BaseModel {

    /**
     *  淘宝搜索
     * @param observer observer
     * @param parameters parameters
     *  1.para 搜索关键字
     *  2.cat 淘宝分类ID
     *  3.coupon 当不传递coupon参数或coupon=0时，默认搜索包含无券产品，当 传coupon=1时则搜索有券产品。
     *  4.is_tmall 是否商城商品，默认0（可选0或1），设为1表示商品是天猫商城商品，不设置或0表示不限制。
     *  5.sort 排序，默认 tk_rate_des（淘客佣金比率降序）。 值0：综合，1：销量降序，2：销量升序，3：价格降序，4：价格升序
     *  6.start_price 指折扣价范围下限，默认值 0
     *  7.end_price 指折扣价范围上限，默认值 0
     */
    public void onSearch(BaseObserver<SearchResultBean> observer, Map<String,Object> parameters){
        HttpManager.newInstance()
                .createService(TaoBaoApiService.class)
                .onSearch(parameters)
                .compose(RxHelper.observableIO2Main(BaseApplication.getAppContext()))
                .subscribe(observer);
    }
}
