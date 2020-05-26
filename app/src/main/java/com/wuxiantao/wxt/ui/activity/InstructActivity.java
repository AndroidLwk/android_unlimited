package com.wuxiantao.wxt.ui.activity;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.InstructContract;
import com.wuxiantao.wxt.mvp.presenter.InstructPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Company:成都可信网络科技有限责任公司
 * FileName:InstructActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-13 下午4:50
 * Description:${DESCRIPTION} 省钱教程
 */
@ContentView(R.layout.activity_instruct)
public class InstructActivity extends MvpActivity<InstructPresenter, InstructContract.IInstructView> implements InstructContract.IInstructView {
    @ViewInject(R.id.instruct_back)
    ImageView instruct_back;
    @ViewInject(R.id.instruct_img)
    SubsamplingScaleImageView instruct_img;

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        mPresenter.instruct();
        setOnClikListener(instruct_back);
    }


    @Override
    protected void widgetClick(int id) {
        if (id == R.id.instruct_back){
            finish();
        }
    }


    @Override
    protected InstructPresenter CreatePresenter() {
        return new InstructPresenter();
    }

    @Override
    public void instructSuccess(String url) {
        ////最小显示比例
        instruct_img.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        instruct_img.setMinScale(0.7f);
        //最大显示比例
        instruct_img.setMaxScale(0.7f);
        //设置ImageViewState设置初始显示比例 三个参数为：scale,center,orientation
        instruct_img.setImage(ImageSource.asset("img/instruct_img.png"),
                new ImageViewState(1.0f, new PointF(0, 0), 0));
    }



    @Override
    public void instructFailure(String failure) {
        ToastUtils.error(failure);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}
