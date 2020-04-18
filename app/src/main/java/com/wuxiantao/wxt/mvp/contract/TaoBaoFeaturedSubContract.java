package com.wuxiantao.wxt.mvp.contract;

import com.wuxiantao.wxt.mvp.search.SearchMvpPresenter;
import com.wuxiantao.wxt.mvp.search.SearchView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoFeaturedSubContract
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午5:12
 * Description:${DESCRIPTION}
 */
public interface TaoBaoFeaturedSubContract {

    interface ITaoBaoSubView extends SearchView {

    }

    interface ITaoBaoSubPresenter extends SearchMvpPresenter<ITaoBaoSubView> {

    }
}
