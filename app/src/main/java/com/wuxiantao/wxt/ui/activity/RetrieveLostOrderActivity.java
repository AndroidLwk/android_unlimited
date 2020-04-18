package com.wuxiantao.wxt.ui.activity;

import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RetrieveLostOrderActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-10 下午3:50
 * Description:${DESCRIPTION}找回丢失订单
 */
@ContentView(R.layout.activity_order_find)
public class RetrieveLostOrderActivity extends BaseActivity {
    @ViewInject(R.id.order_find_rl)
    SmartRefreshLayout order_find_rl;
    @ViewInject(R.id.order_find_back)
    RelativeLayout order_find_back;
    @ViewInject(R.id.order_find_taobao_number_edt)
    EditText order_find_taobao_number_edt;
    @ViewInject(R.id.order_find_btn)
    StateButton order_find_btn;
    @ViewInject(R.id.order_find_number_copy)
    TextView order_find_number_copy;

    @Override
    public void initView() {
        order_find_rl.setEnableRefresh(false);
        order_find_rl.setEnableLoadMore(false);
        setOnClikListener(order_find_back,order_find_btn,order_find_number_copy);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.order_find_back:
                finish();
                break;
            case R.id.order_find_btn:
                $startActivity(OrderFoundSuccessActivity.class);
                break;
            case R.id.order_find_number_copy:
                $startActivity(OrderFoundFailureActivity.class);
                break;
        }
    }

}
