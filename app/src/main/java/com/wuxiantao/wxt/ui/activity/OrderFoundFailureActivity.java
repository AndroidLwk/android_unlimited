package com.wuxiantao.wxt.ui.activity;

import android.widget.RelativeLayout;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderFoundFailureActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-14 下午2:55
 * Description:${DESCRIPTION} 订单找回失败
 */
@ContentView(R.layout.activity_order_find_failure)
public class OrderFoundFailureActivity extends BaseActivity {
    @ViewInject(R.id.order_find_failure_online)
    RelativeLayout order_find_failure_online;

    @Override
    public void initView() {
        setOnClikListener(order_find_failure_online);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.order_find_failure_online:
                $startActivity(OnLineServiceActivity.class);
                break;
        }
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).setMiddleTitleText(R.string.find_order_error).build();
    }
}
