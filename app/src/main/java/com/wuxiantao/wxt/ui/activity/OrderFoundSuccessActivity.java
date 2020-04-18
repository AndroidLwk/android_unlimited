package com.wuxiantao.wxt.ui.activity;

import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.textview.TaoBaoTagTextView;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderFoundFailureActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-14 下午2:55
 * Description:${DESCRIPTION} 订单找回成功
 */
@ContentView(R.layout.activity_order_find_success)
public class OrderFoundSuccessActivity extends BaseActivity {
    @ViewInject(R.id.order_find_success_title)
    TaoBaoTagTextView order_find_success_title;
    @ViewInject(R.id.order_find_success_money)
    TextView order_find_success_money;
    @ViewInject(R.id.order_find_success_confirm)
    StateButton order_find_success_confirm;

    @Override
    public void initView() {

    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish()).setMiddleTitleText(R.string.find_order__success).build();
    }
}
