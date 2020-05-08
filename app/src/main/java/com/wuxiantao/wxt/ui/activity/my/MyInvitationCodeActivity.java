package com.wuxiantao.wxt.ui.activity.my;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.mvp.contract.MyInvitationContract;
import com.wuxiantao.wxt.mvp.contract.MyMemberContract;
import com.wuxiantao.wxt.mvp.presenter.MyInvitationPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的邀请码
 */
@ContentView(R.layout.activity_myinvitationcode)
public class MyInvitationCodeActivity extends MvpActivity<MyInvitationPresenter, MyInvitationContract> implements MyMemberContract {
    @ViewInject(R.id.mine_fansi_back)
    RelativeLayout mine_fansi_back;
    @ViewInject(R.id.mine_fansi_title_img)
    ImageView mine_fansi_title_img;
    @ViewInject(R.id.mine_fansi_title_text)
    TextView mine_fansi_title_text;
    @ViewInject(R.id.tv_hearderInfo)
    TextView tv_hearderInfo;
    @ViewInject(R.id.iv_invateCode)
    ImageView iv_invateCode;
    @Override
    protected void initView() {
        setOnClikListener(mine_fansi_back, iv_invateCode);
    }

    @Override
    protected void widgetClick(int id) {
        switch (id) {
            case R.id.mine_fansi_back:
                finish();
                break;
            case R.id.iv_invateCode:
                break;
        }
    }

    @Override
    protected MyInvitationPresenter CreatePresenter() {
        return new MyInvitationPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
