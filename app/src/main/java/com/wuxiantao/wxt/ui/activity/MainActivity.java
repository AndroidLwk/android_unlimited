package com.wuxiantao.wxt.ui.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.MainContract;
import com.wuxiantao.wxt.mvp.presenter.MainPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MainActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午3:16
 * Description:${DESCRIPTION}
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends MvpActivity<MainPresenter, MainContract.IMainView> implements MainContract.IMainView{

    @ViewInject(R.id.main_img)
    ImageView main_img;

    @Override
    public void initView() {
        mPresenter.downLoadImage();
    }


    @Override
    public void downLoadImageSuccess(Bitmap bitmap) {
        main_img.setImageBitmap(bitmap);
    }

    @Override
    protected MainPresenter CreatePresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
