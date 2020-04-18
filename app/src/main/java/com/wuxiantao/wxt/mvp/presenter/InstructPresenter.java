package com.wuxiantao.wxt.mvp.presenter;

import com.wuxiantao.wxt.bean.InstructBean;
import com.wuxiantao.wxt.mvp.contract.InstructContract;
import com.wuxiantao.wxt.mvp.model.InstructModel;
import com.wuxiantao.wxt.net.base.BaseObserver;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InstructPresenter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-14 下午12:36
 * Description:${DESCRIPTION}
 */
public class InstructPresenter extends BasePresenter<InstructContract.IInstructView> implements InstructContract.IInstructPresenter {

    private InstructContract.IInstructView view;
    private InstructModel model = new InstructModel();

    @Override
    public void instruct() {
        if (view == null){
            view = getMvpView();
        }
        BaseObserver<InstructBean> observer = new BaseObserver<InstructBean>(view) {
            @Override
            public void onSuccess(InstructBean instructBean) {
                view.instructSuccess(instructBean.getImg());
            }

            @Override
            public void onFailure(String errorMsg) {
                view.instructFailure(errorMsg);
            }
        };
        model.instruct(observer);
    }


}
